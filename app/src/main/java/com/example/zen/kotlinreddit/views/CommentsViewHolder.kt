package com.example.zen.kotlinreddit.views

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.row_comment.view.*

class CommentsViewHolder(iv: View) : RecyclerView.ViewHolder(iv) {
	val txtAuthor = iv.txtAuthor
	val txtBody = iv.txtBody
	val txtCreated = iv.txtCommentCreated
}