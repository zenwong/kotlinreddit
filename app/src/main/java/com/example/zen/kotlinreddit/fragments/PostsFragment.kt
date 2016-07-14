package com.example.zen.kotlinreddit.fragments

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import com.example.zen.kotlinreddit.*
import com.example.zen.kotlinreddit.adapters.PostsAdapter
import com.example.zen.kotlinreddit.views.EndlessRecyclerViewScrollListener
import com.example.zen.kotlinreddit.views.PreCachingLayoutManager
import kotlinx.android.synthetic.main.recycler.*
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class PostsFragment : BaseFragment() {
	var currentSort = SORT_HOT
	var subreddit: String? = null
	override val layout = R.layout.front_page
	lateinit var adapter : PostsAdapter
	val table = TPost().getTableName()

	val clearSub = Observable.fromCallable {
		App.sdb.delete(table, null)
		//App.sdb.delete("sqlite_sequence", null)
	}

	val toggleSort = hashMapOf(SORT_HOT to true, SORT_NEW to true, SORT_PREVIEW to true, SORT_COMMENTS to true, SORT_SCORE to true, SORT_SUBREDDIT to true)

	companion object {
		const val TAG = "PostsFragment"
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
	}

	override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
		val query: Observable<List<TPost>>
		if (arguments == null) {
			setTitle("Hot")
			query = App.sdb.createQuery("$table", "select * from $table").mapToList(TPost.MAPPER)
			subs.add(Observable.fromCallable { Reddit.getHotPosts() }.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe())
		} else {
			subreddit = arguments.getString("subreddit")
			setTitle(subreddit!!)
			query = App.sdb.createQuery("$table", "select * from $table where subreddit = ?", subreddit).mapToList(TPost.MAPPER)
			subs.add(Observable.fromCallable { Reddit.getSubredditPosts(subreddit!!) }.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe())
		}

		subs.add(query.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(adapter))

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
				val postsSub = Observable.fromCallable { Reddit.getHotPosts() }
				Observable.concat(clearSub, postsSub).subscribeOn(Schedulers.newThread()).subscribe()
				return true
			}
			R.id.action_new -> {
				setTitle("New")
				val postsSub = Observable.fromCallable { Reddit.getNewPosts() }
				Observable.concat(clearSub, postsSub).subscribeOn(Schedulers.newThread()).subscribe()
				return true
			}
			R.id.action_subreddit -> {
				currentSort = SORT_SUBREDDIT
				adapter.sortBy(currentSort, toggleSort[currentSort])
				setTitleBaseOnSort()
				toggleSort[currentSort] = toggleSort[currentSort]!!.not()
				return true
			}
			R.id.action_comments -> {
				currentSort = SORT_COMMENTS
				adapter.sortBy(currentSort, toggleSort[currentSort])
				setTitleBaseOnSort()
				toggleSort[currentSort] = toggleSort[currentSort]!!.not()
				return true
			}
			R.id.action_score -> {
				currentSort = SORT_SCORE
				adapter.sortBy(currentSort, toggleSort[currentSort])
				setTitleBaseOnSort()
				toggleSort[currentSort] = toggleSort[currentSort]!!.not()
				return true
			}
			R.id.action_preview -> {
				currentSort = SORT_PREVIEW
				adapter.sortBy(currentSort, toggleSort[currentSort])
				setTitleBaseOnSort()
				toggleSort[currentSort] = toggleSort[currentSort]!!.not()
				return true
			}
		}

		return super.onOptionsItemSelected(item)
	}

	fun setTitleBaseOnSort() {
		when(currentSort) {
			SORT_PREVIEW -> {
				if(toggleSort[currentSort] == true) setTitle("Preview Descending")
				else setTitle("Preview Ascending")
			}
			SORT_SUBREDDIT -> {
				if(toggleSort[currentSort] == true) setTitle("Subreddit Descending")
				else setTitle("Subreddit Ascending")
			}
			SORT_SCORE -> {
				if(toggleSort[currentSort] == true) setTitle("Score Descending")
				else setTitle("Score Ascending")
			}
			SORT_COMMENTS -> {
				if(toggleSort[currentSort] == true) setTitle("Comments Descending")
				else setTitle("Comments Ascending")
			}
			else -> setTitle("Hot")
		}
	}

}