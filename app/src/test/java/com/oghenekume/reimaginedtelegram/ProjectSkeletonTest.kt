package com.oghenekume.reimaginedtelegram

import org.junit.Assert.assertEquals
import org.junit.Test

class ProjectSkeletonTest {
    @Test
    fun applicationIdentifierIsStable() {
        assertEquals(
            "com.oghenekume.reimaginedtelegram",
            BuildConfig.APPLICATION_ID,
        )
    }
}
