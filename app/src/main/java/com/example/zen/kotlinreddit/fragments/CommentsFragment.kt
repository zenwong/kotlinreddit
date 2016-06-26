package com.example.zen.kotlinreddit.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.zen.kotlinreddit.App
import com.example.zen.kotlinreddit.DB
import com.example.zen.kotlinreddit.R
import com.example.zen.kotlinreddit.models.Comment
import kotlinx.android.synthetic.main.front_page.*
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Action1
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription
import java.util.*

class CommentsFragment : Fragment() {
	val subscriptions = CompositeSubscription()
	val db = App.sqlBrite.wrapDatabaseHelper(DB(context), Schedulers.io())
	val table = "comments"
	val select = "select * from comments where pid = ?"
	val adapter = CommentsAdapter(context)
	val layout = LinearLayoutManager(context)

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		rv.setHasFixedSize(true)
		rv.layoutManager = layout
		rv.adapter = adapter

		subscriptions.add(db.createQuery(table, select)
			.mapToList(Comment.MAPPER)
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(adapter))
	}

	override fun onPause() {
		super.onPause()
		subscriptions.unsubscribe()
	}
}

class CommentsAdapter(val context: Context): RecyclerView.Adapter<CommentsViewHolder>(), Action1<List<Comment>> {
	val items = ArrayList<Comment>()

	override fun call(t: List<Comment>) {
		items.addAll(t)
		notifyDataSetChanged()
	}

	override fun getItemCount(): Int {
		return items.size
	}

	override fun onBindViewHolder(holder: CommentsViewHolder?, position: Int) {

	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder? {
		return CommentsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_comment, parent, false))
	}

}

class CommentsViewHolder(iv: View): RecyclerView.ViewHolder(iv) {

}