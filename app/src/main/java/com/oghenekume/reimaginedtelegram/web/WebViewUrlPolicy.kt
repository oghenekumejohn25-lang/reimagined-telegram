package com.oghenekume.reimaginedtelegram.web

import java.net.URI
import java.util.Locale

/**
 * Allows only HTTPS navigation to hosts explicitly approved by the caller.
 * Subdomains are not implicitly trusted.
 */
object WebViewUrlPolicy {
    fun isAllowed(url: String, allowedHosts: Set<String>): Boolean {
        val normalizedHosts = allowedHosts.map { it.lowercase(Locale.ROOT) }.toSet()
        val uri = runCatching { URI(url) }.getOrNull() ?: return false
        val host = uri.host?.lowercase(Locale.ROOT) ?: return false

        return uri.scheme.equals("https", ignoreCase = true) && host in normalizedHosts
    }
}
