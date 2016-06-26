package com.example.zen.kotlinreddit

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.zen.kotlinreddit.fragments.BrowserFragment
import com.example.zen.kotlinreddit.fragments.CommentsFragment
import com.example.zen.kotlinreddit.fragments.RedditPostsFragment
import com.example.zen.kotlinreddit.models.Navigation
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

const val FRONT = 1
const val COMMENTS = 2
const val MESSAGES = 3

class MainActivity : AppCompatActivity() {

	override fun onStart() {
		super.onStart()
		EventBus.getDefault().register(this)
	}

	override fun onPause() {
		EventBus.getDefault().unregister(this)
		super.onPause()
	}

	override fun onStop() {
		EventBus.getDefault().unregister(this)
		super.onStop()
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		Reddit.get(App.accessToken, Reddit.REDDIT_FRONT)

		if(App.accessToken == null) {
			val ft = supportFragmentManager.beginTransaction()
			ft.replace(R.id.content, BrowserFragment())
			ft.commit()
		} else {
			//Reddit.get(App.accessToken, Reddit.REDDIT_FRONT)
			val ft = supportFragmentManager.beginTransaction()
			ft.replace(R.id.content, RedditPostsFragment())
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
			FRONT -> ft.replace(R.id.content, RedditPostsFragment())
			COMMENTS -> ft.replace(R.id.content, CommentsFragment.newInstance(nav.id!!))
			MESSAGES -> println("messages")
		}
		ft.commit()
	}
}
