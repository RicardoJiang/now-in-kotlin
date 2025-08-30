package com.jiang.nowinkotlin.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.CoroutineScope

interface LifecycleAware {
    fun onCreate()

    fun onDestroy()
}

@Composable
fun <T : LifecycleAware> rememberLifecycleAware(
    // producer 是一个生产（创建）我们对象的 lambda
    producer: (scope: CoroutineScope) -> T
): T {
    val scope = rememberCoroutineScope()
    // 1. 使用 remember 创建并记住实例
    val instance = remember { producer(scope) }

    // 2. 绑定通用的 onStart/onStop 生命周期
    DisposableEffect(instance) {
        instance.onCreate()
        onDispose {
            instance.onDestroy()
        }
    }

    // 3. 返回实例
    return instance
}
