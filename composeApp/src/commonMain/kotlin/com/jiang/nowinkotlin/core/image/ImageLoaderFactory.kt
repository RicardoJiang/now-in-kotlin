package com.jiang.nowinkotlin.core.image

import coil3.ImageLoader
import coil3.PlatformContext
import coil3.memory.MemoryCache
import coil3.request.crossfade
import coil3.util.DebugLogger

/**
 * 创建配置好的 ImageLoader 实例
 */
fun createImageLoader(context: PlatformContext): ImageLoader {
    return ImageLoader.Builder(context)
        .components {
            // 添加自定义的 KmpNetworkFetcher
            add(KmpNetworkFetcher.Factory())
        }
        .memoryCache {
            MemoryCache.Builder()
                .maxSizePercent(context, 0.25) // 使用 25% 的可用内存
                .build()
        }
        .logger(DebugLogger()) // 开发时启用日志
        .build()
}
