package com.example.zen.kotlinreddit

import android.net.Uri
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.zen.kotlinreddit.fragments.PostsAdapter
import com.example.zen.kotlinreddit.models.Post
import com.example.zen.kotlinreddit.views.PreCachingLayoutManager
import kotlinx.android.synthetic.main.app_bar_posts.*
import kotlinx.android.synthetic.main.main.*
import kotlinx.android.synthetic.main.posts.*
import rx.Observable
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription

class PostsActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
	val subs = CompositeSubscription()
	var adapter: PostsAdapter? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		Log.d("ZXZ", "PostsActivity oncreate")

		setContentView(R.layout.posts)
		setSupportActionBar(postsToolbar)
		supportActionBar?.setDisplayShowTitleEnabled(false)

		toolbar_title.text = "Posts Activity"

		fab.setOnClickListener { view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show() }

		val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
		drawer_layout.addDrawerListener(toggle)
		toggle.syncState()

		nav_view.setNavigationItemSelectedListener(this)

		val layoutManager = PreCachingLayoutManager(this)
		layoutManager.orientation = LinearLayoutManager.VERTICAL
		layoutManager.setExtraLayoutSpace(resources.displayMetrics.heightPixels)
		adapter = PostsAdapter(this, subs)

		postsList.setHasFixedSize(true)
		postsList.layoutManager = layoutManager
		postsList.adapter = adapter

//		var querySub: Subscription? = null
//		if (intent.dataString != null) {
//			val subreddit = Uri.parse(intent.dataString).pathSegments[1]
//			querySub = App.sdb.createQuery("posts", "select * from posts where subreddit = ?", subreddit)
//				.mapToList(Post.MAPPER)
//				.subscribeOn(Schedulers.newThread())
//				.observeOn(AndroidSchedulers.mainThread())
//				.subscribe(adapter)
//		} else {
//			querySub = App.sdb.createQuery("posts", "select * from posts")
//				.mapToList(Post.MAPPER)
//				.subscribeOn(Schedulers.newThread())
//				.observeOn(AndroidSchedulers.mainThread())
//				.subscribe(adapter)
//		}
//
//		subs.add(querySub)
	}

	override fun onRestart() {
		Log.d("ZXZ", "PostsActivity onRestart")
		initQuery()

		super.onRestart()
	}

	override fun onPostResume() {
		Log.d("ZXZ", "PostsActivity onPostResume")
		initQuery()
		super.onPostResume()
	}

	override fun onStop() {
		subs.unsubscribe()
		Log.d("ZXZ", "PostsActivity onStop")

		super.onStop()
	}

	override fun onDestroy() {
		subs.unsubscribe()
		super.onDestroy()
	}

	fun initQuery() {
		var querySub: Subscription? = null
		if (intent.dataString != null) {
			val paths = Uri.parse(intent.dataString).pathSegments
			val subreddit = paths[1]
			if(paths.size > 2) {
				val url = "${Reddit.REDDIT_FRONT}${paths[4]}/.json"
				subs.add(Observable.fromCallable { Reddit.parseComments(url, paths[3]) }
					.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe())
			} else {
				subs.add(Observable.fromCallable { Reddit.getSubredditPosts(subreddit) }
					.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe())
			}
			Log.i("ZXZ", "initQuery $subreddit")

			querySub = App.sdb.createQuery("posts", "select * from posts where subreddit = ? order by subreddit asc", subreddit)
				.mapToList(Post.MAPPER)
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(adapter)


		} else {
			querySub = App.sdb.createQuery("posts", "select * from posts order by subreddit asc")
				.mapToList(Post.MAPPER)
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(adapter)
		}

		subs.add(querySub)
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
			R.id.action_settings -> {
				println("SETTINGS")
				return true
			}
			R.id.action_refresh -> {
				println("REFRESH")
				return true
			}
		}

		return super.onOptionsItemSelected(item)
	}

	@SuppressWarnings("StatementWithEmptyBody")
	override fun onNavigationItemSelected(item: MenuItem): Boolean {
		// Handle navigation view item clicks here.
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
}
