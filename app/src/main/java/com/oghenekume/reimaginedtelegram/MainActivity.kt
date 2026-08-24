package com.oghenekume.reimaginedtelegram

import android.app.Activity
import android.os.Bundle
import android.view.Gravity
import android.widget.TextView

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(
            TextView(this).apply {
                gravity = Gravity.CENTER
                text = "Android project skeleton is ready.\n\nHubSpot or WebView integration will be added after the issue is reproducible and requirements are approved."
                textSize = 18f
                setPadding(48, 48, 48, 48)
            },
        )
    }
}
