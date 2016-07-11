package com.example.zen.kotlinreddit

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
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

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

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
		if(App.accessToken != null) {
			if(intent.dataString != null) {
				//toolbar_title.text = intent.dataString
				val paths = intent.data.pathSegments

				// handle various types of reddit links
				when(paths.size) {
					0 -> ft.replace(R.id.contentFrame, PostsFragment())
					2 -> {
						val subreddit = paths[1]
						ft.replace(R.id.contentFrame, PostsFragment.forSubreddit(subreddit))
					}
					else -> {
						val parent = paths[3]
						ft.replace(R.id.contentFrame, TestCommentsFragment.newInstance(intent.dataString, parent))
					}
				}
			} else {
				ft.replace(R.id.contentFrame, PostsFragment())
			}
		} else {
			ft.replace(R.id.contentFrame, BrowserFragment())
		}

		ft.commit()
	}

	override fun onStop() {
		subs.unsubscribe()
		EventBus.getDefault().unregister(this)
		super.onStop()
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

	override fun onCreateOptionsMenu(menu: Menu): Boolean {
		menuInflater.inflate(R.menu.posts, menu)
		return true
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		when (item.itemId) {
			R.id.action_hot -> {
				println("SETTINGS")
				return true
			}
			R.id.action_new -> {
				println("REFRESH")
				return true
			}
		}

		return super.onOptionsItemSelected(item)
	}

	override fun onNavigationItemSelected(item: MenuItem): Boolean {
		val id = item.itemId

		if (id == R.id.nav_camera) {
			// Handle the camera action
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
		val ft = supportFragmentManager.beginTransaction()
		ft.replace(R.id.contentFrame, TestCommentsFragment.newInstance(req.url, req.parent))
		ft.addToBackStack("CommentsFragment")
		ft.commit()
	}

	@Subscribe(threadMode = ThreadMode.MAIN)
	fun onNav(nav: Navigation) {
		val ft = supportFragmentManager.beginTransaction()
		when (nav.fragment) {
			FRONT -> {
				ft.replace(R.id.contentFrame, PostsFragment())
				ft.addToBackStack("PostsFragment")
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
