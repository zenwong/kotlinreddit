package com.example.zen.kotlinreddit.fragments

import android.os.Bundle
import android.support.v4.app.ListFragment

class RedditPostsFragment: ListFragment() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		println("inside RedditPostsFragment")
	}
}