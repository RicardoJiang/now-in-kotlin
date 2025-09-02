package com.jiang.nowinkotlin.webview

import android.webkit.WebView

actual typealias NativeWebView = WebView

/**
 * Android implementation of [IWebView]
 */
class AndroidWebView(
    override val webView: WebView,
) : IWebView {
    override fun loadUrl(url: String) {
        webView.loadUrl(url)
    }
}
