package com.example.zen.kotlinreddit

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.support.v4.app.Fragment
import com.example.zen.kotlinreddit.activities.PostsActivity
import com.example.zen.kotlinreddit.models.AccessToken
import com.example.zen.kotlinreddit.models.CommentsRequest
import com.example.zen.kotlinreddit.models.RefreshToken
import com.joanzapata.iconify.Iconify
import com.joanzapata.iconify.fonts.FontAwesomeModule
import com.squareup.sqlbrite.BriteDatabase
import com.squareup.sqlbrite.SqlBrite
import com.yydcdut.rxmarkdown.RxMDConfiguration
import com.yydcdut.rxmarkdown.loader.DefaultLoader
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription
import java.io.File

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
		lateinit var cdir: File
		lateinit var rxMdConfig: RxMDConfiguration
	}

	override fun onCreate() {
		cdir = cacheDir
		sqlBrite = SqlBrite.create()
		sdb = sqlBrite.wrapDatabaseHelper(DB(this), Schedulers.io())
		//sdb.setLoggingEnabled(true)

		Reddit.init(this, cacheDir)
		Iconify.with(FontAwesomeModule())
		db = DB(this)
		db.writableDatabase
		EventBus.getDefault().register(this)
		accessToken = getSharedPreferences(TAG, Context.MODE_PRIVATE).getString("ACCESS_TOKEN", null)
		refreshToken = getSharedPreferences(TAG, Context.MODE_PRIVATE).getString("REFRESH_TOKEN", null)

		rxMdConfig = RxMDConfiguration.Builder(this).setDefaultImageSize(100, 100)//default image width & height
			.setBlockQuotesColor(Color.LTGRAY)//default color of block quotes
			.setHeader1RelativeSize(1.6f)//default relative size of header1
			.setHeader2RelativeSize(1.5f)//default relative size of header2
			.setHeader3RelativeSize(1.4f)//default relative size of header3
			.setHeader4RelativeSize(1.3f)//default relative size of header4
			.setHeader5RelativeSize(1.2f)//default relative size of header5
			.setHeader6RelativeSize(1.1f)//default relative size of header6
			.setHorizontalRulesColor(Color.LTGRAY)//default color of horizontal rules's background
			.setInlineCodeBgColor(Color.LTGRAY)//default color of inline code's background
			.setCodeBgColor(Color.LTGRAY)//default color of code's background
			.setTodoColor(Color.DKGRAY)//default color of todo
			.setTodoDoneColor(Color.DKGRAY)//default color of done
			.setUnOrderListColor(Color.BLACK)//default color of unorder list
			.setLinkColor(Color.RED)//default color of link text
			.setLinkUnderline(true)//default value of whether displays link underline
			.setRxMDImageLoader(DefaultLoader(this))//default image loader
			.build()
	}

	override fun onTerminate() {
		super.onTerminate()
		subs.unsubscribe()
	}

	@Subscribe(threadMode = ThreadMode.MAIN)
	fun onAccessToken(access: AccessToken) {
		accessToken = access.token
		editPreferences {	it.putString("ACCESS_TOKEN", access.token) }

		startActivity(Intent(this, PostsActivity::class.java))
	}

	@Subscribe(threadMode = ThreadMode.MAIN)
	fun onRefreshToken(refresh: RefreshToken) {
		refreshToken = refresh.token
		editPreferences {	it.putString("REFRESH_TOKEN", refresh.token)	}
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