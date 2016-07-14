package com.example.zen.kotlinreddit

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.example.zen.kotlinreddit.fragments.BrowserFragment
import com.example.zen.kotlinreddit.fragments.CommentsFragment
import com.example.zen.kotlinreddit.fragments.PostsFragment
import com.example.zen.kotlinreddit.fragments.TestCommentsFragment
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

		toolbar_title.text = "Post Acitivty"

		val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
		drawer_layout.addDrawerListener(toggle)
		toggle.syncState()

		nav_view.setNavigationItemSelectedListener(this)
	}

	override fun onNewIntent(intent: Intent) {
		super.onNewIntent(intent)
		setIntent(intent)
	}

	override fun onResume() {
		super.onResume()
		EventBus.getDefault().register(this)

		val ft = supportFragmentManager.beginTransaction()
		var frag: Fragment? = null
		if(App.accessToken != null) {

			if(intent.dataString != null) {
				//toolbar_title.text = intent.dataString
				val paths = intent.data.pathSegments

				// handle various types of reddit links
				when(paths.size) {
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
			}	else {
				if(state == null) {
					currentTag = PostsFragment.TAG
					frag = PostsFragment()
				} else {
					frag = supportFragmentManager.getFragment(state, currentTag)
				}
			}
		} else {
			toolbar_title.text = "Kotlin Reddit"
			currentTag = BrowserFragment.TAG
			frag = BrowserFragment()
		}

		frag?.let {
			ft.replace(R.id.contentFrame, frag, currentTag)
		}
		ft.commit()
	}

	override fun onStop() {
		subs.unsubscribe()
		EventBus.getDefault().unregister(this)
		super.onStop()
	}

	override fun onSaveInstanceState(outState: Bundle) {
		super.onSaveInstanceState(outState)
		outState.putString("currentTag", currentTag)
	}

	override fun onDestroy() {
		subs.unsubscribe()
		super.onDestroy()
	}

	override fun onBackPressed() {
		if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
			drawer_layout.closeDrawer(GravityCompat.START)
		} else {
			super.onBackPressed()
		}
	}

	override fun onNavigationItemSelected(item: MenuItem): Boolean {
		val id = item.itemId

		if (id == R.id.nav_camera) {
		} else if (id == R.id.nav_gallery) {

		} else if (id == R.id.nav_slideshow) {

		} else if (id == R.id.nav_manage) {

		} else if (id == R.id.nav_share) {

		} else if (id == R.id.nav_send) {

		}

		drawer_layout.closeDrawer(GravityCompat.START)
		return true
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
			MESSAGES -> println("messages")
		}
		ft.commit()
	}
}
