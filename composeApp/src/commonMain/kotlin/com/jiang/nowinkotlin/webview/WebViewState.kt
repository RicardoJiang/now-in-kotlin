package com.jiang.nowinkotlin.webview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

class WebViewState(
    url: String
) {
    /**
     *  The content being loaded by the WebView
     */
    var webUrl: String by mutableStateOf(url)


    /**
     * Whether the WebView should capture back presses and navigate back.
     * We need access to this in the state saver. An internal DisposableEffect or AndroidView
     * onDestroy is called after the state saver and so can't be used.
     */
    internal var webView by mutableStateOf<IWebView?>(null)

    /**
     * The native web view instance. On Android, this is an instance of [android.webkit.WebView].
     * On iOS, this is an instance of [WKWebView]. On desktop, this is an instance of [KCEFBrowser].
     */
    val nativeWebView get() = webView?.webView ?: error("WebView is not initialized")
}


@Composable
fun rememberWebViewState(
    url: String,
): WebViewState =
// Rather than using .apply {} here we will recreate the state, this prevents
    // a recomposition loop when the webview updates the url itself.
    remember {
        WebViewState(url)
    }
