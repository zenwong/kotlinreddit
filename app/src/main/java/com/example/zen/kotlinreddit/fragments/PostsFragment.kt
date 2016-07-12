package com.example.zen.kotlinreddit.fragments

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import com.example.zen.kotlinreddit.Api
import com.example.zen.kotlinreddit.R
import com.example.zen.kotlinreddit.adapters.PostSnappyAdapter
import com.example.zen.kotlinreddit.views.PreCachingLayoutManager
import kotlinx.android.synthetic.main.recycler.*
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class PostsFragment : BaseFragment() {
	var currentSort = PostSnappyAdapter.SORT_CREATED
	lateinit var adapter : PostSnappyAdapter
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

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setHasOptionsMenu(true)
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		when (item.itemId) {
			R.id.action_hot -> {
				val delSub = Observable.fromCallable { Api.clearSnappy() }
				val getSub = Observable.fromCallable { Api.getHotPosts() }
				subs.add(Observable.concat(delSub, getSub).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe())

				return true
			}
			R.id.action_new -> {
				val delSub = Observable.fromCallable { Api.clearSnappy() }
				val getSub = Observable.fromCallable { Api.getHotPosts() }
				subs.add(Observable.concat(delSub, getSub).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe())

				return true
			}
			R.id.action_subreddit -> {
				currentSort = PostSnappyAdapter.SORT_SUBREDDIT
				adapter.sortBy(PostSnappyAdapter.SORT_SUBREDDIT)
				setTitleBaseOnSort()
				return true
			}
			R.id.action_comments -> {
				currentSort = PostSnappyAdapter.SORT_COMMENTS
				adapter.sortBy(PostSnappyAdapter.SORT_COMMENTS)
				setTitleBaseOnSort()
				return true
			}
			R.id.action_score -> {
				currentSort = PostSnappyAdapter.SORT_SCORE
				adapter.sortBy(PostSnappyAdapter.SORT_SCORE)
				setTitleBaseOnSort()
				return true
			}
			R.id.action_preview -> {
				currentSort = PostSnappyAdapter.SORT_PREVIEW
				adapter.sortBy(PostSnappyAdapter.SORT_PREVIEW)
				setTitleBaseOnSort()
				return true
			}
		}

		return super.onOptionsItemSelected(item)
	}

	fun setTitleBaseOnSort() {
		when(currentSort) {
			PostSnappyAdapter.SORT_PREVIEW -> setTitle("Preview")
			PostSnappyAdapter.SORT_SUBREDDIT -> setTitle("Subreddit")
			PostSnappyAdapter.SORT_SCORE -> setTitle("Score")
			PostSnappyAdapter.SORT_COMMENTS -> setTitle("Comments")
			PostSnappyAdapter.SORT_CREATED -> setTitle("Created")
		}
	}

	fun testSnappy() {
		setTitleBaseOnSort()

		subs.add(Observable.fromCallable { Api.getHotPosts() }.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
			.subscribe())

		subs.add(Observable.fromCallable { Api.getAllPostsFromDb() }.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
			.subscribe(adapter))


		val layoutManager = PreCachingLayoutManager(activity)
		layoutManager.orientation = LinearLayoutManager.VERTICAL
		layoutManager.setExtraLayoutSpace(resources.displayMetrics.heightPixels)

		rv.setHasFixedSize(true)
		rv.layoutManager = layoutManager
		rv.adapter = adapter
	}

	override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		adapter = PostSnappyAdapter(context, currentSort)

		testSnappy()

//		val adapter = TestPostAdapter(context)
//		val query: Observable<List<Post>>
//		if (arguments == null) {
//			setTitle("Hot")
//			query = App.sdb.createQuery("posts", "select * from posts").mapToList(Post.MAPPER)
//			subs.add(Observable.fromCallable { Reddit.getHotPosts() }.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe())
//		} else {
//			val subreddit = arguments.getString("subreddit")
//			setTitle(subreddit)
//			query = App.sdb.createQuery("posts", "select * from posts where subreddit = ?", subreddit).mapToList(Post.MAPPER)
//			subs.add(Observable.fromCallable { Reddit.getSubredditPosts(subreddit) }.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe())
//		}
//		subs.add(query.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(adapter))

//		val layoutManager = PreCachingLayoutManager(activity)
//		layoutManager.orientation = LinearLayoutManager.VERTICAL
//		layoutManager.setExtraLayoutSpace(resources.displayMetrics.heightPixels)

		//rv.setHasFixedSize(true)
//		rv.layoutManager = layoutManager
//		rv.adapter = adapter

//		rv.addOnScrollListener(object : EndlessRecyclerViewScrollListener(layoutManager) {
//			override fun onLoadMore(page: Int, totalItemsCount: Int) {
//				subs.add(Observable.fromCallable { Reddit.getPostsAfter(10) }
//					.subscribeOn(Schedulers.newThread())
//					.observeOn(AndroidSchedulers.mainThread())
//					.subscribe())
//			}
//		})
	}

}