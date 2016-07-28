package com.example.zen.kotlinreddit

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.zen.kotlinreddit.fragments.BrowserFragment
import kotlinx.android.synthetic.main.app_bar_posts.*

class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.posts)

		setSupportActionBar(postsToolbar)
		supportActionBar?.setDisplayShowTitleEnabled(false)

		if(App.accessToken != null) {
			startActivity(Intent(this, PostsActivity::class.java))
			finish()
		} else {
			Log.d("TEST", "loading browser fragment")
			supportFragmentManager.beginTransaction().replace(R.id.contentFrame, BrowserFragment(), BrowserFragment.TAG).commit()
		}
	}

//	override fun onResume() {
//		super.onResume()
//		EventBus.getDefault().register(this)
//	}
//
//	override fun onPause() {
//		EventBus.getDefault().unregister(this)
//		super.onPause()
//	}


}
