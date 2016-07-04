package com.example.zen.kotlinreddit

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.zen.kotlinreddit.models.Comment
import com.example.zen.kotlinreddit.models.RedditPost
import java.util.*

class DB(ctx: Context) : SQLiteOpenHelper(ctx, "test.db", null, 1) {
	lateinit var db: SQLiteDatabase

	companion object {
		const val clearSQL = "delete from posts; delete from comments; delete from messages; delete from sqlite_sequence"
	}

	fun insertPosts(posts: List<RedditPost>) {
		db = writableDatabase
		posts.forEach {
			db.insert("posts", null, it.getValues())
		}
	}

	fun insertComments(comments: List<Comment>) {
		db = writableDatabase
		comments.forEach {
			db.insert("comments", null, it.getValues())
		}
	}

	fun getPosts() : List<RedditPost> {
		db = readableDatabase

		val posts = ArrayList<RedditPost>()
		val c = db.rawQuery("select * from posts order by preview desc", null)
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
				//post.preview = c.getString(8)
				post.thumbnail = c.getString(9)
				post.posthint = c.getString(10)
				post.permalink = c.getString(11)
				post.comments = c.getInt(12)
				post.score = c.getInt(13)

				val preview = c.getString(8)
				if(preview == null) {
					post.preview = c.getString(7)
				} else {
					post.preview = preview
				}

				post.created = c.getLong(14)
				posts.add(post)
			} while(c.moveToNext())
		}

		return posts
	}

	override fun onCreate(db: SQLiteDatabase) {
		db.execSQL(postsSchema)
		db.execSQL(commentsSchema)
		db.execSQL(commentHeaderSchema)
		db.execSQL(messagesSchema)
	}

	fun clearTables() {
		db = writableDatabase
		db.execSQL(clearSQL)
		onCreate(db)
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
	display text,
	unique(rid) on conflict replace
);"""

val commentHeaderSchema = """create table if not exists comment_headers (
	_id integer primary key autoincrement,
	parent text,
	selftext text,
	id text,
	author text,
	media text,
  score integer,
	url text,
	title text,
	created integer,
	comments integer,
	preview text,
	foreign key(parent) references posts(rid) on delete cascade,
	unique(id) on conflict ignore
);"""

val commentsSchema = """create table if not exists comments (
	_id integer primary key autoincrement,
	cid text,
	parent text,
	author text,
	body text,
	html text,
	score integer,
	created integer,
	comment_parent text,
	foreign key(parent) references posts(rid) on delete cascade,
	unique(cid) on conflict ignore
);"""

val messagesSchema = """create table if not exists messages (
	_id integer primary key autoincrement,
	mid text,
	title text,
	dest text,
	author text,
	parent text,created integer,unique(mid) on conflict ignore
);"""
}
