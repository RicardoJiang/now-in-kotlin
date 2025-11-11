package com.jiang.nowinkotlin.features.webview.component

import com.jiang.nowinkotlin.features.webview.component.WebViewState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.readValue
import platform.CoreGraphics.CGRectZero
import platform.WebKit.WKAudiovisualMediaTypeNone
import platform.WebKit.WKWebView
import platform.WebKit.WKWebViewConfiguration

/**
 * iOS WebView implementation.
 */
@Composable
actual fun ActualWebView(
    state: WebViewState,
    modifier: Modifier
) {
    IOSWebView(
        state,
        modifier
    )
}

/**
 * iOS WebView implementation.
 */
@OptIn(ExperimentalForeignApi::class, ExperimentalComposeUiApi::class)
@Composable
fun IOSWebView(
    state: WebViewState,
    modifier: Modifier
) {
    // 创建 navigationDelegate 来监听页面加载状态
    val navigationDelegate = remember(state) { WebViewNavigationDelegate(state) }

    UIKitView(
        factory = {
            val config = WKWebViewConfiguration().apply {
                allowsInlineMediaPlayback = true
                mediaTypesRequiringUserActionForPlayback = WKAudiovisualMediaTypeNone
                defaultWebpagePreferences.allowsContentJavaScript = true
            }
            WKWebView(
                frame = CGRectZero.readValue(),
                configuration = config,
            ).apply {
                this.userInteractionEnabled = true // Explicitly enable user interaction

                // 设置 navigationDelegate 以监听页面加载状态
                this.navigationDelegate = navigationDelegate

                with(scrollView) {
                    bounces = false
                    scrollEnabled = true
                    showsHorizontalScrollIndicator = false
                    showsVerticalScrollIndicator = true
                }
            }.also {
                val iosWebView = IOSWebView(it)
                state.webView = iosWebView
            }
        },
        modifier = modifier,
        onRelease = { webView ->
            // 清理 navigationDelegate
            webView.navigationDelegate = null
        }
    )
}

