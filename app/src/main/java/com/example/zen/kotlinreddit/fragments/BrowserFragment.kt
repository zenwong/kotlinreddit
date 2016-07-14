package com.example.zen.kotlinreddit.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.zen.kotlinreddit.R
import com.example.zen.kotlinreddit.Reddit
import kotlinx.android.synthetic.main.browser.*

class BrowserFragment : Fragment() {
	companion object {
		const val TAG = "BrowserFragment"
	}

	override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		browser.loadUrl(Reddit.getAuthUrl())
		browser.setWebViewClient(object: WebViewClient() {
			override fun shouldOverrideUrlLoading(view: WebView?, url: String): Boolean {
				when {
					url.startsWith(Reddit.REDIRECT) -> Reddit.getAccessToken(url)
					else -> return false
				}
				return true
			}
		})
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return inflater.inflate(R.layout.browser, container, false)
	}

}