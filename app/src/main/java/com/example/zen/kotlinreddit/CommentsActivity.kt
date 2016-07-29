package com.example.zen.kotlinreddit

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.Html
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import com.example.zen.kotlinreddit.fragments.BookmarksFragment
import com.example.zen.kotlinreddit.fragments.MessageFragment
import com.example.zen.kotlinreddit.fragments.ParallaxAdapter
import com.hkm.ezwebview.Util.Fx9C
import com.hkm.ezwebview.webviewclients.HClient
import com.squareup.picasso.Picasso
import com.yydcdut.rxmarkdown.RxMarkdown
import com.yydcdut.rxmarkdown.factory.TextFactory
import kotlinx.android.synthetic.main.app_bar_posts.*
import kotlinx.android.synthetic.main.header.*
import kotlinx.android.synthetic.main.main.*
import kotlinx.android.synthetic.main.posts.*
import kotlinx.android.synthetic.main.recycler.*
import rx.Observable
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription
import java.util.*

class CommentsActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
	var headerView: View? = null
	val tHeader = THeader().getTableName()
	val tComment = TComment().getTableName()
	val getBestComments = Observable.fromCallable { Reddit.getComments(url, parent) }
	val getNewComments = Observable.fromCallable { Reddit.getComments("$url?sort=new", parent) }
	val getControversialComments = Observable.fromCallable { Reddit.getComments("$url?sort=controversial", parent) }
	val getOldComments = Observable.fromCallable { Reddit.getComments("$url?sort=old", parent) }
	val getQAComments = Observable.fromCallable { Reddit.getComments("$url?sort=qa", parent) }
	var subs = CompositeSubscription()

	val url: String by lazy {
		var ret = intent.getStringExtra("url")
		if (ret == null) ret = "${Reddit.REDDIT_FRONT}${Uri.parse(intent.dataString).path}.json"
		ret
	}
	val parent: String by lazy {
		var ret = intent.getStringExtra("parent")
		if (ret == null) ret = Uri.parse(intent.dataString).pathSegments[3]
		ret
	}

	val clearObs = Observable.fromCallable {
		App.sdb.delete(tComment, null)
		App.sdb.delete(tHeader, "bookmarked != ?", "1")
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.comments)
		headerView = layoutInflater.inflate(R.layout.header, content, false)

		setSupportActionBar(postsToolbar)
		supportActionBar?.setDisplayShowTitleEnabled(false)

		val test = ArrayList<TComment>()
		val lm = LinearLayoutManager(this)
		val adapter = ParallaxAdapter(this, test)
		adapter.setParallaxHeader(headerView, rv)

		rv.setHasFixedSize(true)
		rv.layoutManager = lm
		rv.adapter = adapter

		subs.add(Observable.fromCallable { Reddit.getComments(url, parent, 200) }
			.subscribeOn(Schedulers.newThread())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe())

		subs.add(App.sdb.createQuery(tComment, "select * from $tComment where parent = ?", parent)
			.mapToList(TComment.MAPPER)
			.subscribeOn(Schedulers.newThread())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(adapter))

		subs.add(App.sdb.createQuery(tHeader, "select * from $tHeader where id = ? limit 1", parent)
			.mapToOne(THeader.MAPPER)
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe {
				if (txtCommentHeaderTitle != null) {
					commentProgress.visibility = View.GONE

					txtCommentHeaderTitle.text = it.title
					txtCommentHeaderAuthor.text = it.author
					adapter.originalAuthor = it.author

					RxMarkdown.with(Html.fromHtml(it.selftext).toString(), this).config(App.rxMdConfig).factory(TextFactory.create()).intoObservable().subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : Subscriber<CharSequence>() {
						override fun onCompleted() {
						}

						override fun onError(e: Throwable) {
						}

						override fun onNext(charSequence: CharSequence) {
							txtCommentHeaderSelfText.setText(charSequence, TextView.BufferType.SPANNABLE)
						}
					})

					txtCommentHeaderTitle.setOnClickListener { click ->
						val uri = Uri.parse(it.url)
						Log.d("host", "host: ${uri.host}")
						if(!uri.host.equals("www.reddit.com")) startActivity(Intent(Intent.ACTION_VIEW, uri))
					}

					if (it.preview == null) {
						frameCommentHeader.visibility = View.GONE
					} else {
						imgCommentHeaderPreview.visibility = View.VISIBLE
						Picasso.with(this)
							.load(it.preview)
							.fit()
							.centerCrop()
							.into(imgCommentHeaderPreview)
					}

					if (it.mp4 != null) {
						mp4Player.visibility = View.VISIBLE
						mp4Player.setUp(it.mp4, it.title)
						mp4Player.setLoop(true)

						Picasso.with(this).load(it.preview)
							.fit().centerCrop()
							.into(mp4Player.thumbImageView)
					}

					if (it.embed != null) {
						val iframe = Html.fromHtml(it.embed).toString()
						Fx9C.setup_web_video(this, framevideoplayer, videoplayer, progressloadingbarpx, iframe,
							object : HClient.Callback {
								override fun overridedefaultlogic(url: String, activity: Activity): Boolean {
									return true
								}

								override fun retrieveCookie(s: String) {

								}
							}, null)
					}
				}

			})
	}

	override fun onNavigationItemSelected(item: MenuItem): Boolean {

		when (item.itemId) {
			R.id.nav_bookmarks -> supportFragmentManager.beginTransaction().replace(R.id.contentFrame, BookmarksFragment(), BookmarksFragment.TAG).addToBackStack(BookmarksFragment.TAG).commit()
			R.id.nav_messages -> supportFragmentManager.beginTransaction().replace(R.id.contentFrame, MessageFragment(), MessageFragment.TAG).addToBackStack(MessageFragment.TAG).commit()
		}

		drawer_layout.closeDrawer(GravityCompat.START)
		return true
	}
}