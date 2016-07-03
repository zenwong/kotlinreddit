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
	var subscriptions = CompositeSubscription()
	lateinit var db: BriteDatabase
	//lateinit var adapter: PostsAdapter

	override fun onResume() {
		super.onResume()
		subscriptions = CompositeSubscription()

		subscriptions.add(Observable.fromCallable { Reddit.getHotPosts() }
			.subscribeOn(Schedulers.newThread())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe())

//		val sqlite = db.createQuery("posts", select).mapToList(RedditPost.MAPPER)
//		val network = Observable.fromCallable { Reddit.getHotPosts() }
//		Observable.concat(sqlite, network).first()
	}

	override fun onAttach(context: Context) {
		super.onAttach(context)
		//adapter = PostsAdapter(context)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		val adapter = PostsAdapter(context)
		db = App.sqlBrite.wrapDatabaseHelper(DB(context), Schedulers.io())
		rv.setHasFixedSize(true)
		rv.layoutManager = LinearLayoutManager(context)
		rv.adapter = adapter

		subscriptions.add(db.createQuery("posts", select)
			.mapToList(RedditPost.MAPPER)
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

class PostsAdapter(val context: Context) : RecyclerView.Adapter<PostsAdapter.PostsViewHolder>(), Action1<List<RedditPost>> {
	val now = System.currentTimeMillis()
	var posts: List<RedditPost> = ArrayList()

	override fun call(list: List<RedditPost>) {
		//posts.addAll(list)
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
		holder.txtCreated.text = DateUtils.getRelativeTimeSpanString(posts[idx].created!!,	now, DateUtils.DAY_IN_MILLIS, DateUtils.FORMAT_ABBREV_RELATIVE)
		//holder.txtCreated.text = DateUtils.getRelativeTimeSpanString(posts[idx].created!!, now, DateUtils.HOUR_IN_MILLIS)

		Picasso.with(context).load(posts[idx].preview)
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