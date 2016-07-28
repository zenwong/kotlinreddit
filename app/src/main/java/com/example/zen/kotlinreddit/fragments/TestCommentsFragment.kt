package com.example.zen.kotlinreddit.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.text.format.DateUtils
import android.util.Log
import android.view.*
import android.widget.TextView
import com.commonsware.cwac.anddown.AndDown
import com.example.zen.kotlinreddit.*
import com.hkm.ezwebview.Util.Fx9C
import com.hkm.ezwebview.webviewclients.HClient
import com.joanzapata.iconify.IconDrawable
import com.joanzapata.iconify.fonts.FontAwesomeIcons
import com.poliveira.parallaxrecyclerview.ParallaxRecyclerAdapter
import com.squareup.picasso.Picasso
import com.yydcdut.rxmarkdown.RxMarkdown
import com.yydcdut.rxmarkdown.factory.TextFactory
import kotlinx.android.synthetic.main.header.*
import kotlinx.android.synthetic.main.recycler.*
import rx.Observable
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Action1
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription
import java.util.*

class TestCommentsFragment : BaseFragment() {
	var headerView: View? = null
	val tHeader = THeader().getTableName()
	val tComment = TComment().getTableName()
	override val layout = R.layout.comments
	var parent = ""
	var url = ""
	val getBestComments = Observable.fromCallable { Reddit.parseComments(url, parent) }
	val getNewComments  = Observable.fromCallable { Reddit.parseComments("$url?sort=new", parent) }
	val getControversialComments = Observable.fromCallable { Reddit.parseComments("$url?sort=controversial", parent) }
	val getOldComments  = Observable.fromCallable { Reddit.parseComments("$url?sort=old", parent) }
	val getQAComments  = Observable.fromCallable { Reddit.parseComments("$url?sort=qa", parent) }
	val md = AndDown()

	val clearObs = Observable.fromCallable {
		App.sdb.delete(tComment, null)
		App.sdb.delete(tHeader, "bookmarked != ?", "1")
	}

