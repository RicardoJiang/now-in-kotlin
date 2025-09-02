package com.jiang.nowinkotlin.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
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
import com.tencent.kmm.network.export.VBTransportGetRequest
import com.tencent.kmm.network.export.VBTransportResultCode
import com.tencent.kmm.network.service.VBTransportService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume

// 1. 定义图片加载的几种可能状态
private sealed interface AsyncImageState {
    object Loading : AsyncImageState
    data class Success(val bitmap: ImageBitmap) : AsyncImageState
    object Error : AsyncImageState
}

private val byteArrayCacheMap = hashMapOf<String, ByteArray>()

private suspend fun fetchImageForGetRequest(
    url: String, logTag: String = "fetchImageForGetRequest", useCurl: Boolean = true
): ByteArray? = suspendCancellableCoroutine { continuation ->
    try {
        byteArrayCacheMap.get(url)?.let {
            continuation.resume(it)
            return@suspendCancellableCoroutine
        }
        val getRequest = VBTransportGetRequest()
        getRequest.url = url
        getRequest.logTag = logTag
        getRequest.header = mutableMapOf("Accept" to "image/*", "Content-Type" to "image/jpeg")
        getRequest.useCurl = useCurl
        VBTransportService.sendGetRequest(getRequest) { result ->
            // 结果码
            val code = result.errorCode
            // 网络任务在取消后会明确告知业务方取消的错误码
            if (code == VBTransportResultCode.CODE_CANCELED) {
                println("[TRACE] [${logTag}] request is canceled.")
            } else {
                // 响应数据
                val byteArray = result.data as? ByteArray
                // 后续ByteArray的处理逻辑，比如存储或者显示等
                if (byteArray != null) {
                    byteArrayCacheMap.put(url, byteArray)
                    continuation.resume(byteArray)
                }
                println(
                    "[TRACE] [${logTag}] fetch image result, code: ${code}, " + "size: ${byteArray?.size}, header:${result.header}"
                )
            }
        }
    } catch (e: Exception) {
        continuation.resume(null)
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
@Composable
fun AsyncImage(
    url: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit
) {
    // 2. 使用 remember 管理当前状态
    var state: AsyncImageState by remember(url) { mutableStateOf(AsyncImageState.Loading) }

    // 3. 使用 LaunchedEffect 启动异步加载
    // 当 url 变化时，这个协程会自动取消并重新启动
    LaunchedEffect(url) {
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
    }

    // 5. 根据当前状态显示不同的 UI
    Box(modifier = modifier) { // 使用 Box 来确保占位符和图片尺寸一致
        when (val currentState = state) {
            is AsyncImageState.Loading -> {
                // 加载时显示一个灰色占位符
                Box(modifier = Modifier.matchParentSize().background(Color.LightGray))
            }

            is AsyncImageState.Success -> {
                Image(
                    bitmap = currentState.bitmap,
                    contentDescription = contentDescription,
                    modifier = Modifier.matchParentSize(), // 图片填满 Box
                    contentScale = contentScale
                )
            }

            is AsyncImageState.Error -> {
                // 失败时显示一个深灰色占位符
                Box(modifier = Modifier.matchParentSize().background(Color.DarkGray))
            }
        }
    }
}
