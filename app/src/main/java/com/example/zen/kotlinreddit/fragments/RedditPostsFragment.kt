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
import com.example.zen.kotlinreddit.models.Title
import com.example.zen.kotlinreddit.views.EndlessRecyclerViewScrollListener
import com.example.zen.kotlinreddit.views.PreCachingLayoutManager
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.front_page.*
import kotlinx.android.synthetic.main.main.*
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
	//val select = "select * from posts order by display desc"
	val select = "select * from posts"
	var subscriptions = CompositeSubscription()
	var adapter: PostsAdapter? = null

	override fun onAttach(context: Context) {
		super.onAttach(context)
		adapter = PostsAdapter(context)
	}

	override fun onResume() {
		super.onResume()
		activity.txtToolbarTitle.text = "Front Page"
		println("PostsFragment onResume")
		subscriptions = CompositeSubscription()
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		println("PostsFragment onViewCreated")
		//val layout = LinearLayoutManager(context)
		val layoutManager = PreCachingLayoutManager(activity)
		layoutManager.orientation = LinearLayoutManager.VERTICAL
		layoutManager.setExtraLayoutSpace(resources.displayMetrics.heightPixels)

		rv.setHasFixedSize(true)
		rv.layoutManager = layoutManager
		rv.adapter = adapter

		rv.addOnScrollListener(object : EndlessRecyclerViewScrollListener(layoutManager) {
			override fun onLoadMore(page: Int, totalItemsCount: Int) {
				println("LOADMORE: page: $page after: ${App.postAfter}")
				subscriptions.add(Observable.fromCallable { Reddit.getPostsAfter(10) }
					.subscribeOn(Schedulers.newThread())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe())
			}
		})

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

class PostsAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), Action1<List<Post>> {
	val now = System.currentTimeMillis()
	var posts: List<Post> = ArrayList()
	val TEXT_ONLY_POST = 0
	val IMAGE_POST = 1


	override fun call(list: List<Post>) {
		//posts.addAll(list)
		println("PostsAdapter call")
		posts = list
		notifyDataSetChanged()
	}

	override fun getItemCount(): Int {
		return posts.size
	}

	override fun getItemViewType(position: Int): Int {
		if(posts[position].preview != null) {
			return IMAGE_POST
		}
		return TEXT_ONLY_POST
	}

	override fun onBindViewHolder(holder: RecyclerView.ViewHolder, idx: Int) {
		when(holder.itemViewType) {
			IMAGE_POST -> {
				holder as ImagePostViewHolder
				holder.txtTitle.text = posts[idx].title
				holder.txtComments.text = "${posts[idx].comments} {fa-comments}"
				holder.txtSubreddit.text = "{fa-reddit} ${posts[idx].subreddit}"
				holder.txtScore.text = "${posts[idx].score} {fa-thumbs-up}"
				holder.txtCreated.text = DateUtils.getRelativeTimeSpanString(posts[idx].created!! * 1000L, now, DateUtils.MINUTE_IN_MILLIS)

				Picasso.with(context).load(posts[idx].display)
					.fit()
					.centerCrop()
					.into(holder.imgPreviw)
			}
			TEXT_ONLY_POST -> {
				holder as TextPostViewHolder
				holder.txtTitle.text = posts[idx].title
				holder.txtComments.text = "${posts[idx].comments} {fa-comments}"
				holder.txtSubreddit.text = "{fa-reddit} ${posts[idx].subreddit}"
				holder.txtScore.text = "${posts[idx].score} {fa-thumbs-up}"
				holder.txtCreated.text = DateUtils.getRelativeTimeSpanString(posts[idx].created!! * 1000L, now, DateUtils.MINUTE_IN_MILLIS)
			}
		}

	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
		if(IMAGE_POST == viewType) {
			return ImagePostViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_post, parent, false))
		}
		return TextPostViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_post_text_only, parent, false))
	}

	inner class ImagePostViewHolder(iv: View) : RecyclerView.ViewHolder(iv) {
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
				EventBus.getDefault().post(Title(posts[adapterPosition].title!!))
				val url = "${Reddit.REDDIT_FRONT}${posts[adapterPosition].permalink}.json"
				val req = CommentsRequest(url, posts[adapterPosition]._id!!, posts[adapterPosition].id!!)
				EventBus.getDefault().post(req)
			}

			imgPreviw.setOnClickListener {
				EventBus.getDefault().post(Title(posts[adapterPosition].title!!))
				val url = "${Reddit.REDDIT_FRONT}${posts[adapterPosition].permalink}.json"
				val req = CommentsRequest(url, posts[adapterPosition]._id!!, posts[adapterPosition].id!!)
				EventBus.getDefault().post(req)
			}

			txtSubreddit.setOnClickListener {
				println("subreddit adapterPosition: $adapterPosition, title: ${posts[adapterPosition].title}")
			}
		}
	}

	inner class TextPostViewHolder(iv: View) : RecyclerView.ViewHolder(iv) {
		val card = iv.card
		val txtTitle = iv.txtPostTitle
		val txtSubreddit = iv.txtSubreddit
		val txtCreated = iv.txtCreated
		val txtScore = iv.txtScore
		val txtComments = iv.txtComments

		init {
			card.useCompatPadding

			txtComments.setOnClickListener {
				EventBus.getDefault().post(Title(posts[adapterPosition].title!!))
				val url = "${Reddit.REDDIT_FRONT}${posts[adapterPosition].permalink}.json"
				val req = CommentsRequest(url, posts[adapterPosition]._id!!, posts[adapterPosition].id!!)
				EventBus.getDefault().post(req)
			}

			txtSubreddit.setOnClickListener {
				println("subreddit adapterPosition: $adapterPosition, title: ${posts[adapterPosition].title}")
			}
		}
	}
}