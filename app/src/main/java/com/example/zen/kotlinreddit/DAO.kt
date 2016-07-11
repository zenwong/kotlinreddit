package com.example.zen.kotlinreddit

import android.content.ContentValues
import android.database.Cursor
import com.example.zen.kotlinreddit.models.Comment
import com.example.zen.kotlinreddit.models.Post
import rx.functions.Func1

class TPost : BaseModel() {
	var id: String? = null
	var title: String? = null
	var subreddit: String? = null
	var author: String? = null
	var preview: String? = null
	var comments: Int = 0
	var score: Int = 0
	var created: Long = 0L

	companion object {
		val MAPPER = Func1<Cursor, Post> { c ->
			Post().apply {
				id = c.getString(1)
				title = c.getString(2)
				subreddit = c.getString(3)
				author = c.getString(4)
				preview = c.getString(5)
				comments = c.getInt(6)
				score = c.getInt(7)
				created = c.getLong(8)
			}
		}
	}
}

class THeader : BaseModel() {
	var id: String? = null
	var selftext: String? = null
	var url: String? = null
	var title: String? = null
	var author: String? = null
	var embed: String? = null
	var preview: String? = null
	var mp4: String? = null

	var comments: Int = 0
	var score: Int = 0
	var created: Long = 0L
}

class TComment : BaseModel() {
	var id: String? = null
	var parent: String? = null
	var author: String? = null
	var body: String? = null
	var score: Int = 0
	var created: Long = 0L

	companion object {
		val MAPPER = Func1<Cursor, Comment> { c ->
			Comment().apply {
				id = c.getString(1)
				parent = c.getString(2)
				author = c.getString(3)
				body = c.getString(4)
				score = c.getInt(6)
				created = c.getLong(7)
			}
		}
	}
}

open class BaseModel() {
	fun getSchema(table: String, foreignKey: String? = null, foreignTable: String? = null): String {
		val sb = StringBuilder()
		sb.append("_id integer primary key autoincrement,")
		javaClass.declaredFields.forEach {
			when (it.type.toString()) {
				"int" -> sb.append("${it.name} integer,")
				"java.lang.String" -> sb.append("${it.name} text,")
			}
		}
		if (foreignTable == null) {
			return """
			create table if not exists $table (
				${sb.toString()}
				unique(id) on conflict ignore
			);"""
		}

		return """
			create table if not exists $table (
				${sb.toString()}
				foreign key($foreignKey) references $foreignTable(_id) on delete cascade,
				unique(id) on conflict ignore
			);"""
	}

	fun getValues(): ContentValues {
		val values = ContentValues()
		javaClass.declaredFields.forEach {
			it.isAccessible = true
			when (it.type.toString()) {
				"int" -> values.put(it.name, it.getInt(this))
				"long" -> values.put(it.name, it.getLong(this))
				"java.lang.String" -> values.put(it.name, it.get(this) as String)
			}
		}
		return values
	}
}

