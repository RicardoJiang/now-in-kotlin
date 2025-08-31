package com.jiang.nowinkotlin.webview

import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun AccompanistWebView(
    state: WebViewState,
    modifier: Modifier
) {
    AndroidView(
        factory = { context ->
            WebView(context)
                .apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    webViewClient = WebViewClient()
                    this.setLayerType(View.LAYER_TYPE_HARDWARE, null)

                    settings.apply {
                        javaScriptEnabled = true
                    }
                }.also {
                    val androidWebView = AndroidWebView(it)
                    state.webView = androidWebView
                }
        },
        modifier = modifier
    )
}
