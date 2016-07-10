package com.example.zen.kotlinreddit.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.zen.kotlinreddit.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.gif_view.*

class GifFragment: Fragment() {
	val imgur = "https://i.imgur.com/17JHT03.mp4"
	val source = "https://i.redditmedia.com/mySzeC5TokoCNl8m97Wi2ujLuf7FFhnYeb6ZyLzpCO8.gif?fm=jpg&amp;s=f8f16a605cf993f8753ae484da926fc2".replace("amp;", "")
	val thumb = "http://cos.myqcloud.com/1000264/qcloud_video_attachment/842646334/vod_cover/cover1458036374.jpg"

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		player.setUp("https://i.imgur.com/17JHT03.mp4", "Imgur")
		player.setLoop(true)

		Picasso.with(context).load(source)
			.fit().centerCrop()
			.into(player.thumbImageView)

		//JCFullScreenActivity.startActivity(activity, imgur,	JCVideoPlayerStandard::class.java, "Imgur")
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		return inflater.inflate(R.layout.gif_view, container, false)
	}

}