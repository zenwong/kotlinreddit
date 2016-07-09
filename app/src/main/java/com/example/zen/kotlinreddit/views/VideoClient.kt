package com.example.zen.kotlinreddit.views

import android.media.MediaPlayer
import android.webkit.WebChromeClient

const val WEBVIEWCSS = """<style type="text/css">
	* {	-webkit-tap-highlight-color: transparent; }
	html, body, iframe{
    color: #333;
    height:___HEIGHT___px;
    width:100%;
    max-height:___HEIGHT___px;
    margin:0 !important;
    padding:0 !important;
    box-sizing:border-box;
	}</style>"""

class VideoClient: WebChromeClient(), MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener {
	override fun onPrepared(mp: MediaPlayer) {
	}

	override fun onCompletion(mp: MediaPlayer) {
	}

	override fun onError(mp: MediaPlayer, what: Int, extra: Int): Boolean {
		return false
	}

}