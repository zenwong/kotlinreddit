package com.example.zen.kotlinreddit

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.zen.kotlinreddit.models.AccessToken
import com.example.zen.kotlinreddit.models.RedditPost
import com.squareup.leakcanary.LeakCanary
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.*

const val TAG = "com.example.zen.kotlinreddit"

class App : Application() {

	companion object {
		var access: String? = null
		var db: DB? = null
	}

	override fun onCreate() {
		LeakCanary.install(this)
		db = DB(this)
		EventBus.getDefault().register(this)
		access = getSharedPreferences(TAG, Context.MODE_PRIVATE).getString("ACCESS_TOKEN", null)
	}

	@Subscribe(threadMode = ThreadMode.MAIN)
	fun onAccessToken(access: AccessToken) {
		Log.d("EVENTBUS", access.token)
		editPreferences {
			editablePreferences ->
			editablePreferences.putString("ACCESS_TOKEN", access.token)
		}
	}

	@Subscribe(threadMode = ThreadMode.MAIN)
	fun onPosts(posts: ArrayList<RedditPost>) {
		db?.insertPosts(posts)

		db?.let {
			println("getting posts from sqlite")
			it.getPosts().forEach {
				println(it.rid)
			}
		}
	}

}

inline fun Context.editPreferences(preferenceFileName: String = TAG, block: (SharedPreferences.Editor) -> Unit) {
	val editablePreferences = getSharedPreferences(preferenceFileName, Context.MODE_PRIVATE).edit()
	block(editablePreferences)
	editablePreferences.commit()
}