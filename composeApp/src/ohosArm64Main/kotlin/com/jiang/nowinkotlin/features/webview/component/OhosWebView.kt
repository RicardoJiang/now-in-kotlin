package com.jiang.nowinkotlin.features.webview.component


actual typealias NativeWebView = Unit

class IOSWebView(
    override val webView: Unit,
) : IWebView {

    override fun loadUrl(
        url: String,
    ) {

    }
}
