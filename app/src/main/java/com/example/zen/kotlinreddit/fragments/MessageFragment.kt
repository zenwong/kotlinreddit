package com.example.zen.kotlinreddit.fragments

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.zen.kotlinreddit.App
import com.example.zen.kotlinreddit.R
import com.example.zen.kotlinreddit.Reddit
import com.example.zen.kotlinreddit.TMessage
import com.yydcdut.rxmarkdown.RxMarkdown
import com.yydcdut.rxmarkdown.factory.TextFactory
import kotlinx.android.synthetic.main.recycler.*
import kotlinx.android.synthetic.main.row_message.view.*
import rx.Observable
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Action1
import rx.schedulers.Schedulers
import java.util.*

class MessageFragment : BaseFragment() {
	override val layout = R.layout.front_page
	val table = TMessage().getTableName()
	var adapter: MessageAdapter? = null

	companion object {
		const val TAG = "MessageFragment"
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		adapter = MessageAdapter(context)
		subs.add(Observable.fromCallable { Reddit.getInbox() }.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe())
		subs.add(App.sdb.createQuery("$table", "select * from $table").mapToList(TMessage.MAPPER).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(adapter))
	}

	override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		val layoutManager = LinearLayoutManager(context)
		layoutManager.orientation = LinearLayoutManager.VERTICAL

		rv.setHasFixedSize(true)
		rv.layoutManager = layoutManager
		rv.adapter = adapter
	}
}

class MessageAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), Action1<List<TMessage>> {
	val now = System.currentTimeMillis()
	var data: List<TMessage> = ArrayList()

	override fun call(t: List<TMessage>) {
		data = t
		notifyDataSetChanged()
	}

	override fun getItemCount(): Int {
		return data.size
	}

	override fun onBindViewHolder(holder: RecyclerView.ViewHolder, idx: Int) {
		holder as MessageViewHolder
		holder.txtTitle.text = data[idx].title

		RxMarkdown.with(Html.fromHtml(data[idx].body).toString(), context).config(App.rxMdConfig).factory(TextFactory.create()).intoObservable().subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : Subscriber<CharSequence>() {
			override fun onCompleted() {
			}

			override fun onError(e: Throwable) {
			}

			override fun onNext(charSequence: CharSequence) {
				holder.txtBody.setText(charSequence, TextView.BufferType.SPANNABLE)
			}
		})


		holder.txtAuthor.text = "${data[idx].author}  (${data[idx].subreddit})"
		holder.txtCreated.text = DateUtils.getRelativeTimeSpanString(data[idx].created * 1000L, now, DateUtils.MINUTE_IN_MILLIS)
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
		return MessageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_message, parent, false))
	}

	inner class MessageViewHolder(iv: View) : RecyclerView.ViewHolder(iv) {
		val txtBody = iv.txtMessageBody
		val txtCreated = iv.txtMessageCreated
		val txtAuthor = iv.txtMessageAuthor
		val txtTitle = iv.txtMessageTitle
	}

}