package com.oghenekume.reimaginedtelegram.web

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class WebViewUrlPolicyTest {
    private val allowedHosts = setOf("support.example.com")

    @Test
    fun allowsHttpsUrlForApprovedHost() {
        assertTrue(WebViewUrlPolicy.isAllowed("https://support.example.com/help", allowedHosts))
    }

    @Test
    fun rejectsCleartextUrl() {
        assertFalse(WebViewUrlPolicy.isAllowed("http://support.example.com/help", allowedHosts))
    }

    @Test
    fun rejectsUnapprovedHostAndSubdomain() {
        assertFalse(WebViewUrlPolicy.isAllowed("https://example.com/help", allowedHosts))
        assertFalse(WebViewUrlPolicy.isAllowed("https://sub.support.example.com/help", allowedHosts))
    }

    @Test
    fun rejectsMalformedUrl() {
        assertFalse(WebViewUrlPolicy.isAllowed("not a url", allowedHosts))
    }

    @Test
    fun defaultDestinationIsAllowedByItsExactHostPolicy() {
        assertTrue(
            WebViewUrlPolicy.isAllowed(
                WebViewActivity.DEFAULT_URL,
                WebViewActivity.DEFAULT_ALLOWED_HOSTS,
            ),
        )
    }
}
