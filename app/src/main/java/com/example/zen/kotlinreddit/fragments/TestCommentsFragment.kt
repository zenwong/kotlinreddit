package com.example.zen.kotlinreddit.fragments

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import com.example.zen.kotlinreddit.*
import com.example.zen.kotlinreddit.adapters.CommentAdapter
import com.joanzapata.iconify.IconDrawable
import com.joanzapata.iconify.fonts.FontAwesomeIcons
import kotlinx.android.synthetic.main.recycler.*
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class TestCommentsFragment : BaseFragment() {
	val tHeader = THeader().getTableName()
	val tComment = TComment().getTableName()
	val lm = LinearLayoutManager(context)
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
		fun newInstance(url: String, parent: String): TestCommentsFragment {
			val frag = TestCommentsFragment()
			val bundle = Bundle()
			bundle.putString("parent", parent)
			bundle.putString("url", url)
			frag.arguments = bundle
			return frag
		}
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		setHasOptionsMenu(true)
		url = arguments.getString("url")
		parent = arguments.getString("parent")

		val adapter = CommentAdapter(context)

		rv.setHasFixedSize(true)
		rv.layoutManager = lm
		rv.adapter = adapter

		subs.add(Observable.fromCallable { Reddit.parseComments(url, parent) }
			.subscribeOn(Schedulers.newThread())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe())

		subs.add(App.sdb.createQuery(tComment, "select * from $tComment where parent = ?", parent)
			.mapToList(TComment.MAPPER)
			.subscribeOn(Schedulers.newThread())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(adapter))
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
}