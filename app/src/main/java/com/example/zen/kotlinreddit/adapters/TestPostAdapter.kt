package com.example.zen.kotlinreddit.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.zen.kotlinreddit.R
import com.example.zen.kotlinreddit.models.Post
import com.example.zen.kotlinreddit.views.ImagePostViewHolder
import com.example.zen.kotlinreddit.views.TextPostViewHolder
import com.squareup.picasso.Picasso
import rx.functions.Action1
import java.util.*

class TestPostAdapter(val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>(), Action1<List<Post>> {
	val now = System.currentTimeMillis()
	var posts: List<Post> = ArrayList()
	val TXT_POST = 0
	val IMG_POST = 1
	val VID_POST = 2

	override fun call(list: List<Post>) {
		posts = list
		notifyDataSetChanged()
	}

	override fun getItemCount(): Int {
		return posts.size
	}

	override fun onBindViewHolder(holder: RecyclerView.ViewHolder, idx: Int) {
		when (holder.itemViewType) {
			IMG_POST -> {
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
			TXT_POST -> {
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
		if (IMG_POST == viewType) {
			return ImagePostViewHolder(posts, LayoutInflater.from(parent.context).inflate(R.layout.row_post, parent, false))
		}
		return TextPostViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_post_text_only, parent, false))
	}

	override fun getItemViewType(idx: Int): Int {
		if (posts[idx].preview != null) {
			return IMG_POST
		}

		return TXT_POST
	}
}