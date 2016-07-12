package com.example.zen.kotlinreddit.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.zen.kotlinreddit.R
import com.example.zen.kotlinreddit.TPost
import com.example.zen.kotlinreddit.views.SnappyImagePostViewHolder
import com.example.zen.kotlinreddit.views.TextPostViewHolder
import com.squareup.picasso.Picasso
import rx.functions.Action1
import java.util.*

class PostSnappyAdapter(val context: Context, val sort: Int = SORT_CREATED): RecyclerView.Adapter<RecyclerView.ViewHolder>(), Action1<ArrayList<TPost>> {
	val now = System.currentTimeMillis()
	val posts = ArrayList<TPost>()

	companion object {
		const val SORT_TITLE = 0
		const val SORT_PREVIEW = 1
		const val SORT_SUBREDDIT = 2
		const val SORT_CREATED = 3
		const val SORT_COMMENTS = 4
		const val SORT_SCORE = 5
		const val IMG_POST = 111
		const val TXT_POST = 222
	}

	override fun call(t: ArrayList<TPost>) {
		posts.clear()
		posts.addAll(t)
		sortBy(sort)
		notifyDataSetChanged()
	}

	fun sortBy(sortColumn: Int) {
		when(sortColumn) {
			SORT_TITLE -> posts.sortByDescending { it.title }
			SORT_PREVIEW -> posts.sortByDescending { it.preview }
			SORT_SUBREDDIT -> posts.sortByDescending { it.subreddit }
			SORT_CREATED -> posts.sortByDescending { it.created }
			SORT_COMMENTS -> posts.sortByDescending { it.comments }
			SORT_SCORE -> posts.sortByDescending { it.score }
		}
		notifyDataSetChanged()
	}

	override fun getItemCount(): Int {
		return posts.size
	}

	override fun onBindViewHolder(holder: RecyclerView.ViewHolder, idx: Int) {
		when (holder.itemViewType) {
			IMG_POST -> {
				holder as SnappyImagePostViewHolder
				holder.txtTitle.text = posts[idx].title
				holder.txtComments.text = "${posts[idx].comments} {fa-comments}"
				holder.txtSubreddit.text = "{fa-reddit} ${posts[idx].subreddit}"
				holder.txtScore.text = "${posts[idx].score} {fa-thumbs-up}"
				holder.txtCreated.text = DateUtils.getRelativeTimeSpanString(posts[idx].created * 1000L, now, DateUtils.MINUTE_IN_MILLIS)

				println("SNAPPY : preview : ${posts[idx].preview}")
				Picasso.with(context).load(posts[idx].preview)
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
				holder.txtCreated.text = DateUtils.getRelativeTimeSpanString(posts[idx].created * 1000L, now, DateUtils.MINUTE_IN_MILLIS)
			}
		}

	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
		if (IMG_POST == viewType) {
			return SnappyImagePostViewHolder(posts, LayoutInflater.from(parent.context).inflate(R.layout.row_post, parent, false))
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