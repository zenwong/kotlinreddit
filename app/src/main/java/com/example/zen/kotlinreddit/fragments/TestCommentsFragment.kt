package com.example.zen.kotlinreddit.fragments

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.text.format.DateUtils
import android.view.*
import com.example.zen.kotlinreddit.*
import com.joanzapata.iconify.IconDrawable
import com.joanzapata.iconify.fonts.FontAwesomeIcons
import com.poliveira.parallaxrecyclerview.ParallaxRecyclerAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.header.*
import kotlinx.android.synthetic.main.recycler.*
import rx.Observable
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

		//val adapter = CommentAdapter(context)

		var test = ArrayList<TComment>()
		val lm = LinearLayoutManager(context)
		val adapter = ParallaxAdapter(context, test)
		adapter.setParallaxHeader(headerView, rv)

		rv.setHasFixedSize(true)
		rv.layoutManager = lm
		rv.adapter = adapter

		val source = "https://i.redditmedia.com/e8S5WZkcryD1WRc07ngpE8C_AkKdfxdSpHFlyL05uCM.gif?fm=jpg&amp;s=cb37aac6b70c9777cb0e9cc80111b515".replace("amp;", "")

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
			.subscribeOn(Schedulers.newThread())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe{
				txtCommentHeaderTitle.text = it.title
				txtCommentHeaderAuthor.text = it.author
				txtCommentHeaderSelfText.text = it.selftext

				if(it.preview == null) {
					frameCommentHeader.visibility = View.GONE
				} else {
					Picasso.with(context)
						.load(it.preview)
						.fit()
						.centerCrop()
						.into(imgCommentHeaderPreview)
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
		headerView = inflater.inflate(R.layout.header, container, false)
		return inflater.inflate(layout, container, false)
	}
}

class ParallaxAdapter(val context: Context, list: List<TComment>?) : ParallaxRecyclerAdapter<TComment>(list), Action1<List<TComment>> {
	val now = System.currentTimeMillis()

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
		//holder.txtBody.loadMarkdown(data[idx].body)
		holder.txtBody.text = Html.fromHtml(data[idx].body)

		holder.txtCreated.text = DateUtils.getRelativeTimeSpanString(data[idx].created * 1000L, now, DateUtils.MINUTE_IN_MILLIS)
	}

	override fun onCreateViewHolderImpl(parent: ViewGroup, p1: ParallaxRecyclerAdapter<TComment>?, p2: Int): RecyclerView.ViewHolder {
		return CommentsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_comment, parent, false))
	}

}