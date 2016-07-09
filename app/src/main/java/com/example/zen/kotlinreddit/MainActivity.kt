package com.example.zen.kotlinreddit

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.zen.kotlinreddit.fragments.BrowserFragment
import com.example.zen.kotlinreddit.fragments.CommentsFragment
import com.example.zen.kotlinreddit.fragments.RedditPostsFragment
import com.example.zen.kotlinreddit.models.CommentsRequest
import com.example.zen.kotlinreddit.models.Navigation
import com.example.zen.kotlinreddit.models.Title
import kotlinx.android.synthetic.main.main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription

const val FRONT = 1
const val COMMENTS = 2
const val MESSAGES = 3

class MainActivity : AppCompatActivity() {
	var subscriptions =  CompositeSubscription()

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

//	override fun onCreateOptionsMenu(menu: Menu): Boolean {
//		val item = menu.add("sync").setOnMenuItemClickListener {
//			val clearSub = Observable.fromCallable {
//				App.sdb.delete("posts", null)
//				App.sdb.delete("comments", null)
//				App.sdb.delete("messages", null)
//				App.sdb.delete("sqlite_sequence", null)
//			}
//
//			//val postsSub = Observable.fromCallable { Reddit.getHotPosts() }
//			val postsSub = Observable.fromCallable { Reddit.getPostsAfter() }
//
//			subscriptions.add(Observable.concat(clearSub, postsSub).subscribeOn(Schedulers.newThread()).subscribe())
//			true
//		}
//
//		item.icon = IconDrawable(this, FontAwesomeIcons.fa_refresh).colorRes(R.color.button_material_light).actionBarSize()
//
//		MenuItemCompat.setShowAsAction(item, MenuItemCompat.SHOW_AS_ACTION_IF_ROOM)
//		return super.onCreateOptionsMenu(menu)
//	}

//	override fun onCreateOptionsMenu(menu: Menu): Boolean {
//		menuInflater.inflate(R.menu.main_menu, menu)
//		val refresh = menu.findItem(R.id.mnuRefresh).setOnMenuItemClickListener {
//
//		}
//		return true
//	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.main)
		setSupportActionBar(toolbar)


		if(App.accessToken == null) {
			val ft = supportFragmentManager.beginTransaction()
			ft.replace(R.id.content, BrowserFragment())
			ft.commit()
		} else {
			txtToolbarTitle.text = "Front Page"
			val ft = supportFragmentManager.beginTransaction()
			ft.replace(R.id.content, RedditPostsFragment())
			ft.addToBackStack("PostsFragment")
			ft.commit()
		}

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
			val clearSub = Observable.fromCallable {
				App.sdb.delete("posts", null)
				App.sdb.delete("comments", null)
				App.sdb.delete("messages", null)
				App.sdb.delete("sqlite_sequence", null)
			}

			val postsSub = Observable.fromCallable { Reddit.getNewPosts() }
			subscriptions.add(Observable.concat(clearSub, postsSub).subscribeOn(Schedulers.newThread()).subscribe())
		}

	}

	@Subscribe(threadMode = ThreadMode.MAIN)
	fun onNav(nav: Navigation) {
		val ft = supportFragmentManager.beginTransaction()
		when(nav.fragment) {
			FRONT ->  {
				ft.replace(R.id.content, RedditPostsFragment())
				ft.addToBackStack("PostsFragment")
			}
			COMMENTS -> {
				//ft.replace(R.id.content, CommentsFragment.newInstance(nav.id!!))
				ft.replace(R.id.content, CommentsFragment.newInstance(nav.pid!!))
				ft.addToBackStack("CommentsFragment")
			}
			MESSAGES -> println("messages")
		}
		ft.commit()
	}

	@Subscribe(threadMode = ThreadMode.MAIN)
	fun onTitle(t: Title) {
		txtToolbarTitle.text = t.title
	}

	@Subscribe(threadMode = ThreadMode.MAIN)
	fun onCommentsRequest(req: CommentsRequest) {

		subscriptions.add(Observable.fromCallable { Reddit.parseComments(req.url, req.parent, 500) }
			.subscribeOn(Schedulers.newThread())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe())

		val ft = supportFragmentManager.beginTransaction()
		ft.replace(R.id.content, CommentsFragment.Companion.newInstance(req.parent))
		ft.addToBackStack("CommentsFragment")
		ft.commit()
	}
}
