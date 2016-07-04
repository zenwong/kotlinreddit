package com.example.zen.kotlinreddit.models

import android.content.ContentValues
import android.database.Cursor
import rx.functions.Func1

class Post {
	var _id: Int? = null
	var subreddit: String? = null
	var id: String? = null
	var clicked: Boolean? = null
	var author: String? = null
	var media_title: String? = null
	var media_preview: String? = null
	var score: Int? = null
	var preview: String? = null
	var comments: Int? = null
	var thumbnail: String? = null
	var permalink: String? = null
	var created: Long? = null
	var url: String? = null
	var title: String? = null
	var domain: String? = null
	var display: String? = null

	companion object {
		val MAPPER = Func1<Cursor, Post> { c ->
			val post = Post().apply {
				_id = c.getInt(0)
				id = c.getString(1)
				title = c.getString(2)
				url = c.getString(3)
				author = c.getString(4)
				subreddit = c.getString(5)
				media_title = c.getString(6)
				media_preview = c.getString(7)
				preview = c.getString(8)
				thumbnail = c.getString(9)
				permalink = c.getString(11)
				comments = c.getInt(12)
				score = c.getInt(13)
				created = c.getLong(14)
				display = c.getString(16)
			}
			post
		}

	}

	fun getValues() : ContentValues {
		val values = ContentValues().apply {
			put("rid", id)
			put("url", url)
			put("title", title)
			put("author", author)
			put("score", score)
			put("comments", comments)
			put("subreddit", subreddit)
			put("media_title", media_title)
			put("media_preview", media_preview)
			put("preview", preview)
			put("thumbnail", thumbnail)
			put("permalink", permalink)
			put("clicked", clicked)
			put("created", created)
			put("display", display)
		}

		return values
	}

	override fun toString(): String {
		return "title: $title\ndomain: $domain, id: $id, author: $author, score: $score, comments: $comments\n" +
			"thumbnail: $thumbnail\npreview: $preview\nmedia: $media_preview\ndisplay: $display\n\n"
	}
}

class RedditPost() {
	var _id: Int? = null
	var subreddit: String? = null
	var rid: String? = null
	var clicked: Boolean? = null
	var author: String? = null
	var media_title: String? = null
	var media_preview: String? = null
	var score: Int? = null
	var preview: String? = null
	var comments: Int? = null
	var thumbnail: String? = null
	var posthint: String? = null
	var permalink: String? = null
	var created: Long? = null
	var url: String? = null
	var title: String? = null
	var domain: String? = null

	companion object {
		val MAPPER = Func1<Cursor, RedditPost> { c ->
			val post = RedditPost().apply {
				_id = c.getInt(0)
				rid = c.getString(1)
				title = c.getString(2)
				url = c.getString(3)
				author = c.getString(4)
				subreddit = c.getString(5)
				media_title = c.getString(6)
				media_preview = c.getString(7)
				preview = c.getString(8)
				thumbnail = c.getString(9)
				posthint = c.getString(10)
				permalink = c.getString(11)
				comments = c.getInt(12)
				score = c.getInt(13)
				created = c.getLong(14)
			}
			post
		}

	}

	fun getValues() : ContentValues {
		val values = ContentValues().apply {
			put("rid", rid)
			put("url", url)
			put("title", title)
			put("author", author)
			put("score", score)
			put("comments", comments)
			put("subreddit", subreddit)
			put("media_title", media_title)
			put("media_preview", media_preview)
			put("preview", preview)
			put("thumbnail", thumbnail)
			put("permalink", permalink)
			put("posthint", posthint)
			put("clicked", clicked)
			put("created", created)
		}

		return values
	}

	override fun toString(): String {
		return "title: $title\ndomain: $domain, id: $rid, author: $author, score: $score, comments: $comments\n" +
			"thumbnail: $thumbnail\npreview: $preview\nmedia: $media_title\n"
	}
}

class Comment() {
	var pid: Int? = null
	var id: String? = null
	var parent: String? = null
	var author: String? = null
	var body: String? = null
	var html: String? = null
	var score: Int? = null
	var created: Long? = null

	companion object {
		val MAPPER = Func1<Cursor, Comment> { c ->
			val comment = Comment().apply {
				pid = c.getInt(1)
				id = c.getString(2)
				parent = c.getString(3)
				author = c.getString(4)
				body = c.getString(5)
				html = c.getString(6)
				score = c.getInt(7)
				created = c.getLong(8)
			}
			comment
		}
	}

	fun getValues() : ContentValues {
		val values = ContentValues().apply {
			put("pid", pid)
			put("cid", id)
			put("author", author)
			put("body", body)
			put("html", html)
			put("created", created)
			put("score", score)
			put("parent", parent)
		}
		return values
	}

	override fun toString(): String {
		return "id: $id, parent: $parent, author: $author, score: $score, created: $created\nbody: $body\n"
	}
}

data class Message(val message: String) {
	var title: String? = null
	var dest: String? = null
	var author: String? = null
	var id: String? = null
	var parent: String? = null

	var created: Long? = null

	override fun toString() : String {
		return "id: $id, parent: $parent, author: $author, created: $created\nmessage: $message\n"
	}
}

data class Navigation(val fragment: Int) {
	var id: String? = null
	var pid: Int? = null
}
data class CommentsRequest(val url: String, val pid: Int)
data class AccessToken(val token: String)
data class RefreshToken(val token: String)