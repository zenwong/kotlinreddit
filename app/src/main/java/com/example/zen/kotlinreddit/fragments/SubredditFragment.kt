package com.example.zen.kotlinreddit.fragments

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import com.example.zen.kotlinreddit.*
import com.example.zen.kotlinreddit.adapters.PostsAdapter
import com.example.zen.kotlinreddit.views.EndlessRecyclerViewScrollListener
import kotlinx.android.synthetic.main.recycler.*
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class SubredditFragment : BaseFragment() {
	var currentSort = SORT_HOT
	var subreddit: String = ""
	override val layout = R.layout.front_page
	lateinit var adapter: PostsAdapter
	val table = TPost().getTableName()

	val clearSub = Observable.fromCallable {
		App.sdb.delete(table, null)
	}

	val toggleSort = hashMapOf(SORT_HOT to true, SORT_NEW to true, SORT_PREVIEW to true, SORT_COMMENTS to true, SORT_SCORE to true, SORT_SUBREDDIT to true)

	companion object {
		const val TAG = "SubredditFragment"
		fun newInstance(subreddit: String): SubredditFragment {
			val frag = SubredditFragment()
			val bundle = Bundle()
			bundle.putString("subreddit", subreddit)
			frag.arguments = bundle
			return frag
		}
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setHasOptionsMenu(true)
		retainInstance = true
		subreddit = arguments.getString("subreddit")
		setTitle(subreddit)

		adapter = PostsAdapter(context)

		subs.add(Observable.fromCallable { Reddit.getSubredditPosts(subreddit) }.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe())
		subs.add(App.sdb.createQuery("$table", "select * from $table where subreddit = ?", subreddit).mapToList(TPost.MAPPER).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(adapter))
	}

	override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
		//setTitleBaseOnSort()

		if (savedInstanceState != null) {
			val sort = savedInstanceState.getInt("currentSort")
			val order = savedInstanceState.getBoolean("currentOrder").not()
			adapter.sortBy(sort, order)
		}

		val layoutManager = LinearLayoutManager(context)
		layoutManager.orientation = LinearLayoutManager.VERTICAL

		// disabling this makes scrolling seem smoother?
		//rv.setHasFixedSize(true)
		rv.layoutManager = layoutManager
		rv.adapter = adapter

		rv.addOnScrollListener(object : EndlessRecyclerViewScrollListener(layoutManager) {
			override fun onLoadMore(page: Int, totalItemsCount: Int) {
				subs.add(Observable.fromCallable { Reddit.getSubredditPostsAfter(subreddit, 20) }
					.subscribeOn(Schedulers.newThread())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe())
			}
		})
	}

	override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater) {
		inflater.inflate(R.menu.posts, menu)
	}

	fun setTitleBaseOnSort() {
		when (currentSort) {
			SORT_HOT -> setTitle("Hot")
			SORT_NEW -> setTitle("New")
			SORT_PREVIEW -> {
				if (toggleSort[currentSort] == true) setTitle("Preview Descending")
				else setTitle("Preview Ascending")
			}
			SORT_SUBREDDIT -> {
				if (toggleSort[currentSort] == true) setTitle("Subreddit Descending")
				else setTitle("Subreddit Ascending")
			}
			SORT_SCORE -> {
				if (toggleSort[currentSort] == true) setTitle("Score Descending")
				else setTitle("Score Ascending")
			}
			SORT_COMMENTS -> {
				if (toggleSort[currentSort] == true) setTitle("Comments Descending")
				else setTitle("Comments Ascending")
			}
			else -> setTitle("Hot")
		}
	}
}