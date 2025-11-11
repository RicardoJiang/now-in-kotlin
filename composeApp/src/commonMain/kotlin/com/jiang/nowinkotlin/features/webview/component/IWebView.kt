package com.jiang.nowinkotlin.features.webview.component

import kotlinx.coroutines.CoroutineScope
import org.jetbrains.compose.resources.ExperimentalResourceApi


expect class NativeWebView
/**
 * Interface for WebView
 */
interface IWebView {
    /**
     * The native web view instance. On Android, this is an instance of [android.webkit.WebView].
     * On iOS, this is an instance of [WKWebView]. On desktop, this is an instance of [KCEFBrowser].
     */
    val webView: NativeWebView

    /**
     * Loads the given URL.
     *
     * @param url The URL of the resource to load.
     */
    fun loadUrl(
        url: String
    )
}
