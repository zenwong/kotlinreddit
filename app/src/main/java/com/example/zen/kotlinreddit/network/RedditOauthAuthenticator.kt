package com.example.zen.kotlinreddit.network

import com.example.zen.kotlinreddit.Reddit
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class RedditOauthAuthenticator : Authenticator {
	override fun authenticate(route: Route, response: Response): Request {
		return response.request().newBuilder().header("Authorization", Reddit.refreshAccessToken()).build()
	}
}