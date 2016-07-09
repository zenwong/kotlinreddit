package com.example.zen.kotlinreddit

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.zen.kotlinreddit.fragments.CommentsAdapter
import com.example.zen.kotlinreddit.models.Comment
import kotlinx.android.synthetic.main.comments.*
import kotlinx.android.synthetic.main.main.*
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription

class CommentsActivity : AppCompatActivity() {
	var subscriptions = CompositeSubscription()
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

		val url = intent.dataString
		val permalink = Uri.parse(url).path
		val link = "${Reddit.REDDIT_FRONT}$permalink.json"
		val parent = Uri.parse(url).pathSegments[3]
		println("ZXZ $link")

		subscriptions.add(Observable.fromCallable { Reddit.parseComments(link, parent) }
			.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe())

		subscriptions.add(App.sdb.createQuery(table, select, parent)
			.mapToList(Comment.MAPPER)
			.subscribeOn(Schedulers.newThread())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(adapter))
	}

	override fun onStop() {
		subscriptions.clear()
		super.onStop()
	}
}