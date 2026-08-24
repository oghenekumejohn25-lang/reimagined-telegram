package com.oghenekume.reimaginedtelegram.web

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.http.SslError
import android.os.Bundle
import android.view.Gravity
import android.webkit.SslErrorHandler
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView

class WebViewActivity : Activity() {
    private var webView: WebView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val initialUrl = intent.getStringExtra(EXTRA_INITIAL_URL).orEmpty()
        val allowedHosts = intent.getStringArrayListExtra(EXTRA_ALLOWED_HOSTS)
            ?.toSet()
            .orEmpty()

        if (!WebViewUrlPolicy.isAllowed(initialUrl, allowedHosts)) {
            showBlockedContentMessage()
            return
        }

        webView = WebView(this).apply {
            settings.apply {
                javaScriptEnabled = false
                domStorageEnabled = false
                allowFileAccess = false
                allowContentAccess = false
                mixedContentMode = WebSettings.MIXED_CONTENT_NEVER_ALLOW
                setSupportMultipleWindows(false)
                javaScriptCanOpenWindowsAutomatically = false
                safeBrowsingEnabled = true
            }

            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView,
                    request: WebResourceRequest,
                ): Boolean = !WebViewUrlPolicy.isAllowed(request.url.toString(), allowedHosts)

                override fun onReceivedSslError(
                    view: WebView,
                    handler: SslErrorHandler,
                    error: SslError,
                ) {
                    handler.cancel()
                    showBlockedContentMessage()
                }
            }
        }

        setContentView(webView)
        webView?.loadUrl(initialUrl)
    }

    @Suppress("DEPRECATION")
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val activeWebView = webView
        if (activeWebView?.canGoBack() == true) {
            activeWebView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        webView?.apply {
            stopLoading()
            loadUrl("about:blank")
            destroy()
        }
        webView = null
        super.onDestroy()
    }

    private fun showBlockedContentMessage() {
        setContentView(
            TextView(this).apply {
                gravity = Gravity.CENTER
                text = "Web content is unavailable because no approved HTTPS destination was supplied."
                textSize = 18f
                setPadding(48, 48, 48, 48)
            },
        )
    }

    companion object {
        private const val EXTRA_INITIAL_URL = "initial_url"
        private const val EXTRA_ALLOWED_HOSTS = "allowed_hosts"

        const val DEFAULT_URL = "https://www.oghenekumejohn.online/"
        val DEFAULT_ALLOWED_HOSTS: Set<String> = setOf("www.oghenekumejohn.online")

        fun newIntent(context: Context): Intent = newIntent(
            context = context,
            initialUrl = DEFAULT_URL,
            allowedHosts = DEFAULT_ALLOWED_HOSTS,
        )

        fun newIntent(
            context: Context,
            initialUrl: String,
            allowedHosts: Set<String>,
        ): Intent = Intent(context, WebViewActivity::class.java).apply {
            putExtra(EXTRA_INITIAL_URL, initialUrl)
            putStringArrayListExtra(EXTRA_ALLOWED_HOSTS, ArrayList(allowedHosts))
        }
    }
}
