package com.example.zen.kotlinreddit

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.webkit.CookieManager
import android.webkit.CookieSyncManager
import com.example.zen.kotlinreddit.fragments.*
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

class PostsActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
	var subs = CompositeSubscription()
	var state: Bundle? = null
	var currentTag = ""

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		savedInstanceState?.let {
			state = savedInstanceState
			currentTag = savedInstanceState.getString("currentTag")
			println("Current Tag $currentTag")
			val frag = supportFragmentManager.getFragment(savedInstanceState, currentTag)

			frag?.let {
				supportFragmentManager.beginTransaction().replace(R.id.contentFrame, frag, currentTag).commit()
			}
		}

		setContentView(R.layout.posts)
		setSupportActionBar(postsToolbar)
		supportActionBar?.setDisplayShowTitleEnabled(false)

		val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
		drawer_layout.addDrawerListener(toggle)
		toggle.syncState()

		nav_view.setNavigationItemSelectedListener(this)
	}

	fun loadAppropriateFragment() {
		val ft = supportFragmentManager.beginTransaction()
		var frag: Fragment? = null

		if (intent.dataString != null) {
			//toolbar_title.text = intent.dataString
			val paths = intent.data.pathSegments

			// handle various types of reddit links
			when (paths.size) {
				0 -> {
					currentTag = PostsFragment.TAG
					frag = PostsFragment()
				}
				2 -> {
					val subreddit = paths[1]
					currentTag = PostsFragment.TAG
					frag = PostsFragment.forSubreddit(subreddit)
				}
				else -> {
					val parent = paths[3]
					currentTag = TestCommentsFragment.TAG
					frag = TestCommentsFragment.newInstance(intent.dataString, parent)
				}
			}
		} else {
			if (state == null) {
				currentTag = PostsFragment.TAG
				frag = PostsFragment()
			} else {
				frag = supportFragmentManager.getFragment(state, currentTag)
			}
		}

		frag?.let {
			ft.replace(R.id.contentFrame, frag, currentTag)
		}
		ft.commit()
	}

	override fun onNewIntent(intent: Intent) {
		super.onNewIntent(intent)
		setIntent(intent)
	}

	override fun onResume() {
		super.onResume()
		loadAppropriateFragment()
		EventBus.getDefault().register(this)
	}

	override fun onPause() {
		subs.unsubscribe()
		EventBus.getDefault().unregister(this)
		super.onPause()
	}

	override fun onSaveInstanceState(outState: Bundle) {
		outState.putString("currentTag", currentTag)
		//super.onSaveInstanceState(outState)
	}

	override fun onBackPressed() {
		if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
			drawer_layout.closeDrawer(GravityCompat.START)
		} else {
			super.onBackPressed()
		}
	}

	override fun onNavigationItemSelected(item: MenuItem): Boolean {

		when(item.itemId) {
			R.id.nav_bookmarks -> supportFragmentManager.beginTransaction().replace(R.id.contentFrame, BookmarksFragment(), BookmarksFragment.TAG).addToBackStack(BookmarksFragment.TAG).commit()
			R.id.nav_messages -> supportFragmentManager.beginTransaction().replace(R.id.contentFrame, MessageFragment(), MessageFragment.TAG).addToBackStack(MessageFragment.TAG).commit()
		}

		drawer_layout.closeDrawer(GravityCompat.START)
		return true
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
		currentTag = TestCommentsFragment.TAG
		val ft = supportFragmentManager.beginTransaction()
		ft.replace(R.id.contentFrame, TestCommentsFragment.newInstance(req.url, req.parent), TestCommentsFragment.TAG)
		ft.addToBackStack(TestCommentsFragment.TAG)
		ft.commit()
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
}
