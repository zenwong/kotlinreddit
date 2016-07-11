package com.example.zen.kotlinreddit

import com.example.zen.kotlinreddit.network.RedditOauthAuthenticator
import com.fasterxml.jackson.core.JsonFactory
import com.fasterxml.jackson.core.JsonToken
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.Request

object Api {
	val factory = JsonFactory()
	lateinit var client: OkHttpClient

	init {
		val cache = Cache(App.cdir, 1024L * 1024L * 100L)
		client = OkHttpClient.Builder().authenticator(RedditOauthAuthenticator()).cache(cache).build()
	}

	fun parsePosts(json: String) {
		val jp = factory.createParser(json)
		while (jp.nextToken() !== null) {
			if ("domain".equals(jp.currentName)) {
				jp.nextToken()
				val post = TPost()

				var thumbnail: String? = null
				var media: String? = null
				var preview: String? = null

				while (jp.nextToken() != JsonToken.END_OBJECT) {

					val name = jp.currentName
					when (name) {
						"media_embed" -> jp.skipChildren()
						"subreddit" -> post.subreddit = jp.nextTextValue()
						"secure_media" -> jp.skipChildren()
						"user_reports" -> jp.skipChildren()
						"id" -> post.id = jp.nextTextValue()
						"author" -> post.author = jp.nextTextValue()
						"media" -> {
							if (jp.nextToken() !== JsonToken.VALUE_NULL) {
								while (jp.nextToken() !== JsonToken.END_OBJECT) {
									val key = jp.currentName
									when (key) {
										"thumbnail_url" -> media = jp.nextTextValue()
									}
								}
								jp.nextToken()
								jp.nextToken()
								jp.nextToken()
							}
						}
						"score" -> post.score = jp.nextIntValue(0)
						"preview" -> {
							loop@ while (jp.nextToken() !== null) {
								val k = jp.currentName
								when (k) {
									"source" -> jp.skipChildren()
									"url" -> {
										jp.nextToken()
										preview = jp.valueAsString
										if (preview.contains("w=320")) {
											preview.replace("amp;", "")
										}
									}
									"variants" -> jp.skipChildren()
									"id" -> break@loop
								}
							}
							jp.nextToken()
							jp.nextToken()
							jp.nextToken()
							jp.nextToken()
						}
						"num_comments" -> post.comments = jp.nextIntValue(0)
						"thumbnail" -> thumbnail = jp.nextTextValue()
						"secure_media_embed" -> jp.skipChildren()
						"title" -> post.title = jp.nextTextValue()
						"created_utc" -> post.created = jp.getValueAsLong(0L)
						"mod_reports" -> jp.skipChildren()
					}
				}

				when (thumbnail) {
					"self", "default" -> {
					}
					else -> post.preview = thumbnail
				}

				media?.let {
					post.preview = media
				}

				preview?.let {
					post.preview = preview
				}

				App.snappy.put(post.id, post)
			}

			if ("after".equals(jp.currentName)) {
				App.postAfter = jp.nextTextValue()
			}
		}
	}

	fun getOrEmpty(url: String): String {
		val resp = client.newCall(Request.Builder().url(url).addHeader("Authorization", "Bearer ${App.accessToken}").build()).execute()
		if(resp.isSuccessful) {
			return resp.body().string()
		}
		return "{}"
	}

	fun getNewPosts() {
		parsePosts(getOrEmpty("https://oauth.reddit.com/new/"))
	}

	fun getHotPosts() {
		parsePosts(getOrEmpty("https://oauth.reddit.com/hot"))
	}
}