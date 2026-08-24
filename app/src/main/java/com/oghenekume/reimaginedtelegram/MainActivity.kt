package com.oghenekume.reimaginedtelegram

import android.app.Activity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.oghenekume.reimaginedtelegram.web.WebViewActivity

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(
            LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                gravity = Gravity.CENTER
                setPadding(48, 48, 48, 48)

                addView(
                    TextView(this@MainActivity).apply {
                        gravity = Gravity.CENTER
                        text = "Oghenekume Digital Hub"
                        textSize = 24f
                    },
                )

                addView(
                    Button(this@MainActivity).apply {
                        text = "Open website"
                        setOnClickListener {
                            startActivity(WebViewActivity.newIntent(this@MainActivity))
                        }
                    },
                )
            },
        )
    }
}
