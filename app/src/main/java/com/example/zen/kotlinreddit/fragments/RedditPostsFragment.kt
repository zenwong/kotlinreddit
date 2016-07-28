package com.example.zen.kotlinreddit.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.zen.kotlinreddit.App
import com.example.zen.kotlinreddit.R
import com.example.zen.kotlinreddit.Reddit
import com.example.zen.kotlinreddit.adapters.PostsAdapter
import com.example.zen.kotlinreddit.models.Post
import com.example.zen.kotlinreddit.models.PostSort
import com.example.zen.kotlinreddit.views.EndlessRecyclerViewScrollListener
import com.squareup.sqlbrite.SqlBrite
import kotlinx.android.synthetic.main.main.*
import kotlinx.android.synthetic.main.recycler.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subjects.PublishSubject
import rx.subscriptions.CompositeSubscription

class RedditPostsFragment : Fragment() {
	//val select = "select * from posts order by display desc, created desc"
	//val select = "select * from posts order by display desc"
	//val select = "select * from posts"
	var sortParam = "display"
	var param = Observable.just(sortParam).distinctUntilChanged()
	val subject: PublishSubject<String> = PublishSubject.create()
	val select = "select * from posts order by ? desc"
	var subscriptions = CompositeSubscription()
	var adapter: PostsAdapter? = null
	//var selectSub = App.sdb.createQuery("posts", select).mapToList(Post.MAPPER)

	companion object {
		fun forSubreddit(subreddit: String) : RedditPostsFragment {
			val frag = RedditPostsFragment()
			val bundle = Bundle()
			bundle.putString("subreddit", subreddit)
			frag.arguments = bundle
			return frag
		}
	}

	override fun onAttach(context: Context) {
		super.onAttach(context)
		adapter = PostsAdapter(context)
	}

	override fun onResume() {
		super.onResume()



		EventBus.getDefault().register(this)
		activity.txtToolbarTitle.text = "Front Page"
		println("PostsFragment onResume")
		subscriptions = CompositeSubscription()
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		println("PostsFragment onViewCreated")
		//val layout = LinearLayoutManager(context)
		val layoutManager = LinearLayoutManager(activity)
		layoutManager.orientation = LinearLayoutManager.VERTICAL

		rv.setHasFixedSize(true)
		rv.layoutManager = layoutManager
		rv.adapter = adapter

		rv.addOnScrollListener(object : EndlessRecyclerViewScrollListener(layoutManager) {
			override fun onLoadMore(page: Int, totalItemsCount: Int) {
				println("LOADMORE: page: $page after: ${App.postAfter}")
				subscriptions.add(Observable.fromCallable { Reddit.getPostsAfter(10) }
					.subscribeOn(Schedulers.newThread())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe())
			}
		})

		subscriptions.add(Observable.fromCallable { Reddit.getHotPosts() }
			.subscribeOn(Schedulers.newThread())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe())

//		subscriptions.add(App.sdb.createQuery("posts", select, sortParam)
//			.mapToList(Post.MAPPER)
//			.subscribeOn(Schedulers.newThread())
//			.observeOn(AndroidSchedulers.mainThread())
//			.subscribe(adapter))


		param.flatMap { App.sdb.createQuery("posts", select, it)
			.mapToList(Post.MAPPER) }.subscribeOn(Schedulers.newThread())
			.observeOn(AndroidSchedulers.mainThread())
			//.subscribe(adapter)

		//subscriptions.add(selectSub.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(adapter))
	}

	fun query(sort: String) : Observable<SqlBrite.Query> {
		return App.sdb.createQuery("posts", select, sort)
	}

	@Subscribe(threadMode = ThreadMode.MAIN)
	fun onPostSort(sort: PostSort) {
		println("onPostsSort ${sort.sort}")
		when (sort.sort) {
			"preview" -> 	sortParam = "display"
			"comments" -> 	sortParam = "comments"
			"score" ->  sortParam = "score"
		}

		//subscriptions.add(App.sdb.createQuery("posts", select).mapToList(Post.MAPPER).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(sortedAdapter))
	}

	override fun onPause() {
		super.onPause()
		EventBus.getDefault().unregister(this)
		subscriptions.clear()
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		return inflater.inflate(R.layout.front_page, container, false)
	}
}