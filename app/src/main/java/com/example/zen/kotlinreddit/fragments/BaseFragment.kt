package com.example.zen.kotlinreddit.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.app_bar_posts.*
import rx.subscriptions.CompositeSubscription

abstract class BaseFragment : Fragment() {
	var subs: CompositeSubscription = CompositeSubscription()
	open val layout = 0

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		subs = CompositeSubscription()
		return inflater.inflate(layout, container, false)
	}

	override fun onDestroyView() {
		subs.unsubscribe()
		super.onDestroyView()
	}

	fun setTitle(title: String) {
		activity.toolbar_title.text = title
	}
}