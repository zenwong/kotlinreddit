package com.example.zen.kotlinreddit

import com.squareup.sqlbrite.BriteDatabase
import de.jodamob.kotlin.testrunner.KotlinTestRunner
import de.jodamob.kotlin.testrunner.OpenedClasses
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import java.io.File
import kotlin.test.assertEquals

@RunWith(KotlinTestRunner::class)
@OpenedClasses(BriteDatabase::class)
class MyKotlinTestclass {

	@Test
	fun test() {
		val db = Mockito.mock(BriteDatabase::class.java)
		val json = File("/home/zen/AndroidStudioProjects/KotlinReddit/testJson/comment-youtube.json").readText()
		val header = THeader()
		Reddit.parseComments(json, "parent", db, header)

		assertEquals(header.selftext, "")
		assertEquals(header.id, "4rbra9")
		assertEquals(header.author, "mothh9")
		assertEquals(header.score, 6)
		assertEquals(header.preview, "https://i.redditmedia.com/EErvC7DsLSdEobrSUeuqNTcYRl1sLC8W4G7bGqwXMk8.jpg?s=089982b9acb58eb31f522f057dd986e8")
		assertEquals(header.url, "https://www.youtube.com/watch?v=02mk-3furUs")
		assertEquals(header.title,"New devlog of Subject A-119, a game I am developing in Unreal Engine 4")
		assertEquals(header.created, 1467708025L)
		assertEquals(header.comments, 1)
		assertEquals(header.embed, "&lt;iframe width=\"600\" height=\"338\" src=\"https://www.youtube.com/embed/02mk-3furUs?feature=oembed\" frameborder=\"0\" allowfullscreen&gt;&lt;/iframe&gt;")

	}
}