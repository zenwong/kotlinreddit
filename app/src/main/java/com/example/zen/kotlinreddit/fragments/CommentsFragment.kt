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
import com.example.zen.kotlinreddit.models.CommentHeader
import com.example.zen.kotlinreddit.views.DividerItemDecoration
import com.poliveira.parallaxrecyclerview.ParallaxRecyclerAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.comment_header.*
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
	var headerView: View? = null
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

		//val adapter = CommentsAdapter(context)
		var test = ArrayList<Comment>()
		val adapter = ParallaxCommentAdapter(context, test)
		adapter.setParallaxHeader(headerView, list)
//		adapter.setOnParallaxScroll { percentage, offset, view ->
//			val c = activity.toolbar.background
//			c.alpha = Math.round(percentage * 255)
//			activity.toolbar.background = c
//		}

		list.setHasFixedSize(true)
		list.layoutManager = layout
		list.addItemDecoration(DividerItemDecoration(ResourcesCompat.getDrawable(resources, R.drawable.abc_list_divider_mtrl_alpha, null)!!))
		list.adapter = adapter

		subscriptions.add(App.sdb.createQuery(table, select, pid.toString())
			.mapToList(Comment.MAPPER)
			.subscribeOn(Schedulers.newThread())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(adapter))

		subscriptions.add(App.sdb.createQuery("comment_headers", "select * from comment_headers where parent = ? limit 1", pid)
			.mapToOne(CommentHeader.MAPPER)
			.subscribeOn(Schedulers.newThread())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe {
				println("TTT: media: ${it.media} preview: ${it.best}")

				txtCommentHeaderTitle.text = it.title
				txtCommentHeaderAuthor.text = it.author
				txtCommentHeaderSelfText.text = it.selftext

				val test1 = "https://archive.org/download/Popeye_forPresident/Popeye_forPresident_512kb.mp4"
				val mp4Test = "https://g.redditmedia.com/a1_KqfCCYEwn4INgjOHIPjQ8BNxppS51Bx0GRKP1Plc.gif?fit=crop&crop=faces%2Centropy&arh=2&w=320&fm=mp4&mp4-fragmented=false&s=ccfd63d187741cf58b571428986c0c94"
				//vidView.bringToFront()
//				vidView.setOnPreparedListener {
//					vidView.start()
//				}
//				vidView.setVideoURI(Uri.parse(test1))

				vidView.setUp(mp4Test, "Test 1")
				Picasso.with(context).load(it.best)
					.fit()
					.centerCrop()
					.into(vidView.thumbImageView)

				println("SSS commentFragment ${it.best}")

//				imgCommentHeaderPreview.bringToFront()
//				Picasso.with(context).load(it.best)
//					.fit()
//					.centerCrop()
//					.into(imgCommentHeaderPreview)

//				if(it.media != null) {
//					webCommentHeaderMedia.apply {
//						setWebChromeClient(WebChromeClient())
//						settings.pluginState = WebSettings.PluginState.ON
//						settings.pluginState = WebSettings.PluginState.ON_DEMAND
//						setWebViewClient(WebViewClient())
//						settings.setJavaScriptEnabled(true)
//					}
//
//					webCommentHeaderMedia.bringToFront()
//					webCommentHeaderMedia.loadDataWithBaseURL("https://youtube.com", it.media, "text/html", "UTF-8", null)
//				}
//
//				Picasso.with(context).load(it.best)
//					.fit()
//					.centerCrop()
//					.into(imgCommentHeaderPreview)
			})
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		headerView = inflater.inflate(R.layout.comment_header, container, false)
		return inflater.inflate(R.layout.comments, container, false)
	}

	override fun onPause() {
		super.onPause()
		subscriptions.unsubscribe()
		vidView.removeAllViews()
	}
}

class ParallaxCommentAdapter(val context: Context, list: List<Comment>?) : ParallaxRecyclerAdapter<Comment>(list), Action1<List<Comment>> {
	val now = System.currentTimeMillis()

	override fun call(t: List<Comment>) {
		data.addAll(t)
		notifyDataSetChanged()
	}

	override fun getItemCountImpl(p0: ParallaxRecyclerAdapter<Comment>?): Int {
		return data.size
	}

	override fun onBindViewHolderImpl(vh: RecyclerView.ViewHolder, adapter: ParallaxRecyclerAdapter<Comment>, idx: Int) {
		val holder: CommentsViewHolder = vh as CommentsViewHolder
		holder.txtAuthor.text = data[idx].author
		holder.txtBody.loadMarkdown(data[idx].body)
		holder.txtCreated.text = DateUtils.getRelativeTimeSpanString(data[idx].created!! * 1000L, now, DateUtils.MINUTE_IN_MILLIS)
	}

	override fun onCreateViewHolderImpl(parent: ViewGroup, p1: ParallaxRecyclerAdapter<Comment>?, p2: Int): RecyclerView.ViewHolder {
		return CommentsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_comment, parent, false))
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