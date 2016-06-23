package com.example.zen.kotlinreddit

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DB(val ctx: Context) : SQLiteOpenHelper(ctx, "test.db", null, 1) {

	val schema = "create table if not exists posts (_id integer primary key autoincrement, preview text);"

	fun test() {
		println(schema)
	}

	override fun onCreate(db: SQLiteDatabase) {
		try {
			println(schema)
			db.execSQL(schema)
		} catch(e: Exception) {
			println(e.message)
		}
	}

	override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
	}

}