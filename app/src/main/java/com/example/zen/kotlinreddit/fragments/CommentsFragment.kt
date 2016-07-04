package com.example.zen.kotlinreddit.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.zen.kotlinreddit.App
import com.example.zen.kotlinreddit.R
import com.example.zen.kotlinreddit.models.Comment
import com.example.zen.kotlinreddit.views.DividerItemDecoration
import kotlinx.android.synthetic.main.front_page.*
import kotlinx.android.synthetic.main.row_comment.view.*
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Action1
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription
import java.util.*

class CommentsFragment : Fragment() {
	var subscriptions = CompositeSubscription()
	val table = "comments"
	val select = "select * from comments where parent = ?"
	val layout = LinearLayoutManager(context)
	var pid : String? = null
	//var pid: Int? = null

	companion object {
		fun newInstance(postId: String) : CommentsFragment {
			val frag = CommentsFragment()
			val bundle = Bundle()
			bundle.putString("pid", postId)
			frag.arguments = bundle
			return frag
		}

		fun newInstance(pid: Int) : CommentsFragment {
			val frag = CommentsFragment()
			val bundle = Bundle()
			bundle.putInt("pid", pid)
			frag.arguments = bundle
			return frag
		}
	}

	override fun onResume() {
		super.onResume()
		subscriptions = CompositeSubscription()
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

		rv.setHasFixedSize(true)
		rv.layoutManager = layout
		rv.addItemDecoration(DividerItemDecoration(ResourcesCompat.getDrawable(resources, R.drawable.abc_list_divider_mtrl_alpha, null)!!))
		rv.adapter = adapter
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return inflater.inflate(R.layout.front_page, container, false)
	}

	override fun onPause() {
		super.onPause()
		subscriptions.clear()
	}
}

class CommentsAdapter(val context: Context): RecyclerView.Adapter<CommentsViewHolder>(), Action1<List<Comment>> {
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
		holder.txtBody.text = items[idx].body
		holder.txtCreated.text = DateUtils.getRelativeTimeSpanString(items[idx].created!! * 1000L,	now, DateUtils.MINUTE_IN_MILLIS)
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder? {
		return CommentsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_comment, parent, false))
	}

}

class CommentsViewHolder(iv: View): RecyclerView.ViewHolder(iv) {
	val txtAuthor = iv.txtAuthor
	val txtBody = iv.txtBody
	val txtCreated = iv.txtCommentCreated
}