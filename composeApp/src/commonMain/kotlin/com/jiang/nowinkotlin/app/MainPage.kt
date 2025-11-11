
package com.jiang.nowinkotlin.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.jiang.nowinkotlin.core.navigation.NavigatorHost
import com.jiang.nowinkotlin.core.navigation.AppMainScreen
import com.tencent.kmm.network.service.VBTransportServiceTest

/**
 * 主页面入口 Composable。
 * 现在它的职责非常单一：初始化必要的服务并启动导航容器。
 */
@Composable
internal fun MainPage(skiaRender: Boolean = true) {
    // LaunchedEffect 仍然可以用来执行只需要在组件首次进入组合时运行一次的逻辑，
    // 比如初始化网络服务。
    LaunchedEffect(Unit) {
        VBTransportServiceTest.testServiceInit()
    }

    // 启动导航容器，并指定我们的 AppMainScreen 作为初始页面。
    // 所有导航相关的复杂逻辑（返回栈、动画、状态保持）都由 NavigatorHost 内部处理。
    NavigatorHost(initialScreen = AppMainScreen)
}
