# Now in Kotlin

[![Platform](https://img.shields.io/badge/Platform-Android%20%7C%20iOS%20%7C%20HarmonyOS-brightgreen.svg)](https://kotlinlang.org/docs/multiplatform.html)

`Now in Kotlin` 是一个基于 ovCompose 技术构建的跨平台项目。本项目旨在帮助开发者了解 Kotlin 最新技术动态，同时演示如何利用 ovCompose 在 Android、iOS 和 鸿蒙三个平台上实现代码共享与高效开发。

## 功能特性
目前 `Now in Kotlin` 主要包括两个功能

- Kotlin 炉边漫谈播客：内容来源于[https://podcast.kotlin.tips](https://podcast.kotlin.tips)，是一个以 Kotlin 为主题的中文播客节目。
- Kotlin 技术月报：内容来源于[https://beijing-kug.github.io/news/](https://beijing-kug.github.io/news/)，每月更新 Kotlin 相关的技术动态。

## UI 设计
程序员一般不是很擅长 UI 设计，我这里直接使用了[Motiff 妙多](https://miaoduo.com/) 来生成设计稿与 Logo。

Motiff 不仅支持根据提示词生成设计稿，也可以选中指定区域之后与 AI 对话进行微调，同时生成的设计稿也支持通过 MCP 直接生成代码。

![](https://hub.gitmirror.com/raw.githubusercontent.com/RicardoJiang/resource/refs/heads/main/2025/september/p5.png)

## 具体实现
### UI 界面
如上面所说，通过 Motiff MCP 生成 Compose 代码即可，生成的 Compose 代码可通过 ovCompose 复用到 iOS 与 鸿蒙平台。

生成的 UI 代码基本不需要改动，需要注意的一点是目前 ovCompose 还不支持 material3，因此生成的代码的部分需要注意一下。

### 路由简单实现
由于目前 ovCompose 还没有支持 Navigation，因此需要简单实现路由跳转。

如下所示，实际上是把 navigator 栈中的所有页面都绘制了，但是只有 zIndex 最高的那个可见。

```kotlin
@Composable
fun NavigatorHost(initialScreen: Screen) {
    val navigator = remember { Navigator(initialScreen) }

    BackHandler(navigator.stack.size > 1) {
        navigator.pop()
    }

    Box(Modifier.fillMaxSize()) {
        CompositionLocalProvider(LocalNavigator provides navigator) {
            // 遍历导航栈中的所有页面
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
```

### 网络图片加载简单实现
由于 ovCompose 目前还没有支持网络图片加载，因此也需要简单实现一个`AsyncImage`

如下所示，通过传入的 url 下载 ByteArray 并转化为 Bitmap，下载成功的 Bitmap 会做一个内存缓存。

有点需要注意的 iOS 平台与鸿蒙平台都使用了`Image.makeFromEncoded`来解码 Bitmap，但是这里 iOS 平台没有问题，鸿蒙平台只支持 PNG 格式。
因为项目中使用的图片正好都是 PNG 格式所以没有问题，如果你需要使用其它格式图片，还需要调用鸿蒙 API 处理一下解码问题。

```kotlin
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
        // ...
        asyncImageCache.set(url, AsyncImageState.Loading)
        val byteArray: ByteArray? = withContext(Dispatchers.IO) {
            fetchImageForGetRequest(url)
        }

        val bitmap = imageBitmapFromBytes(byteArray)
        state = AsyncImageState.Success(bitmap)
        asyncImageCache.set(url, state)
    }
    
    // ...

    // 5. 根据当前状态显示不同的 UI
    Box(modifier = modifier) { // 使用 Box 来确保占位符和图片尺寸一致
        when (val currentState = state) {
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
```

### ViewModel 简单支持
ViewModel 目前也没有支持，因此也需要自己做一个简单实现。如下所示，通过`rememberLifecycleAware`即可创建一个与 Compose 组件生命周期一致的 ViewModel。

```kotlin
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

// 创建一个跟 Compose 组件生命周期一致的 ViewModel
val monthlyReportViewModel = rememberLifecycleAware { scope ->
    MonthlyReportViewModel(scope)
}

internal class MonthlyReportViewModel(val scope: CoroutineScope) : LifecycleAware {
    override fun onCreate() {
        scope.launch{
            // ...
        }
    }

    override fun onDestroy() {
    }
}
```

### WebView 与音频播放支持
WebView 与音频播放都必须使用平台相关能力，因此需要与原生层进行交互。 而 ovCompose 支持与 Android, iOS, 鸿蒙原生组件嵌套，同时也支持与原生 API 互操作。
如下所示，就是一个 Compose 嵌套 ArkUIView 的示例，其他的实现可参见源码。

```kotlin
@Composable
actual fun ActualWebView(
    state: WebViewState, modifier: Modifier
) {
    OhosWebView(state, modifier)
}

@OptIn(ExperimentalForeignApi::class, ExperimentalComposeUiApi::class)
@Composable
fun OhosWebView(
    state: WebViewState, modifier: Modifier
) {
    ArkUIView(
        name = "harmonyWebView",
        modifier = modifier,
        parameter = js {
            "url"(state.webUrl)
            "backgroundColor"("#FF0000FF")
        },
    )
}
```

### 其他注意事项
- 网络库：Ktor 等库也没有适配鸿蒙，网络库这里使用了腾讯开源的[NetworkKMM](https://github.com/Tencent-TDS/KuiklyBase-components/tree/master/NetworkKMM)。 在接入网络库的过程中需要注意鸿蒙平台需要配置`linkerOpts`，以正确链接`lpbcurlwrapper`等库
- 反序列化：反序化则是在最近已经支持了 kotlinx.serialization，直接使用即可
- 添加资源：图片资源新增时需要在 ComposeApp 与 harmonyApp 中都添加一份

## 下载使用
### Android 平台
可通过链接下载：[https://www.pgyer.com/8SryRWGk](https://www.pgyer.com/8SryRWGk)，或者也可以通过扫码安装：

![](https://www.pgyer.com/app/qrcode/8SryRWGk)

### iOS 平台
由于未申请 iOS 开发者证书，只能下载源码编译安装到模拟器或者自己手机

## 项目地址
[https://github.com/RicardoJiang/now-in-kotlin](https://github.com/RicardoJiang/now-in-kotlin)



