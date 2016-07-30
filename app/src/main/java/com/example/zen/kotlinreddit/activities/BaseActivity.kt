package com.example.zen.kotlinreddit.activities

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.webkit.CookieManager
import android.webkit.CookieSyncManager
import com.example.zen.kotlinreddit.*
import com.example.zen.kotlinreddit.fragments.BookmarksFragment
import com.example.zen.kotlinreddit.fragments.BrowserFragment
import com.example.zen.kotlinreddit.fragments.MessageFragment
import com.example.zen.kotlinreddit.fragments.PostsFragment
import com.example.zen.kotlinreddit.models.CommentsRequest
import com.example.zen.kotlinreddit.models.Navigation
import com.example.zen.kotlinreddit.models.Title
import kotlinx.android.synthetic.main.app_bar_posts.*
import kotlinx.android.synthetic.main.main.*
import kotlinx.android.synthetic.main.posts.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import rx.subscriptions.CompositeSubscription

abstract class BaseActivity: AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
	var currentTag = ""
	var state: Bundle? = null
	var subs = CompositeSubscription()

	abstract fun init(savedInstanceState: Bundle?)

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.posts)
		setSupportActionBar(postsToolbar)
		supportActionBar?.setDisplayShowTitleEnabled(false)
		val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
		drawer_layout.addDrawerListener(toggle)
		toggle.syncState()
		nav_view.setNavigationItemSelectedListener(this)

		init(savedInstanceState)
	}

	override fun onNavigationItemSelected(item: MenuItem): Boolean {
		when (item.itemId) {
			R.id.nav_bookmarks -> supportFragmentManager.beginTransaction().replace(R.id.contentFrame, BookmarksFragment(), BookmarksFragment.TAG).addToBackStack(BookmarksFragment.TAG).commit()
			R.id.nav_messages -> supportFragmentManager.beginTransaction().replace(R.id.contentFrame, MessageFragment(), MessageFragment.TAG).addToBackStack(MessageFragment.TAG).commit()
		}

		drawer_layout.closeDrawer(GravityCompat.START)
		return true
	}

	override fun onBackPressed() {
		if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
			drawer_layout.closeDrawer(GravityCompat.START)
		} else {
			super.onBackPressed()
		}
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

	@Subscribe(threadMode = ThreadMode.MAIN)
	fun onTitle(t: Title) {
		toolbar_title.text = t.title
	}

	@Subscribe(threadMode = ThreadMode.MAIN)
	fun onCommentsRequest(req: CommentsRequest) {
//		currentTag = TestCommentsFragment.TAG
//		val ft = supportFragmentManager.beginTransaction()
//		ft.replace(R.id.contentFrame, TestCommentsFragment.newInstance(req.url, req.parent), TestCommentsFragment.TAG)
//		ft.addToBackStack(TestCommentsFragment.TAG)
//		ft.commit()

		val intent = Intent(this, CommentsActivity::class.java)
		intent.putExtra("url", req.url)
		intent.putExtra("parent", req.parent)
		startActivity(intent)
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
//				ft.replace(R.id.contentFrame, CommentsFragment.newInstance(nav.pid!!))
//				ft.addToBackStack("CommentsFragment")

				startActivity(Intent(this, CommentsActivity::class.java))
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

//	override fun onNewIntent(intent: Intent) {
//		super.onNewIntent(intent)
//		setIntent(intent)
//	}

	override fun onResume() {
		super.onResume()
		Log.d("onResume", javaClass.simpleName)
		EventBus.getDefault().register(this)
	}

	override fun onPause() {
		subs.unsubscribe()
		EventBus.getDefault().unregister(this)
		super.onPause()
	}

}