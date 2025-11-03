package com.jiang.nowinkotlin.webview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

/**
 * Sealed class for constraining possible loading states.
 */
sealed class LoadingState {
    /**
     * Describes a WebView that has not yet loaded for the first time.
     */
    data object Initializing : LoadingState()

    /**
     * Describes a webview between onPageStarted and onPageFinished events.
     */
    data object Loading : LoadingState()

    /**
     * Describes a webview that has finished loading content.
     */
    data object Finished : LoadingState()
}

class WebViewState(
    url: String
) {
    /**
     *  The content being loaded by the WebView
     */
    var webUrl: String by mutableStateOf(url)

    /**
     * The loading state of the WebView.
     */
    var loadingState: LoadingState by mutableStateOf(LoadingState.Initializing)
        internal set

    /**
     * Whether the page has finished loading.
     * Returns true only when loadingState is Finished.
     */
    val isPageFinished: Boolean
        get() = loadingState is LoadingState.Finished

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
