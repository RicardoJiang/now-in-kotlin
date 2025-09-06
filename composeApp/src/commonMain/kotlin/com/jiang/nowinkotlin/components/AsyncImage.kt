package com.jiang.nowinkotlin.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import com.jiang.nowinkotlin.imageBitmapFromBytes
import com.jiang.nowinkotlin.network.KmpNetworkHelper
import com.jiang.nowinkotlin.rememberLocalImage
import com.tencent.kmm.network.export.VBTransportGetRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi

// 1. 定义图片加载的几种可能状态
private sealed interface AsyncImageState {
    object Loading : AsyncImageState
    data class Success(val bitmap: ImageBitmap) : AsyncImageState
    object Error : AsyncImageState
}

private val asyncImageCache = hashMapOf<String, AsyncImageState>()

private suspend fun fetchImageForGetRequest(
    url: String, logTag: String = "fetchImageForGetRequest", useCurl: Boolean = true
): ByteArray? {
    try {
        val getRequest = VBTransportGetRequest()
        getRequest.url = url
        getRequest.logTag = logTag
        getRequest.header = mutableMapOf("Accept" to "image/*", "Content-Type" to "image/jpeg")
        getRequest.useCurl = useCurl
        val result = KmpNetworkHelper.sendGetRequest(getRequest)
        // 响应数据
        val byteArray = result.data as? ByteArray
        return byteArray
    } catch (e: Exception) {
        println("fetchImageForGetRequest exception: ${e.message}")
        return null
    }
}


/**
 * 一个可以异步加载并显示网络图片的 Composable 组件。
 *
 * @param url 图片的 URL 地址。
 * @param contentDescription 图片的内容描述，用于辅助功能。
 * @param modifier 应用于此组件的 Modifier。
 * @param contentScale 图片的缩放方式。
 */
@OptIn(ExperimentalResourceApi::class)
@Composable
fun AsyncImage(
    url: String,
    placeHodler: DrawableResource,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit
) {
    // 2. 使用 remember 管理当前状态
    var state: AsyncImageState by remember(url) { mutableStateOf(AsyncImageState.Loading) }

    // 3. 使用 LaunchedEffect 启动异步加载
    // 当 url 变化时，这个协程会自动取消并重新启动
    LaunchedEffect(url) {
        val cacheState = asyncImageCache.get(url)
        if (cacheState != null) {
            if (cacheState != AsyncImageState.Loading) {
                state = cacheState
            }
            return@LaunchedEffect
        }
        asyncImageCache.set(url, AsyncImageState.Loading)
        val byteArray: ByteArray? = withContext(Dispatchers.IO) {
            fetchImageForGetRequest(url)
        }

        // 4. 处理下载结果
        if (byteArray != null) {
            try {
                val bitmap = imageBitmapFromBytes(byteArray)
                state = AsyncImageState.Success(bitmap)
            } catch (e: Exception) {
                // 解码失败
                e.printStackTrace()
                state = AsyncImageState.Error
            }
        } else {
            // 下载失败
            state = AsyncImageState.Error
        }
        asyncImageCache.set(url, state)
    }

    DisposableEffect(url) {
        onDispose {
            val cacheState = asyncImageCache.get(url)
            if (cacheState == AsyncImageState.Loading) {
                asyncImageCache.remove(url)
            }
        }
    }

    // 5. 根据当前状态显示不同的 UI
    Box(modifier = modifier) { // 使用 Box 来确保占位符和图片尺寸一致
        when (val currentState = state) {
            is AsyncImageState.Loading, AsyncImageState.Error -> {
                // 加载中
                Image(
                    bitmap = rememberLocalImage(placeHodler),
                    modifier = Modifier.matchParentSize().background(Color.LightGray),
                    contentScale = ContentScale.Crop,
                    contentDescription = "placeholder"
                )
            }

            is AsyncImageState.Success -> {
                Image(
                    bitmap = currentState.bitmap,
                    contentDescription = contentDescription,
                    modifier = Modifier.matchParentSize(), // 图片填满 Box
                    contentScale = contentScale
                )
            }
        }
    }
}
