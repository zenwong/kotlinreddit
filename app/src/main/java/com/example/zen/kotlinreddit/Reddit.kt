package com.example.zen.kotlinreddit

import android.content.Context
import android.net.Uri
import android.util.Base64
import com.example.zen.kotlinreddit.models.*
import com.example.zen.kotlinreddit.network.RedditOauthAuthenticator
import com.fasterxml.jackson.core.JsonFactory
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonToken
import okhttp3.*
import org.greenrobot.eventbus.EventBus
import java.io.File
import java.io.IOException

object Reddit {
	val CLIENTID = "f-A-UqH0oTkkeA"
	val REDIRECT = "http://zreddit"
	val REDDIT_AUTH_TOKEN = "https://ssl.reddit.com/api/v1/access_token"
	val REDDIT_FRONT = "https://oauth.reddit.com"
	val BASIC_AUTH = Base64.encodeToString("$CLIENTID:".toByteArray(), Base64.URL_SAFE or Base64.NO_WRAP)
	val jsonFactory = JsonFactory()
	lateinit var cache: Cache
	lateinit var client: OkHttpClient
	lateinit var ctx: Context

	fun init(context: Context, cacheDir: File) {
		ctx = context
		cache = Cache(cacheDir, 1024L * 1024L * 100L)
		client = OkHttpClient.Builder().authenticator(RedditOauthAuthenticator()).cache(cache).build()
	}

	fun getAuthUrl(clientid: String = CLIENTID, state: String = "NONCE", redirect: String = "http://zreddit", scope: String = "read identity privatemessages"): String {
		return "https://ssl.reddit.com/api/v1/authorize.compact?client_id=$clientid&response_type=code&state=$state&redirect_uri=$redirect&duration=permanent&scope=$scope"
	}

	fun getAccessToken(url: String) {
		val uri = Uri.parse(url)
		val error = uri.getQueryParameter("error")
		if (error !== null) {
			println(error)
		} else {
			val code = uri.getQueryParameter("code")
			val body = FormBody.Builder().add("code", code).add("redirect_uri", REDIRECT).add("grant_type", "authorization_code").build()
			val req = Request.Builder().url(REDDIT_AUTH_TOKEN).addHeader("Authorization", "Basic $BASIC_AUTH").post(body).build()

			client.newCall(req).enqueue(object : Callback {
				override fun onFailure(call: Call?, e: IOException?) {
				}

				override fun onResponse(call: Call?, response: Response) {
					val jp = jsonFactory.createParser(response.body().string())

					println(response.body().string())

					while (jp.nextToken() != JsonToken.END_OBJECT) {
						when (jp.currentName) {
							"access_token" -> {
								jp.nextToken()
								val access = jp.valueAsString
								App.accessToken = access
								EventBus.getDefault().post(AccessToken(access))
								EventBus.getDefault().post(Navigation(FRONT))
							}
							"refresh_token" -> {
								jp.nextToken()
								EventBus.getDefault().post(RefreshToken(jp.valueAsString))
							}
						}
					}
				}
			})
		}

	}

