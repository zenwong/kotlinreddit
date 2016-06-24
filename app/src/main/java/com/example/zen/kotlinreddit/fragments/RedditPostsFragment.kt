package com.example.zen.kotlinreddit.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.zen.kotlinreddit.R
import com.example.zen.kotlinreddit.models.RedditPost
import kotlinx.android.synthetic.main.row_post.view.*

class RedditPostsFragment: Fragment() {
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return inflater.inflate(R.layout.front_page, container, false)
	}

	inner class PostsAdapters(val posts: List<RedditPost>): RecyclerView.Adapter<PostsAdapters.PostsViewHolder>() {
		override fun getItemCount(): Int {
			return posts.size
		}

		override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {

		}

		override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
			return PostsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_post, parent, false))
		}


		inner class PostsViewHolder(iv: View) : RecyclerView.ViewHolder(iv) {
			val card = iv.card
			val txtTitle = iv.txtPostTitle
			val txtSureddit = iv.txtSubreddit
			val txtComments = iv.txtComments

			init {
				card.useCompatPadding
			}
		}
	}
}