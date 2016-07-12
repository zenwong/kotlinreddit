package com.example.zen.kotlinreddit.fragments

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import com.example.zen.kotlinreddit.App
import com.example.zen.kotlinreddit.R
import com.example.zen.kotlinreddit.Reddit
import com.example.zen.kotlinreddit.models.Comment
import kotlinx.android.synthetic.main.recycler.*
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class TestCommentsFragment : BaseFragment() {
	val lm = LinearLayoutManager(context)
	override val layout = R.layout.comments
	var parent = ""
	var url = ""

	companion object {
		fun newInstance(url: String, parent: String): TestCommentsFragment {
			val frag = TestCommentsFragment()
			val bundle = Bundle()
			bundle.putString("parent", parent)
			bundle.putString("url", url)
			frag.arguments = bundle
			return frag
		}
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		setHasOptionsMenu(true)
		url = arguments.getString("url")
		parent = arguments.getString("parent")

		val adapter = CommentsAdapter(context)

		rv.setHasFixedSize(true)
		rv.layoutManager = lm
		rv.adapter = adapter

		subs.add(Observable.fromCallable { Reddit.parseComments(url, parent) }
			.subscribeOn(Schedulers.newThread())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe())

		subs.add(App.sdb.createQuery("comments", "select * from comments where parent = ?", parent)
			.mapToList(Comment.MAPPER)
			.subscribeOn(Schedulers.newThread())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(adapter))
	}

	override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater) {
		inflater.inflate(R.menu.comments, menu)
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		when (item.itemId) {
			R.id.action_best -> {

				return true
			}
			R.id.action_new -> {

				return true
			}
			R.id.action_author -> {
				return true
			}
			R.id.action_controversial -> {
				return true
			}
		}

		return super.onOptionsItemSelected(item)
	}
}