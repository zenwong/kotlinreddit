package com.example.zen.kotlinreddit.activities

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.CookieManager
import android.webkit.CookieSyncManager
import com.example.zen.kotlinreddit.*
import com.example.zen.kotlinreddit.fragments.BrowserFragment
import com.example.zen.kotlinreddit.fragments.CommentsFragment
import com.example.zen.kotlinreddit.fragments.PostsFragment
import com.example.zen.kotlinreddit.models.Navigation
import kotlinx.android.synthetic.main.app_bar_posts.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.posts)

		setSupportActionBar(postsToolbar)
		supportActionBar?.setDisplayShowTitleEnabled(false)

		if (App.accessToken != null) {
			startActivity(Intent(this, PostsActivity::class.java))
			finish()
		} else {
			supportFragmentManager.beginTransaction().replace(R.id.contentFrame, BrowserFragment(), BrowserFragment.TAG).commit()
		}
	}

	@Subscribe(threadMode = ThreadMode.MAIN)
	fun onNav(nav: Navigation) {
		val ft = supportFragmentManager.beginTransaction()
		when (nav.fragment) {
			FRONT -> {
				ft.replace(R.id.contentFrame, PostsFragment(), PostsFragment.TAG)
				ft.addToBackStack(PostsFragment.TAG)
			}
			COMMENTS -> {
				//ft.replace(R.id.content, CommentsFragment.newInstance(nav.id!!))
				ft.replace(R.id.contentFrame, CommentsFragment.newInstance(nav.pid!!))
				ft.addToBackStack("CommentsFragment")
			}
			BROWSER -> {
				clearCookies(this)
				ft.replace(R.id.contentFrame, BrowserFragment(), BrowserFragment.TAG)
			}
			MESSAGES -> {
				println("messages")
			}
		}
		ft.commit()
	}

	@SuppressWarnings("deprecation")
	fun clearCookies(context: Context) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
			CookieManager.getInstance().removeAllCookies(null)
			CookieManager.getInstance().flush()
		} else {
			val cookieSyncMngr = CookieSyncManager.createInstance(context)
			cookieSyncMngr.startSync()
			val cookieManager = CookieManager.getInstance()
			cookieManager.removeAllCookie()
			cookieManager.removeSessionCookie()
			cookieSyncMngr.stopSync()
			cookieSyncMngr.sync()
		}
	}

	override fun onResume() {
		super.onResume()
		EventBus.getDefault().register(this)
	}

	override fun onPause() {
		EventBus.getDefault().unregister(this)
		super.onPause()
	}

}
