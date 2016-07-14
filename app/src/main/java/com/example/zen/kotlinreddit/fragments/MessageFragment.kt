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
import com.commonsware.cwac.anddown.AndDown
import com.example.zen.kotlinreddit.App
import com.example.zen.kotlinreddit.R
import com.example.zen.kotlinreddit.Reddit
import com.example.zen.kotlinreddit.TMessage
import com.example.zen.kotlinreddit.views.PreCachingLayoutManager
import kotlinx.android.synthetic.main.recycler.*
import kotlinx.android.synthetic.main.row_message.view.*
import rx.Observable
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

		val layoutManager = PreCachingLayoutManager(context)
		layoutManager.orientation = LinearLayoutManager.VERTICAL
		layoutManager.setExtraLayoutSpace(resources.displayMetrics.heightPixels)

		rv.setHasFixedSize(true)
		rv.layoutManager = layoutManager
		rv.adapter = adapter
	}
}

class MessageAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), Action1<List<TMessage>> {
	val now = System.currentTimeMillis()
	val md = AndDown()
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
		holder.txtBody.text = Html.fromHtml(md.markdownToHtml(data[idx].body))
		holder.txtAuthor.text = data[idx].author
		holder.txtCreated.text = DateUtils.getRelativeTimeSpanString(data[idx].created * 1000L, now, DateUtils.MINUTE_IN_MILLIS)
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
		return MessageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_message, parent, false))
	}

	inner class MessageViewHolder(iv: View) : RecyclerView.ViewHolder(iv) {
		val txtBody = iv.txtMessageBody
		val txtCreated = iv.txtMessageCreated
		val txtAuthor = iv.txtMessageAuthor
	}

}