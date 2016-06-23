package com.example.zen.kotlinreddit.models

data class RedditPost(val domain: String) {
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
	var posthint: String? = null
	var permalink: String? = null
	var created: Int? = null
	var url: String? = null
	var title: String? = null

	override fun toString(): String {
		return "title: $title\ndomain: $domain, id: $id, author: $author, score: $score, comments: $comments\n" +
			"thumbnail: $thumbnail\npreview: $preview\nmedia: $media_title\n"
	}
}

data class Comment(val id: String) {
	var parent: String? = null
	var author: String? = null
	var body: String? = null
	var score: Int? = null
	var created: Int? = null

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

	var created: Int? = null

	override fun toString() : String {
		return "id: $id, parent: $parent, author: $author, created: $created\nmessage: $message\n"
	}
}

data class Navigation(val fragment: Int)
data class AccessToken(val token: String)
data class RefreshToken(val token: String)