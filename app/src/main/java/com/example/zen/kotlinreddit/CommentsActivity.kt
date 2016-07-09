package com.example.zen.kotlinreddit

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.zen.kotlinreddit.fragments.CommentsAdapter
import com.example.zen.kotlinreddit.models.Comment
import com.example.zen.kotlinreddit.models.CommentHeader
import kotlinx.android.synthetic.main.comments.*
import kotlinx.android.synthetic.main.main.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription

class CommentsActivity : AppCompatActivity() {
	val subscriptions = CompositeSubscription()
	val table = "comments"
	val select = "select * from comments where parent = ?"
	val layout = LinearLayoutManager(this)
	var pid: String? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.comments_new)
		setSupportActionBar(toolbar)
		pid = intent.getStringExtra("parent")
		val adapter = CommentsAdapter(this)
		list.setHasFixedSize(true)
		list.layoutManager = layout
		list.adapter = adapter

		subscriptions.add(App.sdb.createQuery(table, select, pid.toString())
			.mapToList(Comment.MAPPER)
			.subscribeOn(Schedulers.newThread())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(adapter))

		subscriptions.add(App.sdb.createQuery("comment_headers", "select * from comment_headers where parent = ? limit 1", pid)
			.mapToOne(CommentHeader.MAPPER)
			.subscribeOn(Schedulers.newThread())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe {
				println("TTT: media: ${it.media} preview: ${it.best}")

//				txtCommentHeaderTitle.text = it.title
//				txtCommentHeaderAuthor.text = it.author
//				txtCommentHeaderSelfText.text = it.selftext
			})
	}
}