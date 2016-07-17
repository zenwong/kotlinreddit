
import com.example.zen.kotlinreddit.Reddit
import com.example.zen.kotlinreddit.models.Comment
import com.example.zen.kotlinreddit.models.CommentHeader
import io.kotlintest.specs.StringSpec
import java.util.*


class CommentParserTest : StringSpec() {

  init {
    val desiredWidth = 320
    val header = CommentHeader()
    val comments = ArrayList<Comment>()
    Reddit.parseComments(commentsJson, "4mfawk")

    "selftext" { header.selftext shouldBe "" }
    "id" { header.id shouldBe "4mfawk" }
    "author" { header.author shouldBe  "bjconnoisseur" }
    "score" { header.score shouldBe 7185 }
    "preview" { header.preview?.thumb shouldBe  "https://i.redditmedia.com/BlbZnUPuOenzwsHYyznp8P0gUN8aCxFPI7zUH2VUntc.jpg?fit=crop&amp;crop=faces%2Centropy&amp;arh=2&amp;w=320&amp;s=e0909a11c36b8077e015f9aee3c1e02b".replace("amp;", "") }
    "url" { header.url shouldBe "http://imgur.com/743FyVt" }
    "title" { header.title shouldBe "Except it is 116 today,and 118 tomorrow, why do I live here?" }
    "created_utc" { header.created shouldBe 1464991077L }
    "comments" { header.comments shouldBe 2407 }

    "comment 0 id" { comments[0].id shouldBe "d3v98dc" }
    "comment 0 body" { comments[0].body shouldBe "Bring back Leo and Satan." }
    "comment 0 created_utc" { comments[0].created shouldBe 1465004238L }
    "comment 0 author" { comments[0].author shouldBe "ProfessorMuffin" }
    "comment 0 parent" { comments[0].comment_parent shouldBe "t1_d3v8q0y" }

    "comment 1 id" { comments[1].id shouldBe "d3v8q0y" }
    "comment 1 body" { comments[1].body shouldBe "Socks ah fo yous feets silleh" }
    "comment 1 created_utc" { comments[1].created shouldBe 1465003357L }
    "comment 1 author" { comments[1].author shouldBe "Lyratheflirt" }
    "comment 1 parent" { comments[1].comment_parent shouldBe "t1_d3v88pc" }
  }


}

val commentsJson = """
[
  {
    "kind": "Listing",
    "data": {
      "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
      "children": [
        {
          "kind": "t3",
          "data": {
            "domain": "imgur.com",
            "banned_by": null,
            "media_embed": {

            },
            "subreddit": "funny",
            "selftext_html": null,
            "selftext": "",
            "likes": null,
            "suggested_sort": null,
            "user_reports": [

            ],
            "secure_media": null,
            "link_flair_text": null,
            "id": "4mfawk",
            "from_kind": null,
            "gilded": 0,
            "archived": false,
            "clicked": false,
            "report_reasons": null,
            "author": "bjconnoisseur",
            "media": null,
            "name": "t3_4mfawk",
            "score": 7185,
            "approved_by": null,
            "over_18": false,
            "hidden": false,
            "preview": {
              "images": [
                {
                  "source": {
                    "url": "https://i.redditmedia.com/BlbZnUPuOenzwsHYyznp8P0gUN8aCxFPI7zUH2VUntc.jpg?s=3c7f73efda126b085efa3f1c8244adb7",
                    "width": 625,
                    "height": 1070
                  },
                  "resolutions": [
                    {
                      "url": "https://i.redditmedia.com/BlbZnUPuOenzwsHYyznp8P0gUN8aCxFPI7zUH2VUntc.jpg?fit=crop&amp;crop=faces%2Centropy&amp;arh=2&amp;w=108&amp;s=1fde4004e5926677dd76a2569a2907ac",
                      "width": 108,
                      "height": 184
                    },
                    {
                      "url": "https://i.redditmedia.com/BlbZnUPuOenzwsHYyznp8P0gUN8aCxFPI7zUH2VUntc.jpg?fit=crop&amp;crop=faces%2Centropy&amp;arh=2&amp;w=216&amp;s=2b097f5eb0b8e48a784fe534b8038700",
                      "width": 216,
                      "height": 369
                    },
                    {
                      "url": "https://i.redditmedia.com/BlbZnUPuOenzwsHYyznp8P0gUN8aCxFPI7zUH2VUntc.jpg?fit=crop&amp;crop=faces%2Centropy&amp;arh=2&amp;w=320&amp;s=e0909a11c36b8077e015f9aee3c1e02b",
                      "width": 320,
                      "height": 547
                    }
                  ],
                  "variants": {

                  },
                  "id": "Gl5RwHJfMbErFgi_d8Fiz9GO8qhtDKxoa4xtkvqPioY"
                }
              ]
            },
            "thumbnail": "http://a.thumbs.redditmedia.com/GzbKheUcyNsv_XOo6RkSJ4R7nAmTDZwtCEGtgsH9SJ8.jpg",
            "subreddit_id": "t5_2qh33",
            "edited": false,
            "link_flair_css_class": null,
            "author_flair_css_class": null,
            "downs": 0,
            "mod_reports": [

            ],
            "secure_media_embed": {

            },
            "saved": false,
            "removal_reason": null,
            "post_hint": "link",
            "stickied": false,
            "from": null,
            "is_self": false,
            "from_id": null,
            "permalink": "/r/funny/comments/4mfawk/except_it_is_116_todayand_118_tomorrow_why_do_i/",
            "locked": false,
            "hide_score": false,
            "created": 1465019877.0,
            "url": "http://imgur.com/743FyVt",
            "author_flair_text": null,
            "quarantine": false,
            "title": "Except it is 116 today,and 118 tomorrow, why do I live here?",
            "created_utc": 1464991077.0,
            "ups": 7185,
            "upvote_ratio": 0.89,
            "num_comments": 2407,
            "visited": false,
            "num_reports": null,
            "distinguished": null
          }
        }
      ],
      "after": null,
      "before": null
    }
  },
  {
    "kind": "Listing",
    "data": {
      "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
      "children": [
        {
          "kind": "t1",
          "data": {
            "subreddit_id": "t5_2qh33",
            "banned_by": null,
            "removal_reason": null,
            "link_id": "t3_4mfawk",
            "likes": null,
            "replies": {
              "kind": "Listing",
              "data": {
                "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                "children": [
                  {
                    "kind": "t1",
                    "data": {
                      "subreddit_id": "t5_2qh33",
                      "banned_by": null,
                      "removal_reason": null,
                      "link_id": "t3_4mfawk",
                      "likes": null,
                      "replies": {
                        "kind": "Listing",
                        "data": {
                          "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                          "children": [
                            {
                              "kind": "t1",
                              "data": {
                                "subreddit_id": "t5_2qh33",
                                "banned_by": null,
                                "removal_reason": null,
                                "link_id": "t3_4mfawk",
                                "likes": null,
                                "replies": {
                                  "kind": "Listing",
                                  "data": {
                                    "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                    "children": [
                                      {
                                        "kind": "t1",
                                        "data": {
                                          "subreddit_id": "t5_2qh33",
                                          "banned_by": null,
                                          "removal_reason": null,
                                          "link_id": "t3_4mfawk",
                                          "likes": null,
                                          "replies": {
                                            "kind": "Listing",
                                            "data": {
                                              "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                              "children": [
                                                {
                                                  "kind": "t1",
                                                  "data": {
                                                    "subreddit_id": "t5_2qh33",
                                                    "banned_by": null,
                                                    "removal_reason": null,
                                                    "link_id": "t3_4mfawk",
                                                    "likes": null,
                                                    "replies": {
                                                      "kind": "Listing",
                                                      "data": {
                                                        "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                                        "children": [
                                                          {
                                                            "kind": "t1",
                                                            "data": {
                                                              "subreddit_id": "t5_2qh33",
                                                              "banned_by": null,
                                                              "removal_reason": null,
                                                              "link_id": "t3_4mfawk",
                                                              "likes": null,
                                                              "replies": {
                                                                "kind": "Listing",
                                                                "data": {
                                                                  "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                                                  "children": [
                                                                    {
                                                                      "kind": "t1",
                                                                      "data": {
                                                                        "subreddit_id": "t5_2qh33",
                                                                        "banned_by": null,
                                                                        "removal_reason": null,
                                                                        "link_id": "t3_4mfawk",
                                                                        "likes": null,
                                                                        "replies": {
                                                                          "kind": "Listing",
                                                                          "data": {
                                                                            "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                                                            "children": [
                                                                              {
                                                                                "kind": "t1",
                                                                                "data": {
                                                                                  "subreddit_id": "t5_2qh33",
                                                                                  "banned_by": null,
                                                                                  "removal_reason": null,
                                                                                  "link_id": "t3_4mfawk",
                                                                                  "likes": null,
                                                                                  "replies": {
                                                                                    "kind": "Listing",
                                                                                    "data": {
                                                                                      "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                                                                      "children": [
                                                                                        {
                                                                                          "kind": "more",
                                                                                          "data": {
                                                                                            "count": 1,
                                                                                            "parent_id": "t1_d3v98dc",
                                                                                            "children": [
                                                                                              "d3vbbw0"
                                                                                            ],
                                                                                            "name": "t1_d3vbbw0",
                                                                                            "id": "d3vbbw0"
                                                                                          }
                                                                                        }
                                                                                      ],
                                                                                      "after": null,
                                                                                      "before": null
                                                                                    }
                                                                                  },
                                                                                  "user_reports": [

                                                                                  ],
                                                                                  "saved": false,
                                                                                  "id": "d3v98dc",
                                                                                  "gilded": 0,
                                                                                  "archived": false,
                                                                                  "report_reasons": null,
                                                                                  "author": "ProfessorMuffin",
                                                                                  "parent_id": "t1_d3v8q0y",
                                                                                  "score": 29,
                                                                                  "approved_by": null,
                                                                                  "controversiality": 0,
                                                                                  "body": "Bring back Leo and Satan.",
                                                                                  "edited": false,
                                                                                  "author_flair_css_class": null,
                                                                                  "downs": 0,
                                                                                  "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Bring back Leo and Satan.&lt;/p&gt;\n&lt;/div&gt;",
                                                                                  "subreddit": "funny",
                                                                                  "name": "t1_d3v98dc",
                                                                                  "score_hidden": false,
                                                                                  "stickied": false,
                                                                                  "created": 1465033038.0,
                                                                                  "author_flair_text": null,
                                                                                  "created_utc": 1465004238.0,
                                                                                  "distinguished": null,
                                                                                  "mod_reports": [

                                                                                  ],
                                                                                  "num_reports": null,
                                                                                  "ups": 29
                                                                                }
                                                                              }
                                                                            ],
                                                                            "after": null,
                                                                            "before": null
                                                                          }
                                                                        },
                                                                        "user_reports": [

                                                                        ],
                                                                        "saved": false,
                                                                        "id": "d3v8q0y",
                                                                        "gilded": 0,
                                                                        "archived": false,
                                                                        "report_reasons": null,
                                                                        "author": "Lyratheflirt",
                                                                        "parent_id": "t1_d3v88pc",
                                                                        "score": 89,
                                                                        "approved_by": null,
                                                                        "controversiality": 0,
                                                                        "body": "Socks ah fo yous feets silleh",
                                                                        "edited": false,
                                                                        "author_flair_css_class": null,
                                                                        "downs": 0,
                                                                        "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Socks ah fo yous feets silleh&lt;/p&gt;\n&lt;/div&gt;",
                                                                        "subreddit": "funny",
                                                                        "name": "t1_d3v8q0y",
                                                                        "score_hidden": false,
                                                                        "stickied": false,
                                                                        "created": 1465032157.0,
                                                                        "author_flair_text": null,
                                                                        "created_utc": 1465003357.0,
                                                                        "distinguished": null,
                                                                        "mod_reports": [

                                                                        ],
                                                                        "num_reports": null,
                                                                        "ups": 89
                                                                      }
                                                                    },
                                                                    {
                                                                      "kind": "t1",
                                                                      "data": {
                                                                        "subreddit_id": "t5_2qh33",
                                                                        "banned_by": null,
                                                                        "removal_reason": null,
                                                                        "link_id": "t3_4mfawk",
                                                                        "likes": null,
                                                                        "replies": "",
                                                                        "user_reports": [

                                                                        ],
                                                                        "saved": false,
                                                                        "id": "d3v93g7",
                                                                        "gilded": 0,
                                                                        "archived": false,
                                                                        "report_reasons": null,
                                                                        "author": "GaryV83",
                                                                        "parent_id": "t1_d3v88pc",
                                                                        "score": 48,
                                                                        "approved_by": null,
                                                                        "controversiality": 0,
                                                                        "body": "You have been made a moderator of /r/malefashionadvice.",
                                                                        "edited": false,
                                                                        "author_flair_css_class": null,
                                                                        "downs": 0,
                                                                        "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;You have been made a moderator of &lt;a href=\"/r/malefashionadvice\"&gt;/r/malefashionadvice&lt;/a&gt;.&lt;/p&gt;\n&lt;/div&gt;",
                                                                        "subreddit": "funny",
                                                                        "name": "t1_d3v93g7",
                                                                        "score_hidden": false,
                                                                        "stickied": false,
                                                                        "created": 1465032808.0,
                                                                        "author_flair_text": null,
                                                                        "created_utc": 1465004008.0,
                                                                        "distinguished": null,
                                                                        "mod_reports": [

                                                                        ],
                                                                        "num_reports": null,
                                                                        "ups": 48
                                                                      }
                                                                    }
                                                                  ],
                                                                  "after": null,
                                                                  "before": null
                                                                }
                                                              },
                                                              "user_reports": [

                                                              ],
                                                              "saved": false,
                                                              "id": "d3v88pc",
                                                              "gilded": 0,
                                                              "archived": false,
                                                              "report_reasons": null,
                                                              "author": "086bagofcats",
                                                              "parent_id": "t1_d3v82jx",
                                                              "score": 310,
                                                              "approved_by": null,
                                                              "controversiality": 0,
                                                              "body": "Good job you had socks on to protect your feet ;p",
                                                              "edited": false,
                                                              "author_flair_css_class": null,
                                                              "downs": 0,
                                                              "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Good job you had socks on to protect your feet ;p&lt;/p&gt;\n&lt;/div&gt;",
                                                              "subreddit": "funny",
                                                              "name": "t1_d3v88pc",
                                                              "score_hidden": false,
                                                              "stickied": false,
                                                              "created": 1465031346.0,
                                                              "author_flair_text": null,
                                                              "created_utc": 1465002546.0,
                                                              "distinguished": null,
                                                              "mod_reports": [

                                                              ],
                                                              "num_reports": null,
                                                              "ups": 310
                                                            }
                                                          },
                                                          {
                                                            "kind": "t1",
                                                            "data": {
                                                              "subreddit_id": "t5_2qh33",
                                                              "banned_by": null,
                                                              "removal_reason": null,
                                                              "link_id": "t3_4mfawk",
                                                              "likes": null,
                                                              "replies": {
                                                                "kind": "Listing",
                                                                "data": {
                                                                  "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                                                  "children": [
                                                                    {
                                                                      "kind": "t1",
                                                                      "data": {
                                                                        "subreddit_id": "t5_2qh33",
                                                                        "banned_by": null,
                                                                        "removal_reason": null,
                                                                        "link_id": "t3_4mfawk",
                                                                        "likes": null,
                                                                        "replies": {
                                                                          "kind": "Listing",
                                                                          "data": {
                                                                            "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                                                            "children": [
                                                                              {
                                                                                "kind": "t1",
                                                                                "data": {
                                                                                  "subreddit_id": "t5_2qh33",
                                                                                  "banned_by": null,
                                                                                  "removal_reason": null,
                                                                                  "link_id": "t3_4mfawk",
                                                                                  "likes": null,
                                                                                  "replies": {
                                                                                    "kind": "Listing",
                                                                                    "data": {
                                                                                      "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                                                                      "children": [
                                                                                        {
                                                                                          "kind": "t1",
                                                                                          "data": {
                                                                                            "subreddit_id": "t5_2qh33",
                                                                                            "banned_by": null,
                                                                                            "removal_reason": null,
                                                                                            "link_id": "t3_4mfawk",
                                                                                            "likes": null,
                                                                                            "replies": "",
                                                                                            "user_reports": [

                                                                                            ],
                                                                                            "saved": false,
                                                                                            "id": "d3v9e31",
                                                                                            "gilded": 0,
                                                                                            "archived": false,
                                                                                            "report_reasons": null,
                                                                                            "author": "Artiemes",
                                                                                            "parent_id": "t1_d3v8v2a",
                                                                                            "score": 61,
                                                                                            "approved_by": null,
                                                                                            "controversiality": 0,
                                                                                            "body": "Username checks out.",
                                                                                            "edited": false,
                                                                                            "author_flair_css_class": null,
                                                                                            "downs": 0,
                                                                                            "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Username checks out.&lt;/p&gt;\n&lt;/div&gt;",
                                                                                            "subreddit": "funny",
                                                                                            "name": "t1_d3v9e31",
                                                                                            "score_hidden": false,
                                                                                            "stickied": false,
                                                                                            "created": 1465033314.0,
                                                                                            "author_flair_text": null,
                                                                                            "created_utc": 1465004514.0,
                                                                                            "distinguished": null,
                                                                                            "mod_reports": [

                                                                                            ],
                                                                                            "num_reports": null,
                                                                                            "ups": 61
                                                                                          }
                                                                                        },
                                                                                        {
                                                                                          "kind": "more",
                                                                                          "data": {
                                                                                            "count": 10,
                                                                                            "parent_id": "t1_d3v8v2a",
                                                                                            "children": [
                                                                                              "d3vbzrn",
                                                                                              "d3v9mud",
                                                                                              "d3v9hbx"
                                                                                            ],
                                                                                            "name": "t1_d3vbzrn",
                                                                                            "id": "d3vbzrn"
                                                                                          }
                                                                                        }
                                                                                      ],
                                                                                      "after": null,
                                                                                      "before": null
                                                                                    }
                                                                                  },
                                                                                  "user_reports": [

                                                                                  ],
                                                                                  "saved": false,
                                                                                  "id": "d3v8v2a",
                                                                                  "gilded": 0,
                                                                                  "archived": false,
                                                                                  "report_reasons": null,
                                                                                  "author": "mcsparklenuts",
                                                                                  "parent_id": "t1_d3v8fir",
                                                                                  "score": 174,
                                                                                  "approved_by": null,
                                                                                  "controversiality": 0,
                                                                                  "body": "You don't give us gays enough credit. We wouldn't be caught dead in crocs either.",
                                                                                  "edited": false,
                                                                                  "author_flair_css_class": null,
                                                                                  "downs": 0,
                                                                                  "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;You don&amp;#39;t give us gays enough credit. We wouldn&amp;#39;t be caught dead in crocs either.&lt;/p&gt;\n&lt;/div&gt;",
                                                                                  "subreddit": "funny",
                                                                                  "name": "t1_d3v8v2a",
                                                                                  "score_hidden": false,
                                                                                  "stickied": false,
                                                                                  "created": 1465032399.0,
                                                                                  "author_flair_text": null,
                                                                                  "created_utc": 1465003599.0,
                                                                                  "distinguished": null,
                                                                                  "mod_reports": [

                                                                                  ],
                                                                                  "num_reports": null,
                                                                                  "ups": 174
                                                                                }
                                                                              },
                                                                              {
                                                                                "kind": "t1",
                                                                                "data": {
                                                                                  "subreddit_id": "t5_2qh33",
                                                                                  "banned_by": null,
                                                                                  "removal_reason": null,
                                                                                  "link_id": "t3_4mfawk",
                                                                                  "likes": null,
                                                                                  "replies": {
                                                                                    "kind": "Listing",
                                                                                    "data": {
                                                                                      "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                                                                      "children": [
                                                                                        {
                                                                                          "kind": "more",
                                                                                          "data": {
                                                                                            "count": 1,
                                                                                            "parent_id": "t1_d3v8we9",
                                                                                            "children": [
                                                                                              "d3vbqv8"
                                                                                            ],
                                                                                            "name": "t1_d3vbqv8",
                                                                                            "id": "d3vbqv8"
                                                                                          }
                                                                                        }
                                                                                      ],
                                                                                      "after": null,
                                                                                      "before": null
                                                                                    }
                                                                                  },
                                                                                  "user_reports": [

                                                                                  ],
                                                                                  "saved": false,
                                                                                  "id": "d3v8we9",
                                                                                  "gilded": 0,
                                                                                  "archived": false,
                                                                                  "report_reasons": null,
                                                                                  "author": "Kitty_McBitty",
                                                                                  "parent_id": "t1_d3v8fir",
                                                                                  "score": 38,
                                                                                  "approved_by": null,
                                                                                  "controversiality": 0,
                                                                                  "body": "I dunno, I'm pretty sure the gays would be the first to shun crocs. ",
                                                                                  "edited": false,
                                                                                  "author_flair_css_class": null,
                                                                                  "downs": 0,
                                                                                  "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;I dunno, I&amp;#39;m pretty sure the gays would be the first to shun crocs. &lt;/p&gt;\n&lt;/div&gt;",
                                                                                  "subreddit": "funny",
                                                                                  "name": "t1_d3v8we9",
                                                                                  "score_hidden": false,
                                                                                  "stickied": false,
                                                                                  "created": 1465032464.0,
                                                                                  "author_flair_text": null,
                                                                                  "created_utc": 1465003664.0,
                                                                                  "distinguished": null,
                                                                                  "mod_reports": [

                                                                                  ],
                                                                                  "num_reports": null,
                                                                                  "ups": 38
                                                                                }
                                                                              },
                                                                              {
                                                                                "kind": "more",
                                                                                "data": {
                                                                                  "count": 24,
                                                                                  "parent_id": "t1_d3v8fir",
                                                                                  "id": "d3v90iy",
                                                                                  "name": "t1_d3v90iy",
                                                                                  "children": [
                                                                                    "d3v90iy",
                                                                                    "d3v8xu3",
                                                                                    "d3va2xb",
                                                                                    "d3v8uoh",
                                                                                    "d3v94dx",
                                                                                    "d3vb0re"
                                                                                  ]
                                                                                }
                                                                              }
                                                                            ],
                                                                            "after": null,
                                                                            "before": null
                                                                          }
                                                                        },
                                                                        "user_reports": [

                                                                        ],
                                                                        "saved": false,
                                                                        "id": "d3v8fir",
                                                                        "gilded": 0,
                                                                        "archived": false,
                                                                        "report_reasons": null,
                                                                        "author": "chiliedogg",
                                                                        "parent_id": "t1_d3v89bk",
                                                                        "score": 205,
                                                                        "approved_by": null,
                                                                        "controversiality": 0,
                                                                        "body": "Why is wearing crocs like getting a blowjob from a man?\n\nBoth feel great until you look down and realize you're gay.",
                                                                        "edited": false,
                                                                        "author_flair_css_class": null,
                                                                        "downs": 0,
                                                                        "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Why is wearing crocs like getting a blowjob from a man?&lt;/p&gt;\n\n&lt;p&gt;Both feel great until you look down and realize you&amp;#39;re gay.&lt;/p&gt;\n&lt;/div&gt;",
                                                                        "subreddit": "funny",
                                                                        "name": "t1_d3v8fir",
                                                                        "score_hidden": false,
                                                                        "stickied": false,
                                                                        "created": 1465031660.0,
                                                                        "author_flair_text": null,
                                                                        "created_utc": 1465002860.0,
                                                                        "distinguished": null,
                                                                        "mod_reports": [

                                                                        ],
                                                                        "num_reports": null,
                                                                        "ups": 205
                                                                      }
                                                                    }
                                                                  ],
                                                                  "after": null,
                                                                  "before": null
                                                                }
                                                              },
                                                              "user_reports": [

                                                              ],
                                                              "saved": false,
                                                              "id": "d3v89bk",
                                                              "gilded": 0,
                                                              "archived": false,
                                                              "report_reasons": null,
                                                              "author": "Allanbag",
                                                              "parent_id": "t1_d3v82jx",
                                                              "score": 169,
                                                              "approved_by": null,
                                                              "controversiality": 0,
                                                              "body": "I think the sand was just doing you a service.",
                                                              "edited": false,
                                                              "author_flair_css_class": null,
                                                              "downs": 0,
                                                              "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;I think the sand was just doing you a service.&lt;/p&gt;\n&lt;/div&gt;",
                                                              "subreddit": "funny",
                                                              "name": "t1_d3v89bk",
                                                              "score_hidden": false,
                                                              "stickied": false,
                                                              "created": 1465031375.0,
                                                              "author_flair_text": null,
                                                              "created_utc": 1465002575.0,
                                                              "distinguished": null,
                                                              "mod_reports": [

                                                              ],
                                                              "num_reports": null,
                                                              "ups": 169
                                                            }
                                                          },
                                                          {
                                                            "kind": "t1",
                                                            "data": {
                                                              "subreddit_id": "t5_2qh33",
                                                              "banned_by": null,
                                                              "removal_reason": null,
                                                              "link_id": "t3_4mfawk",
                                                              "likes": null,
                                                              "replies": {
                                                                "kind": "Listing",
                                                                "data": {
                                                                  "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                                                  "children": [
                                                                    {
                                                                      "kind": "t1",
                                                                      "data": {
                                                                        "subreddit_id": "t5_2qh33",
                                                                        "banned_by": null,
                                                                        "removal_reason": null,
                                                                        "link_id": "t3_4mfawk",
                                                                        "likes": null,
                                                                        "replies": {
                                                                          "kind": "Listing",
                                                                          "data": {
                                                                            "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                                                            "children": [
                                                                              {
                                                                                "kind": "more",
                                                                                "data": {
                                                                                  "count": 5,
                                                                                  "parent_id": "t1_d3v8lcl",
                                                                                  "id": "d3vblo9",
                                                                                  "name": "t1_d3vblo9",
                                                                                  "children": [
                                                                                    "d3vblo9",
                                                                                    "d3vbgqb",
                                                                                    "d3v911x",
                                                                                    "d3v9ffy",
                                                                                    "d3vb4kf"
                                                                                  ]
                                                                                }
                                                                              }
                                                                            ],
                                                                            "after": null,
                                                                            "before": null
                                                                          }
                                                                        },
                                                                        "user_reports": [

                                                                        ],
                                                                        "saved": false,
                                                                        "id": "d3v8lcl",
                                                                        "gilded": 0,
                                                                        "archived": false,
                                                                        "report_reasons": null,
                                                                        "author": "MyNameIsRags",
                                                                        "parent_id": "t1_d3v89un",
                                                                        "score": 20,
                                                                        "approved_by": null,
                                                                        "controversiality": 0,
                                                                        "body": "Last time I went to a shoe store I saw crocs that were actual proper sandals. They still had the shit rubber ones everybody remembers, but hey, they branched out at least.",
                                                                        "edited": false,
                                                                        "author_flair_css_class": null,
                                                                        "downs": 0,
                                                                        "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Last time I went to a shoe store I saw crocs that were actual proper sandals. They still had the shit rubber ones everybody remembers, but hey, they branched out at least.&lt;/p&gt;\n&lt;/div&gt;",
                                                                        "subreddit": "funny",
                                                                        "name": "t1_d3v8lcl",
                                                                        "score_hidden": false,
                                                                        "stickied": false,
                                                                        "created": 1465031932.0,
                                                                        "author_flair_text": null,
                                                                        "created_utc": 1465003132.0,
                                                                        "distinguished": null,
                                                                        "mod_reports": [

                                                                        ],
                                                                        "num_reports": null,
                                                                        "ups": 20
                                                                      }
                                                                    },
                                                                    {
                                                                      "kind": "more",
                                                                      "data": {
                                                                        "count": 2,
                                                                        "parent_id": "t1_d3v89un",
                                                                        "children": [
                                                                          "d3vc16z",
                                                                          "d3v8yvy"
                                                                        ],
                                                                        "name": "t1_d3vc16z",
                                                                        "id": "d3vc16z"
                                                                      }
                                                                    }
                                                                  ],
                                                                  "after": null,
                                                                  "before": null
                                                                }
                                                              },
                                                              "user_reports": [

                                                              ],
                                                              "saved": false,
                                                              "id": "d3v89un",
                                                              "gilded": 0,
                                                              "archived": false,
                                                              "report_reasons": null,
                                                              "author": "NeedHelpWithExcel",
                                                              "parent_id": "t1_d3v82jx",
                                                              "score": 50,
                                                              "approved_by": null,
                                                              "controversiality": 0,
                                                              "body": "I was about to say \"Wow you were a young lad when crocs were around?\"\n\nThen I realized that crocs came out 14 years ago :/",
                                                              "edited": false,
                                                              "author_flair_css_class": null,
                                                              "downs": 0,
                                                              "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;I was about to say &amp;quot;Wow you were a young lad when crocs were around?&amp;quot;&lt;/p&gt;\n\n&lt;p&gt;Then I realized that crocs came out 14 years ago :/&lt;/p&gt;\n&lt;/div&gt;",
                                                              "subreddit": "funny",
                                                              "name": "t1_d3v89un",
                                                              "score_hidden": false,
                                                              "stickied": false,
                                                              "created": 1465031400.0,
                                                              "author_flair_text": null,
                                                              "created_utc": 1465002600.0,
                                                              "distinguished": null,
                                                              "mod_reports": [

                                                              ],
                                                              "num_reports": null,
                                                              "ups": 50
                                                            }
                                                          },
                                                          {
                                                            "kind": "t1",
                                                            "data": {
                                                              "subreddit_id": "t5_2qh33",
                                                              "banned_by": null,
                                                              "removal_reason": null,
                                                              "link_id": "t3_4mfawk",
                                                              "likes": null,
                                                              "replies": {
                                                                "kind": "Listing",
                                                                "data": {
                                                                  "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                                                  "children": [
                                                                    {
                                                                      "kind": "more",
                                                                      "data": {
                                                                        "count": 1,
                                                                        "parent_id": "t1_d3v89z9",
                                                                        "children": [
                                                                          "d3vbx28"
                                                                        ],
                                                                        "name": "t1_d3vbx28",
                                                                        "id": "d3vbx28"
                                                                      }
                                                                    }
                                                                  ],
                                                                  "after": null,
                                                                  "before": null
                                                                }
                                                              },
                                                              "user_reports": [

                                                              ],
                                                              "saved": false,
                                                              "id": "d3v89z9",
                                                              "gilded": 0,
                                                              "archived": false,
                                                              "report_reasons": null,
                                                              "author": "keath",
                                                              "parent_id": "t1_d3v82jx",
                                                              "score": 41,
                                                              "approved_by": null,
                                                              "controversiality": 0,
                                                              "body": "In Death Valley, the same sight is visible from the air-conditioned car as outside of it.",
                                                              "edited": false,
                                                              "author_flair_css_class": null,
                                                              "downs": 0,
                                                              "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;In Death Valley, the same sight is visible from the air-conditioned car as outside of it.&lt;/p&gt;\n&lt;/div&gt;",
                                                              "subreddit": "funny",
                                                              "name": "t1_d3v89z9",
                                                              "score_hidden": false,
                                                              "stickied": false,
                                                              "created": 1465031406.0,
                                                              "author_flair_text": null,
                                                              "created_utc": 1465002606.0,
                                                              "distinguished": null,
                                                              "mod_reports": [

                                                              ],
                                                              "num_reports": null,
                                                              "ups": 41
                                                            }
                                                          },
                                                          {
                                                            "kind": "t1",
                                                            "data": {
                                                              "subreddit_id": "t5_2qh33",
                                                              "banned_by": null,
                                                              "removal_reason": null,
                                                              "link_id": "t3_4mfawk",
                                                              "likes": null,
                                                              "replies": {
                                                                "kind": "Listing",
                                                                "data": {
                                                                  "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                                                  "children": [
                                                                    {
                                                                      "kind": "t1",
                                                                      "data": {
                                                                        "subreddit_id": "t5_2qh33",
                                                                        "banned_by": null,
                                                                        "removal_reason": null,
                                                                        "link_id": "t3_4mfawk",
                                                                        "likes": null,
                                                                        "replies": {
                                                                          "kind": "Listing",
                                                                          "data": {
                                                                            "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                                                            "children": [
                                                                              {
                                                                                "kind": "t1",
                                                                                "data": {
                                                                                  "subreddit_id": "t5_2qh33",
                                                                                  "banned_by": null,
                                                                                  "removal_reason": null,
                                                                                  "link_id": "t3_4mfawk",
                                                                                  "likes": null,
                                                                                  "replies": {
                                                                                    "kind": "Listing",
                                                                                    "data": {
                                                                                      "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                                                                      "children": [
                                                                                        {
                                                                                          "kind": "more",
                                                                                          "data": {
                                                                                            "count": 1,
                                                                                            "parent_id": "t1_d3v8foy",
                                                                                            "children": [
                                                                                              "d3vb63q"
                                                                                            ],
                                                                                            "name": "t1_d3vb63q",
                                                                                            "id": "d3vb63q"
                                                                                          }
                                                                                        }
                                                                                      ],
                                                                                      "after": null,
                                                                                      "before": null
                                                                                    }
                                                                                  },
                                                                                  "user_reports": [

                                                                                  ],
                                                                                  "saved": false,
                                                                                  "id": "d3v8foy",
                                                                                  "gilded": 0,
                                                                                  "archived": false,
                                                                                  "report_reasons": null,
                                                                                  "author": "twominitsturkish",
                                                                                  "parent_id": "t1_d3v89pq",
                                                                                  "score": 26,
                                                                                  "approved_by": null,
                                                                                  "controversiality": 0,
                                                                                  "body": "He forgot his own name, you think he'll remember a family vacation in the 5th grade?  ",
                                                                                  "edited": false,
                                                                                  "author_flair_css_class": null,
                                                                                  "downs": 0,
                                                                                  "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;He forgot his own name, you think he&amp;#39;ll remember a family vacation in the 5th grade?  &lt;/p&gt;\n&lt;/div&gt;",
                                                                                  "subreddit": "funny",
                                                                                  "name": "t1_d3v8foy",
                                                                                  "score_hidden": false,
                                                                                  "stickied": false,
                                                                                  "created": 1465031668.0,
                                                                                  "author_flair_text": null,
                                                                                  "created_utc": 1465002868.0,
                                                                                  "distinguished": null,
                                                                                  "mod_reports": [

                                                                                  ],
                                                                                  "num_reports": null,
                                                                                  "ups": 26
                                                                                }
                                                                              },
                                                                              {
                                                                                "kind": "more",
                                                                                "data": {
                                                                                  "count": 1,
                                                                                  "parent_id": "t1_d3v89pq",
                                                                                  "id": "d3v9j9p",
                                                                                  "name": "t1_d3v9j9p",
                                                                                  "children": [
                                                                                    "d3v9j9p"
                                                                                  ]
                                                                                }
                                                                              }
                                                                            ],
                                                                            "after": null,
                                                                            "before": null
                                                                          }
                                                                        },
                                                                        "user_reports": [

                                                                        ],
                                                                        "saved": false,
                                                                        "id": "d3v89pq",
                                                                        "gilded": 0,
                                                                        "archived": false,
                                                                        "report_reasons": null,
                                                                        "author": "zappa325",
                                                                        "parent_id": "t1_d3v88t9",
                                                                        "score": 28,
                                                                        "approved_by": null,
                                                                        "controversiality": 0,
                                                                        "body": "Have you ever been to Death Valley?",
                                                                        "edited": false,
                                                                        "author_flair_css_class": null,
                                                                        "downs": 0,
                                                                        "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Have you ever been to Death Valley?&lt;/p&gt;\n&lt;/div&gt;",
                                                                        "subreddit": "funny",
                                                                        "name": "t1_d3v89pq",
                                                                        "score_hidden": false,
                                                                        "stickied": false,
                                                                        "created": 1465031394.0,
                                                                        "author_flair_text": null,
                                                                        "created_utc": 1465002594.0,
                                                                        "distinguished": null,
                                                                        "mod_reports": [

                                                                        ],
                                                                        "num_reports": null,
                                                                        "ups": 28
                                                                      }
                                                                    },
                                                                    {
                                                                      "kind": "more",
                                                                      "data": {
                                                                        "count": 1,
                                                                        "parent_id": "t1_d3v88t9",
                                                                        "children": [
                                                                          "d3v8av9"
                                                                        ],
                                                                        "name": "t1_d3v8av9",
                                                                        "id": "d3v8av9"
                                                                      }
                                                                    }
                                                                  ],
                                                                  "after": null,
                                                                  "before": null
                                                                }
                                                              },
                                                              "user_reports": [

                                                              ],
                                                              "saved": false,
                                                              "id": "d3v88t9",
                                                              "gilded": 0,
                                                              "archived": false,
                                                              "report_reasons": null,
                                                              "author": "ihadanamebutforgot",
                                                              "parent_id": "t1_d3v82jx",
                                                              "score": 16,
                                                              "approved_by": null,
                                                              "controversiality": 0,
                                                              "body": "Whoa forget the Crocs, the sand was *boiling?!*",
                                                              "edited": false,
                                                              "author_flair_css_class": null,
                                                              "downs": 0,
                                                              "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Whoa forget the Crocs, the sand was &lt;em&gt;boiling?!&lt;/em&gt;&lt;/p&gt;\n&lt;/div&gt;",
                                                              "subreddit": "funny",
                                                              "name": "t1_d3v88t9",
                                                              "score_hidden": false,
                                                              "stickied": false,
                                                              "created": 1465031351.0,
                                                              "author_flair_text": null,
                                                              "created_utc": 1465002551.0,
                                                              "distinguished": null,
                                                              "mod_reports": [

                                                              ],
                                                              "num_reports": null,
                                                              "ups": 16
                                                            }
                                                          },
                                                          {
                                                            "kind": "t1",
                                                            "data": {
                                                              "subreddit_id": "t5_2qh33",
                                                              "banned_by": null,
                                                              "removal_reason": null,
                                                              "link_id": "t3_4mfawk",
                                                              "likes": null,
                                                              "replies": {
                                                                "kind": "Listing",
                                                                "data": {
                                                                  "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                                                  "children": [
                                                                    {
                                                                      "kind": "t1",
                                                                      "data": {
                                                                        "subreddit_id": "t5_2qh33",
                                                                        "banned_by": null,
                                                                        "removal_reason": null,
                                                                        "link_id": "t3_4mfawk",
                                                                        "likes": null,
                                                                        "replies": {
                                                                          "kind": "Listing",
                                                                          "data": {
                                                                            "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                                                            "children": [
                                                                              {
                                                                                "kind": "more",
                                                                                "data": {
                                                                                  "count": 1,
                                                                                  "parent_id": "t1_d3v8ve9",
                                                                                  "id": "d3vbj63",
                                                                                  "name": "t1_d3vbj63",
                                                                                  "children": [
                                                                                    "d3vbj63"
                                                                                  ]
                                                                                }
                                                                              }
                                                                            ],
                                                                            "after": null,
                                                                            "before": null
                                                                          }
                                                                        },
                                                                        "user_reports": [

                                                                        ],
                                                                        "saved": false,
                                                                        "id": "d3v8ve9",
                                                                        "gilded": 0,
                                                                        "archived": false,
                                                                        "report_reasons": null,
                                                                        "author": "twominitsturkish",
                                                                        "parent_id": "t1_d3v8azw",
                                                                        "score": 50,
                                                                        "approved_by": null,
                                                                        "controversiality": 0,
                                                                        "body": "[55 million years or so.](https://en.wikipedia.org/wiki/Crocodile) ",
                                                                        "edited": false,
                                                                        "author_flair_css_class": null,
                                                                        "downs": 0,
                                                                        "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;&lt;a href=\"https://en.wikipedia.org/wiki/Crocodile\"&gt;55 million years or so.&lt;/a&gt; &lt;/p&gt;\n&lt;/div&gt;",
                                                                        "subreddit": "funny",
                                                                        "name": "t1_d3v8ve9",
                                                                        "score_hidden": false,
                                                                        "stickied": false,
                                                                        "created": 1465032416.0,
                                                                        "author_flair_text": null,
                                                                        "created_utc": 1465003616.0,
                                                                        "distinguished": null,
                                                                        "mod_reports": [

                                                                        ],
                                                                        "num_reports": null,
                                                                        "ups": 50
                                                                      }
                                                                    },
                                                                    {
                                                                      "kind": "more",
                                                                      "data": {
                                                                        "count": 1,
                                                                        "parent_id": "t1_d3v8azw",
                                                                        "children": [
                                                                          "d3v8trl"
                                                                        ],
                                                                        "name": "t1_d3v8trl",
                                                                        "id": "d3v8trl"
                                                                      }
                                                                    }
                                                                  ],
                                                                  "after": null,
                                                                  "before": null
                                                                }
                                                              },
                                                              "user_reports": [

                                                              ],
                                                              "saved": false,
                                                              "id": "d3v8azw",
                                                              "gilded": 0,
                                                              "archived": false,
                                                              "report_reasons": null,
                                                              "author": "I_love_black_girls",
                                                              "parent_id": "t1_d3v82jx",
                                                              "score": 10,
                                                              "approved_by": null,
                                                              "controversiality": 0,
                                                              "body": "How long have Crocs been around?",
                                                              "edited": false,
                                                              "author_flair_css_class": null,
                                                              "downs": 0,
                                                              "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;How long have Crocs been around?&lt;/p&gt;\n&lt;/div&gt;",
                                                              "subreddit": "funny",
                                                              "name": "t1_d3v8azw",
                                                              "score_hidden": false,
                                                              "stickied": false,
                                                              "created": 1465031455.0,
                                                              "author_flair_text": null,
                                                              "created_utc": 1465002655.0,
                                                              "distinguished": null,
                                                              "mod_reports": [

                                                              ],
                                                              "num_reports": null,
                                                              "ups": 10
                                                            }
                                                          },
                                                          {
                                                            "kind": "more",
                                                            "data": {
                                                              "count": 19,
                                                              "parent_id": "t1_d3v82jx",
                                                              "id": "d3vckxt",
                                                              "name": "t1_d3vckxt",
                                                              "children": [
                                                                "d3vckxt",
                                                                "d3v90si",
                                                                "d3vblli",
                                                                "d3vagxt",
                                                                "d3v8d3c",
                                                                "d3vb7ex",
                                                                "d3vbfrf",
                                                                "d3v91os",
                                                                "d3v9fpz",
                                                                "d3v89tb",
                                                                "d3vageo",
                                                                "d3vau7n",
                                                                "d3val90",
                                                                "d3va9me",
                                                                "d3vbzzr",
                                                                "d3vbba0",
                                                                "d3vbiof",
                                                                "d3vc20t",
                                                                "d3v8g9t"
                                                              ]
                                                            }
                                                          }
                                                        ],
                                                        "after": null,
                                                        "before": null
                                                      }
                                                    },
                                                    "user_reports": [

                                                    ],
                                                    "saved": false,
                                                    "id": "d3v82jx",
                                                    "gilded": 0,
                                                    "archived": false,
                                                    "report_reasons": null,
                                                    "author": "zappa325",
                                                    "parent_id": "t1_d3v7mhy",
                                                    "score": 190,
                                                    "approved_by": null,
                                                    "controversiality": 0,
                                                    "body": "When I went to Death Valley when I was a young lad it was 115 degrees, I remember that clearly. It was so hot that my crocs melted in the boiling sand once we got out of the car to sightsee.",
                                                    "edited": false,
                                                    "author_flair_css_class": null,
                                                    "downs": 0,
                                                    "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;When I went to Death Valley when I was a young lad it was 115 degrees, I remember that clearly. It was so hot that my crocs melted in the boiling sand once we got out of the car to sightsee.&lt;/p&gt;\n&lt;/div&gt;",
                                                    "subreddit": "funny",
                                                    "name": "t1_d3v82jx",
                                                    "score_hidden": false,
                                                    "stickied": false,
                                                    "created": 1465031064.0,
                                                    "author_flair_text": null,
                                                    "created_utc": 1465002264.0,
                                                    "distinguished": null,
                                                    "mod_reports": [

                                                    ],
                                                    "num_reports": null,
                                                    "ups": 190
                                                  }
                                                },
                                                {
                                                  "kind": "more",
                                                  "data": {
                                                    "count": 12,
                                                    "parent_id": "t1_d3v7mhy",
                                                    "children": [
                                                      "d3v8cjk",
                                                      "d3v8g5i",
                                                      "d3v8az0",
                                                      "d3v8wp9",
                                                      "d3v9lcu",
                                                      "d3vb109",
                                                      "d3v8fmm"
                                                    ],
                                                    "name": "t1_d3v8cjk",
                                                    "id": "d3v8cjk"
                                                  }
                                                }
                                              ],
                                              "after": null,
                                              "before": null
                                            }
                                          },
                                          "user_reports": [

                                          ],
                                          "saved": false,
                                          "id": "d3v7mhy",
                                          "gilded": 0,
                                          "archived": false,
                                          "report_reasons": null,
                                          "author": "Can_I_get_laid_here",
                                          "parent_id": "t1_d3v7eac",
                                          "score": 644,
                                          "approved_by": null,
                                          "controversiality": 0,
                                          "body": "I feel like \"mid 100s\" in this case means \"between 100 and 110\". Not \"around 150\". ",
                                          "edited": false,
                                          "author_flair_css_class": null,
                                          "downs": 0,
                                          "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;I feel like &amp;quot;mid 100s&amp;quot; in this case means &amp;quot;between 100 and 110&amp;quot;. Not &amp;quot;around 150&amp;quot;. &lt;/p&gt;\n&lt;/div&gt;",
                                          "subreddit": "funny",
                                          "name": "t1_d3v7mhy",
                                          "score_hidden": false,
                                          "stickied": false,
                                          "created": 1465030294.0,
                                          "author_flair_text": null,
                                          "created_utc": 1465001494.0,
                                          "distinguished": null,
                                          "mod_reports": [

                                          ],
                                          "num_reports": null,
                                          "ups": 644
                                        }
                                      },
                                      {
                                        "kind": "t1",
                                        "data": {
                                          "subreddit_id": "t5_2qh33",
                                          "banned_by": null,
                                          "removal_reason": null,
                                          "link_id": "t3_4mfawk",
                                          "likes": null,
                                          "replies": {
                                            "kind": "Listing",
                                            "data": {
                                              "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                              "children": [
                                                {
                                                  "kind": "t1",
                                                  "data": {
                                                    "subreddit_id": "t5_2qh33",
                                                    "banned_by": null,
                                                    "removal_reason": null,
                                                    "link_id": "t3_4mfawk",
                                                    "likes": null,
                                                    "replies": {
                                                      "kind": "Listing",
                                                      "data": {
                                                        "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                                        "children": [
                                                          {
                                                            "kind": "t1",
                                                            "data": {
                                                              "subreddit_id": "t5_2qh33",
                                                              "banned_by": null,
                                                              "removal_reason": null,
                                                              "link_id": "t3_4mfawk",
                                                              "likes": null,
                                                              "replies": {
                                                                "kind": "Listing",
                                                                "data": {
                                                                  "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                                                  "children": [
                                                                    {
                                                                      "kind": "t1",
                                                                      "data": {
                                                                        "subreddit_id": "t5_2qh33",
                                                                        "banned_by": null,
                                                                        "removal_reason": null,
                                                                        "link_id": "t3_4mfawk",
                                                                        "likes": null,
                                                                        "replies": {
                                                                          "kind": "Listing",
                                                                          "data": {
                                                                            "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                                                            "children": [
                                                                              {
                                                                                "kind": "t1",
                                                                                "data": {
                                                                                  "subreddit_id": "t5_2qh33",
                                                                                  "banned_by": null,
                                                                                  "removal_reason": null,
                                                                                  "link_id": "t3_4mfawk",
                                                                                  "likes": null,
                                                                                  "replies": {
                                                                                    "kind": "Listing",
                                                                                    "data": {
                                                                                      "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                                                                      "children": [
                                                                                        {
                                                                                          "kind": "more",
                                                                                          "data": {
                                                                                            "count": 4,
                                                                                            "parent_id": "t1_d3v9ijt",
                                                                                            "children": [
                                                                                              "d3v9oc5"
                                                                                            ],
                                                                                            "name": "t1_d3v9oc5",
                                                                                            "id": "d3v9oc5"
                                                                                          }
                                                                                        }
                                                                                      ],
                                                                                      "after": null,
                                                                                      "before": null
                                                                                    }
                                                                                  },
                                                                                  "user_reports": [

                                                                                  ],
                                                                                  "saved": false,
                                                                                  "id": "d3v9ijt",
                                                                                  "gilded": 0,
                                                                                  "archived": false,
                                                                                  "report_reasons": null,
                                                                                  "author": "ChefBoyAreWeFucked",
                                                                                  "parent_id": "t1_d3v9b4m",
                                                                                  "score": 11,
                                                                                  "approved_by": null,
                                                                                  "controversiality": 0,
                                                                                  "body": "I have been near paper mills, and I can't imagine the horrific smell not being the worst part at any distance.",
                                                                                  "edited": false,
                                                                                  "author_flair_css_class": null,
                                                                                  "downs": 0,
                                                                                  "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;I have been near paper mills, and I can&amp;#39;t imagine the horrific smell not being the worst part at any distance.&lt;/p&gt;\n&lt;/div&gt;",
                                                                                  "subreddit": "funny",
                                                                                  "name": "t1_d3v9ijt",
                                                                                  "score_hidden": false,
                                                                                  "stickied": false,
                                                                                  "created": 1465033532.0,
                                                                                  "author_flair_text": null,
                                                                                  "created_utc": 1465004732.0,
                                                                                  "distinguished": null,
                                                                                  "mod_reports": [

                                                                                  ],
                                                                                  "num_reports": null,
                                                                                  "ups": 11
                                                                                }
                                                                              },
                                                                              {
                                                                                "kind": "more",
                                                                                "data": {
                                                                                  "count": 3,
                                                                                  "parent_id": "t1_d3v9b4m",
                                                                                  "id": "d3vbalk",
                                                                                  "name": "t1_d3vbalk",
                                                                                  "children": [
                                                                                    "d3vbalk",
                                                                                    "d3vaajp"
                                                                                  ]
                                                                                }
                                                                              }
                                                                            ],
                                                                            "after": null,
                                                                            "before": null
                                                                          }
                                                                        },
                                                                        "user_reports": [

                                                                        ],
                                                                        "saved": false,
                                                                        "id": "d3v9b4m",
                                                                        "gilded": 0,
                                                                        "archived": false,
                                                                        "report_reasons": null,
                                                                        "author": "Bgndrsn",
                                                                        "parent_id": "t1_d3v8e4p",
                                                                        "score": 13,
                                                                        "approved_by": null,
                                                                        "controversiality": 0,
                                                                        "body": "My father works in a papermill. Massive machinery and it's always 100% humidity. One day I went to his work to talk to one of their engineers about making some parts for them and holy fuck was that a mistake. It felt like my face was melting off.",
                                                                        "edited": false,
                                                                        "author_flair_css_class": null,
                                                                        "downs": 0,
                                                                        "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;My father works in a papermill. Massive machinery and it&amp;#39;s always 100% humidity. One day I went to his work to talk to one of their engineers about making some parts for them and holy fuck was that a mistake. It felt like my face was melting off.&lt;/p&gt;\n&lt;/div&gt;",
                                                                        "subreddit": "funny",
                                                                        "name": "t1_d3v9b4m",
                                                                        "score_hidden": false,
                                                                        "stickied": false,
                                                                        "created": 1465033175.0,
                                                                        "author_flair_text": null,
                                                                        "created_utc": 1465004375.0,
                                                                        "distinguished": null,
                                                                        "mod_reports": [

                                                                        ],
                                                                        "num_reports": null,
                                                                        "ups": 13
                                                                      }
                                                                    },
                                                                    {
                                                                      "kind": "more",
                                                                      "data": {
                                                                        "count": 8,
                                                                        "parent_id": "t1_d3v8e4p",
                                                                        "children": [
                                                                          "d3va59w",
                                                                          "d3v9vuy",
                                                                          "d3v97o4",
                                                                          "d3v9tvb"
                                                                        ],
                                                                        "name": "t1_d3va59w",
                                                                        "id": "d3va59w"
                                                                      }
                                                                    }
                                                                  ],
                                                                  "after": null,
                                                                  "before": null
                                                                }
                                                              },
                                                              "user_reports": [

                                                              ],
                                                              "saved": false,
                                                              "id": "d3v8e4p",
                                                              "gilded": 0,
                                                              "archived": false,
                                                              "report_reasons": null,
                                                              "author": "redghotiblueghoti",
                                                              "parent_id": "t1_d3v86h6",
                                                              "score": 83,
                                                              "approved_by": null,
                                                              "controversiality": 0,
                                                              "body": "I think I would just lay down and die at that point, I've been in an engine room in the red sea and anything above 110 at that humidity can fuck right off.",
                                                              "edited": false,
                                                              "author_flair_css_class": null,
                                                              "downs": 0,
                                                              "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;I think I would just lay down and die at that point, I&amp;#39;ve been in an engine room in the red sea and anything above 110 at that humidity can fuck right off.&lt;/p&gt;\n&lt;/div&gt;",
                                                              "subreddit": "funny",
                                                              "name": "t1_d3v8e4p",
                                                              "score_hidden": false,
                                                              "stickied": false,
                                                              "created": 1465031597.0,
                                                              "author_flair_text": null,
                                                              "created_utc": 1465002797.0,
                                                              "distinguished": null,
                                                              "mod_reports": [

                                                              ],
                                                              "num_reports": null,
                                                              "ups": 83
                                                            }
                                                          },
                                                          {
                                                            "kind": "t1",
                                                            "data": {
                                                              "subreddit_id": "t5_2qh33",
                                                              "banned_by": null,
                                                              "removal_reason": null,
                                                              "link_id": "t3_4mfawk",
                                                              "likes": null,
                                                              "replies": {
                                                                "kind": "Listing",
                                                                "data": {
                                                                  "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                                                  "children": [
                                                                    {
                                                                      "kind": "t1",
                                                                      "data": {
                                                                        "subreddit_id": "t5_2qh33",
                                                                        "banned_by": null,
                                                                        "removal_reason": null,
                                                                        "link_id": "t3_4mfawk",
                                                                        "likes": null,
                                                                        "replies": {
                                                                          "kind": "Listing",
                                                                          "data": {
                                                                            "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                                                            "children": [
                                                                              {
                                                                                "kind": "more",
                                                                                "data": {
                                                                                  "count": 3,
                                                                                  "parent_id": "t1_d3v8zhl",
                                                                                  "id": "d3vbzeg",
                                                                                  "name": "t1_d3vbzeg",
                                                                                  "children": [
                                                                                    "d3vbzeg",
                                                                                    "d3v9r9v",
                                                                                    "d3vb7ly"
                                                                                  ]
                                                                                }
                                                                              }
                                                                            ],
                                                                            "after": null,
                                                                            "before": null
                                                                          }
                                                                        },
                                                                        "user_reports": [

                                                                        ],
                                                                        "saved": false,
                                                                        "id": "d3v8zhl",
                                                                        "gilded": 0,
                                                                        "archived": false,
                                                                        "report_reasons": null,
                                                                        "author": "dmand8",
                                                                        "parent_id": "t1_d3v8bla",
                                                                        "score": 40,
                                                                        "approved_by": null,
                                                                        "controversiality": 0,
                                                                        "body": "Sure is hot in these rhinos.",
                                                                        "edited": false,
                                                                        "author_flair_css_class": null,
                                                                        "downs": 0,
                                                                        "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Sure is hot in these rhinos.&lt;/p&gt;\n&lt;/div&gt;",
                                                                        "subreddit": "funny",
                                                                        "name": "t1_d3v8zhl",
                                                                        "score_hidden": false,
                                                                        "stickied": false,
                                                                        "created": 1465032616.0,
                                                                        "author_flair_text": null,
                                                                        "created_utc": 1465003816.0,
                                                                        "distinguished": null,
                                                                        "mod_reports": [

                                                                        ],
                                                                        "num_reports": null,
                                                                        "ups": 40
                                                                      }
                                                                    },
                                                                    {
                                                                      "kind": "t1",
                                                                      "data": {
                                                                        "subreddit_id": "t5_2qh33",
                                                                        "banned_by": null,
                                                                        "removal_reason": null,
                                                                        "link_id": "t3_4mfawk",
                                                                        "likes": null,
                                                                        "replies": {
                                                                          "kind": "Listing",
                                                                          "data": {
                                                                            "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                                                            "children": [
                                                                              {
                                                                                "kind": "t1",
                                                                                "data": {
                                                                                  "subreddit_id": "t5_2qh33",
                                                                                  "banned_by": null,
                                                                                  "removal_reason": null,
                                                                                  "link_id": "t3_4mfawk",
                                                                                  "likes": null,
                                                                                  "replies": {
                                                                                    "kind": "Listing",
                                                                                    "data": {
                                                                                      "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                                                                      "children": [
                                                                                        {
                                                                                          "kind": "t1",
                                                                                          "data": {
                                                                                            "subreddit_id": "t5_2qh33",
                                                                                            "banned_by": null,
                                                                                            "removal_reason": null,
                                                                                            "link_id": "t3_4mfawk",
                                                                                            "likes": null,
                                                                                            "replies": "",
                                                                                            "user_reports": [

                                                                                            ],
                                                                                            "saved": false,
                                                                                            "id": "d3v8qx1",
                                                                                            "gilded": 0,
                                                                                            "archived": false,
                                                                                            "report_reasons": null,
                                                                                            "author": "twominitsturkish",
                                                                                            "parent_id": "t1_d3v8ocw",
                                                                                            "score": 8,
                                                                                            "approved_by": null,
                                                                                            "controversiality": 0,
                                                                                            "body": "https://www.youtube.com/watch?v=S4IkNSSk92A",
                                                                                            "edited": false,
                                                                                            "author_flair_css_class": null,
                                                                                            "downs": 0,
                                                                                            "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;&lt;a href=\"https://www.youtube.com/watch?v=S4IkNSSk92A\"&gt;https://www.youtube.com/watch?v=S4IkNSSk92A&lt;/a&gt;&lt;/p&gt;\n&lt;/div&gt;",
                                                                                            "subreddit": "funny",
                                                                                            "name": "t1_d3v8qx1",
                                                                                            "score_hidden": false,
                                                                                            "stickied": false,
                                                                                            "created": 1465032197.0,
                                                                                            "author_flair_text": null,
                                                                                            "created_utc": 1465003397.0,
                                                                                            "distinguished": null,
                                                                                            "mod_reports": [

                                                                                            ],
                                                                                            "num_reports": null,
                                                                                            "ups": 8
                                                                                          }
                                                                                        }
                                                                                      ],
                                                                                      "after": null,
                                                                                      "before": null
                                                                                    }
                                                                                  },
                                                                                  "user_reports": [

                                                                                  ],
                                                                                  "saved": false,
                                                                                  "id": "d3v8ocw",
                                                                                  "gilded": 0,
                                                                                  "archived": false,
                                                                                  "report_reasons": null,
                                                                                  "author": "crimsontideftw24",
                                                                                  "parent_id": "t1_d3v8k8n",
                                                                                  "score": 44,
                                                                                  "approved_by": null,
                                                                                  "controversiality": 0,
                                                                                  "body": "How'd a turkey get on the internet?",
                                                                                  "edited": false,
                                                                                  "author_flair_css_class": null,
                                                                                  "downs": 0,
                                                                                  "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;How&amp;#39;d a turkey get on the internet?&lt;/p&gt;\n&lt;/div&gt;",
                                                                                  "subreddit": "funny",
                                                                                  "name": "t1_d3v8ocw",
                                                                                  "score_hidden": false,
                                                                                  "stickied": false,
                                                                                  "created": 1465032076.0,
                                                                                  "author_flair_text": null,
                                                                                  "created_utc": 1465003276.0,
                                                                                  "distinguished": null,
                                                                                  "mod_reports": [

                                                                                  ],
                                                                                  "num_reports": null,
                                                                                  "ups": 44
                                                                                }
                                                                              }
                                                                            ],
                                                                            "after": null,
                                                                            "before": null
                                                                          }
                                                                        },
                                                                        "user_reports": [

                                                                        ],
                                                                        "saved": false,
                                                                        "id": "d3v8k8n",
                                                                        "gilded": 0,
                                                                        "archived": false,
                                                                        "report_reasons": null,
                                                                        "author": "arcanition",
                                                                        "parent_id": "t1_d3v8bla",
                                                                        "score": 25,
                                                                        "approved_by": null,
                                                                        "controversiality": 0,
                                                                        "body": "Sounds like it was an oven.",
                                                                        "edited": false,
                                                                        "author_flair_css_class": null,
                                                                        "downs": 0,
                                                                        "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Sounds like it was an oven.&lt;/p&gt;\n&lt;/div&gt;",
                                                                        "subreddit": "funny",
                                                                        "name": "t1_d3v8k8n",
                                                                        "score_hidden": false,
                                                                        "stickied": false,
                                                                        "created": 1465031879.0,
                                                                        "author_flair_text": null,
                                                                        "created_utc": 1465003079.0,
                                                                        "distinguished": null,
                                                                        "mod_reports": [

                                                                        ],
                                                                        "num_reports": null,
                                                                        "ups": 25
                                                                      }
                                                                    }
                                                                  ],
                                                                  "after": null,
                                                                  "before": null
                                                                }
                                                              },
                                                              "user_reports": [

                                                              ],
                                                              "saved": false,
                                                              "id": "d3v8bla",
                                                              "gilded": 0,
                                                              "archived": false,
                                                              "report_reasons": null,
                                                              "author": "buffaloruffneck",
                                                              "parent_id": "t1_d3v86h6",
                                                              "score": 47,
                                                              "approved_by": null,
                                                              "controversiality": 0,
                                                              "body": "I have to ask; metal enclosure in Northern Africa? \n\nTroubles?",
                                                              "edited": false,
                                                              "author_flair_css_class": null,
                                                              "downs": 0,
                                                              "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;I have to ask; metal enclosure in Northern Africa? &lt;/p&gt;\n\n&lt;p&gt;Troubles?&lt;/p&gt;\n&lt;/div&gt;",
                                                              "subreddit": "funny",
                                                              "name": "t1_d3v8bla",
                                                              "score_hidden": false,
                                                              "stickied": false,
                                                              "created": 1465031482.0,
                                                              "author_flair_text": null,
                                                              "created_utc": 1465002682.0,
                                                              "distinguished": null,
                                                              "mod_reports": [

                                                              ],
                                                              "num_reports": null,
                                                              "ups": 47
                                                            }
                                                          },
                                                          {
                                                            "kind": "t1",
                                                            "data": {
                                                              "subreddit_id": "t5_2qh33",
                                                              "banned_by": null,
                                                              "removal_reason": null,
                                                              "link_id": "t3_4mfawk",
                                                              "likes": null,
                                                              "replies": "",
                                                              "user_reports": [

                                                              ],
                                                              "saved": false,
                                                              "id": "d3v8h8c",
                                                              "gilded": 0,
                                                              "archived": false,
                                                              "report_reasons": null,
                                                              "author": "SquirrelInTheAttic",
                                                              "parent_id": "t1_d3v86h6",
                                                              "score": 10,
                                                              "approved_by": null,
                                                              "controversiality": 0,
                                                              "body": "Sounds like military. Maybe even ATC.",
                                                              "edited": false,
                                                              "author_flair_css_class": null,
                                                              "downs": 0,
                                                              "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Sounds like military. Maybe even ATC.&lt;/p&gt;\n&lt;/div&gt;",
                                                              "subreddit": "funny",
                                                              "name": "t1_d3v8h8c",
                                                              "score_hidden": false,
                                                              "stickied": false,
                                                              "created": 1465031739.0,
                                                              "author_flair_text": null,
                                                              "created_utc": 1465002939.0,
                                                              "distinguished": null,
                                                              "mod_reports": [

                                                              ],
                                                              "num_reports": null,
                                                              "ups": 10
                                                            }
                                                          },
                                                          {
                                                            "kind": "more",
                                                            "data": {
                                                              "count": 21,
                                                              "parent_id": "t1_d3v86h6",
                                                              "id": "d3v8aw3",
                                                              "name": "t1_d3v8aw3",
                                                              "children": [
                                                                "d3v8aw3",
                                                                "d3v8mlh",
                                                                "d3v8ccm",
                                                                "d3v8j8o",
                                                                "d3v8bwr",
                                                                "d3v9h4d",
                                                                "d3v9sr2",
                                                                "d3v9kj1",
                                                                "d3v8y6t",
                                                                "d3v8rq1"
                                                              ]
                                                            }
                                                          }
                                                        ],
                                                        "after": null,
                                                        "before": null
                                                      }
                                                    },
                                                    "user_reports": [

                                                    ],
                                                    "saved": false,
                                                    "id": "d3v86h6",
                                                    "gilded": 0,
                                                    "archived": false,
                                                    "report_reasons": null,
                                                    "author": "Hodr",
                                                    "parent_id": "t1_d3v7omv",
                                                    "score": 73,
                                                    "approved_by": null,
                                                    "controversiality": 0,
                                                    "body": "Did 134 with near 100% humidity in a metal enclosure in northern africa. I swear I drank a 2 liter of water every hour.",
                                                    "edited": false,
                                                    "author_flair_css_class": null,
                                                    "downs": 0,
                                                    "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Did 134 with near 100% humidity in a metal enclosure in northern africa. I swear I drank a 2 liter of water every hour.&lt;/p&gt;\n&lt;/div&gt;",
                                                    "subreddit": "funny",
                                                    "name": "t1_d3v86h6",
                                                    "score_hidden": false,
                                                    "stickied": false,
                                                    "created": 1465031244.0,
                                                    "author_flair_text": null,
                                                    "created_utc": 1465002444.0,
                                                    "distinguished": null,
                                                    "mod_reports": [

                                                    ],
                                                    "num_reports": null,
                                                    "ups": 73
                                                  }
                                                },
                                                {
                                                  "kind": "more",
                                                  "data": {
                                                    "count": 4,
                                                    "parent_id": "t1_d3v7omv",
                                                    "children": [
                                                      "d3v9eh0",
                                                      "d3v8za2",
                                                      "d3v8n77",
                                                      "d3v8x44"
                                                    ],
                                                    "name": "t1_d3v9eh0",
                                                    "id": "d3v9eh0"
                                                  }
                                                }
                                              ],
                                              "after": null,
                                              "before": null
                                            }
                                          },
                                          "user_reports": [

                                          ],
                                          "saved": false,
                                          "id": "d3v7omv",
                                          "gilded": 0,
                                          "archived": false,
                                          "report_reasons": null,
                                          "author": "CRRZ",
                                          "parent_id": "t1_d3v7eac",
                                          "score": 61,
                                          "approved_by": null,
                                          "controversiality": 0,
                                          "body": "You haven't felt heat until you've been in a humid 150\u00b0 mid August day. \n\nEdit: it seems I should clarify that I was joking, I know OP meant ~105\u00b0. I've never been in 150\u00b0 heat but as a Floridian I've been through plenty that feel that hot. ",
                                          "edited": 1465006338.0,
                                          "author_flair_css_class": null,
                                          "downs": 0,
                                          "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;You haven&amp;#39;t felt heat until you&amp;#39;ve been in a humid 150\u00b0 mid August day. &lt;/p&gt;\n\n&lt;p&gt;Edit: it seems I should clarify that I was joking, I know OP meant ~105\u00b0. I&amp;#39;ve never been in 150\u00b0 heat but as a Floridian I&amp;#39;ve been through plenty that feel that hot. &lt;/p&gt;\n&lt;/div&gt;",
                                          "subreddit": "funny",
                                          "name": "t1_d3v7omv",
                                          "score_hidden": false,
                                          "stickied": false,
                                          "created": 1465030400.0,
                                          "author_flair_text": null,
                                          "created_utc": 1465001600.0,
                                          "distinguished": null,
                                          "mod_reports": [

                                          ],
                                          "num_reports": null,
                                          "ups": 61
                                        }
                                      },
                                      {
                                        "kind": "t1",
                                        "data": {
                                          "subreddit_id": "t5_2qh33",
                                          "banned_by": null,
                                          "removal_reason": null,
                                          "link_id": "t3_4mfawk",
                                          "likes": null,
                                          "replies": {
                                            "kind": "Listing",
                                            "data": {
                                              "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                              "children": [
                                                {
                                                  "kind": "more",
                                                  "data": {
                                                    "count": 2,
                                                    "parent_id": "t1_d3v7oua",
                                                    "children": [
                                                      "d3v9cxj"
                                                    ],
                                                    "name": "t1_d3v9cxj",
                                                    "id": "d3v9cxj"
                                                  }
                                                }
                                              ],
                                              "after": null,
                                              "before": null
                                            }
                                          },
                                          "user_reports": [

                                          ],
                                          "saved": false,
                                          "id": "d3v7oua",
                                          "gilded": 0,
                                          "archived": false,
                                          "report_reasons": null,
                                          "author": "Myrdinz",
                                          "parent_id": "t1_d3v7eac",
                                          "score": 23,
                                          "approved_by": null,
                                          "controversiality": 0,
                                          "body": "As in 105 not 150, the same way you would say mid 90s I guess.",
                                          "edited": false,
                                          "author_flair_css_class": null,
                                          "downs": 0,
                                          "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;As in 105 not 150, the same way you would say mid 90s I guess.&lt;/p&gt;\n&lt;/div&gt;",
                                          "subreddit": "funny",
                                          "name": "t1_d3v7oua",
                                          "score_hidden": false,
                                          "stickied": false,
                                          "created": 1465030409.0,
                                          "author_flair_text": null,
                                          "created_utc": 1465001609.0,
                                          "distinguished": null,
                                          "mod_reports": [

                                          ],
                                          "num_reports": null,
                                          "ups": 23
                                        }
                                      }
                                    ],
                                    "after": null,
                                    "before": null
                                  }
                                },
                                "user_reports": [

                                ],
                                "saved": false,
                                "id": "d3v7eac",
                                "gilded": 0,
                                "archived": false,
                                "report_reasons": null,
                                "author": "Bgndrsn",
                                "parent_id": "t1_d3v4zgq",
                                "score": 490,
                                "approved_by": null,
                                "controversiality": 0,
                                "body": "&gt;  mid 100s\n\nwut",
                                "edited": false,
                                "author_flair_css_class": null,
                                "downs": 0,
                                "body_html": "&lt;div class=\"md\"&gt;&lt;blockquote&gt;\n&lt;p&gt;mid 100s&lt;/p&gt;\n&lt;/blockquote&gt;\n\n&lt;p&gt;wut&lt;/p&gt;\n&lt;/div&gt;",
                                "subreddit": "funny",
                                "name": "t1_d3v7eac",
                                "score_hidden": false,
                                "stickied": false,
                                "created": 1465029897.0,
                                "author_flair_text": null,
                                "created_utc": 1465001097.0,
                                "distinguished": null,
                                "mod_reports": [

                                ],
                                "num_reports": null,
                                "ups": 490
                              }
                            },
                            {
                              "kind": "t1",
                              "data": {
                                "subreddit_id": "t5_2qh33",
                                "banned_by": null,
                                "removal_reason": null,
                                "link_id": "t3_4mfawk",
                                "likes": null,
                                "replies": {
                                  "kind": "Listing",
                                  "data": {
                                    "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                    "children": [
                                      {
                                        "kind": "t1",
                                        "data": {
                                          "subreddit_id": "t5_2qh33",
                                          "banned_by": null,
                                          "removal_reason": null,
                                          "link_id": "t3_4mfawk",
                                          "likes": null,
                                          "replies": {
                                            "kind": "Listing",
                                            "data": {
                                              "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                              "children": [
                                                {
                                                  "kind": "more",
                                                  "data": {
                                                    "count": 10,
                                                    "parent_id": "t1_d3v93pa",
                                                    "children": [
                                                      "d3vaidg",
                                                      "d3vcf51",
                                                      "d3vbns8",
                                                      "d3vas3z",
                                                      "d3vcieq",
                                                      "d3v9vg9"
                                                    ],
                                                    "name": "t1_d3vaidg",
                                                    "id": "d3vaidg"
                                                  }
                                                }
                                              ],
                                              "after": null,
                                              "before": null
                                            }
                                          },
                                          "user_reports": [

                                          ],
                                          "saved": false,
                                          "id": "d3v93pa",
                                          "gilded": 0,
                                          "archived": false,
                                          "report_reasons": null,
                                          "author": "Famousenuff",
                                          "parent_id": "t1_d3v8oes",
                                          "score": 35,
                                          "approved_by": null,
                                          "controversiality": 0,
                                          "body": "The difference between Phoenix and other places,  even Tucson, is that it doesn't cool down much at night. The asphalt and cement release the heat at night.  It's not shocking anymore for it to be 105 at 10 pm. We just don't get a break- it's no wonder why we're all raving maniacs by late August. ",
                                          "edited": false,
                                          "author_flair_css_class": null,
                                          "downs": 0,
                                          "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;The difference between Phoenix and other places,  even Tucson, is that it doesn&amp;#39;t cool down much at night. The asphalt and cement release the heat at night.  It&amp;#39;s not shocking anymore for it to be 105 at 10 pm. We just don&amp;#39;t get a break- it&amp;#39;s no wonder why we&amp;#39;re all raving maniacs by late August. &lt;/p&gt;\n&lt;/div&gt;",
                                          "subreddit": "funny",
                                          "name": "t1_d3v93pa",
                                          "score_hidden": false,
                                          "stickied": false,
                                          "created": 1465032820.0,
                                          "author_flair_text": null,
                                          "created_utc": 1465004020.0,
                                          "distinguished": null,
                                          "mod_reports": [

                                          ],
                                          "num_reports": null,
                                          "ups": 35
                                        }
                                      },
                                      {
                                        "kind": "t1",
                                        "data": {
                                          "subreddit_id": "t5_2qh33",
                                          "banned_by": null,
                                          "removal_reason": null,
                                          "link_id": "t3_4mfawk",
                                          "likes": null,
                                          "replies": {
                                            "kind": "Listing",
                                            "data": {
                                              "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                              "children": [
                                                {
                                                  "kind": "t1",
                                                  "data": {
                                                    "subreddit_id": "t5_2qh33",
                                                    "banned_by": null,
                                                    "removal_reason": null,
                                                    "link_id": "t3_4mfawk",
                                                    "likes": null,
                                                    "replies": "",
                                                    "user_reports": [

                                                    ],
                                                    "saved": false,
                                                    "id": "d3v9os3",
                                                    "gilded": 0,
                                                    "archived": false,
                                                    "report_reasons": null,
                                                    "author": "Shopworn_Soul",
                                                    "parent_id": "t1_d3v94p0",
                                                    "score": 12,
                                                    "approved_by": null,
                                                    "controversiality": 0,
                                                    "body": "To be fair, that would be crazy. Everyone knows it's only safe to stick your head in while the oven is stationary.",
                                                    "edited": false,
                                                    "author_flair_css_class": null,
                                                    "downs": 0,
                                                    "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;To be fair, that would be crazy. Everyone knows it&amp;#39;s only safe to stick your head in while the oven is stationary.&lt;/p&gt;\n&lt;/div&gt;",
                                                    "subreddit": "funny",
                                                    "name": "t1_d3v9os3",
                                                    "score_hidden": false,
                                                    "stickied": false,
                                                    "created": 1465033832.0,
                                                    "author_flair_text": null,
                                                    "created_utc": 1465005032.0,
                                                    "distinguished": null,
                                                    "mod_reports": [

                                                    ],
                                                    "num_reports": null,
                                                    "ups": 12
                                                  }
                                                }
                                              ],
                                              "after": null,
                                              "before": null
                                            }
                                          },
                                          "user_reports": [

                                          ],
                                          "saved": false,
                                          "id": "d3v94p0",
                                          "gilded": 0,
                                          "archived": false,
                                          "report_reasons": null,
                                          "author": "Aimless_Precision",
                                          "parent_id": "t1_d3v8oes",
                                          "score": 13,
                                          "approved_by": null,
                                          "controversiality": 0,
                                          "body": "I hope no one goes to stick their head in a running g oven and has an accident. \n\nLet's avoid any Darwin Awards!",
                                          "edited": false,
                                          "author_flair_css_class": null,
                                          "downs": 0,
                                          "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;I hope no one goes to stick their head in a running g oven and has an accident. &lt;/p&gt;\n\n&lt;p&gt;Let&amp;#39;s avoid any Darwin Awards!&lt;/p&gt;\n&lt;/div&gt;",
                                          "subreddit": "funny",
                                          "name": "t1_d3v94p0",
                                          "score_hidden": false,
                                          "stickied": false,
                                          "created": 1465032867.0,
                                          "author_flair_text": null,
                                          "created_utc": 1465004067.0,
                                          "distinguished": null,
                                          "mod_reports": [

                                          ],
                                          "num_reports": null,
                                          "ups": 13
                                        }
                                      },
                                      {
                                        "kind": "more",
                                        "data": {
                                          "count": 42,
                                          "parent_id": "t1_d3v8oes",
                                          "id": "d3v9tmo",
                                          "name": "t1_d3v9tmo",
                                          "children": [
                                            "d3v9tmo",
                                            "d3v9bvo",
                                            "d3vcjph",
                                            "d3vcd2e",
                                            "d3vaxvr",
                                            "d3v9f8p",
                                            "d3vc5my",
                                            "d3v9cff",
                                            "d3vcdil",
                                            "d3v9h4f",
                                            "d3vc7qp",
                                            "d3vbvgy",
                                            "d3v9g39",
                                            "d3v9wra",
                                            "d3vahay",
                                            "d3vbqxb",
                                            "d3vc93p"
                                          ]
                                        }
                                      }
                                    ],
                                    "after": null,
                                    "before": null
                                  }
                                },
                                "user_reports": [

                                ],
                                "saved": false,
                                "id": "d3v8oes",
                                "gilded": 0,
                                "archived": false,
                                "report_reasons": null,
                                "author": "MeesterScott",
                                "parent_id": "t1_d3v4zgq",
                                "score": 75,
                                "approved_by": null,
                                "controversiality": 0,
                                "body": "Also lived in Tucson for 19 years and grew up with a swamp cooler as well, they were shit during the summer. Also, Tucson is slightly higher elevation than Phoenix and doesn't get quite as hot. \r\rNow if you really want to experience heat in the summer visit Yuma, AZ. It can get above 120 during the summer. I remember playing a little league baseball game one summer on the hottest day on record, 127 degrees. Pretty sure that record still stands.\r\r\rTo anyone who says, \"But it's a dry heat.\" Please humor me and set your oven to 125, then stuck your head in there for 10 minutes. When you get out, please tell me all about how comfortable a dry heat is. \r\rI've also experienced southern humidity during the summer and I tell everyone the same thing. In my opinion, if it's 120+ with minimal humidity, or 100+ with high humidity, either way its pretty miserable. I can't say I think one is worse than the other, they both suck. \r\rOne thing people in the south don't have to deal with is getting in your car in the summer, and not being able to touch anything without risking serious burns. \r\rOne more thing to add. When the high in phoenix is 118, it probably won't drop below 100 as the low. \r\r\rEdit: To everyone in the south who finds it necessary to point out how hot it is during the summer;  unless you have experienced both extremes, I'm sorry but you don't know what you're talking about.",
                                "edited": 1465005559.0,
                                "author_flair_css_class": null,
                                "downs": 0,
                                "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Also lived in Tucson for 19 years and grew up with a swamp cooler as well, they were shit during the summer. Also, Tucson is slightly higher elevation than Phoenix and doesn&amp;#39;t get quite as hot. &lt;/p&gt;\n\n&lt;p&gt;Now if you really want to experience heat in the summer visit Yuma, AZ. It can get above 120 during the summer. I remember playing a little league baseball game one summer on the hottest day on record, 127 degrees. Pretty sure that record still stands.&lt;/p&gt;\n\n&lt;p&gt;To anyone who says, &amp;quot;But it&amp;#39;s a dry heat.&amp;quot; Please humor me and set your oven to 125, then stuck your head in there for 10 minutes. When you get out, please tell me all about how comfortable a dry heat is. &lt;/p&gt;\n\n&lt;p&gt;I&amp;#39;ve also experienced southern humidity during the summer and I tell everyone the same thing. In my opinion, if it&amp;#39;s 120+ with minimal humidity, or 100+ with high humidity, either way its pretty miserable. I can&amp;#39;t say I think one is worse than the other, they both suck. &lt;/p&gt;\n\n&lt;p&gt;One thing people in the south don&amp;#39;t have to deal with is getting in your car in the summer, and not being able to touch anything without risking serious burns. &lt;/p&gt;\n\n&lt;p&gt;One more thing to add. When the high in phoenix is 118, it probably won&amp;#39;t drop below 100 as the low. &lt;/p&gt;\n\n&lt;p&gt;Edit: To everyone in the south who finds it necessary to point out how hot it is during the summer;  unless you have experienced both extremes, I&amp;#39;m sorry but you don&amp;#39;t know what you&amp;#39;re talking about.&lt;/p&gt;\n&lt;/div&gt;",
                                "subreddit": "funny",
                                "name": "t1_d3v8oes",
                                "score_hidden": false,
                                "stickied": false,
                                "created": 1465032078.0,
                                "author_flair_text": null,
                                "created_utc": 1465003278.0,
                                "distinguished": null,
                                "mod_reports": [

                                ],
                                "num_reports": null,
                                "ups": 75
                              }
                            },
                            {
                              "kind": "more",
                              "data": {
                                "count": 21,
                                "parent_id": "t1_d3v4zgq",
                                "children": [
                                  "d3v94v6",
                                  "d3vcbhf",
                                  "d3v8all",
                                  "d3v9p1n",
                                  "d3v9vjz",
                                  "d3v9hgi",
                                  "d3v8428",
                                  "d3va16x",
                                  "d3v8pvf",
                                  "d3v92m6",
                                  "d3v9fzz"
                                ],
                                "name": "t1_d3v94v6",
                                "id": "d3v94v6"
                              }
                            }
                          ],
                          "after": null,
                          "before": null
                        }
                      },
                      "user_reports": [

                      ],
                      "saved": false,
                      "id": "d3v4zgq",
                      "gilded": 0,
                      "archived": false,
                      "report_reasons": null,
                      "author": "indieaz",
                      "parent_id": "t1_d3v2yc4",
                      "score": 467,
                      "approved_by": null,
                      "controversiality": 0,
                      "body": "Yes, in June when it's in the mid 100s and dry out swamp coolers work really well.  Once the monsoon kicks in it's absolute hell.\n\nSource: Lived in Tucson 32 years, grew up with swamp cooler the first 13 years of my life.",
                      "edited": false,
                      "author_flair_css_class": null,
                      "downs": 0,
                      "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Yes, in June when it&amp;#39;s in the mid 100s and dry out swamp coolers work really well.  Once the monsoon kicks in it&amp;#39;s absolute hell.&lt;/p&gt;\n\n&lt;p&gt;Source: Lived in Tucson 32 years, grew up with swamp cooler the first 13 years of my life.&lt;/p&gt;\n&lt;/div&gt;",
                      "subreddit": "funny",
                      "name": "t1_d3v4zgq",
                      "score_hidden": false,
                      "stickied": false,
                      "created": 1465025662.0,
                      "author_flair_text": null,
                      "created_utc": 1464996862.0,
                      "distinguished": null,
                      "mod_reports": [

                      ],
                      "num_reports": null,
                      "ups": 467
                    }
                  },
                  {
                    "kind": "t1",
                    "data": {
                      "subreddit_id": "t5_2qh33",
                      "banned_by": null,
                      "removal_reason": null,
                      "link_id": "t3_4mfawk",
                      "likes": null,
                      "replies": {
                        "kind": "Listing",
                        "data": {
                          "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                          "children": [
                            {
                              "kind": "t1",
                              "data": {
                                "subreddit_id": "t5_2qh33",
                                "banned_by": null,
                                "removal_reason": null,
                                "link_id": "t3_4mfawk",
                                "likes": null,
                                "replies": {
                                  "kind": "Listing",
                                  "data": {
                                    "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                    "children": [
                                      {
                                        "kind": "t1",
                                        "data": {
                                          "subreddit_id": "t5_2qh33",
                                          "banned_by": null,
                                          "removal_reason": null,
                                          "link_id": "t3_4mfawk",
                                          "likes": null,
                                          "replies": {
                                            "kind": "Listing",
                                            "data": {
                                              "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                              "children": [
                                                {
                                                  "kind": "t1",
                                                  "data": {
                                                    "subreddit_id": "t5_2qh33",
                                                    "banned_by": null,
                                                    "removal_reason": null,
                                                    "link_id": "t3_4mfawk",
                                                    "likes": null,
                                                    "replies": {
                                                      "kind": "Listing",
                                                      "data": {
                                                        "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                                        "children": [
                                                          {
                                                            "kind": "t1",
                                                            "data": {
                                                              "subreddit_id": "t5_2qh33",
                                                              "banned_by": null,
                                                              "removal_reason": null,
                                                              "link_id": "t3_4mfawk",
                                                              "likes": null,
                                                              "replies": {
                                                                "kind": "Listing",
                                                                "data": {
                                                                  "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                                                  "children": [
                                                                    {
                                                                      "kind": "t1",
                                                                      "data": {
                                                                        "subreddit_id": "t5_2qh33",
                                                                        "banned_by": null,
                                                                        "removal_reason": null,
                                                                        "link_id": "t3_4mfawk",
                                                                        "likes": null,
                                                                        "replies": {
                                                                          "kind": "Listing",
                                                                          "data": {
                                                                            "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                                                            "children": [
                                                                              {
                                                                                "kind": "t1",
                                                                                "data": {
                                                                                  "subreddit_id": "t5_2qh33",
                                                                                  "banned_by": null,
                                                                                  "removal_reason": null,
                                                                                  "link_id": "t3_4mfawk",
                                                                                  "likes": null,
                                                                                  "replies": {
                                                                                    "kind": "Listing",
                                                                                    "data": {
                                                                                      "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                                                                      "children": [
                                                                                        {
                                                                                          "kind": "t1",
                                                                                          "data": {
                                                                                            "subreddit_id": "t5_2qh33",
                                                                                            "banned_by": null,
                                                                                            "removal_reason": null,
                                                                                            "link_id": "t3_4mfawk",
                                                                                            "likes": null,
                                                                                            "replies": {
                                                                                              "kind": "Listing",
                                                                                              "data": {
                                                                                                "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                                                                                "children": [
                                                                                                  {
                                                                                                    "kind": "more",
                                                                                                    "data": {
                                                                                                      "count": 1,
                                                                                                      "parent_id": "t1_d3v9hzm",
                                                                                                      "id": "d3vavsd",
                                                                                                      "name": "t1_d3vavsd",
                                                                                                      "children": [
                                                                                                        "d3vavsd"
                                                                                                      ]
                                                                                                    }
                                                                                                  }
                                                                                                ],
                                                                                                "after": null,
                                                                                                "before": null
                                                                                              }
                                                                                            },
                                                                                            "user_reports": [

                                                                                            ],
                                                                                            "saved": false,
                                                                                            "id": "d3v9hzm",
                                                                                            "gilded": 0,
                                                                                            "archived": false,
                                                                                            "report_reasons": null,
                                                                                            "author": "Nodnarb4242",
                                                                                            "parent_id": "t1_d3v92gf",
                                                                                            "score": 11,
                                                                                            "approved_by": null,
                                                                                            "controversiality": 0,
                                                                                            "body": "scrubbing/soaping is overall bad for the protective skin layers. Not saying don't shower... just don't try to polish yourself into a god",
                                                                                            "edited": false,
                                                                                            "author_flair_css_class": null,
                                                                                            "downs": 0,
                                                                                            "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;scrubbing/soaping is overall bad for the protective skin layers. Not saying don&amp;#39;t shower... just don&amp;#39;t try to polish yourself into a god&lt;/p&gt;\n&lt;/div&gt;",
                                                                                            "subreddit": "funny",
                                                                                            "name": "t1_d3v9hzm",
                                                                                            "score_hidden": false,
                                                                                            "stickied": false,
                                                                                            "created": 1465033506.0,
                                                                                            "author_flair_text": null,
                                                                                            "created_utc": 1465004706.0,
                                                                                            "distinguished": null,
                                                                                            "mod_reports": [

                                                                                            ],
                                                                                            "num_reports": null,
                                                                                            "ups": 11
                                                                                          }
                                                                                        },
                                                                                        {
                                                                                          "kind": "more",
                                                                                          "data": {
                                                                                            "count": 3,
                                                                                            "parent_id": "t1_d3v92gf",
                                                                                            "children": [
                                                                                              "d3v9l34",
                                                                                              "d3vabfd",
                                                                                              "d3vccbc"
                                                                                            ],
                                                                                            "name": "t1_d3v9l34",
                                                                                            "id": "d3v9l34"
                                                                                          }
                                                                                        }
                                                                                      ],
                                                                                      "after": null,
                                                                                      "before": null
                                                                                    }
                                                                                  },
                                                                                  "user_reports": [

                                                                                  ],
                                                                                  "saved": false,
                                                                                  "id": "d3v92gf",
                                                                                  "gilded": 0,
                                                                                  "archived": false,
                                                                                  "report_reasons": null,
                                                                                  "author": "FuckingMadBoy",
                                                                                  "parent_id": "t1_d3v8j9b",
                                                                                  "score": 37,
                                                                                  "approved_by": null,
                                                                                  "controversiality": 0,
                                                                                  "body": "no all they have to do is put on lotion you nasty bastard. ",
                                                                                  "edited": false,
                                                                                  "author_flair_css_class": null,
                                                                                  "downs": 0,
                                                                                  "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;no all they have to do is put on lotion you nasty bastard. &lt;/p&gt;\n&lt;/div&gt;",
                                                                                  "subreddit": "funny",
                                                                                  "name": "t1_d3v92gf",
                                                                                  "score_hidden": false,
                                                                                  "stickied": false,
                                                                                  "created": 1465032762.0,
                                                                                  "author_flair_text": null,
                                                                                  "created_utc": 1465003962.0,
                                                                                  "distinguished": null,
                                                                                  "mod_reports": [

                                                                                  ],
                                                                                  "num_reports": null,
                                                                                  "ups": 37
                                                                                }
                                                                              },
                                                                              {
                                                                                "kind": "more",
                                                                                "data": {
                                                                                  "count": 1,
                                                                                  "parent_id": "t1_d3v8j9b",
                                                                                  "id": "d3vc0nq",
                                                                                  "name": "t1_d3vc0nq",
                                                                                  "children": [
                                                                                    "d3vc0nq"
                                                                                  ]
                                                                                }
                                                                              }
                                                                            ],
                                                                            "after": null,
                                                                            "before": null
                                                                          }
                                                                        },
                                                                        "user_reports": [

                                                                        ],
                                                                        "saved": false,
                                                                        "id": "d3v8j9b",
                                                                        "gilded": 0,
                                                                        "archived": false,
                                                                        "report_reasons": null,
                                                                        "author": "GummyTumor",
                                                                        "parent_id": "t1_d3v85nh",
                                                                        "score": 13,
                                                                        "approved_by": null,
                                                                        "controversiality": 0,
                                                                        "body": "It sounds gross, but cut back on soap during the winter. Take your daily showers, but only use soap on your pits, crotch, feet, etc.. It really helps me during the winter and I promise it won't make you smell stank. ",
                                                                        "edited": false,
                                                                        "author_flair_css_class": null,
                                                                        "downs": 0,
                                                                        "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;It sounds gross, but cut back on soap during the winter. Take your daily showers, but only use soap on your pits, crotch, feet, etc.. It really helps me during the winter and I promise it won&amp;#39;t make you smell stank. &lt;/p&gt;\n&lt;/div&gt;",
                                                                        "subreddit": "funny",
                                                                        "name": "t1_d3v8j9b",
                                                                        "score_hidden": false,
                                                                        "stickied": false,
                                                                        "created": 1465031834.0,
                                                                        "author_flair_text": null,
                                                                        "created_utc": 1465003034.0,
                                                                        "distinguished": null,
                                                                        "mod_reports": [

                                                                        ],
                                                                        "num_reports": null,
                                                                        "ups": 13
                                                                      }
                                                                    },
                                                                    {
                                                                      "kind": "more",
                                                                      "data": {
                                                                        "count": 1,
                                                                        "parent_id": "t1_d3v85nh",
                                                                        "children": [
                                                                          "d3v8jds"
                                                                        ],
                                                                        "name": "t1_d3v8jds",
                                                                        "id": "d3v8jds"
                                                                      }
                                                                    }
                                                                  ],
                                                                  "after": null,
                                                                  "before": null
                                                                }
                                                              },
                                                              "user_reports": [

                                                              ],
                                                              "saved": false,
                                                              "id": "d3v85nh",
                                                              "gilded": 0,
                                                              "archived": false,
                                                              "report_reasons": null,
                                                              "author": "mdonaberger",
                                                              "parent_id": "t1_d3v80mz",
                                                              "score": 48,
                                                              "approved_by": null,
                                                              "controversiality": 0,
                                                              "body": "Current PA resident here. Winters turn me into a God damn lizard person.",
                                                              "edited": false,
                                                              "author_flair_css_class": null,
                                                              "downs": 0,
                                                              "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Current PA resident here. Winters turn me into a God damn lizard person.&lt;/p&gt;\n&lt;/div&gt;",
                                                              "subreddit": "funny",
                                                              "name": "t1_d3v85nh",
                                                              "score_hidden": false,
                                                              "stickied": false,
                                                              "created": 1465031206.0,
                                                              "author_flair_text": null,
                                                              "created_utc": 1465002406.0,
                                                              "distinguished": null,
                                                              "mod_reports": [

                                                              ],
                                                              "num_reports": null,
                                                              "ups": 48
                                                            }
                                                          },
                                                          {
                                                            "kind": "t1",
                                                            "data": {
                                                              "subreddit_id": "t5_2qh33",
                                                              "banned_by": null,
                                                              "removal_reason": null,
                                                              "link_id": "t3_4mfawk",
                                                              "likes": null,
                                                              "replies": {
                                                                "kind": "Listing",
                                                                "data": {
                                                                  "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                                                  "children": [
                                                                    {
                                                                      "kind": "more",
                                                                      "data": {
                                                                        "count": 5,
                                                                        "parent_id": "t1_d3v87f2",
                                                                        "children": [
                                                                          "d3v8edd",
                                                                          "d3v8imj"
                                                                        ],
                                                                        "name": "t1_d3v8edd",
                                                                        "id": "d3v8edd"
                                                                      }
                                                                    }
                                                                  ],
                                                                  "after": null,
                                                                  "before": null
                                                                }
                                                              },
                                                              "user_reports": [

                                                              ],
                                                              "saved": false,
                                                              "id": "d3v87f2",
                                                              "gilded": 0,
                                                              "archived": false,
                                                              "report_reasons": null,
                                                              "author": "SharkFart86",
                                                              "parent_id": "t1_d3v80mz",
                                                              "score": 17,
                                                              "approved_by": null,
                                                              "controversiality": 0,
                                                              "body": "Yeah PA is paper dry in the winter and thick as soup in the summer.",
                                                              "edited": false,
                                                              "author_flair_css_class": null,
                                                              "downs": 0,
                                                              "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Yeah PA is paper dry in the winter and thick as soup in the summer.&lt;/p&gt;\n&lt;/div&gt;",
                                                              "subreddit": "funny",
                                                              "name": "t1_d3v87f2",
                                                              "score_hidden": false,
                                                              "stickied": false,
                                                              "created": 1465031286.0,
                                                              "author_flair_text": null,
                                                              "created_utc": 1465002486.0,
                                                              "distinguished": null,
                                                              "mod_reports": [

                                                              ],
                                                              "num_reports": null,
                                                              "ups": 17
                                                            }
                                                          },
                                                          {
                                                            "kind": "more",
                                                            "data": {
                                                              "count": 9,
                                                              "parent_id": "t1_d3v80mz",
                                                              "id": "d3v8jyg",
                                                              "name": "t1_d3v8jyg",
                                                              "children": [
                                                                "d3v8jyg",
                                                                "d3v8jgq",
                                                                "d3v8mrv",
                                                                "d3v8gyw",
                                                                "d3v8ius",
                                                                "d3v8iqw"
                                                              ]
                                                            }
                                                          }
                                                        ],
                                                        "after": null,
                                                        "before": null
                                                      }
                                                    },
                                                    "user_reports": [

                                                    ],
                                                    "saved": false,
                                                    "id": "d3v80mz",
                                                    "gilded": 0,
                                                    "archived": false,
                                                    "report_reasons": null,
                                                    "author": "Advacar",
                                                    "parent_id": "t1_d3v7ouc",
                                                    "score": 36,
                                                    "approved_by": null,
                                                    "controversiality": 0,
                                                    "body": "Did you have problems in winter? Growing up in PA I used to get terrible itchy skin in the middle of winter.",
                                                    "edited": false,
                                                    "author_flair_css_class": null,
                                                    "downs": 0,
                                                    "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Did you have problems in winter? Growing up in PA I used to get terrible itchy skin in the middle of winter.&lt;/p&gt;\n&lt;/div&gt;",
                                                    "subreddit": "funny",
                                                    "name": "t1_d3v80mz",
                                                    "score_hidden": false,
                                                    "stickied": false,
                                                    "created": 1465030975.0,
                                                    "author_flair_text": null,
                                                    "created_utc": 1465002175.0,
                                                    "distinguished": null,
                                                    "mod_reports": [

                                                    ],
                                                    "num_reports": null,
                                                    "ups": 36
                                                  }
                                                },
                                                {
                                                  "kind": "more",
                                                  "data": {
                                                    "count": 5,
                                                    "parent_id": "t1_d3v7ouc",
                                                    "children": [
                                                      "d3v9kjk",
                                                      "d3vbdz4",
                                                      "d3v878i",
                                                      "d3vaz5w",
                                                      "d3v96hy"
                                                    ],
                                                    "name": "t1_d3v9kjk",
                                                    "id": "d3v9kjk"
                                                  }
                                                }
                                              ],
                                              "after": null,
                                              "before": null
                                            }
                                          },
                                          "user_reports": [

                                          ],
                                          "saved": false,
                                          "id": "d3v7ouc",
                                          "gilded": 0,
                                          "archived": false,
                                          "report_reasons": null,
                                          "author": "I_r_redditmans",
                                          "parent_id": "t1_d3v4u45",
                                          "score": 72,
                                          "approved_by": null,
                                          "controversiality": 0,
                                          "body": "I'm from Maryland but went to school in California. I think I actually started having skin problems because the air was too dry for me.",
                                          "edited": false,
                                          "author_flair_css_class": null,
                                          "downs": 0,
                                          "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;I&amp;#39;m from Maryland but went to school in California. I think I actually started having skin problems because the air was too dry for me.&lt;/p&gt;\n&lt;/div&gt;",
                                          "subreddit": "funny",
                                          "name": "t1_d3v7ouc",
                                          "score_hidden": false,
                                          "stickied": false,
                                          "created": 1465030409.0,
                                          "author_flair_text": null,
                                          "created_utc": 1465001609.0,
                                          "distinguished": null,
                                          "mod_reports": [

                                          ],
                                          "num_reports": null,
                                          "ups": 72
                                        }
                                      },
                                      {
                                        "kind": "t1",
                                        "data": {
                                          "subreddit_id": "t5_2qh33",
                                          "banned_by": null,
                                          "removal_reason": null,
                                          "link_id": "t3_4mfawk",
                                          "likes": null,
                                          "replies": {
                                            "kind": "Listing",
                                            "data": {
                                              "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                              "children": [
                                                {
                                                  "kind": "more",
                                                  "data": {
                                                    "count": 18,
                                                    "parent_id": "t1_d3v82p6",
                                                    "children": [
                                                      "d3va3vk",
                                                      "d3vafr8",
                                                      "d3v8ipz",
                                                      "d3v8ah8",
                                                      "d3vbg8d",
                                                      "d3vbokw",
                                                      "d3v9vn9",
                                                      "d3v8z1y",
                                                      "d3vbd50",
                                                      "d3va6ai",
                                                      "d3v98ov"
                                                    ],
                                                    "name": "t1_d3va3vk",
                                                    "id": "d3va3vk"
                                                  }
                                                }
                                              ],
                                              "after": null,
                                              "before": null
                                            }
                                          },
                                          "user_reports": [

                                          ],
                                          "saved": false,
                                          "id": "d3v82p6",
                                          "gilded": 0,
                                          "archived": false,
                                          "report_reasons": null,
                                          "author": "DeathMonkey6969",
                                          "parent_id": "t1_d3v4u45",
                                          "score": 65,
                                          "approved_by": null,
                                          "controversiality": 0,
                                          "body": "From NorCal and spent a week in Charlotte North Carolina in June was miserable. I thought 95\u00b0 F and 85% humidity was bad, then it rained at 2 o'clock in the afternoon and the humidity went up to 95%. Going outside was like walking into a steam bath. Upside is everything is nice and green all summer instead of dry brown and on fire like Cali. ",
                                          "edited": false,
                                          "author_flair_css_class": null,
                                          "downs": 0,
                                          "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;From NorCal and spent a week in Charlotte North Carolina in June was miserable. I thought 95\u00b0 F and 85% humidity was bad, then it rained at 2 o&amp;#39;clock in the afternoon and the humidity went up to 95%. Going outside was like walking into a steam bath. Upside is everything is nice and green all summer instead of dry brown and on fire like Cali. &lt;/p&gt;\n&lt;/div&gt;",
                                          "subreddit": "funny",
                                          "name": "t1_d3v82p6",
                                          "score_hidden": false,
                                          "stickied": false,
                                          "created": 1465031071.0,
                                          "author_flair_text": null,
                                          "created_utc": 1465002271.0,
                                          "distinguished": null,
                                          "mod_reports": [

                                          ],
                                          "num_reports": null,
                                          "ups": 65
                                        }
                                      },
                                      {
                                        "kind": "t1",
                                        "data": {
                                          "subreddit_id": "t5_2qh33",
                                          "banned_by": null,
                                          "removal_reason": null,
                                          "link_id": "t3_4mfawk",
                                          "likes": null,
                                          "replies": {
                                            "kind": "Listing",
                                            "data": {
                                              "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                              "children": [
                                                {
                                                  "kind": "more",
                                                  "data": {
                                                    "count": 9,
                                                    "parent_id": "t1_d3v7usq",
                                                    "children": [
                                                      "d3vcmh5",
                                                      "d3vc0fi",
                                                      "d3va2i7",
                                                      "d3vaqk1",
                                                      "d3v9j02",
                                                      "d3v8cj9",
                                                      "d3v8sk9"
                                                    ],
                                                    "name": "t1_d3vcmh5",
                                                    "id": "d3vcmh5"
                                                  }
                                                }
                                              ],
                                              "after": null,
                                              "before": null
                                            }
                                          },
                                          "user_reports": [

                                          ],
                                          "saved": false,
                                          "id": "d3v7usq",
                                          "gilded": 0,
                                          "archived": false,
                                          "report_reasons": null,
                                          "author": "Raw_Stanky",
                                          "parent_id": "t1_d3v4u45",
                                          "score": 24,
                                          "approved_by": null,
                                          "controversiality": 0,
                                          "body": "Summer is horrible in Louisiana ",
                                          "edited": false,
                                          "author_flair_css_class": null,
                                          "downs": 0,
                                          "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Summer is horrible in Louisiana &lt;/p&gt;\n&lt;/div&gt;",
                                          "subreddit": "funny",
                                          "name": "t1_d3v7usq",
                                          "score_hidden": false,
                                          "stickied": false,
                                          "created": 1465030697.0,
                                          "author_flair_text": null,
                                          "created_utc": 1465001897.0,
                                          "distinguished": null,
                                          "mod_reports": [

                                          ],
                                          "num_reports": null,
                                          "ups": 24
                                        }
                                      },
                                      {
                                        "kind": "t1",
                                        "data": {
                                          "subreddit_id": "t5_2qh33",
                                          "banned_by": null,
                                          "removal_reason": null,
                                          "link_id": "t3_4mfawk",
                                          "likes": null,
                                          "replies": "",
                                          "user_reports": [

                                          ],
                                          "saved": false,
                                          "id": "d3v8gly",
                                          "gilded": 0,
                                          "archived": false,
                                          "report_reasons": null,
                                          "author": "psychoacer",
                                          "parent_id": "t1_d3v4u45",
                                          "score": 15,
                                          "approved_by": null,
                                          "controversiality": 0,
                                          "body": "Don't travel to Chicago. You can't outrun the bullets when you're being dragged down by the humidity. ",
                                          "edited": false,
                                          "author_flair_css_class": null,
                                          "downs": 0,
                                          "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Don&amp;#39;t travel to Chicago. You can&amp;#39;t outrun the bullets when you&amp;#39;re being dragged down by the humidity. &lt;/p&gt;\n&lt;/div&gt;",
                                          "subreddit": "funny",
                                          "name": "t1_d3v8gly",
                                          "score_hidden": false,
                                          "stickied": false,
                                          "created": 1465031709.0,
                                          "author_flair_text": null,
                                          "created_utc": 1465002909.0,
                                          "distinguished": null,
                                          "mod_reports": [

                                          ],
                                          "num_reports": null,
                                          "ups": 15
                                        }
                                      },
                                      {
                                        "kind": "t1",
                                        "data": {
                                          "subreddit_id": "t5_2qh33",
                                          "banned_by": null,
                                          "removal_reason": null,
                                          "link_id": "t3_4mfawk",
                                          "likes": null,
                                          "replies": {
                                            "kind": "Listing",
                                            "data": {
                                              "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                              "children": [
                                                {
                                                  "kind": "t1",
                                                  "data": {
                                                    "subreddit_id": "t5_2qh33",
                                                    "banned_by": null,
                                                    "removal_reason": null,
                                                    "link_id": "t3_4mfawk",
                                                    "likes": null,
                                                    "replies": {
                                                      "kind": "Listing",
                                                      "data": {
                                                        "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                                        "children": [
                                                          {
                                                            "kind": "more",
                                                            "data": {
                                                              "count": 8,
                                                              "parent_id": "t1_d3v8ims",
                                                              "id": "d3vawsw",
                                                              "name": "t1_d3vawsw",
                                                              "children": [
                                                                "d3vawsw",
                                                                "d3v98pu",
                                                                "d3vbrye",
                                                                "d3v8vjb",
                                                                "d3varbk",
                                                                "d3vcjwx",
                                                                "d3vao50"
                                                              ]
                                                            }
                                                          }
                                                        ],
                                                        "after": null,
                                                        "before": null
                                                      }
                                                    },
                                                    "user_reports": [

                                                    ],
                                                    "saved": false,
                                                    "id": "d3v8ims",
                                                    "gilded": 0,
                                                    "archived": false,
                                                    "report_reasons": null,
                                                    "author": "GrayAntarctica",
                                                    "parent_id": "t1_d3v86p6",
                                                    "score": 14,
                                                    "approved_by": null,
                                                    "controversiality": 0,
                                                    "body": "Ahahahahaha. You're going to have fun come July - August. Enjoy 80-90s and 90%+ humidity if you're remotely near the coast, or DC.",
                                                    "edited": false,
                                                    "author_flair_css_class": null,
                                                    "downs": 0,
                                                    "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Ahahahahaha. You&amp;#39;re going to have fun come July - August. Enjoy 80-90s and 90%+ humidity if you&amp;#39;re remotely near the coast, or DC.&lt;/p&gt;\n&lt;/div&gt;",
                                                    "subreddit": "funny",
                                                    "name": "t1_d3v8ims",
                                                    "score_hidden": false,
                                                    "stickied": false,
                                                    "created": 1465031805.0,
                                                    "author_flair_text": null,
                                                    "created_utc": 1465003005.0,
                                                    "distinguished": null,
                                                    "mod_reports": [

                                                    ],
                                                    "num_reports": null,
                                                    "ups": 14
                                                  }
                                                },
                                                {
                                                  "kind": "more",
                                                  "data": {
                                                    "count": 3,
                                                    "parent_id": "t1_d3v86p6",
                                                    "children": [
                                                      "d3v8rf5",
                                                      "d3vaz79",
                                                      "d3v96ap"
                                                    ],
                                                    "name": "t1_d3v8rf5",
                                                    "id": "d3v8rf5"
                                                  }
                                                }
                                              ],
                                              "after": null,
                                              "before": null
                                            }
                                          },
                                          "user_reports": [

                                          ],
                                          "saved": false,
                                          "id": "d3v86p6",
                                          "gilded": 0,
                                          "archived": false,
                                          "report_reasons": null,
                                          "author": "arkhound",
                                          "parent_id": "t1_d3v4u45",
                                          "score": 15,
                                          "approved_by": null,
                                          "controversiality": 0,
                                          "body": "Can confirm. Moved to Virginia from California. Already uncomfortable and it's only been like 80F at 70% humidity so far.",
                                          "edited": false,
                                          "author_flair_css_class": null,
                                          "downs": 0,
                                          "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Can confirm. Moved to Virginia from California. Already uncomfortable and it&amp;#39;s only been like 80F at 70% humidity so far.&lt;/p&gt;\n&lt;/div&gt;",
                                          "subreddit": "funny",
                                          "name": "t1_d3v86p6",
                                          "score_hidden": false,
                                          "stickied": false,
                                          "created": 1465031254.0,
                                          "author_flair_text": null,
                                          "created_utc": 1465002454.0,
                                          "distinguished": null,
                                          "mod_reports": [

                                          ],
                                          "num_reports": null,
                                          "ups": 15
                                        }
                                      },
                                      {
                                        "kind": "t1",
                                        "data": {
                                          "subreddit_id": "t5_2qh33",
                                          "banned_by": null,
                                          "removal_reason": null,
                                          "link_id": "t3_4mfawk",
                                          "likes": null,
                                          "replies": {
                                            "kind": "Listing",
                                            "data": {
                                              "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                              "children": [
                                                {
                                                  "kind": "more",
                                                  "data": {
                                                    "count": 4,
                                                    "parent_id": "t1_d3v89hv",
                                                    "children": [
                                                      "d3v8rg0",
                                                      "d3v94gi",
                                                      "d3vamej"
                                                    ],
                                                    "name": "t1_d3v8rg0",
                                                    "id": "d3v8rg0"
                                                  }
                                                }
                                              ],
                                              "after": null,
                                              "before": null
                                            }
                                          },
                                          "user_reports": [

                                          ],
                                          "saved": false,
                                          "id": "d3v89hv",
                                          "gilded": 0,
                                          "archived": false,
                                          "report_reasons": null,
                                          "author": "stealingroadsigns",
                                          "parent_id": "t1_d3v4u45",
                                          "score": 8,
                                          "approved_by": null,
                                          "controversiality": 0,
                                          "body": "In New York if it's hot out you're fucked no matter where you go. ",
                                          "edited": false,
                                          "author_flair_css_class": null,
                                          "downs": 0,
                                          "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;In New York if it&amp;#39;s hot out you&amp;#39;re fucked no matter where you go. &lt;/p&gt;\n&lt;/div&gt;",
                                          "subreddit": "funny",
                                          "name": "t1_d3v89hv",
                                          "score_hidden": false,
                                          "stickied": false,
                                          "created": 1465031383.0,
                                          "author_flair_text": null,
                                          "created_utc": 1465002583.0,
                                          "distinguished": null,
                                          "mod_reports": [

                                          ],
                                          "num_reports": null,
                                          "ups": 8
                                        }
                                      },
                                      {
                                        "kind": "more",
                                        "data": {
                                          "count": 7,
                                          "parent_id": "t1_d3v4u45",
                                          "id": "d3va2gz",
                                          "name": "t1_d3va2gz",
                                          "children": [
                                            "d3va2gz",
                                            "d3v8ptx",
                                            "d3vbx3b",
                                            "d3vb9e4",
                                            "d3v9xt8",
                                            "d3v8mi4",
                                            "d3v8xqr"
                                          ]
                                        }
                                      }
                                    ],
                                    "after": null,
                                    "before": null
                                  }
                                },
                                "user_reports": [

                                ],
                                "saved": false,
                                "id": "d3v4u45",
                                "gilded": 0,
                                "archived": false,
                                "report_reasons": null,
                                "author": "baloothebear44",
                                "parent_id": "t1_d3v3ewj",
                                "score": 284,
                                "approved_by": null,
                                "controversiality": 0,
                                "body": "Don't travel to the east coast. Cali is no where near the humidity of the south or north east",
                                "edited": false,
                                "author_flair_css_class": null,
                                "downs": 0,
                                "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Don&amp;#39;t travel to the east coast. Cali is no where near the humidity of the south or north east&lt;/p&gt;\n&lt;/div&gt;",
                                "subreddit": "funny",
                                "name": "t1_d3v4u45",
                                "score_hidden": false,
                                "stickied": false,
                                "created": 1465025404.0,
                                "author_flair_text": null,
                                "created_utc": 1464996604.0,
                                "distinguished": null,
                                "mod_reports": [

                                ],
                                "num_reports": null,
                                "ups": 284
                              }
                            },
                            {
                              "kind": "t1",
                              "data": {
                                "subreddit_id": "t5_2qh33",
                                "banned_by": null,
                                "removal_reason": null,
                                "link_id": "t3_4mfawk",
                                "likes": null,
                                "replies": {
                                  "kind": "Listing",
                                  "data": {
                                    "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                    "children": [
                                      {
                                        "kind": "t1",
                                        "data": {
                                          "subreddit_id": "t5_2qh33",
                                          "banned_by": null,
                                          "removal_reason": null,
                                          "link_id": "t3_4mfawk",
                                          "likes": null,
                                          "replies": {
                                            "kind": "Listing",
                                            "data": {
                                              "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                              "children": [
                                                {
                                                  "kind": "t1",
                                                  "data": {
                                                    "subreddit_id": "t5_2qh33",
                                                    "banned_by": null,
                                                    "removal_reason": null,
                                                    "link_id": "t3_4mfawk",
                                                    "likes": null,
                                                    "replies": {
                                                      "kind": "Listing",
                                                      "data": {
                                                        "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                                        "children": [
                                                          {
                                                            "kind": "t1",
                                                            "data": {
                                                              "subreddit_id": "t5_2qh33",
                                                              "banned_by": null,
                                                              "removal_reason": null,
                                                              "link_id": "t3_4mfawk",
                                                              "likes": null,
                                                              "replies": {
                                                                "kind": "Listing",
                                                                "data": {
                                                                  "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                                                  "children": [
                                                                    {
                                                                      "kind": "more",
                                                                      "data": {
                                                                        "count": 6,
                                                                        "parent_id": "t1_d3v8d0c",
                                                                        "children": [
                                                                          "d3v9h1n",
                                                                          "d3v9cnx",
                                                                          "d3v9gst"
                                                                        ],
                                                                        "name": "t1_d3v9h1n",
                                                                        "id": "d3v9h1n"
                                                                      }
                                                                    }
                                                                  ],
                                                                  "after": null,
                                                                  "before": null
                                                                }
                                                              },
                                                              "user_reports": [

                                                              ],
                                                              "saved": false,
                                                              "id": "d3v8d0c",
                                                              "gilded": 0,
                                                              "archived": false,
                                                              "report_reasons": null,
                                                              "author": "knobbyknees",
                                                              "parent_id": "t1_d3v84wm",
                                                              "score": 26,
                                                              "approved_by": null,
                                                              "controversiality": 0,
                                                              "body": "I grew up in Houston. Every summer I wondered how settlers stood to live here. They must've landed in October and been like, \"Mild! Awesome!\" then were stuck when April rolled around.",
                                                              "edited": false,
                                                              "author_flair_css_class": null,
                                                              "downs": 0,
                                                              "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;I grew up in Houston. Every summer I wondered how settlers stood to live here. They must&amp;#39;ve landed in October and been like, &amp;quot;Mild! Awesome!&amp;quot; then were stuck when April rolled around.&lt;/p&gt;\n&lt;/div&gt;",
                                                              "subreddit": "funny",
                                                              "name": "t1_d3v8d0c",
                                                              "score_hidden": false,
                                                              "stickied": false,
                                                              "created": 1465031548.0,
                                                              "author_flair_text": null,
                                                              "created_utc": 1465002748.0,
                                                              "distinguished": null,
                                                              "mod_reports": [

                                                              ],
                                                              "num_reports": null,
                                                              "ups": 26
                                                            }
                                                          },
                                                          {
                                                            "kind": "t1",
                                                            "data": {
                                                              "subreddit_id": "t5_2qh33",
                                                              "banned_by": null,
                                                              "removal_reason": null,
                                                              "link_id": "t3_4mfawk",
                                                              "likes": null,
                                                              "replies": {
                                                                "kind": "Listing",
                                                                "data": {
                                                                  "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                                                  "children": [
                                                                    {
                                                                      "kind": "more",
                                                                      "data": {
                                                                        "count": 3,
                                                                        "parent_id": "t1_d3v8epu",
                                                                        "children": [
                                                                          "d3va2xd",
                                                                          "d3v9sdo"
                                                                        ],
                                                                        "name": "t1_d3va2xd",
                                                                        "id": "d3va2xd"
                                                                      }
                                                                    }
                                                                  ],
                                                                  "after": null,
                                                                  "before": null
                                                                }
                                                              },
                                                              "user_reports": [

                                                              ],
                                                              "saved": false,
                                                              "id": "d3v8epu",
                                                              "gilded": 0,
                                                              "archived": false,
                                                              "report_reasons": null,
                                                              "author": "Dokpsy",
                                                              "parent_id": "t1_d3v84wm",
                                                              "score": 19,
                                                              "approved_by": null,
                                                              "controversiality": 0,
                                                              "body": "I know I'm home in Houston when I step off the plane and get blanketed in the warm moist air. Doesn't matter the season, the blanket is always there. ",
                                                              "edited": false,
                                                              "author_flair_css_class": null,
                                                              "downs": 0,
                                                              "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;I know I&amp;#39;m home in Houston when I step off the plane and get blanketed in the warm moist air. Doesn&amp;#39;t matter the season, the blanket is always there. &lt;/p&gt;\n&lt;/div&gt;",
                                                              "subreddit": "funny",
                                                              "name": "t1_d3v8epu",
                                                              "score_hidden": false,
                                                              "stickied": false,
                                                              "created": 1465031622.0,
                                                              "author_flair_text": null,
                                                              "created_utc": 1465002822.0,
                                                              "distinguished": null,
                                                              "mod_reports": [

                                                              ],
                                                              "num_reports": null,
                                                              "ups": 19
                                                            }
                                                          },
                                                          {
                                                            "kind": "t1",
                                                            "data": {
                                                              "subreddit_id": "t5_2qh33",
                                                              "banned_by": null,
                                                              "removal_reason": null,
                                                              "link_id": "t3_4mfawk",
                                                              "likes": null,
                                                              "replies": {
                                                                "kind": "Listing",
                                                                "data": {
                                                                  "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                                                  "children": [
                                                                    {
                                                                      "kind": "t1",
                                                                      "data": {
                                                                        "subreddit_id": "t5_2qh33",
                                                                        "banned_by": null,
                                                                        "removal_reason": null,
                                                                        "link_id": "t3_4mfawk",
                                                                        "likes": null,
                                                                        "replies": {
                                                                          "kind": "Listing",
                                                                          "data": {
                                                                            "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                                                            "children": [
                                                                              {
                                                                                "kind": "more",
                                                                                "data": {
                                                                                  "count": 1,
                                                                                  "parent_id": "t1_d3v8x8g",
                                                                                  "id": "d3v9khr",
                                                                                  "name": "t1_d3v9khr",
                                                                                  "children": [
                                                                                    "d3v9khr"
                                                                                  ]
                                                                                }
                                                                              }
                                                                            ],
                                                                            "after": null,
                                                                            "before": null
                                                                          }
                                                                        },
                                                                        "user_reports": [

                                                                        ],
                                                                        "saved": false,
                                                                        "id": "d3v8x8g",
                                                                        "gilded": 0,
                                                                        "archived": false,
                                                                        "report_reasons": null,
                                                                        "author": "exyccc",
                                                                        "parent_id": "t1_d3v8dlx",
                                                                        "score": 12,
                                                                        "approved_by": null,
                                                                        "controversiality": 0,
                                                                        "body": "Florida isn't too bad if you have a nice house with a good air-conditioner and a big American car with a huge compressor so it blows -300F inside ",
                                                                        "edited": false,
                                                                        "author_flair_css_class": null,
                                                                        "downs": 0,
                                                                        "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Florida isn&amp;#39;t too bad if you have a nice house with a good air-conditioner and a big American car with a huge compressor so it blows -300F inside &lt;/p&gt;\n&lt;/div&gt;",
                                                                        "subreddit": "funny",
                                                                        "name": "t1_d3v8x8g",
                                                                        "score_hidden": false,
                                                                        "stickied": false,
                                                                        "created": 1465032506.0,
                                                                        "author_flair_text": null,
                                                                        "created_utc": 1465003706.0,
                                                                        "distinguished": null,
                                                                        "mod_reports": [

                                                                        ],
                                                                        "num_reports": null,
                                                                        "ups": 12
                                                                      }
                                                                    },
                                                                    {
                                                                      "kind": "more",
                                                                      "data": {
                                                                        "count": 2,
                                                                        "parent_id": "t1_d3v8dlx",
                                                                        "children": [
                                                                          "d3v8zpg",
                                                                          "d3vagi7"
                                                                        ],
                                                                        "name": "t1_d3v8zpg",
                                                                        "id": "d3v8zpg"
                                                                      }
                                                                    }
                                                                  ],
                                                                  "after": null,
                                                                  "before": null
                                                                }
                                                              },
                                                              "user_reports": [

                                                              ],
                                                              "saved": false,
                                                              "id": "d3v8dlx",
                                                              "gilded": 0,
                                                              "archived": false,
                                                              "report_reasons": null,
                                                              "author": "Knight42Relyks",
                                                              "parent_id": "t1_d3v84wm",
                                                              "score": 16,
                                                              "approved_by": null,
                                                              "controversiality": 0,
                                                              "body": "*Antlanta, Houston*\n\n^I ^live ^next ^to ^the ^Everglades..",
                                                              "edited": false,
                                                              "author_flair_css_class": null,
                                                              "downs": 0,
                                                              "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;&lt;em&gt;Antlanta, Houston&lt;/em&gt;&lt;/p&gt;\n\n&lt;p&gt;&lt;sup&gt;I&lt;/sup&gt; &lt;sup&gt;live&lt;/sup&gt; &lt;sup&gt;next&lt;/sup&gt; &lt;sup&gt;to&lt;/sup&gt; &lt;sup&gt;the&lt;/sup&gt; &lt;sup&gt;Everglades..&lt;/sup&gt;&lt;/p&gt;\n&lt;/div&gt;",
                                                              "subreddit": "funny",
                                                              "name": "t1_d3v8dlx",
                                                              "score_hidden": false,
                                                              "stickied": false,
                                                              "created": 1465031574.0,
                                                              "author_flair_text": null,
                                                              "created_utc": 1465002774.0,
                                                              "distinguished": null,
                                                              "mod_reports": [

                                                              ],
                                                              "num_reports": null,
                                                              "ups": 16
                                                            }
                                                          },
                                                          {
                                                            "kind": "more",
                                                            "data": {
                                                              "count": 2,
                                                              "parent_id": "t1_d3v84wm",
                                                              "id": "d3v8nvn",
                                                              "name": "t1_d3v8nvn",
                                                              "children": [
                                                                "d3v8nvn",
                                                                "d3v8kic"
                                                              ]
                                                            }
                                                          }
                                                        ],
                                                        "after": null,
                                                        "before": null
                                                      }
                                                    },
                                                    "user_reports": [

                                                    ],
                                                    "saved": false,
                                                    "id": "d3v84wm",
                                                    "gilded": 0,
                                                    "archived": false,
                                                    "report_reasons": null,
                                                    "author": "PM_ME_FAKE_TITS",
                                                    "parent_id": "t1_d3v7pvw",
                                                    "score": 29,
                                                    "approved_by": null,
                                                    "controversiality": 0,
                                                    "body": "Did my PhD in Atlanta.   It was more manageable than Houston.  Fuck that Swamp. ",
                                                    "edited": false,
                                                    "author_flair_css_class": null,
                                                    "downs": 0,
                                                    "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Did my PhD in Atlanta.   It was more manageable than Houston.  Fuck that Swamp. &lt;/p&gt;\n&lt;/div&gt;",
                                                    "subreddit": "funny",
                                                    "name": "t1_d3v84wm",
                                                    "score_hidden": false,
                                                    "stickied": false,
                                                    "created": 1465031172.0,
                                                    "author_flair_text": null,
                                                    "created_utc": 1465002372.0,
                                                    "distinguished": null,
                                                    "mod_reports": [

                                                    ],
                                                    "num_reports": null,
                                                    "ups": 29
                                                  }
                                                },
                                                {
                                                  "kind": "t1",
                                                  "data": {
                                                    "subreddit_id": "t5_2qh33",
                                                    "banned_by": null,
                                                    "removal_reason": null,
                                                    "link_id": "t3_4mfawk",
                                                    "likes": null,
                                                    "replies": {
                                                      "kind": "Listing",
                                                      "data": {
                                                        "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                                        "children": [
                                                          {
                                                            "kind": "more",
                                                            "data": {
                                                              "count": 7,
                                                              "parent_id": "t1_d3v877l",
                                                              "id": "d3v8c7c",
                                                              "name": "t1_d3v8c7c",
                                                              "children": [
                                                                "d3v8c7c",
                                                                "d3v8csw",
                                                                "d3v8jx4"
                                                              ]
                                                            }
                                                          }
                                                        ],
                                                        "after": null,
                                                        "before": null
                                                      }
                                                    },
                                                    "user_reports": [

                                                    ],
                                                    "saved": false,
                                                    "id": "d3v877l",
                                                    "gilded": 0,
                                                    "archived": false,
                                                    "report_reasons": null,
                                                    "author": "Zarbi92",
                                                    "parent_id": "t1_d3v7pvw",
                                                    "score": 14,
                                                    "approved_by": null,
                                                    "controversiality": 0,
                                                    "body": "I live in Savannah. The sun setting doesn't even help.  It's so damn hot and humid, grew up in PA between swamps and thought I knew humidity",
                                                    "edited": false,
                                                    "author_flair_css_class": null,
                                                    "downs": 0,
                                                    "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;I live in Savannah. The sun setting doesn&amp;#39;t even help.  It&amp;#39;s so damn hot and humid, grew up in PA between swamps and thought I knew humidity&lt;/p&gt;\n&lt;/div&gt;",
                                                    "subreddit": "funny",
                                                    "name": "t1_d3v877l",
                                                    "score_hidden": false,
                                                    "stickied": false,
                                                    "created": 1465031277.0,
                                                    "author_flair_text": null,
                                                    "created_utc": 1465002477.0,
                                                    "distinguished": null,
                                                    "mod_reports": [

                                                    ],
                                                    "num_reports": null,
                                                    "ups": 14
                                                  }
                                                },
                                                {
                                                  "kind": "more",
                                                  "data": {
                                                    "count": 5,
                                                    "parent_id": "t1_d3v7pvw",
                                                    "children": [
                                                      "d3v84nt",
                                                      "d3v84dm",
                                                      "d3v88ia",
                                                      "d3v9jxb"
                                                    ],
                                                    "name": "t1_d3v84nt",
                                                    "id": "d3v84nt"
                                                  }
                                                }
                                              ],
                                              "after": null,
                                              "before": null
                                            }
                                          },
                                          "user_reports": [

                                          ],
                                          "saved": false,
                                          "id": "d3v7pvw",
                                          "gilded": 0,
                                          "archived": false,
                                          "report_reasons": null,
                                          "author": "CaptainDAAVE",
                                          "parent_id": "t1_d3v61lx",
                                          "score": 50,
                                          "approved_by": null,
                                          "controversiality": 0,
                                          "body": "Fucking Atlanta man.\n\nWalk for like 1 block and i'm soaked through my shirt like a fucking weirdo",
                                          "edited": false,
                                          "author_flair_css_class": null,
                                          "downs": 0,
                                          "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Fucking Atlanta man.&lt;/p&gt;\n\n&lt;p&gt;Walk for like 1 block and i&amp;#39;m soaked through my shirt like a fucking weirdo&lt;/p&gt;\n&lt;/div&gt;",
                                          "subreddit": "funny",
                                          "name": "t1_d3v7pvw",
                                          "score_hidden": false,
                                          "stickied": false,
                                          "created": 1465030458.0,
                                          "author_flair_text": null,
                                          "created_utc": 1465001658.0,
                                          "distinguished": null,
                                          "mod_reports": [

                                          ],
                                          "num_reports": null,
                                          "ups": 50
                                        }
                                      },
                                      {
                                        "kind": "t1",
                                        "data": {
                                          "subreddit_id": "t5_2qh33",
                                          "banned_by": null,
                                          "removal_reason": null,
                                          "link_id": "t3_4mfawk",
                                          "likes": null,
                                          "replies": {
                                            "kind": "Listing",
                                            "data": {
                                              "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                              "children": [
                                                {
                                                  "kind": "more",
                                                  "data": {
                                                    "count": 3,
                                                    "parent_id": "t1_d3v83vo",
                                                    "children": [
                                                      "d3v8mga",
                                                      "d3v91ml",
                                                      "d3v8ub3"
                                                    ],
                                                    "name": "t1_d3v8mga",
                                                    "id": "d3v8mga"
                                                  }
                                                }
                                              ],
                                              "after": null,
                                              "before": null
                                            }
                                          },
                                          "user_reports": [

                                          ],
                                          "saved": false,
                                          "id": "d3v83vo",
                                          "gilded": 0,
                                          "archived": false,
                                          "report_reasons": null,
                                          "author": "SomeGuyNamedPaul",
                                          "parent_id": "t1_d3v61lx",
                                          "score": 12,
                                          "approved_by": null,
                                          "controversiality": 0,
                                          "body": "Florida reporting in, fuck high humidity.",
                                          "edited": false,
                                          "author_flair_css_class": null,
                                          "downs": 0,
                                          "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Florida reporting in, fuck high humidity.&lt;/p&gt;\n&lt;/div&gt;",
                                          "subreddit": "funny",
                                          "name": "t1_d3v83vo",
                                          "score_hidden": false,
                                          "stickied": false,
                                          "created": 1465031124.0,
                                          "author_flair_text": null,
                                          "created_utc": 1465002324.0,
                                          "distinguished": null,
                                          "mod_reports": [

                                          ],
                                          "num_reports": null,
                                          "ups": 12
                                        }
                                      },
                                      {
                                        "kind": "more",
                                        "data": {
                                          "count": 5,
                                          "parent_id": "t1_d3v61lx",
                                          "id": "d3v9vdu",
                                          "name": "t1_d3v9vdu",
                                          "children": [
                                            "d3v9vdu",
                                            "d3v7rs5",
                                            "d3va4gf"
                                          ]
                                        }
                                      }
                                    ],
                                    "after": null,
                                    "before": null
                                  }
                                },
                                "user_reports": [

                                ],
                                "saved": false,
                                "id": "d3v61lx",
                                "gilded": 0,
                                "archived": false,
                                "report_reasons": null,
                                "author": "Exodor",
                                "parent_id": "t1_d3v3ewj",
                                "score": 81,
                                "approved_by": null,
                                "controversiality": 0,
                                "body": "I moved to Knoxville from the Bay Area. Trust me; until you've been to the American South, you haven't experienced humidity yet.",
                                "edited": false,
                                "author_flair_css_class": null,
                                "downs": 0,
                                "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;I moved to Knoxville from the Bay Area. Trust me; until you&amp;#39;ve been to the American South, you haven&amp;#39;t experienced humidity yet.&lt;/p&gt;\n&lt;/div&gt;",
                                "subreddit": "funny",
                                "name": "t1_d3v61lx",
                                "score_hidden": false,
                                "stickied": false,
                                "created": 1465027519.0,
                                "author_flair_text": null,
                                "created_utc": 1464998719.0,
                                "distinguished": null,
                                "mod_reports": [

                                ],
                                "num_reports": null,
                                "ups": 81
                              }
                            },
                            {
                              "kind": "t1",
                              "data": {
                                "subreddit_id": "t5_2qh33",
                                "banned_by": null,
                                "removal_reason": null,
                                "link_id": "t3_4mfawk",
                                "likes": null,
                                "replies": "",
                                "user_reports": [

                                ],
                                "saved": false,
                                "id": "d3v83ln",
                                "gilded": 0,
                                "archived": false,
                                "report_reasons": null,
                                "author": "rileyrulesu",
                                "parent_id": "t1_d3v3ewj",
                                "score": 28,
                                "approved_by": null,
                                "controversiality": 0,
                                "body": "Yep. I'm from florida, and when I went to Phoenix, I was shocked at how hot compared to our 95^o summers it was, but then I learned spraying water on my face actually cooled me down, and it was great! If you do that in florida it just makes it worse.",
                                "edited": false,
                                "author_flair_css_class": null,
                                "downs": 0,
                                "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Yep. I&amp;#39;m from florida, and when I went to Phoenix, I was shocked at how hot compared to our 95&lt;sup&gt;o&lt;/sup&gt; summers it was, but then I learned spraying water on my face actually cooled me down, and it was great! If you do that in florida it just makes it worse.&lt;/p&gt;\n&lt;/div&gt;",
                                "subreddit": "funny",
                                "name": "t1_d3v83ln",
                                "score_hidden": false,
                                "stickied": false,
                                "created": 1465031111.0,
                                "author_flair_text": null,
                                "created_utc": 1465002311.0,
                                "distinguished": null,
                                "mod_reports": [

                                ],
                                "num_reports": null,
                                "ups": 28
                              }
                            },
                            {
                              "kind": "more",
                              "data": {
                                "count": 15,
                                "parent_id": "t1_d3v3ewj",
                                "children": [
                                  "d3v8xs1",
                                  "d3v84kl",
                                  "d3v8h0n",
                                  "d3v89gr",
                                  "d3varim",
                                  "d3va9qn",
                                  "d3v8e34",
                                  "d3vbwmw",
                                  "d3vcfrn",
                                  "d3vadti",
                                  "d3v9u6w",
                                  "d3vbtve",
                                  "d3v8sov"
                                ],
                                "name": "t1_d3v8xs1",
                                "id": "d3v8xs1"
                              }
                            }
                          ],
                          "after": null,
                          "before": null
                        }
                      },
                      "user_reports": [

                      ],
                      "saved": false,
                      "id": "d3v3ewj",
                      "gilded": 0,
                      "archived": false,
                      "report_reasons": null,
                      "author": "SaffellBot",
                      "parent_id": "t1_d3v2yc4",
                      "score": 113,
                      "approved_by": null,
                      "controversiality": 0,
                      "body": "Yeah, that's the nice thing about the Desert. Shade and swamp coolers work really well. Denver is the same, through not as extreme.\n\nThe first time I went to Cali it fucked me up. It was a hot day, so I decided to hang out in the shade. It blew my mind that it didn't help.",
                      "edited": false,
                      "author_flair_css_class": null,
                      "downs": 0,
                      "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Yeah, that&amp;#39;s the nice thing about the Desert. Shade and swamp coolers work really well. Denver is the same, through not as extreme.&lt;/p&gt;\n\n&lt;p&gt;The first time I went to Cali it fucked me up. It was a hot day, so I decided to hang out in the shade. It blew my mind that it didn&amp;#39;t help.&lt;/p&gt;\n&lt;/div&gt;",
                      "subreddit": "funny",
                      "name": "t1_d3v3ewj",
                      "score_hidden": false,
                      "stickied": false,
                      "created": 1465022978.0,
                      "author_flair_text": null,
                      "created_utc": 1464994178.0,
                      "distinguished": null,
                      "mod_reports": [

                      ],
                      "num_reports": null,
                      "ups": 113
                    }
                  },
                  {
                    "kind": "t1",
                    "data": {
                      "subreddit_id": "t5_2qh33",
                      "banned_by": null,
                      "removal_reason": null,
                      "link_id": "t3_4mfawk",
                      "likes": null,
                      "replies": {
                        "kind": "Listing",
                        "data": {
                          "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                          "children": [
                            {
                              "kind": "t1",
                              "data": {
                                "subreddit_id": "t5_2qh33",
                                "banned_by": null,
                                "removal_reason": null,
                                "link_id": "t3_4mfawk",
                                "likes": null,
                                "replies": {
                                  "kind": "Listing",
                                  "data": {
                                    "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                    "children": [
                                      {
                                        "kind": "more",
                                        "data": {
                                          "count": 8,
                                          "parent_id": "t1_d3v8h30",
                                          "id": "d3v9515",
                                          "name": "t1_d3v9515",
                                          "children": [
                                            "d3v9515",
                                            "d3v95ub",
                                            "d3vc1yu",
                                            "d3vawzj"
                                          ]
                                        }
                                      }
                                    ],
                                    "after": null,
                                    "before": null
                                  }
                                },
                                "user_reports": [

                                ],
                                "saved": false,
                                "id": "d3v8h30",
                                "gilded": 0,
                                "archived": false,
                                "report_reasons": null,
                                "author": "DJ63010",
                                "parent_id": "t1_d3v7gg4",
                                "score": 56,
                                "approved_by": null,
                                "controversiality": 0,
                                "body": "Friend of mine went to Phoenix for a week of vacation and it rained the whole week, he said it was a dry rain though. ",
                                "edited": false,
                                "author_flair_css_class": null,
                                "downs": 0,
                                "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Friend of mine went to Phoenix for a week of vacation and it rained the whole week, he said it was a dry rain though. &lt;/p&gt;\n&lt;/div&gt;",
                                "subreddit": "funny",
                                "name": "t1_d3v8h30",
                                "score_hidden": false,
                                "stickied": false,
                                "created": 1465031731.0,
                                "author_flair_text": null,
                                "created_utc": 1465002931.0,
                                "distinguished": null,
                                "mod_reports": [

                                ],
                                "num_reports": null,
                                "ups": 56
                              }
                            },
                            {
                              "kind": "more",
                              "data": {
                                "count": 11,
                                "parent_id": "t1_d3v7gg4",
                                "children": [
                                  "d3v979c",
                                  "d3v9m02",
                                  "d3v8rmv",
                                  "d3va622",
                                  "d3va3si",
                                  "d3v8km0"
                                ],
                                "name": "t1_d3v979c",
                                "id": "d3v979c"
                              }
                            }
                          ],
                          "after": null,
                          "before": null
                        }
                      },
                      "user_reports": [

                      ],
                      "saved": false,
                      "id": "d3v7gg4",
                      "gilded": 0,
                      "archived": false,
                      "report_reasons": null,
                      "author": "YouEnglishNotSoGood",
                      "parent_id": "t1_d3v2yc4",
                      "score": 70,
                      "approved_by": null,
                      "controversiality": 0,
                      "body": "Spent some time in Phoenix last year. \n\nWhile it was 85-90 in Tennessee (where I'm from, like you), it was 110-115 in AZ.  It felt much, much cooler and quite tolerable in AZ. \n\nAll of my life I've heard \"it's not the heat, it's the humidity\". Never believed it until then. ",
                      "edited": 1465005751.0,
                      "author_flair_css_class": null,
                      "downs": 0,
                      "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Spent some time in Phoenix last year. &lt;/p&gt;\n\n&lt;p&gt;While it was 85-90 in Tennessee (where I&amp;#39;m from, like you), it was 110-115 in AZ.  It felt much, much cooler and quite tolerable in AZ. &lt;/p&gt;\n\n&lt;p&gt;All of my life I&amp;#39;ve heard &amp;quot;it&amp;#39;s not the heat, it&amp;#39;s the humidity&amp;quot;. Never believed it until then. &lt;/p&gt;\n&lt;/div&gt;",
                      "subreddit": "funny",
                      "name": "t1_d3v7gg4",
                      "score_hidden": false,
                      "stickied": false,
                      "created": 1465030004.0,
                      "author_flair_text": null,
                      "created_utc": 1465001204.0,
                      "distinguished": null,
                      "mod_reports": [

                      ],
                      "num_reports": null,
                      "ups": 70
                    }
                  },
                  {
                    "kind": "t1",
                    "data": {
                      "subreddit_id": "t5_2qh33",
                      "banned_by": null,
                      "removal_reason": null,
                      "link_id": "t3_4mfawk",
                      "likes": null,
                      "replies": {
                        "kind": "Listing",
                        "data": {
                          "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                          "children": [
                            {
                              "kind": "t1",
                              "data": {
                                "subreddit_id": "t5_2qh33",
                                "banned_by": null,
                                "removal_reason": null,
                                "link_id": "t3_4mfawk",
                                "likes": null,
                                "replies": {
                                  "kind": "Listing",
                                  "data": {
                                    "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                    "children": [
                                      {
                                        "kind": "more",
                                        "data": {
                                          "count": 11,
                                          "parent_id": "t1_d3v88pi",
                                          "id": "d3vap7z",
                                          "name": "t1_d3vap7z",
                                          "children": [
                                            "d3vap7z",
                                            "d3vav01",
                                            "d3vbiyc",
                                            "d3v99ju",
                                            "d3va7cf",
                                            "d3vc4d8",
                                            "d3vbxtm",
                                            "d3v8kgv"
                                          ]
                                        }
                                      }
                                    ],
                                    "after": null,
                                    "before": null
                                  }
                                },
                                "user_reports": [

                                ],
                                "saved": false,
                                "id": "d3v88pi",
                                "gilded": 0,
                                "archived": false,
                                "report_reasons": null,
                                "author": "zaphod_85",
                                "parent_id": "t1_d3v4ure",
                                "score": 52,
                                "approved_by": null,
                                "controversiality": 0,
                                "body": "The worst part is that the sweat just doesn't evaporate. You just get to stew in your own juices all day long.",
                                "edited": false,
                                "author_flair_css_class": null,
                                "downs": 0,
                                "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;The worst part is that the sweat just doesn&amp;#39;t evaporate. You just get to stew in your own juices all day long.&lt;/p&gt;\n&lt;/div&gt;",
                                "subreddit": "funny",
                                "name": "t1_d3v88pi",
                                "score_hidden": false,
                                "stickied": false,
                                "created": 1465031346.0,
                                "author_flair_text": null,
                                "created_utc": 1465002546.0,
                                "distinguished": null,
                                "mod_reports": [

                                ],
                                "num_reports": null,
                                "ups": 52
                              }
                            },
                            {
                              "kind": "more",
                              "data": {
                                "count": 22,
                                "parent_id": "t1_d3v4ure",
                                "children": [
                                  "d3vaf1c",
                                  "d3v9upv",
                                  "d3v9yd2",
                                  "d3v93za",
                                  "d3v925j",
                                  "d3vaetj",
                                  "d3v9p72",
                                  "d3v9rv2",
                                  "d3v8omu"
                                ],
                                "name": "t1_d3vaf1c",
                                "id": "d3vaf1c"
                              }
                            }
                          ],
                          "after": null,
                          "before": null
                        }
                      },
                      "user_reports": [

                      ],
                      "saved": false,
                      "id": "d3v4ure",
                      "gilded": 0,
                      "archived": false,
                      "report_reasons": null,
                      "author": "Buttersgoo23",
                      "parent_id": "t1_d3v2yc4",
                      "score": 51,
                      "approved_by": null,
                      "controversiality": 0,
                      "body": "The humidity makes a huge difference. I live in St. Louis and we've been having some brutal summers lately. It was only 96 with heat index the other day but the humidity must have been close to 80 or 90% and within 10 minutes of being outside I was drenched in sweat. Literally drenched. ",
                      "edited": false,
                      "author_flair_css_class": null,
                      "downs": 0,
                      "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;The humidity makes a huge difference. I live in St. Louis and we&amp;#39;ve been having some brutal summers lately. It was only 96 with heat index the other day but the humidity must have been close to 80 or 90% and within 10 minutes of being outside I was drenched in sweat. Literally drenched. &lt;/p&gt;\n&lt;/div&gt;",
                      "subreddit": "funny",
                      "name": "t1_d3v4ure",
                      "score_hidden": false,
                      "stickied": false,
                      "created": 1465025434.0,
                      "author_flair_text": null,
                      "created_utc": 1464996634.0,
                      "distinguished": null,
                      "mod_reports": [

                      ],
                      "num_reports": null,
                      "ups": 51
                    }
                  },
                  {
                    "kind": "t1",
                    "data": {
                      "subreddit_id": "t5_2qh33",
                      "banned_by": null,
                      "removal_reason": null,
                      "link_id": "t3_4mfawk",
                      "likes": null,
                      "replies": {
                        "kind": "Listing",
                        "data": {
                          "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                          "children": [
                            {
                              "kind": "t1",
                              "data": {
                                "subreddit_id": "t5_2qh33",
                                "banned_by": null,
                                "removal_reason": null,
                                "link_id": "t3_4mfawk",
                                "likes": null,
                                "replies": {
                                  "kind": "Listing",
                                  "data": {
                                    "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                    "children": [
                                      {
                                        "kind": "t1",
                                        "data": {
                                          "subreddit_id": "t5_2qh33",
                                          "banned_by": null,
                                          "removal_reason": null,
                                          "link_id": "t3_4mfawk",
                                          "likes": null,
                                          "replies": {
                                            "kind": "Listing",
                                            "data": {
                                              "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                              "children": [
                                                {
                                                  "kind": "t1",
                                                  "data": {
                                                    "subreddit_id": "t5_2qh33",
                                                    "banned_by": null,
                                                    "removal_reason": null,
                                                    "link_id": "t3_4mfawk",
                                                    "likes": null,
                                                    "replies": {
                                                      "kind": "Listing",
                                                      "data": {
                                                        "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                                        "children": [
                                                          {
                                                            "kind": "more",
                                                            "data": {
                                                              "count": 1,
                                                              "parent_id": "t1_d3v7zba",
                                                              "id": "d3vbze6",
                                                              "name": "t1_d3vbze6",
                                                              "children": [
                                                                "d3vbze6"
                                                              ]
                                                            }
                                                          }
                                                        ],
                                                        "after": null,
                                                        "before": null
                                                      }
                                                    },
                                                    "user_reports": [

                                                    ],
                                                    "saved": false,
                                                    "id": "d3v7zba",
                                                    "gilded": 0,
                                                    "archived": false,
                                                    "report_reasons": null,
                                                    "author": "The-Mathematician",
                                                    "parent_id": "t1_d3v6h1m",
                                                    "score": 20,
                                                    "approved_by": null,
                                                    "controversiality": 0,
                                                    "body": "You could steam a lobster by setting it outside.",
                                                    "edited": false,
                                                    "author_flair_css_class": null,
                                                    "downs": 0,
                                                    "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;You could steam a lobster by setting it outside.&lt;/p&gt;\n&lt;/div&gt;",
                                                    "subreddit": "funny",
                                                    "name": "t1_d3v7zba",
                                                    "score_hidden": false,
                                                    "stickied": false,
                                                    "created": 1465030913.0,
                                                    "author_flair_text": null,
                                                    "created_utc": 1465002113.0,
                                                    "distinguished": null,
                                                    "mod_reports": [

                                                    ],
                                                    "num_reports": null,
                                                    "ups": 20
                                                  }
                                                },
                                                {
                                                  "kind": "more",
                                                  "data": {
                                                    "count": 3,
                                                    "parent_id": "t1_d3v6h1m",
                                                    "children": [
                                                      "d3v9pzg",
                                                      "d3v89uc",
                                                      "d3v9l5b"
                                                    ],
                                                    "name": "t1_d3v9pzg",
                                                    "id": "d3v9pzg"
                                                  }
                                                }
                                              ],
                                              "after": null,
                                              "before": null
                                            }
                                          },
                                          "user_reports": [

                                          ],
                                          "saved": false,
                                          "id": "d3v6h1m",
                                          "gilded": 0,
                                          "archived": false,
                                          "report_reasons": null,
                                          "author": "Exodor",
                                          "parent_id": "t1_d3v68dx",
                                          "score": 18,
                                          "approved_by": null,
                                          "controversiality": 0,
                                          "body": "That's just disgusting. How can anyone live in those conditions?",
                                          "edited": false,
                                          "author_flair_css_class": null,
                                          "downs": 0,
                                          "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;That&amp;#39;s just disgusting. How can anyone live in those conditions?&lt;/p&gt;\n&lt;/div&gt;",
                                          "subreddit": "funny",
                                          "name": "t1_d3v6h1m",
                                          "score_hidden": false,
                                          "stickied": false,
                                          "created": 1465028280.0,
                                          "author_flair_text": null,
                                          "created_utc": 1464999480.0,
                                          "distinguished": null,
                                          "mod_reports": [

                                          ],
                                          "num_reports": null,
                                          "ups": 18
                                        }
                                      },
                                      {
                                        "kind": "more",
                                        "data": {
                                          "count": 8,
                                          "parent_id": "t1_d3v68dx",
                                          "id": "d3v8nfc",
                                          "name": "t1_d3v8nfc",
                                          "children": [
                                            "d3v8nfc",
                                            "d3v8dkj",
                                            "d3v6hpx",
                                            "d3va9n1"
                                          ]
                                        }
                                      }
                                    ],
                                    "after": null,
                                    "before": null
                                  }
                                },
                                "user_reports": [

                                ],
                                "saved": false,
                                "id": "d3v68dx",
                                "gilded": 0,
                                "archived": false,
                                "report_reasons": null,
                                "author": "FoximusMaximus",
                                "parent_id": "t1_d3v5flg",
                                "score": 38,
                                "approved_by": null,
                                "controversiality": 0,
                                "body": "Not all parts of the ME have a dry heat.  Iraq is pretty dry so it's not a big deal.  Qatar on the other hand somehow finds a way to be 120+, 100% humidity, and STILL be a desert.  I got off the plane at 2am local time mid August and was instantly drenched.",
                                "edited": false,
                                "author_flair_css_class": null,
                                "downs": 0,
                                "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Not all parts of the ME have a dry heat.  Iraq is pretty dry so it&amp;#39;s not a big deal.  Qatar on the other hand somehow finds a way to be 120+, 100% humidity, and STILL be a desert.  I got off the plane at 2am local time mid August and was instantly drenched.&lt;/p&gt;\n&lt;/div&gt;",
                                "subreddit": "funny",
                                "name": "t1_d3v68dx",
                                "score_hidden": false,
                                "stickied": false,
                                "created": 1465027851.0,
                                "author_flair_text": null,
                                "created_utc": 1464999051.0,
                                "distinguished": null,
                                "mod_reports": [

                                ],
                                "num_reports": null,
                                "ups": 38
                              }
                            },
                            {
                              "kind": "more",
                              "data": {
                                "count": 2,
                                "parent_id": "t1_d3v5flg",
                                "children": [
                                  "d3v9ttw",
                                  "d3va9qm"
                                ],
                                "name": "t1_d3v9ttw",
                                "id": "d3v9ttw"
                              }
                            }
                          ],
                          "after": null,
                          "before": null
                        }
                      },
                      "user_reports": [

                      ],
                      "saved": false,
                      "id": "d3v5flg",
                      "gilded": 0,
                      "archived": false,
                      "report_reasons": null,
                      "author": "brandontripp",
                      "parent_id": "t1_d3v2yc4",
                      "score": 33,
                      "approved_by": null,
                      "controversiality": 0,
                      "body": "&gt; To me, the difference between a searing dry heat and an oppressive wet heat is huge\n\nIt really is. I'm in Canada (southern ontario) and I've had people come from the middle east in a desert and tell me that the heat there is less oppressive than the heat here because of the humidity.",
                      "edited": false,
                      "author_flair_css_class": null,
                      "downs": 0,
                      "body_html": "&lt;div class=\"md\"&gt;&lt;blockquote&gt;\n&lt;p&gt;To me, the difference between a searing dry heat and an oppressive wet heat is huge&lt;/p&gt;\n&lt;/blockquote&gt;\n\n&lt;p&gt;It really is. I&amp;#39;m in Canada (southern ontario) and I&amp;#39;ve had people come from the middle east in a desert and tell me that the heat there is less oppressive than the heat here because of the humidity.&lt;/p&gt;\n&lt;/div&gt;",
                      "subreddit": "funny",
                      "name": "t1_d3v5flg",
                      "score_hidden": false,
                      "stickied": false,
                      "created": 1465026436.0,
                      "author_flair_text": null,
                      "created_utc": 1464997636.0,
                      "distinguished": null,
                      "mod_reports": [

                      ],
                      "num_reports": null,
                      "ups": 33
                    }
                  },
                  {
                    "kind": "t1",
                    "data": {
                      "subreddit_id": "t5_2qh33",
                      "banned_by": null,
                      "removal_reason": null,
                      "link_id": "t3_4mfawk",
                      "likes": null,
                      "replies": {
                        "kind": "Listing",
                        "data": {
                          "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                          "children": [
                            {
                              "kind": "more",
                              "data": {
                                "count": 5,
                                "parent_id": "t1_d3v78td",
                                "children": [
                                  "d3v85xg"
                                ],
                                "name": "t1_d3v85xg",
                                "id": "d3v85xg"
                              }
                            }
                          ],
                          "after": null,
                          "before": null
                        }
                      },
                      "user_reports": [

                      ],
                      "saved": false,
                      "id": "d3v78td",
                      "gilded": 0,
                      "archived": false,
                      "report_reasons": null,
                      "author": "drdanieldoom",
                      "parent_id": "t1_d3v2yc4",
                      "score": 23,
                      "approved_by": null,
                      "controversiality": 0,
                      "body": "Go Vols! ",
                      "edited": false,
                      "author_flair_css_class": null,
                      "downs": 0,
                      "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Go Vols! &lt;/p&gt;\n&lt;/div&gt;",
                      "subreddit": "funny",
                      "name": "t1_d3v78td",
                      "score_hidden": false,
                      "stickied": false,
                      "created": 1465029627.0,
                      "author_flair_text": null,
                      "created_utc": 1465000827.0,
                      "distinguished": null,
                      "mod_reports": [

                      ],
                      "num_reports": null,
                      "ups": 23
                    }
                  },
                  {
                    "kind": "t1",
                    "data": {
                      "subreddit_id": "t5_2qh33",
                      "banned_by": null,
                      "removal_reason": null,
                      "link_id": "t3_4mfawk",
                      "likes": null,
                      "replies": {
                        "kind": "Listing",
                        "data": {
                          "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                          "children": [
                            {
                              "kind": "more",
                              "data": {
                                "count": 3,
                                "parent_id": "t1_d3v539g",
                                "children": [
                                  "d3v9cmv"
                                ],
                                "name": "t1_d3v9cmv",
                                "id": "d3v9cmv"
                              }
                            }
                          ],
                          "after": null,
                          "before": null
                        }
                      },
                      "user_reports": [

                      ],
                      "saved": false,
                      "id": "d3v539g",
                      "gilded": 0,
                      "archived": false,
                      "report_reasons": null,
                      "author": "SPacific",
                      "parent_id": "t1_d3v2yc4",
                      "score": 17,
                      "approved_by": null,
                      "controversiality": 0,
                      "body": "I lived in Tucson for most of my life and I despised the heat and the desert. I moved to the Midwest about ten years ago and now I really miss it.",
                      "edited": false,
                      "author_flair_css_class": null,
                      "downs": 0,
                      "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;I lived in Tucson for most of my life and I despised the heat and the desert. I moved to the Midwest about ten years ago and now I really miss it.&lt;/p&gt;\n&lt;/div&gt;",
                      "subreddit": "funny",
                      "name": "t1_d3v539g",
                      "score_hidden": false,
                      "stickied": false,
                      "created": 1465025840.0,
                      "author_flair_text": null,
                      "created_utc": 1464997040.0,
                      "distinguished": null,
                      "mod_reports": [

                      ],
                      "num_reports": null,
                      "ups": 17
                    }
                  },
                  {
                    "kind": "t1",
                    "data": {
                      "subreddit_id": "t5_2qh33",
                      "banned_by": null,
                      "removal_reason": null,
                      "link_id": "t3_4mfawk",
                      "likes": null,
                      "replies": {
                        "kind": "Listing",
                        "data": {
                          "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                          "children": [
                            {
                              "kind": "more",
                              "data": {
                                "count": 3,
                                "parent_id": "t1_d3v82sv",
                                "children": [
                                  "d3vb85l",
                                  "d3v981q"
                                ],
                                "name": "t1_d3vb85l",
                                "id": "d3vb85l"
                              }
                            }
                          ],
                          "after": null,
                          "before": null
                        }
                      },
                      "user_reports": [

                      ],
                      "saved": false,
                      "id": "d3v82sv",
                      "gilded": 0,
                      "archived": false,
                      "report_reasons": null,
                      "author": "FrogAttackLite",
                      "parent_id": "t1_d3v2yc4",
                      "score": 12,
                      "approved_by": null,
                      "controversiality": 0,
                      "body": "It's best to use Dew Point to gauge how comfortable it will feel. 115 degrees with 10 percent humidity is only 46 degree dew point whereas a 90 degree day with 50 percent humidity is 69 degree dew point. It might seem a bit dry with humidity under 30 but compared to high humidity most people will happily take it.\n\nWind plays a huge factor in it as well but I don't know of any index's that take all three into account very well. With the dry heat wind will have more of an effect, in a humid heat the is necessary or it will feel like a swampy rain forest inside a steam engine. With a humid heat the wind can make it feel warmer if the temperature gets high enough.\n\nAs you said the shade in a dry heat will give you a massive comfort boost because the sun isn't beating down on you, without the moisture in the air it's a lot more direct radiation hitting you. When it gets that hot it's actually advised you don't go outside if you can help it.\n\nAlso with dry areas you have a huge diurnal temperature variation so at night it will cool down significantly whereas in humid areas it will often be warmer because the temperature drops only a little but the humidity spikes to near maximum.\n\nI'll take a dry heat over humidity ANY day, or night for that matter.",
                      "edited": false,
                      "author_flair_css_class": null,
                      "downs": 0,
                      "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;It&amp;#39;s best to use Dew Point to gauge how comfortable it will feel. 115 degrees with 10 percent humidity is only 46 degree dew point whereas a 90 degree day with 50 percent humidity is 69 degree dew point. It might seem a bit dry with humidity under 30 but compared to high humidity most people will happily take it.&lt;/p&gt;\n\n&lt;p&gt;Wind plays a huge factor in it as well but I don&amp;#39;t know of any index&amp;#39;s that take all three into account very well. With the dry heat wind will have more of an effect, in a humid heat the is necessary or it will feel like a swampy rain forest inside a steam engine. With a humid heat the wind can make it feel warmer if the temperature gets high enough.&lt;/p&gt;\n\n&lt;p&gt;As you said the shade in a dry heat will give you a massive comfort boost because the sun isn&amp;#39;t beating down on you, without the moisture in the air it&amp;#39;s a lot more direct radiation hitting you. When it gets that hot it&amp;#39;s actually advised you don&amp;#39;t go outside if you can help it.&lt;/p&gt;\n\n&lt;p&gt;Also with dry areas you have a huge diurnal temperature variation so at night it will cool down significantly whereas in humid areas it will often be warmer because the temperature drops only a little but the humidity spikes to near maximum.&lt;/p&gt;\n\n&lt;p&gt;I&amp;#39;ll take a dry heat over humidity ANY day, or night for that matter.&lt;/p&gt;\n&lt;/div&gt;",
                      "subreddit": "funny",
                      "name": "t1_d3v82sv",
                      "score_hidden": false,
                      "stickied": false,
                      "created": 1465031075.0,
                      "author_flair_text": null,
                      "created_utc": 1465002275.0,
                      "distinguished": null,
                      "mod_reports": [

                      ],
                      "num_reports": null,
                      "ups": 12
                    }
                  },
                  {
                    "kind": "t1",
                    "data": {
                      "subreddit_id": "t5_2qh33",
                      "banned_by": null,
                      "removal_reason": null,
                      "link_id": "t3_4mfawk",
                      "likes": null,
                      "replies": {
                        "kind": "Listing",
                        "data": {
                          "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                          "children": [
                            {
                              "kind": "t1",
                              "data": {
                                "subreddit_id": "t5_2qh33",
                                "banned_by": null,
                                "removal_reason": null,
                                "link_id": "t3_4mfawk",
                                "likes": null,
                                "replies": "",
                                "user_reports": [

                                ],
                                "saved": false,
                                "id": "d3v8e0n",
                                "gilded": 0,
                                "archived": false,
                                "report_reasons": null,
                                "author": "Chubbadog",
                                "parent_id": "t1_d3v62er",
                                "score": 10,
                                "approved_by": null,
                                "controversiality": 0,
                                "body": "Ah, the lost city of Atlanta.",
                                "edited": false,
                                "author_flair_css_class": null,
                                "downs": 0,
                                "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Ah, the lost city of Atlanta.&lt;/p&gt;\n&lt;/div&gt;",
                                "subreddit": "funny",
                                "name": "t1_d3v8e0n",
                                "score_hidden": false,
                                "stickied": false,
                                "created": 1465031592.0,
                                "author_flair_text": null,
                                "created_utc": 1465002792.0,
                                "distinguished": null,
                                "mod_reports": [

                                ],
                                "num_reports": null,
                                "ups": 10
                              }
                            }
                          ],
                          "after": null,
                          "before": null
                        }
                      },
                      "user_reports": [

                      ],
                      "saved": false,
                      "id": "d3v62er",
                      "gilded": 0,
                      "archived": false,
                      "report_reasons": null,
                      "author": "Jiggynerd",
                      "parent_id": "t1_d3v2yc4",
                      "score": 9,
                      "approved_by": null,
                      "controversiality": 0,
                      "body": "We went too! When i got back to Atlanta i felt like i needed gills",
                      "edited": false,
                      "author_flair_css_class": null,
                      "downs": 0,
                      "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;We went too! When i got back to Atlanta i felt like i needed gills&lt;/p&gt;\n&lt;/div&gt;",
                      "subreddit": "funny",
                      "name": "t1_d3v62er",
                      "score_hidden": false,
                      "stickied": false,
                      "created": 1465027557.0,
                      "author_flair_text": null,
                      "created_utc": 1464998757.0,
                      "distinguished": null,
                      "mod_reports": [

                      ],
                      "num_reports": null,
                      "ups": 9
                    }
                  },
                  {
                    "kind": "more",
                    "data": {
                      "count": 169,
                      "parent_id": "t1_d3v2yc4",
                      "id": "d3v8zrd",
                      "name": "t1_d3v8zrd",
                      "children": [
                        "d3v8zrd",
                        "d3v9wjy",
                        "d3v8w7k",
                        "d3v895k",
                        "d3v8zdf",
                        "d3v8cgr",
                        "d3va0wt",
                        "d3v3agh",
                        "d3vckf0",
                        "d3v84zf",
                        "d3v9av4",
                        "d3varsy",
                        "d3vba24",
                        "d3v9rgq",
                        "d3v4le8",
                        "d3va79v",
                        "d3v93ro",
                        "d3v90lx",
                        "d3v9vfc",
                        "d3v7of5",
                        "d3v91t0",
                        "d3vakq2",
                        "d3v8twv",
                        "d3vah68",
                        "d3v9px1",
                        "d3vc1o6",
                        "d3vbtry",
                        "d3vb5v0",
                        "d3v7jbe",
                        "d3v8osv",
                        "d3v7hbj",
                        "d3v96or",
                        "d3v7545",
                        "d3vasa0",
                        "d3v912k",
                        "d3v8jap",
                        "d3v8wc4",
                        "d3v9igm",
                        "d3v8vxz",
                        "d3v9m0o",
                        "d3v8g5a",
                        "d3v7ur9",
                        "d3vbphc",
                        "d3v8xj6",
                        "d3vankc",
                        "d3va6kv",
                        "d3vc0yb",
                        "d3v8vyy",
                        "d3v93vi",
                        "d3v9o7k",
                        "d3v8lbf",
                        "d3v8stt",
                        "d3v873n",
                        "d3va0ac",
                        "d3v8rn9",
                        "d3v8o3d",
                        "d3va5fh",
                        "d3vaakg",
                        "d3v8nbb",
                        "d3v85gx",
                        "d3v9ez7",
                        "d3vbh91",
                        "d3v9ixl",
                        "d3v915n",
                        "d3v8dei",
                        "d3v97hd",
                        "d3vabx9",
                        "d3vckdu",
                        "d3v935h",
                        "d3v8toa",
                        "d3v9gld",
                        "d3v8y10",
                        "d3v97wn",
                        "d3v8h1l",
                        "d3v9gzz",
                        "d3va3wy",
                        "d3v8zmg",
                        "d3v8wb3",
                        "d3v840s",
                        "d3v9t9e",
                        "d3vcapn",
                        "d3vbii4",
                        "d3v8x9r",
                        "d3v8prn",
                        "d3v7qlw",
                        "d3vbkhk",
                        "d3v9hfh",
                        "d3vbhq6",
                        "d3va9vk",
                        "d3va5j6",
                        "d3vbi4k",
                        "d3v8bsu",
                        "d3vbupw",
                        "d3vc49o",
                        "d3v8crp",
                        "d3va0fn",
                        "d3v8y4a",
                        "d3va6d7",
                        "d3v929q",
                        "d3vaz7q",
                        "d3va762",
                        "d3vcf07",
                        "d3v6spc",
                        "d3vb55e",
                        "d3v9nf8",
                        "d3v9z9x",
                        "d3v8qn0",
                        "d3v8ul9",
                        "d3v980u",
                        "d3v9y91",
                        "d3v8jis",
                        "d3v4hcy",
                        "d3v8kyj",
                        "d3v8wkp",
                        "d3vbht8",
                        "d3vafhr",
                        "d3vc5wx",
                        "d3vabxy",
                        "d3v8zcn",
                        "d3v92ig",
                        "d3v8gsc"
                      ]
                    }
                  }
                ],
                "after": null,
                "before": null
              }
            },
            "user_reports": [

            ],
            "saved": false,
            "id": "d3v2yc4",
            "gilded": 0,
            "archived": false,
            "report_reasons": null,
            "author": "Exodor",
            "parent_id": "t3_4mfawk",
            "score": 1858,
            "approved_by": null,
            "controversiality": 0,
            "body": "I visited Tucson for a week in the summer of 2003 from my home in Knoxville, TN. While I was there, the afternoon temperatures fluctuated between 106\u00b0-116\u00b0F. And when I was standing in the direct sunlight, it felt every bit of that.\n\nBut when I was in the shade, I found it to be comparatively (and unexpectedly) comfortable. And the house that I stayed in didn't even use the type of air conditioning that I'm used to here in Knoxville...it was basically a fan with a mister, and it kept the house completely comfortable even through the hottest part of the day.\n\nTo me, the difference between a searing dry heat and an oppressive wet heat is huge. I know it's a clich\u00e9 to say so, but it really is the humidity that matters most.\n\nAlso, Tucson was breathtakingly beautiful. God, I love the desert. \n\nEDIT: changed the claimed temperature range due to probable memory error. Apologies.",
            "edited": 1464999125.0,
            "author_flair_css_class": null,
            "downs": 0,
            "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;I visited Tucson for a week in the summer of 2003 from my home in Knoxville, TN. While I was there, the afternoon temperatures fluctuated between 106\u00b0-116\u00b0F. And when I was standing in the direct sunlight, it felt every bit of that.&lt;/p&gt;\n\n&lt;p&gt;But when I was in the shade, I found it to be comparatively (and unexpectedly) comfortable. And the house that I stayed in didn&amp;#39;t even use the type of air conditioning that I&amp;#39;m used to here in Knoxville...it was basically a fan with a mister, and it kept the house completely comfortable even through the hottest part of the day.&lt;/p&gt;\n\n&lt;p&gt;To me, the difference between a searing dry heat and an oppressive wet heat is huge. I know it&amp;#39;s a clich\u00e9 to say so, but it really is the humidity that matters most.&lt;/p&gt;\n\n&lt;p&gt;Also, Tucson was breathtakingly beautiful. God, I love the desert. &lt;/p&gt;\n\n&lt;p&gt;EDIT: changed the claimed temperature range due to probable memory error. Apologies.&lt;/p&gt;\n&lt;/div&gt;",
            "subreddit": "funny",
            "name": "t1_d3v2yc4",
            "score_hidden": false,
            "stickied": false,
            "created": 1465022203.0,
            "author_flair_text": null,
            "created_utc": 1464993403.0,
            "distinguished": null,
            "mod_reports": [

            ],
            "num_reports": null,
            "ups": 1858
          }
        },
        {
          "kind": "t1",
          "data": {
            "subreddit_id": "t5_2qh33",
            "banned_by": null,
            "removal_reason": null,
            "link_id": "t3_4mfawk",
            "likes": null,
            "replies": {
              "kind": "Listing",
              "data": {
                "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                "children": [
                  {
                    "kind": "t1",
                    "data": {
                      "subreddit_id": "t5_2qh33",
                      "banned_by": null,
                      "removal_reason": null,
                      "link_id": "t3_4mfawk",
                      "likes": null,
                      "replies": {
                        "kind": "Listing",
                        "data": {
                          "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                          "children": [
                            {
                              "kind": "t1",
                              "data": {
                                "subreddit_id": "t5_2qh33",
                                "banned_by": null,
                                "removal_reason": null,
                                "link_id": "t3_4mfawk",
                                "likes": null,
                                "replies": {
                                  "kind": "Listing",
                                  "data": {
                                    "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                    "children": [
                                      {
                                        "kind": "t1",
                                        "data": {
                                          "subreddit_id": "t5_2qh33",
                                          "banned_by": null,
                                          "removal_reason": null,
                                          "link_id": "t3_4mfawk",
                                          "likes": null,
                                          "replies": {
                                            "kind": "Listing",
                                            "data": {
                                              "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                              "children": [
                                                {
                                                  "kind": "t1",
                                                  "data": {
                                                    "subreddit_id": "t5_2qh33",
                                                    "banned_by": null,
                                                    "removal_reason": null,
                                                    "link_id": "t3_4mfawk",
                                                    "likes": null,
                                                    "replies": {
                                                      "kind": "Listing",
                                                      "data": {
                                                        "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                                        "children": [
                                                          {
                                                            "kind": "t1",
                                                            "data": {
                                                              "subreddit_id": "t5_2qh33",
                                                              "banned_by": null,
                                                              "removal_reason": null,
                                                              "link_id": "t3_4mfawk",
                                                              "likes": null,
                                                              "replies": {
                                                                "kind": "Listing",
                                                                "data": {
                                                                  "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                                                  "children": [
                                                                    {
                                                                      "kind": "more",
                                                                      "data": {
                                                                        "count": 8,
                                                                        "parent_id": "t1_d3v9hq9",
                                                                        "children": [
                                                                          "d3vc6e8",
                                                                          "d3vbexf",
                                                                          "d3v9jdp"
                                                                        ],
                                                                        "name": "t1_d3vc6e8",
                                                                        "id": "d3vc6e8"
                                                                      }
                                                                    }
                                                                  ],
                                                                  "after": null,
                                                                  "before": null
                                                                }
                                                              },
                                                              "user_reports": [

                                                              ],
                                                              "saved": false,
                                                              "id": "d3v9hq9",
                                                              "gilded": 0,
                                                              "archived": false,
                                                              "report_reasons": null,
                                                              "author": "snotbag_pukebucket",
                                                              "parent_id": "t1_d3v9ef7",
                                                              "score": 28,
                                                              "approved_by": null,
                                                              "controversiality": 0,
                                                              "body": "Where's your moral barometer?",
                                                              "edited": false,
                                                              "author_flair_css_class": null,
                                                              "downs": 0,
                                                              "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Where&amp;#39;s your moral barometer?&lt;/p&gt;\n&lt;/div&gt;",
                                                              "subreddit": "funny",
                                                              "name": "t1_d3v9hq9",
                                                              "score_hidden": false,
                                                              "stickied": false,
                                                              "created": 1465033495.0,
                                                              "author_flair_text": null,
                                                              "created_utc": 1465004695.0,
                                                              "distinguished": null,
                                                              "mod_reports": [

                                                              ],
                                                              "num_reports": null,
                                                              "ups": 28
                                                            }
                                                          },
                                                          {
                                                            "kind": "more",
                                                            "data": {
                                                              "count": 1,
                                                              "parent_id": "t1_d3v9ef7",
                                                              "id": "d3v9mny",
                                                              "name": "t1_d3v9mny",
                                                              "children": [
                                                                "d3v9mny"
                                                              ]
                                                            }
                                                          }
                                                        ],
                                                        "after": null,
                                                        "before": null
                                                      }
                                                    },
                                                    "user_reports": [

                                                    ],
                                                    "saved": false,
                                                    "id": "d3v9ef7",
                                                    "gilded": 0,
                                                    "archived": false,
                                                    "report_reasons": null,
                                                    "author": "zappa325",
                                                    "parent_id": "t1_d3v9att",
                                                    "score": 42,
                                                    "approved_by": null,
                                                    "controversiality": 0,
                                                    "body": "You need a barometer to calculate that!",
                                                    "edited": false,
                                                    "author_flair_css_class": null,
                                                    "downs": 0,
                                                    "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;You need a barometer to calculate that!&lt;/p&gt;\n&lt;/div&gt;",
                                                    "subreddit": "funny",
                                                    "name": "t1_d3v9ef7",
                                                    "score_hidden": false,
                                                    "stickied": false,
                                                    "created": 1465033329.0,
                                                    "author_flair_text": null,
                                                    "created_utc": 1465004529.0,
                                                    "distinguished": null,
                                                    "mod_reports": [

                                                    ],
                                                    "num_reports": null,
                                                    "ups": 42
                                                  }
                                                },
                                                {
                                                  "kind": "more",
                                                  "data": {
                                                    "count": 1,
                                                    "parent_id": "t1_d3v9att",
                                                    "children": [
                                                      "d3va8fl"
                                                    ],
                                                    "name": "t1_d3va8fl",
                                                    "id": "d3va8fl"
                                                  }
                                                }
                                              ],
                                              "after": null,
                                              "before": null
                                            }
                                          },
                                          "user_reports": [

                                          ],
                                          "saved": false,
                                          "id": "d3v9att",
                                          "gilded": 0,
                                          "archived": false,
                                          "report_reasons": null,
                                          "author": "sonny_sailor",
                                          "parent_id": "t1_d3v8zll",
                                          "score": 101,
                                          "approved_by": null,
                                          "controversiality": 0,
                                          "body": "53 % to the thermosphere!!",
                                          "edited": false,
                                          "author_flair_css_class": null,
                                          "downs": 0,
                                          "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;53 % to the thermosphere!!&lt;/p&gt;\n&lt;/div&gt;",
                                          "subreddit": "funny",
                                          "name": "t1_d3v9att",
                                          "score_hidden": false,
                                          "stickied": false,
                                          "created": 1465033160.0,
                                          "author_flair_text": null,
                                          "created_utc": 1465004360.0,
                                          "distinguished": null,
                                          "mod_reports": [

                                          ],
                                          "num_reports": null,
                                          "ups": 101
                                        }
                                      },
                                      {
                                        "kind": "t1",
                                        "data": {
                                          "subreddit_id": "t5_2qh33",
                                          "banned_by": null,
                                          "removal_reason": null,
                                          "link_id": "t3_4mfawk",
                                          "likes": null,
                                          "replies": {
                                            "kind": "Listing",
                                            "data": {
                                              "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                              "children": [
                                                {
                                                  "kind": "more",
                                                  "data": {
                                                    "count": 1,
                                                    "parent_id": "t1_d3v9k38",
                                                    "children": [
                                                      "d3vbj9s"
                                                    ],
                                                    "name": "t1_d3vbj9s",
                                                    "id": "d3vbj9s"
                                                  }
                                                }
                                              ],
                                              "after": null,
                                              "before": null
                                            }
                                          },
                                          "user_reports": [

                                          ],
                                          "saved": false,
                                          "id": "d3v9k38",
                                          "gilded": 0,
                                          "archived": false,
                                          "report_reasons": null,
                                          "author": "Anal_Vacuum",
                                          "parent_id": "t1_d3v8zll",
                                          "score": 30,
                                          "approved_by": null,
                                          "controversiality": 0,
                                          "body": "Summoning /u/TheWallGrows",
                                          "edited": false,
                                          "author_flair_css_class": null,
                                          "downs": 0,
                                          "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Summoning &lt;a href=\"/u/TheWallGrows\"&gt;/u/TheWallGrows&lt;/a&gt;&lt;/p&gt;\n&lt;/div&gt;",
                                          "subreddit": "funny",
                                          "name": "t1_d3v9k38",
                                          "score_hidden": false,
                                          "stickied": false,
                                          "created": 1465033602.0,
                                          "author_flair_text": null,
                                          "created_utc": 1465004802.0,
                                          "distinguished": null,
                                          "mod_reports": [

                                          ],
                                          "num_reports": null,
                                          "ups": 30
                                        }
                                      },
                                      {
                                        "kind": "more",
                                        "data": {
                                          "count": 12,
                                          "parent_id": "t1_d3v8zll",
                                          "id": "d3vad42",
                                          "name": "t1_d3vad42",
                                          "children": [
                                            "d3vad42",
                                            "d3v9plw",
                                            "d3valu1",
                                            "d3vbcln",
                                            "d3vacp0",
                                            "d3v9jfm",
                                            "d3v9sdy",
                                            "d3vc09j",
                                            "d3vb3sp",
                                            "d3v96g4"
                                          ]
                                        }
                                      }
                                    ],
                                    "after": null,
                                    "before": null
                                  }
                                },
                                "user_reports": [

                                ],
                                "saved": false,
                                "id": "d3v8zll",
                                "gilded": 0,
                                "archived": false,
                                "report_reasons": null,
                                "author": "creamyottersoup",
                                "parent_id": "t1_d3v8ky5",
                                "score": 209,
                                "approved_by": null,
                                "controversiality": 0,
                                "body": "Where's the 10feet higher bot when you need it the most. Whats it at right now? Way over Mt. Everest I think.",
                                "edited": false,
                                "author_flair_css_class": null,
                                "downs": 0,
                                "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Where&amp;#39;s the 10feet higher bot when you need it the most. Whats it at right now? Way over Mt. Everest I think.&lt;/p&gt;\n&lt;/div&gt;",
                                "subreddit": "funny",
                                "name": "t1_d3v8zll",
                                "score_hidden": false,
                                "stickied": false,
                                "created": 1465032621.0,
                                "author_flair_text": null,
                                "created_utc": 1465003821.0,
                                "distinguished": null,
                                "mod_reports": [

                                ],
                                "num_reports": null,
                                "ups": 209
                              }
                            },
                            {
                              "kind": "t1",
                              "data": {
                                "subreddit_id": "t5_2qh33",
                                "banned_by": null,
                                "removal_reason": null,
                                "link_id": "t3_4mfawk",
                                "likes": null,
                                "replies": {
                                  "kind": "Listing",
                                  "data": {
                                    "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                    "children": [
                                      {
                                        "kind": "more",
                                        "data": {
                                          "count": 9,
                                          "parent_id": "t1_d3v8z6z",
                                          "id": "d3v97p4",
                                          "name": "t1_d3v97p4",
                                          "children": [
                                            "d3v97p4",
                                            "d3vb4l4",
                                            "d3v947i"
                                          ]
                                        }
                                      }
                                    ],
                                    "after": null,
                                    "before": null
                                  }
                                },
                                "user_reports": [

                                ],
                                "saved": false,
                                "id": "d3v8z6z",
                                "gilded": 0,
                                "archived": false,
                                "report_reasons": null,
                                "author": "Seoul_Surfer",
                                "parent_id": "t1_d3v8ky5",
                                "score": 10,
                                "approved_by": null,
                                "controversiality": 0,
                                "body": "I don't think that's right but I don't really know enough about walls to dispute it.",
                                "edited": false,
                                "author_flair_css_class": null,
                                "downs": 0,
                                "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;I don&amp;#39;t think that&amp;#39;s right but I don&amp;#39;t really know enough about walls to dispute it.&lt;/p&gt;\n&lt;/div&gt;",
                                "subreddit": "funny",
                                "name": "t1_d3v8z6z",
                                "score_hidden": false,
                                "stickied": false,
                                "created": 1465032602.0,
                                "author_flair_text": null,
                                "created_utc": 1465003802.0,
                                "distinguished": null,
                                "mod_reports": [

                                ],
                                "num_reports": null,
                                "ups": 10
                              }
                            },
                            {
                              "kind": "more",
                              "data": {
                                "count": 17,
                                "parent_id": "t1_d3v8ky5",
                                "children": [
                                  "d3vb0oy",
                                  "d3v8xk3",
                                  "d3vcf8k",
                                  "d3vbahx",
                                  "d3v9dft",
                                  "d3vb21e",
                                  "d3vay8f",
                                  "d3vbomp",
                                  "d3vaq5u",
                                  "d3vasaf",
                                  "d3vauc9",
                                  "d3vbrq6"
                                ],
                                "name": "t1_d3vb0oy",
                                "id": "d3vb0oy"
                              }
                            }
                          ],
                          "after": null,
                          "before": null
                        }
                      },
                      "user_reports": [

                      ],
                      "saved": false,
                      "id": "d3v8ky5",
                      "gilded": 0,
                      "archived": false,
                      "report_reasons": null,
                      "author": "Midwestie",
                      "parent_id": "t1_d3v2xbs",
                      "score": 1256,
                      "approved_by": null,
                      "controversiality": 0,
                      "body": "Don't worry. In a few years, the shade from Trumps wall will cool it off.",
                      "edited": false,
                      "author_flair_css_class": null,
                      "downs": 0,
                      "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Don&amp;#39;t worry. In a few years, the shade from Trumps wall will cool it off.&lt;/p&gt;\n&lt;/div&gt;",
                      "subreddit": "funny",
                      "name": "t1_d3v8ky5",
                      "score_hidden": false,
                      "stickied": false,
                      "created": 1465031913.0,
                      "author_flair_text": null,
                      "created_utc": 1465003113.0,
                      "distinguished": null,
                      "mod_reports": [

                      ],
                      "num_reports": null,
                      "ups": 1256
                    }
                  },
                  {
                    "kind": "t1",
                    "data": {
                      "subreddit_id": "t5_2qh33",
                      "banned_by": null,
                      "removal_reason": null,
                      "link_id": "t3_4mfawk",
                      "likes": null,
                      "replies": {
                        "kind": "Listing",
                        "data": {
                          "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                          "children": [
                            {
                              "kind": "t1",
                              "data": {
                                "subreddit_id": "t5_2qh33",
                                "banned_by": null,
                                "removal_reason": null,
                                "link_id": "t3_4mfawk",
                                "likes": null,
                                "replies": {
                                  "kind": "Listing",
                                  "data": {
                                    "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                    "children": [
                                      {
                                        "kind": "t1",
                                        "data": {
                                          "subreddit_id": "t5_2qh33",
                                          "banned_by": null,
                                          "removal_reason": null,
                                          "link_id": "t3_4mfawk",
                                          "likes": null,
                                          "replies": {
                                            "kind": "Listing",
                                            "data": {
                                              "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                              "children": [
                                                {
                                                  "kind": "more",
                                                  "data": {
                                                    "count": 6,
                                                    "parent_id": "t1_d3vasmn",
                                                    "children": [
                                                      "d3vc4bf",
                                                      "d3vayos",
                                                      "d3vc2py"
                                                    ],
                                                    "name": "t1_d3vc4bf",
                                                    "id": "d3vc4bf"
                                                  }
                                                }
                                              ],
                                              "after": null,
                                              "before": null
                                            }
                                          },
                                          "user_reports": [

                                          ],
                                          "saved": false,
                                          "id": "d3vasmn",
                                          "gilded": 0,
                                          "archived": false,
                                          "report_reasons": null,
                                          "author": "AngryAssassin69",
                                          "parent_id": "t1_d3v9p69",
                                          "score": 14,
                                          "approved_by": null,
                                          "controversiality": 0,
                                          "body": "lol I hear ya, it's a balmy 59f in Central City right now also and i've got all the windows open to try and get a breeze going in here.  Fuck heat!",
                                          "edited": false,
                                          "author_flair_css_class": null,
                                          "downs": 0,
                                          "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;lol I hear ya, it&amp;#39;s a balmy 59f in Central City right now also and i&amp;#39;ve got all the windows open to try and get a breeze going in here.  Fuck heat!&lt;/p&gt;\n&lt;/div&gt;",
                                          "subreddit": "funny",
                                          "name": "t1_d3vasmn",
                                          "score_hidden": false,
                                          "stickied": false,
                                          "created": 1465035712.0,
                                          "author_flair_text": null,
                                          "created_utc": 1465006912.0,
                                          "distinguished": null,
                                          "mod_reports": [

                                          ],
                                          "num_reports": null,
                                          "ups": 14
                                        }
                                      },
                                      {
                                        "kind": "t1",
                                        "data": {
                                          "subreddit_id": "t5_2qh33",
                                          "banned_by": null,
                                          "removal_reason": null,
                                          "link_id": "t3_4mfawk",
                                          "likes": null,
                                          "replies": {
                                            "kind": "Listing",
                                            "data": {
                                              "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                              "children": [
                                                {
                                                  "kind": "t1",
                                                  "data": {
                                                    "subreddit_id": "t5_2qh33",
                                                    "banned_by": null,
                                                    "removal_reason": null,
                                                    "link_id": "t3_4mfawk",
                                                    "likes": null,
                                                    "replies": {
                                                      "kind": "Listing",
                                                      "data": {
                                                        "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                                        "children": [
                                                          {
                                                            "kind": "more",
                                                            "data": {
                                                              "count": 1,
                                                              "parent_id": "t1_d3vb0yk",
                                                              "id": "d3vbfno",
                                                              "name": "t1_d3vbfno",
                                                              "children": [
                                                                "d3vbfno"
                                                              ]
                                                            }
                                                          }
                                                        ],
                                                        "after": null,
                                                        "before": null
                                                      }
                                                    },
                                                    "user_reports": [

                                                    ],
                                                    "saved": false,
                                                    "id": "d3vb0yk",
                                                    "gilded": 0,
                                                    "archived": false,
                                                    "report_reasons": null,
                                                    "author": "Sixstringkiing",
                                                    "parent_id": "t1_d3van5p",
                                                    "score": 27,
                                                    "approved_by": null,
                                                    "controversiality": 0,
                                                    "body": "No. I think the correct term is Frisbee Throw Super Fun Time 5000. ",
                                                    "edited": false,
                                                    "author_flair_css_class": null,
                                                    "downs": 0,
                                                    "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;No. I think the correct term is Frisbee Throw Super Fun Time 5000. &lt;/p&gt;\n&lt;/div&gt;",
                                                    "subreddit": "funny",
                                                    "name": "t1_d3vb0yk",
                                                    "score_hidden": false,
                                                    "stickied": false,
                                                    "created": 1465036098.0,
                                                    "author_flair_text": null,
                                                    "created_utc": 1465007298.0,
                                                    "distinguished": null,
                                                    "mod_reports": [

                                                    ],
                                                    "num_reports": null,
                                                    "ups": 27
                                                  }
                                                },
                                                {
                                                  "kind": "more",
                                                  "data": {
                                                    "count": 6,
                                                    "parent_id": "t1_d3van5p",
                                                    "children": [
                                                      "d3vb3fo",
                                                      "d3vb46r",
                                                      "d3vb5l0",
                                                      "d3vbfjr"
                                                    ],
                                                    "name": "t1_d3vb3fo",
                                                    "id": "d3vb3fo"
                                                  }
                                                }
                                              ],
                                              "after": null,
                                              "before": null
                                            }
                                          },
                                          "user_reports": [

                                          ],
                                          "saved": false,
                                          "id": "d3van5p",
                                          "gilded": 0,
                                          "archived": false,
                                          "report_reasons": null,
                                          "author": "Arctis_Tor",
                                          "parent_id": "t1_d3v9p69",
                                          "score": 11,
                                          "approved_by": null,
                                          "controversiality": 0,
                                          "body": "You mean disc golf right?",
                                          "edited": false,
                                          "author_flair_css_class": null,
                                          "downs": 0,
                                          "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;You mean disc golf right?&lt;/p&gt;\n&lt;/div&gt;",
                                          "subreddit": "funny",
                                          "name": "t1_d3van5p",
                                          "score_hidden": false,
                                          "stickied": false,
                                          "created": 1465035468.0,
                                          "author_flair_text": null,
                                          "created_utc": 1465006668.0,
                                          "distinguished": null,
                                          "mod_reports": [

                                          ],
                                          "num_reports": null,
                                          "ups": 11
                                        }
                                      },
                                      {
                                        "kind": "more",
                                        "data": {
                                          "count": 19,
                                          "parent_id": "t1_d3v9p69",
                                          "id": "d3vbykh",
                                          "name": "t1_d3vbykh",
                                          "children": [
                                            "d3vbykh",
                                            "d3vcldn",
                                            "d3vals6",
                                            "d3vbpd2",
                                            "d3vcfu3",
                                            "d3vbmi4",
                                            "d3vb01b",
                                            "d3vc2j5",
                                            "d3vbcns",
                                            "d3vbjs6",
                                            "d3vb0bf",
                                            "d3vbu8s",
                                            "d3vag66"
                                          ]
                                        }
                                      }
                                    ],
                                    "after": null,
                                    "before": null
                                  }
                                },
                                "user_reports": [

                                ],
                                "saved": false,
                                "id": "d3v9p69",
                                "gilded": 0,
                                "archived": false,
                                "report_reasons": null,
                                "author": "Sixstringkiing",
                                "parent_id": "t1_d3v996v",
                                "score": 107,
                                "approved_by": null,
                                "controversiality": 0,
                                "body": "I refused to go play frisbee golf today because I couldnt stand the 75 degree heat here in colorado. ",
                                "edited": false,
                                "author_flair_css_class": null,
                                "downs": 0,
                                "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;I refused to go play frisbee golf today because I couldnt stand the 75 degree heat here in colorado. &lt;/p&gt;\n&lt;/div&gt;",
                                "subreddit": "funny",
                                "name": "t1_d3v9p69",
                                "score_hidden": false,
                                "stickied": false,
                                "created": 1465033853.0,
                                "author_flair_text": null,
                                "created_utc": 1465005053.0,
                                "distinguished": null,
                                "mod_reports": [

                                ],
                                "num_reports": null,
                                "ups": 107
                              }
                            },
                            {
                              "kind": "more",
                              "data": {
                                "count": 10,
                                "parent_id": "t1_d3v996v",
                                "children": [
                                  "d3vasxs",
                                  "d3v9xy9",
                                  "d3vafhe",
                                  "d3va2p0",
                                  "d3vb4qy",
                                  "d3vaaia",
                                  "d3vawr7",
                                  "d3vat11",
                                  "d3vc0r9"
                                ],
                                "name": "t1_d3vasxs",
                                "id": "d3vasxs"
                              }
                            }
                          ],
                          "after": null,
                          "before": null
                        }
                      },
                      "user_reports": [

                      ],
                      "saved": false,
                      "id": "d3v996v",
                      "gilded": 0,
                      "archived": false,
                      "report_reasons": null,
                      "author": "floppy_contortionist",
                      "parent_id": "t1_d3v2xbs",
                      "score": 86,
                      "approved_by": null,
                      "controversiality": 0,
                      "body": "A teeth chattering 112 in havasu (we call anything under 120 \"jacket weather\")",
                      "edited": false,
                      "author_flair_css_class": null,
                      "downs": 0,
                      "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;A teeth chattering 112 in havasu (we call anything under 120 &amp;quot;jacket weather&amp;quot;)&lt;/p&gt;\n&lt;/div&gt;",
                      "subreddit": "funny",
                      "name": "t1_d3v996v",
                      "score_hidden": false,
                      "stickied": false,
                      "created": 1465033077.0,
                      "author_flair_text": null,
                      "created_utc": 1465004277.0,
                      "distinguished": null,
                      "mod_reports": [

                      ],
                      "num_reports": null,
                      "ups": 86
                    }
                  },
                  {
                    "kind": "t1",
                    "data": {
                      "subreddit_id": "t5_2qh33",
                      "banned_by": null,
                      "removal_reason": null,
                      "link_id": "t3_4mfawk",
                      "likes": null,
                      "replies": {
                        "kind": "Listing",
                        "data": {
                          "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                          "children": [
                            {
                              "kind": "t1",
                              "data": {
                                "subreddit_id": "t5_2qh33",
                                "banned_by": null,
                                "removal_reason": null,
                                "link_id": "t3_4mfawk",
                                "likes": null,
                                "replies": {
                                  "kind": "Listing",
                                  "data": {
                                    "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                    "children": [
                                      {
                                        "kind": "t1",
                                        "data": {
                                          "subreddit_id": "t5_2qh33",
                                          "banned_by": null,
                                          "removal_reason": null,
                                          "link_id": "t3_4mfawk",
                                          "likes": null,
                                          "replies": {
                                            "kind": "Listing",
                                            "data": {
                                              "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                              "children": [
                                                {
                                                  "kind": "t1",
                                                  "data": {
                                                    "subreddit_id": "t5_2qh33",
                                                    "banned_by": null,
                                                    "removal_reason": null,
                                                    "link_id": "t3_4mfawk",
                                                    "likes": null,
                                                    "replies": {
                                                      "kind": "Listing",
                                                      "data": {
                                                        "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                                        "children": [
                                                          {
                                                            "kind": "more",
                                                            "data": {
                                                              "count": 3,
                                                              "parent_id": "t1_d3v9umn",
                                                              "id": "d3vav45",
                                                              "name": "t1_d3vav45",
                                                              "children": [
                                                                "d3vav45"
                                                              ]
                                                            }
                                                          }
                                                        ],
                                                        "after": null,
                                                        "before": null
                                                      }
                                                    },
                                                    "user_reports": [

                                                    ],
                                                    "saved": false,
                                                    "id": "d3v9umn",
                                                    "gilded": 0,
                                                    "archived": false,
                                                    "report_reasons": null,
                                                    "author": "IAmNotAnAlcoholic",
                                                    "parent_id": "t1_d3v9srp",
                                                    "score": 32,
                                                    "approved_by": null,
                                                    "controversiality": 0,
                                                    "body": "65 here and my ass is soaked. I need to get in shape.",
                                                    "edited": false,
                                                    "author_flair_css_class": null,
                                                    "downs": 0,
                                                    "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;65 here and my ass is soaked. I need to get in shape.&lt;/p&gt;\n&lt;/div&gt;",
                                                    "subreddit": "funny",
                                                    "name": "t1_d3v9umn",
                                                    "score_hidden": false,
                                                    "stickied": false,
                                                    "created": 1465034119.0,
                                                    "author_flair_text": null,
                                                    "created_utc": 1465005319.0,
                                                    "distinguished": null,
                                                    "mod_reports": [

                                                    ],
                                                    "num_reports": null,
                                                    "ups": 32
                                                  }
                                                },
                                                {
                                                  "kind": "more",
                                                  "data": {
                                                    "count": 9,
                                                    "parent_id": "t1_d3v9srp",
                                                    "children": [
                                                      "d3vao35",
                                                      "d3vbh77",
                                                      "d3vavck",
                                                      "d3vahgt",
                                                      "d3vbdgb"
                                                    ],
                                                    "name": "t1_d3vao35",
                                                    "id": "d3vao35"
                                                  }
                                                }
                                              ],
                                              "after": null,
                                              "before": null
                                            }
                                          },
                                          "user_reports": [

                                          ],
                                          "saved": false,
                                          "id": "d3v9srp",
                                          "gilded": 0,
                                          "archived": false,
                                          "report_reasons": null,
                                          "author": "French_Mustache",
                                          "parent_id": "t1_d3v9kdf",
                                          "score": 140,
                                          "approved_by": null,
                                          "controversiality": 0,
                                          "body": "Swamp ass. Swamp ass all day \ud83d\ude2d",
                                          "edited": false,
                                          "author_flair_css_class": null,
                                          "downs": 0,
                                          "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Swamp ass. Swamp ass all day \ud83d\ude2d&lt;/p&gt;\n&lt;/div&gt;",
                                          "subreddit": "funny",
                                          "name": "t1_d3v9srp",
                                          "score_hidden": false,
                                          "stickied": false,
                                          "created": 1465034029.0,
                                          "author_flair_text": null,
                                          "created_utc": 1465005229.0,
                                          "distinguished": null,
                                          "mod_reports": [

                                          ],
                                          "num_reports": null,
                                          "ups": 140
                                        }
                                      },
                                      {
                                        "kind": "t1",
                                        "data": {
                                          "subreddit_id": "t5_2qh33",
                                          "banned_by": null,
                                          "removal_reason": null,
                                          "link_id": "t3_4mfawk",
                                          "likes": null,
                                          "replies": {
                                            "kind": "Listing",
                                            "data": {
                                              "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                              "children": [
                                                {
                                                  "kind": "more",
                                                  "data": {
                                                    "count": 9,
                                                    "parent_id": "t1_d3va96r",
                                                    "children": [
                                                      "d3varr9"
                                                    ],
                                                    "name": "t1_d3varr9",
                                                    "id": "d3varr9"
                                                  }
                                                }
                                              ],
                                              "after": null,
                                              "before": null
                                            }
                                          },
                                          "user_reports": [

                                          ],
                                          "saved": false,
                                          "id": "d3va96r",
                                          "gilded": 0,
                                          "archived": false,
                                          "report_reasons": null,
                                          "author": "bcsw222",
                                          "parent_id": "t1_d3v9kdf",
                                          "score": 41,
                                          "approved_by": null,
                                          "controversiality": 0,
                                          "body": "I'll take 115 in a dry climate over 95 in a humid one. Holy shit Florida is brutal. ",
                                          "edited": false,
                                          "author_flair_css_class": null,
                                          "downs": 0,
                                          "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;I&amp;#39;ll take 115 in a dry climate over 95 in a humid one. Holy shit Florida is brutal. &lt;/p&gt;\n&lt;/div&gt;",
                                          "subreddit": "funny",
                                          "name": "t1_d3va96r",
                                          "score_hidden": false,
                                          "stickied": false,
                                          "created": 1465034817.0,
                                          "author_flair_text": null,
                                          "created_utc": 1465006017.0,
                                          "distinguished": null,
                                          "mod_reports": [

                                          ],
                                          "num_reports": null,
                                          "ups": 41
                                        }
                                      },
                                      {
                                        "kind": "t1",
                                        "data": {
                                          "subreddit_id": "t5_2qh33",
                                          "banned_by": null,
                                          "removal_reason": null,
                                          "link_id": "t3_4mfawk",
                                          "likes": null,
                                          "replies": {
                                            "kind": "Listing",
                                            "data": {
                                              "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                              "children": [
                                                {
                                                  "kind": "more",
                                                  "data": {
                                                    "count": 7,
                                                    "parent_id": "t1_d3vae1m",
                                                    "children": [
                                                      "d3vca43",
                                                      "d3vbm0z",
                                                      "d3vcc11",
                                                      "d3vb56j",
                                                      "d3vbv5x"
                                                    ],
                                                    "name": "t1_d3vca43",
                                                    "id": "d3vca43"
                                                  }
                                                }
                                              ],
                                              "after": null,
                                              "before": null
                                            }
                                          },
                                          "user_reports": [

                                          ],
                                          "saved": false,
                                          "id": "d3vae1m",
                                          "gilded": 0,
                                          "archived": false,
                                          "report_reasons": null,
                                          "author": "silly_psycho",
                                          "parent_id": "t1_d3v9kdf",
                                          "score": 23,
                                          "approved_by": null,
                                          "controversiality": 0,
                                          "body": "No man. I don't think it ever gets over 30% where I'm at in Califonia, ever. I imagine Florida/Georgia and my whole body resonates \"No\".",
                                          "edited": false,
                                          "author_flair_css_class": null,
                                          "downs": 0,
                                          "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;No man. I don&amp;#39;t think it ever gets over 30% where I&amp;#39;m at in Califonia, ever. I imagine Florida/Georgia and my whole body resonates &amp;quot;No&amp;quot;.&lt;/p&gt;\n&lt;/div&gt;",
                                          "subreddit": "funny",
                                          "name": "t1_d3vae1m",
                                          "score_hidden": false,
                                          "stickied": false,
                                          "created": 1465035038.0,
                                          "author_flair_text": null,
                                          "created_utc": 1465006238.0,
                                          "distinguished": null,
                                          "mod_reports": [

                                          ],
                                          "num_reports": null,
                                          "ups": 23
                                        }
                                      },
                                      {
                                        "kind": "more",
                                        "data": {
                                          "count": 12,
                                          "parent_id": "t1_d3v9kdf",
                                          "id": "d3vbxa8",
                                          "name": "t1_d3vbxa8",
                                          "children": [
                                            "d3vbxa8",
                                            "d3vbtuj",
                                            "d3vb3xi",
                                            "d3val0m",
                                            "d3vbk2w",
                                            "d3vcck3",
                                            "d3v9y5y",
                                            "d3vb7qv",
                                            "d3vblwb",
                                            "d3vb9i7"
                                          ]
                                        }
                                      }
                                    ],
                                    "after": null,
                                    "before": null
                                  }
                                },
                                "user_reports": [

                                ],
                                "saved": false,
                                "id": "d3v9kdf",
                                "gilded": 0,
                                "archived": false,
                                "report_reasons": null,
                                "author": "danpac",
                                "parent_id": "t1_d3v988x",
                                "score": 161,
                                "approved_by": null,
                                "controversiality": 0,
                                "body": "No humidity makes it more manageable come to Florida and you feel like you are in a oven/sauna combo",
                                "edited": false,
                                "author_flair_css_class": null,
                                "downs": 0,
                                "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;No humidity makes it more manageable come to Florida and you feel like you are in a oven/sauna combo&lt;/p&gt;\n&lt;/div&gt;",
                                "subreddit": "funny",
                                "name": "t1_d3v9kdf",
                                "score_hidden": false,
                                "stickied": false,
                                "created": 1465033616.0,
                                "author_flair_text": null,
                                "created_utc": 1465004816.0,
                                "distinguished": null,
                                "mod_reports": [

                                ],
                                "num_reports": null,
                                "ups": 161
                              }
                            },
                            {
                              "kind": "more",
                              "data": {
                                "count": 21,
                                "parent_id": "t1_d3v988x",
                                "children": [
                                  "d3vba9t",
                                  "d3vaj7n",
                                  "d3vcbnp",
                                  "d3v9zxj",
                                  "d3vc8co",
                                  "d3vah9p",
                                  "d3v9kkv",
                                  "d3vbtf4",
                                  "d3vbi43",
                                  "d3va2es",
                                  "d3vbxg5",
                                  "d3vclw7",
                                  "d3vcku5",
                                  "d3v9rgf",
                                  "d3vbfnp",
                                  "d3vag71",
                                  "d3v9bku",
                                  "d3vc3r9"
                                ],
                                "name": "t1_d3vba9t",
                                "id": "d3vba9t"
                              }
                            }
                          ],
                          "after": null,
                          "before": null
                        }
                      },
                      "user_reports": [

                      ],
                      "saved": false,
                      "id": "d3v988x",
                      "gilded": 0,
                      "archived": false,
                      "report_reasons": null,
                      "author": "ChowMeinKGo",
                      "parent_id": "t1_d3v2xbs",
                      "score": 59,
                      "approved_by": null,
                      "controversiality": 0,
                      "body": "It was 115 in south phoenix and I was sunbathing for 2 hours and hardly felt the heat. Remarkable how bodies can get accustomed to a temperature. ",
                      "edited": false,
                      "author_flair_css_class": null,
                      "downs": 0,
                      "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;It was 115 in south phoenix and I was sunbathing for 2 hours and hardly felt the heat. Remarkable how bodies can get accustomed to a temperature. &lt;/p&gt;\n&lt;/div&gt;",
                      "subreddit": "funny",
                      "name": "t1_d3v988x",
                      "score_hidden": false,
                      "stickied": false,
                      "created": 1465033032.0,
                      "author_flair_text": null,
                      "created_utc": 1465004232.0,
                      "distinguished": null,
                      "mod_reports": [

                      ],
                      "num_reports": null,
                      "ups": 59
                    }
                  },
                  {
                    "kind": "more",
                    "data": {
                      "count": 56,
                      "parent_id": "t1_d3v2xbs",
                      "id": "d3v9tz8",
                      "name": "t1_d3v9tz8",
                      "children": [
                        "d3v9tz8",
                        "d3vbo5m",
                        "d3vbamm",
                        "d3vc2z3",
                        "d3v9xu8",
                        "d3vcetv",
                        "d3vbcwn",
                        "d3vam8q",
                        "d3v95a5",
                        "d3vb19a",
                        "d3v9pr4",
                        "d3vciw1",
                        "d3va84i",
                        "d3vaof8",
                        "d3v9mwb",
                        "d3vboz1",
                        "d3vc065",
                        "d3vaaib",
                        "d3vamgk",
                        "d3vccad",
                        "d3vbjqu",
                        "d3vaq4f",
                        "d3va4w5",
                        "d3v99za",
                        "d3vacrl",
                        "d3v9lxk",
                        "d3vbo7f",
                        "d3v9g3z",
                        "d3vb8tf",
                        "d3vb6c8",
                        "d3v9f4r",
                        "d3v9rkt",
                        "d3v9x0e",
                        "d3vapun",
                        "d3v9xwg",
                        "d3va6eb",
                        "d3vbox0",
                        "d3v8naf",
                        "d3vagrz",
                        "d3v9lv4",
                        "d3v99bm",
                        "d3vaxh0",
                        "d3vajnf",
                        "d3v9ngd"
                      ]
                    }
                  }
                ],
                "after": null,
                "before": null
              }
            },
            "user_reports": [

            ],
            "saved": false,
            "id": "d3v2xbs",
            "gilded": 0,
            "archived": false,
            "report_reasons": null,
            "author": "lurch350z",
            "parent_id": "t3_4mfawk",
            "score": 1317,
            "approved_by": null,
            "controversiality": 0,
            "body": "It's a chilly 110 down here in Tucson. ",
            "edited": false,
            "author_flair_css_class": null,
            "downs": 0,
            "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;It&amp;#39;s a chilly 110 down here in Tucson. &lt;/p&gt;\n&lt;/div&gt;",
            "subreddit": "funny",
            "name": "t1_d3v2xbs",
            "score_hidden": false,
            "stickied": false,
            "created": 1465022155.0,
            "author_flair_text": null,
            "created_utc": 1464993355.0,
            "distinguished": null,
            "mod_reports": [

            ],
            "num_reports": null,
            "ups": 1317
          }
        },
        {
          "kind": "t1",
          "data": {
            "subreddit_id": "t5_2qh33",
            "banned_by": null,
            "removal_reason": null,
            "link_id": "t3_4mfawk",
            "likes": null,
            "replies": {
              "kind": "Listing",
              "data": {
                "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                "children": [
                  {
                    "kind": "t1",
                    "data": {
                      "subreddit_id": "t5_2qh33",
                      "banned_by": null,
                      "removal_reason": null,
                      "link_id": "t3_4mfawk",
                      "likes": null,
                      "replies": {
                        "kind": "Listing",
                        "data": {
                          "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                          "children": [
                            {
                              "kind": "t1",
                              "data": {
                                "subreddit_id": "t5_2qh33",
                                "banned_by": null,
                                "removal_reason": null,
                                "link_id": "t3_4mfawk",
                                "likes": null,
                                "replies": {
                                  "kind": "Listing",
                                  "data": {
                                    "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                    "children": [
                                      {
                                        "kind": "t1",
                                        "data": {
                                          "subreddit_id": "t5_2qh33",
                                          "banned_by": null,
                                          "removal_reason": null,
                                          "link_id": "t3_4mfawk",
                                          "likes": null,
                                          "replies": {
                                            "kind": "Listing",
                                            "data": {
                                              "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                              "children": [
                                                {
                                                  "kind": "t1",
                                                  "data": {
                                                    "subreddit_id": "t5_2qh33",
                                                    "banned_by": null,
                                                    "removal_reason": null,
                                                    "link_id": "t3_4mfawk",
                                                    "likes": null,
                                                    "replies": {
                                                      "kind": "Listing",
                                                      "data": {
                                                        "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                                        "children": [
                                                          {
                                                            "kind": "more",
                                                            "data": {
                                                              "count": 3,
                                                              "parent_id": "t1_d3vagrw",
                                                              "id": "d3vbqg0",
                                                              "name": "t1_d3vbqg0",
                                                              "children": [
                                                                "d3vbqg0",
                                                                "d3vbmx6",
                                                                "d3vbya2"
                                                              ]
                                                            }
                                                          }
                                                        ],
                                                        "after": null,
                                                        "before": null
                                                      }
                                                    },
                                                    "user_reports": [

                                                    ],
                                                    "saved": false,
                                                    "id": "d3vagrw",
                                                    "gilded": 0,
                                                    "archived": false,
                                                    "report_reasons": null,
                                                    "author": "tacoafficionado",
                                                    "parent_id": "t1_d3v9hgf",
                                                    "score": 41,
                                                    "approved_by": null,
                                                    "controversiality": 0,
                                                    "body": "having beads of sweat roll down my legs was something I had never experienced until I moved to Houston.",
                                                    "edited": false,
                                                    "author_flair_css_class": null,
                                                    "downs": 0,
                                                    "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;having beads of sweat roll down my legs was something I had never experienced until I moved to Houston.&lt;/p&gt;\n&lt;/div&gt;",
                                                    "subreddit": "funny",
                                                    "name": "t1_d3vagrw",
                                                    "score_hidden": false,
                                                    "stickied": false,
                                                    "created": 1465035164.0,
                                                    "author_flair_text": null,
                                                    "created_utc": 1465006364.0,
                                                    "distinguished": null,
                                                    "mod_reports": [

                                                    ],
                                                    "num_reports": null,
                                                    "ups": 41
                                                  }
                                                },
                                                {
                                                  "kind": "t1",
                                                  "data": {
                                                    "subreddit_id": "t5_2qh33",
                                                    "banned_by": null,
                                                    "removal_reason": null,
                                                    "link_id": "t3_4mfawk",
                                                    "likes": null,
                                                    "replies": {
                                                      "kind": "Listing",
                                                      "data": {
                                                        "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                                        "children": [
                                                          {
                                                            "kind": "more",
                                                            "data": {
                                                              "count": 2,
                                                              "parent_id": "t1_d3v9srn",
                                                              "id": "d3vb9g9",
                                                              "name": "t1_d3vb9g9",
                                                              "children": [
                                                                "d3vb9g9"
                                                              ]
                                                            }
                                                          }
                                                        ],
                                                        "after": null,
                                                        "before": null
                                                      }
                                                    },
                                                    "user_reports": [

                                                    ],
                                                    "saved": false,
                                                    "id": "d3v9srn",
                                                    "gilded": 0,
                                                    "archived": false,
                                                    "report_reasons": null,
                                                    "author": "RichardMcNixon",
                                                    "parent_id": "t1_d3v9hgf",
                                                    "score": 10,
                                                    "approved_by": null,
                                                    "controversiality": 0,
                                                    "body": "haha Phoenix here, was in Nebraska recently for a funeral.  It was 30-50 degrees during my visit and that was all fine for i had a coat.  Inside, however, people would have their heaters on and houses at a reasonable temperature.  The humidity, even in the cold, was such that i was sweating my balls of in every house i went to.\n\nIf i'm going to live in a climate like that again, it better fucking have an ocean attached. ",
                                                    "edited": false,
                                                    "author_flair_css_class": null,
                                                    "downs": 0,
                                                    "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;haha Phoenix here, was in Nebraska recently for a funeral.  It was 30-50 degrees during my visit and that was all fine for i had a coat.  Inside, however, people would have their heaters on and houses at a reasonable temperature.  The humidity, even in the cold, was such that i was sweating my balls of in every house i went to.&lt;/p&gt;\n\n&lt;p&gt;If i&amp;#39;m going to live in a climate like that again, it better fucking have an ocean attached. &lt;/p&gt;\n&lt;/div&gt;",
                                                    "subreddit": "funny",
                                                    "name": "t1_d3v9srn",
                                                    "score_hidden": false,
                                                    "stickied": false,
                                                    "created": 1465034029.0,
                                                    "author_flair_text": null,
                                                    "created_utc": 1465005229.0,
                                                    "distinguished": null,
                                                    "mod_reports": [

                                                    ],
                                                    "num_reports": null,
                                                    "ups": 10
                                                  }
                                                },
                                                {
                                                  "kind": "more",
                                                  "data": {
                                                    "count": 5,
                                                    "parent_id": "t1_d3v9hgf",
                                                    "children": [
                                                      "d3vbzwx",
                                                      "d3vbrc4",
                                                      "d3vc464",
                                                      "d3vbnrr"
                                                    ],
                                                    "name": "t1_d3vbzwx",
                                                    "id": "d3vbzwx"
                                                  }
                                                }
                                              ],
                                              "after": null,
                                              "before": null
                                            }
                                          },
                                          "user_reports": [

                                          ],
                                          "saved": false,
                                          "id": "d3v9hgf",
                                          "gilded": 0,
                                          "archived": false,
                                          "report_reasons": null,
                                          "author": "ILikeMasterChief",
                                          "parent_id": "t1_d3v9bhz",
                                          "score": 111,
                                          "approved_by": null,
                                          "controversiality": 0,
                                          "body": "I once told a friend from AZ about how you literally sweat just walking to your car when it's 85+ with 60%+ humidity. He went on and on about how 85 is comfortable. He didn't believe it until he experienced it himself. ",
                                          "edited": false,
                                          "author_flair_css_class": null,
                                          "downs": 0,
                                          "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;I once told a friend from AZ about how you literally sweat just walking to your car when it&amp;#39;s 85+ with 60%+ humidity. He went on and on about how 85 is comfortable. He didn&amp;#39;t believe it until he experienced it himself. &lt;/p&gt;\n&lt;/div&gt;",
                                          "subreddit": "funny",
                                          "name": "t1_d3v9hgf",
                                          "score_hidden": false,
                                          "stickied": false,
                                          "created": 1465033481.0,
                                          "author_flair_text": null,
                                          "created_utc": 1465004681.0,
                                          "distinguished": null,
                                          "mod_reports": [

                                          ],
                                          "num_reports": null,
                                          "ups": 111
                                        }
                                      },
                                      {
                                        "kind": "t1",
                                        "data": {
                                          "subreddit_id": "t5_2qh33",
                                          "banned_by": null,
                                          "removal_reason": null,
                                          "link_id": "t3_4mfawk",
                                          "likes": null,
                                          "replies": {
                                            "kind": "Listing",
                                            "data": {
                                              "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                              "children": [
                                                {
                                                  "kind": "t1",
                                                  "data": {
                                                    "subreddit_id": "t5_2qh33",
                                                    "banned_by": null,
                                                    "removal_reason": null,
                                                    "link_id": "t3_4mfawk",
                                                    "likes": null,
                                                    "replies": {
                                                      "kind": "Listing",
                                                      "data": {
                                                        "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                                        "children": [
                                                          {
                                                            "kind": "more",
                                                            "data": {
                                                              "count": 1,
                                                              "parent_id": "t1_d3vax47",
                                                              "id": "d3vc1jw",
                                                              "name": "t1_d3vc1jw",
                                                              "children": [
                                                                "d3vc1jw"
                                                              ]
                                                            }
                                                          }
                                                        ],
                                                        "after": null,
                                                        "before": null
                                                      }
                                                    },
                                                    "user_reports": [

                                                    ],
                                                    "saved": false,
                                                    "id": "d3vax47",
                                                    "gilded": 0,
                                                    "archived": false,
                                                    "report_reasons": null,
                                                    "author": "Skankintoopiv",
                                                    "parent_id": "t1_d3v9w5q",
                                                    "score": 29,
                                                    "approved_by": null,
                                                    "controversiality": 0,
                                                    "body": "Not in Florida. Where weather *outside* of what he described only lasts 4-5 days.",
                                                    "edited": false,
                                                    "author_flair_css_class": null,
                                                    "downs": 0,
                                                    "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Not in Florida. Where weather &lt;em&gt;outside&lt;/em&gt; of what he described only lasts 4-5 days.&lt;/p&gt;\n&lt;/div&gt;",
                                                    "subreddit": "funny",
                                                    "name": "t1_d3vax47",
                                                    "score_hidden": false,
                                                    "stickied": false,
                                                    "created": 1465035914.0,
                                                    "author_flair_text": null,
                                                    "created_utc": 1465007114.0,
                                                    "distinguished": null,
                                                    "mod_reports": [

                                                    ],
                                                    "num_reports": null,
                                                    "ups": 29
                                                  }
                                                },
                                                {
                                                  "kind": "more",
                                                  "data": {
                                                    "count": 3,
                                                    "parent_id": "t1_d3v9w5q",
                                                    "children": [
                                                      "d3vb0u0",
                                                      "d3vbpyz",
                                                      "d3vazd7"
                                                    ],
                                                    "name": "t1_d3vb0u0",
                                                    "id": "d3vb0u0"
                                                  }
                                                }
                                              ],
                                              "after": null,
                                              "before": null
                                            }
                                          },
                                          "user_reports": [

                                          ],
                                          "saved": false,
                                          "id": "d3v9w5q",
                                          "gilded": 0,
                                          "archived": false,
                                          "report_reasons": null,
                                          "author": "birdnerd",
                                          "parent_id": "t1_d3v9bhz",
                                          "score": 94,
                                          "approved_by": null,
                                          "controversiality": 0,
                                          "body": "Yes, but it only lasts for 4-5 months.\n\nI like to describe Summer in Houston as a series of mad dashes between air conditioned locations.",
                                          "edited": false,
                                          "author_flair_css_class": null,
                                          "downs": 0,
                                          "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Yes, but it only lasts for 4-5 months.&lt;/p&gt;\n\n&lt;p&gt;I like to describe Summer in Houston as a series of mad dashes between air conditioned locations.&lt;/p&gt;\n&lt;/div&gt;",
                                          "subreddit": "funny",
                                          "name": "t1_d3v9w5q",
                                          "score_hidden": false,
                                          "stickied": false,
                                          "created": 1465034195.0,
                                          "author_flair_text": null,
                                          "created_utc": 1465005395.0,
                                          "distinguished": null,
                                          "mod_reports": [

                                          ],
                                          "num_reports": null,
                                          "ups": 94
                                        }
                                      },
                                      {
                                        "kind": "t1",
                                        "data": {
                                          "subreddit_id": "t5_2qh33",
                                          "banned_by": null,
                                          "removal_reason": null,
                                          "link_id": "t3_4mfawk",
                                          "likes": null,
                                          "replies": {
                                            "kind": "Listing",
                                            "data": {
                                              "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                              "children": [
                                                {
                                                  "kind": "more",
                                                  "data": {
                                                    "count": 1,
                                                    "parent_id": "t1_d3vaczm",
                                                    "children": [
                                                      "d3vcihi"
                                                    ],
                                                    "name": "t1_d3vcihi",
                                                    "id": "d3vcihi"
                                                  }
                                                }
                                              ],
                                              "after": null,
                                              "before": null
                                            }
                                          },
                                          "user_reports": [

                                          ],
                                          "saved": false,
                                          "id": "d3vaczm",
                                          "gilded": 0,
                                          "archived": false,
                                          "report_reasons": null,
                                          "author": "LaziestManAlive",
                                          "parent_id": "t1_d3v9bhz",
                                          "score": 14,
                                          "approved_by": null,
                                          "controversiality": 0,
                                          "body": "I disagree. Having experienced plenty of both, dry triple-digit heat is far worse to me. I was hiking in northern Israel two summers ago in 90 F weather with 90% humidity and while it was very uncomfortable, I would take that over stepping out into 110+ degree heat. People think that type of heat is more tolerable because you can get in shade or get quickly to AC. Try walking home from school in it and you'll change your mind. ",
                                          "edited": false,
                                          "author_flair_css_class": null,
                                          "downs": 0,
                                          "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;I disagree. Having experienced plenty of both, dry triple-digit heat is far worse to me. I was hiking in northern Israel two summers ago in 90 F weather with 90% humidity and while it was very uncomfortable, I would take that over stepping out into 110+ degree heat. People think that type of heat is more tolerable because you can get in shade or get quickly to AC. Try walking home from school in it and you&amp;#39;ll change your mind. &lt;/p&gt;\n&lt;/div&gt;",
                                          "subreddit": "funny",
                                          "name": "t1_d3vaczm",
                                          "score_hidden": false,
                                          "stickied": false,
                                          "created": 1465034987.0,
                                          "author_flair_text": null,
                                          "created_utc": 1465006187.0,
                                          "distinguished": null,
                                          "mod_reports": [

                                          ],
                                          "num_reports": null,
                                          "ups": 14
                                        }
                                      },
                                      {
                                        "kind": "more",
                                        "data": {
                                          "count": 39,
                                          "parent_id": "t1_d3v9bhz",
                                          "id": "d3vb0cg",
                                          "name": "t1_d3vb0cg",
                                          "children": [
                                            "d3vb0cg",
                                            "d3vbp06",
                                            "d3v9xxm",
                                            "d3vbvny",
                                            "d3vav4h",
                                            "d3va0wq",
                                            "d3vb2ci",
                                            "d3vapb8",
                                            "d3vbcbq",
                                            "d3vaf8n",
                                            "d3vc7qg",
                                            "d3v9w6l",
                                            "d3vavxs",
                                            "d3vb73o",
                                            "d3vbgs9",
                                            "d3vbxks",
                                            "d3vbwju",
                                            "d3vbm0y",
                                            "d3vb2a0",
                                            "d3vb1p2",
                                            "d3vavkn",
                                            "d3vbbh4",
                                            "d3vc3ms",
                                            "d3vblno",
                                            "d3vbnkh",
                                            "d3v9pii",
                                            "d3v9qzr",
                                            "d3vc48e",
                                            "d3vcji5",
                                            "d3vam43",
                                            "d3vb05b"
                                          ]
                                        }
                                      }
                                    ],
                                    "after": null,
                                    "before": null
                                  }
                                },
                                "user_reports": [

                                ],
                                "saved": false,
                                "id": "d3v9bhz",
                                "gilded": 0,
                                "archived": false,
                                "report_reasons": null,
                                "author": "alailsifio",
                                "parent_id": "t1_d3v8uue",
                                "score": 471,
                                "approved_by": null,
                                "controversiality": 0,
                                "body": "Honestly, the dry heat of Arizona is not that bad. It's super hot and not something I would *want* to experience, but it's better than the muggy cesspool that is Texas and other areas of the Gulf Coast during the summer.\n\nOh, only 88 degrees today? That's not bad at all. Hell, that's *comfortable*. But then you realize it's 95% humidity and the \"feel-like\" temperature is 95+. You walk outside and immediately feel your shirt stick to your back. Sweat beads on your forehead before you even get to your car. The air is so heavy it physically feels hard to breathe. Then the mosquitoes. Dear god...the mosquitoes. They swarm. Cicadas are so loud you can't hear yourself think. \n\nCoastal Texas is hell during the summer. Absolute hell. I'd take 110 degrees of dry heat over 90 degrees of wet, humid, SCUBA-necessary Houston summer.",
                                "edited": false,
                                "author_flair_css_class": null,
                                "downs": 0,
                                "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Honestly, the dry heat of Arizona is not that bad. It&amp;#39;s super hot and not something I would &lt;em&gt;want&lt;/em&gt; to experience, but it&amp;#39;s better than the muggy cesspool that is Texas and other areas of the Gulf Coast during the summer.&lt;/p&gt;\n\n&lt;p&gt;Oh, only 88 degrees today? That&amp;#39;s not bad at all. Hell, that&amp;#39;s &lt;em&gt;comfortable&lt;/em&gt;. But then you realize it&amp;#39;s 95% humidity and the &amp;quot;feel-like&amp;quot; temperature is 95+. You walk outside and immediately feel your shirt stick to your back. Sweat beads on your forehead before you even get to your car. The air is so heavy it physically feels hard to breathe. Then the mosquitoes. Dear god...the mosquitoes. They swarm. Cicadas are so loud you can&amp;#39;t hear yourself think. &lt;/p&gt;\n\n&lt;p&gt;Coastal Texas is hell during the summer. Absolute hell. I&amp;#39;d take 110 degrees of dry heat over 90 degrees of wet, humid, SCUBA-necessary Houston summer.&lt;/p&gt;\n&lt;/div&gt;",
                                "subreddit": "funny",
                                "name": "t1_d3v9bhz",
                                "score_hidden": false,
                                "stickied": false,
                                "created": 1465033193.0,
                                "author_flair_text": null,
                                "created_utc": 1465004393.0,
                                "distinguished": null,
                                "mod_reports": [

                                ],
                                "num_reports": null,
                                "ups": 471
                              }
                            },
                            {
                              "kind": "more",
                              "data": {
                                "count": 11,
                                "parent_id": "t1_d3v8uue",
                                "children": [
                                  "d3v9igx",
                                  "d3vc58k",
                                  "d3vc885",
                                  "d3vcmdo",
                                  "d3vamsu",
                                  "d3vb6wf",
                                  "d3vbftc",
                                  "d3vakd3"
                                ],
                                "name": "t1_d3v9igx",
                                "id": "d3v9igx"
                              }
                            }
                          ],
                          "after": null,
                          "before": null
                        }
                      },
                      "user_reports": [

                      ],
                      "saved": false,
                      "id": "d3v8uue",
                      "gilded": 0,
                      "archived": false,
                      "report_reasons": null,
                      "author": "Randdist",
                      "parent_id": "t1_d3v3inc",
                      "score": 342,
                      "approved_by": null,
                      "controversiality": 0,
                      "body": "I refuse to live anywhere close to an area where this is possible.",
                      "edited": false,
                      "author_flair_css_class": null,
                      "downs": 0,
                      "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;I refuse to live anywhere close to an area where this is possible.&lt;/p&gt;\n&lt;/div&gt;",
                      "subreddit": "funny",
                      "name": "t1_d3v8uue",
                      "score_hidden": false,
                      "stickied": false,
                      "created": 1465032390.0,
                      "author_flair_text": null,
                      "created_utc": 1465003590.0,
                      "distinguished": null,
                      "mod_reports": [

                      ],
                      "num_reports": null,
                      "ups": 342
                    }
                  },
                  {
                    "kind": "t1",
                    "data": {
                      "subreddit_id": "t5_2qh33",
                      "banned_by": null,
                      "removal_reason": null,
                      "link_id": "t3_4mfawk",
                      "likes": null,
                      "replies": {
                        "kind": "Listing",
                        "data": {
                          "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                          "children": [
                            {
                              "kind": "t1",
                              "data": {
                                "subreddit_id": "t5_2qh33",
                                "banned_by": null,
                                "removal_reason": null,
                                "link_id": "t3_4mfawk",
                                "likes": null,
                                "replies": {
                                  "kind": "Listing",
                                  "data": {
                                    "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                    "children": [
                                      {
                                        "kind": "more",
                                        "data": {
                                          "count": 4,
                                          "parent_id": "t1_d3v9ble",
                                          "id": "d3v9zoh",
                                          "name": "t1_d3v9zoh",
                                          "children": [
                                            "d3v9zoh",
                                            "d3vc9sx"
                                          ]
                                        }
                                      }
                                    ],
                                    "after": null,
                                    "before": null
                                  }
                                },
                                "user_reports": [

                                ],
                                "saved": false,
                                "id": "d3v9ble",
                                "gilded": 0,
                                "archived": false,
                                "report_reasons": null,
                                "author": "disoriented",
                                "parent_id": "t1_d3v947n",
                                "score": 25,
                                "approved_by": null,
                                "controversiality": 0,
                                "body": "They're called joggers. Look into them. They're skinny but feel like sweatpants. Trust me",
                                "edited": false,
                                "author_flair_css_class": null,
                                "downs": 0,
                                "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;They&amp;#39;re called joggers. Look into them. They&amp;#39;re skinny but feel like sweatpants. Trust me&lt;/p&gt;\n&lt;/div&gt;",
                                "subreddit": "funny",
                                "name": "t1_d3v9ble",
                                "score_hidden": false,
                                "stickied": false,
                                "created": 1465033198.0,
                                "author_flair_text": null,
                                "created_utc": 1465004398.0,
                                "distinguished": null,
                                "mod_reports": [

                                ],
                                "num_reports": null,
                                "ups": 25
                              }
                            },
                            {
                              "kind": "more",
                              "data": {
                                "count": 2,
                                "parent_id": "t1_d3v947n",
                                "children": [
                                  "d3vas4y",
                                  "d3v99tq"
                                ],
                                "name": "t1_d3vas4y",
                                "id": "d3vas4y"
                              }
                            }
                          ],
                          "after": null,
                          "before": null
                        }
                      },
                      "user_reports": [

                      ],
                      "saved": false,
                      "id": "d3v947n",
                      "gilded": 0,
                      "archived": false,
                      "report_reasons": null,
                      "author": "noshoemolamola",
                      "parent_id": "t1_d3v3inc",
                      "score": 27,
                      "approved_by": null,
                      "controversiality": 0,
                      "body": "dear god why is he wearing skin tight jeans this makes me so uncomfortable",
                      "edited": false,
                      "author_flair_css_class": null,
                      "downs": 0,
                      "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;dear god why is he wearing skin tight jeans this makes me so uncomfortable&lt;/p&gt;\n&lt;/div&gt;",
                      "subreddit": "funny",
                      "name": "t1_d3v947n",
                      "score_hidden": false,
                      "stickied": false,
                      "created": 1465032844.0,
                      "author_flair_text": null,
                      "created_utc": 1465004044.0,
                      "distinguished": null,
                      "mod_reports": [

                      ],
                      "num_reports": null,
                      "ups": 27
                    }
                  },
                  {
                    "kind": "t1",
                    "data": {
                      "subreddit_id": "t5_2qh33",
                      "banned_by": null,
                      "removal_reason": null,
                      "link_id": "t3_4mfawk",
                      "likes": null,
                      "replies": {
                        "kind": "Listing",
                        "data": {
                          "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                          "children": [
                            {
                              "kind": "more",
                              "data": {
                                "count": 1,
                                "parent_id": "t1_d3v8yv8",
                                "children": [
                                  "d3va9un"
                                ],
                                "name": "t1_d3va9un",
                                "id": "d3va9un"
                              }
                            }
                          ],
                          "after": null,
                          "before": null
                        }
                      },
                      "user_reports": [

                      ],
                      "saved": false,
                      "id": "d3v8yv8",
                      "gilded": 0,
                      "archived": false,
                      "report_reasons": null,
                      "author": "ThousandYearsWide",
                      "parent_id": "t1_d3v3inc",
                      "score": 26,
                      "approved_by": null,
                      "controversiality": 0,
                      "body": "Great. Now I'm hungry.",
                      "edited": false,
                      "author_flair_css_class": null,
                      "downs": 0,
                      "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Great. Now I&amp;#39;m hungry.&lt;/p&gt;\n&lt;/div&gt;",
                      "subreddit": "funny",
                      "name": "t1_d3v8yv8",
                      "score_hidden": false,
                      "stickied": false,
                      "created": 1465032585.0,
                      "author_flair_text": null,
                      "created_utc": 1465003785.0,
                      "distinguished": null,
                      "mod_reports": [

                      ],
                      "num_reports": null,
                      "ups": 26
                    }
                  },
                  {
                    "kind": "t1",
                    "data": {
                      "subreddit_id": "t5_2qh33",
                      "banned_by": null,
                      "removal_reason": null,
                      "link_id": "t3_4mfawk",
                      "likes": null,
                      "replies": {
                        "kind": "Listing",
                        "data": {
                          "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                          "children": [
                            {
                              "kind": "more",
                              "data": {
                                "count": 3,
                                "parent_id": "t1_d3v906p",
                                "children": [
                                  "d3v9uph"
                                ],
                                "name": "t1_d3v9uph",
                                "id": "d3v9uph"
                              }
                            }
                          ],
                          "after": null,
                          "before": null
                        }
                      },
                      "user_reports": [

                      ],
                      "saved": false,
                      "id": "d3v906p",
                      "gilded": 0,
                      "archived": false,
                      "report_reasons": null,
                      "author": "SHIT_SOUP",
                      "parent_id": "t1_d3v3inc",
                      "score": 8,
                      "approved_by": null,
                      "controversiality": 0,
                      "body": "I have an inexplicable desire to know more about this guy",
                      "edited": false,
                      "author_flair_css_class": null,
                      "downs": 0,
                      "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;I have an inexplicable desire to know more about this guy&lt;/p&gt;\n&lt;/div&gt;",
                      "subreddit": "funny",
                      "name": "t1_d3v906p",
                      "score_hidden": false,
                      "stickied": false,
                      "created": 1465032651.0,
                      "author_flair_text": null,
                      "created_utc": 1465003851.0,
                      "distinguished": null,
                      "mod_reports": [

                      ],
                      "num_reports": null,
                      "ups": 8
                    }
                  },
                  {
                    "kind": "more",
                    "data": {
                      "count": 53,
                      "parent_id": "t1_d3v3inc",
                      "id": "d3vcfob",
                      "name": "t1_d3vcfob",
                      "children": [
                        "d3vcfob",
                        "d3v959g",
                        "d3v9e6g",
                        "d3vb2yx",
                        "d3v8ljj",
                        "d3vcavv",
                        "d3v7xz8",
                        "d3v9p97",
                        "d3vbopq",
                        "d3v97of"
                      ]
                    }
                  }
                ],
                "after": null,
                "before": null
              }
            },
            "user_reports": [

            ],
            "saved": false,
            "id": "d3v3inc",
            "gilded": 0,
            "archived": false,
            "report_reasons": null,
            "author": "mrshatnertoyou",
            "parent_id": "t3_4mfawk",
            "score": 675,
            "approved_by": null,
            "controversiality": 0,
            "body": "http://i.imgur.com/zRkZAZM.gif",
            "edited": false,
            "author_flair_css_class": null,
            "downs": 0,
            "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;&lt;a href=\"http://i.imgur.com/zRkZAZM.gif\"&gt;http://i.imgur.com/zRkZAZM.gif&lt;/a&gt;&lt;/p&gt;\n&lt;/div&gt;",
            "subreddit": "funny",
            "name": "t1_d3v3inc",
            "score_hidden": false,
            "stickied": false,
            "created": 1465023148.0,
            "author_flair_text": null,
            "created_utc": 1464994348.0,
            "distinguished": null,
            "mod_reports": [

            ],
            "num_reports": null,
            "ups": 675
          }
        },
        {
          "kind": "t1",
          "data": {
            "subreddit_id": "t5_2qh33",
            "banned_by": null,
            "removal_reason": null,
            "link_id": "t3_4mfawk",
            "likes": null,
            "replies": {
              "kind": "Listing",
              "data": {
                "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                "children": [
                  {
                    "kind": "t1",
                    "data": {
                      "subreddit_id": "t5_2qh33",
                      "banned_by": null,
                      "removal_reason": null,
                      "link_id": "t3_4mfawk",
                      "likes": null,
                      "replies": {
                        "kind": "Listing",
                        "data": {
                          "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                          "children": [
                            {
                              "kind": "t1",
                              "data": {
                                "subreddit_id": "t5_2qh33",
                                "banned_by": null,
                                "removal_reason": null,
                                "link_id": "t3_4mfawk",
                                "likes": null,
                                "replies": {
                                  "kind": "Listing",
                                  "data": {
                                    "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                    "children": [
                                      {
                                        "kind": "t1",
                                        "data": {
                                          "subreddit_id": "t5_2qh33",
                                          "banned_by": null,
                                          "removal_reason": null,
                                          "link_id": "t3_4mfawk",
                                          "likes": null,
                                          "replies": {
                                            "kind": "Listing",
                                            "data": {
                                              "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                              "children": [
                                                {
                                                  "kind": "more",
                                                  "data": {
                                                    "count": 1,
                                                    "parent_id": "t1_d3v9vzs",
                                                    "children": [
                                                      "d3vcjvv"
                                                    ],
                                                    "name": "t1_d3vcjvv",
                                                    "id": "d3vcjvv"
                                                  }
                                                }
                                              ],
                                              "after": null,
                                              "before": null
                                            }
                                          },
                                          "user_reports": [

                                          ],
                                          "saved": false,
                                          "id": "d3v9vzs",
                                          "gilded": 0,
                                          "archived": false,
                                          "report_reasons": null,
                                          "author": "unclefisty",
                                          "parent_id": "t1_d3v8s7e",
                                          "score": 9,
                                          "approved_by": null,
                                          "controversiality": 0,
                                          "body": "Well I bet his balls sure did.",
                                          "edited": false,
                                          "author_flair_css_class": null,
                                          "downs": 0,
                                          "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Well I bet his balls sure did.&lt;/p&gt;\n&lt;/div&gt;",
                                          "subreddit": "funny",
                                          "name": "t1_d3v9vzs",
                                          "score_hidden": false,
                                          "stickied": false,
                                          "created": 1465034187.0,
                                          "author_flair_text": null,
                                          "created_utc": 1465005387.0,
                                          "distinguished": null,
                                          "mod_reports": [

                                          ],
                                          "num_reports": null,
                                          "ups": 9
                                        }
                                      },
                                      {
                                        "kind": "more",
                                        "data": {
                                          "count": 1,
                                          "parent_id": "t1_d3v8s7e",
                                          "id": "d3vc9jp",
                                          "name": "t1_d3vc9jp",
                                          "children": [
                                            "d3vc9jp"
                                          ]
                                        }
                                      }
                                    ],
                                    "after": null,
                                    "before": null
                                  }
                                },
                                "user_reports": [

                                ],
                                "saved": false,
                                "id": "d3v8s7e",
                                "gilded": 0,
                                "archived": false,
                                "report_reasons": null,
                                "author": "crimsontideftw24",
                                "parent_id": "t1_d3v7x3i",
                                "score": 223,
                                "approved_by": null,
                                "controversiality": 0,
                                "body": "[So you did this, right?](http://i.imgur.com/rytrJfc.jpg)",
                                "edited": false,
                                "author_flair_css_class": null,
                                "downs": 0,
                                "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;&lt;a href=\"http://i.imgur.com/rytrJfc.jpg\"&gt;So you did this, right?&lt;/a&gt;&lt;/p&gt;\n&lt;/div&gt;",
                                "subreddit": "funny",
                                "name": "t1_d3v8s7e",
                                "score_hidden": false,
                                "stickied": false,
                                "created": 1465032258.0,
                                "author_flair_text": null,
                                "created_utc": 1465003458.0,
                                "distinguished": null,
                                "mod_reports": [

                                ],
                                "num_reports": null,
                                "ups": 223
                              }
                            },
                            {
                              "kind": "t1",
                              "data": {
                                "subreddit_id": "t5_2qh33",
                                "banned_by": null,
                                "removal_reason": null,
                                "link_id": "t3_4mfawk",
                                "likes": null,
                                "replies": {
                                  "kind": "Listing",
                                  "data": {
                                    "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                    "children": [
                                      {
                                        "kind": "more",
                                        "data": {
                                          "count": 8,
                                          "parent_id": "t1_d3v8sa0",
                                          "id": "d3vas7m",
                                          "name": "t1_d3vas7m",
                                          "children": [
                                            "d3vas7m",
                                            "d3v9nuk",
                                            "d3vcc53"
                                          ]
                                        }
                                      }
                                    ],
                                    "after": null,
                                    "before": null
                                  }
                                },
                                "user_reports": [

                                ],
                                "saved": false,
                                "id": "d3v8sa0",
                                "gilded": 0,
                                "archived": false,
                                "report_reasons": null,
                                "author": "qwertymaster",
                                "parent_id": "t1_d3v7x3i",
                                "score": 42,
                                "approved_by": null,
                                "controversiality": 0,
                                "body": "Picture of it melted?",
                                "edited": false,
                                "author_flair_css_class": null,
                                "downs": 0,
                                "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Picture of it melted?&lt;/p&gt;\n&lt;/div&gt;",
                                "subreddit": "funny",
                                "name": "t1_d3v8sa0",
                                "score_hidden": false,
                                "stickied": false,
                                "created": 1465032262.0,
                                "author_flair_text": null,
                                "created_utc": 1465003462.0,
                                "distinguished": null,
                                "mod_reports": [

                                ],
                                "num_reports": null,
                                "ups": 42
                              }
                            },
                            {
                              "kind": "t1",
                              "data": {
                                "subreddit_id": "t5_2qh33",
                                "banned_by": null,
                                "removal_reason": null,
                                "link_id": "t3_4mfawk",
                                "likes": null,
                                "replies": {
                                  "kind": "Listing",
                                  "data": {
                                    "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                    "children": [
                                      {
                                        "kind": "more",
                                        "data": {
                                          "count": 4,
                                          "parent_id": "t1_d3v8x7w",
                                          "id": "d3vb4rd",
                                          "name": "t1_d3vb4rd",
                                          "children": [
                                            "d3vb4rd",
                                            "d3vbnhu",
                                            "d3vcap2"
                                          ]
                                        }
                                      }
                                    ],
                                    "after": null,
                                    "before": null
                                  }
                                },
                                "user_reports": [

                                ],
                                "saved": false,
                                "id": "d3v8x7w",
                                "gilded": 0,
                                "archived": false,
                                "report_reasons": null,
                                "author": "TIE_FIGHTER_HANDS",
                                "parent_id": "t1_d3v7x3i",
                                "score": 25,
                                "approved_by": null,
                                "controversiality": 0,
                                "body": "You should always put outdoor thermometers in the shade, it's much more accurate to the actual air temperature, and much less melty. ",
                                "edited": false,
                                "author_flair_css_class": null,
                                "downs": 0,
                                "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;You should always put outdoor thermometers in the shade, it&amp;#39;s much more accurate to the actual air temperature, and much less melty. &lt;/p&gt;\n&lt;/div&gt;",
                                "subreddit": "funny",
                                "name": "t1_d3v8x7w",
                                "score_hidden": false,
                                "stickied": false,
                                "created": 1465032505.0,
                                "author_flair_text": null,
                                "created_utc": 1465003705.0,
                                "distinguished": null,
                                "mod_reports": [

                                ],
                                "num_reports": null,
                                "ups": 25
                              }
                            },
                            {
                              "kind": "t1",
                              "data": {
                                "subreddit_id": "t5_2qh33",
                                "banned_by": null,
                                "removal_reason": null,
                                "link_id": "t3_4mfawk",
                                "likes": null,
                                "replies": "",
                                "user_reports": [

                                ],
                                "saved": false,
                                "id": "d3v8stf",
                                "gilded": 0,
                                "archived": false,
                                "report_reasons": null,
                                "author": "therapistiscrazy",
                                "parent_id": "t1_d3v7x3i",
                                "score": 19,
                                "approved_by": null,
                                "controversiality": 0,
                                "body": "I was wearing flip flops earlier and was standing next to our running car for a second. Holy shit, it felt like I was laying an iron on my foot between the heat from the car and the heat coming off the ground. ",
                                "edited": false,
                                "author_flair_css_class": null,
                                "downs": 0,
                                "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;I was wearing flip flops earlier and was standing next to our running car for a second. Holy shit, it felt like I was laying an iron on my foot between the heat from the car and the heat coming off the ground. &lt;/p&gt;\n&lt;/div&gt;",
                                "subreddit": "funny",
                                "name": "t1_d3v8stf",
                                "score_hidden": false,
                                "stickied": false,
                                "created": 1465032289.0,
                                "author_flair_text": null,
                                "created_utc": 1465003489.0,
                                "distinguished": null,
                                "mod_reports": [

                                ],
                                "num_reports": null,
                                "ups": 19
                              }
                            },
                            {
                              "kind": "more",
                              "data": {
                                "count": 21,
                                "parent_id": "t1_d3v7x3i",
                                "children": [
                                  "d3vbhk2",
                                  "d3vbz9t",
                                  "d3vcabt",
                                  "d3vcmto",
                                  "d3v98k1",
                                  "d3v941g",
                                  "d3vc57g",
                                  "d3vcfy6",
                                  "d3vc0qn"
                                ],
                                "name": "t1_d3vbhk2",
                                "id": "d3vbhk2"
                              }
                            }
                          ],
                          "after": null,
                          "before": null
                        }
                      },
                      "user_reports": [

                      ],
                      "saved": false,
                      "id": "d3v7x3i",
                      "gilded": 0,
                      "archived": false,
                      "report_reasons": null,
                      "author": "awesometographer",
                      "parent_id": "t1_d3v4370",
                      "score": 267,
                      "approved_by": null,
                      "controversiality": 0,
                      "body": "[Radiant heat is a bitch](http://imgur.com/sXTXbT7) - I bike an hour home in this heat...\n\nI put up [this thermometer](http://imgur.com/kQtgIdI) behind my shop one morning. This was at 3:00 - It melted before 5:00",
                      "edited": false,
                      "author_flair_css_class": null,
                      "downs": 0,
                      "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;&lt;a href=\"http://imgur.com/sXTXbT7\"&gt;Radiant heat is a bitch&lt;/a&gt; - I bike an hour home in this heat...&lt;/p&gt;\n\n&lt;p&gt;I put up &lt;a href=\"http://imgur.com/kQtgIdI\"&gt;this thermometer&lt;/a&gt; behind my shop one morning. This was at 3:00 - It melted before 5:00&lt;/p&gt;\n&lt;/div&gt;",
                      "subreddit": "funny",
                      "name": "t1_d3v7x3i",
                      "score_hidden": false,
                      "stickied": false,
                      "created": 1465030810.0,
                      "author_flair_text": null,
                      "created_utc": 1465002010.0,
                      "distinguished": null,
                      "mod_reports": [

                      ],
                      "num_reports": null,
                      "ups": 267
                    }
                  },
                  {
                    "kind": "t1",
                    "data": {
                      "subreddit_id": "t5_2qh33",
                      "banned_by": null,
                      "removal_reason": null,
                      "link_id": "t3_4mfawk",
                      "likes": null,
                      "replies": {
                        "kind": "Listing",
                        "data": {
                          "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                          "children": [
                            {
                              "kind": "t1",
                              "data": {
                                "subreddit_id": "t5_2qh33",
                                "banned_by": null,
                                "removal_reason": null,
                                "link_id": "t3_4mfawk",
                                "likes": null,
                                "replies": {
                                  "kind": "Listing",
                                  "data": {
                                    "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                    "children": [
                                      {
                                        "kind": "more",
                                        "data": {
                                          "count": 9,
                                          "parent_id": "t1_d3v8wvq",
                                          "id": "d3vb06q",
                                          "name": "t1_d3vb06q",
                                          "children": [
                                            "d3vb06q"
                                          ]
                                        }
                                      }
                                    ],
                                    "after": null,
                                    "before": null
                                  }
                                },
                                "user_reports": [

                                ],
                                "saved": false,
                                "id": "d3v8wvq",
                                "gilded": 0,
                                "archived": false,
                                "report_reasons": null,
                                "author": "TK42What",
                                "parent_id": "t1_d3v8k0b",
                                "score": 71,
                                "approved_by": null,
                                "controversiality": 0,
                                "body": "Remote start: it's not just for the cold:",
                                "edited": false,
                                "author_flair_css_class": null,
                                "downs": 0,
                                "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Remote start: it&amp;#39;s not just for the cold:&lt;/p&gt;\n&lt;/div&gt;",
                                "subreddit": "funny",
                                "name": "t1_d3v8wvq",
                                "score_hidden": false,
                                "stickied": false,
                                "created": 1465032487.0,
                                "author_flair_text": null,
                                "created_utc": 1465003687.0,
                                "distinguished": null,
                                "mod_reports": [

                                ],
                                "num_reports": null,
                                "ups": 71
                              }
                            },
                            {
                              "kind": "t1",
                              "data": {
                                "subreddit_id": "t5_2qh33",
                                "banned_by": null,
                                "removal_reason": null,
                                "link_id": "t3_4mfawk",
                                "likes": null,
                                "replies": {
                                  "kind": "Listing",
                                  "data": {
                                    "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                    "children": [
                                      {
                                        "kind": "t1",
                                        "data": {
                                          "subreddit_id": "t5_2qh33",
                                          "banned_by": null,
                                          "removal_reason": null,
                                          "link_id": "t3_4mfawk",
                                          "likes": null,
                                          "replies": {
                                            "kind": "Listing",
                                            "data": {
                                              "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                              "children": [
                                                {
                                                  "kind": "more",
                                                  "data": {
                                                    "count": 2,
                                                    "parent_id": "t1_d3valab",
                                                    "children": [
                                                      "d3vbzts",
                                                      "d3vco0v"
                                                    ],
                                                    "name": "t1_d3vbzts",
                                                    "id": "d3vbzts"
                                                  }
                                                }
                                              ],
                                              "after": null,
                                              "before": null
                                            }
                                          },
                                          "user_reports": [

                                          ],
                                          "saved": false,
                                          "id": "d3valab",
                                          "gilded": 0,
                                          "archived": false,
                                          "report_reasons": null,
                                          "author": "oceanpine",
                                          "parent_id": "t1_d3v917k",
                                          "score": 15,
                                          "approved_by": null,
                                          "controversiality": 0,
                                          "body": "I still have a car with no A/C and I live in Tucson. I'm usually ok for short trips but I do get a little panicky if it takes too long to get through a light. I've started staying in the right lane just in case I need to pull over and run into a Circle K. ",
                                          "edited": false,
                                          "author_flair_css_class": null,
                                          "downs": 0,
                                          "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;I still have a car with no A/C and I live in Tucson. I&amp;#39;m usually ok for short trips but I do get a little panicky if it takes too long to get through a light. I&amp;#39;ve started staying in the right lane just in case I need to pull over and run into a Circle K. &lt;/p&gt;\n&lt;/div&gt;",
                                          "subreddit": "funny",
                                          "name": "t1_d3valab",
                                          "score_hidden": false,
                                          "stickied": false,
                                          "created": 1465035381.0,
                                          "author_flair_text": null,
                                          "created_utc": 1465006581.0,
                                          "distinguished": null,
                                          "mod_reports": [

                                          ],
                                          "num_reports": null,
                                          "ups": 15
                                        }
                                      }
                                    ],
                                    "after": null,
                                    "before": null
                                  }
                                },
                                "user_reports": [

                                ],
                                "saved": false,
                                "id": "d3v917k",
                                "gilded": 0,
                                "archived": false,
                                "report_reasons": null,
                                "author": "Iforgotwhatimdoing",
                                "parent_id": "t1_d3v8k0b",
                                "score": 37,
                                "approved_by": null,
                                "controversiality": 0,
                                "body": "I moved to AZ several years back driving a black car with no A/C.  Promptly traded it in for a white car with a working A/C because after a week I thought I was gonna pass out stuck in traffic.",
                                "edited": false,
                                "author_flair_css_class": null,
                                "downs": 0,
                                "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;I moved to AZ several years back driving a black car with no A/C.  Promptly traded it in for a white car with a working A/C because after a week I thought I was gonna pass out stuck in traffic.&lt;/p&gt;\n&lt;/div&gt;",
                                "subreddit": "funny",
                                "name": "t1_d3v917k",
                                "score_hidden": false,
                                "stickied": false,
                                "created": 1465032702.0,
                                "author_flair_text": null,
                                "created_utc": 1465003902.0,
                                "distinguished": null,
                                "mod_reports": [

                                ],
                                "num_reports": null,
                                "ups": 37
                              }
                            },
                            {
                              "kind": "t1",
                              "data": {
                                "subreddit_id": "t5_2qh33",
                                "banned_by": null,
                                "removal_reason": null,
                                "link_id": "t3_4mfawk",
                                "likes": null,
                                "replies": "",
                                "user_reports": [

                                ],
                                "saved": false,
                                "id": "d3v9zik",
                                "gilded": 0,
                                "archived": false,
                                "report_reasons": null,
                                "author": "quarab",
                                "parent_id": "t1_d3v8k0b",
                                "score": 22,
                                "approved_by": null,
                                "controversiality": 0,
                                "body": "Why when we go car shopping we insist that the salesmen *not* cool the car down and bring it around for us. Want to know exactly how long it takes to cool down.\n",
                                "edited": false,
                                "author_flair_css_class": null,
                                "downs": 0,
                                "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Why when we go car shopping we insist that the salesmen &lt;em&gt;not&lt;/em&gt; cool the car down and bring it around for us. Want to know exactly how long it takes to cool down.&lt;/p&gt;\n&lt;/div&gt;",
                                "subreddit": "funny",
                                "name": "t1_d3v9zik",
                                "score_hidden": false,
                                "stickied": false,
                                "created": 1465034363.0,
                                "author_flair_text": null,
                                "created_utc": 1465005563.0,
                                "distinguished": null,
                                "mod_reports": [

                                ],
                                "num_reports": null,
                                "ups": 22
                              }
                            },
                            {
                              "kind": "more",
                              "data": {
                                "count": 11,
                                "parent_id": "t1_d3v8k0b",
                                "children": [
                                  "d3vbdvl",
                                  "d3vcf50",
                                  "d3vcdgh",
                                  "d3varaq",
                                  "d3vaprm",
                                  "d3vcltn",
                                  "d3va63g"
                                ],
                                "name": "t1_d3vbdvl",
                                "id": "d3vbdvl"
                              }
                            }
                          ],
                          "after": null,
                          "before": null
                        }
                      },
                      "user_reports": [

                      ],
                      "saved": false,
                      "id": "d3v8k0b",
                      "gilded": 0,
                      "archived": false,
                      "report_reasons": null,
                      "author": "Nofgob",
                      "parent_id": "t1_d3v4370",
                      "score": 47,
                      "approved_by": null,
                      "controversiality": 0,
                      "body": "I have a black truck. When I got in it today the thermometer read 126\u00b0. Was a pretty sweaty ride until the A/C kicked in enough to start cooling it down.",
                      "edited": false,
                      "author_flair_css_class": null,
                      "downs": 0,
                      "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;I have a black truck. When I got in it today the thermometer read 126\u00b0. Was a pretty sweaty ride until the A/C kicked in enough to start cooling it down.&lt;/p&gt;\n&lt;/div&gt;",
                      "subreddit": "funny",
                      "name": "t1_d3v8k0b",
                      "score_hidden": false,
                      "stickied": false,
                      "created": 1465031869.0,
                      "author_flair_text": null,
                      "created_utc": 1465003069.0,
                      "distinguished": null,
                      "mod_reports": [

                      ],
                      "num_reports": null,
                      "ups": 47
                    }
                  },
                  {
                    "kind": "more",
                    "data": {
                      "count": 39,
                      "parent_id": "t1_d3v4370",
                      "id": "d3v7siq",
                      "name": "t1_d3v7siq",
                      "children": [
                        "d3v7siq",
                        "d3v8kg4",
                        "d3val06",
                        "d3vbhzc",
                        "d3v8zk9",
                        "d3vb6ru",
                        "d3vbeo2",
                        "d3v8xu7",
                        "d3vaulu",
                        "d3vai1p",
                        "d3vb6om",
                        "d3vc0vs",
                        "d3vb0nm",
                        "d3vbecd",
                        "d3vbnlq",
                        "d3vbgud"
                      ]
                    }
                  }
                ],
                "after": null,
                "before": null
              }
            },
            "user_reports": [

            ],
            "saved": false,
            "id": "d3v4370",
            "gilded": 0,
            "archived": false,
            "report_reasons": null,
            "author": "GlobalMessenger",
            "parent_id": "t3_4mfawk",
            "score": 486,
            "approved_by": null,
            "controversiality": 0,
            "body": "I thought this was just a joke...then I googled \"temperature in Phoenix\". Much respect to everyone in Phoenix!",
            "edited": false,
            "author_flair_css_class": null,
            "downs": 0,
            "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;I thought this was just a joke...then I googled &amp;quot;temperature in Phoenix&amp;quot;. Much respect to everyone in Phoenix!&lt;/p&gt;\n&lt;/div&gt;",
            "subreddit": "funny",
            "name": "t1_d3v4370",
            "score_hidden": false,
            "stickied": false,
            "created": 1465024117.0,
            "author_flair_text": null,
            "created_utc": 1464995317.0,
            "distinguished": null,
            "mod_reports": [

            ],
            "num_reports": null,
            "ups": 486
          }
        },
        {
          "kind": "t1",
          "data": {
            "subreddit_id": "t5_2qh33",
            "banned_by": null,
            "removal_reason": null,
            "link_id": "t3_4mfawk",
            "likes": null,
            "replies": {
              "kind": "Listing",
              "data": {
                "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                "children": [
                  {
                    "kind": "t1",
                    "data": {
                      "subreddit_id": "t5_2qh33",
                      "banned_by": null,
                      "removal_reason": null,
                      "link_id": "t3_4mfawk",
                      "likes": null,
                      "replies": {
                        "kind": "Listing",
                        "data": {
                          "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                          "children": [
                            {
                              "kind": "t1",
                              "data": {
                                "subreddit_id": "t5_2qh33",
                                "banned_by": null,
                                "removal_reason": null,
                                "link_id": "t3_4mfawk",
                                "likes": null,
                                "replies": {
                                  "kind": "Listing",
                                  "data": {
                                    "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                    "children": [
                                      {
                                        "kind": "t1",
                                        "data": {
                                          "subreddit_id": "t5_2qh33",
                                          "banned_by": null,
                                          "removal_reason": null,
                                          "link_id": "t3_4mfawk",
                                          "likes": null,
                                          "replies": {
                                            "kind": "Listing",
                                            "data": {
                                              "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                              "children": [
                                                {
                                                  "kind": "t1",
                                                  "data": {
                                                    "subreddit_id": "t5_2qh33",
                                                    "banned_by": null,
                                                    "removal_reason": null,
                                                    "link_id": "t3_4mfawk",
                                                    "likes": null,
                                                    "replies": "",
                                                    "user_reports": [

                                                    ],
                                                    "saved": false,
                                                    "id": "d3v8edl",
                                                    "gilded": 0,
                                                    "archived": false,
                                                    "report_reasons": null,
                                                    "author": "Crymson831",
                                                    "parent_id": "t1_d3v7kkb",
                                                    "score": 13,
                                                    "approved_by": null,
                                                    "controversiality": 0,
                                                    "body": "No you haven't, Shawn.",
                                                    "edited": false,
                                                    "author_flair_css_class": null,
                                                    "downs": 0,
                                                    "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;No you haven&amp;#39;t, Shawn.&lt;/p&gt;\n&lt;/div&gt;",
                                                    "subreddit": "funny",
                                                    "name": "t1_d3v8edl",
                                                    "score_hidden": false,
                                                    "stickied": false,
                                                    "created": 1465031608.0,
                                                    "author_flair_text": null,
                                                    "created_utc": 1465002808.0,
                                                    "distinguished": null,
                                                    "mod_reports": [

                                                    ],
                                                    "num_reports": null,
                                                    "ups": 13
                                                  }
                                                },
                                                {
                                                  "kind": "t1",
                                                  "data": {
                                                    "subreddit_id": "t5_2qh33",
                                                    "banned_by": null,
                                                    "removal_reason": null,
                                                    "link_id": "t3_4mfawk",
                                                    "likes": null,
                                                    "replies": {
                                                      "kind": "Listing",
                                                      "data": {
                                                        "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                                        "children": [
                                                          {
                                                            "kind": "more",
                                                            "data": {
                                                              "count": 1,
                                                              "parent_id": "t1_d3v8kbb",
                                                              "id": "d3v9bxf",
                                                              "name": "t1_d3v9bxf",
                                                              "children": [
                                                                "d3v9bxf"
                                                              ]
                                                            }
                                                          }
                                                        ],
                                                        "after": null,
                                                        "before": null
                                                      }
                                                    },
                                                    "user_reports": [

                                                    ],
                                                    "saved": false,
                                                    "id": "d3v8kbb",
                                                    "gilded": 0,
                                                    "archived": false,
                                                    "report_reasons": null,
                                                    "author": "Loki_SW",
                                                    "parent_id": "t1_d3v7kkb",
                                                    "score": 11,
                                                    "approved_by": null,
                                                    "controversiality": 0,
                                                    "body": "No you haven't, Shaun",
                                                    "edited": false,
                                                    "author_flair_css_class": null,
                                                    "downs": 0,
                                                    "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;No you haven&amp;#39;t, Shaun&lt;/p&gt;\n&lt;/div&gt;",
                                                    "subreddit": "funny",
                                                    "name": "t1_d3v8kbb",
                                                    "score_hidden": false,
                                                    "stickied": false,
                                                    "created": 1465031883.0,
                                                    "author_flair_text": null,
                                                    "created_utc": 1465003083.0,
                                                    "distinguished": null,
                                                    "mod_reports": [

                                                    ],
                                                    "num_reports": null,
                                                    "ups": 11
                                                  }
                                                },
                                                {
                                                  "kind": "more",
                                                  "data": {
                                                    "count": 3,
                                                    "parent_id": "t1_d3v7kkb",
                                                    "children": [
                                                      "d3v9fc1",
                                                      "d3vb57a",
                                                      "d3v9bbz"
                                                    ],
                                                    "name": "t1_d3v9fc1",
                                                    "id": "d3v9fc1"
                                                  }
                                                }
                                              ],
                                              "after": null,
                                              "before": null
                                            }
                                          },
                                          "user_reports": [

                                          ],
                                          "saved": false,
                                          "id": "d3v7kkb",
                                          "gilded": 0,
                                          "archived": false,
                                          "report_reasons": null,
                                          "author": "RenderUntoWalter",
                                          "parent_id": "t1_d3v75iw",
                                          "score": 17,
                                          "approved_by": null,
                                          "controversiality": 0,
                                          "body": "I've heard both. ",
                                          "edited": false,
                                          "author_flair_css_class": null,
                                          "downs": 0,
                                          "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;I&amp;#39;ve heard both. &lt;/p&gt;\n&lt;/div&gt;",
                                          "subreddit": "funny",
                                          "name": "t1_d3v7kkb",
                                          "score_hidden": false,
                                          "stickied": false,
                                          "created": 1465030200.0,
                                          "author_flair_text": null,
                                          "created_utc": 1465001400.0,
                                          "distinguished": null,
                                          "mod_reports": [

                                          ],
                                          "num_reports": null,
                                          "ups": 17
                                        }
                                      },
                                      {
                                        "kind": "more",
                                        "data": {
                                          "count": 18,
                                          "parent_id": "t1_d3v75iw",
                                          "id": "d3v9uoy",
                                          "name": "t1_d3v9uoy",
                                          "children": [
                                            "d3v9uoy",
                                            "d3v8oqw",
                                            "d3v8d61",
                                            "d3v98bu",
                                            "d3v83bj",
                                            "d3v97cg",
                                            "d3v7v5m",
                                            "d3v7yof",
                                            "d3v9u9q"
                                          ]
                                        }
                                      }
                                    ],
                                    "after": null,
                                    "before": null
                                  }
                                },
                                "user_reports": [

                                ],
                                "saved": false,
                                "id": "d3v75iw",
                                "gilded": 0,
                                "archived": false,
                                "report_reasons": null,
                                "author": "Warlizard",
                                "parent_id": "t1_d3v6vde",
                                "score": 72,
                                "approved_by": null,
                                "controversiality": 0,
                                "body": "Get it right. \"Snobbsdale\".",
                                "edited": false,
                                "author_flair_css_class": null,
                                "downs": 0,
                                "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Get it right. &amp;quot;Snobbsdale&amp;quot;.&lt;/p&gt;\n&lt;/div&gt;",
                                "subreddit": "funny",
                                "name": "t1_d3v75iw",
                                "score_hidden": false,
                                "stickied": false,
                                "created": 1465029462.0,
                                "author_flair_text": null,
                                "created_utc": 1465000662.0,
                                "distinguished": null,
                                "mod_reports": [

                                ],
                                "num_reports": null,
                                "ups": 72
                              }
                            },
                            {
                              "kind": "more",
                              "data": {
                                "count": 10,
                                "parent_id": "t1_d3v6vde",
                                "children": [
                                  "d3vb7sx",
                                  "d3vcdwb",
                                  "d3vcnqh",
                                  "d3v95jg",
                                  "d3v8s0i",
                                  "d3v8g6t",
                                  "d3v9la3"
                                ],
                                "name": "t1_d3vb7sx",
                                "id": "d3vb7sx"
                              }
                            }
                          ],
                          "after": null,
                          "before": null
                        }
                      },
                      "user_reports": [

                      ],
                      "saved": false,
                      "id": "d3v6vde",
                      "gilded": 0,
                      "archived": false,
                      "report_reasons": null,
                      "author": "Prince_Edward_IV",
                      "parent_id": "t1_d3v3618",
                      "score": 109,
                      "approved_by": null,
                      "controversiality": 0,
                      "body": "MORE LIKE SNOTSDALE\n\nedit: a bit more Arizonianites on here than i thought. i was afraid nobody would get the joke. hi guys Glendale here. we're almost good enough to be Phoenix but not good enough to be PV ",
                      "edited": 1465006216.0,
                      "author_flair_css_class": null,
                      "downs": 0,
                      "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;MORE LIKE SNOTSDALE&lt;/p&gt;\n\n&lt;p&gt;edit: a bit more Arizonianites on here than i thought. i was afraid nobody would get the joke. hi guys Glendale here. we&amp;#39;re almost good enough to be Phoenix but not good enough to be PV &lt;/p&gt;\n&lt;/div&gt;",
                      "subreddit": "funny",
                      "name": "t1_d3v6vde",
                      "score_hidden": false,
                      "stickied": false,
                      "created": 1465028965.0,
                      "author_flair_text": null,
                      "created_utc": 1465000165.0,
                      "distinguished": null,
                      "mod_reports": [

                      ],
                      "num_reports": null,
                      "ups": 109
                    }
                  },
                  {
                    "kind": "t1",
                    "data": {
                      "subreddit_id": "t5_2qh33",
                      "banned_by": null,
                      "removal_reason": null,
                      "link_id": "t3_4mfawk",
                      "likes": null,
                      "replies": {
                        "kind": "Listing",
                        "data": {
                          "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                          "children": [
                            {
                              "kind": "t1",
                              "data": {
                                "subreddit_id": "t5_2qh33",
                                "banned_by": null,
                                "removal_reason": null,
                                "link_id": "t3_4mfawk",
                                "likes": null,
                                "replies": {
                                  "kind": "Listing",
                                  "data": {
                                    "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                    "children": [
                                      {
                                        "kind": "t1",
                                        "data": {
                                          "subreddit_id": "t5_2qh33",
                                          "banned_by": null,
                                          "removal_reason": null,
                                          "link_id": "t3_4mfawk",
                                          "likes": null,
                                          "replies": {
                                            "kind": "Listing",
                                            "data": {
                                              "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                              "children": [
                                                {
                                                  "kind": "t1",
                                                  "data": {
                                                    "subreddit_id": "t5_2qh33",
                                                    "banned_by": null,
                                                    "removal_reason": null,
                                                    "link_id": "t3_4mfawk",
                                                    "likes": null,
                                                    "replies": {
                                                      "kind": "Listing",
                                                      "data": {
                                                        "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                                        "children": [
                                                          {
                                                            "kind": "t1",
                                                            "data": {
                                                              "subreddit_id": "t5_2qh33",
                                                              "banned_by": null,
                                                              "removal_reason": null,
                                                              "link_id": "t3_4mfawk",
                                                              "likes": null,
                                                              "replies": {
                                                                "kind": "Listing",
                                                                "data": {
                                                                  "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                                                  "children": [
                                                                    {
                                                                      "kind": "more",
                                                                      "data": {
                                                                        "count": 3,
                                                                        "parent_id": "t1_d3v79mj",
                                                                        "children": [
                                                                          "d3v8912",
                                                                          "d3v85gv"
                                                                        ],
                                                                        "name": "t1_d3v8912",
                                                                        "id": "d3v8912"
                                                                      }
                                                                    }
                                                                  ],
                                                                  "after": null,
                                                                  "before": null
                                                                }
                                                              },
                                                              "user_reports": [

                                                              ],
                                                              "saved": false,
                                                              "id": "d3v79mj",
                                                              "gilded": 0,
                                                              "archived": false,
                                                              "report_reasons": null,
                                                              "author": "Mjolnir12",
                                                              "parent_id": "t1_d3v7634",
                                                              "score": 22,
                                                              "approved_by": null,
                                                              "controversiality": 0,
                                                              "body": "Sounds like confirmation to me.",
                                                              "edited": false,
                                                              "author_flair_css_class": null,
                                                              "downs": 0,
                                                              "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Sounds like confirmation to me.&lt;/p&gt;\n&lt;/div&gt;",
                                                              "subreddit": "funny",
                                                              "name": "t1_d3v79mj",
                                                              "score_hidden": false,
                                                              "stickied": false,
                                                              "created": 1465029668.0,
                                                              "author_flair_text": null,
                                                              "created_utc": 1465000868.0,
                                                              "distinguished": null,
                                                              "mod_reports": [

                                                              ],
                                                              "num_reports": null,
                                                              "ups": 22
                                                            }
                                                          },
                                                          {
                                                            "kind": "more",
                                                            "data": {
                                                              "count": 3,
                                                              "parent_id": "t1_d3v7634",
                                                              "id": "d3vau94",
                                                              "name": "t1_d3vau94",
                                                              "children": [
                                                                "d3vau94"
                                                              ]
                                                            }
                                                          }
                                                        ],
                                                        "after": null,
                                                        "before": null
                                                      }
                                                    },
                                                    "user_reports": [

                                                    ],
                                                    "saved": false,
                                                    "id": "d3v7634",
                                                    "gilded": 0,
                                                    "archived": false,
                                                    "report_reasons": null,
                                                    "author": "Warlizard",
                                                    "parent_id": "t1_d3v6q91",
                                                    "score": 30,
                                                    "approved_by": null,
                                                    "controversiality": 0,
                                                    "body": "A chance, sure.",
                                                    "edited": false,
                                                    "author_flair_css_class": null,
                                                    "downs": 0,
                                                    "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;A chance, sure.&lt;/p&gt;\n&lt;/div&gt;",
                                                    "subreddit": "funny",
                                                    "name": "t1_d3v7634",
                                                    "score_hidden": false,
                                                    "stickied": false,
                                                    "created": 1465029490.0,
                                                    "author_flair_text": null,
                                                    "created_utc": 1465000690.0,
                                                    "distinguished": null,
                                                    "mod_reports": [

                                                    ],
                                                    "num_reports": null,
                                                    "ups": 30
                                                  }
                                                }
                                              ],
                                              "after": null,
                                              "before": null
                                            }
                                          },
                                          "user_reports": [

                                          ],
                                          "saved": false,
                                          "id": "d3v6q91",
                                          "gilded": 0,
                                          "archived": false,
                                          "report_reasons": null,
                                          "author": "Mjolnir12",
                                          "parent_id": "t1_d3v46tp",
                                          "score": 38,
                                          "approved_by": null,
                                          "controversiality": 0,
                                          "body": "Since you live in Scottsdale, I guess there is a chance that you could be an actual lizard.",
                                          "edited": false,
                                          "author_flair_css_class": null,
                                          "downs": 0,
                                          "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Since you live in Scottsdale, I guess there is a chance that you could be an actual lizard.&lt;/p&gt;\n&lt;/div&gt;",
                                          "subreddit": "funny",
                                          "name": "t1_d3v6q91",
                                          "score_hidden": false,
                                          "stickied": false,
                                          "created": 1465028719.0,
                                          "author_flair_text": null,
                                          "created_utc": 1464999919.0,
                                          "distinguished": null,
                                          "mod_reports": [

                                          ],
                                          "num_reports": null,
                                          "ups": 38
                                        }
                                      },
                                      {
                                        "kind": "t1",
                                        "data": {
                                          "subreddit_id": "t5_2qh33",
                                          "banned_by": null,
                                          "removal_reason": null,
                                          "link_id": "t3_4mfawk",
                                          "likes": null,
                                          "replies": {
                                            "kind": "Listing",
                                            "data": {
                                              "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                              "children": [
                                                {
                                                  "kind": "t1",
                                                  "data": {
                                                    "subreddit_id": "t5_2qh33",
                                                    "banned_by": null,
                                                    "removal_reason": null,
                                                    "link_id": "t3_4mfawk",
                                                    "likes": null,
                                                    "replies": {
                                                      "kind": "Listing",
                                                      "data": {
                                                        "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                                        "children": [
                                                          {
                                                            "kind": "t1",
                                                            "data": {
                                                              "subreddit_id": "t5_2qh33",
                                                              "banned_by": null,
                                                              "removal_reason": null,
                                                              "link_id": "t3_4mfawk",
                                                              "likes": null,
                                                              "replies": "",
                                                              "user_reports": [

                                                              ],
                                                              "saved": false,
                                                              "id": "d3v7yc0",
                                                              "gilded": 0,
                                                              "archived": false,
                                                              "report_reasons": null,
                                                              "author": "Flux_n_Uck",
                                                              "parent_id": "t1_d3v77jc",
                                                              "score": 8,
                                                              "approved_by": null,
                                                              "controversiality": 0,
                                                              "body": "Wow I share a continent with that guy from the Warlizard gaming forum!",
                                                              "edited": false,
                                                              "author_flair_css_class": null,
                                                              "downs": 0,
                                                              "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Wow I share a continent with that guy from the Warlizard gaming forum!&lt;/p&gt;\n&lt;/div&gt;",
                                                              "subreddit": "funny",
                                                              "name": "t1_d3v7yc0",
                                                              "score_hidden": false,
                                                              "stickied": false,
                                                              "created": 1465030869.0,
                                                              "author_flair_text": null,
                                                              "created_utc": 1465002069.0,
                                                              "distinguished": null,
                                                              "mod_reports": [

                                                              ],
                                                              "num_reports": null,
                                                              "ups": 8
                                                            }
                                                          },
                                                          {
                                                            "kind": "more",
                                                            "data": {
                                                              "count": 3,
                                                              "parent_id": "t1_d3v77jc",
                                                              "id": "d3v895d",
                                                              "name": "t1_d3v895d",
                                                              "children": [
                                                                "d3v895d",
                                                                "d3v8a46"
                                                              ]
                                                            }
                                                          }
                                                        ],
                                                        "after": null,
                                                        "before": null
                                                      }
                                                    },
                                                    "user_reports": [

                                                    ],
                                                    "saved": false,
                                                    "id": "d3v77jc",
                                                    "gilded": 0,
                                                    "archived": false,
                                                    "report_reasons": null,
                                                    "author": "Warlizard",
                                                    "parent_id": "t1_d3v663z",
                                                    "score": 28,
                                                    "approved_by": null,
                                                    "controversiality": 0,
                                                    "body": "Maricopa represent.",
                                                    "edited": false,
                                                    "author_flair_css_class": null,
                                                    "downs": 0,
                                                    "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Maricopa represent.&lt;/p&gt;\n&lt;/div&gt;",
                                                    "subreddit": "funny",
                                                    "name": "t1_d3v77jc",
                                                    "score_hidden": false,
                                                    "stickied": false,
                                                    "created": 1465029564.0,
                                                    "author_flair_text": null,
                                                    "created_utc": 1465000764.0,
                                                    "distinguished": null,
                                                    "mod_reports": [

                                                    ],
                                                    "num_reports": null,
                                                    "ups": 28
                                                  }
                                                },
                                                {
                                                  "kind": "more",
                                                  "data": {
                                                    "count": 1,
                                                    "parent_id": "t1_d3v663z",
                                                    "children": [
                                                      "d3vagmw"
                                                    ],
                                                    "name": "t1_d3vagmw",
                                                    "id": "d3vagmw"
                                                  }
                                                }
                                              ],
                                              "after": null,
                                              "before": null
                                            }
                                          },
                                          "user_reports": [

                                          ],
                                          "saved": false,
                                          "id": "d3v663z",
                                          "gilded": 0,
                                          "archived": false,
                                          "report_reasons": null,
                                          "author": "RadarReady",
                                          "parent_id": "t1_d3v46tp",
                                          "score": 23,
                                          "approved_by": null,
                                          "controversiality": 0,
                                          "body": "Wow I share a county with that guy from the Warlizard gaming forum!",
                                          "edited": false,
                                          "author_flair_css_class": null,
                                          "downs": 0,
                                          "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Wow I share a county with that guy from the Warlizard gaming forum!&lt;/p&gt;\n&lt;/div&gt;",
                                          "subreddit": "funny",
                                          "name": "t1_d3v663z",
                                          "score_hidden": false,
                                          "stickied": false,
                                          "created": 1465027736.0,
                                          "author_flair_text": null,
                                          "created_utc": 1464998936.0,
                                          "distinguished": null,
                                          "mod_reports": [

                                          ],
                                          "num_reports": null,
                                          "ups": 23
                                        }
                                      },
                                      {
                                        "kind": "more",
                                        "data": {
                                          "count": 2,
                                          "parent_id": "t1_d3v46tp",
                                          "id": "d3vafak",
                                          "name": "t1_d3vafak",
                                          "children": [
                                            "d3vafak"
                                          ]
                                        }
                                      }
                                    ],
                                    "after": null,
                                    "before": null
                                  }
                                },
                                "user_reports": [

                                ],
                                "saved": false,
                                "id": "d3v46tp",
                                "gilded": 0,
                                "archived": false,
                                "report_reasons": null,
                                "author": "Warlizard",
                                "parent_id": "t1_d3v3mxc",
                                "score": 27,
                                "approved_by": null,
                                "controversiality": 0,
                                "body": "16:11:13 GMT-0700 (US Mountain Standard Time) \n\nAnd yes, presuming you live in Arizona.",
                                "edited": false,
                                "author_flair_css_class": null,
                                "downs": 0,
                                "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;16:11:13 GMT-0700 (US Mountain Standard Time) &lt;/p&gt;\n\n&lt;p&gt;And yes, presuming you live in Arizona.&lt;/p&gt;\n&lt;/div&gt;",
                                "subreddit": "funny",
                                "name": "t1_d3v46tp",
                                "score_hidden": false,
                                "stickied": false,
                                "created": 1465024290.0,
                                "author_flair_text": null,
                                "created_utc": 1464995490.0,
                                "distinguished": null,
                                "mod_reports": [

                                ],
                                "num_reports": null,
                                "ups": 27
                              }
                            }
                          ],
                          "after": null,
                          "before": null
                        }
                      },
                      "user_reports": [

                      ],
                      "saved": false,
                      "id": "d3v3mxc",
                      "gilded": 0,
                      "archived": false,
                      "report_reasons": null,
                      "author": "Mjolnir12",
                      "parent_id": "t1_d3v3618",
                      "score": 47,
                      "approved_by": null,
                      "controversiality": 0,
                      "body": "Hey, aren't you that guy from the Warlizard gaming forum? Also holy shit you live in the same state as me?\n\nAlso this is the third time I have done this to you, I'm sorry. I should probably stop.",
                      "edited": false,
                      "author_flair_css_class": null,
                      "downs": 0,
                      "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Hey, aren&amp;#39;t you that guy from the Warlizard gaming forum? Also holy shit you live in the same state as me?&lt;/p&gt;\n\n&lt;p&gt;Also this is the third time I have done this to you, I&amp;#39;m sorry. I should probably stop.&lt;/p&gt;\n&lt;/div&gt;",
                      "subreddit": "funny",
                      "name": "t1_d3v3mxc",
                      "score_hidden": false,
                      "stickied": false,
                      "created": 1465023351.0,
                      "author_flair_text": null,
                      "created_utc": 1464994551.0,
                      "distinguished": null,
                      "mod_reports": [

                      ],
                      "num_reports": null,
                      "ups": 47
                    }
                  },
                  {
                    "kind": "t1",
                    "data": {
                      "subreddit_id": "t5_2qh33",
                      "banned_by": null,
                      "removal_reason": null,
                      "link_id": "t3_4mfawk",
                      "likes": null,
                      "replies": {
                        "kind": "Listing",
                        "data": {
                          "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                          "children": [
                            {
                              "kind": "more",
                              "data": {
                                "count": 6,
                                "parent_id": "t1_d3v8z0e",
                                "children": [
                                  "d3vc1wi",
                                  "d3vazor",
                                  "d3vbp7r",
                                  "d3vbt3f",
                                  "d3vcigf",
                                  "d3v9ji7"
                                ],
                                "name": "t1_d3vc1wi",
                                "id": "d3vc1wi"
                              }
                            }
                          ],
                          "after": null,
                          "before": null
                        }
                      },
                      "user_reports": [

                      ],
                      "saved": false,
                      "id": "d3v8z0e",
                      "gilded": 0,
                      "archived": false,
                      "report_reasons": null,
                      "author": "Defreshs10",
                      "parent_id": "t1_d3v3618",
                      "score": 32,
                      "approved_by": null,
                      "controversiality": 0,
                      "body": "Gilbert checking in. you may notice me from the 11 cop cars that had to pull me over cause there was nothing better to do....",
                      "edited": false,
                      "author_flair_css_class": null,
                      "downs": 0,
                      "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Gilbert checking in. you may notice me from the 11 cop cars that had to pull me over cause there was nothing better to do....&lt;/p&gt;\n&lt;/div&gt;",
                      "subreddit": "funny",
                      "name": "t1_d3v8z0e",
                      "score_hidden": false,
                      "stickied": false,
                      "created": 1465032592.0,
                      "author_flair_text": null,
                      "created_utc": 1465003792.0,
                      "distinguished": null,
                      "mod_reports": [

                      ],
                      "num_reports": null,
                      "ups": 32
                    }
                  },
                  {
                    "kind": "t1",
                    "data": {
                      "subreddit_id": "t5_2qh33",
                      "banned_by": null,
                      "removal_reason": null,
                      "link_id": "t3_4mfawk",
                      "likes": null,
                      "replies": {
                        "kind": "Listing",
                        "data": {
                          "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                          "children": [
                            {
                              "kind": "t1",
                              "data": {
                                "subreddit_id": "t5_2qh33",
                                "banned_by": null,
                                "removal_reason": null,
                                "link_id": "t3_4mfawk",
                                "likes": null,
                                "replies": {
                                  "kind": "Listing",
                                  "data": {
                                    "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                    "children": [
                                      {
                                        "kind": "t1",
                                        "data": {
                                          "subreddit_id": "t5_2qh33",
                                          "banned_by": null,
                                          "removal_reason": null,
                                          "link_id": "t3_4mfawk",
                                          "likes": null,
                                          "replies": {
                                            "kind": "Listing",
                                            "data": {
                                              "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                              "children": [
                                                {
                                                  "kind": "t1",
                                                  "data": {
                                                    "subreddit_id": "t5_2qh33",
                                                    "banned_by": null,
                                                    "removal_reason": null,
                                                    "link_id": "t3_4mfawk",
                                                    "likes": null,
                                                    "replies": {
                                                      "kind": "Listing",
                                                      "data": {
                                                        "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                                        "children": [
                                                          {
                                                            "kind": "more",
                                                            "data": {
                                                              "count": 10,
                                                              "parent_id": "t1_d3v50s0",
                                                              "id": "d3v57fl",
                                                              "name": "t1_d3v57fl",
                                                              "children": [
                                                                "d3v57fl"
                                                              ]
                                                            }
                                                          }
                                                        ],
                                                        "after": null,
                                                        "before": null
                                                      }
                                                    },
                                                    "user_reports": [

                                                    ],
                                                    "saved": false,
                                                    "id": "d3v50s0",
                                                    "gilded": 0,
                                                    "archived": false,
                                                    "report_reasons": null,
                                                    "author": "Warlizard",
                                                    "parent_id": "t1_d3v4jwh",
                                                    "score": 9,
                                                    "approved_by": null,
                                                    "controversiality": 0,
                                                    "body": "If you have kids, go to MMR -- it's pretty fun. Aside from that, Westworld always has something going on. Barrett-Jackson is one of my favorites.\n",
                                                    "edited": false,
                                                    "author_flair_css_class": null,
                                                    "downs": 0,
                                                    "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;If you have kids, go to MMR -- it&amp;#39;s pretty fun. Aside from that, Westworld always has something going on. Barrett-Jackson is one of my favorites.&lt;/p&gt;\n&lt;/div&gt;",
                                                    "subreddit": "funny",
                                                    "name": "t1_d3v50s0",
                                                    "score_hidden": false,
                                                    "stickied": false,
                                                    "created": 1465025724.0,
                                                    "author_flair_text": null,
                                                    "created_utc": 1464996924.0,
                                                    "distinguished": null,
                                                    "mod_reports": [

                                                    ],
                                                    "num_reports": null,
                                                    "ups": 9
                                                  }
                                                },
                                                {
                                                  "kind": "more",
                                                  "data": {
                                                    "count": 5,
                                                    "parent_id": "t1_d3v4jwh",
                                                    "children": [
                                                      "d3v9209",
                                                      "d3vauro",
                                                      "d3va2uc",
                                                      "d3v8yg4"
                                                    ],
                                                    "name": "t1_d3v9209",
                                                    "id": "d3v9209"
                                                  }
                                                }
                                              ],
                                              "after": null,
                                              "before": null
                                            }
                                          },
                                          "user_reports": [

                                          ],
                                          "saved": false,
                                          "id": "d3v4jwh",
                                          "gilded": 0,
                                          "archived": false,
                                          "report_reasons": null,
                                          "author": "puppy_paste",
                                          "parent_id": "t1_d3v46g8",
                                          "score": 8,
                                          "approved_by": null,
                                          "controversiality": 0,
                                          "body": "Nice.  I just moved to the area a couple months ago from Ahwatukee.  What the hell is there around here that's within five minutes?\n\n(Puppies not required.)",
                                          "edited": false,
                                          "author_flair_css_class": null,
                                          "downs": 0,
                                          "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Nice.  I just moved to the area a couple months ago from Ahwatukee.  What the hell is there around here that&amp;#39;s within five minutes?&lt;/p&gt;\n\n&lt;p&gt;(Puppies not required.)&lt;/p&gt;\n&lt;/div&gt;",
                                          "subreddit": "funny",
                                          "name": "t1_d3v4jwh",
                                          "score_hidden": false,
                                          "stickied": false,
                                          "created": 1465024916.0,
                                          "author_flair_text": null,
                                          "created_utc": 1464996116.0,
                                          "distinguished": null,
                                          "mod_reports": [

                                          ],
                                          "num_reports": null,
                                          "ups": 8
                                        }
                                      }
                                    ],
                                    "after": null,
                                    "before": null
                                  }
                                },
                                "user_reports": [

                                ],
                                "saved": false,
                                "id": "d3v46g8",
                                "gilded": 0,
                                "archived": false,
                                "report_reasons": null,
                                "author": "Warlizard",
                                "parent_id": "t1_d3v3u3o",
                                "score": 12,
                                "approved_by": null,
                                "controversiality": 0,
                                "body": "You're about 4 minutes from me. lol",
                                "edited": false,
                                "author_flair_css_class": null,
                                "downs": 0,
                                "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;You&amp;#39;re about 4 minutes from me. lol&lt;/p&gt;\n&lt;/div&gt;",
                                "subreddit": "funny",
                                "name": "t1_d3v46g8",
                                "score_hidden": false,
                                "stickied": false,
                                "created": 1465024273.0,
                                "author_flair_text": null,
                                "created_utc": 1464995473.0,
                                "distinguished": null,
                                "mod_reports": [

                                ],
                                "num_reports": null,
                                "ups": 12
                              }
                            },
                            {
                              "kind": "more",
                              "data": {
                                "count": 3,
                                "parent_id": "t1_d3v3u3o",
                                "children": [
                                  "d3v8pj4",
                                  "d3v8a96",
                                  "d3vbgj6"
                                ],
                                "name": "t1_d3v8pj4",
                                "id": "d3v8pj4"
                              }
                            }
                          ],
                          "after": null,
                          "before": null
                        }
                      },
                      "user_reports": [

                      ],
                      "saved": false,
                      "id": "d3v3u3o",
                      "gilded": 0,
                      "archived": false,
                      "report_reasons": null,
                      "author": "puppy_paste",
                      "parent_id": "t1_d3v3618",
                      "score": 29,
                      "approved_by": null,
                      "controversiality": 0,
                      "body": "McCormick Ranch, represent.  So many adorable puppies in the area.",
                      "edited": false,
                      "author_flair_css_class": null,
                      "downs": 0,
                      "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;McCormick Ranch, represent.  So many adorable puppies in the area.&lt;/p&gt;\n&lt;/div&gt;",
                      "subreddit": "funny",
                      "name": "t1_d3v3u3o",
                      "score_hidden": false,
                      "stickied": false,
                      "created": 1465023686.0,
                      "author_flair_text": null,
                      "created_utc": 1464994886.0,
                      "distinguished": null,
                      "mod_reports": [

                      ],
                      "num_reports": null,
                      "ups": 29
                    }
                  },
                  {
                    "kind": "t1",
                    "data": {
                      "subreddit_id": "t5_2qh33",
                      "banned_by": null,
                      "removal_reason": null,
                      "link_id": "t3_4mfawk",
                      "likes": null,
                      "replies": {
                        "kind": "Listing",
                        "data": {
                          "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                          "children": [
                            {
                              "kind": "t1",
                              "data": {
                                "subreddit_id": "t5_2qh33",
                                "banned_by": null,
                                "removal_reason": null,
                                "link_id": "t3_4mfawk",
                                "likes": null,
                                "replies": {
                                  "kind": "Listing",
                                  "data": {
                                    "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                    "children": [
                                      {
                                        "kind": "more",
                                        "data": {
                                          "count": 2,
                                          "parent_id": "t1_d3v8af9",
                                          "id": "d3vc5kg",
                                          "name": "t1_d3vc5kg",
                                          "children": [
                                            "d3vc5kg",
                                            "d3vagcf"
                                          ]
                                        }
                                      }
                                    ],
                                    "after": null,
                                    "before": null
                                  }
                                },
                                "user_reports": [

                                ],
                                "saved": false,
                                "id": "d3v8af9",
                                "gilded": 0,
                                "archived": false,
                                "report_reasons": null,
                                "author": "Toytles",
                                "parent_id": "t1_d3v7yjt",
                                "score": 22,
                                "approved_by": null,
                                "controversiality": 0,
                                "body": "UofA UofA UofA",
                                "edited": 1465009499.0,
                                "author_flair_css_class": null,
                                "downs": 0,
                                "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;UofA UofA UofA&lt;/p&gt;\n&lt;/div&gt;",
                                "subreddit": "funny",
                                "name": "t1_d3v8af9",
                                "score_hidden": false,
                                "stickied": false,
                                "created": 1465031428.0,
                                "author_flair_text": null,
                                "created_utc": 1465002628.0,
                                "distinguished": null,
                                "mod_reports": [

                                ],
                                "num_reports": null,
                                "ups": 22
                              }
                            },
                            {
                              "kind": "more",
                              "data": {
                                "count": 1,
                                "parent_id": "t1_d3v7yjt",
                                "children": [
                                  "d3vc8ul"
                                ],
                                "name": "t1_d3vc8ul",
                                "id": "d3vc8ul"
                              }
                            }
                          ],
                          "after": null,
                          "before": null
                        }
                      },
                      "user_reports": [

                      ],
                      "saved": false,
                      "id": "d3v7yjt",
                      "gilded": 0,
                      "archived": false,
                      "report_reasons": null,
                      "author": "paysonderulo",
                      "parent_id": "t1_d3v3618",
                      "score": 30,
                      "approved_by": null,
                      "controversiality": 0,
                      "body": "Downtown Tempe checking in.",
                      "edited": false,
                      "author_flair_css_class": null,
                      "downs": 0,
                      "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Downtown Tempe checking in.&lt;/p&gt;\n&lt;/div&gt;",
                      "subreddit": "funny",
                      "name": "t1_d3v7yjt",
                      "score_hidden": false,
                      "stickied": false,
                      "created": 1465030880.0,
                      "author_flair_text": null,
                      "created_utc": 1465002080.0,
                      "distinguished": null,
                      "mod_reports": [

                      ],
                      "num_reports": null,
                      "ups": 30
                    }
                  },
                  {
                    "kind": "more",
                    "data": {
                      "count": 34,
                      "parent_id": "t1_d3v3618",
                      "id": "d3v8401",
                      "name": "t1_d3v8401",
                      "children": [
                        "d3v8401",
                        "d3v8337",
                        "d3v8rwk",
                        "d3vaipi",
                        "d3va11o",
                        "d3v9voc",
                        "d3vcn13",
                        "d3v9lqk",
                        "d3v8fg9",
                        "d3v974g",
                        "d3v9gz7",
                        "d3v9eb8",
                        "d3v8ekj",
                        "d3vasvd",
                        "d3vc7dl",
                        "d3vbqak",
                        "d3v99bx",
                        "d3v8dwe",
                        "d3vcm7z"
                      ]
                    }
                  }
                ],
                "after": null,
                "before": null
              }
            },
            "user_reports": [

            ],
            "saved": false,
            "id": "d3v3618",
            "gilded": 0,
            "archived": false,
            "report_reasons": null,
            "author": "Warlizard",
            "parent_id": "t3_4mfawk",
            "score": 239,
            "approved_by": null,
            "controversiality": 0,
            "body": "And you never call me.  Scottsdale holla.",
            "edited": false,
            "author_flair_css_class": null,
            "downs": 0,
            "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;And you never call me.  Scottsdale holla.&lt;/p&gt;\n&lt;/div&gt;",
            "subreddit": "funny",
            "name": "t1_d3v3618",
            "score_hidden": false,
            "stickied": false,
            "created": 1465022560.0,
            "author_flair_text": null,
            "created_utc": 1464993760.0,
            "distinguished": null,
            "mod_reports": [

            ],
            "num_reports": null,
            "ups": 239
          }
        },
        {
          "kind": "t1",
          "data": {
            "subreddit_id": "t5_2qh33",
            "banned_by": null,
            "removal_reason": null,
            "link_id": "t3_4mfawk",
            "likes": null,
            "replies": {
              "kind": "Listing",
              "data": {
                "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                "children": [
                  {
                    "kind": "t1",
                    "data": {
                      "subreddit_id": "t5_2qh33",
                      "banned_by": null,
                      "removal_reason": null,
                      "link_id": "t3_4mfawk",
                      "likes": null,
                      "replies": {
                        "kind": "Listing",
                        "data": {
                          "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                          "children": [
                            {
                              "kind": "t1",
                              "data": {
                                "subreddit_id": "t5_2qh33",
                                "banned_by": null,
                                "removal_reason": null,
                                "link_id": "t3_4mfawk",
                                "likes": null,
                                "replies": {
                                  "kind": "Listing",
                                  "data": {
                                    "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                    "children": [
                                      {
                                        "kind": "more",
                                        "data": {
                                          "count": 3,
                                          "parent_id": "t1_d3v99ef",
                                          "id": "d3vbm64",
                                          "name": "t1_d3vbm64",
                                          "children": [
                                            "d3vbm64",
                                            "d3vcjbz"
                                          ]
                                        }
                                      }
                                    ],
                                    "after": null,
                                    "before": null
                                  }
                                },
                                "user_reports": [

                                ],
                                "saved": false,
                                "id": "d3v99ef",
                                "gilded": 0,
                                "archived": false,
                                "report_reasons": null,
                                "author": "GoodAtExplaining",
                                "parent_id": "t1_d3v87qi",
                                "score": 26,
                                "approved_by": null,
                                "controversiality": 0,
                                "body": "Humid heat sucks. It's like a sweaty morbidly obese tall man constantly giving you a hug.",
                                "edited": false,
                                "author_flair_css_class": null,
                                "downs": 0,
                                "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Humid heat sucks. It&amp;#39;s like a sweaty morbidly obese tall man constantly giving you a hug.&lt;/p&gt;\n&lt;/div&gt;",
                                "subreddit": "funny",
                                "name": "t1_d3v99ef",
                                "score_hidden": false,
                                "stickied": false,
                                "created": 1465033089.0,
                                "author_flair_text": null,
                                "created_utc": 1465004289.0,
                                "distinguished": null,
                                "mod_reports": [

                                ],
                                "num_reports": null,
                                "ups": 26
                              }
                            },
                            {
                              "kind": "more",
                              "data": {
                                "count": 4,
                                "parent_id": "t1_d3v87qi",
                                "children": [
                                  "d3v9zh5",
                                  "d3v9y3u",
                                  "d3v9skj",
                                  "d3v91g4"
                                ],
                                "name": "t1_d3v9zh5",
                                "id": "d3v9zh5"
                              }
                            }
                          ],
                          "after": null,
                          "before": null
                        }
                      },
                      "user_reports": [

                      ],
                      "saved": false,
                      "id": "d3v87qi",
                      "gilded": 0,
                      "archived": false,
                      "report_reasons": null,
                      "author": "awesometographer",
                      "parent_id": "t1_d3v7nbc",
                      "score": 73,
                      "approved_by": null,
                      "controversiality": 0,
                      "body": "Lived in Augusta/Columbus, GA for 7 years - now in Vegas (very similar to Phoenix)...\n\nI concur. In Georgia, I'd have to shower after walking to the mailbox.",
                      "edited": false,
                      "author_flair_css_class": null,
                      "downs": 0,
                      "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Lived in Augusta/Columbus, GA for 7 years - now in Vegas (very similar to Phoenix)...&lt;/p&gt;\n\n&lt;p&gt;I concur. In Georgia, I&amp;#39;d have to shower after walking to the mailbox.&lt;/p&gt;\n&lt;/div&gt;",
                      "subreddit": "funny",
                      "name": "t1_d3v87qi",
                      "score_hidden": false,
                      "stickied": false,
                      "created": 1465031302.0,
                      "author_flair_text": null,
                      "created_utc": 1465002502.0,
                      "distinguished": null,
                      "mod_reports": [

                      ],
                      "num_reports": null,
                      "ups": 73
                    }
                  },
                  {
                    "kind": "t1",
                    "data": {
                      "subreddit_id": "t5_2qh33",
                      "banned_by": null,
                      "removal_reason": null,
                      "link_id": "t3_4mfawk",
                      "likes": null,
                      "replies": {
                        "kind": "Listing",
                        "data": {
                          "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                          "children": [
                            {
                              "kind": "more",
                              "data": {
                                "count": 1,
                                "parent_id": "t1_d3v8z9y",
                                "children": [
                                  "d3v9dab"
                                ],
                                "name": "t1_d3v9dab",
                                "id": "d3v9dab"
                              }
                            }
                          ],
                          "after": null,
                          "before": null
                        }
                      },
                      "user_reports": [

                      ],
                      "saved": false,
                      "id": "d3v8z9y",
                      "gilded": 0,
                      "archived": false,
                      "report_reasons": null,
                      "author": "Eji1700",
                      "parent_id": "t1_d3v7nbc",
                      "score": 16,
                      "approved_by": null,
                      "controversiality": 0,
                      "body": "I went to philly in something like 90 degrees with 90% humidity and learned two things:\n\n1. Wet heat does not give a fuck about your shade.\n\n2. For some strange reason the entire east coast enjoys boiling to death because every fucking building we went in had air conditioning that would be best described as \"not fucking working\"",
                      "edited": false,
                      "author_flair_css_class": null,
                      "downs": 0,
                      "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;I went to philly in something like 90 degrees with 90% humidity and learned two things:&lt;/p&gt;\n\n&lt;ol&gt;\n&lt;li&gt;&lt;p&gt;Wet heat does not give a fuck about your shade.&lt;/p&gt;&lt;/li&gt;\n&lt;li&gt;&lt;p&gt;For some strange reason the entire east coast enjoys boiling to death because every fucking building we went in had air conditioning that would be best described as &amp;quot;not fucking working&amp;quot;&lt;/p&gt;&lt;/li&gt;\n&lt;/ol&gt;\n&lt;/div&gt;",
                      "subreddit": "funny",
                      "name": "t1_d3v8z9y",
                      "score_hidden": false,
                      "stickied": false,
                      "created": 1465032606.0,
                      "author_flair_text": null,
                      "created_utc": 1465003806.0,
                      "distinguished": null,
                      "mod_reports": [

                      ],
                      "num_reports": null,
                      "ups": 16
                    }
                  },
                  {
                    "kind": "t1",
                    "data": {
                      "subreddit_id": "t5_2qh33",
                      "banned_by": null,
                      "removal_reason": null,
                      "link_id": "t3_4mfawk",
                      "likes": null,
                      "replies": {
                        "kind": "Listing",
                        "data": {
                          "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                          "children": [
                            {
                              "kind": "more",
                              "data": {
                                "count": 6,
                                "parent_id": "t1_d3v8em1",
                                "children": [
                                  "d3v9tzo",
                                  "d3v9dg5",
                                  "d3va9we"
                                ],
                                "name": "t1_d3v9tzo",
                                "id": "d3v9tzo"
                              }
                            }
                          ],
                          "after": null,
                          "before": null
                        }
                      },
                      "user_reports": [

                      ],
                      "saved": false,
                      "id": "d3v8em1",
                      "gilded": 0,
                      "archived": false,
                      "report_reasons": null,
                      "author": "methfish",
                      "parent_id": "t1_d3v7nbc",
                      "score": 11,
                      "approved_by": null,
                      "controversiality": 0,
                      "body": "I'm in VA and we get rain that warms the air up so its like 95 and humid and the parts of you not sweating are being rained on. I just don't get it. Growing up in the midwest, rain brought cooler weather, it didn't raise the temperature a couple degrees while raining.",
                      "edited": false,
                      "author_flair_css_class": null,
                      "downs": 0,
                      "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;I&amp;#39;m in VA and we get rain that warms the air up so its like 95 and humid and the parts of you not sweating are being rained on. I just don&amp;#39;t get it. Growing up in the midwest, rain brought cooler weather, it didn&amp;#39;t raise the temperature a couple degrees while raining.&lt;/p&gt;\n&lt;/div&gt;",
                      "subreddit": "funny",
                      "name": "t1_d3v8em1",
                      "score_hidden": false,
                      "stickied": false,
                      "created": 1465031617.0,
                      "author_flair_text": null,
                      "created_utc": 1465002817.0,
                      "distinguished": null,
                      "mod_reports": [

                      ],
                      "num_reports": null,
                      "ups": 11
                    }
                  },
                  {
                    "kind": "more",
                    "data": {
                      "count": 22,
                      "parent_id": "t1_d3v7nbc",
                      "id": "d3vbeaq",
                      "name": "t1_d3vbeaq",
                      "children": [
                        "d3vbeaq",
                        "d3vbzl0",
                        "d3v9yhy",
                        "d3v9r2e",
                        "d3vai35",
                        "d3vbnk8",
                        "d3v9l55",
                        "d3v9v6y",
                        "d3v9byt",
                        "d3v9mod",
                        "d3vba4u",
                        "d3v9mbh",
                        "d3v8shd",
                        "d3v9hde",
                        "d3vbvlq",
                        "d3v8iy1",
                        "d3v9jdk",
                        "d3vbyl6",
                        "d3vcbnh",
                        "d3vby4e",
                        "d3v95b2"
                      ]
                    }
                  }
                ],
                "after": null,
                "before": null
              }
            },
            "user_reports": [

            ],
            "saved": false,
            "id": "d3v7nbc",
            "gilded": 0,
            "archived": false,
            "report_reasons": null,
            "author": "jakematheny83",
            "parent_id": "t3_4mfawk",
            "score": 206,
            "approved_by": null,
            "controversiality": 0,
            "body": "As someone from Georgia visiting Phoenix, 116 is hot, but I still don't think it's as bad as 98 degrees with 95% humidity.",
            "edited": false,
            "author_flair_css_class": null,
            "downs": 0,
            "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;As someone from Georgia visiting Phoenix, 116 is hot, but I still don&amp;#39;t think it&amp;#39;s as bad as 98 degrees with 95% humidity.&lt;/p&gt;\n&lt;/div&gt;",
            "subreddit": "funny",
            "name": "t1_d3v7nbc",
            "score_hidden": false,
            "stickied": false,
            "created": 1465030334.0,
            "author_flair_text": null,
            "created_utc": 1465001534.0,
            "distinguished": null,
            "mod_reports": [

            ],
            "num_reports": null,
            "ups": 206
          }
        },
        {
          "kind": "t1",
          "data": {
            "subreddit_id": "t5_2qh33",
            "banned_by": null,
            "removal_reason": null,
            "link_id": "t3_4mfawk",
            "likes": null,
            "replies": {
              "kind": "Listing",
              "data": {
                "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                "children": [
                  {
                    "kind": "t1",
                    "data": {
                      "subreddit_id": "t5_2qh33",
                      "banned_by": null,
                      "removal_reason": null,
                      "link_id": "t3_4mfawk",
                      "likes": null,
                      "replies": {
                        "kind": "Listing",
                        "data": {
                          "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                          "children": [
                            {
                              "kind": "t1",
                              "data": {
                                "subreddit_id": "t5_2qh33",
                                "banned_by": null,
                                "removal_reason": null,
                                "link_id": "t3_4mfawk",
                                "likes": null,
                                "replies": {
                                  "kind": "Listing",
                                  "data": {
                                    "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                    "children": [
                                      {
                                        "kind": "t1",
                                        "data": {
                                          "subreddit_id": "t5_2qh33",
                                          "banned_by": null,
                                          "removal_reason": null,
                                          "link_id": "t3_4mfawk",
                                          "likes": null,
                                          "replies": {
                                            "kind": "Listing",
                                            "data": {
                                              "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                              "children": [
                                                {
                                                  "kind": "t1",
                                                  "data": {
                                                    "subreddit_id": "t5_2qh33",
                                                    "banned_by": null,
                                                    "removal_reason": null,
                                                    "link_id": "t3_4mfawk",
                                                    "likes": null,
                                                    "replies": {
                                                      "kind": "Listing",
                                                      "data": {
                                                        "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                                        "children": [
                                                          {
                                                            "kind": "more",
                                                            "data": {
                                                              "count": 3,
                                                              "parent_id": "t1_d3v9cme",
                                                              "id": "d3vahjs",
                                                              "name": "t1_d3vahjs",
                                                              "children": [
                                                                "d3vahjs",
                                                                "d3vchrp",
                                                                "d3vbzwf"
                                                              ]
                                                            }
                                                          }
                                                        ],
                                                        "after": null,
                                                        "before": null
                                                      }
                                                    },
                                                    "user_reports": [

                                                    ],
                                                    "saved": false,
                                                    "id": "d3v9cme",
                                                    "gilded": 0,
                                                    "archived": false,
                                                    "report_reasons": null,
                                                    "author": "thephoenixx",
                                                    "parent_id": "t1_d3v8tvr",
                                                    "score": 19,
                                                    "approved_by": null,
                                                    "controversiality": 0,
                                                    "body": "I should have, as a kid. Ran to the mailbox and back barefooted in 115+ weather, was out of commission for a couple days.",
                                                    "edited": false,
                                                    "author_flair_css_class": null,
                                                    "downs": 0,
                                                    "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;I should have, as a kid. Ran to the mailbox and back barefooted in 115+ weather, was out of commission for a couple days.&lt;/p&gt;\n&lt;/div&gt;",
                                                    "subreddit": "funny",
                                                    "name": "t1_d3v9cme",
                                                    "score_hidden": false,
                                                    "stickied": false,
                                                    "created": 1465033247.0,
                                                    "author_flair_text": null,
                                                    "created_utc": 1465004447.0,
                                                    "distinguished": null,
                                                    "mod_reports": [

                                                    ],
                                                    "num_reports": null,
                                                    "ups": 19
                                                  }
                                                },
                                                {
                                                  "kind": "t1",
                                                  "data": {
                                                    "subreddit_id": "t5_2qh33",
                                                    "banned_by": null,
                                                    "removal_reason": null,
                                                    "link_id": "t3_4mfawk",
                                                    "likes": null,
                                                    "replies": {
                                                      "kind": "Listing",
                                                      "data": {
                                                        "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                                        "children": [
                                                          {
                                                            "kind": "more",
                                                            "data": {
                                                              "count": 2,
                                                              "parent_id": "t1_d3v9gb1",
                                                              "id": "d3vaf5z",
                                                              "name": "t1_d3vaf5z",
                                                              "children": [
                                                                "d3vaf5z"
                                                              ]
                                                            }
                                                          }
                                                        ],
                                                        "after": null,
                                                        "before": null
                                                      }
                                                    },
                                                    "user_reports": [

                                                    ],
                                                    "saved": false,
                                                    "id": "d3v9gb1",
                                                    "gilded": 0,
                                                    "archived": false,
                                                    "report_reasons": null,
                                                    "author": "TangerineDiesel",
                                                    "parent_id": "t1_d3v8tvr",
                                                    "score": 11,
                                                    "approved_by": null,
                                                    "controversiality": 0,
                                                    "body": "When I was a kid walking to the cul-de-sac's pool we'd just jump around in shaded grass like Mario after he touches lava in one of the recent games.",
                                                    "edited": false,
                                                    "author_flair_css_class": null,
                                                    "downs": 0,
                                                    "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;When I was a kid walking to the cul-de-sac&amp;#39;s pool we&amp;#39;d just jump around in shaded grass like Mario after he touches lava in one of the recent games.&lt;/p&gt;\n&lt;/div&gt;",
                                                    "subreddit": "funny",
                                                    "name": "t1_d3v9gb1",
                                                    "score_hidden": false,
                                                    "stickied": false,
                                                    "created": 1465033423.0,
                                                    "author_flair_text": null,
                                                    "created_utc": 1465004623.0,
                                                    "distinguished": null,
                                                    "mod_reports": [

                                                    ],
                                                    "num_reports": null,
                                                    "ups": 11
                                                  }
                                                },
                                                {
                                                  "kind": "more",
                                                  "data": {
                                                    "count": 6,
                                                    "parent_id": "t1_d3v8tvr",
                                                    "children": [
                                                      "d3vbaoj",
                                                      "d3vcgm5",
                                                      "d3v94ym",
                                                      "d3v9h31"
                                                    ],
                                                    "name": "t1_d3vbaoj",
                                                    "id": "d3vbaoj"
                                                  }
                                                }
                                              ],
                                              "after": null,
                                              "before": null
                                            }
                                          },
                                          "user_reports": [

                                          ],
                                          "saved": false,
                                          "id": "d3v8tvr",
                                          "gilded": 0,
                                          "archived": false,
                                          "report_reasons": null,
                                          "author": "TheHairyManrilla",
                                          "parent_id": "t1_d3v8407",
                                          "score": 8,
                                          "approved_by": null,
                                          "controversiality": 0,
                                          "body": "Do people actually go to the hospital for that?",
                                          "edited": false,
                                          "author_flair_css_class": null,
                                          "downs": 0,
                                          "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Do people actually go to the hospital for that?&lt;/p&gt;\n&lt;/div&gt;",
                                          "subreddit": "funny",
                                          "name": "t1_d3v8tvr",
                                          "score_hidden": false,
                                          "stickied": false,
                                          "created": 1465032342.0,
                                          "author_flair_text": null,
                                          "created_utc": 1465003542.0,
                                          "distinguished": null,
                                          "mod_reports": [

                                          ],
                                          "num_reports": null,
                                          "ups": 8
                                        }
                                      },
                                      {
                                        "kind": "more",
                                        "data": {
                                          "count": 11,
                                          "parent_id": "t1_d3v8407",
                                          "id": "d3v9jab",
                                          "name": "t1_d3v9jab",
                                          "children": [
                                            "d3v9jab",
                                            "d3vbxl0",
                                            "d3v9dom",
                                            "d3vc3td",
                                            "d3vaobb",
                                            "d3vaxco"
                                          ]
                                        }
                                      }
                                    ],
                                    "after": null,
                                    "before": null
                                  }
                                },
                                "user_reports": [

                                ],
                                "saved": false,
                                "id": "d3v8407",
                                "gilded": 0,
                                "archived": false,
                                "report_reasons": null,
                                "author": "GreatOwl1",
                                "parent_id": "t1_d3v7v0v",
                                "score": 127,
                                "approved_by": null,
                                "controversiality": 0,
                                "body": "Yes. If you walk barefoot on the asphalt you can fry your feet, too.",
                                "edited": false,
                                "author_flair_css_class": null,
                                "downs": 0,
                                "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Yes. If you walk barefoot on the asphalt you can fry your feet, too.&lt;/p&gt;\n&lt;/div&gt;",
                                "subreddit": "funny",
                                "name": "t1_d3v8407",
                                "score_hidden": false,
                                "stickied": false,
                                "created": 1465031130.0,
                                "author_flair_text": null,
                                "created_utc": 1465002330.0,
                                "distinguished": null,
                                "mod_reports": [

                                ],
                                "num_reports": null,
                                "ups": 127
                              }
                            },
                            {
                              "kind": "t1",
                              "data": {
                                "subreddit_id": "t5_2qh33",
                                "banned_by": null,
                                "removal_reason": null,
                                "link_id": "t3_4mfawk",
                                "likes": null,
                                "replies": {
                                  "kind": "Listing",
                                  "data": {
                                    "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                    "children": [
                                      {
                                        "kind": "more",
                                        "data": {
                                          "count": 4,
                                          "parent_id": "t1_d3v8g1h",
                                          "id": "d3v9r6b",
                                          "name": "t1_d3v9r6b",
                                          "children": [
                                            "d3v9r6b"
                                          ]
                                        }
                                      }
                                    ],
                                    "after": null,
                                    "before": null
                                  }
                                },
                                "user_reports": [

                                ],
                                "saved": false,
                                "id": "d3v8g1h",
                                "gilded": 0,
                                "archived": false,
                                "report_reasons": null,
                                "author": "JerrSolo",
                                "parent_id": "t1_d3v7v0v",
                                "score": 36,
                                "approved_by": null,
                                "controversiality": 0,
                                "body": "Only as a joke. The sidewalk will burn your feet, but it doesn't actually get hot enough to cook a steak.\n\nEdit: If you set a frying pan out in the sun, it probably would get hot enough to fry an egg, but I doubt a steak would work. As soon as it hits the pan, the steak will cool the area it rests on, and without the sun hitting that spot the pan will probably not remain hot enough.",
                                "edited": 1465004540.0,
                                "author_flair_css_class": null,
                                "downs": 0,
                                "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Only as a joke. The sidewalk will burn your feet, but it doesn&amp;#39;t actually get hot enough to cook a steak.&lt;/p&gt;\n\n&lt;p&gt;Edit: If you set a frying pan out in the sun, it probably would get hot enough to fry an egg, but I doubt a steak would work. As soon as it hits the pan, the steak will cool the area it rests on, and without the sun hitting that spot the pan will probably not remain hot enough.&lt;/p&gt;\n&lt;/div&gt;",
                                "subreddit": "funny",
                                "name": "t1_d3v8g1h",
                                "score_hidden": false,
                                "stickied": false,
                                "created": 1465031682.0,
                                "author_flair_text": null,
                                "created_utc": 1465002882.0,
                                "distinguished": null,
                                "mod_reports": [

                                ],
                                "num_reports": null,
                                "ups": 36
                              }
                            },
                            {
                              "kind": "t1",
                              "data": {
                                "subreddit_id": "t5_2qh33",
                                "banned_by": null,
                                "removal_reason": null,
                                "link_id": "t3_4mfawk",
                                "likes": null,
                                "replies": {
                                  "kind": "Listing",
                                  "data": {
                                    "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                    "children": [
                                      {
                                        "kind": "t1",
                                        "data": {
                                          "subreddit_id": "t5_2qh33",
                                          "banned_by": null,
                                          "removal_reason": null,
                                          "link_id": "t3_4mfawk",
                                          "likes": null,
                                          "replies": {
                                            "kind": "Listing",
                                            "data": {
                                              "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                              "children": [
                                                {
                                                  "kind": "more",
                                                  "data": {
                                                    "count": 2,
                                                    "parent_id": "t1_d3v8f4p",
                                                    "children": [
                                                      "d3v8nku"
                                                    ],
                                                    "name": "t1_d3v8nku",
                                                    "id": "d3v8nku"
                                                  }
                                                }
                                              ],
                                              "after": null,
                                              "before": null
                                            }
                                          },
                                          "user_reports": [

                                          ],
                                          "saved": false,
                                          "id": "d3v8f4p",
                                          "gilded": 0,
                                          "archived": false,
                                          "report_reasons": null,
                                          "author": "MrDTD",
                                          "parent_id": "t1_d3v888i",
                                          "score": 32,
                                          "approved_by": null,
                                          "controversiality": 0,
                                          "body": "You could put out a cast iron pan for the same effect and actually eat from it.",
                                          "edited": false,
                                          "author_flair_css_class": null,
                                          "downs": 0,
                                          "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;You could put out a cast iron pan for the same effect and actually eat from it.&lt;/p&gt;\n&lt;/div&gt;",
                                          "subreddit": "funny",
                                          "name": "t1_d3v8f4p",
                                          "score_hidden": false,
                                          "stickied": false,
                                          "created": 1465031641.0,
                                          "author_flair_text": null,
                                          "created_utc": 1465002841.0,
                                          "distinguished": null,
                                          "mod_reports": [

                                          ],
                                          "num_reports": null,
                                          "ups": 32
                                        }
                                      },
                                      {
                                        "kind": "more",
                                        "data": {
                                          "count": 2,
                                          "parent_id": "t1_d3v888i",
                                          "id": "d3v8qxc",
                                          "name": "t1_d3v8qxc",
                                          "children": [
                                            "d3v8qxc",
                                            "d3v8l01"
                                          ]
                                        }
                                      }
                                    ],
                                    "after": null,
                                    "before": null
                                  }
                                },
                                "user_reports": [

                                ],
                                "saved": false,
                                "id": "d3v888i",
                                "gilded": 0,
                                "archived": false,
                                "report_reasons": null,
                                "author": "robkabob",
                                "parent_id": "t1_d3v7v0v",
                                "score": 22,
                                "approved_by": null,
                                "controversiality": 0,
                                "body": "Hygienically, no. But it is actually possible. ",
                                "edited": false,
                                "author_flair_css_class": null,
                                "downs": 0,
                                "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Hygienically, no. But it is actually possible. &lt;/p&gt;\n&lt;/div&gt;",
                                "subreddit": "funny",
                                "name": "t1_d3v888i",
                                "score_hidden": false,
                                "stickied": false,
                                "created": 1465031324.0,
                                "author_flair_text": null,
                                "created_utc": 1465002524.0,
                                "distinguished": null,
                                "mod_reports": [

                                ],
                                "num_reports": null,
                                "ups": 22
                              }
                            },
                            {
                              "kind": "t1",
                              "data": {
                                "subreddit_id": "t5_2qh33",
                                "banned_by": null,
                                "removal_reason": null,
                                "link_id": "t3_4mfawk",
                                "likes": null,
                                "replies": "",
                                "user_reports": [

                                ],
                                "saved": false,
                                "id": "d3v8gnd",
                                "gilded": 0,
                                "archived": false,
                                "report_reasons": null,
                                "author": "SquirtLikeABoss",
                                "parent_id": "t1_d3v7v0v",
                                "score": 17,
                                "approved_by": null,
                                "controversiality": 0,
                                "body": "Yes when you're in downtown Tempe you can see the hipster bums cooking all types of meals on the pavement ",
                                "edited": false,
                                "author_flair_css_class": null,
                                "downs": 0,
                                "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Yes when you&amp;#39;re in downtown Tempe you can see the hipster bums cooking all types of meals on the pavement &lt;/p&gt;\n&lt;/div&gt;",
                                "subreddit": "funny",
                                "name": "t1_d3v8gnd",
                                "score_hidden": false,
                                "stickied": false,
                                "created": 1465031711.0,
                                "author_flair_text": null,
                                "created_utc": 1465002911.0,
                                "distinguished": null,
                                "mod_reports": [

                                ],
                                "num_reports": null,
                                "ups": 17
                              }
                            },
                            {
                              "kind": "t1",
                              "data": {
                                "subreddit_id": "t5_2qh33",
                                "banned_by": null,
                                "removal_reason": null,
                                "link_id": "t3_4mfawk",
                                "likes": null,
                                "replies": "",
                                "user_reports": [

                                ],
                                "saved": false,
                                "id": "d3v9953",
                                "gilded": 0,
                                "archived": false,
                                "report_reasons": null,
                                "author": "smartcookiecrumbles",
                                "parent_id": "t1_d3v7v0v",
                                "score": 15,
                                "approved_by": null,
                                "controversiality": 0,
                                "body": "I have a friend who's made car cookies several summers in a row. Just cooked em right in the car, sitting in the sun. ",
                                "edited": false,
                                "author_flair_css_class": null,
                                "downs": 0,
                                "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;I have a friend who&amp;#39;s made car cookies several summers in a row. Just cooked em right in the car, sitting in the sun. &lt;/p&gt;\n&lt;/div&gt;",
                                "subreddit": "funny",
                                "name": "t1_d3v9953",
                                "score_hidden": false,
                                "stickied": false,
                                "created": 1465033075.0,
                                "author_flair_text": null,
                                "created_utc": 1465004275.0,
                                "distinguished": null,
                                "mod_reports": [

                                ],
                                "num_reports": null,
                                "ups": 15
                              }
                            },
                            {
                              "kind": "more",
                              "data": {
                                "count": 4,
                                "parent_id": "t1_d3v7v0v",
                                "children": [
                                  "d3vcea9",
                                  "d3vcfeb",
                                  "d3vclqe",
                                  "d3vccpi"
                                ],
                                "name": "t1_d3vcea9",
                                "id": "d3vcea9"
                              }
                            }
                          ],
                          "after": null,
                          "before": null
                        }
                      },
                      "user_reports": [

                      ],
                      "saved": false,
                      "id": "d3v7v0v",
                      "gilded": 0,
                      "archived": false,
                      "report_reasons": null,
                      "author": "starrynyght",
                      "parent_id": "t1_d3v2w1r",
                      "score": 36,
                      "approved_by": null,
                      "controversiality": 0,
                      "body": "Do people actually do that?",
                      "edited": false,
                      "author_flair_css_class": null,
                      "downs": 0,
                      "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Do people actually do that?&lt;/p&gt;\n&lt;/div&gt;",
                      "subreddit": "funny",
                      "name": "t1_d3v7v0v",
                      "score_hidden": false,
                      "stickied": false,
                      "created": 1465030708.0,
                      "author_flair_text": null,
                      "created_utc": 1465001908.0,
                      "distinguished": null,
                      "mod_reports": [

                      ],
                      "num_reports": null,
                      "ups": 36
                    }
                  },
                  {
                    "kind": "more",
                    "data": {
                      "count": 6,
                      "parent_id": "t1_d3v2w1r",
                      "id": "d3v8x48",
                      "name": "t1_d3v8x48",
                      "children": [
                        "d3v8x48",
                        "d3v8q3m",
                        "d3vb077",
                        "d3vccvw",
                        "d3v8beu"
                      ]
                    }
                  }
                ],
                "after": null,
                "before": null
              }
            },
            "user_reports": [

            ],
            "saved": false,
            "id": "d3v2w1r",
            "gilded": 0,
            "archived": false,
            "report_reasons": null,
            "author": "bjconnoisseur",
            "parent_id": "t3_4mfawk",
            "score": 124,
            "approved_by": null,
            "controversiality": 0,
            "body": "Summer is coming, I'm sure you will also start to see eggs frying and steaks cooking on the pavement from AZ soon.",
            "edited": false,
            "author_flair_css_class": null,
            "downs": 0,
            "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Summer is coming, I&amp;#39;m sure you will also start to see eggs frying and steaks cooking on the pavement from AZ soon.&lt;/p&gt;\n&lt;/div&gt;",
            "subreddit": "funny",
            "name": "t1_d3v2w1r",
            "score_hidden": false,
            "stickied": false,
            "created": 1465022097.0,
            "author_flair_text": null,
            "created_utc": 1464993297.0,
            "distinguished": null,
            "mod_reports": [

            ],
            "num_reports": null,
            "ups": 124
          }
        },
        {
          "kind": "t1",
          "data": {
            "subreddit_id": "t5_2qh33",
            "banned_by": null,
            "removal_reason": null,
            "link_id": "t3_4mfawk",
            "likes": null,
            "replies": {
              "kind": "Listing",
              "data": {
                "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                "children": [
                  {
                    "kind": "t1",
                    "data": {
                      "subreddit_id": "t5_2qh33",
                      "banned_by": null,
                      "removal_reason": null,
                      "link_id": "t3_4mfawk",
                      "likes": null,
                      "replies": {
                        "kind": "Listing",
                        "data": {
                          "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                          "children": [
                            {
                              "kind": "t1",
                              "data": {
                                "subreddit_id": "t5_2qh33",
                                "banned_by": null,
                                "removal_reason": null,
                                "link_id": "t3_4mfawk",
                                "likes": null,
                                "replies": {
                                  "kind": "Listing",
                                  "data": {
                                    "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                    "children": [
                                      {
                                        "kind": "more",
                                        "data": {
                                          "count": 12,
                                          "parent_id": "t1_d3v8w1p",
                                          "id": "d3vad20",
                                          "name": "t1_d3vad20",
                                          "children": [
                                            "d3vad20",
                                            "d3vb7kb",
                                            "d3vccf8",
                                            "d3v9jmu",
                                            "d3vbml3"
                                          ]
                                        }
                                      }
                                    ],
                                    "after": null,
                                    "before": null
                                  }
                                },
                                "user_reports": [

                                ],
                                "saved": false,
                                "id": "d3v8w1p",
                                "gilded": 0,
                                "archived": false,
                                "report_reasons": null,
                                "author": "Plays-one-on-TV",
                                "parent_id": "t1_d3v8o8b",
                                "score": 10,
                                "approved_by": null,
                                "controversiality": 0,
                                "body": "From MO here. Always liked the idea of CO but assumed it was even colder. Is it not?",
                                "edited": false,
                                "author_flair_css_class": null,
                                "downs": 0,
                                "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;From MO here. Always liked the idea of CO but assumed it was even colder. Is it not?&lt;/p&gt;\n&lt;/div&gt;",
                                "subreddit": "funny",
                                "name": "t1_d3v8w1p",
                                "score_hidden": false,
                                "stickied": false,
                                "created": 1465032448.0,
                                "author_flair_text": null,
                                "created_utc": 1465003648.0,
                                "distinguished": null,
                                "mod_reports": [

                                ],
                                "num_reports": null,
                                "ups": 10
                              }
                            },
                            {
                              "kind": "more",
                              "data": {
                                "count": 64,
                                "parent_id": "t1_d3v8o8b",
                                "children": [
                                  "d3vbpcr",
                                  "d3vbu79",
                                  "d3vc9if",
                                  "d3vam94",
                                  "d3v9oa5",
                                  "d3v8ur2",
                                  "d3v9bcl",
                                  "d3vbakf",
                                  "d3v9rr4",
                                  "d3v95cj",
                                  "d3vbb2s",
                                  "d3vbgm1",
                                  "d3vcixn",
                                  "d3vacbh",
                                  "d3vb1sy",
                                  "d3vbzag",
                                  "d3vay25",
                                  "d3v9ey7",
                                  "d3vaqp4",
                                  "d3v9u4f",
                                  "d3v8xdp",
                                  "d3vacce",
                                  "d3vci67"
                                ],
                                "name": "t1_d3vbpcr",
                                "id": "d3vbpcr"
                              }
                            }
                          ],
                          "after": null,
                          "before": null
                        }
                      },
                      "user_reports": [

                      ],
                      "saved": false,
                      "id": "d3v8o8b",
                      "gilded": 0,
                      "archived": false,
                      "report_reasons": null,
                      "author": "Redditors_Cat",
                      "parent_id": "t1_d3v3dbk",
                      "score": 43,
                      "approved_by": null,
                      "controversiality": 0,
                      "body": "But....but....that's only half the year!  Here in colorado the weather is perfect YEAR ROUND.  as long as you don't mind some snow,  that is.  I fucking love the cool mild temps, but being from Chicago / the Midwest I may be a bit biased.    (There we had absolutely bitter teeth-hurt cold in winter AND blistering heat 100% humidity in summer)\n\n",
                      "edited": 1465005012.0,
                      "author_flair_css_class": null,
                      "downs": 0,
                      "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;But....but....that&amp;#39;s only half the year!  Here in colorado the weather is perfect YEAR ROUND.  as long as you don&amp;#39;t mind some snow,  that is.  I fucking love the cool mild temps, but being from Chicago / the Midwest I may be a bit biased.    (There we had absolutely bitter teeth-hurt cold in winter AND blistering heat 100% humidity in summer)&lt;/p&gt;\n&lt;/div&gt;",
                      "subreddit": "funny",
                      "name": "t1_d3v8o8b",
                      "score_hidden": false,
                      "stickied": false,
                      "created": 1465032069.0,
                      "author_flair_text": null,
                      "created_utc": 1465003269.0,
                      "distinguished": null,
                      "mod_reports": [

                      ],
                      "num_reports": null,
                      "ups": 43
                    }
                  },
                  {
                    "kind": "more",
                    "data": {
                      "count": 24,
                      "parent_id": "t1_d3v3dbk",
                      "id": "d3vblxc",
                      "name": "t1_d3vblxc",
                      "children": [
                        "d3vblxc",
                        "d3v8udf",
                        "d3vam7k",
                        "d3v99bl",
                        "d3vbx2x",
                        "d3v8sex",
                        "d3vbhq4"
                      ]
                    }
                  }
                ],
                "after": null,
                "before": null
              }
            },
            "user_reports": [

            ],
            "saved": false,
            "id": "d3v3dbk",
            "gilded": 0,
            "archived": false,
            "report_reasons": null,
            "author": "SisyphusSmiled",
            "parent_id": "t3_4mfawk",
            "score": 103,
            "approved_by": null,
            "controversiality": 0,
            "body": "We live here because the weather from November through April is *perfect*.",
            "edited": false,
            "author_flair_css_class": null,
            "downs": 0,
            "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;We live here because the weather from November through April is &lt;em&gt;perfect&lt;/em&gt;.&lt;/p&gt;\n&lt;/div&gt;",
            "subreddit": "funny",
            "name": "t1_d3v3dbk",
            "score_hidden": false,
            "stickied": false,
            "created": 1465022903.0,
            "author_flair_text": null,
            "created_utc": 1464994103.0,
            "distinguished": null,
            "mod_reports": [

            ],
            "num_reports": null,
            "ups": 103
          }
        },
        {
          "kind": "t1",
          "data": {
            "subreddit_id": "t5_2qh33",
            "banned_by": null,
            "removal_reason": null,
            "link_id": "t3_4mfawk",
            "likes": null,
            "replies": {
              "kind": "Listing",
              "data": {
                "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                "children": [
                  {
                    "kind": "t1",
                    "data": {
                      "subreddit_id": "t5_2qh33",
                      "banned_by": null,
                      "removal_reason": null,
                      "link_id": "t3_4mfawk",
                      "likes": null,
                      "replies": "",
                      "user_reports": [

                      ],
                      "saved": false,
                      "id": "d3v96de",
                      "gilded": 0,
                      "archived": false,
                      "report_reasons": null,
                      "author": "kittycuddler",
                      "parent_id": "t1_d3v8fcr",
                      "score": 9,
                      "approved_by": null,
                      "controversiality": 0,
                      "body": "Source for Cntrl+F'ers.\nAnd thanks!",
                      "edited": false,
                      "author_flair_css_class": null,
                      "downs": 0,
                      "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Source for Cntrl+F&amp;#39;ers.\nAnd thanks!&lt;/p&gt;\n&lt;/div&gt;",
                      "subreddit": "funny",
                      "name": "t1_d3v96de",
                      "score_hidden": false,
                      "stickied": false,
                      "created": 1465032945.0,
                      "author_flair_text": null,
                      "created_utc": 1465004145.0,
                      "distinguished": null,
                      "mod_reports": [

                      ],
                      "num_reports": null,
                      "ups": 9
                    }
                  },
                  {
                    "kind": "more",
                    "data": {
                      "count": 1,
                      "parent_id": "t1_d3v8fcr",
                      "id": "d3vc915",
                      "name": "t1_d3vc915",
                      "children": [
                        "d3vc915"
                      ]
                    }
                  }
                ],
                "after": null,
                "before": null
              }
            },
            "user_reports": [

            ],
            "saved": false,
            "id": "d3v8fcr",
            "gilded": 0,
            "archived": false,
            "report_reasons": null,
            "author": "mystupidaccountname",
            "parent_id": "t3_4mfawk",
            "score": 64,
            "approved_by": null,
            "controversiality": 0,
            "body": "Here is the video https://www.youtube.com/watch?v=4PYt0SDnrBE",
            "edited": false,
            "author_flair_css_class": null,
            "downs": 0,
            "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Here is the video &lt;a href=\"https://www.youtube.com/watch?v=4PYt0SDnrBE\"&gt;https://www.youtube.com/watch?v=4PYt0SDnrBE&lt;/a&gt;&lt;/p&gt;\n&lt;/div&gt;",
            "subreddit": "funny",
            "name": "t1_d3v8fcr",
            "score_hidden": false,
            "stickied": false,
            "created": 1465031652.0,
            "author_flair_text": null,
            "created_utc": 1465002852.0,
            "distinguished": null,
            "mod_reports": [

            ],
            "num_reports": null,
            "ups": 64
          }
        },
        {
          "kind": "t1",
          "data": {
            "subreddit_id": "t5_2qh33",
            "banned_by": null,
            "removal_reason": null,
            "link_id": "t3_4mfawk",
            "likes": null,
            "replies": {
              "kind": "Listing",
              "data": {
                "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                "children": [
                  {
                    "kind": "more",
                    "data": {
                      "count": 14,
                      "parent_id": "t1_d3v7h99",
                      "id": "d3vaxi8",
                      "name": "t1_d3vaxi8",
                      "children": [
                        "d3vaxi8",
                        "d3vb0rv",
                        "d3vaf53",
                        "d3v8zxk",
                        "d3vaadn",
                        "d3v9upf",
                        "d3va53u",
                        "d3v93lp"
                      ]
                    }
                  }
                ],
                "after": null,
                "before": null
              }
            },
            "user_reports": [

            ],
            "saved": false,
            "id": "d3v7h99",
            "gilded": 0,
            "archived": false,
            "report_reasons": null,
            "author": "Vikt22",
            "parent_id": "t3_4mfawk",
            "score": 55,
            "approved_by": null,
            "controversiality": 0,
            "body": "As someone who has experienced many humid low-mid ninety Wisconson days and many 110+ dry Arizona days, I would take the Arizona one every single time.",
            "edited": false,
            "author_flair_css_class": null,
            "downs": 0,
            "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;As someone who has experienced many humid low-mid ninety Wisconson days and many 110+ dry Arizona days, I would take the Arizona one every single time.&lt;/p&gt;\n&lt;/div&gt;",
            "subreddit": "funny",
            "name": "t1_d3v7h99",
            "score_hidden": false,
            "stickied": false,
            "created": 1465030042.0,
            "author_flair_text": null,
            "created_utc": 1465001242.0,
            "distinguished": null,
            "mod_reports": [

            ],
            "num_reports": null,
            "ups": 55
          }
        },
        {
          "kind": "t1",
          "data": {
            "subreddit_id": "t5_2qh33",
            "banned_by": null,
            "removal_reason": null,
            "link_id": "t3_4mfawk",
            "likes": null,
            "replies": {
              "kind": "Listing",
              "data": {
                "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                "children": [
                  {
                    "kind": "t1",
                    "data": {
                      "subreddit_id": "t5_2qh33",
                      "banned_by": null,
                      "removal_reason": null,
                      "link_id": "t3_4mfawk",
                      "likes": null,
                      "replies": {
                        "kind": "Listing",
                        "data": {
                          "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                          "children": [
                            {
                              "kind": "t1",
                              "data": {
                                "subreddit_id": "t5_2qh33",
                                "banned_by": null,
                                "removal_reason": null,
                                "link_id": "t3_4mfawk",
                                "likes": null,
                                "replies": {
                                  "kind": "Listing",
                                  "data": {
                                    "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                    "children": [
                                      {
                                        "kind": "more",
                                        "data": {
                                          "count": 3,
                                          "parent_id": "t1_d3v8uu5",
                                          "id": "d3v9zot",
                                          "name": "t1_d3v9zot",
                                          "children": [
                                            "d3v9zot"
                                          ]
                                        }
                                      }
                                    ],
                                    "after": null,
                                    "before": null
                                  }
                                },
                                "user_reports": [

                                ],
                                "saved": false,
                                "id": "d3v8uu5",
                                "gilded": 0,
                                "archived": false,
                                "report_reasons": null,
                                "author": "AvellionB",
                                "parent_id": "t1_d3v8qlk",
                                "score": 19,
                                "approved_by": null,
                                "controversiality": 0,
                                "body": "Some lady stopped to give me a couple of bottles of water though. So there is that.",
                                "edited": false,
                                "author_flair_css_class": null,
                                "downs": 0,
                                "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Some lady stopped to give me a couple of bottles of water though. So there is that.&lt;/p&gt;\n&lt;/div&gt;",
                                "subreddit": "funny",
                                "name": "t1_d3v8uu5",
                                "score_hidden": false,
                                "stickied": false,
                                "created": 1465032390.0,
                                "author_flair_text": null,
                                "created_utc": 1465003590.0,
                                "distinguished": null,
                                "mod_reports": [

                                ],
                                "num_reports": null,
                                "ups": 19
                              }
                            }
                          ],
                          "after": null,
                          "before": null
                        }
                      },
                      "user_reports": [

                      ],
                      "saved": false,
                      "id": "d3v8qlk",
                      "gilded": 0,
                      "archived": false,
                      "report_reasons": null,
                      "author": "-ParticleMan-",
                      "parent_id": "t1_d3v8anj",
                      "score": 14,
                      "approved_by": null,
                      "controversiality": 0,
                      "body": "you didnt need to be reminded, this just rubbed it in",
                      "edited": false,
                      "author_flair_css_class": null,
                      "downs": 0,
                      "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;you didnt need to be reminded, this just rubbed it in&lt;/p&gt;\n&lt;/div&gt;",
                      "subreddit": "funny",
                      "name": "t1_d3v8qlk",
                      "score_hidden": false,
                      "stickied": false,
                      "created": 1465032185.0,
                      "author_flair_text": null,
                      "created_utc": 1465003385.0,
                      "distinguished": null,
                      "mod_reports": [

                      ],
                      "num_reports": null,
                      "ups": 14
                    }
                  },
                  {
                    "kind": "more",
                    "data": {
                      "count": 2,
                      "parent_id": "t1_d3v8anj",
                      "id": "d3vcg7l",
                      "name": "t1_d3vcg7l",
                      "children": [
                        "d3vcg7l",
                        "d3vbjrj"
                      ]
                    }
                  }
                ],
                "after": null,
                "before": null
              }
            },
            "user_reports": [

            ],
            "saved": false,
            "id": "d3v8anj",
            "gilded": 0,
            "archived": false,
            "report_reasons": null,
            "author": "AvellionB",
            "parent_id": "t3_4mfawk",
            "score": 54,
            "approved_by": null,
            "controversiality": 0,
            "body": "This is what I love about reddit. I am sitting in a church parking lot in Mesa waiting on a tow truck and I found this post to remind me how fucking hot it is.\n\nEdit: roadside assistance just called to let me know it will be another 30 minutes. Whoever my insurance contracts for towing can suck a bag of dicks.\n\nEdit 2: well aparently my tow got cancelled due to a \"miscommunication\" and now it will be another hour since they had to dispatch a new truck. At least it has dolled down to the 90s now that the sun had gone down.",
            "edited": 1465008222.0,
            "author_flair_css_class": null,
            "downs": 0,
            "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;This is what I love about reddit. I am sitting in a church parking lot in Mesa waiting on a tow truck and I found this post to remind me how fucking hot it is.&lt;/p&gt;\n\n&lt;p&gt;Edit: roadside assistance just called to let me know it will be another 30 minutes. Whoever my insurance contracts for towing can suck a bag of dicks.&lt;/p&gt;\n\n&lt;p&gt;Edit 2: well aparently my tow got cancelled due to a &amp;quot;miscommunication&amp;quot; and now it will be another hour since they had to dispatch a new truck. At least it has dolled down to the 90s now that the sun had gone down.&lt;/p&gt;\n&lt;/div&gt;",
            "subreddit": "funny",
            "name": "t1_d3v8anj",
            "score_hidden": false,
            "stickied": false,
            "created": 1465031439.0,
            "author_flair_text": null,
            "created_utc": 1465002639.0,
            "distinguished": null,
            "mod_reports": [

            ],
            "num_reports": null,
            "ups": 54
          }
        },
        {
          "kind": "t1",
          "data": {
            "subreddit_id": "t5_2qh33",
            "banned_by": null,
            "removal_reason": null,
            "link_id": "t3_4mfawk",
            "likes": null,
            "replies": {
              "kind": "Listing",
              "data": {
                "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                "children": [
                  {
                    "kind": "t1",
                    "data": {
                      "subreddit_id": "t5_2qh33",
                      "banned_by": null,
                      "removal_reason": null,
                      "link_id": "t3_4mfawk",
                      "likes": null,
                      "replies": {
                        "kind": "Listing",
                        "data": {
                          "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                          "children": [
                            {
                              "kind": "t1",
                              "data": {
                                "subreddit_id": "t5_2qh33",
                                "banned_by": null,
                                "removal_reason": null,
                                "link_id": "t3_4mfawk",
                                "likes": null,
                                "replies": {
                                  "kind": "Listing",
                                  "data": {
                                    "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                    "children": [
                                      {
                                        "kind": "t1",
                                        "data": {
                                          "subreddit_id": "t5_2qh33",
                                          "banned_by": null,
                                          "removal_reason": null,
                                          "link_id": "t3_4mfawk",
                                          "likes": null,
                                          "replies": {
                                            "kind": "Listing",
                                            "data": {
                                              "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                              "children": [
                                                {
                                                  "kind": "t1",
                                                  "data": {
                                                    "subreddit_id": "t5_2qh33",
                                                    "banned_by": null,
                                                    "removal_reason": null,
                                                    "link_id": "t3_4mfawk",
                                                    "likes": null,
                                                    "replies": {
                                                      "kind": "Listing",
                                                      "data": {
                                                        "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                                        "children": [
                                                          {
                                                            "kind": "t1",
                                                            "data": {
                                                              "subreddit_id": "t5_2qh33",
                                                              "banned_by": null,
                                                              "removal_reason": null,
                                                              "link_id": "t3_4mfawk",
                                                              "likes": null,
                                                              "replies": "",
                                                              "user_reports": [

                                                              ],
                                                              "saved": false,
                                                              "id": "d3v98n2",
                                                              "gilded": 0,
                                                              "archived": false,
                                                              "report_reasons": null,
                                                              "author": "Dunda",
                                                              "parent_id": "t1_d3v3td7",
                                                              "score": 10,
                                                              "approved_by": null,
                                                              "controversiality": 0,
                                                              "body": "Wow, you just made it seem kinda weird.",
                                                              "edited": false,
                                                              "author_flair_css_class": null,
                                                              "downs": 0,
                                                              "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Wow, you just made it seem kinda weird.&lt;/p&gt;\n&lt;/div&gt;",
                                                              "subreddit": "funny",
                                                              "name": "t1_d3v98n2",
                                                              "score_hidden": false,
                                                              "stickied": false,
                                                              "created": 1465033052.0,
                                                              "author_flair_text": null,
                                                              "created_utc": 1465004252.0,
                                                              "distinguished": null,
                                                              "mod_reports": [

                                                              ],
                                                              "num_reports": null,
                                                              "ups": 10
                                                            }
                                                          },
                                                          {
                                                            "kind": "more",
                                                            "data": {
                                                              "count": 1,
                                                              "parent_id": "t1_d3v3td7",
                                                              "id": "d3vaxnr",
                                                              "name": "t1_d3vaxnr",
                                                              "children": [
                                                                "d3vaxnr"
                                                              ]
                                                            }
                                                          }
                                                        ],
                                                        "after": null,
                                                        "before": null
                                                      }
                                                    },
                                                    "user_reports": [

                                                    ],
                                                    "saved": false,
                                                    "id": "d3v3td7",
                                                    "gilded": 0,
                                                    "archived": false,
                                                    "report_reasons": null,
                                                    "author": "iMakeItSeemWeird",
                                                    "parent_id": "t1_d3v3psh",
                                                    "score": 24,
                                                    "approved_by": null,
                                                    "controversiality": 0,
                                                    "body": "I just like to stare at women's feet.\n\nI guess I'll stick to the beach.",
                                                    "edited": false,
                                                    "author_flair_css_class": null,
                                                    "downs": 0,
                                                    "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;I just like to stare at women&amp;#39;s feet.&lt;/p&gt;\n\n&lt;p&gt;I guess I&amp;#39;ll stick to the beach.&lt;/p&gt;\n&lt;/div&gt;",
                                                    "subreddit": "funny",
                                                    "name": "t1_d3v3td7",
                                                    "score_hidden": false,
                                                    "stickied": false,
                                                    "created": 1465023651.0,
                                                    "author_flair_text": null,
                                                    "created_utc": 1464994851.0,
                                                    "distinguished": null,
                                                    "mod_reports": [

                                                    ],
                                                    "num_reports": null,
                                                    "ups": 24
                                                  }
                                                },
                                                {
                                                  "kind": "t1",
                                                  "data": {
                                                    "subreddit_id": "t5_2qh33",
                                                    "banned_by": null,
                                                    "removal_reason": null,
                                                    "link_id": "t3_4mfawk",
                                                    "likes": null,
                                                    "replies": {
                                                      "kind": "Listing",
                                                      "data": {
                                                        "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                                        "children": [
                                                          {
                                                            "kind": "t1",
                                                            "data": {
                                                              "subreddit_id": "t5_2qh33",
                                                              "banned_by": null,
                                                              "removal_reason": null,
                                                              "link_id": "t3_4mfawk",
                                                              "likes": null,
                                                              "replies": {
                                                                "kind": "Listing",
                                                                "data": {
                                                                  "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                                                  "children": [
                                                                    {
                                                                      "kind": "more",
                                                                      "data": {
                                                                        "count": 8,
                                                                        "parent_id": "t1_d3v7aew",
                                                                        "children": [
                                                                          "d3v9dap",
                                                                          "d3v8vyq",
                                                                          "d3v92ui"
                                                                        ],
                                                                        "name": "t1_d3v9dap",
                                                                        "id": "d3v9dap"
                                                                      }
                                                                    }
                                                                  ],
                                                                  "after": null,
                                                                  "before": null
                                                                }
                                                              },
                                                              "user_reports": [

                                                              ],
                                                              "saved": false,
                                                              "id": "d3v7aew",
                                                              "gilded": 0,
                                                              "archived": false,
                                                              "report_reasons": null,
                                                              "author": "wingnut5k",
                                                              "parent_id": "t1_d3v4qls",
                                                              "score": 17,
                                                              "approved_by": null,
                                                              "controversiality": 0,
                                                              "body": "Australia temperature wise is nothing compared to Arizona. Animal wise, it is surely worse.",
                                                              "edited": false,
                                                              "author_flair_css_class": null,
                                                              "downs": 0,
                                                              "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Australia temperature wise is nothing compared to Arizona. Animal wise, it is surely worse.&lt;/p&gt;\n&lt;/div&gt;",
                                                              "subreddit": "funny",
                                                              "name": "t1_d3v7aew",
                                                              "score_hidden": false,
                                                              "stickied": false,
                                                              "created": 1465029707.0,
                                                              "author_flair_text": null,
                                                              "created_utc": 1465000907.0,
                                                              "distinguished": null,
                                                              "mod_reports": [

                                                              ],
                                                              "num_reports": null,
                                                              "ups": 17
                                                            }
                                                          },
                                                          {
                                                            "kind": "more",
                                                            "data": {
                                                              "count": 2,
                                                              "parent_id": "t1_d3v4qls",
                                                              "id": "d3v8mju",
                                                              "name": "t1_d3v8mju",
                                                              "children": [
                                                                "d3v8mju",
                                                                "d3vaw8m"
                                                              ]
                                                            }
                                                          }
                                                        ],
                                                        "after": null,
                                                        "before": null
                                                      }
                                                    },
                                                    "user_reports": [

                                                    ],
                                                    "saved": false,
                                                    "id": "d3v4qls",
                                                    "gilded": 0,
                                                    "archived": false,
                                                    "report_reasons": null,
                                                    "author": "Buttersgoo23",
                                                    "parent_id": "t1_d3v3psh",
                                                    "score": 12,
                                                    "approved_by": null,
                                                    "controversiality": 0,
                                                    "body": "So, like a mini Australia?",
                                                    "edited": false,
                                                    "author_flair_css_class": null,
                                                    "downs": 0,
                                                    "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;So, like a mini Australia?&lt;/p&gt;\n&lt;/div&gt;",
                                                    "subreddit": "funny",
                                                    "name": "t1_d3v4qls",
                                                    "score_hidden": false,
                                                    "stickied": false,
                                                    "created": 1465025236.0,
                                                    "author_flair_text": null,
                                                    "created_utc": 1464996436.0,
                                                    "distinguished": null,
                                                    "mod_reports": [

                                                    ],
                                                    "num_reports": null,
                                                    "ups": 12
                                                  }
                                                },
                                                {
                                                  "kind": "more",
                                                  "data": {
                                                    "count": 7,
                                                    "parent_id": "t1_d3v3psh",
                                                    "children": [
                                                      "d3vatft",
                                                      "d3v6nwk",
                                                      "d3v853n",
                                                      "d3vaclw",
                                                      "d3v71kk"
                                                    ],
                                                    "name": "t1_d3vatft",
                                                    "id": "d3vatft"
                                                  }
                                                }
                                              ],
                                              "after": null,
                                              "before": null
                                            }
                                          },
                                          "user_reports": [

                                          ],
                                          "saved": false,
                                          "id": "d3v3psh",
                                          "gilded": 0,
                                          "archived": false,
                                          "report_reasons": null,
                                          "author": "Mjolnir12",
                                          "parent_id": "t1_d3v3o5i",
                                          "score": 32,
                                          "approved_by": null,
                                          "controversiality": 0,
                                          "body": "Oh, also fire ants. And spiders. Basically, everything is trying to kill you.",
                                          "edited": false,
                                          "author_flair_css_class": null,
                                          "downs": 0,
                                          "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Oh, also fire ants. And spiders. Basically, everything is trying to kill you.&lt;/p&gt;\n&lt;/div&gt;",
                                          "subreddit": "funny",
                                          "name": "t1_d3v3psh",
                                          "score_hidden": false,
                                          "stickied": false,
                                          "created": 1465023486.0,
                                          "author_flair_text": null,
                                          "created_utc": 1464994686.0,
                                          "distinguished": null,
                                          "mod_reports": [

                                          ],
                                          "num_reports": null,
                                          "ups": 32
                                        }
                                      },
                                      {
                                        "kind": "more",
                                        "data": {
                                          "count": 2,
                                          "parent_id": "t1_d3v3o5i",
                                          "id": "d3v9dag",
                                          "name": "t1_d3v9dag",
                                          "children": [
                                            "d3v9dag",
                                            "d3v9f06"
                                          ]
                                        }
                                      }
                                    ],
                                    "after": null,
                                    "before": null
                                  }
                                },
                                "user_reports": [

                                ],
                                "saved": false,
                                "id": "d3v3o5i",
                                "gilded": 0,
                                "archived": false,
                                "report_reasons": null,
                                "author": "iMakeItSeemWeird",
                                "parent_id": "t1_d3v3lsg",
                                "score": 23,
                                "approved_by": null,
                                "controversiality": 0,
                                "body": "Not worth it then.",
                                "edited": false,
                                "author_flair_css_class": null,
                                "downs": 0,
                                "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Not worth it then.&lt;/p&gt;\n&lt;/div&gt;",
                                "subreddit": "funny",
                                "name": "t1_d3v3o5i",
                                "score_hidden": false,
                                "stickied": false,
                                "created": 1465023410.0,
                                "author_flair_text": null,
                                "created_utc": 1464994610.0,
                                "distinguished": null,
                                "mod_reports": [

                                ],
                                "num_reports": null,
                                "ups": 23
                              }
                            },
                            {
                              "kind": "more",
                              "data": {
                                "count": 15,
                                "parent_id": "t1_d3v3lsg",
                                "children": [
                                  "d3v9ao0",
                                  "d3v5oav",
                                  "d3va2nr",
                                  "d3v8lsg",
                                  "d3vacad",
                                  "d3vawk6",
                                  "d3v8i9j"
                                ],
                                "name": "t1_d3v9ao0",
                                "id": "d3v9ao0"
                              }
                            }
                          ],
                          "after": null,
                          "before": null
                        }
                      },
                      "user_reports": [

                      ],
                      "saved": false,
                      "id": "d3v3lsg",
                      "gilded": 0,
                      "archived": false,
                      "report_reasons": null,
                      "author": "Mjolnir12",
                      "parent_id": "t1_d3v34gk",
                      "score": 42,
                      "approved_by": null,
                      "controversiality": 0,
                      "body": "Nope, scorpions and snakes",
                      "edited": false,
                      "author_flair_css_class": null,
                      "downs": 0,
                      "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Nope, scorpions and snakes&lt;/p&gt;\n&lt;/div&gt;",
                      "subreddit": "funny",
                      "name": "t1_d3v3lsg",
                      "score_hidden": false,
                      "stickied": false,
                      "created": 1465023299.0,
                      "author_flair_text": null,
                      "created_utc": 1464994499.0,
                      "distinguished": null,
                      "mod_reports": [

                      ],
                      "num_reports": null,
                      "ups": 42
                    }
                  },
                  {
                    "kind": "more",
                    "data": {
                      "count": 13,
                      "parent_id": "t1_d3v34gk",
                      "id": "d3v8jk0",
                      "name": "t1_d3v8jk0",
                      "children": [
                        "d3v8jk0",
                        "d3v4w8x",
                        "d3v8tid",
                        "d3v927c",
                        "d3v9nqk",
                        "d3v88ra",
                        "d3v85el"
                      ]
                    }
                  }
                ],
                "after": null,
                "before": null
              }
            },
            "user_reports": [

            ],
            "saved": false,
            "id": "d3v34gk",
            "gilded": 0,
            "archived": false,
            "report_reasons": null,
            "author": "iMakeItSeemWeird",
            "parent_id": "t3_4mfawk",
            "score": 40,
            "approved_by": null,
            "controversiality": 0,
            "body": "I bet there are lots of flip flops.  That's why I'd live there.",
            "edited": false,
            "author_flair_css_class": null,
            "downs": 0,
            "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;I bet there are lots of flip flops.  That&amp;#39;s why I&amp;#39;d live there.&lt;/p&gt;\n&lt;/div&gt;",
            "subreddit": "funny",
            "name": "t1_d3v34gk",
            "score_hidden": false,
            "stickied": false,
            "created": 1465022488.0,
            "author_flair_text": null,
            "created_utc": 1464993688.0,
            "distinguished": null,
            "mod_reports": [

            ],
            "num_reports": null,
            "ups": 40
          }
        },
        {
          "kind": "t1",
          "data": {
            "subreddit_id": "t5_2qh33",
            "banned_by": null,
            "removal_reason": null,
            "link_id": "t3_4mfawk",
            "likes": null,
            "replies": {
              "kind": "Listing",
              "data": {
                "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                "children": [
                  {
                    "kind": "t1",
                    "data": {
                      "subreddit_id": "t5_2qh33",
                      "banned_by": null,
                      "removal_reason": null,
                      "link_id": "t3_4mfawk",
                      "likes": null,
                      "replies": {
                        "kind": "Listing",
                        "data": {
                          "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                          "children": [
                            {
                              "kind": "more",
                              "data": {
                                "count": 1,
                                "parent_id": "t1_d3v4z67",
                                "children": [
                                  "d3v9y0q"
                                ],
                                "name": "t1_d3v9y0q",
                                "id": "d3v9y0q"
                              }
                            }
                          ],
                          "after": null,
                          "before": null
                        }
                      },
                      "user_reports": [

                      ],
                      "saved": false,
                      "id": "d3v4z67",
                      "gilded": 0,
                      "archived": false,
                      "report_reasons": null,
                      "author": "Lord_Grundlebeard",
                      "parent_id": "t1_d3v3owp",
                      "score": 51,
                      "approved_by": null,
                      "controversiality": 0,
                      "body": "Goddamn communists stealing our nice weather.",
                      "edited": false,
                      "author_flair_css_class": null,
                      "downs": 0,
                      "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Goddamn communists stealing our nice weather.&lt;/p&gt;\n&lt;/div&gt;",
                      "subreddit": "funny",
                      "name": "t1_d3v4z67",
                      "score_hidden": false,
                      "stickied": false,
                      "created": 1465025648.0,
                      "author_flair_text": null,
                      "created_utc": 1464996848.0,
                      "distinguished": null,
                      "mod_reports": [

                      ],
                      "num_reports": null,
                      "ups": 51
                    }
                  },
                  {
                    "kind": "t1",
                    "data": {
                      "subreddit_id": "t5_2qh33",
                      "banned_by": null,
                      "removal_reason": null,
                      "link_id": "t3_4mfawk",
                      "likes": null,
                      "replies": {
                        "kind": "Listing",
                        "data": {
                          "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                          "children": [
                            {
                              "kind": "more",
                              "data": {
                                "count": 3,
                                "parent_id": "t1_d3v8nvz",
                                "children": [
                                  "d3v9nve"
                                ],
                                "name": "t1_d3v9nve",
                                "id": "d3v9nve"
                              }
                            }
                          ],
                          "after": null,
                          "before": null
                        }
                      },
                      "user_reports": [

                      ],
                      "saved": false,
                      "id": "d3v8nvz",
                      "gilded": 0,
                      "archived": false,
                      "report_reasons": null,
                      "author": "-OldGregg-",
                      "parent_id": "t1_d3v3owp",
                      "score": 15,
                      "approved_by": null,
                      "controversiality": 0,
                      "body": "Well 77c is pretty hot ",
                      "edited": false,
                      "author_flair_css_class": null,
                      "downs": 0,
                      "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Well 77c is pretty hot &lt;/p&gt;\n&lt;/div&gt;",
                      "subreddit": "funny",
                      "name": "t1_d3v8nvz",
                      "score_hidden": false,
                      "stickied": false,
                      "created": 1465032053.0,
                      "author_flair_text": null,
                      "created_utc": 1465003253.0,
                      "distinguished": null,
                      "mod_reports": [

                      ],
                      "num_reports": null,
                      "ups": 15
                    }
                  },
                  {
                    "kind": "t1",
                    "data": {
                      "subreddit_id": "t5_2qh33",
                      "banned_by": null,
                      "removal_reason": null,
                      "link_id": "t3_4mfawk",
                      "likes": null,
                      "replies": "",
                      "user_reports": [

                      ],
                      "saved": false,
                      "id": "d3v6onj",
                      "gilded": 0,
                      "archived": false,
                      "report_reasons": null,
                      "author": "sleepyj910",
                      "parent_id": "t1_d3v3owp",
                      "score": 13,
                      "approved_by": null,
                      "controversiality": 0,
                      "body": "but that sweat is half vodka",
                      "edited": false,
                      "author_flair_css_class": null,
                      "downs": 0,
                      "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;but that sweat is half vodka&lt;/p&gt;\n&lt;/div&gt;",
                      "subreddit": "funny",
                      "name": "t1_d3v6onj",
                      "score_hidden": false,
                      "stickied": false,
                      "created": 1465028644.0,
                      "author_flair_text": null,
                      "created_utc": 1464999844.0,
                      "distinguished": null,
                      "mod_reports": [

                      ],
                      "num_reports": null,
                      "ups": 13
                    }
                  },
                  {
                    "kind": "more",
                    "data": {
                      "count": 5,
                      "parent_id": "t1_d3v3owp",
                      "id": "d3va36a",
                      "name": "t1_d3va36a",
                      "children": [
                        "d3va36a",
                        "d3vce64",
                        "d3vagn9",
                        "d3v8c4t"
                      ]
                    }
                  }
                ],
                "after": null,
                "before": null
              }
            },
            "user_reports": [

            ],
            "saved": false,
            "id": "d3v3owp",
            "gilded": 0,
            "archived": false,
            "report_reasons": null,
            "author": "topsng",
            "parent_id": "t3_4mfawk",
            "score": 35,
            "approved_by": null,
            "controversiality": 0,
            "body": "It's 77 in russia and i'm already sweating badly ",
            "edited": false,
            "author_flair_css_class": null,
            "downs": 0,
            "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;It&amp;#39;s 77 in russia and i&amp;#39;m already sweating badly &lt;/p&gt;\n&lt;/div&gt;",
            "subreddit": "funny",
            "name": "t1_d3v3owp",
            "score_hidden": false,
            "stickied": false,
            "created": 1465023445.0,
            "author_flair_text": null,
            "created_utc": 1464994645.0,
            "distinguished": null,
            "mod_reports": [

            ],
            "num_reports": null,
            "ups": 35
          }
        },
        {
          "kind": "t1",
          "data": {
            "subreddit_id": "t5_2qh33",
            "banned_by": null,
            "removal_reason": null,
            "link_id": "t3_4mfawk",
            "likes": null,
            "replies": {
              "kind": "Listing",
              "data": {
                "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                "children": [
                  {
                    "kind": "t1",
                    "data": {
                      "subreddit_id": "t5_2qh33",
                      "banned_by": null,
                      "removal_reason": null,
                      "link_id": "t3_4mfawk",
                      "likes": null,
                      "replies": {
                        "kind": "Listing",
                        "data": {
                          "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                          "children": [
                            {
                              "kind": "t1",
                              "data": {
                                "subreddit_id": "t5_2qh33",
                                "banned_by": null,
                                "removal_reason": null,
                                "link_id": "t3_4mfawk",
                                "likes": null,
                                "replies": {
                                  "kind": "Listing",
                                  "data": {
                                    "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                    "children": [
                                      {
                                        "kind": "more",
                                        "data": {
                                          "count": 3,
                                          "parent_id": "t1_d3v35xn",
                                          "id": "d3v9hze",
                                          "name": "t1_d3v9hze",
                                          "children": [
                                            "d3v9hze",
                                            "d3v3zrb"
                                          ]
                                        }
                                      }
                                    ],
                                    "after": null,
                                    "before": null
                                  }
                                },
                                "user_reports": [

                                ],
                                "saved": false,
                                "id": "d3v35xn",
                                "gilded": 0,
                                "archived": false,
                                "report_reasons": null,
                                "author": "[deleted]",
                                "parent_id": "t1_d3v32hp",
                                "score": 12,
                                "approved_by": null,
                                "controversiality": 0,
                                "body": "[deleted]",
                                "edited": false,
                                "author_flair_css_class": null,
                                "downs": 0,
                                "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;[deleted]&lt;/p&gt;\n&lt;/div&gt;",
                                "subreddit": "funny",
                                "name": "t1_d3v35xn",
                                "score_hidden": false,
                                "stickied": false,
                                "created": 1465022556.0,
                                "author_flair_text": null,
                                "created_utc": 1464993756.0,
                                "distinguished": null,
                                "mod_reports": [

                                ],
                                "num_reports": null,
                                "ups": 12
                              }
                            },
                            {
                              "kind": "more",
                              "data": {
                                "count": 1,
                                "parent_id": "t1_d3v32hp",
                                "children": [
                                  "d3v342m"
                                ],
                                "name": "t1_d3v342m",
                                "id": "d3v342m"
                              }
                            }
                          ],
                          "after": null,
                          "before": null
                        }
                      },
                      "user_reports": [

                      ],
                      "saved": false,
                      "id": "d3v32hp",
                      "gilded": 0,
                      "archived": false,
                      "report_reasons": null,
                      "author": "Longboxjockey",
                      "parent_id": "t1_d3v1wbf",
                      "score": 21,
                      "approved_by": null,
                      "controversiality": 0,
                      "body": "# HEAR ME X-MEN! I AM NO LONGER THE WOMAN YOU KNEW! I AM FIRE AND LIFE INCARNATE NOW AND FOREVER!",
                      "edited": false,
                      "author_flair_css_class": null,
                      "downs": 0,
                      "body_html": "&lt;div class=\"md\"&gt;&lt;h1&gt;HEAR ME X-MEN! I AM NO LONGER THE WOMAN YOU KNEW! I AM FIRE AND LIFE INCARNATE NOW AND FOREVER!&lt;/h1&gt;\n&lt;/div&gt;",
                      "subreddit": "funny",
                      "name": "t1_d3v32hp",
                      "score_hidden": false,
                      "stickied": false,
                      "created": 1465022397.0,
                      "author_flair_text": null,
                      "created_utc": 1464993597.0,
                      "distinguished": null,
                      "mod_reports": [

                      ],
                      "num_reports": null,
                      "ups": 21
                    }
                  }
                ],
                "after": null,
                "before": null
              }
            },
            "user_reports": [

            ],
            "saved": false,
            "id": "d3v1wbf",
            "gilded": 0,
            "archived": false,
            "report_reasons": null,
            "author": "Ar3s701",
            "parent_id": "t3_4mfawk",
            "score": 30,
            "approved_by": null,
            "controversiality": 0,
            "body": "The Dark Phoenix rises!",
            "edited": false,
            "author_flair_css_class": null,
            "downs": 0,
            "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;The Dark Phoenix rises!&lt;/p&gt;\n&lt;/div&gt;",
            "subreddit": "funny",
            "name": "t1_d3v1wbf",
            "score_hidden": false,
            "stickied": false,
            "created": 1465020484.0,
            "author_flair_text": null,
            "created_utc": 1464991684.0,
            "distinguished": null,
            "mod_reports": [

            ],
            "num_reports": null,
            "ups": 30
          }
        },
        {
          "kind": "t1",
          "data": {
            "subreddit_id": "t5_2qh33",
            "banned_by": null,
            "removal_reason": null,
            "link_id": "t3_4mfawk",
            "likes": null,
            "replies": {
              "kind": "Listing",
              "data": {
                "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                "children": [
                  {
                    "kind": "t1",
                    "data": {
                      "subreddit_id": "t5_2qh33",
                      "banned_by": null,
                      "removal_reason": null,
                      "link_id": "t3_4mfawk",
                      "likes": null,
                      "replies": {
                        "kind": "Listing",
                        "data": {
                          "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                          "children": [
                            {
                              "kind": "t1",
                              "data": {
                                "subreddit_id": "t5_2qh33",
                                "banned_by": null,
                                "removal_reason": null,
                                "link_id": "t3_4mfawk",
                                "likes": null,
                                "replies": "",
                                "user_reports": [

                                ],
                                "saved": false,
                                "id": "d3v7mlq",
                                "gilded": 0,
                                "archived": false,
                                "report_reasons": null,
                                "author": "cwitt12",
                                "parent_id": "t1_d3v5cjt",
                                "score": 9,
                                "approved_by": null,
                                "controversiality": 0,
                                "body": "Bingo! Cheers fellow valley resident!",
                                "edited": false,
                                "author_flair_css_class": null,
                                "downs": 0,
                                "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Bingo! Cheers fellow valley resident!&lt;/p&gt;\n&lt;/div&gt;",
                                "subreddit": "funny",
                                "name": "t1_d3v7mlq",
                                "score_hidden": false,
                                "stickied": false,
                                "created": 1465030299.0,
                                "author_flair_text": null,
                                "created_utc": 1465001499.0,
                                "distinguished": null,
                                "mod_reports": [

                                ],
                                "num_reports": null,
                                "ups": 9
                              }
                            },
                            {
                              "kind": "more",
                              "data": {
                                "count": 10,
                                "parent_id": "t1_d3v5cjt",
                                "children": [
                                  "d3v8gqp",
                                  "d3vaipx",
                                  "d3v9meg",
                                  "d3v8o22",
                                  "d3vapsf",
                                  "d3v8h9x",
                                  "d3vazwa",
                                  "d3vakyn"
                                ],
                                "name": "t1_d3v8gqp",
                                "id": "d3v8gqp"
                              }
                            }
                          ],
                          "after": null,
                          "before": null
                        }
                      },
                      "user_reports": [

                      ],
                      "saved": false,
                      "id": "d3v5cjt",
                      "gilded": 0,
                      "archived": false,
                      "report_reasons": null,
                      "author": "techgirl_33",
                      "parent_id": "t1_d3v2z1l",
                      "score": 64,
                      "approved_by": null,
                      "controversiality": 0,
                      "body": "I live here because I wear flip flops 10 months out of the year. Yes it's going to be hot. There is a cure for it. Fruity drinks and a pool oh and we can do fruity drinks and a pool from March to November. Off work in 16 minutes and that's where I'm headed",
                      "edited": false,
                      "author_flair_css_class": null,
                      "downs": 0,
                      "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;I live here because I wear flip flops 10 months out of the year. Yes it&amp;#39;s going to be hot. There is a cure for it. Fruity drinks and a pool oh and we can do fruity drinks and a pool from March to November. Off work in 16 minutes and that&amp;#39;s where I&amp;#39;m headed&lt;/p&gt;\n&lt;/div&gt;",
                      "subreddit": "funny",
                      "name": "t1_d3v5cjt",
                      "score_hidden": false,
                      "stickied": false,
                      "created": 1465026285.0,
                      "author_flair_text": null,
                      "created_utc": 1464997485.0,
                      "distinguished": null,
                      "mod_reports": [

                      ],
                      "num_reports": null,
                      "ups": 64
                    }
                  },
                  {
                    "kind": "t1",
                    "data": {
                      "subreddit_id": "t5_2qh33",
                      "banned_by": null,
                      "removal_reason": null,
                      "link_id": "t3_4mfawk",
                      "likes": null,
                      "replies": {
                        "kind": "Listing",
                        "data": {
                          "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                          "children": [
                            {
                              "kind": "t1",
                              "data": {
                                "subreddit_id": "t5_2qh33",
                                "banned_by": null,
                                "removal_reason": null,
                                "link_id": "t3_4mfawk",
                                "likes": null,
                                "replies": {
                                  "kind": "Listing",
                                  "data": {
                                    "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                    "children": [
                                      {
                                        "kind": "t1",
                                        "data": {
                                          "subreddit_id": "t5_2qh33",
                                          "banned_by": null,
                                          "removal_reason": null,
                                          "link_id": "t3_4mfawk",
                                          "likes": null,
                                          "replies": {
                                            "kind": "Listing",
                                            "data": {
                                              "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                              "children": [
                                                {
                                                  "kind": "t1",
                                                  "data": {
                                                    "subreddit_id": "t5_2qh33",
                                                    "banned_by": null,
                                                    "removal_reason": null,
                                                    "link_id": "t3_4mfawk",
                                                    "likes": null,
                                                    "replies": {
                                                      "kind": "Listing",
                                                      "data": {
                                                        "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                                        "children": [
                                                          {
                                                            "kind": "more",
                                                            "data": {
                                                              "count": 2,
                                                              "parent_id": "t1_d3v8ggw",
                                                              "id": "d3vbhrd",
                                                              "name": "t1_d3vbhrd",
                                                              "children": [
                                                                "d3vbhrd",
                                                                "d3v9ahq"
                                                              ]
                                                            }
                                                          }
                                                        ],
                                                        "after": null,
                                                        "before": null
                                                      }
                                                    },
                                                    "user_reports": [

                                                    ],
                                                    "saved": false,
                                                    "id": "d3v8ggw",
                                                    "gilded": 0,
                                                    "archived": false,
                                                    "report_reasons": null,
                                                    "author": "RedditsInBed2",
                                                    "parent_id": "t1_d3v82zc",
                                                    "score": 15,
                                                    "approved_by": null,
                                                    "controversiality": 0,
                                                    "body": "Not to mention you get used to the heat if you've lived here long enough and get a good summer time routine going so you're not running errands at noon. The three months really aren't that bad at all once you get settled in.",
                                                    "edited": false,
                                                    "author_flair_css_class": null,
                                                    "downs": 0,
                                                    "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Not to mention you get used to the heat if you&amp;#39;ve lived here long enough and get a good summer time routine going so you&amp;#39;re not running errands at noon. The three months really aren&amp;#39;t that bad at all once you get settled in.&lt;/p&gt;\n&lt;/div&gt;",
                                                    "subreddit": "funny",
                                                    "name": "t1_d3v8ggw",
                                                    "score_hidden": false,
                                                    "stickied": false,
                                                    "created": 1465031702.0,
                                                    "author_flair_text": null,
                                                    "created_utc": 1465002902.0,
                                                    "distinguished": null,
                                                    "mod_reports": [

                                                    ],
                                                    "num_reports": null,
                                                    "ups": 15
                                                  }
                                                },
                                                {
                                                  "kind": "more",
                                                  "data": {
                                                    "count": 4,
                                                    "parent_id": "t1_d3v82zc",
                                                    "children": [
                                                      "d3vcnkh",
                                                      "d3v8sn9",
                                                      "d3v9nla"
                                                    ],
                                                    "name": "t1_d3vcnkh",
                                                    "id": "d3vcnkh"
                                                  }
                                                }
                                              ],
                                              "after": null,
                                              "before": null
                                            }
                                          },
                                          "user_reports": [

                                          ],
                                          "saved": false,
                                          "id": "d3v82zc",
                                          "gilded": 0,
                                          "archived": false,
                                          "report_reasons": null,
                                          "author": "Ivanb5",
                                          "parent_id": "t1_d3v7d33",
                                          "score": 17,
                                          "approved_by": null,
                                          "controversiality": 0,
                                          "body": "Yes. Besides the three how months of the year I say Phoenix is paradise.",
                                          "edited": false,
                                          "author_flair_css_class": null,
                                          "downs": 0,
                                          "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Yes. Besides the three how months of the year I say Phoenix is paradise.&lt;/p&gt;\n&lt;/div&gt;",
                                          "subreddit": "funny",
                                          "name": "t1_d3v82zc",
                                          "score_hidden": false,
                                          "stickied": false,
                                          "created": 1465031083.0,
                                          "author_flair_text": null,
                                          "created_utc": 1465002283.0,
                                          "distinguished": null,
                                          "mod_reports": [

                                          ],
                                          "num_reports": null,
                                          "ups": 17
                                        }
                                      },
                                      {
                                        "kind": "more",
                                        "data": {
                                          "count": 7,
                                          "parent_id": "t1_d3v7d33",
                                          "id": "d3v9vbs",
                                          "name": "t1_d3v9vbs",
                                          "children": [
                                            "d3v9vbs",
                                            "d3va23n",
                                            "d3v98zh",
                                            "d3v9zta"
                                          ]
                                        }
                                      }
                                    ],
                                    "after": null,
                                    "before": null
                                  }
                                },
                                "user_reports": [

                                ],
                                "saved": false,
                                "id": "d3v7d33",
                                "gilded": 0,
                                "archived": false,
                                "report_reasons": null,
                                "author": "wingnut5k",
                                "parent_id": "t1_d3v77nf",
                                "score": 36,
                                "approved_by": null,
                                "controversiality": 0,
                                "body": "Housing is dirt cheap as well.",
                                "edited": false,
                                "author_flair_css_class": null,
                                "downs": 0,
                                "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Housing is dirt cheap as well.&lt;/p&gt;\n&lt;/div&gt;",
                                "subreddit": "funny",
                                "name": "t1_d3v7d33",
                                "score_hidden": false,
                                "stickied": false,
                                "created": 1465029839.0,
                                "author_flair_text": null,
                                "created_utc": 1465001039.0,
                                "distinguished": null,
                                "mod_reports": [

                                ],
                                "num_reports": null,
                                "ups": 36
                              }
                            },
                            {
                              "kind": "t1",
                              "data": {
                                "subreddit_id": "t5_2qh33",
                                "banned_by": null,
                                "removal_reason": null,
                                "link_id": "t3_4mfawk",
                                "likes": null,
                                "replies": {
                                  "kind": "Listing",
                                  "data": {
                                    "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                    "children": [
                                      {
                                        "kind": "more",
                                        "data": {
                                          "count": 4,
                                          "parent_id": "t1_d3v8ao0",
                                          "id": "d3v9yeo",
                                          "name": "t1_d3v9yeo",
                                          "children": [
                                            "d3v9yeo",
                                            "d3v9dt0",
                                            "d3vbidb"
                                          ]
                                        }
                                      }
                                    ],
                                    "after": null,
                                    "before": null
                                  }
                                },
                                "user_reports": [

                                ],
                                "saved": false,
                                "id": "d3v8ao0",
                                "gilded": 0,
                                "archived": false,
                                "report_reasons": null,
                                "author": "__insertjokehere__",
                                "parent_id": "t1_d3v77nf",
                                "score": 22,
                                "approved_by": null,
                                "controversiality": 0,
                                "body": "And close to San Diego if you want the beach, Flagstaff if you want the mountains, and Vegas if you want to forget.",
                                "edited": false,
                                "author_flair_css_class": null,
                                "downs": 0,
                                "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;And close to San Diego if you want the beach, Flagstaff if you want the mountains, and Vegas if you want to forget.&lt;/p&gt;\n&lt;/div&gt;",
                                "subreddit": "funny",
                                "name": "t1_d3v8ao0",
                                "score_hidden": false,
                                "stickied": false,
                                "created": 1465031439.0,
                                "author_flair_text": null,
                                "created_utc": 1465002639.0,
                                "distinguished": null,
                                "mod_reports": [

                                ],
                                "num_reports": null,
                                "ups": 22
                              }
                            },
                            {
                              "kind": "more",
                              "data": {
                                "count": 21,
                                "parent_id": "t1_d3v77nf",
                                "children": [
                                  "d3v95z5",
                                  "d3v9hwh",
                                  "d3v8qei",
                                  "d3v8vt7",
                                  "d3v9o1a",
                                  "d3v9xoy",
                                  "d3v9ohk"
                                ],
                                "name": "t1_d3v95z5",
                                "id": "d3v95z5"
                              }
                            }
                          ],
                          "after": null,
                          "before": null
                        }
                      },
                      "user_reports": [

                      ],
                      "saved": false,
                      "id": "d3v77nf",
                      "gilded": 0,
                      "archived": false,
                      "report_reasons": null,
                      "author": "Ivanb5",
                      "parent_id": "t1_d3v2z1l",
                      "score": 62,
                      "approved_by": null,
                      "controversiality": 0,
                      "body": "Oh no tornados, earthquakes, snow storms, floodings. And when it's hot ac is in my car, where I work, and at home. Oh did I mention no tornados, earthquakes, snow storms,floodings.",
                      "edited": false,
                      "author_flair_css_class": null,
                      "downs": 0,
                      "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Oh no tornados, earthquakes, snow storms, floodings. And when it&amp;#39;s hot ac is in my car, where I work, and at home. Oh did I mention no tornados, earthquakes, snow storms,floodings.&lt;/p&gt;\n&lt;/div&gt;",
                      "subreddit": "funny",
                      "name": "t1_d3v77nf",
                      "score_hidden": false,
                      "stickied": false,
                      "created": 1465029569.0,
                      "author_flair_text": null,
                      "created_utc": 1465000769.0,
                      "distinguished": null,
                      "mod_reports": [

                      ],
                      "num_reports": null,
                      "ups": 62
                    }
                  },
                  {
                    "kind": "t1",
                    "data": {
                      "subreddit_id": "t5_2qh33",
                      "banned_by": null,
                      "removal_reason": null,
                      "link_id": "t3_4mfawk",
                      "likes": null,
                      "replies": {
                        "kind": "Listing",
                        "data": {
                          "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                          "children": [
                            {
                              "kind": "more",
                              "data": {
                                "count": 8,
                                "parent_id": "t1_d3v9lb2",
                                "children": [
                                  "d3vbgy4"
                                ],
                                "name": "t1_d3vbgy4",
                                "id": "d3vbgy4"
                              }
                            }
                          ],
                          "after": null,
                          "before": null
                        }
                      },
                      "user_reports": [

                      ],
                      "saved": false,
                      "id": "d3v9lb2",
                      "gilded": 0,
                      "archived": false,
                      "report_reasons": null,
                      "author": "thephoenixx",
                      "parent_id": "t1_d3v2z1l",
                      "score": 19,
                      "approved_by": null,
                      "controversiality": 0,
                      "body": "Because it's fucking dope.  There's no winter.  There's 70f in December.  There's the pool in March.  There's gorgeous...i mean GORGEOUS people here.  There's amazing Mexican food.  There's the crazy low cost of living for such a major metropolitan area.  \n\nThere's margaritas and beautiful patios and hiking Camelback mountain and the beauty of the desert and the proximity to Sedona and Flagstaff and the Grand Canyon and Jerome and really close to Vegas and San Diego and Rocky Point. \n\nThere's so much to love here.  I just got out of my pool, the breeze made me chilly!  110 here ain't shit compared to most of you guys with 90 and humidity.  And snow!!! Fuck snow!  And no salting driveways or whatever.  No shoveling it.  No going out early to warm your car up.  No scraping ice.  No boilers or radiators and no pipes bursting.  No snow chains.  No snow boots or parkas or snow jackets or whatever the fuck you all wear.\n\nNothing but sunshine...mostly mild most of the year, then searing and intense but alleviated by the fact that like 70% of the houses have pools and there's no sticky feeling.  \n\nYeah, I have reasons to live here. ",
                      "edited": false,
                      "author_flair_css_class": null,
                      "downs": 0,
                      "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Because it&amp;#39;s fucking dope.  There&amp;#39;s no winter.  There&amp;#39;s 70f in December.  There&amp;#39;s the pool in March.  There&amp;#39;s gorgeous...i mean GORGEOUS people here.  There&amp;#39;s amazing Mexican food.  There&amp;#39;s the crazy low cost of living for such a major metropolitan area.  &lt;/p&gt;\n\n&lt;p&gt;There&amp;#39;s margaritas and beautiful patios and hiking Camelback mountain and the beauty of the desert and the proximity to Sedona and Flagstaff and the Grand Canyon and Jerome and really close to Vegas and San Diego and Rocky Point. &lt;/p&gt;\n\n&lt;p&gt;There&amp;#39;s so much to love here.  I just got out of my pool, the breeze made me chilly!  110 here ain&amp;#39;t shit compared to most of you guys with 90 and humidity.  And snow!!! Fuck snow!  And no salting driveways or whatever.  No shoveling it.  No going out early to warm your car up.  No scraping ice.  No boilers or radiators and no pipes bursting.  No snow chains.  No snow boots or parkas or snow jackets or whatever the fuck you all wear.&lt;/p&gt;\n\n&lt;p&gt;Nothing but sunshine...mostly mild most of the year, then searing and intense but alleviated by the fact that like 70% of the houses have pools and there&amp;#39;s no sticky feeling.  &lt;/p&gt;\n\n&lt;p&gt;Yeah, I have reasons to live here. &lt;/p&gt;\n&lt;/div&gt;",
                      "subreddit": "funny",
                      "name": "t1_d3v9lb2",
                      "score_hidden": false,
                      "stickied": false,
                      "created": 1465033661.0,
                      "author_flair_text": null,
                      "created_utc": 1465004861.0,
                      "distinguished": null,
                      "mod_reports": [

                      ],
                      "num_reports": null,
                      "ups": 19
                    }
                  },
                  {
                    "kind": "more",
                    "data": {
                      "count": 27,
                      "parent_id": "t1_d3v2z1l",
                      "id": "d3v9ta8",
                      "name": "t1_d3v9ta8",
                      "children": [
                        "d3v9ta8",
                        "d3v8fb9",
                        "d3v8tht",
                        "d3vae6g",
                        "d3v99in",
                        "d3v8g4x",
                        "d3vazfh",
                        "d3vcmib",
                        "d3vbgnz",
                        "d3v8xdo",
                        "d3v8rmw",
                        "d3v876n"
                      ]
                    }
                  }
                ],
                "after": null,
                "before": null
              }
            },
            "user_reports": [

            ],
            "saved": false,
            "id": "d3v2z1l",
            "gilded": 0,
            "archived": false,
            "report_reasons": null,
            "author": "black_flag_4ever",
            "parent_id": "t3_4mfawk",
            "score": 27,
            "approved_by": null,
            "controversiality": 0,
            "body": "Seriously, why the hell does anyone live there?",
            "edited": false,
            "author_flair_css_class": null,
            "downs": 0,
            "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Seriously, why the hell does anyone live there?&lt;/p&gt;\n&lt;/div&gt;",
            "subreddit": "funny",
            "name": "t1_d3v2z1l",
            "score_hidden": false,
            "stickied": false,
            "created": 1465022235.0,
            "author_flair_text": null,
            "created_utc": 1464993435.0,
            "distinguished": null,
            "mod_reports": [

            ],
            "num_reports": null,
            "ups": 27
          }
        },
        {
          "kind": "t1",
          "data": {
            "subreddit_id": "t5_2qh33",
            "banned_by": null,
            "removal_reason": null,
            "link_id": "t3_4mfawk",
            "likes": null,
            "replies": {
              "kind": "Listing",
              "data": {
                "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                "children": [
                  {
                    "kind": "t1",
                    "data": {
                      "subreddit_id": "t5_2qh33",
                      "banned_by": null,
                      "removal_reason": null,
                      "link_id": "t3_4mfawk",
                      "likes": null,
                      "replies": {
                        "kind": "Listing",
                        "data": {
                          "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                          "children": [
                            {
                              "kind": "t1",
                              "data": {
                                "subreddit_id": "t5_2qh33",
                                "banned_by": null,
                                "removal_reason": null,
                                "link_id": "t3_4mfawk",
                                "likes": null,
                                "replies": {
                                  "kind": "Listing",
                                  "data": {
                                    "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                                    "children": [
                                      {
                                        "kind": "more",
                                        "data": {
                                          "count": 2,
                                          "parent_id": "t1_d3v9x8b",
                                          "id": "d3vauu1",
                                          "name": "t1_d3vauu1",
                                          "children": [
                                            "d3vauu1"
                                          ]
                                        }
                                      }
                                    ],
                                    "after": null,
                                    "before": null
                                  }
                                },
                                "user_reports": [

                                ],
                                "saved": false,
                                "id": "d3v9x8b",
                                "gilded": 0,
                                "archived": false,
                                "report_reasons": null,
                                "author": "TyTyTheFireGuy",
                                "parent_id": "t1_d3v9c6r",
                                "score": 9,
                                "approved_by": null,
                                "controversiality": 0,
                                "body": "Thank you. I sincerely appreciate that.",
                                "edited": false,
                                "author_flair_css_class": null,
                                "downs": 0,
                                "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Thank you. I sincerely appreciate that.&lt;/p&gt;\n&lt;/div&gt;",
                                "subreddit": "funny",
                                "name": "t1_d3v9x8b",
                                "score_hidden": false,
                                "stickied": false,
                                "created": 1465034251.0,
                                "author_flair_text": null,
                                "created_utc": 1465005451.0,
                                "distinguished": null,
                                "mod_reports": [

                                ],
                                "num_reports": null,
                                "ups": 9
                              }
                            }
                          ],
                          "after": null,
                          "before": null
                        }
                      },
                      "user_reports": [

                      ],
                      "saved": false,
                      "id": "d3v9c6r",
                      "gilded": 0,
                      "archived": false,
                      "report_reasons": null,
                      "author": "Y0L0LIFE",
                      "parent_id": "t1_d3v8stj",
                      "score": 10,
                      "approved_by": null,
                      "controversiality": 0,
                      "body": "thank you for all that you do",
                      "edited": false,
                      "author_flair_css_class": null,
                      "downs": 0,
                      "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;thank you for all that you do&lt;/p&gt;\n&lt;/div&gt;",
                      "subreddit": "funny",
                      "name": "t1_d3v9c6r",
                      "score_hidden": false,
                      "stickied": false,
                      "created": 1465033226.0,
                      "author_flair_text": null,
                      "created_utc": 1465004426.0,
                      "distinguished": null,
                      "mod_reports": [

                      ],
                      "num_reports": null,
                      "ups": 10
                    }
                  },
                  {
                    "kind": "more",
                    "data": {
                      "count": 3,
                      "parent_id": "t1_d3v8stj",
                      "id": "d3vckro",
                      "name": "t1_d3vckro",
                      "children": [
                        "d3vckro",
                        "d3vc5lw"
                      ]
                    }
                  }
                ],
                "after": null,
                "before": null
              }
            },
            "user_reports": [

            ],
            "saved": false,
            "id": "d3v8stj",
            "gilded": 1,
            "archived": false,
            "report_reasons": null,
            "author": "TyTyTheFireGuy",
            "parent_id": "t3_4mfawk",
            "score": 26,
            "approved_by": null,
            "controversiality": 0,
            "body": "AZ firefighter checking in. On duty and hating life! I've drank, literally, 4 gallons of water. Still haven't peed. Sweating my fucking ass off!",
            "edited": false,
            "author_flair_css_class": null,
            "downs": 0,
            "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;AZ firefighter checking in. On duty and hating life! I&amp;#39;ve drank, literally, 4 gallons of water. Still haven&amp;#39;t peed. Sweating my fucking ass off!&lt;/p&gt;\n&lt;/div&gt;",
            "subreddit": "funny",
            "name": "t1_d3v8stj",
            "score_hidden": false,
            "stickied": false,
            "created": 1465032289.0,
            "author_flair_text": null,
            "created_utc": 1465003489.0,
            "distinguished": null,
            "mod_reports": [

            ],
            "num_reports": null,
            "ups": 26
          }
        },
        {
          "kind": "t1",
          "data": {
            "subreddit_id": "t5_2qh33",
            "banned_by": null,
            "removal_reason": null,
            "link_id": "t3_4mfawk",
            "likes": null,
            "replies": {
              "kind": "Listing",
              "data": {
                "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                "children": [
                  {
                    "kind": "more",
                    "data": {
                      "count": 14,
                      "parent_id": "t1_d3v5wog",
                      "id": "d3vb6xt",
                      "name": "t1_d3vb6xt",
                      "children": [
                        "d3vb6xt",
                        "d3vc3yc",
                        "d3v8t5a",
                        "d3v8pbj",
                        "d3v9jw2",
                        "d3v9zim",
                        "d3v9noe",
                        "d3v8nx1"
                      ]
                    }
                  }
                ],
                "after": null,
                "before": null
              }
            },
            "user_reports": [

            ],
            "saved": false,
            "id": "d3v5wog",
            "gilded": 0,
            "archived": false,
            "report_reasons": null,
            "author": "azside20",
            "parent_id": "t3_4mfawk",
            "score": 21,
            "approved_by": null,
            "controversiality": 0,
            "body": "I was in Austin last week which I absolutely loved other than the humidity.  I'm back in Phoenix and I will take this dry heat any day even if it is over 110.  My body didn't handle the humidity very well and I normally don't go to humid places and have been living in the desert for 30 years.",
            "edited": false,
            "author_flair_css_class": null,
            "downs": 0,
            "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;I was in Austin last week which I absolutely loved other than the humidity.  I&amp;#39;m back in Phoenix and I will take this dry heat any day even if it is over 110.  My body didn&amp;#39;t handle the humidity very well and I normally don&amp;#39;t go to humid places and have been living in the desert for 30 years.&lt;/p&gt;\n&lt;/div&gt;",
            "subreddit": "funny",
            "name": "t1_d3v5wog",
            "score_hidden": false,
            "stickied": false,
            "created": 1465027275.0,
            "author_flair_text": null,
            "created_utc": 1464998475.0,
            "distinguished": null,
            "mod_reports": [

            ],
            "num_reports": null,
            "ups": 21
          }
        },
        {
          "kind": "t1",
          "data": {
            "subreddit_id": "t5_2qh33",
            "banned_by": null,
            "removal_reason": null,
            "link_id": "t3_4mfawk",
            "likes": null,
            "replies": {
              "kind": "Listing",
              "data": {
                "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                "children": [
                  {
                    "kind": "more",
                    "data": {
                      "count": 2,
                      "parent_id": "t1_d3v823r",
                      "id": "d3v91zc",
                      "name": "t1_d3v91zc",
                      "children": [
                        "d3v91zc",
                        "d3va5py"
                      ]
                    }
                  }
                ],
                "after": null,
                "before": null
              }
            },
            "user_reports": [

            ],
            "saved": false,
            "id": "d3v823r",
            "gilded": 0,
            "archived": false,
            "report_reasons": null,
            "author": "_KoingWolf_",
            "parent_id": "t3_4mfawk",
            "score": 21,
            "approved_by": null,
            "controversiality": 0,
            "body": "I'm so irritated that Netflix removed King of the Hill. It was one of the only shows you could put on, totally random season and episode, and just let it play in the background for hours. ",
            "edited": false,
            "author_flair_css_class": null,
            "downs": 0,
            "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;I&amp;#39;m so irritated that Netflix removed King of the Hill. It was one of the only shows you could put on, totally random season and episode, and just let it play in the background for hours. &lt;/p&gt;\n&lt;/div&gt;",
            "subreddit": "funny",
            "name": "t1_d3v823r",
            "score_hidden": false,
            "stickied": false,
            "created": 1465031042.0,
            "author_flair_text": null,
            "created_utc": 1465002242.0,
            "distinguished": null,
            "mod_reports": [

            ],
            "num_reports": null,
            "ups": 21
          }
        },
        {
          "kind": "t1",
          "data": {
            "subreddit_id": "t5_2qh33",
            "banned_by": null,
            "removal_reason": null,
            "link_id": "t3_4mfawk",
            "likes": null,
            "replies": {
              "kind": "Listing",
              "data": {
                "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                "children": [
                  {
                    "kind": "more",
                    "data": {
                      "count": 16,
                      "parent_id": "t1_d3v85i6",
                      "id": "d3v9pmo",
                      "name": "t1_d3v9pmo",
                      "children": [
                        "d3v9pmo",
                        "d3vax54",
                        "d3vazfu",
                        "d3va9cj",
                        "d3va4zp"
                      ]
                    }
                  }
                ],
                "after": null,
                "before": null
              }
            },
            "user_reports": [

            ],
            "saved": false,
            "id": "d3v85i6",
            "gilded": 0,
            "archived": false,
            "report_reasons": null,
            "author": "xynder0",
            "parent_id": "t3_4mfawk",
            "score": 20,
            "approved_by": null,
            "controversiality": 0,
            "body": "lived in phoenix my entire life (except 2 years which were in Tucson) and just moved to St Louis last year.\n\nyes, it's somewhat humid in St Louis in the summer.\n\nIt does not compare to 117 in Phoenix.  The sun literally \"hurts\" to be on your skin.  so much hurt from burning hot pavement/concrete and playground equipment.  literally, several people die every summer in their sleep from broken A/Cs.\n\nfuck phoenix",
            "edited": 1465004221.0,
            "author_flair_css_class": null,
            "downs": 0,
            "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;lived in phoenix my entire life (except 2 years which were in Tucson) and just moved to St Louis last year.&lt;/p&gt;\n\n&lt;p&gt;yes, it&amp;#39;s somewhat humid in St Louis in the summer.&lt;/p&gt;\n\n&lt;p&gt;It does not compare to 117 in Phoenix.  The sun literally &amp;quot;hurts&amp;quot; to be on your skin.  so much hurt from burning hot pavement/concrete and playground equipment.  literally, several people die every summer in their sleep from broken A/Cs.&lt;/p&gt;\n\n&lt;p&gt;fuck phoenix&lt;/p&gt;\n&lt;/div&gt;",
            "subreddit": "funny",
            "name": "t1_d3v85i6",
            "score_hidden": false,
            "stickied": false,
            "created": 1465031199.0,
            "author_flair_text": null,
            "created_utc": 1465002399.0,
            "distinguished": null,
            "mod_reports": [

            ],
            "num_reports": null,
            "ups": 20
          }
        },
        {
          "kind": "t1",
          "data": {
            "subreddit_id": "t5_2qh33",
            "banned_by": null,
            "removal_reason": null,
            "link_id": "t3_4mfawk",
            "likes": null,
            "replies": "",
            "user_reports": [

            ],
            "saved": false,
            "id": "d3v85vi",
            "gilded": 0,
            "archived": false,
            "report_reasons": null,
            "author": "ItsTrumpsFault",
            "parent_id": "t3_4mfawk",
            "score": 16,
            "approved_by": null,
            "controversiality": 0,
            "body": "As long as you have a bottle of water, it is no more troublesome than walking across an oven.",
            "edited": false,
            "author_flair_css_class": null,
            "downs": 0,
            "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;As long as you have a bottle of water, it is no more troublesome than walking across an oven.&lt;/p&gt;\n&lt;/div&gt;",
            "subreddit": "funny",
            "name": "t1_d3v85vi",
            "score_hidden": false,
            "stickied": false,
            "created": 1465031216.0,
            "author_flair_text": null,
            "created_utc": 1465002416.0,
            "distinguished": null,
            "mod_reports": [

            ],
            "num_reports": null,
            "ups": 16
          }
        },
        {
          "kind": "t1",
          "data": {
            "subreddit_id": "t5_2qh33",
            "banned_by": null,
            "removal_reason": null,
            "link_id": "t3_4mfawk",
            "likes": null,
            "replies": "",
            "user_reports": [

            ],
            "saved": false,
            "id": "d3v827j",
            "gilded": 0,
            "archived": false,
            "report_reasons": null,
            "author": "Kidsturk",
            "parent_id": "t3_4mfawk",
            "score": 14,
            "approved_by": null,
            "controversiality": 0,
            "body": "&gt;Fort Yuma is probably the hottest place on earth. The thermometer stays at one hundred and twenty in the shade there all the time - except when it varies and goes higher. It is a U.S. Military Post, and its occupants get so used to the terrific heat that they suffer without it. There is a tradition...that a very, very wicked soldier died there once, and of course, went straight to the hottest corner of perdition, -- and the next day he telegraphed back for his blankets.\n\n&gt;There can be no doubt about the truth of this statement...I have seen the place where that soldier used to board.\n\n-Mark Twain, *Roughing It*",
            "edited": false,
            "author_flair_css_class": null,
            "downs": 0,
            "body_html": "&lt;div class=\"md\"&gt;&lt;blockquote&gt;\n&lt;p&gt;Fort Yuma is probably the hottest place on earth. The thermometer stays at one hundred and twenty in the shade there all the time - except when it varies and goes higher. It is a U.S. Military Post, and its occupants get so used to the terrific heat that they suffer without it. There is a tradition...that a very, very wicked soldier died there once, and of course, went straight to the hottest corner of perdition, -- and the next day he telegraphed back for his blankets.&lt;/p&gt;\n\n&lt;p&gt;There can be no doubt about the truth of this statement...I have seen the place where that soldier used to board.&lt;/p&gt;\n&lt;/blockquote&gt;\n\n&lt;p&gt;-Mark Twain, &lt;em&gt;Roughing It&lt;/em&gt;&lt;/p&gt;\n&lt;/div&gt;",
            "subreddit": "funny",
            "name": "t1_d3v827j",
            "score_hidden": false,
            "stickied": false,
            "created": 1465031047.0,
            "author_flair_text": null,
            "created_utc": 1465002247.0,
            "distinguished": null,
            "mod_reports": [

            ],
            "num_reports": null,
            "ups": 14
          }
        },
        {
          "kind": "t1",
          "data": {
            "subreddit_id": "t5_2qh33",
            "banned_by": null,
            "removal_reason": null,
            "link_id": "t3_4mfawk",
            "likes": null,
            "replies": {
              "kind": "Listing",
              "data": {
                "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                "children": [
                  {
                    "kind": "more",
                    "data": {
                      "count": 2,
                      "parent_id": "t1_d3v8spe",
                      "id": "d3vclpt",
                      "name": "t1_d3vclpt",
                      "children": [
                        "d3vclpt",
                        "d3v9sjj"
                      ]
                    }
                  }
                ],
                "after": null,
                "before": null
              }
            },
            "user_reports": [

            ],
            "saved": false,
            "id": "d3v8spe",
            "gilded": 0,
            "archived": false,
            "report_reasons": null,
            "author": "nebnamfuak",
            "parent_id": "t3_4mfawk",
            "score": 11,
            "approved_by": null,
            "controversiality": 0,
            "body": "I've been living in Phoenix my whole life and honestly after 95 degrees, you can't tell the difference. You just drink way more water than usual and probably had sunglasses at every phase of life. ",
            "edited": false,
            "author_flair_css_class": null,
            "downs": 0,
            "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;I&amp;#39;ve been living in Phoenix my whole life and honestly after 95 degrees, you can&amp;#39;t tell the difference. You just drink way more water than usual and probably had sunglasses at every phase of life. &lt;/p&gt;\n&lt;/div&gt;",
            "subreddit": "funny",
            "name": "t1_d3v8spe",
            "score_hidden": false,
            "stickied": false,
            "created": 1465032283.0,
            "author_flair_text": null,
            "created_utc": 1465003483.0,
            "distinguished": null,
            "mod_reports": [

            ],
            "num_reports": null,
            "ups": 11
          }
        },
        {
          "kind": "t1",
          "data": {
            "subreddit_id": "t5_2qh33",
            "banned_by": null,
            "removal_reason": null,
            "link_id": "t3_4mfawk",
            "likes": null,
            "replies": {
              "kind": "Listing",
              "data": {
                "modhash": "3br9ts8bywa63af9f3c0bc64bf704a45c723e67e0a5ae35d04",
                "children": [
                  {
                    "kind": "more",
                    "data": {
                      "count": 1,
                      "parent_id": "t1_d3v757z",
                      "id": "d3v93an",
                      "name": "t1_d3v93an",
                      "children": [
                        "d3v93an"
                      ]
                    }
                  }
                ],
                "after": null,
                "before": null
              }
            },
            "user_reports": [

            ],
            "saved": false,
            "id": "d3v757z",
            "gilded": 0,
            "archived": false,
            "report_reasons": null,
            "author": "Sterling_Archer_ISIS",
            "parent_id": "t3_4mfawk",
            "score": 9,
            "approved_by": null,
            "controversiality": 0,
            "body": "I will take the dry heat any day over humidity. I remember being on a ship in the Pacific near the equator, and it was absolutely miserable, you couldn't go one second with out being completely drenched in sweat, and I mean dripping from you clothes wet. I remember it was so bad one day and that the ceiling was dripping and walls were coated with moisture and sweat from all the Marines in my platoon's berthing area(room).   I still hate when it's 110 and above in Arizona, but I hate humidity more.",
            "edited": false,
            "author_flair_css_class": null,
            "downs": 0,
            "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;I will take the dry heat any day over humidity. I remember being on a ship in the Pacific near the equator, and it was absolutely miserable, you couldn&amp;#39;t go one second with out being completely drenched in sweat, and I mean dripping from you clothes wet. I remember it was so bad one day and that the ceiling was dripping and walls were coated with moisture and sweat from all the Marines in my platoon&amp;#39;s berthing area(room).   I still hate when it&amp;#39;s 110 and above in Arizona, but I hate humidity more.&lt;/p&gt;\n&lt;/div&gt;",
            "subreddit": "funny",
            "name": "t1_d3v757z",
            "score_hidden": false,
            "stickied": false,
            "created": 1465029447.0,
            "author_flair_text": null,
            "created_utc": 1465000647.0,
            "distinguished": null,
            "mod_reports": [

            ],
            "num_reports": null,
            "ups": 9
          }
        },
        {
          "kind": "more",
          "data": {
            "count": 762,
            "parent_id": "t3_4mfawk",
            "children": [
              "d3v9onf",
              "d3vaioc",
              "d3v8a31",
              "d3v7spj",
              "d3vc0ez",
              "d3vb02r",
              "d3v86xy",
              "d3v86y1",
              "d3va1bf",
              "d3v8ung",
              "d3vc9wu",
              "d3vco4v",
              "d3vbhgw",
              "d3v9tf6",
              "d3vbkms",
              "d3v9n3q",
              "d3v8euv",
              "d3vc8c8",
              "d3v8jlm",
              "d3v8mzj",
              "d3v8t33",
              "d3v814m",
              "d3v86yv",
              "d3v9wlk",
              "d3v88i4",
              "d3v9jyo",
              "d3vbb6d",
              "d3vbfx3",
              "d3v95qw",
              "d3vawyl",
              "d3v8l7j",
              "d3vbj37",
              "d3v8ew6",
              "d3vb9lz",
              "d3v9ai1",
              "d3v7cz1",
              "d3vae0f",
              "d3v9gts",
              "d3v9wmq",
              "d3vayk4",
              "d3v9ghq",
              "d3v8pyu",
              "d3v9jzw",
              "d3v9476",
              "d3v98xx",
              "d3vakcq",
              "d3v9ifi",
              "d3v9dp1",
              "d3vb1qu",
              "d3v9n6f",
              "d3vacgu",
              "d3vaoqt",
              "d3vc3om",
              "d3vbxd3",
              "d3v3hyi",
              "d3v9v3h",
              "d3v8wby",
              "d3v8xwv",
              "d3v8rll",
              "d3v9tiz",
              "d3vbu81",
              "d3v9rya",
              "d3vcblx",
              "d3v9k21",
              "d3va8f6",
              "d3vbjkd",
              "d3vc5au",
              "d3v97fe",
              "d3v8czz",
              "d3va319",
              "d3v990e",
              "d3v9qe7",
              "d3va7dc",
              "d3vbbab",
              "d3v92p0",
              "d3vaqqw",
              "d3vacn0",
              "d3vae3v",
              "d3vacj5",
              "d3vb08l",
              "d3v88oo",
              "d3vbvuh",
              "d3v9ou9",
              "d3vcete",
              "d3vcgec",
              "d3v9ybp",
              "d3v9gy1",
              "d3vbbbh",
              "d3v9qfk",
              "d3vbua9",
              "d3vbmdx",
              "d3vbbbu",
              "d3v9lin",
              "d3v8lcs",
              "d3va68t",
              "d3v9gym",
              "d3v8i73",
              "d3v8uuc",
              "d3v8ron",
              "d3vam20",
              "d3v8jsa",
              "d3vaazv",
              "d3vbub2",
              "d3vb0ac",
              "d3v9ws6",
              "d3vb0ah",
              "d3v992z",
              "d3v9931",
              "d3vaqta",
              "d3v9iki",
              "d3v8px2",
              "d3vb1vr",
              "d3v916x",
              "d3varef",
              "d3v8bws",
              "d3vc28g",
              "d3v35g2",
              "d3vbz2t",
              "d3v3a6u",
              "d3v9il8",
              "d3v8len",
              "d3vc290",
              "d3v5ko6",
              "d3v8qz5",
              "d3v8div",
              "d3vb3i0",
              "d3vc5ff",
              "d3vb0cc",
              "d3v95z1",
              "d3v8gp6",
              "d3v9qiz",
              "d3v9tp1",
              "d3v9192",
              "d3vavmn",
              "d3v8ae8",
              "d3v9fhd",
              "d3v9fhh",
              "d3vae93",
              "d3v960f",
              "d3v66k5",
              "d3v8aey",
              "d3v5x2z",
              "d3v9qkc",
              "d3va7y8",
              "d3v9nhm",
              "d3v8dl3",
              "d3vaqx4",
              "d3v8uyy",
              "d3v9tqw",
              "d3vb0ex",
              "d3v97mj",
              "d3vanrz",
              "d3v97mq",
              "d3vb92f",
              "d3vaj1p",
              "d3vasj4",
              "d3v97n7",
              "d3v8f7b",
              "d3vbkzd",
              "d3v76yr",
              "d3v968c",
              "d3vb56x",
              "d3vbjez",
              "d3vam8c",
              "d3v9ngu",
              "d3v9ngv",
              "d3vb575",
              "d3v91cs",
              "d3vacrh",
              "d3vbswu",
              "d3vcgm9",
              "d3vcbvr",
              "d3vbw2x",
              "d3vcld6",
              "d3v999o",
              "d3v8ai7",
              "d3vbd4n",
              "d3v8jmn",
              "d3vcgn5",
              "d3v94jm",
              "d3va7ld",
              "d3va3b9",
              "d3v2bm0",
              "d3v9ykz",
              "d3vaslx",
              "d3v8ora",
              "d3vaedz",
              "d3v92zc",
              "d3var18",
              "d3vcgnz",
              "d3vbeqw",
              "d3v8tj2",
              "d3v8tj4",
              "d3v9ww0",
              "d3v9ite",
              "d3vcjut",
              "d3v9fnq",
              "d3v8dqf",
              "d3vcgp4",
              "d3v7lak",
              "d3vbbml",
              "d3v2rha",
              "d3v7ogw",
              "d3v8lnp",
              "d3vbpuu",
              "d3v967d",
              "d3v8lny",
              "d3vbzcg",
              "d3v8otw",
              "d3vc2ie",
              "d3vbpvc",
              "d3vasor",
              "d3v5scq",
              "d3vc5oh",
              "d3vbuma",
              "d3v8amd",
              "d3v8sv5",
              "d3var49",
              "d3v8s0c",
              "d3vcgr1",
              "d3vbrgw",
              "d3vb0lv",
              "d3vame0",
              "d3vbpwi",
              "d3vcgrp",
              "d3vbmqu",
              "d3vabc7",
              "d3v892h",
              "d3v892j",
              "d3vb6y9",
              "d3v9fqv",
              "d3v8qgp",
              "d3var5o",
              "d3v9b0e",
              "d3v8wsj",
              "d3v9nnq",
              "d3v9frb",
              "d3vahoo",
              "d3v7co9",
              "d3vb28c",
              "d3v934g",
              "d3vb9w8",
              "d3v9kib",
              "d3v9vks",
              "d3v8ili",
              "d3v5hkb",
              "d3vb3u4",
              "d3v8ilq",
              "d3v9e7o",
              "d3vao1k",
              "d3v9sg0",
              "d3vc11g",
              "d3vbexr",
              "d3vaelc",
              "d3v7wiv",
              "d3v9cop",
              "d3v8wu8",
              "d3v8uzv",
              "d3vcgwb",
              "d3vad1p",
              "d3vck26",
              "d3vaz69",
              "d3vcn84",
              "d3v902g",
              "d3vb72z",
              "d3v8iou",
              "d3vbrmx",
              "d3va98f",
              "d3vcn8y",
              "d3v87n9",
              "d3vb8om",
              "d3v94u1",
              "d3varay",
              "d3va0m7",
              "d3vaw1o",
              "d3v6rik",
              "d3vao5v",
              "d3vc15h",
              "d3va395",
              "d3va57d",
              "d3v82xn",
              "d3vb2e5",
              "d3v8kbn",
              "d3v9vqi",
              "d3vbwfl",
              "d3v8ved",
              "d3vajg6",
              "d3v8qns",
              "d3v981t",
              "d3vbq4i",
              "d3vaepw",
              "d3v9651",
              "d3vby1e",
              "d3v8nir",
              "d3v982c",
              "d3v8p3r",
              "d3v9068",
              "d3v9maq",
              "d3vaouz",
              "d3vback",
              "d3v8lyd",
              "d3v9eeg",
              "d3va6us",
              "d3v9qmh",
              "d3vaw8k",
              "d3vad6i",
              "d3vba73",
              "d3vaerl",
              "d3v9mbm",
              "d3vbgp1",
              "d3vaa12",
              "d3v9g07",
              "d3v8f93",
              "d3v9hlf",
              "d3vbtcl",
              "d3vaul6",
              "d3v9xej",
              "d3vaw6c",
              "d3v84me",
              "d3v8x2i",
              "d3v9nzx",
              "d3vc3ft",
              "d3vc96g",
              "d3v8kfv",
              "d3v60sc",
              "d3vajjz",
              "d3vaw7a",
              "d3v8m2r",
              "d3vbteg",
              "d3v9nz0",
              "d3vb9ol",
              "d3v91uo",
              "d3v93ft",
              "d3v9bcb",
              "d3vc1bh",
              "d3v90a7",
              "d3vai0g",
              "d3vb0z8",
              "d3v986w",
              "d3v91vf",
              "d3va1ef",
              "d3vaxtp",
              "d3vcatc",
              "d3vbf7n",
              "d3vbjyc",
              "d3v8clm",
              "d3v8vke",
              "d3v7qhd",
              "d3v951y",
              "d3vbie9",
              "d3v9nvm",
              "d3vcaav",
              "d3v96nr",
              "d3v9mgp",
              "d3vbrwa",
              "d3v97oq",
              "d3v835w",
              "d3v9eja",
              "d3v7qix",
              "d3v8he5",
              "d3v9pn8",
              "d3vbwnq",
              "d3v8pav",
              "d3v9o2g",
              "d3v9kwr",
              "d3v7in0",
              "d3v2wpw",
              "d3vb2nf",
              "d3vckdo",
              "d3v89io",
              "d3vak5k",
              "d3v9em3",
              "d3vbk1o",
              "d3vc9c0",
              "d3vb5tt",
              "d3v91z7",
              "d3v8pc6",
              "d3vazig",
              "d3va2bt",
              "d3v8aif",
              "d3v96q4",
              "d3vbv4e",
              "d3vbya9",
              "d3v8q1v",
              "d3valas",
              "d3varmf",
              "d3v9hsq",
              "d3vc317",
              "d3vbal9",
              "d3v84tf",
              "d3vadfe",
              "d3vcayj",
              "d3v9jaj",
              "d3vadfo",
              "d3vb7gl",
              "d3v8yvd",
              "d3v8fwr",
              "d3v7p1r",
              "d3v8yvk",
              "d3vckgh",
              "d3v957a",
              "d3v8xaw",
              "d3vblp8",
              "d3v9eou",
              "d3v8d5n",
              "d3vbwrr",
              "d3v7lwo",
              "d3v9rc9",
              "d3v8koa",
              "d3vbs17",
              "d3vbs1c",
              "d3v8xbn",
              "d3vbtme",
              "d3vart6",
              "d3v90hv",
              "d3v922t",
              "d3vbfeu",
              "d3v9jxr",
              "d3v8slr",
              "d3v9234",
              "d3v93o2",
              "d3vbye2",
              "d3v8pgc",
              "d3v7zzu",
              "d3v93kn",
              "d3v9w4n",
              "d3vbyeh",
              "d3vbk6i",
              "d3vblrg",
              "d3vai99",
              "d3v77qn",
              "d3vb4dx",
              "d3vbzzq",
              "d3v7chl",
              "d3v8vst",
              "d3v94xx",
              "d3va1qt",
              "d3v84xs",
              "d3v5c4r",
              "d3vbtom",
              "d3vbqiv",
              "d3vcm4q",
              "d3v9a1d",
              "d3va42b",
              "d3v9t06",
              "d3v8xeg",
              "d3vcizf",
              "d3v8yzk",
              "d3v9mp1",
              "d3vb2uf",
              "d3vbaqx",
              "d3vce96",
              "d3v8g1b",
              "d3vahen",
              "d3va792",
              "d3v9a2f",
              "d3vafz3",
              "d3vadku",
              "d3va0xs",
              "d3varsx",
              "d3va8ua",
              "d3v83es",
              "d3vaz7a",
              "d3vcklm",
              "d3v9gek",
              "d3v9zdm",
              "d3v9obg",
              "d3v74o0",
              "d3vc7z6",
              "d3v8f0x",
              "d3v9unc",
              "d3vaq96",
              "d3v8zd8",
              "d3vaizj",
              "d3v9a45",
              "d3v8j5s",
              "d3v9bpk",
              "d3v9bps",
              "d3vbdzm",
              "d3vc4us",
              "d3vcfx4",
              "d3vcntn",
              "d3varvi",
              "d3vazs1",
              "d3v9db6",
              "d3vae58",
              "d3vc817",
              "d3v9dbp",
              "d3v8fwl",
              "d3vbni1",
              "d3v9ewt",
              "d3vceda",
              "d3v8sqe",
              "d3v970n",
              "d3v9rkf",
              "d3v83io",
              "d3v8d09",
              "d3va12f",
              "d3v8vzd",
              "d3v8vzh",
              "d3v9a7l",
              "d3vbh84",
              "d3vblyv",
              "d3v9eyh",
              "d3vbsai",
              "d3v9jp7",
              "d3vcj5o",
              "d3v9zxm",
              "d3valmn",
              "d3vazuo",
              "d3vb1fm",
              "d3vckqr",
              "d3vaih0",
              "d3v9xxi",
              "d3v8ufb",
              "d3v9ziq",
              "d3vcmcc",
              "d3v8ra9",
              "d3vatkc",
              "d3vb31p",
              "d3v9oha",
              "d3vafcg",
              "d3vccvt",
              "d3vbx31",
              "d3vb4n4",
              "d3v9mwz",
              "d3vbyo6",
              "d3vb7t4",
              "d3v8kzt",
              "d3vawqy",
              "d3vceh7",
              "d3vb4ni",
              "d3vak3z",
              "d3v8575",
              "d3vb4nn",
              "d3v9bv8",
              "d3vcehl",
              "d3va923",
              "d3vawri",
              "d3v8d47",
              "d3v96po",
              "d3vbnn0",
              "d3vbx4g",
              "d3vagz9",
              "d3v9wft",
              "d3vaqgq",
              "d3vayd7",
              "d3v9dh8",
              "d3vbnni",
              "d3vazi9",
              "d3vbclk",
              "d3v8l1n",
              "d3v8psd",
              "d3vaikv",
              "d3vakof",
              "d3v9y1n",
              "d3va4dd",
              "d3v9jw1",
              "d3vafrb",
              "d3vayem",
              "d3vckvm",
              "d3v9dis",
              "d3v8kv2",
              "d3v8erc",
              "d3v8ji1",
              "d3vc6nx",
              "d3vbqv4",
              "d3v8szn",
              "d3vb6bq",
              "d3v91zg",
              "d3valk2",
              "d3v9tcm",
              "d3v8mon",
              "d3vb4rh",
              "d3vak7z",
              "d3vb7xt",
              "d3vcely",
              "d3v8w6w",
              "d3v7vui",
              "d3vab11"
            ],
            "name": "t1_d3v9onf",
            "id": "d3v9onf"
          }
        }
      ],
      "after": null,
      "before": null
    }
  }
]
"""