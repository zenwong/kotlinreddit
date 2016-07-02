package com.example.zen.kotlinreddit

import android.content.Context
import android.net.Uri
import android.util.Base64
import com.example.zen.kotlinreddit.models.*
import com.fasterxml.jackson.core.JsonFactory
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonToken
import com.squareup.sqlbrite.BriteDatabase
import okhttp3.*
import org.greenrobot.eventbus.EventBus
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.io.File
import java.io.IOException
import java.util.*

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
  lateinit var db: BriteDatabase

  fun init(context: Context, cacheDir: File) {
    ctx = context
    cache = Cache(cacheDir, 1024L * 1024L * 100L)
    //client = OkHttpClient.Builder().authenticator(RedditOauthAuthenticator()).cache(cache).build()
    client = OkHttpClient.Builder().cache(cache).build()
    db = App.sqlBrite.wrapDatabaseHelper(DB(ctx), Schedulers.io())
  }

  fun getAuthUrl(clientid: String = CLIENTID, state: String = "NONCE", redirect: String = "http://zreddit", scope: String = "read identity"): String {
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
                get(access, REDDIT_FRONT)
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
    val token = ctx.getSharedPreferences(TAG, Context.MODE_PRIVATE).getString("REFRESH_TOKEN", null)
    val body = FormBody.Builder().add("grant_type", "refresh_token").add("refresh_token", token).build()
    val req = Request.Builder().url(REDDIT_AUTH_TOKEN).addHeader("Authorization", "Basic $BASIC_AUTH").post(body).build()
    return client.newCall(req).execute().body().string()
  }

  fun get(accessToken: String? = App.accessToken, url: String) {
    client.newCall(Request.Builder().url(url).addHeader("Authorization", "Bearer $accessToken").build()).enqueue(object : Callback {
      override fun onFailure(call: Call?, e: IOException?) {
      }

      override fun onResponse(call: Call?, response: Response) {
        if (response.isSuccessful) parseFrontPage(response.body().string())
        else {
          println("onResponse not successful " + response.code())
        }
      }
    })
  }

  fun getObs(url: String): String {
    return client.newCall(Request.Builder().url(url).addHeader("Authorization", "Bearer ${App.accessToken}").build()).execute().body().string()
  }

  fun getNewPosts() {
    parsePosts(getObs("https://oauth.reddit.com/new/"))
  }

  fun parsePosts(json: String) {
    val jp = jsonFactory.createParser(json)

    val tr = db.newTransaction()
    try {
      while (jp.nextToken() !== null) {
        if ("domain".equals(jp.currentName)) {
          jp.nextToken()
          val post = RedditPost()
          post.domain = jp.valueAsString

          while (jp.nextToken() != JsonToken.END_OBJECT) {
            if ("media_embed".equals(jp.currentName)) jp.skipChildren()
            if ("subreddit".equals(jp.currentName)) {
              jp.nextToken()
              post.subreddit = jp.valueAsString
            }
            if ("secure_media".equals(jp.currentName)) jp.skipChildren()
            if ("user_reports".equals(jp.currentName)) jp.skipChildren()
            if ("id".equals(jp.currentName)) {
              jp.nextToken()
              post.rid = jp.valueAsString
            }
            if ("author".equals(jp.currentName)) {
              jp.nextToken()
              post.author = jp.valueAsString
            }
            if ("media".equals(jp.currentName)) {
              if (jp.nextToken() !== JsonToken.VALUE_NULL) parseMedia(jp, post)
            }
            if ("score".equals(jp.currentName)) {
              jp.nextToken()
              post.score = jp.valueAsInt
            }
            if ("preview".equals(jp.currentName)) {
              parsePreview(jp, post)
            }
            if ("num_comments".equals(jp.currentName)) {
              jp.nextToken()
              post.comments = jp.valueAsInt
            }
            if ("thumbnail".equals(jp.currentName)) {
              jp.nextToken()
              post.thumbnail = jp.valueAsString
            }
            if ("secure_media_embed".equals(jp.currentName)) jp.skipChildren()
            if ("permalink".equals(jp.currentName)) {
              jp.nextToken()
              post.permalink = jp.valueAsString
            }
            if ("created".equals(jp.currentName)) {
              jp.nextToken()
              post.created = jp.valueAsInt
            }
            if ("url".equals(jp.currentName)) {
              jp.nextToken()
              post.url = jp.valueAsString
            }
            if ("title".equals(jp.currentName)) {
              jp.nextToken()
              post.title = jp.valueAsString
            }

            if ("mod_reports".equals(jp.currentName)) jp.skipChildren()
          }

          db.insert("posts", post.getValues())
        }
      }
      tr.markSuccessful()
    } finally {
      tr.end()
    }

  }

  fun getComments(url: String) {
    client.newCall(Request.Builder().url(url).addHeader("Authorization", "Bearer ${App.accessToken}").build()).enqueue(object : Callback {
      override fun onFailure(call: Call?, e: IOException?) {
      }

      override fun onResponse(call: Call?, response: Response) {
        if (response.isSuccessful) parseComments(response.body().string())
      }
    })
  }

  fun parseFrontPage(json: String): List<RedditPost> {
    val jp = jsonFactory.createParser(json)
    val list = ArrayList<RedditPost>()

    while (jp.nextToken() !== null) {
      if ("domain".equals(jp.currentName)) {
        jp.nextToken()
        val post = RedditPost()
        post.domain = jp.valueAsString

        while (jp.nextToken() != JsonToken.END_OBJECT) {
          if ("media_embed".equals(jp.currentName)) jp.skipChildren()
          if ("subreddit".equals(jp.currentName)) {
            jp.nextToken()
            post.subreddit = jp.valueAsString
          }
          if ("secure_media".equals(jp.currentName)) jp.skipChildren()
          if ("user_reports".equals(jp.currentName)) jp.skipChildren()
          if ("id".equals(jp.currentName)) {
            jp.nextToken()
            post.rid = jp.valueAsString
          }
          if ("author".equals(jp.currentName)) {
            jp.nextToken()
            post.author = jp.valueAsString
          }
          if ("media".equals(jp.currentName)) {
            //jp.skipChildren()
            if (jp.nextToken() !== JsonToken.VALUE_NULL) parseMedia(jp, post)
          }
          if ("score".equals(jp.currentName)) {
            jp.nextToken()
            post.score = jp.valueAsInt
          }
          if ("preview".equals(jp.currentName)) {
            parsePreview(jp, post)
          }
          if ("num_comments".equals(jp.currentName)) {
            jp.nextToken()
            post.comments = jp.valueAsInt
          }
          if ("thumbnail".equals(jp.currentName)) {
            jp.nextToken()
            post.thumbnail = jp.valueAsString
          }
          if ("secure_media_embed".equals(jp.currentName)) jp.skipChildren()
          if ("permalink".equals(jp.currentName)) {
            jp.nextToken()
            post.permalink = jp.valueAsString
          }
          if ("created".equals(jp.currentName)) {
            jp.nextToken()
            post.created = jp.valueAsInt
          }
          if ("url".equals(jp.currentName)) {
            jp.nextToken()
            post.url = jp.valueAsString
          }
          if ("title".equals(jp.currentName)) {
            jp.nextToken()
            post.title = jp.valueAsString
          }

          if ("mod_reports".equals(jp.currentName)) jp.skipChildren()
        }

        //println(post)
        list.add(post)
      }
    }

    EventBus.getDefault().post(list)

    list.sortByDescending { it.preview }
    return list
  }

  fun parseMedia(jp: JsonParser, post: RedditPost) {
    while (jp.nextToken() !== JsonToken.END_OBJECT) {
      if ("title".equals(jp.currentName)) {
        jp.nextToken()
        post.media_title = jp.valueAsString
      }

      if ("thumbnail_url".equals(jp.currentName)) {
        jp.nextToken()
        post.media_preview = jp.valueAsString
      }

      //if("author_url".equals(jp.currentName)) break
    }
    jp.nextToken()
    jp.nextToken()
    jp.nextToken()
    //info(jp)
  }

  fun parsePreview(jp: JsonParser, post: RedditPost) {
    while (jp.nextToken() !== null) {
      if ("source".equals(jp.currentName)) jp.skipChildren()

      if ("url".equals(jp.currentName)) {
        jp.nextToken()
        val preview = jp.valueAsString
        if (preview.contains("w=320")) {
          post.preview = preview.replace("amp;", "")
        }
      }

      if ("variants".equals(jp.currentName)) jp.skipChildren()

      if ("id".equals(jp.currentName)) break
    }
    jp.nextToken()
    jp.nextToken()
    jp.nextToken()
    jp.nextToken()
    //info(jp)
  }

  fun parseComments(json: String) {
    val list = ArrayList<Comment>()
    val jp = jsonFactory.createParser(json)

    while (jp.nextToken() !== null) {
      /* if("children".equals(jp.currentName)) {
         jp.nextToken()
         println("kind: " + jp.valueAsString)
       }*/

      //if("replies".equals(jp.currentName)) jp.skipChildren()

      if ("id".equals(jp.currentName)) {
        jp.nextToken()
        val comment = Comment()
        comment.id = jp.valueAsString

        while (jp.nextToken() != JsonToken.END_OBJECT) {
          if ("author".equals(jp.currentName)) {
            jp.nextToken()
            comment.author = jp.valueAsString
          }

          if ("parent_id".equals(jp.currentName)) {
            jp.nextToken()
            comment.parent = jp.valueAsString
          }

          if ("score".equals(jp.currentName)) {
            jp.nextToken()
            comment.score = jp.valueAsInt
          }

          if ("body".equals(jp.currentName)) {
            jp.nextToken()
            comment.body = jp.valueAsString
          }

          if ("body_html".equals(jp.currentName)) {
            jp.nextToken()
            comment.html = jp.valueAsString
          }

          if ("created".equals(jp.currentName)) {
            jp.nextToken()
            comment.created = jp.valueAsInt
          }
        }

        comment.body?.let {
          list.add(comment)
        }
      }

    }

    Observable.fromCallable {
      App.db.insertComments(list)
    }.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe()

    //list.sortByDescending { it.score }
//		list.groupBy { it.parent }.forEach {
//			println(it)
//		}
  }

  fun parseMessages() {
    val jp = jsonFactory.createParser(File("/home/zen/reddit/messages.json"))

    while (jp.nextToken() !== null) {

      if ("body".equals(jp.currentName)) {
        jp.nextToken()
        val msg = Message(jp.valueAsString)

        while (jp.nextToken() !== JsonToken.END_OBJECT) {
          if ("link_title".equals(jp.currentName)) {
            jp.nextToken()
            msg.title = jp.valueAsString
          }

          if ("created".equals(jp.currentName)) {
            jp.nextToken()
            msg.created = jp.valueAsInt
          }

          if ("dest".equals(jp.currentName)) {
            jp.nextToken()
            msg.dest = jp.valueAsString
          }

          if ("author".equals(jp.currentName)) {
            jp.nextToken()
            msg.author = jp.valueAsString
          }

          if ("parent_id".equals(jp.currentName)) {
            jp.nextToken()
            msg.parent = jp.valueAsString
          }

          if ("id".equals(jp.currentName)) {
            jp.nextToken()
            msg.id = jp.valueAsString
          }
        }

        println(msg)
      }

    }
  }

}