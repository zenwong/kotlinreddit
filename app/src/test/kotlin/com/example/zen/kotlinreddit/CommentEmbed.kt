import com.example.zen.kotlinreddit.Reddit
import com.example.zen.kotlinreddit.models.Comment
import com.example.zen.kotlinreddit.models.CommentHeader
import io.kotlintest.specs.StringSpec
import java.util.*

class CommentEmbed : StringSpec() {

  init {
    val desiredWidth = 320
    val header = CommentHeader()
    val comments = ArrayList<Comment>()
    Reddit.parseComments("/root/work/reddit/comment-mp4.json", "4rey0x", comments, header, desiredWidth)

    "selftext" { header.selftext shouldBe "" }
    "id" { header.id shouldBe "4rey0x" }
    "author" { header.author shouldBe  "GallowBoob" }
    "score" { header.score shouldBe 4853 }
    "preview thumb" { header.preview?.thumb shouldBe  "https://i.redditmedia.com/a1_KqfCCYEwn4INgjOHIPjQ8BNxppS51Bx0GRKP1Plc.gif?fit=crop&amp;crop=faces%2Centropy&amp;arh=2&amp;w=320&amp;fm=jpg&amp;s=bf92f602a505d94456400a5ce568c336".replace("amp;", "") }
    "preview source" { header.preview?.source shouldBe "https://i.redditmedia.com/a1_KqfCCYEwn4INgjOHIPjQ8BNxppS51Bx0GRKP1Plc.gif?fm=jpg&amp;s=41efffaadcd544ab5ccaf087a2d5fc52" }
    "url" { header.url shouldBe "https://i.imgur.com/z49xCGQ.gifv" }
    "title" { header.title shouldBe "This rescue bird can party" }
    "created_utc" { header.created shouldBe 1467753379L }
    "comments" { header.comments shouldBe 749 }
    "media" { header.media shouldBe null }

//    "comment 0 id" { comments[0].id shouldBe "d4zs7gn" }
//    "comment 0 body" { comments[0].body shouldBe "If this isn't allowed to be posted here, please let me know and I will remove it." }
//    "comment 0 created_utc" { comments[0].created shouldBe 1467708098L }
//    "comment 0 author" { comments[0].author shouldBe "mothh9" }
//    "comment 0 parent" { comments[0].comment_parent shouldBe "t3_4rbra9" }
  }

}