package com.example.zen.kotlinreddit

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.example.zen.kotlinreddit.activities.PostsActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class TestPosts {

	@Rule @JvmField
	val activity = ActivityTestRule<PostsActivity>(PostsActivity::class.java)

	@Test
	fun basicTest() {
		onView(withId(R.id.toolbar_title)).check(matches(withText("Hot")))

		onView(withId(R.id.rv)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))

//		onView(withText(startsWith("Invalid"))).check(matches(isDisplayed()))
//		onView(withId(android.R.id.button1)).perform(click())
//		onView(withId(R.id.arg1)).perform(typeText("12345"))
//		onView(withId(R.id.arg2)).perform(typeText("54321"))
//		onView(withId(R.id.calcButton)).perform(click())
//		onView(withId(R.id.answer)).check(matches(withText("66666")))

	}


}