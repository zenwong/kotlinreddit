package com.example.zen.kotlinreddit

import android.os.Bundle
import android.support.v4.view.MenuItemCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import com.example.zen.kotlinreddit.fragments.BrowserFragment
import com.example.zen.kotlinreddit.fragments.CommentsFragment
import com.example.zen.kotlinreddit.fragments.RedditPostsFragment
import com.example.zen.kotlinreddit.models.CommentsRequest
import com.example.zen.kotlinreddit.models.Navigation
import com.joanzapata.iconify.IconDrawable
import com.joanzapata.iconify.fonts.FontAwesomeIcons
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
	lateinit var subscriptions: CompositeSubscription

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
		subscriptions.unsubscribe()
		super.onStop()
	}

	override fun onCreateOptionsMenu(menu: Menu): Boolean {
		val item = menu.add("sync").setOnMenuItemClickListener {
			println("sync clicked")
			true
		}

		item.icon = IconDrawable(this, FontAwesomeIcons.fa_refresh).colorRes(R.color.button_material_light).actionBarSize()

		MenuItemCompat.setShowAsAction(item, MenuItemCompat.SHOW_AS_ACTION_IF_ROOM)
		return super.onCreateOptionsMenu(menu)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		//Reddit.get(App.accessToken, Reddit.REDDIT_FRONT)

		if(App.accessToken == null) {
			val ft = supportFragmentManager.beginTransaction()
			ft.replace(R.id.content, BrowserFragment())
			ft.commit()
		} else {
			//Reddit.get(App.accessToken, Reddit.REDDIT_FRONT)
			val ft = supportFragmentManager.beginTransaction()
			ft.replace(R.id.content, RedditPostsFragment())
			ft.addToBackStack("PostsFragment")
			ft.commit()
		}

//		val ft = supportFragmentManager.beginTransaction()
//		ft.replace(R.id.content, BrowserFragment())
//		ft.commit()
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
	fun onCommentsRequest(req: CommentsRequest) {

		subscriptions.add(Observable.fromCallable { Reddit.getComments(req.url, req.pid) }
		.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe())

		val ft = supportFragmentManager.beginTransaction()
		ft.replace(R.id.content, CommentsFragment.Companion.newInstance(req.pid))
		ft.addToBackStack("CommentsFragment")
		ft.commit()
	}
}
