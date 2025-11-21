# Now in Kotlin 代码风格指南

## 基本规范

### 语言要求
- 使用中文进行 code review 和代码总结
- 代码注释使用中文，清晰说明意图

### 项目架构
```
composeApp/src/
├── commonMain/         # 共享代码
│   ├── core/          # 核心基础设施（network/navigation/ui/util）
│   ├── features/      # 业务功能模块（audio/video/webview/home/monthlyreport）
│   └── shared/        # 共享数据和接口
├── androidMain/       # Android 平台实现
├── iosMain/          # iOS 平台实现
├── ohosArm64Main/    # HarmonyOS 平台实现
└── commonTest/       # 测试代码
```

## Kotlin 编码规范

### 命名规范
- **类名**: PascalCase - `MonthlyReportViewModel`
- **函数/变量**: camelCase - `onCreate()`, `episodeList`
- **常量**: UPPER_SNAKE_CASE - `MAX_RETRY_COUNT`
- **文件名**: 与主要类名一致

### 可见性
- `internal`: 模块内共享的类和函数
- `private set`: 状态属性只允许内部修改
```kotlin
var uiState by mutableStateOf(HomeUIState())
    private set
```

### 状态管理
使用不可变的 data class 定义 UI 状态，通过 `copy()` 更新：
```kotlin
internal data class MonthlyReportUIState(
    val monthlyReportList: List<MonthlyReportItem> = emptyList()
)

// 更新状态
uiState = uiState.copy(monthlyReportList = newList)
```

### ViewModel
实现 `LifecycleAware` 接口，使用 CoroutineExceptionHandler 处理异常：
```kotlin
internal class MonthlyReportViewModel(val scope: CoroutineScope) : LifecycleAware {
    override fun onCreate() {
        scope.launch(Dispatchers.Default + CoroutineExceptionHandler { _, e ->
            println("Error: ${e.message}")
        }) {
            // 业务逻辑
        }
    }

    override fun onDestroy() {
        // 清理资源
    }
}
```

## Compose UI 规范

### 组件命名和结构
- 页面级组件: `*Screen` 后缀
- 页面内部组件: 使用 `private`
- 参数顺序: 数据参数 → 回调函数 → Modifier

```kotlin
@Composable
fun EpisodeCard(
    title: String,              // 必需参数
    tags: List<String> = emptyList(),  // 可选参数
    onClick: () -> Unit,        // 回调
    modifier: Modifier = Modifier  // Modifier 总是最后
)
```

### 设计规范
- **间距**: 8dp 的倍数（8dp, 16dp, 24dp）
- **圆角**: 12dp（小）、16dp（中）、24dp（大）
- **颜色**: 使用主题颜色 `TextPrimary`, `TextSecondary`, `TextTertiary`

### 性能优化
- 使用 `remember` 缓存对象
- 为列表项提供稳定的 `key`
- `LaunchedEffect` 注意依赖项

## 平台特定代码

### expect/actual 模式
```kotlin
// commonMain
expect fun getPlatformName(): String

// androidMain
actual fun getPlatformName(): String = "Android"

// iosMain
actual fun getPlatformName(): String = "iOS"

// ohosArm64Main
actual fun getPlatformName(): String = "HarmonyOS"
```

### 平台组件封装
对外提供统一接口，内部使用 `Actual*` 前缀的平台实现：
```kotlin
@Composable
fun WebView(state: WebViewState, modifier: Modifier) {
    ActualWebView(state, modifier)
}
```

## 测试规范

### 测试结构
- 文件位置: `commonTest/kotlin/` 镜像主代码结构
- 类名: `*Test` 后缀
- 方法名: 清晰的描述性名称

```kotlin
@OptIn(ExperimentalCoroutinesApi::class)
class MonthlyReportViewModelTest {
    private lateinit var viewModel: MonthlyReportViewModel
    private val testDispatcher = StandardTestDispatcher()

    @BeforeTest
    fun setup() {
        viewModel = MonthlyReportViewModel(TestScope(testDispatcher))
    }

    @Test
    fun testInitialState() {
        assertEquals(0, viewModel.uiState.monthlyReportList.size)
    }
}
```

## 资源管理

### 图片资源
- Compose: `composeApp/src/commonMain/composeResources/drawable/`
- HarmonyOS: 需要在 `harmonyApp` 中也添加一份

### 使用 AsyncImage
```kotlin
AsyncImage(
    url = imageUrl,
    placeHodler = Res.drawable.episode_cover,
    contentDescription = "...",
    modifier = Modifier.size(64.dp)
)
```

## 注释规范

### 代码注释
```kotlin
/**
 * 技术月报 ViewModel
 * 负责加载和管理月报数据
 */
internal class MonthlyReportViewModel

// 过滤包含"技术月报"标签的文章
val filtered = list.filter { it.tags?.contains("技术月报") == true }
```

## Git Commit 规范

使用类型前缀:
- `FIX:` - 修复 bug
- `MOD:` - 修改功能
- `ADD:` - 新增功能
- `DEL:` - 删除功能
- `OPT:` - 优化代码

示例: `FIX: harmony compile bug`

## 依赖管理

使用 `gradle/libs.versions.toml` 统一管理依赖版本，注意鸿蒙平台的特殊配置需求（linkerOpts）

## 最佳实践

1. 保持简洁 - 函数和类单一职责
2. 避免硬编码 - 使用常量和配置
3. 错误处理 - 总是处理可能的异常
4. 测试覆盖 - 为关键业务编写测试
5. 平台抽象 - 使用 expect/actual 隔离差异
6. 性能优先 - 注意 Compose 重组优化

## 参考资源
- [Kotlin 官方编码规范](https://kotlinlang.org/docs/coding-conventions.html)
- [Compose 最佳实践](https://developer.android.com/jetpack/compose/performance)
- [ovCompose 文档](https://github.com/Tencent-TDS/KuiklyBase-components)
