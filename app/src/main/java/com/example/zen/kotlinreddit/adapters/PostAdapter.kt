package com.example.zen.kotlinreddit.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.zen.kotlinreddit.CommentsActivity
import com.example.zen.kotlinreddit.R
import com.example.zen.kotlinreddit.Reddit
import com.example.zen.kotlinreddit.models.CommentsRequest
import com.example.zen.kotlinreddit.models.Post
import com.example.zen.kotlinreddit.models.Title
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_post.view.*
import org.greenrobot.eventbus.EventBus
import rx.functions.Action1
import rx.subscriptions.CompositeSubscription
import java.util.*

class PostsAdapter(val context: Context, val subscriptions: CompositeSubscription) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), Action1<List<Post>> {
	val now = System.currentTimeMillis()
	var posts: List<Post> = ArrayList()
	val TEXT_ONLY_POST = 0
	val IMAGE_POST = 1

	override fun call(list: List<Post>) {
		//posts.addAll(list)
		posts = list
		println("PostsAdapter call ${posts.size}")
		notifyDataSetChanged()
	}

	override fun getItemCount(): Int {
		return posts.size
	}

	override fun getItemViewType(position: Int): Int {
		if (posts[position].preview != null) {
			return IMAGE_POST
		}
		return TEXT_ONLY_POST
	}

	override fun onBindViewHolder(holder: RecyclerView.ViewHolder, idx: Int) {
		when (holder.itemViewType) {
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

		// optimization for pre caching top 10 comments so user sees comments immediately
		// optional check for wifi connection or user setting before getting comments
		// subscriptions.add(Observable.fromCallable { Reddit.parseComments("${Reddit.REDDIT_FRONT}${posts[idx].permalink}.json", posts[idx].id!!) }.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe())
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
		if (IMAGE_POST == viewType) {
			return ImagePostViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_post, parent, false))
		}
		return TextPostViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_post_text_only, parent, false))
	}

	fun handleTxtComments(adapterPosition: Int) {
		val url = "${Reddit.REDDIT_FRONT}${posts[adapterPosition].permalink}.json"
		val req = CommentsRequest(url, posts[adapterPosition]._id!!, posts[adapterPosition].id!!)
		EventBus.getDefault().post(Title(posts[adapterPosition].title!!))
		EventBus.getDefault().post(req)
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
				handleTxtComments(adapterPosition)
//				EventBus.getDefault().post(Title(posts[adapterPosition].title!!))
//				val url = "${Reddit.REDDIT_FRONT}${posts[adapterPosition].permalink}.json"
//				val req = CommentsRequest(url, posts[adapterPosition]._id!!, posts[adapterPosition].id!!)
//				EventBus.getDefault().post(req)
			}

			imgPreviw.setOnClickListener {
				EventBus.getDefault().post(Title(posts[adapterPosition].title!!))
				val url = "${Reddit.REDDIT_FRONT}${posts[adapterPosition].permalink}.json"
				val req = CommentsRequest(url, posts[adapterPosition]._id!!, posts[adapterPosition].id!!)
				//EventBus.getDefault().post(req)
				val intent = Intent(context, CommentsActivity::class.java)
				intent.putExtra("url", req.url)
				intent.putExtra("parent", req.parent)
				context.startActivity(intent)

				//navigate<CommentsActivity>(req)
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
				handleTxtComments(adapterPosition)
//				EventBus.getDefault().post(Title(posts[adapterPosition].title!!))
//				val url = "${Reddit.REDDIT_FRONT}${posts[adapterPosition].permalink}.json"
//				val req = CommentsRequest(url, posts[adapterPosition]._id!!, posts[adapterPosition].id!!)
//				EventBus.getDefault().post(req)
			}

			txtSubreddit.setOnClickListener {
				println("subreddit adapterPosition: $adapterPosition, title: ${posts[adapterPosition].title}")
			}
		}
	}
}
