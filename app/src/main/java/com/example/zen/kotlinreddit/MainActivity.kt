package com.example.zen.kotlinreddit

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.zen.kotlinreddit.fragments.BrowserFragment
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

		if(App.access == null) {
			val ft = supportFragmentManager.beginTransaction()
			ft.replace(R.id.content, BrowserFragment())
			ft.commit()
		} else {
			Reddit.get(App.access, Reddit.REDDIT_FRONT)
			val ft = supportFragmentManager.beginTransaction()
			ft.replace(R.id.content, RedditPostsFragment())
			ft.commit()
		}
	}

	@Subscribe(threadMode = ThreadMode.MAIN)
	fun onNav(nav: Navigation) {
		when(nav.fragment) {
			FRONT -> println("front")
			COMMENTS -> println("comments")
			MESSAGES -> println("messages")
		}
	}
}
