package com.example.zen.kotlinreddit.models

import android.databinding.BaseObservable
import android.util.Log
import android.view.View

class LoginUser(var username: String, var password: String) : BaseObservable() {
	//var username: String? = null
	//var password: String? = null

	fun onLogin(view: View) {
		Log.d("TEST", "username: $username, password: $password")
	}
}