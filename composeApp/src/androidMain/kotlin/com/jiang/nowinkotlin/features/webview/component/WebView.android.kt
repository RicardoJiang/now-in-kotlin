package com.jiang.nowinkotlin.features.webview.component

import com.jiang.nowinkotlin.features.webview.component.WebViewState
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
