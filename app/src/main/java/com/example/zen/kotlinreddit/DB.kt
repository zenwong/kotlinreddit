package com.example.zen.kotlinreddit

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.zen.kotlinreddit.models.RedditPost
import java.util.*

class DB(ctx: Context) : SQLiteOpenHelper(ctx, "test.db", null, 1) {
	lateinit var db: SQLiteDatabase

	fun insertPosts(posts: List<RedditPost>) {
		db = writableDatabase

		posts.forEach {
			db.insert("posts", null, it.getValues())
		}
	}

	fun getPosts() : List<RedditPost> {
		db = writableDatabase

		val posts = ArrayList<RedditPost>()
		val c = db.rawQuery("select * from posts", null)
		if(c.moveToFirst()) {
			do {
				val post = RedditPost()
				post.rid = c.getString(1)
				post.title = c.getString(2)
				post.url = c.getString(3)
				post.author = c.getString(4)
				post.subreddit = c.getString(5)
				post.media_title = c.getString(6)
				post.media_preview = c.getString(7)
				post.preview = c.getString(8)
				post.thumbnail = c.getString(9)
				post.posthint = c.getString(10)
				post.permalink = c.getString(11)
				post.comments = c.getInt(12)
				post.score = c.getInt(13)
				post.created = c.getInt(14)
				posts.add(post)
			} while(c.moveToNext())
		}

		return posts
	}

	override fun onCreate(db: SQLiteDatabase) {
		db.execSQL(postsSchema)
		db.execSQL(commentsSchema)
		db.execSQL(messagesSchema)
	}

	override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
	}

	val postsSchema = """create table if not exists posts (
	_id integer primary key autoincrement,
	rid text,
	title text,
	url text,
	author text,
	subreddit text,
  media_title text,
	media_preview text,
	preview text,
	thumbnail text,
	posthint text,
	permalink text,
	comments integer,
	score integer,
	created integer,
	clicked integer,
	unique(rid) on conflict ignore
);"""

val commentsSchema = """create table if not exists comments (
	_id integer primary key autoincrement,
	pid integer,
	cid text,
	parent text,
	author text,
	body text,
	score integer,
	created integer,
	foreign key(pid) references posts(_id) on delete cascade,
	unique(cid) on conflict ignore
);"""

val messagesSchema = """create table if not exists messages (
	_id integer primary key autoincrement,
	mid text,
	title text,
	dest text,
	author text,
	parent text,
	created integer,
	unique(mid) on conflict ignore
);"""
}
