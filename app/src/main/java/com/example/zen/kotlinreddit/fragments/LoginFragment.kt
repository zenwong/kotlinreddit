package com.example.zen.kotlinreddit.fragments

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.zen.kotlinreddit.R
import com.example.zen.kotlinreddit.databinding.LoginBinding
import com.example.zen.kotlinreddit.models.User

class LoginFragment : Fragment() {

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		val binding : LoginBinding = DataBindingUtil.inflate(inflater, R.layout.login, container, false)
		val user = User("zen", "password")
		binding.user = user

		return binding.root
	}


}