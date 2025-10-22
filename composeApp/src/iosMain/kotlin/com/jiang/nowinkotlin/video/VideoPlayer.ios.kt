package com.jiang.nowinkotlin.video

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.interop.UIKitView
import kotlinx.cinterop.ExperimentalForeignApi
import platform.AVFoundation.*
import platform.AVKit.AVPlayerViewController
import platform.CoreGraphics.CGRectMake
import platform.UIKit.*
import platform.Foundation.NSNumber
import platform.Foundation.setValue
import kotlinx.cinterop.useContents
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue

// Store the preferred orientation
private var preferredOrientation: ScreenOrientation = ScreenOrientation.UNSPECIFIED

@OptIn(ExperimentalForeignApi::class)
actual fun setScreenOrientation(orientation: ScreenOrientation) {
    preferredOrientation = orientation

    // Ensure we're on the main thread
    dispatch_async(dispatch_get_main_queue()) {
        performOrientationChange(orientation)
    }
}

@OptIn(ExperimentalForeignApi::class)
private fun performOrientationChange(orientation: ScreenOrientation) {
    val targetOrientation: Long = when (orientation) {
        ScreenOrientation.PORTRAIT -> UIInterfaceOrientationPortrait
        ScreenOrientation.LANDSCAPE -> UIInterfaceOrientationLandscapeRight
        ScreenOrientation.UNSPECIFIED -> UIInterfaceOrientationPortrait
    }

    val orientationMask: ULong = when (orientation) {
        ScreenOrientation.PORTRAIT -> UIInterfaceOrientationMaskPortrait
        ScreenOrientation.LANDSCAPE -> UIInterfaceOrientationMaskLandscape
        ScreenOrientation.UNSPECIFIED -> UIInterfaceOrientationMaskAll
    }

    println("Setting screen orientation to: $orientation (mask: $orientationMask)")

    // Method 1: Use UIDevice setValue (works on iOS < 16)
    try {
        val device = UIDevice.currentDevice
        // First set to unknown to force a change
        device.setValue(NSNumber(long = UIInterfaceOrientationUnknown), forKey = "orientation")
        // Then set to target orientation
        device.setValue(NSNumber(long = targetOrientation), forKey = "orientation")
        println("Method 1 (UIDevice setValue) succeeded")
    } catch (e: Exception) {
        println("Method 1 failed: ${e.message}")
    }

    // Method 2: Request all view controllers to update
    UIViewController.attemptRotationToDeviceOrientation()

    // Method 3: Use UIWindowScene geometry update (iOS 16+)
    try {
        UIApplication.sharedApplication.connectedScenes.forEach { scene ->
            (scene as? UIWindowScene)?.let { windowScene ->
                val geometryPreferences = UIWindowSceneGeometryPreferencesIOS(
                    interfaceOrientations = orientationMask
                )

                windowScene.requestGeometryUpdateWithPreferences(
                    geometryPreferences = geometryPreferences,
                    errorHandler = { error ->
                        error?.let {
                            println("Geometry update error: ${it.localizedDescription}")
                        } ?: println("Geometry update succeeded")
                    }
                )
            }
        }
    } catch (e: Exception) {
        println("Method 3 failed: ${e.message}")
    }
}

// Helper function to get preferred orientation mask
fun getPreferredOrientationMask(): ULong {
    return when (preferredOrientation) {
        ScreenOrientation.PORTRAIT -> UIInterfaceOrientationMaskPortrait
        ScreenOrientation.LANDSCAPE -> UIInterfaceOrientationMaskLandscape
        ScreenOrientation.UNSPECIFIED -> UIInterfaceOrientationMaskAll
    }
}

/**
 * iOS 平台的视频播放视图（不带控制器）
 */
@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun PlatformVideoPlayerView(
    controller: VideoPlayerController,
    modifier: Modifier
) {
    val iosController = controller as IOSVideoPlayerController

    // 使用 remember 保存 playerLayer 引用，确保不会因为重组而重新创建
    val playerLayer = remember(iosController) {
        println("Creating new AVPlayerLayer for player: ${iosController.player}")
        AVPlayerLayer().apply {
            player = iosController.player
            videoGravity = AVLayerVideoGravityResizeAspect
        }
    }

    // 使用 remember 保存 containerView，防止屏幕旋转时重新创建
    val containerView = remember(iosController) {
        println("Creating new containerView")
        object : UIView(frame = CGRectMake(0.0, 0.0, 0.0, 0.0)) {
            override fun layoutSubviews() {
                super.layoutSubviews()
                // 每次布局变化时更新 playerLayer 的 frame
                println("layoutSubviews called with bounds: ${this.bounds}")
                playerLayer.setFrame(this.bounds)
            }
        }.apply {
            backgroundColor = UIColor.blackColor
            // 将 playerLayer 添加到容器视图的 layer
            layer.addSublayer(playerLayer)
        }
    }

    UIKitView(
        factory = {
            println("UIKitView factory called - returning cached containerView")
            containerView
        },
        update = { view ->
            // 确保在更新时也正确设置 frame
            println("UIKitView update called with bounds: ${view.bounds}")
            playerLayer.setFrame(view.bounds)
        },
        modifier = modifier
    )
}

@Composable
fun IOSVideoPlayerView(
    controller: VideoPlayerController,
    modifier: Modifier = Modifier
) {
    PlatformVideoPlayerView(controller, modifier)
}

// 为了保持兼容性，保留旧的接口
@Composable
actual fun VideoPlayerView(
    videoUrl: String,
    modifier: Modifier,
    onLoadingChanged: (Boolean) -> Unit
) {
    val controller = rememberVideoPlayerController(videoUrl)

    DisposableEffect(controller) {
        onDispose {
            // onLoadingChanged will be handled by the controller's isBuffering state
        }
    }

    IOSVideoPlayerView(controller = controller, modifier = modifier)
}
