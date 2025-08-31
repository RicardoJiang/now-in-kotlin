package com.jiang.nowinkotlin.webview

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.cValue
import kotlinx.cinterop.readValue
import platform.CoreGraphics.CGRectZero
import platform.Foundation.NSOperatingSystemVersion
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
        modifier = modifier
    )
}

