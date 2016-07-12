package com.example.zen.kotlinreddit.views

import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.zen.kotlinreddit.TPost
import com.example.zen.kotlinreddit.models.CommentsRequest
import com.example.zen.kotlinreddit.models.Post
import com.example.zen.kotlinreddit.models.Title
import kotlinx.android.synthetic.main.row_post.view.*
import org.greenrobot.eventbus.EventBus
import java.util.*

class ImagePostViewHolder(val posts: List<Post>, iv: View) : RecyclerView.ViewHolder(iv) {
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
			EventBus.getDefault().post(CommentsRequest(posts[adapterPosition].permalink!!, 0, posts[adapterPosition].id!!))
		}

		imgPreviw.setOnClickListener {
			EventBus.getDefault().post(Title(posts[adapterPosition].title!!))
			EventBus.getDefault().post(CommentsRequest(posts[adapterPosition].permalink!!, 0, posts[adapterPosition].id!!))
		}

		txtSubreddit.setOnClickListener {
			println("subreddit adapterPosition: $adapterPosition, title: ${posts[adapterPosition].title}")
		}
	}
}

class SnappyImagePostViewHolder(val posts: ArrayList<TPost>, iv: View) : RecyclerView.ViewHolder(iv) {
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
			EventBus.getDefault().post(CommentsRequest(posts[adapterPosition].permalink!!, 0, posts[adapterPosition].id!!))
		}

		imgPreviw.setOnClickListener {
			EventBus.getDefault().post(Title(posts[adapterPosition].title!!))
			EventBus.getDefault().post(CommentsRequest(posts[adapterPosition].permalink!!, 0, posts[adapterPosition].id!!))
		}

		txtSubreddit.setOnClickListener {
			println("subreddit adapterPosition: $adapterPosition, title: ${posts[adapterPosition].title}")
		}
	}
}