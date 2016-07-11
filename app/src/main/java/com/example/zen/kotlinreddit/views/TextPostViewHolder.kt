package com.example.zen.kotlinreddit.views

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.row_post.view.*

class TextPostViewHolder(iv: View) : RecyclerView.ViewHolder(iv) {
	val card = iv.card
	val txtTitle = iv.txtPostTitle
	val txtSubreddit = iv.txtSubreddit
	val txtCreated = iv.txtCreated
	val txtScore = iv.txtScore
	val txtComments = iv.txtComments

	init {
		card.useCompatPadding

		txtComments.setOnClickListener {
		}

	}
}