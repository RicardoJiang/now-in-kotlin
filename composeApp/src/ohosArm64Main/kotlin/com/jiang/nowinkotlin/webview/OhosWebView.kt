package com.jiang.nowinkotlin.webview


actual typealias NativeWebView = Unit

class IOSWebView(
    override val webView: Unit,
) : IWebView {

    override fun loadUrl(
        url: String,
    ) {

    }
}
