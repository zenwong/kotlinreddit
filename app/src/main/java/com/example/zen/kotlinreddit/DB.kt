package com.example.zen.kotlinreddit

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DB(ctx: Context) : SQLiteOpenHelper(ctx, "reddit.db", null, 1) {

	override fun onCreate(db: SQLiteDatabase) {
		db.execSQL(TPost().getSchema())
		db.execSQL(THeader().getSchema())
		db.execSQL(TComment().getSchema())
		db.execSQL(TMessage().getSchema())
	}

	override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
	}

}