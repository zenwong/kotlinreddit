
import android.content.Context
import com.example.zen.kotlinreddit.App
import com.example.zen.kotlinreddit.DB
import com.example.zen.kotlinreddit.Reddit
import com.example.zen.kotlinreddit.THeader
import com.squareup.sqlbrite.SqlBrite
import mockit.Mocked
import org.junit.Test
import rx.schedulers.Schedulers
import java.io.File
import kotlin.test.assertEquals

class CommentYoutube  {

//  init {
//    val desiredWidth = 320
//    val header = THeader()
//    val comments = ArrayList<Comment>()
//    Reddit.parseComments("/root/work/reddit/comment-youtube.json", "parent", header)
//
//    "selftext" { header.selftext shouldBe "" }
//    "id" { header.id shouldBe "4rbra9" }
//    "author" { header.author shouldBe  "mothh9" }
//    "score" { header.score shouldBe 6 }
//    "preview source" { header.preview shouldBe "https://i.redditmedia.com/EErvC7DsLSdEobrSUeuqNTcYRl1sLC8W4G7bGqwXMk8.jpg?s=089982b9acb58eb31f522f057dd986e8" }
//    "url" { header.url shouldBe "https://www.youtube.com/watch?v=02mk-3furUs" }
//    "title" { header.title shouldBe "New devlog of Subject A-119, a game I am developing in Unreal Engine 4" }
//    "created_utc" { header.created shouldBe 1467708025L }
//    "comments" { header.comments shouldBe 1 }
//    "media" { header.embed shouldBe "&lt;iframe width=\"600\" height=\"338\" src=\"https://www.youtube.com/embed/02mk-3furUs?feature=oembed\" frameborder=\"0\" allowfullscreen&gt;&lt;/iframe&gt;" }
//
//    "comment 0 id" { comments[0].id shouldBe "d4zs7gn" }
//    "comment 0 body" { comments[0].body shouldBe "If this isn't allowed to be posted here, please let me know and I will remove it." }
//    "comment 0 created_utc" { comments[0].created shouldBe 1467708098L }
//    "comment 0 author" { comments[0].author shouldBe "mothh9" }
//    "comment 0 parent" { comments[0].comment_parent shouldBe "t3_4rbra9" }
//  }


	@Mocked val context: Context = App()
	@Mocked val DB = DB(context)
	@Mocked val sqlBrite = SqlBrite.create()
	@Mocked val db = sqlBrite.wrapDatabaseHelper(DB(context), Schedulers.io())

	@Test
	fun testHeader() {

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