	fun refreshAccessToken(): String {
		val body = FormBody.Builder().add("grant_type", "refresh_token").add("refresh_token", App.refreshToken).build()
		val req = Request.Builder().url(REDDIT_AUTH_TOKEN).addHeader("Authorization", "Basic $BASIC_AUTH").post(body).build()
		val json = client.newCall(req).execute().body().string()
		val jp = jsonFactory.createParser(json)

		var accessToken = ""
		while (jp.nextToken() != JsonToken.END_OBJECT) {
			when (jp.currentName) {
				"access_token" -> {
					accessToken = jp.nextTextValue()
					App.accessToken = accessToken
				}
			}
		}
		return accessToken
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

	fun getSubredditPosts(subreddit: String) {
		parsePosts(getOrEmpty("https://oauth.reddit.com/r/$subreddit"))
	}

	fun getPostsAfter(limit: Int = 5) {
		parsePosts(getOrEmpty("$REDDIT_FRONT?limit=$limit&after=${App.postAfter}"))
	}

	fun parsePosts(json: String) {
		val jp = jsonFactory.createParser(json)
		val tr = App.sdb.newTransaction()
		val table = TPost().getTableName()
		try {
			while (jp.nextToken() !== null) {
				if ("domain".equals(jp.currentName)) {
					jp.nextToken()
					val post = TPost()
					var thumbnail: String? = null
					var media: String? = null
					var preview: String? = null

					//post.domain = jp.valueAsString

					while (jp.nextToken() != JsonToken.END_OBJECT) {

						val name = jp.currentName
						when (name) {
							"media_embed" -> jp.skipChildren()
							"subreddit" -> post.subreddit = jp.nextTextValue()
							"secure_media" -> jp.skipChildren()
							"user_reports" -> jp.skipChildren()
							"id" -> post.id = jp.nextTextValue()
							//"clicked" -> post.clicked = jp.nextBooleanValue()
							"author" -> post.author = jp.nextTextValue()
							"media" -> {
								if (jp.nextToken() !== JsonToken.VALUE_NULL) {
									while (jp.nextToken() !== JsonToken.END_OBJECT) {
										val key = jp.currentName
										when (key) {
											//"title" -> post.media_title = jp.nextTextValue()
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
											val local = jp.valueAsString
											if (local.contains("w=320")) {
												preview = local.replace("amp;", "")
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
							"permalink" -> post.permalink = jp.nextTextValue()
							//"url" -> post.url = jp.nextTextValue()
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

					App.sdb.insert("$table", post.getValues())
				}

				if ("after".equals(jp.currentName)) {
					App.postAfter = jp.nextTextValue()
					//println("AFTER: ${App.postAfter}")
				}
			}

			tr.markSuccessful()
		} finally {
			tr.end()
		}

	}

	fun normalizeCommentsUrl(url: String, limit: Int = 10) : String {
		val uri = Uri.parse(url)
		val ret = "$REDDIT_FRONT${uri.path}.json?limit=$limit"
		//println("DDDD url: $ret")
		return ret
	}

	fun parseComments(url: String, parent: String, limit: Int = 10) {
		val resp = client.newCall(Request.Builder().url(normalizeCommentsUrl(url)).addHeader("Authorization", "Bearer ${App.accessToken}").build()).execute()

		if(resp.isSuccessful) {
			val json = resp.body().string()
			val jp = jsonFactory.createParser(json)
			val headTable = THeader().getTableName()
			val commentTable = TComment().getTableName()
			val tr = App.sdb.newTransaction()
			try {
				while (jp.nextToken() !== null) {
					if ("selftext".equals(jp.currentName)) {
						val header = THeader()
						header.selftext = jp.nextTextValue()

						loop@ while (jp.nextToken() != JsonToken.END_OBJECT) {
							val key = jp.currentName
							when (key) {
								"user_reports" -> jp.skipChildren()
								"secure_media" -> {
									if (jp.nextToken() == JsonToken.START_OBJECT) {
										val media = Media()
										parseMedia(jp, media)
										header.embed = media.html
									}
								}
								"id" -> header.id = jp.nextTextValue()
								"author" -> header.author = jp.nextTextValue()
								"media" -> jp.skipChildren()
								"score" -> header.score = jp.nextIntValue(0)
								"preview" -> {
									val local = Preview()
									parsePreview(jp, local, 320)
									header.preview = local.source
								}
								"mod_reports" -> jp.skipChildren()
								"secure_media_embed" -> jp.skipChildren()
								"url" -> header.url = jp.nextTextValue()
								"title" -> header.title = jp.nextTextValue()
								"created_utc" -> {
									jp.nextToken()
									header.created = jp.getValueAsLong(0L)
								}
								"before" -> {
									println("inside before")
									break@loop
								}
							}
						}

						App.sdb.insert(headTable, header.getValues())
					}

					if ("author".equals(jp.currentName)) {
						val comment = TComment()
						comment.author = jp.nextTextValue()
						comment.parent = parent

						while (jp.nextToken() != JsonToken.END_OBJECT) {
							val key = jp.currentName
							when (key) {
								"score" -> comment.score = jp.nextIntValue(0)
								"body" -> comment.body = jp.nextTextValue()
								"name" -> comment.id = jp.nextTextValue().replace("t1_", "")
								"created_utc" -> {
									jp.nextToken()
									comment.created = jp.valueAsLong
								}
							}
						}

						App.sdb.insert(commentTable, comment.getValues())
					}

				}

				tr.markSuccessful()
			} finally {
				tr.end()
			}
		}

	}

	fun parseMedia(jp: JsonParser, media: Media) {
		while (jp.nextToken() !== JsonToken.END_OBJECT) {
			val key = jp.currentName
			when (key) {
				"title" -> media.title = jp.nextTextValue()
				"html" -> media.html = jp.nextTextValue()
				"provider_name" -> media.provider = jp.nextTextValue()
				"thumbnail_url" -> media.thumbnail = jp.nextTextValue()
			}
		}
		jp.nextToken()
		jp.nextToken()
		jp.nextToken()
		println("parseMedia ${jp.currentName}")
	}

	fun parsePreview(jp: JsonParser, preview: Preview, width: Int = 320) {
		val thumbWidth = "w=$width"
		var selectedPreview: String? = null
		while (jp.nextToken() !== JsonToken.END_OBJECT) {
			val key = jp.currentName

			when (key) {
				"source" -> {
					while (jp.nextToken() != JsonToken.END_OBJECT) {
						if ("url".equals(jp.currentName)) {
							preview.source = jp.nextTextValue()
						}
					}
				}
				"resolutions" -> {
					while (jp.nextToken() != JsonToken.END_ARRAY) {
						if ("url".equals(jp.currentName)) {
							val thumb = jp.nextTextValue()
							selectedPreview = thumb
							if (thumb.contains(thumbWidth)) {
								preview.thumb = thumb.replace("amp;", "")
							}
						}
					}
				}
				"variants" -> {
					while (jp.nextToken() != JsonToken.END_OBJECT) {
						val k = jp.currentName
						when (k) {
							"gif" -> {
								while (jp.nextToken() != JsonToken.END_ARRAY) {
									if ("url".equals(jp.currentName)) {
										val thumb = jp.nextTextValue()
										if (thumb.contains(thumbWidth)) {
											preview.gif = thumb.replace("amp;", "")
										}
									}
								}
								jp.nextToken() // move to start of next object mp4
							}
							"mp4" -> {
								while (jp.nextToken() != JsonToken.END_ARRAY) {
									if ("url".equals(jp.currentName)) {
										val thumb = jp.nextTextValue()
										if (thumb.contains(thumbWidth)) {
											preview.mp4 = thumb.replace("amp;", "")
										}
									}
								}
								jp.nextToken()
								jp.nextToken()
							}
						}
					}
				}
				"id" -> {
					//println("inside id")
					jp.skipChildren()
					jp.nextToken()
					jp.nextToken()
					jp.nextToken()
					//println(jp.currentName)
				}
			}

		}
		// if desired preview width not available select next best width
		if (preview.thumb == null) preview.thumb = selectedPreview
	}

	fun getInbox() {
//		val resp = client.newCall(Request.Builder().url("https://oauth.reddit.com/message/inbox").addHeader("Authorization", "Bearer ${App.accessToken}").build()).execute()
//		println("getInbox : ${resp.body().string()}")
//		if(resp.isSuccessful)	parseMessages(resp.body().string())
		parseMessages(getOrEmpty("https://oauth.reddit.com/message/inbox"))
	}

	fun getUnread() {
		parseMessages(getOrEmpty("https://oauth.reddit.com/message/unread"))
	}

	fun parseMessages(json: String) {
		val jp = jsonFactory.createParser(json)
		val table = TMessage().getTableName()
		val tr = App.sdb.newTransaction()
		try {
			while (jp.nextToken() !== null) {

				if ("body".equals(jp.currentName)) {
					val msg = TMessage()
					msg.body = jp.nextTextValue()
					println("body: ${msg.body}")

					while (jp.nextToken() !== JsonToken.END_OBJECT) {
						val key = jp.currentName
						when (key) {
							"link_title" -> msg.title = jp.nextTextValue()
							"dest" -> msg.dest = jp.nextTextValue()
							"author" -> msg.author = jp.nextTextValue()
							"created_utc" -> {
								jp.nextToken()
								msg.created = jp.valueAsLong
							}
							"subreddit" -> msg.subreddit = jp.nextTextValue()
							"parent_id" -> msg.parent = jp.nextTextValue()
							"context" -> msg.context = jp.nextTextValue()
							"id" -> msg.id = jp.nextTextValue()
						}
					}

					App.sdb.insert(table, msg.getValues())
				}
			}
			tr.markSuccessful()
		} finally {
			tr.end()
		}
	}

}