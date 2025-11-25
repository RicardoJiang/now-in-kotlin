package com.jiang.nowinkotlin.core.image

import coil3.ImageLoader
import coil3.Uri
import coil3.decode.DataSource
import coil3.decode.ImageSource
import coil3.fetch.FetchResult
import coil3.fetch.Fetcher
import coil3.fetch.SourceFetchResult
import coil3.request.Options
import coil3.toUri
import com.jiang.nowinkotlin.core.network.KmpNetworkHelper
import com.tencent.kmm.network.export.VBTransportGetRequest
import okio.Buffer

/**
 * 自定义 Coil Fetcher，使用 KmpNetworkHelper 加载网络图片
 */
class KmpNetworkFetcher(
    private val url: String,
    private val options: Options
) : Fetcher {

    override suspend fun fetch(): FetchResult {
        val getRequest = VBTransportGetRequest()
        getRequest.url = url
        getRequest.logTag = "KmpNetworkFetcher"
        getRequest.header = mutableMapOf("Accept" to "image/*")
        getRequest.useCurl = true

        val result = KmpNetworkHelper.sendGetRequest(getRequest)
        val byteArray = result.data as? ByteArray
            ?: throw IllegalStateException("Failed to load image from $url")

        // 将 ByteArray 转换为 Okio Buffer
        val buffer = Buffer().write(byteArray)

        return SourceFetchResult(
            source = ImageSource(
                source = buffer,
                fileSystem = options.fileSystem
            ),
            mimeType = null,
            dataSource = DataSource.NETWORK
        )
    }

    class Factory : Fetcher.Factory<Uri> {
        override fun create(
            data: Uri,
            options: Options,
            imageLoader: ImageLoader
        ): Fetcher? {
            val url = data.toString()
            // 只处理 http/https URL
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                return null
            }
            return KmpNetworkFetcher(url, options)
        }
    }
}
