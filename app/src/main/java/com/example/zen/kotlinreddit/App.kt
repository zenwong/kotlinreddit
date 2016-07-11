package com.example.zen.kotlinreddit

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v4.app.Fragment
import android.util.Log
import com.example.zen.kotlinreddit.models.AccessToken
import com.example.zen.kotlinreddit.models.CommentsRequest
import com.example.zen.kotlinreddit.models.RefreshToken
import com.joanzapata.iconify.Iconify
import com.joanzapata.iconify.fonts.FontAwesomeModule
import com.squareup.sqlbrite.BriteDatabase
import com.squareup.sqlbrite.SqlBrite
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription

const val TAG = "com.example.zen.kotlinreddit"

class App : Application() {
	val subs = CompositeSubscription()

	companion object {
		var accessToken: String? = null
		var refreshToken: String? = null
		var postAfter: String? = null
		lateinit var db: DB
		lateinit var sqlBrite : SqlBrite
		lateinit var sdb: BriteDatabase
		//lateinit var store: StoreProvider
	}

	override fun onCreate() {
		sqlBrite = SqlBrite.create()
		sdb = sqlBrite.wrapDatabaseHelper(DB(this), Schedulers.io())
		//sdb.setLoggingEnabled(true)
		//store = StoreProvider.withContext(this).inDir("rxStore").using(JacksonConverter())

		Reddit.init(this, cacheDir)
		Iconify.with(FontAwesomeModule())
		db = DB(this)
		db.writableDatabase
		EventBus.getDefault().register(this)
		accessToken = getSharedPreferences(TAG, Context.MODE_PRIVATE).getString("ACCESS_TOKEN", null)
		refreshToken = getSharedPreferences(TAG, Context.MODE_PRIVATE).getString("REFRESH_TOKEN", null)
	}

	override fun onTerminate() {
		super.onTerminate()
		subs.unsubscribe()
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


}

inline fun Context.editPreferences(preferenceFileName: String = TAG, block: (SharedPreferences.Editor) -> Unit) {
	val editablePreferences = getSharedPreferences(preferenceFileName, Context.MODE_PRIVATE).edit()
	block(editablePreferences)
	editablePreferences.commit()
}

inline fun <reified T : Fragment> Fragment.navigate(req: CommentsRequest) {
	val intent = Intent(this.context, T::class.java)
	intent.putExtra("url", req.url)
	intent.putExtra("id", req.parent)
	this.context.startActivity(intent)
}
