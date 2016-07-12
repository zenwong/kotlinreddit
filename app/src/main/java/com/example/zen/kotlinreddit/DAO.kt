package com.example.zen.kotlinreddit

import android.content.ContentValues
import android.database.Cursor
import rx.functions.Func1

class TPost : BaseModel() {
	var author: String? = null
	var comments: Int = 0
	var created: Long = 0L
	var id: String? = null
	var permalink: String? = null
	var preview: String? = null
	var score: Int = 0
	var subreddit: String? = null
	var title: String? = null

	companion object {
		val MAPPER = Func1<Cursor, TPost> { c ->
			val post = TPost().apply {
				author = c.getString(1)
				comments = c.getInt(2)
				created = c.getLong(3)
				id = c.getString(4)
				permalink = c.getString(5)
				preview = c.getString(6)
				score = c.getInt(7)
				subreddit = c.getString(8)
				title = c.getString(9)
			}
			post
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

	companion object {
		val MAPPER = Func1<Cursor, THeader> { c ->
			THeader().apply {
				author = c.getString(1)
				comments = c.getInt(2)
				created = c.getLong(13)
				embed = c.getString(4)
				id = c.getString(5)
				mp4 = c.getString(6)
				preview = c.getString(7)
				score = c.getInt(8)
				selftext = c.getString(9)
				title = c.getString(10)
				url = c.getString(11)
			}
		}
	}
}

class TComment : BaseModel() {
	var id: String? = null
	var parent: String? = null
	var author: String? = null
	var body: String? = null
	var score: Int = 0
	var created: Long = 0L

	companion object {
		val MAPPER = Func1<Cursor, TComment> { c ->
			TComment().apply {
				author = c.getString(1)
				body = c.getString(2)
				created = c.getLong(3)
				id = c.getString(4)
				parent = c.getString(5)
				score = c.getInt(6)
			}
		}
	}
}

open class BaseModel() {
	fun getTableName() : String {
		return javaClass.simpleName
	}

	fun getSchema(table: String = getTableName(), foreignKey: String? = null, foreignTable: String? = null): String {
		val sb = StringBuilder()
		sb.append("_id integer primary key autoincrement,")
		javaClass.declaredFields.forEach {
			println("SCHEMA ${it.name}")

			when (it.genericType.toString()) {
				"class java.lang.String" -> sb.append("${it.name} text,")
				"int" -> sb.append("${it.name} integer,")
				"long" -> sb.append("${it.name} integer,")
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

			when (it.genericType.toString()) {
				"int" -> values.put(it.name, it.getInt(this))
				"long" -> values.put(it.name, it.getLong(this))
				"class java.lang.String" -> values.put(it.name, it.get(this) as String?)
			}
		}
		return values
	}
}

