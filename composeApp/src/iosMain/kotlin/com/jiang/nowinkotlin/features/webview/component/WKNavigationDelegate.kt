package com.jiang.nowinkotlin.features.webview.component

import com.jiang.nowinkotlin.features.webview.component.WebViewState
import com.jiang.nowinkotlin.features.webview.component.LoadingState
import kotlinx.cinterop.ObjCSignatureOverride
import platform.WebKit.WKNavigation
import platform.WebKit.WKNavigationDelegateProtocol
import platform.WebKit.WKWebView
import platform.darwin.NSObject

/**
 * iOS WKWebView Navigation Delegate
 * 用于监听 WKWebView 的页面加载状态并更新 WebViewState
 */
@Suppress("CONFLICTING_OVERLOADS")
class WebViewNavigationDelegate(
    private val state: WebViewState
) : NSObject(), WKNavigationDelegateProtocol {

    /**
     * Called when the web view begins to receive web content.
     * 当 WebView 开始接收网页内容时调用
     */
    @ObjCSignatureOverride
    override fun webView(
        webView: WKWebView,
        didStartProvisionalNavigation: WKNavigation?
    ) {
        // 页面开始加载，设置状态为 Loading
        state.loadingState = LoadingState.Loading
    }

    /**
     * Called when the web view finishes loading.
     * 当 WebView 完成加载时调用
     */
    @ObjCSignatureOverride
    override fun webView(
        webView: WKWebView,
        didFinishNavigation: WKNavigation?
    ) {
        // 页面加载完成，设置状态为 Finished
        state.loadingState = LoadingState.Finished
    }
}
