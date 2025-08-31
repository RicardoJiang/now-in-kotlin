package com.jiang.nowinkotlin.webview

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Android WebView implementation.
 */
@Composable
actual fun ActualWebView(
    state: WebViewState,
    modifier: Modifier
) {
    AccompanistWebView(
        state,
        modifier
    )
}
