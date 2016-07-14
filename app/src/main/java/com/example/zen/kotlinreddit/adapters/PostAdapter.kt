package com.example.zen.kotlinreddit.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.zen.kotlinreddit.*
import com.example.zen.kotlinreddit.models.CommentsRequest
import com.example.zen.kotlinreddit.models.Title
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_post.view.*
import org.greenrobot.eventbus.EventBus
import rx.functions.Action1
import java.util.*

class PostsAdapter(val context: Context, val sort: Int = SORT_CREATED) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), Action1<List<TPost>> {
	val now = System.currentTimeMillis()
	var posts = ArrayList<TPost>()
	val TEXT_ONLY_POST = 0
	val IMAGE_POST = 1

	fun sortBy(sortColumn: Int, order: Boolean?) {
		if(order == true) {
			when(sortColumn) {
				SORT_TITLE -> posts.sortByDescending { it.title }
				SORT_PREVIEW -> posts.sortByDescending { it.preview }
				SORT_SUBREDDIT -> posts.sortByDescending { it.subreddit }
				SORT_CREATED -> posts.sortByDescending { it.created }
				SORT_COMMENTS -> posts.sortByDescending { it.comments }
				SORT_SCORE -> posts.sortByDescending { it.score }
			}
		} else {
			when(sortColumn) {
				SORT_TITLE -> posts.sortBy { it.title }
				SORT_PREVIEW -> posts.sortBy { it.preview }
				SORT_SUBREDDIT -> posts.sortBy { it.subreddit }
				SORT_CREATED -> posts.sortBy { it.created }
				SORT_COMMENTS -> posts.sortBy { it.comments }
				SORT_SCORE -> posts.sortBy { it.score }
			}
		}
		notifyDataSetChanged()
	}

	override fun call(list: List<TPost>) {
		posts = list as ArrayList<TPost>
		println("PostsAdapter Call ${posts.size}")
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

				Picasso.with(context).load(posts[idx].preview)
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
		if (IMAGE_POST == viewType) {
			return ImagePostViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_post, parent, false))
		}
		return TextPostViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_post_text_only, parent, false))
	}

	fun handleTxtComments(adapterPosition: Int) {
		val url = "${Reddit.REDDIT_FRONT}${posts[adapterPosition].permalink}.json"
		val req = CommentsRequest(url, 0, posts[adapterPosition].id!!)
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
			}

			imgPreviw.setOnClickListener {
				handleTxtComments(adapterPosition)
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
			}

			txtSubreddit.setOnClickListener {
				println("subreddit adapterPosition: $adapterPosition, title: ${posts[adapterPosition].title}")
			}
		}
	}
}
