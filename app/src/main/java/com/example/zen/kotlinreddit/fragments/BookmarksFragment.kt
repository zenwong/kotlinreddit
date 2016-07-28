package com.example.zen.kotlinreddit.fragments

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import com.example.zen.kotlinreddit.App
import com.example.zen.kotlinreddit.R
import com.example.zen.kotlinreddit.THeader
import com.example.zen.kotlinreddit.adapters.BookmarkAdapter
import kotlinx.android.synthetic.main.recycler.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class BookmarksFragment : BaseFragment() {
	val table = THeader().getTableName()
	override val layout = R.layout.front_page
	lateinit var adapter: BookmarkAdapter

	companion object {
		const val TAG = "BookmarksFragment"
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setHasOptionsMenu(true)
		retainInstance = true
		setTitle("Bookmarks")

		adapter = BookmarkAdapter(context)

		subs.add(App.sdb.createQuery("$table", "select * from $table where bookmarked = 1").mapToList(THeader.MAPPER).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(adapter))
	}

	override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
		val layoutManager = LinearLayoutManager(context)
		layoutManager.orientation = LinearLayoutManager.VERTICAL
		rv.layoutManager = layoutManager
		rv.adapter = adapter
	}

	override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater) {
		inflater.inflate(R.menu.posts, menu)
	}

}