package com.jiang.nowinkotlin.core.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.zIndex
import com.jiang.nowinkotlin.features.audio.player.KmpAudioPlayer
import com.jiang.nowinkotlin.core.platform.BackHandler
import com.jiang.nowinkotlin.core.platform.LocalPlatformContext

/**
 * 定义一个屏幕需要实现的接口。
 * 每个可导航的页面都是一个 Screen。
 */
interface Screen {
    @Composable
    fun Content()
}

/**
 * 导航器类，负责管理导航栈。
 * @param initialScreen 初始屏幕。
 */
class Navigator(initialScreen: Screen) {
    // 使用 mutableStateOf 来持有导航栈，这样当它变化时，Compose 会自动触发重组。
    var stack by mutableStateOf(listOf(initialScreen))
        private set

    /**
     * 入栈一个新屏幕，实现页面跳转。
     */
    fun push(screen: Screen) {
        stack = stack + screen
    }

    /**
     * 弹出栈顶屏幕，实现返回上一页。
     */
    fun pop() {
        if (stack.size > 1) { // 防止弹出最后一个屏幕
            stack = stack.take(stack.size - 1)
        }
    }
}

/**
 * 通过 CompositionLocal 将 Navigator 实例向下传递。
 * 这样，任何子 Composable 都可以通过 `LocalNavigator.current` 获取到导航器实例。
 */
val LocalNavigator: ProvidableCompositionLocal<Navigator?> = staticCompositionLocalOf { null }

/**
 * 导航容器 Composable。
 * 它持有 Navigator 实例，并负责显示当前栈顶的屏幕。
 * @param initialScreen 应用程序的起始屏幕。
 */
@Composable
fun NavigatorHost(initialScreen: Screen) {
    val navigator = remember { Navigator(initialScreen) }

    val context = LocalPlatformContext.current

    LaunchedEffect(Unit) {
        KmpAudioPlayer.init(context)
    }

    BackHandler(navigator.stack.size > 1) {
        navigator.pop()
    }

    Box(Modifier.fillMaxSize()) {
        CompositionLocalProvider(LocalNavigator provides navigator) {
            // 遍历导航栈中的所有屏幕
            navigator.stack.forEachIndexed { index, screen ->
                // 使用 key 来为每个屏幕的 Composable 提供一个稳定的身份，这是状态保持的关键！
                key(screen) {
                    // 使用 zIndex 来确保栈顶的屏幕绘制在最上层。
                    // 所有屏幕都在组件树中，但只有 zIndex 最高的那个可见。
                    Box(modifier = Modifier.fillMaxSize().zIndex(index.toFloat())) {
                        screen.Content()
                    }
                }
            }
        }
    }
}
