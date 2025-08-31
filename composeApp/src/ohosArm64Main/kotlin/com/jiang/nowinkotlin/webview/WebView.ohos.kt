package com.jiang.nowinkotlin.webview

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.napi.js
import com.jiang.nowinkotlin.ArkUIView
import kotlinx.cinterop.ExperimentalForeignApi

/**
 * iOS WebView implementation.
 */
@Composable
actual fun ActualWebView(
    state: WebViewState,
    modifier: Modifier
) {
    OhosWebView(
        state,
        modifier
    )
}

/**
 * iOS WebView implementation.
 */
@OptIn(ExperimentalForeignApi::class, ExperimentalComposeUiApi::class)
@Composable
fun OhosWebView(
    state: WebViewState,
    modifier: Modifier
) {
    ArkUIView(
        name = "harmonyWebView",
        modifier = modifier,
        parameter = js {
            "url"(state.webUrl)
            "backgroundColor"("#FF0000FF")
        },
    )
}