	companion object {
		const val TAG = "CommentsFragment"
		fun newInstance(url: String, parent: String): TestCommentsFragment {
			val frag = TestCommentsFragment()
			val bundle = Bundle()
			bundle.putString("parent", parent)
			bundle.putString("url", url)
			frag.arguments = bundle
			return frag
		}
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		retainInstance = true
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		setHasOptionsMenu(true)
		url = arguments.getString("url")
		parent = arguments.getString("parent")

		Log.d("DDDD", "url: $url parent: $parent")

		//val adapter = CommentAdapter(context)

		var test = ArrayList<TComment>()
		val lm = LinearLayoutManager(context)
		val adapter = ParallaxAdapter(context, test)
		adapter.setParallaxHeader(headerView, rv)

		rv.setHasFixedSize(true)
		rv.layoutManager = lm
		rv.adapter = adapter

		val source = "https://i.redditmedia.com/e8S5WZkcryD1WRc07ngpE8C_AkKdfxdSpHFlyL05uCM.gif?fm=jpg&amp;s=cb37aac6b70c9777cb0e9cc80111b515".replace("amp;", "")
		var mp4: String? = null

		subs.add(Observable.fromCallable { Reddit.parseComments(url, parent) }
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
				if(txtCommentHeaderTitle != null) {
					commentProgress.visibility = View.GONE

					txtCommentHeaderTitle.text = it.title
					txtCommentHeaderAuthor.text = it.author
					adapter.originalAuthor = it.author
					//txtCommentHeaderSelfText.text = Html.fromHtml(md.markdownToHtml(it.selftext))

					RxMarkdown.with(it.selftext, context).config(App.rxMdConfig).factory(TextFactory.create()).intoObservable().subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : Subscriber<CharSequence>() {
						override fun onCompleted() {
						}

						override fun onError(e: Throwable) {
						}

						override fun onNext(charSequence: CharSequence) {
							txtCommentHeaderSelfText.setText(charSequence, TextView.BufferType.SPANNABLE)
						}
					})

					txtCommentHeaderTitle.setOnClickListener { click ->
						startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it.url)))
					}

					if(it.preview == null) {
						frameCommentHeader.visibility = View.GONE
					} else {
						imgCommentHeaderPreview.visibility = View.VISIBLE
						Picasso.with(context)
							.load(it.preview)
							.fit()
							.centerCrop()
							.into(imgCommentHeaderPreview)
					}

					//mp4 = "https://g.redditmedia.com/LL25GBmeVYQRq1czEHcphmMD0p9F935iyNXL6ITHhpA.gif?fit=crop&crop=faces%2Centropy&arh=2&w=108&fm=mp4&mp4-fragmented=false&s=f483ae822896d4f08c6ec501a82693d8"
					//mp4 = "https://g.redditmedia.com/ue0DjQCfHnTn7yXpiol3qTtZGAyTO1ma4OS07c5TvqQ.gif?fit=crop&crop=faces%2Centropy&arh=2&w=320&fm=mp4&mp4-fragmented=false&s=4f251ec415e6c57c40a4d0061deae4ee"
					if(it.mp4 != null) {
						mp4Player.visibility = View.VISIBLE
						mp4Player.setUp(it.mp4, it.title)
						mp4Player.setLoop(true)

						Picasso.with(context).load(it.preview)
							.fit().centerCrop()
							.into(mp4Player.thumbImageView)
					}

					if(it.embed != null) {
						val iframe = Html.fromHtml(it.embed).toString()
						Fx9C.setup_web_video(this, framevideoplayer, videoplayer,	progressloadingbarpx,	iframe,
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

	override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
		inflater.inflate(R.menu.comments, menu)
		menu.findItem(R.id.action_save).icon = IconDrawable(context, FontAwesomeIcons.fa_bookmark).colorRes(R.color.white).actionBarSize()
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		when (item.itemId) {
			R.id.action_save -> {
				App.sdb.execute("update $tHeader set bookmarked = 1 where id = '$parent'")
				return true
			}
			R.id.action_best -> {
				subs.add(Observable.concat(clearObs, getBestComments).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe())
				return true
			}
			R.id.action_new -> {
				subs.add(Observable.concat(clearObs, getNewComments).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe())
				return true
			}
			R.id.action_author -> {
				return true
			}
			R.id.action_controversial -> {
				subs.add(Observable.concat(clearObs, getControversialComments).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe())
				return true
			}
			R.id.action_old -> {
				subs.add(Observable.concat(clearObs, getOldComments).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe())
				return true
			}
			R.id.action_qa -> {
				subs.add(Observable.concat(clearObs, getQAComments).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe())
				return true
			}
		}

		return super.onOptionsItemSelected(item)
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		subs = CompositeSubscription()
		Log.d("DDDD", "oncreateView")
		headerView = inflater.inflate(R.layout.header, container, false)
		return inflater.inflate(layout, container, false)
	}


	override fun onPause() {
		super.onPause()
		subs.unsubscribe()
		Fx9C.clearVideo(framevideoplayer, videoplayer)
		Fx9C.killWebView(videoplayer)
	}
}

class ParallaxAdapter(val context: Context, list: List<TComment>?) : ParallaxRecyclerAdapter<TComment>(list), Action1<List<TComment>> {
	val now = System.currentTimeMillis()
	val md = AndDown()
	var originalAuthor: String? = null
	val color = Color.parseColor("#FF4081")

	override fun call(t: List<TComment>) {
		data = t
		notifyDataSetChanged()
	}

	override fun getItemCountImpl(p0: ParallaxRecyclerAdapter<TComment>?): Int {
		return data.size
	}

	override fun onBindViewHolderImpl(vh: RecyclerView.ViewHolder, adapter: ParallaxRecyclerAdapter<TComment>, idx: Int) {
		val holder: CommentsViewHolder = vh as CommentsViewHolder
		holder.txtAuthor.text = data[idx].author
		if(data[idx].author == originalAuthor) holder.txtAuthor.setTextColor(color)
		//holder.txtBody.text = Html.fromHtml(md.markdownToHtml(data[idx].body))

		RxMarkdown.with(data[idx].body, context).config(App.rxMdConfig).factory(TextFactory.create()).intoObservable().subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : Subscriber<CharSequence>() {
			override fun onCompleted() {
			}

			override fun onError(e: Throwable) {
			}

			override fun onNext(charSequence: CharSequence) {
				holder.txtBody.setText(charSequence, TextView.BufferType.SPANNABLE)
			}
		})

		holder.txtCreated.text = DateUtils.getRelativeTimeSpanString(data[idx].created * 1000L, now, DateUtils.MINUTE_IN_MILLIS)
	}

	override fun onCreateViewHolderImpl(parent: ViewGroup, p1: ParallaxRecyclerAdapter<TComment>?, p2: Int): RecyclerView.ViewHolder {
		return CommentsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_comment, parent, false))
	}

}