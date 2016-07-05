package com.example.zen.kotlinreddit.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.format.DateUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.zen.kotlinreddit.App
import com.example.zen.kotlinreddit.R
import com.example.zen.kotlinreddit.models.Comment
import com.example.zen.kotlinreddit.models.CommentHeader
import com.example.zen.kotlinreddit.views.DividerItemDecoration
import kotlinx.android.synthetic.main.comments.*
import kotlinx.android.synthetic.main.row_comment.view.*
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Action1
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription
import java.util.*

class CommentsFragment : Fragment() {
	val subscriptions = CompositeSubscription()
	val table = "comments"
	val select = "select * from comments where parent = ?"
	val layout = LinearLayoutManager(context)
	var pid: String? = null
	//var pid: Int? = null

	companion object {
		fun newInstance(postId: String): CommentsFragment {
			val frag = CommentsFragment()
			val bundle = Bundle()
			bundle.putString("pid", postId)
			frag.arguments = bundle
			return frag
		}

		fun newInstance(pid: Int): CommentsFragment {
			val frag = CommentsFragment()
			val bundle = Bundle()
			bundle.putInt("pid", pid)
			frag.arguments = bundle
			return frag
		}
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		pid = arguments.getString("pid")
		//pid = arguments.getInt("pid")
		println("parent: $pid")

		val adapter = CommentsAdapter(context)

		subscriptions.add(App.sdb.createQuery(table, select, pid.toString())
			.mapToList(Comment.MAPPER)
			.subscribeOn(Schedulers.newThread())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(adapter))

		subscriptions.add(App.sdb.createQuery("comment_headers", "select * from comment_headers where parent = ? limit 1", pid)
			.mapToOne(CommentHeader.MAPPER)
			//.subscribeOn(Schedulers.newThread())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe {
				println("TTT: ${it.title}")
				txtCommentHeaderTitle.text = it.title
				txtCommentHeaderAuthor.text = it.author
				txtCommentHeaderSelfText.text = it.selftext
			})

		list.setHasFixedSize(true)
		list.layoutManager = layout
		list.addItemDecoration(DividerItemDecoration(ResourcesCompat.getDrawable(resources, R.drawable.abc_list_divider_mtrl_alpha, null)!!))
		list.adapter = adapter

		var firstVisibleInListview = layout.findFirstVisibleItemPosition()
		list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
			override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
				super.onScrolled(recyclerView, dx, dy)

				val currentFirstVisible = layout.findFirstVisibleItemPosition()

				if (currentFirstVisible > firstVisibleInListview)
					Log.i("RecyclerView scrolled: ", "scroll up!")
				else
					Log.i("RecyclerView scrolled: ", "scroll down!")

				firstVisibleInListview = currentFirstVisible
			}

			override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
				super.onScrollStateChanged(recyclerView, newState)
			}

		})
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return inflater.inflate(R.layout.comments, container, false)
	}

	override fun onPause() {
		super.onPause()
		subscriptions.unsubscribe()
	}
}

class CommentsAdapter(val context: Context) : RecyclerView.Adapter<CommentsViewHolder>(), Action1<List<Comment>> {
	val items = ArrayList<Comment>()
	val now = System.currentTimeMillis()

	override fun call(t: List<Comment>) {
		items.addAll(t)
		notifyDataSetChanged()
	}

	override fun getItemCount(): Int {
		return items.size
	}

	override fun onBindViewHolder(holder: CommentsViewHolder, idx: Int) {
		holder.txtAuthor.text = items[idx].author
		holder.txtBody.loadMarkdown(items[idx].body)
		holder.txtCreated.text = DateUtils.getRelativeTimeSpanString(items[idx].created!! * 1000L, now, DateUtils.MINUTE_IN_MILLIS)
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder? {
		return CommentsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_comment, parent, false))
	}

}

class CommentsViewHolder(iv: View) : RecyclerView.ViewHolder(iv) {
	val txtAuthor = iv.txtAuthor
	val txtBody = iv.txtBody
	val txtCreated = iv.txtCommentCreated
}