package com.example.zen.kotlinreddit.activities

import com.example.zen.kotlinreddit.activities.BaseActivity
import com.example.zen.kotlinreddit.R
import com.example.zen.kotlinreddit.fragments.SubredditFragment

class SubredditActivity: BaseActivity() {
	override fun init() {
		val subreddit = intent.data.pathSegments[1]
		println(intent.data)
		supportFragmentManager.beginTransaction().replace(R.id.contentFrame, SubredditFragment.newInstance(subreddit), SubredditFragment.TAG).commit()
	}

}