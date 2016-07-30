package com.example.zen.kotlinreddit.activities

import android.os.Bundle
import com.example.zen.kotlinreddit.R
import com.example.zen.kotlinreddit.fragments.SubredditFragment

class SubredditActivity: BaseActivity() {
	override fun init(savedInstanceState: Bundle?) {
		val subreddit = intent.data.pathSegments[1]

		if(savedInstanceState != null) {
			val frag = supportFragmentManager.findFragmentByTag(SubredditFragment.TAG)
			supportFragmentManager.beginTransaction().replace(R.id.contentFrame, frag, SubredditFragment.TAG).commit()
		} else {
			supportFragmentManager.beginTransaction().replace(R.id.contentFrame, SubredditFragment.newInstance(subreddit), SubredditFragment.TAG).commit()
		}

	}

}