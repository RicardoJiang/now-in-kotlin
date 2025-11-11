package com.jiang.nowinkotlin.features.webview.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier


@Composable
fun WebView(
    state: WebViewState,
    modifier: Modifier
) {
    state.webView?.let { wv ->
        LaunchedEffect(wv, state) {
            snapshotFlow { state.webUrl }.collect { content ->
                wv.loadUrl(content)
            }
        }
    }
    ActualWebView(
        state = state,
        modifier = modifier
    )
}

/**
 * Expect API of [WebView] that is implemented in the platform-specific modules.
 */
@Composable
expect fun ActualWebView(
    state: WebViewState,
    modifier: Modifier
)
