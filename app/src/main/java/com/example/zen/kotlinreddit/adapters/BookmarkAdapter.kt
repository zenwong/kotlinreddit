package com.example.zen.kotlinreddit.adapters

import android.app.Activity
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.zen.kotlinreddit.App
import com.example.zen.kotlinreddit.R
import com.example.zen.kotlinreddit.Reddit
import com.example.zen.kotlinreddit.THeader
import com.example.zen.kotlinreddit.models.CommentsRequest
import com.hkm.ezwebview.Util.Fx9C
import com.hkm.ezwebview.webviewclients.HClient
import com.squareup.picasso.Picasso
import com.yydcdut.rxmarkdown.RxMarkdown
import com.yydcdut.rxmarkdown.factory.TextFactory
import kotlinx.android.synthetic.main.header.view.*
import org.greenrobot.eventbus.EventBus
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Action1
import rx.schedulers.Schedulers
import java.util.*

class BookmarkAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), Action1<List<THeader>> {
	var data = ArrayList<THeader>()

	override fun getItemCount(): Int {
		return data.size
	}

	override fun onBindViewHolder(holder: RecyclerView.ViewHolder, idx: Int) {
		holder as BookmarkViewHolder
		//holder.commentProgress.visibility = View.GONE

		holder.txtTitle.text = data[idx].title
		holder.txtAuthor.text = data[idx].author

		RxMarkdown.with(Html.fromHtml(data[idx].selftext).toString(), context).config(App.rxMdConfig).factory(TextFactory.create()).intoObservable().subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : Subscriber<CharSequence>() {
			override fun onCompleted() {
			}

			override fun onError(e: Throwable) {
			}

			override fun onNext(charSequence: CharSequence) {
				holder.txtSelfText.setText(charSequence, TextView.BufferType.SPANNABLE)
			}
		})

		if (data[idx].preview == null) {
			holder.frameCommentHeader.visibility = View.GONE
		} else {
			holder.imgCommentHeaderPreview.visibility = View.VISIBLE
			Picasso.with(context)
				.load(data[idx].preview)
				.fit()
				.centerCrop()
				.into(holder.imgCommentHeaderPreview)
		}

		//mp4 = "https://g.redditmedia.com/LL25GBmeVYQRq1czEHcphmMD0p9F935iyNXL6ITHhpA.gif?fit=crop&crop=faces%2Centropy&arh=2&w=108&fm=mp4&mp4-fragmented=false&s=f483ae822896d4f08c6ec501a82693d8"
		//mp4 = "https://g.redditmedia.com/ue0DjQCfHnTn7yXpiol3qTtZGAyTO1ma4OS07c5TvqQ.gif?fit=crop&crop=faces%2Centropy&arh=2&w=320&fm=mp4&mp4-fragmented=false&s=4f251ec415e6c57c40a4d0061deae4ee"
		if (data[idx].mp4 != null) {
			holder.mp4Player.visibility = View.VISIBLE
			holder.mp4Player.setUp(data[idx].mp4, data[idx].title)
			holder.mp4Player.setLoop(true)

			Picasso.with(context).load(data[idx].preview)
				.fit().centerCrop()
				.into(holder.mp4Player.thumbImageView)
		}

		if (data[idx].embed != null) {
			val iframe = Html.fromHtml(data[idx].embed).toString()
			Fx9C.setup_web_video(this, holder.framevideoplayer, holder.videoplayer, holder.progressloadingbarpx, iframe,
				object : HClient.Callback {
					override fun overridedefaultlogic(url: String, activity: Activity): Boolean {
						return true
					}

					override fun retrieveCookie(s: String) {

					}
				}, null)
		}


	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
		return BookmarkViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.header, parent, false))
	}

	override fun call(t: List<THeader>?) {
		data = t as ArrayList<THeader>
		notifyDataSetChanged()
	}

	fun handleTxtComments(adapterPosition: Int) {
		val url = "${Reddit.REDDIT_FRONT}${data[adapterPosition].url}.json"
		val req = CommentsRequest(url, 0, data[adapterPosition].id!!)
		EventBus.getDefault().post(req)
	}

	inner class BookmarkViewHolder(iv: View) : RecyclerView.ViewHolder(iv) {
		val card = iv.headerCard
		val txtTitle = iv.txtCommentHeaderTitle
		val txtAuthor = iv.txtCommentHeaderAuthor
		val txtSelfText = iv.txtCommentHeaderSelfText
		val imgCommentHeaderPreview = iv.imgCommentHeaderPreview
		val mp4Player = iv.mp4Player
		val framevideoplayer = iv.framevideoplayer
		val videoplayer = iv.videoplayer
		val progressloadingbarpx = iv.progressloadingbarpx

		//val commentProgress = iv.commentProgress
		val frameCommentHeader = iv.frameCommentHeader

		init {
			card.setOnClickListener { handleTxtComments(adapterPosition) }
		}
	}

}
