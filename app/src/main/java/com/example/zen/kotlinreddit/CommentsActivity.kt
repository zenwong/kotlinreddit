package com.example.zen.kotlinreddit

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.Html
import android.view.View
import android.view.WindowManager
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.zen.kotlinreddit.fragments.CommentsAdapter
import com.example.zen.kotlinreddit.models.Comment
import com.example.zen.kotlinreddit.models.CommentHeader
import com.example.zen.kotlinreddit.views.VideoEnabledWebChromeClient
import com.example.zen.kotlinreddit.views.WEBVIEWCSS
import kotlinx.android.synthetic.main.comments_new.*
import kotlinx.android.synthetic.main.main.*
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription

class CommentsActivity : AppCompatActivity() {
	var subscriptions = CompositeSubscription()
	val table = "comments"
	val select = "select * from comments where parent = ?"
	val layout = LinearLayoutManager(this)
	val link: String by lazy {
		var ret = intent.getStringExtra("url")
		if (ret == null) ret = "${Reddit.REDDIT_FRONT}${Uri.parse(intent.dataString).path}.json"
		ret
	}
	val parent: String by lazy {
		var ret = intent.getStringExtra("parent")
		if (ret == null) ret = Uri.parse(intent.dataString).pathSegments[3]
		ret
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.comments_new)
		setSupportActionBar(toolbar)
		val adapter = CommentsAdapter(this)
		clist.setHasFixedSize(true)
		clist.layoutManager = layout
		clist.adapter = adapter

		subscriptions.add(Observable.fromCallable { Reddit.parseComments(link, parent) }
			.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe())

		subscriptions.add(App.sdb.createQuery(table, select, parent)
			.mapToList(Comment.MAPPER)
			.subscribeOn(Schedulers.newThread())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(adapter))

		subscriptions.add(App.sdb.createQuery("comment_headers", "select * from comment_headers where parent = ? limit 1", parent)
			.mapToOne(CommentHeader.MAPPER)
			.subscribeOn(Schedulers.newThread())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe {
				val youtube = Html.fromHtml("&lt;iframe width=\"600\" height=\"338\" src=\"https://www.youtube.com/embed/7D9GE3-o54o?feature=oembed\" frameborder=\"0\" allowfullscreen&gt;&lt;/iframe&gt;").toString()
				val vimeo = Html.fromHtml("&lt;iframe class=\"embedly-embed\" src=\"https://cdn.embedly.com/widgets/media.html?src=https%3A%2F%2Fplayer.vimeo.com%2Fvideo%2F173383757&amp;url=https%3A%2F%2Fvimeo.com%2F173383757&amp;image=http%3A%2F%2Fi.vimeocdn.com%2Fvideo%2F579856595_640.jpg&amp;key=2aa3c4d5f3de4f5b9120b660ad850dc9&amp;type=text%2Fhtml&amp;schema=vimeo\" width=\"600\" height=\"338\" scrolling=\"no\" frameborder=\"0\" allowfullscreen&gt;&lt;/iframe&gt;").toString()


				val loadingView = layoutInflater.inflate(R.layout.loading_video, null)
				val webChromeClient = object : VideoEnabledWebChromeClient(nonVideoLayout, videoLayout, loadingView, embedPlayer) {
					override fun onProgressChanged(view: WebView, progress: Int) {
					}
				}
				webChromeClient.setOnToggledFullscreen { fullscreen ->
					println("onFullScreen")
					if (fullscreen) {
						val attrs = window.attributes
						attrs.flags = attrs.flags or WindowManager.LayoutParams.FLAG_FULLSCREEN
						attrs.flags = attrs.flags or WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
						window.attributes = attrs
						if (android.os.Build.VERSION.SDK_INT >= 14) {
							window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LOW_PROFILE
						}
					} else {
						val attrs = window.attributes
						attrs.flags = attrs.flags and WindowManager.LayoutParams.FLAG_FULLSCREEN.inv()
						attrs.flags = attrs.flags and WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON.inv()
						window.attributes = attrs
						if (android.os.Build.VERSION.SDK_INT >= 14) {
							window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
						}
					}
				}

				embedPlayer.apply {
					setWebChromeClient(webChromeClient)
					setWebViewClient(WebViewClient())
					//settings.loadWithOverviewMode = true
					//settings.useWideViewPort = true
					settings.builtInZoomControls = true

					loadData("${WEBVIEWCSS.replace("___HEIGHT___", "220")}$youtube", "text/html", "UTF-8")
				}

			})

	}

	override fun onStop() {
		subscriptions.clear()
		embedPlayer.destroy()
		super.onStop()
	}
}