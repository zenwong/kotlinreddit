package com.example.zen.kotlinreddit.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.zen.kotlinreddit.R
import com.example.zen.kotlinreddit.TComment
import com.example.zen.kotlinreddit.fragments.CommentsViewHolder
import rx.functions.Action1
import java.util.*

class CommentAdapter(val context: Context) : RecyclerView.Adapter<CommentsViewHolder>(), Action1<List<TComment>> {
	val items = ArrayList<TComment>()
	val now = System.currentTimeMillis()

	override fun call(t: List<TComment>) {
		items.addAll(t)
		notifyDataSetChanged()
	}

	override fun getItemCount(): Int {
		return items.size
	}

	override fun onBindViewHolder(holder: CommentsViewHolder, idx: Int) {
		holder.txtAuthor.text = "${items[idx].author}  ${items[idx].score} pts"
		holder.txtBody.loadMarkdown(items[idx].body)
		holder.txtCreated.text = DateUtils.getRelativeTimeSpanString(items[idx].created * 1000L, now, DateUtils.MINUTE_IN_MILLIS)
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder? {
		return CommentsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_comment, parent, false))
	}

}