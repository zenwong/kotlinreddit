package com.example.zen.kotlinreddit.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.zen.kotlinreddit.App
import com.example.zen.kotlinreddit.R
import com.example.zen.kotlinreddit.Reddit
import com.example.zen.kotlinreddit.models.CommentsRequest
import com.example.zen.kotlinreddit.models.Post
import com.squareup.picasso.Picasso
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
	//val select = "select * from posts order by display desc, created desc"
	val select = "select * from posts order by display desc"
	var subscriptions = CompositeSubscription()
	var adapter: PostsAdapter? = null

	override fun onAttach(context: Context) {
		super.onAttach(context)
		adapter = PostsAdapter(context)
	}

	override fun onResume() {
		super.onResume()
		println("PostsFragment onResume")
		subscriptions = CompositeSubscription()
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		println("PostsFragment onViewCreated")
		rv.setHasFixedSize(true)
		rv.layoutManager = LinearLayoutManager(context)
		rv.adapter = adapter

		subscriptions.add(Observable.fromCallable { Reddit.getHotPosts() }
			.subscribeOn(Schedulers.newThread())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe())

		subscriptions.add(App.sdb.createQuery("posts", select)
			.mapToList(Post.MAPPER)
			.subscribeOn(Schedulers.newThread())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(adapter))
	}

	override fun onPause() {
		super.onPause()
		subscriptions.clear()
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		return inflater.inflate(R.layout.front_page, container, false)
	}
}

class PostsAdapter(val context: Context) : RecyclerView.Adapter<PostsAdapter.PostsViewHolder>(), Action1<List<Post>> {
	val now = System.currentTimeMillis()
	var posts: List<Post> = ArrayList()

	override fun call(list: List<Post>) {
		//posts.addAll(list)
		println("PostsAdapter call")
		posts = list
		notifyDataSetChanged()
	}

	override fun getItemCount(): Int {
		return posts.size
	}

	override fun onBindViewHolder(holder: PostsViewHolder, idx: Int) {
		holder.txtTitle.text = posts[idx].title
		holder.txtComments.text = "${posts[idx].comments} {fa-comments}"
		holder.txtSubreddit.text = "{fa-reddit} ${posts[idx].subreddit}"
		holder.txtScore.text = "${posts[idx].score} {fa-thumbs-up}"

		//println("PostAdapter onBindViewHolder created: ${posts[idx].created} now: $now")
		//println("PostAdapter display: ${posts[idx].display}")

		holder.txtCreated.text = DateUtils.getRelativeTimeSpanString(posts[idx].created!! * 1000L,	now, DateUtils.MINUTE_IN_MILLIS)
		//holder.txtCreated.text = DateUtils.getRelativeTimeSpanString(posts[idx].created!!, now, DateUtils.HOUR_IN_MILLIS)

		Picasso.with(context).load(posts[idx].display)
			.fit()
			.centerCrop()
			.into(holder.imgPreviw)
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
		return PostsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_post, parent, false))
	}

	inner class PostsViewHolder(iv: View) : RecyclerView.ViewHolder(iv) {
		val card = iv.card
		val txtTitle = iv.txtPostTitle
		val txtSubreddit = iv.txtSubreddit
		val txtCreated = iv.txtCreated
		val txtScore = iv.txtScore
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
			println(url)
			val req = CommentsRequest(url, posts[adapterPosition]._id!!, posts[adapterPosition].id!!)
				EventBus.getDefault().post(req)
				println("comments adapterPosition: $adapterPosition, title: ${posts[adapterPosition].title}")
			}

			txtSubreddit.setOnClickListener {
				println("subreddit adapterPosition: $adapterPosition, title: ${posts[adapterPosition].title}")
			}
		}
	}
}