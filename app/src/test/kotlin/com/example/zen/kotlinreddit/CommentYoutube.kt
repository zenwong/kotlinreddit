
import com.example.zen.kotlinreddit.Reddit
import com.example.zen.kotlinreddit.models.Comment
import com.example.zen.kotlinreddit.models.CommentHeader
import io.kotlintest.specs.StringSpec
import java.util.*


class CommentYoutube : StringSpec() {

  init {
    val desiredWidth = 320
    val header = CommentHeader()
    val comments = ArrayList<Comment>()
    Reddit.parseComments("/root/work/reddit/comment-youtube.json", "parent", comments, header, desiredWidth)

    "selftext" { header.selftext shouldBe "" }
    "id" { header.id shouldBe "4rbra9" }
    "author" { header.author shouldBe  "mothh9" }
    "score" { header.score shouldBe 6 }
    "preview thumb" { header.preview?.thumb shouldBe  "https://i.redditmedia.com/EErvC7DsLSdEobrSUeuqNTcYRl1sLC8W4G7bGqwXMk8.jpg?fit=crop&amp;crop=faces%2Centropy&amp;arh=2&amp;w=320&amp;s=9109e410fd804305f463d82f58f362b5".replace("amp;", "") }
    "preview source" { header.preview?.source shouldBe "https://i.redditmedia.com/EErvC7DsLSdEobrSUeuqNTcYRl1sLC8W4G7bGqwXMk8.jpg?s=089982b9acb58eb31f522f057dd986e8" }
    "url" { header.url shouldBe "https://www.youtube.com/watch?v=02mk-3furUs" }
    "title" { header.title shouldBe "New devlog of Subject A-119, a game I am developing in Unreal Engine 4" }
    "created_utc" { header.created shouldBe 1467708025L }
    "comments" { header.comments shouldBe 1 }
    "media" { header.media shouldBe "&lt;iframe width=\"600\" height=\"338\" src=\"https://www.youtube.com/embed/02mk-3furUs?feature=oembed\" frameborder=\"0\" allowfullscreen&gt;&lt;/iframe&gt;" }

    "comment 0 id" { comments[0].id shouldBe "d4zs7gn" }
    "comment 0 body" { comments[0].body shouldBe "If this isn't allowed to be posted here, please let me know and I will remove it." }
    "comment 0 created_utc" { comments[0].created shouldBe 1467708098L }
    "comment 0 author" { comments[0].author shouldBe "mothh9" }
    "comment 0 parent" { comments[0].comment_parent shouldBe "t3_4rbra9" }
  }


}