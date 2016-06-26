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
import com.squareup.sqlbrite.BriteDatabase
import kotlinx.android.synthetic.main.front_page.*
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Action1
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription
import java.util.*

class CommentsFragment : Fragment() {
	val subscriptions = CompositeSubscription()
	lateinit var db : BriteDatabase
	val table = "comments"
	val select = "select * from comments where pid = ?"
	lateinit var adapter: CommentsAdapter
	val layout = LinearLayoutManager(context)
	var pid : Int = 0

	companion object {
		fun newInstance(postId: Int) : CommentsFragment {
			val frag = CommentsFragment()
			val bundle = Bundle()
			bundle.putInt("pid", postId)
			frag.arguments = bundle
			return frag
		}
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		db = App.sqlBrite.wrapDatabaseHelper(DB(context), Schedulers.io())
		//savedInstanceState?.let {	pid = it.getInt("pid") }
		pid = arguments.getInt("pid")
		println("pid: $pid")

		adapter = CommentsAdapter(context)
		rv.setHasFixedSize(true)
		rv.layoutManager = layout
		rv.adapter = adapter

		db.createQuery(table, select, pid.toString())
			.mapToList(Comment.MAPPER)
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe({ comments ->
				comments.forEach {
					println("pid: ${it.pid}, _id: ${it.id}, author: ${it.author}\nbody: ${it.body}\n")
				}
			})

		subscriptions.add(db.createQuery(table, select, pid.toString())
			.mapToList(Comment.MAPPER)
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(adapter))
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return inflater.inflate(R.layout.front_page, container, false)
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