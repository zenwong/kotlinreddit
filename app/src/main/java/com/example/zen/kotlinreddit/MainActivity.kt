package com.example.zen.kotlinreddit

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.zen.kotlinreddit.fragments.BrowserFragment
import com.example.zen.kotlinreddit.models.PostSort
import kotlinx.android.synthetic.main.main.*
import org.greenrobot.eventbus.EventBus
import rx.Observable
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription

class MainActivity : AppCompatActivity() {
	var subscriptions = CompositeSubscription()

	override fun onStart() {
		super.onStart()
		subscriptions = CompositeSubscription()
		EventBus.getDefault().register(this)
	}

	override fun onPause() {
		EventBus.getDefault().unregister(this)
		super.onPause()
	}

	override fun onStop() {
		EventBus.getDefault().unregister(this)
		subscriptions.clear()
		super.onStop()
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.main)
		setSupportActionBar(toolbar)

		val ft = supportFragmentManager.beginTransaction()
		if (App.accessToken == null) {
			ft.replace(R.id.content, BrowserFragment())
		} else {
			//txtToolbarTitle.text = "Front Page"
//			ft.replace(R.id.content, RedditPostsFragment())
//			ft.addToBackStack("PostsFragment")

//			txtToolbarTitle.text = "Gif Fragment"
//			ft.replace(R.id.content, GifFragment())
//			ft.addToBackStack("PostsFragment")

			val intent = Intent(this, PostsActivity::class.java)
			startActivity(intent)
		}
		ft.commit()


		txtToolbarRefresh.setOnClickListener {
			val clearSub = Observable.fromCallable {
				App.sdb.delete("posts", null)
				App.sdb.delete("comments", null)
				App.sdb.delete("messages", null)
				App.sdb.delete("sqlite_sequence", null)
			}

			val postsSub = Observable.fromCallable { Reddit.getHotPosts() }
			//val postsSub = Observable.fromCallable { Reddit.getPostsAfter() }

			subscriptions.add(Observable.concat(clearSub, postsSub).subscribeOn(Schedulers.newThread()).subscribe())
		}

		txtToolbarSort.setOnClickListener {
//			val clearSub = Observable.fromCallable {
//				App.sdb.delete("posts", null)
//				App.sdb.delete("comments", null)
//				App.sdb.delete("messages", null)
//				App.sdb.delete("sqlite_sequence", null)
//			}
//
//			val postsSub = Observable.fromCallable { Reddit.getNewPosts() }
//			subscriptions.add(Observable.concat(clearSub, postsSub).subscribeOn(Schedulers.newThread()).subscribe())

			//PostSortDialog().show(supportFragmentManager, "dialog")

			showDialog(1)
		}

	}

	override fun onCreateDialog(id: Int): Dialog {
		return AlertDialog.Builder(this)
			.setTitle("Sort Options")
			.setItems(R.array.posts_sort_options) { dialog, which ->
				println("clicked on $which")
				val clearSub = Observable.fromCallable {
					App.sdb.delete("posts", null)
					App.sdb.delete("comments", null)
					App.sdb.delete("comment_headers", null)
					App.sdb.delete("messages", null)
					App.sdb.delete("sqlite_sequence", null)
				}

				when (which) {
					0 -> subscriptions.add(Observable.concat(clearSub, Observable.fromCallable { Reddit.getHotPosts() }).subscribeOn(Schedulers.newThread()).subscribe())
					1 -> subscriptions.add(Observable.concat(clearSub, Observable.fromCallable { Reddit.getNewPosts() }).subscribeOn(Schedulers.newThread()).subscribe())
					2 -> EventBus.getDefault().post(PostSort("preview"))
					3 -> EventBus.getDefault().post(PostSort("comments"))
					4 -> EventBus.getDefault().post(PostSort("score"))
				}
			}
			.create()
	}

//	@Subscribe(threadMode = ThreadMode.MAIN)
//	fun onNav(nav: Navigation) {
//		val ft = supportFragmentManager.beginTransaction()
//		when (nav.fragment) {
//			FRONT -> {
//				ft.replace(R.id.content, RedditPostsFragment())
//				ft.addToBackStack("PostsFragment")
//			}
//			COMMENTS -> {
//				//ft.replace(R.id.content, CommentsFragment.newInstance(nav.id!!))
//				ft.replace(R.id.content, CommentsFragment.newInstance(nav.pid!!))
//				ft.addToBackStack("CommentsFragment")
//			}
//			MESSAGES -> println("messages")
//		}
//		ft.commit()
//	}
//
//	@Subscribe(threadMode = ThreadMode.MAIN)
//	fun onTitle(t: Title) {
//		txtToolbarTitle.text = t.title
//	}
//
//	@Subscribe(threadMode = ThreadMode.MAIN)
//	fun onCommentsRequest(req: CommentsRequest) {
//
//		subscriptions.add(Observable.fromCallable { Reddit.parseComments(req.url, req.parent, 500) }
//			.subscribeOn(Schedulers.newThread())
//			.observeOn(AndroidSchedulers.mainThread())
//			.subscribe())
//
//		val ft = supportFragmentManager.beginTransaction()
//		ft.replace(R.id.content, CommentsFragment.Companion.newInstance(req.parent))
//		ft.addToBackStack("CommentsFragment")
//		ft.commit()
//	}
}
