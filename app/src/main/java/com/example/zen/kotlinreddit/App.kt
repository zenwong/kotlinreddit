package com.example.zen.kotlinreddit

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.zen.kotlinreddit.models.AccessToken
import com.example.zen.kotlinreddit.models.RedditPost
import com.example.zen.kotlinreddit.models.RefreshToken
import com.squareup.sqlbrite.SqlBrite
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*

const val TAG = "com.example.zen.kotlinreddit"

class App : Application() {

	companion object {
		var accessToken: String? = null
		var refreshToken: String? = null
		lateinit var db: DB
		lateinit var sqlBrite : SqlBrite
	}

	override fun onCreate() {
		sqlBrite = SqlBrite.create()
		Reddit.init(this, cacheDir)
		db = DB(this)
		db.writableDatabase
		EventBus.getDefault().register(this)
		//accessToken = getSharedPreferences(TAG, Context.MODE_PRIVATE).getString("ACCESS_TOKEN", null)
		//refreshToken = getSharedPreferences(TAG, Context.MODE_PRIVATE).getString("REFRESH_TOKEN", null)

//		Observable.fromCallable { db.getPosts() }
//			.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
//			.subscribe {
//				it?.forEach {
//					println(it.url)
//				}
//			}
	}

	@Subscribe(threadMode = ThreadMode.MAIN)
	fun onAccessToken(access: AccessToken) {
		Log.d("EVENTBUS", "onAccessToken: ${access.token}")
		accessToken = access.token
		editPreferences {
			editablePreferences ->
			editablePreferences.putString("ACCESS_TOKEN", access.token)
		}
	}

	@Subscribe(threadMode = ThreadMode.MAIN)
	fun onRefreshToken(refresh: RefreshToken) {
		Log.d("EVENTBUS", "onRefreshToken: ${refresh.token}")
		refreshToken = refresh.token
		editPreferences {
			editablePreferences ->
			editablePreferences.putString("REFRESH_TOKEN", refresh.token)
		}
	}

	@Subscribe(threadMode = ThreadMode.MAIN)
	fun onPosts(posts: ArrayList<RedditPost>) {
		Observable.fromCallable { db.insertPosts(posts) }
			.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
			.subscribe {
				println("onPOSTS after subscribe")
			}

	}

}

inline fun Context.editPreferences(preferenceFileName: String = TAG, block: (SharedPreferences.Editor) -> Unit) {
	val editablePreferences = getSharedPreferences(preferenceFileName, Context.MODE_PRIVATE).edit()
	block(editablePreferences)
	editablePreferences.commit()
}