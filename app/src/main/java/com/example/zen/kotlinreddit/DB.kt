package com.example.zen.kotlinreddit

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DB(ctx: Context) : SQLiteOpenHelper(ctx, "reddit.db", null, 1) {
	lateinit var db: SQLiteDatabase

	companion object {
		const val clearSQL = "delete from posts; delete from comments; delete from messages; delete from sqlite_sequence"
	}

	override fun onCreate(db: SQLiteDatabase) {
		db.execSQL(TPost().getSchema())
		db.execSQL(THeader().getSchema())
		db.execSQL(TComment().getSchema())
	}

	override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
	}

}