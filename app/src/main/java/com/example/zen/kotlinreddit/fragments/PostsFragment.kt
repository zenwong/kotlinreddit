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
import com.example.zen.kotlinreddit.TPost
import com.example.zen.kotlinreddit.adapters.PostsAdapter
import com.example.zen.kotlinreddit.views.EndlessRecyclerViewScrollListener
import com.example.zen.kotlinreddit.views.PreCachingLayoutManager
import kotlinx.android.synthetic.main.recycler.*
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class PostsFragment : BaseFragment() {
	var currentSort = PostsAdapter.SORT_HOT
	lateinit var adapter : PostsAdapter
	var subreddit: String? = null
	override val layout = R.layout.front_page

	companion object {
		fun forSubreddit(subreddit: String): PostsFragment {
			val frag = PostsFragment()
			val bundle = Bundle()
			bundle.putString("subreddit", subreddit)
			frag.arguments = bundle
			return frag
		}
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		if (savedInstanceState != null) {
			//Restore the fragment's state here
			subreddit = savedInstanceState.getString("subreddit")
		}
	}

	override fun onSaveInstanceState(outState: Bundle?) {
		super.onSaveInstanceState(outState)
		//Save the fragment's state here
		outState?.putString("subreddit", subreddit)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setHasOptionsMenu(true)
		adapter = PostsAdapter(context, currentSort)

		val query: Observable<List<TPost>>
		if (arguments == null) {
			//setTitle("Hot")
			query = App.sdb.createQuery("posts", "select * from TPosts").mapToList(TPost.MAPPER)
			subs.add(Observable.fromCallable { Reddit.getHotPosts() }.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe())
		} else {
			subreddit = arguments.getString("subreddit")
			//setTitle(subreddit!!)
			query = App.sdb.createQuery("posts", "select * from TPosts where subreddit = ?", subreddit).mapToList(TPost.MAPPER)
			subs.add(Observable.fromCallable { Reddit.getSubredditPosts(subreddit!!) }.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe())
		}
		subs.add(query.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(adapter))

	}

	override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		val layoutManager = PreCachingLayoutManager(context)
		layoutManager.orientation = LinearLayoutManager.VERTICAL
		layoutManager.setExtraLayoutSpace(resources.displayMetrics.heightPixels)

		rv.setHasFixedSize(true)
		rv.layoutManager = layoutManager
		rv.adapter = adapter

		rv.addOnScrollListener(object : EndlessRecyclerViewScrollListener(layoutManager) {
			override fun onLoadMore(page: Int, totalItemsCount: Int) {
				subs.add(Observable.fromCallable { Reddit.getPostsAfter(10) }
					.subscribeOn(Schedulers.newThread())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe())
			}
		})
	}

	override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater) {
		inflater.inflate(R.menu.posts, menu)
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		when (item.itemId) {
			R.id.action_hot -> {

				return true
			}
			R.id.action_new -> {

				return true
			}
			R.id.action_subreddit -> {
				currentSort = PostsAdapter.SORT_SUBREDDIT
				adapter.sortBy(PostsAdapter.SORT_SUBREDDIT)
				setTitleBaseOnSort()
				return true
			}
			R.id.action_comments -> {
				currentSort = PostsAdapter.SORT_COMMENTS
				adapter.sortBy(PostsAdapter.SORT_COMMENTS)
				setTitleBaseOnSort()
				return true
			}
			R.id.action_score -> {
				currentSort = PostsAdapter.SORT_SCORE
				adapter.sortBy(PostsAdapter.SORT_SCORE)
				setTitleBaseOnSort()
				return true
			}
			R.id.action_preview -> {
				currentSort = PostsAdapter.SORT_PREVIEW
				adapter.sortBy(PostsAdapter.SORT_PREVIEW)
				setTitleBaseOnSort()
				return true
			}
		}

		return super.onOptionsItemSelected(item)
	}

	fun setTitleBaseOnSort() {
		when(currentSort) {
			PostsAdapter.SORT_PREVIEW -> setTitle("Preview")
			PostsAdapter.SORT_SUBREDDIT -> setTitle("Subreddit")
			PostsAdapter.SORT_SCORE -> setTitle("Score")
			PostsAdapter.SORT_COMMENTS -> setTitle("Comments")
			PostsAdapter.SORT_CREATED -> setTitle("Created")
			else -> setTitle("Hot")
		}
	}

}