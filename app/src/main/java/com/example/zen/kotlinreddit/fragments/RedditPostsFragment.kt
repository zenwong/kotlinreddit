package com.example.zen.kotlinreddit.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.zen.kotlinreddit.App
import com.example.zen.kotlinreddit.DB
import com.example.zen.kotlinreddit.R
import com.example.zen.kotlinreddit.Reddit
import com.example.zen.kotlinreddit.models.CommentsRequest
import com.example.zen.kotlinreddit.models.RedditPost
import com.squareup.picasso.Picasso
import com.squareup.sqlbrite.BriteDatabase
import kotlinx.android.synthetic.main.front_page.*
import kotlinx.android.synthetic.main.row_post.view.*
import org.greenrobot.eventbus.EventBus
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Action1
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription
import java.util.*

class RedditPostsFragment : Fragment() {
	val select = "select * from posts order by preview desc, created desc"
	lateinit var subscriptions: CompositeSubscription
	lateinit var db: BriteDatabase

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		subscriptions = CompositeSubscription()
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		db = App.sqlBrite.wrapDatabaseHelper(DB(context), Schedulers.io())
		val adapter = PostsAdapter(context)
		rv.setHasFixedSize(true)
		rv.layoutManager = LinearLayoutManager(context)
		rv.adapter = adapter

		subscriptions.add(db.createQuery("posts", select)
			.mapToList(RedditPost.MAPPER)
			.subscribeOn(Schedulers.newThread())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(adapter))

		subscriptions.add(Observable.fromCallable { Reddit.getHotPosts() }
			.subscribeOn(Schedulers.newThread())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe())
	}

	override fun onDestroy() {
		super.onDestroy()
		subscriptions.unsubscribe()
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return inflater.inflate(R.layout.front_page, container, false)
	}
}

class PostsAdapter(val context: Context) : RecyclerView.Adapter<PostsAdapter.PostsViewHolder>(), Action1<List<RedditPost>> {
	val posts = ArrayList<RedditPost>()

	override fun call(list: List<RedditPost>) {
		posts.addAll(list)
		notifyDataSetChanged()
	}

	override fun getItemCount(): Int {
		return posts.size
	}

	override fun onBindViewHolder(holder: PostsViewHolder, idx: Int) {
		holder.txtTitle.text = posts[idx].title
		holder.txtComments.text = "Comments: ${posts[idx].comments}"
		holder.txtSubreddit.text = "Subreddit: ${posts[idx].subreddit}"
		Picasso.with(context).load(posts[idx].preview)
			.fit()
			.centerCrop()
			.into(holder.imgPreviw)
		//val url = "${Reddit.REDDIT_FRONT}${posts[idx].permalink}.json"
		//Reddit.getComments(url)
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
		return PostsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_post, parent, false))
	}

	inner class PostsViewHolder(iv: View) : RecyclerView.ViewHolder(iv) {
		val card = iv.card
		val txtTitle = iv.txtPostTitle
		val txtSubreddit = iv.txtSubreddit
		val txtComments = iv.txtComments
		val imgPreviw = iv.imgPreview

		init {
			card.useCompatPadding

			txtComments.setOnClickListener {
//				val nav = Navigation(COMMENTS)
//				nav.id = "t3_${posts[adapterPosition].rid}"
//				nav.pid = posts[adapterPosition]._id
//				EventBus.getDefault().post(nav)

			val url = "${Reddit.REDDIT_FRONT}${posts[adapterPosition].permalink}.json"
			val req = CommentsRequest(url, posts[adapterPosition]._id!!)
				EventBus.getDefault().post(req)
				println("comments adapterPosition: $adapterPosition, title: ${posts[adapterPosition].title}")
			}

			txtSubreddit.setOnClickListener {
				println("subreddit adapterPosition: $adapterPosition, title: ${posts[adapterPosition].title}")
			}
		}
	}
}