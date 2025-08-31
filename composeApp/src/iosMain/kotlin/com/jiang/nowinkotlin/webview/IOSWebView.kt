package com.jiang.nowinkotlin.webview

import platform.Foundation.NSMutableURLRequest
import platform.Foundation.NSURL
import platform.WebKit.WKWebView

actual typealias NativeWebView = WKWebView

class IOSWebView(
    override val webView: WKWebView,
) : IWebView {

    override fun loadUrl(
        url: String,
    ) {
        // Handle regular HTTP/HTTPS URLs
        val request = NSMutableURLRequest.requestWithURL(URL = NSURL(string = url))
        webView.loadRequest(request = request)
    }
}
