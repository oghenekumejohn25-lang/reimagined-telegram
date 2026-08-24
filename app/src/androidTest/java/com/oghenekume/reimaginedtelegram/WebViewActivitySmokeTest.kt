package com.oghenekume.reimaginedtelegram

import android.webkit.WebView
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.oghenekume.reimaginedtelegram.web.WebViewActivity
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom

@RunWith(AndroidJUnit4::class)
class WebViewActivitySmokeTest {
    @Test
    fun approvedDestinationCreatesVisibleWebView() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        ActivityScenario.launch<WebViewActivity>(WebViewActivity.newIntent(context)).use {
            onView(isAssignableFrom(WebView::class.java)).check(matches(isDisplayed()))
        }
    }
}
