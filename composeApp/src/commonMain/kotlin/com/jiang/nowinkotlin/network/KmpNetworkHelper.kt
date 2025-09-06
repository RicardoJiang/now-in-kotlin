package com.jiang.nowinkotlin.network

import com.tencent.kmm.network.export.VBTransportGetRequest
import com.tencent.kmm.network.export.VBTransportGetResponse
import com.tencent.kmm.network.internal.VBPBRequestIdGenerator
import com.tencent.kmm.network.internal.VBTransportManager
import com.tencent.kmm.network.internal.VBTransportState
import com.tencent.kmm.network.internal.VBTransportTask
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resumeWithException

object KmpNetworkHelper {
    private val taskManager = VBTransportManager

    suspend fun sendGetRequest(request: VBTransportGetRequest): VBTransportGetResponse =
        suspendCancellableCoroutine { continuation ->
            request.requestId = VBPBRequestIdGenerator.getRequestId()
            val task = VBTransportTask(
                request.requestId, request.useCurl, request.logTag,
                taskManager
            )
            taskManager.onTaskBegin(task)

            task.sendGetRequest(request) { response ->
                if (task.getState() != VBTransportState.Done) {
                    task.setState(VBTransportState.Done)
                    continuation.resumeWith(Result.success(response))
                } else {
                    if (task.getState() == VBTransportState.Unknown) {
                        continuation.resumeWithException(Throwable("unknow network exception"))
                    }
                }
            }

            continuation.invokeOnCancellation {
                if (taskManager.getState(requestId = request.requestId) != VBTransportState.Done) {
                    taskManager.cancel(request.requestId)
                }
            }
        }

}