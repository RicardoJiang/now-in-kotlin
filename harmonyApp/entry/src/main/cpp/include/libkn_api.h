#ifndef KONAN_LIBKN_H
#define KONAN_LIBKN_H
#ifdef __cplusplus
extern "C" {
#endif
#ifdef __cplusplus
typedef bool            libkn_KBoolean;
#else
typedef _Bool           libkn_KBoolean;
#endif
typedef unsigned short     libkn_KChar;
typedef signed char        libkn_KByte;
typedef short              libkn_KShort;
typedef int                libkn_KInt;
typedef long long          libkn_KLong;
typedef unsigned char      libkn_KUByte;
typedef unsigned short     libkn_KUShort;
typedef unsigned int       libkn_KUInt;
typedef unsigned long long libkn_KULong;
typedef float              libkn_KFloat;
typedef double             libkn_KDouble;
typedef float __attribute__ ((__vector_size__ (16))) libkn_KVector128;
typedef void*              libkn_KNativePtr;
struct libkn_KType;
typedef struct libkn_KType libkn_KType;

typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlin_Byte;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlin_Short;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlin_Int;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlin_Long;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlin_Float;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlin_Double;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlin_Char;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlin_Boolean;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlin_Unit;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlin_UByte;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlin_UShort;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlin_UInt;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlin_ULong;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_Greeting;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_androidx_compose_runtime_ProvidableCompositionLocal;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_core_navigation_AppMainScreen;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_core_navigation_Screen;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_core_navigation_Navigator;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlin_collections_List;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_core_network_KmpNetworkHelper;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_tencent_tmm_knoi_type_JSValue;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_core_platform_OhosVideoAVPlayerProxy;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_core_platform_OhosServices;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_core_platform_OhosVideoAVPlayerFactory;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_core_platform_SetScreenOrientation;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_core_platform_OhosServicesProxy;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlin_collections_Map;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_core_platform_SetScreenOrientationProxy;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_core_platform_OhosVideoAVPlayerFactoryProxy;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_core_platform_OhosVideoAVPlayer;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_core_platform_PlatformContext;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_core_platform_PlatformContext_Companion;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_core_ui_components_BottomNavItem;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_androidx_compose_ui_graphics_vector_ImageVector;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlin_Any;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_androidx_compose_material_Typography;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_features_audio_platform_OhosAVPlayer;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_features_audio_platform_OhosAVPlayerProxy;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_features_audio_platform_OhosStateListenerProvider;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlin_Array;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlin_reflect_KClass;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_features_audio_platform_MediaCommandHandlerProvider;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_features_audio_platform_OhosStateListener;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_features_audio_platform_MediaCommandHandler;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_features_audio_platform_OhosPlayerStateManager;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_features_audio_platform_PlaylistManager;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_shared_data_Episode;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_features_audio_player_KmpAudioPlayer;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_features_audio_player_MediaPlaybackController;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlinx_coroutines_flow_StateFlow;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_features_audio_player_PlayingStatus;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_features_audio_player_PlayingStatus_PLAYING;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_features_audio_player_PlayingStatus_PAUSED;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_features_audio_player_PlayingStatus_BUFFERING;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_features_audio_player_PlayingStatus_IDLE;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_features_audio_player_PlayingStatus_ENDED;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_features_audio_player_PlaybackState;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_features_audio_player_PlatformMediaPlaybackController;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_androidx_compose_material_icons_Icons_Filled;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_features_video_player_ScreenOrientation;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_features_video_player_Video;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_features_video_player_ScreenOrientation_PORTRAIT;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_features_video_player_ScreenOrientation_LANDSCAPE;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_features_video_player_ScreenOrientation_UNSPECIFIED;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_features_video_player_VideoPlayerController;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_androidx_compose_runtime_State;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_features_video_player_OHOSVideoPlayerController;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_features_webview_component_IWebView;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_features_webview_component_LoadingState;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_features_webview_component_LoadingState_Initializing;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_features_webview_component_LoadingState_Loading;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_features_webview_component_LoadingState_Finished;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_features_webview_component_WebViewState;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_features_webview_component_IOSWebView;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_shared_data_MonthlyReportItem;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_shared_data_MonthlyReportItem_$serializer;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlinx_serialization_descriptors_SerialDescriptor;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlinx_serialization_encoding_Decoder;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlinx_serialization_encoding_Encoder;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_shared_data_MonthlyReportItem_Companion;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlinx_serialization_KSerializer;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_shared_data_EpisodeModel;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_shared_data_AudioResourceModel;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_shared_data_EpisodeModel_$serializer;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_shared_data_EpisodeModel_Companion;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_shared_data_AudioResourceModel_$serializer;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_shared_data_AudioResourceModel_Companion;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_shared_viewmodel_LifecycleAware;

extern void androidx_compose_ui_arkui_ArkUIViewController_aboutToAppear(void* controllerRef);
extern void androidx_compose_ui_arkui_ArkUIViewController_aboutToDisappear(void* controllerRef);
extern void androidx_compose_ui_arkui_ArkUIViewController_cancelSyncRefresh(void* controllerRef, libkn_KInt refreshId);
extern void androidx_compose_ui_arkui_ArkUIViewController_dispatchHoverEvent(void* controllerRef);
extern void androidx_compose_ui_arkui_ArkUIViewController_dispatchMouseEvent(void* controllerRef);
extern libkn_KBoolean androidx_compose_ui_arkui_ArkUIViewController_dispatchTouchEvent(void* controllerRef, void* nativeTouchEvent, libkn_KBoolean ignoreInteropView);
extern const char* androidx_compose_ui_arkui_ArkUIViewController_getId(void* controllerRef);
extern void* androidx_compose_ui_arkui_ArkUIViewController_getXComponentRender(void* controllerRef);
extern void androidx_compose_ui_arkui_ArkUIViewController_keyboardWillHide(void* controllerRef);
extern void androidx_compose_ui_arkui_ArkUIViewController_keyboardWillShow(void* controllerRef, libkn_KFloat keyboardHeight);
extern libkn_KBoolean androidx_compose_ui_arkui_ArkUIViewController_onBackPress(void* controllerRef);
extern void androidx_compose_ui_arkui_ArkUIViewController_onFinalize(void* controllerRef);
extern void androidx_compose_ui_arkui_ArkUIViewController_onFocusEvent(void* controllerRef);
extern void androidx_compose_ui_arkui_ArkUIViewController_onFrame(void* controllerRef, libkn_KLong timestamp, libkn_KLong targetTimestamp);
extern void androidx_compose_ui_arkui_ArkUIViewController_onKeyEvent(void* controllerRef);
extern void androidx_compose_ui_arkui_ArkUIViewController_onPageHide(void* controllerRef);
extern void androidx_compose_ui_arkui_ArkUIViewController_onPageShow(void* controllerRef);
extern void androidx_compose_ui_arkui_ArkUIViewController_onSurfaceChanged(void* controllerRef, libkn_KInt width, libkn_KInt height);
extern void androidx_compose_ui_arkui_ArkUIViewController_onSurfaceCreated(void* controllerRef, void* xcomponentPtr, libkn_KInt width, libkn_KInt height);
extern void androidx_compose_ui_arkui_ArkUIViewController_onSurfaceDestroyed(void* controllerRef);
extern void androidx_compose_ui_arkui_ArkUIViewController_onSurfaceHide(void* controllerRef);
extern void androidx_compose_ui_arkui_ArkUIViewController_onSurfaceShow(void* controllerRef);
extern libkn_KInt androidx_compose_ui_arkui_ArkUIViewController_requestSyncRefresh(void* controllerRef);
extern const char* androidx_compose_ui_arkui_ArkUIViewController_sendMessage(void* controllerRef, const char* type, const char* message);
extern void androidx_compose_ui_arkui_ArkUIViewController_setContext(void* controllerRef, void* context);
extern void androidx_compose_ui_arkui_ArkUIViewController_setEnv(void* controllerRef, void* env);
extern void androidx_compose_ui_arkui_ArkUIViewController_setId(void* controllerRef, const char* id);
extern void androidx_compose_ui_arkui_ArkUIViewController_setMessenger(void* controllerRef, void* messenger);
extern void androidx_compose_ui_arkui_ArkUIViewController_setRootView(void* controllerRef, void* backRootView, void* foreRootView, void* touchableRootView);
extern void androidx_compose_ui_arkui_ArkUIViewController_setUIContext(void* controllerRef, void* uiContext);
extern void androidx_compose_ui_arkui_ArkUIViewController_setXComponentRender(void* controllerRef, void* render);
extern void androidx_compose_ui_arkui_init(void* env, void* exports);
extern void* MainArkUIViewController(void* env);
extern void com_tencent_tmm_knoi_initEnv(void* env, void* value, libkn_KBoolean debug);
extern void com_tencent_tmm_knoi_initBridge();

typedef struct {
  /* Service functions. */
  void (*DisposeStablePointer)(libkn_KNativePtr ptr);
  void (*DisposeString)(const char* string);
  libkn_KBoolean (*IsInstance)(libkn_KNativePtr ref, const libkn_KType* type);
  libkn_kref_kotlin_Byte (*createNullableByte)(libkn_KByte);
  libkn_KByte (*getNonNullValueOfByte)(libkn_kref_kotlin_Byte);
  libkn_kref_kotlin_Short (*createNullableShort)(libkn_KShort);
  libkn_KShort (*getNonNullValueOfShort)(libkn_kref_kotlin_Short);
  libkn_kref_kotlin_Int (*createNullableInt)(libkn_KInt);
  libkn_KInt (*getNonNullValueOfInt)(libkn_kref_kotlin_Int);
  libkn_kref_kotlin_Long (*createNullableLong)(libkn_KLong);
  libkn_KLong (*getNonNullValueOfLong)(libkn_kref_kotlin_Long);
  libkn_kref_kotlin_Float (*createNullableFloat)(libkn_KFloat);
  libkn_KFloat (*getNonNullValueOfFloat)(libkn_kref_kotlin_Float);
  libkn_kref_kotlin_Double (*createNullableDouble)(libkn_KDouble);
  libkn_KDouble (*getNonNullValueOfDouble)(libkn_kref_kotlin_Double);
  libkn_kref_kotlin_Char (*createNullableChar)(libkn_KChar);
  libkn_KChar (*getNonNullValueOfChar)(libkn_kref_kotlin_Char);
  libkn_kref_kotlin_Boolean (*createNullableBoolean)(libkn_KBoolean);
  libkn_KBoolean (*getNonNullValueOfBoolean)(libkn_kref_kotlin_Boolean);
  libkn_kref_kotlin_Unit (*createNullableUnit)(void);
  libkn_kref_kotlin_UByte (*createNullableUByte)(libkn_KUByte);
  libkn_KUByte (*getNonNullValueOfUByte)(libkn_kref_kotlin_UByte);
  libkn_kref_kotlin_UShort (*createNullableUShort)(libkn_KUShort);
  libkn_KUShort (*getNonNullValueOfUShort)(libkn_kref_kotlin_UShort);
  libkn_kref_kotlin_UInt (*createNullableUInt)(libkn_KUInt);
  libkn_KUInt (*getNonNullValueOfUInt)(libkn_kref_kotlin_UInt);
  libkn_kref_kotlin_ULong (*createNullableULong)(libkn_KULong);
  libkn_KULong (*getNonNullValueOfULong)(libkn_kref_kotlin_ULong);

  /* User functions. */
  struct {
    struct {
      struct {
        struct {
          struct {
            struct {
              struct {
                void (*_Export_ArkUIViewController_aboutToAppear)(void* controllerRef);
                void (*_Export_ArkUIViewController_aboutToDisappear)(void* controllerRef);
                void (*_Export_ArkUIViewController_cancelSyncRefresh)(void* controllerRef, libkn_KInt refreshId);
                void (*_Export_ArkUIViewController_dispatchHoverEvent)(void* controllerRef);
                void (*_Export_ArkUIViewController_dispatchMouseEvent)(void* controllerRef);
                libkn_KBoolean (*_Export_ArkUIViewController_dispatchTouchEvent)(void* controllerRef, void* nativeTouchEvent, libkn_KBoolean ignoreInteropView);
                const char* (*_Export_ArkUIViewController_getId)(void* controllerRef);
                void* (*_Export_ArkUIViewController_getXComponentRender)(void* controllerRef);
                void (*_Export_ArkUIViewController_keyboardWillHide)(void* controllerRef);
                void (*_Export_ArkUIViewController_keyboardWillShow)(void* controllerRef, libkn_KFloat keyboardHeight);
                libkn_KBoolean (*_Export_ArkUIViewController_onBackPress)(void* controllerRef);
                void (*_Export_ArkUIViewController_onFinalize)(void* controllerRef);
                void (*_Export_ArkUIViewController_onFocusEvent)(void* controllerRef);
                void (*_Export_ArkUIViewController_onFrame)(void* controllerRef, libkn_KLong timestamp, libkn_KLong targetTimestamp);
                void (*_Export_ArkUIViewController_onKeyEvent)(void* controllerRef);
                void (*_Export_ArkUIViewController_onPageHide)(void* controllerRef);
                void (*_Export_ArkUIViewController_onPageShow)(void* controllerRef);
                void (*_Export_ArkUIViewController_onSurfaceChanged)(void* controllerRef, libkn_KInt width, libkn_KInt height);
                void (*_Export_ArkUIViewController_onSurfaceCreated)(void* controllerRef, void* xcomponentPtr, libkn_KInt width, libkn_KInt height);
                void (*_Export_ArkUIViewController_onSurfaceDestroyed)(void* controllerRef);
                void (*_Export_ArkUIViewController_onSurfaceHide)(void* controllerRef);
                void (*_Export_ArkUIViewController_onSurfaceShow)(void* controllerRef);
                libkn_KInt (*_Export_ArkUIViewController_requestSyncRefresh)(void* controllerRef);
                const char* (*_Export_ArkUIViewController_sendMessage)(void* controllerRef, const char* type, const char* message);
                void (*_Export_ArkUIViewController_setContext)(void* controllerRef, void* context);
                void (*_Export_ArkUIViewController_setEnv)(void* controllerRef, void* env);
                void (*_Export_ArkUIViewController_setId)(void* controllerRef, const char* id);
                void (*_Export_ArkUIViewController_setMessenger)(void* controllerRef, void* messenger);
                void (*_Export_ArkUIViewController_setRootView)(void* controllerRef, void* backRootView, void* foreRootView, void* touchableRootView);
                void (*_Export_ArkUIViewController_setUIContext)(void* controllerRef, void* uiContext);
                void (*_Export_ArkUIViewController_setXComponentRender)(void* controllerRef, void* render);
                void (*_Export_ArkUIViewInitializer_init)(void* env, void* exports);
              } arkui;
            } ui;
          } export_;
        } compose;
      } androidx;
      struct {
        struct {
          struct {
            struct {
              libkn_KType* (*_type)(void);
              libkn_kref_com_jiang_nowinkotlin_Greeting (*Greeting)();
              const char* (*greet)(libkn_kref_com_jiang_nowinkotlin_Greeting thiz);
            } Greeting;
            struct {
              void* (*MainArkUIViewController_)(void* env);
              void (*initResourceManager)(void* resourceManager);
            } app;
            struct {
              struct {
                struct {
                  libkn_KType* (*_type)(void);
                  libkn_kref_com_jiang_nowinkotlin_core_navigation_AppMainScreen (*_instance)();
                } AppMainScreen;
                struct {
                  libkn_KType* (*_type)(void);
                } Screen;
                struct {
                  libkn_KType* (*_type)(void);
                  libkn_kref_com_jiang_nowinkotlin_core_navigation_Navigator (*Navigator)(libkn_kref_com_jiang_nowinkotlin_core_navigation_Screen initialScreen);
                  libkn_kref_kotlin_collections_List (*get_stack)(libkn_kref_com_jiang_nowinkotlin_core_navigation_Navigator thiz);
                  void (*pop)(libkn_kref_com_jiang_nowinkotlin_core_navigation_Navigator thiz);
                  void (*push)(libkn_kref_com_jiang_nowinkotlin_core_navigation_Navigator thiz, libkn_kref_com_jiang_nowinkotlin_core_navigation_Screen screen);
                } Navigator;
                libkn_KInt (*com_jiang_nowinkotlin_core_navigation_AppMainScreen$stableprop_getter)();
                libkn_KInt (*com_jiang_nowinkotlin_core_navigation_NavItem$stableprop_getter)();
                libkn_KInt (*com_jiang_nowinkotlin_core_navigation_Navigator$stableprop_getter)();
                libkn_KInt (*com_jiang_nowinkotlin_core_navigation_WebViewScreen$stableprop_getter)();
                libkn_KInt (*com_jiang_nowinkotlin_core_navigation_AppMainScreen$stableprop_getter_)();
                libkn_KInt (*com_jiang_nowinkotlin_core_navigation_NavItem$stableprop_getter_)();
                libkn_KInt (*com_jiang_nowinkotlin_core_navigation_Navigator$stableprop_getter_)();
                libkn_KInt (*com_jiang_nowinkotlin_core_navigation_WebViewScreen$stableprop_getter_)();
                libkn_kref_androidx_compose_runtime_ProvidableCompositionLocal (*get_LocalNavigator)();
                libkn_KInt (*com_jiang_nowinkotlin_core_navigation_AppMainScreen$stableprop_getter__)();
                libkn_KInt (*com_jiang_nowinkotlin_core_navigation_NavItem$stableprop_getter__)();
                libkn_KInt (*com_jiang_nowinkotlin_core_navigation_Navigator$stableprop_getter__)();
                libkn_KInt (*com_jiang_nowinkotlin_core_navigation_WebViewScreen$stableprop_getter__)();
              } navigation;
              struct {
                struct {
                  libkn_KType* (*_type)(void);
                  libkn_kref_com_jiang_nowinkotlin_core_network_KmpNetworkHelper (*_instance)();
                } KmpNetworkHelper;
                libkn_KInt (*com_jiang_nowinkotlin_core_network_KmpNetworkHelper$stableprop_getter)();
              } network;
              struct {
                struct {
                  libkn_KType* (*_type)(void);
                  libkn_kref_com_jiang_nowinkotlin_core_platform_OhosVideoAVPlayerProxy (*OhosVideoAVPlayerProxy)(libkn_kref_com_tencent_tmm_knoi_type_JSValue _value);
                  libkn_kref_com_tencent_tmm_knoi_type_JSValue (*get__value)(libkn_kref_com_jiang_nowinkotlin_core_platform_OhosVideoAVPlayerProxy thiz);
                  libkn_KLong (*currentTime)(libkn_kref_com_jiang_nowinkotlin_core_platform_OhosVideoAVPlayerProxy thiz);
                  libkn_KLong (*duration)(libkn_kref_com_jiang_nowinkotlin_core_platform_OhosVideoAVPlayerProxy thiz);
                  libkn_KBoolean (*isBuffering)(libkn_kref_com_jiang_nowinkotlin_core_platform_OhosVideoAVPlayerProxy thiz);
                  libkn_KBoolean (*isPlaying)(libkn_kref_com_jiang_nowinkotlin_core_platform_OhosVideoAVPlayerProxy thiz);
                  void (*pause)(libkn_kref_com_jiang_nowinkotlin_core_platform_OhosVideoAVPlayerProxy thiz);
                  void (*play)(libkn_kref_com_jiang_nowinkotlin_core_platform_OhosVideoAVPlayerProxy thiz);
                  void (*release)(libkn_kref_com_jiang_nowinkotlin_core_platform_OhosVideoAVPlayerProxy thiz);
                  void (*seekTo)(libkn_kref_com_jiang_nowinkotlin_core_platform_OhosVideoAVPlayerProxy thiz, libkn_KLong position);
                  void (*setUpVideo)(libkn_kref_com_jiang_nowinkotlin_core_platform_OhosVideoAVPlayerProxy thiz, const char* surfaceId, const char* url);
                } OhosVideoAVPlayerProxy;
                struct {
                  libkn_KType* (*_type)(void);
                  libkn_kref_com_jiang_nowinkotlin_core_platform_OhosServicesProxy (*OhosServicesProxy)();
                  libkn_kref_kotlin_collections_Map (*parseJson)(libkn_kref_com_jiang_nowinkotlin_core_platform_OhosServicesProxy thiz, const char* json);
                } OhosServicesProxy;
                struct {
                  libkn_KType* (*_type)(void);
                  libkn_kref_com_jiang_nowinkotlin_core_platform_SetScreenOrientationProxy (*SetScreenOrientationProxy)();
                  void (*setOrientation)(libkn_kref_com_jiang_nowinkotlin_core_platform_SetScreenOrientationProxy thiz, libkn_KBoolean isLandscape);
                } SetScreenOrientationProxy;
                struct {
                  libkn_KType* (*_type)(void);
                  libkn_kref_com_jiang_nowinkotlin_core_platform_OhosVideoAVPlayerFactoryProxy (*OhosVideoAVPlayerFactoryProxy)();
                  libkn_kref_com_tencent_tmm_knoi_type_JSValue (*createOhosVideoAVPlayer)(libkn_kref_com_jiang_nowinkotlin_core_platform_OhosVideoAVPlayerFactoryProxy thiz);
                } OhosVideoAVPlayerFactoryProxy;
                struct {
                  libkn_KType* (*_type)(void);
                  libkn_kref_kotlin_collections_Map (*parseJson)(libkn_kref_com_jiang_nowinkotlin_core_platform_OhosServices thiz, const char* json);
                } OhosServices;
                struct {
                  libkn_KType* (*_type)(void);
                  void (*setOrientation)(libkn_kref_com_jiang_nowinkotlin_core_platform_SetScreenOrientation thiz, libkn_KBoolean isLandscape);
                } SetScreenOrientation;
                struct {
                  libkn_KType* (*_type)(void);
                  libkn_kref_com_tencent_tmm_knoi_type_JSValue (*createOhosVideoAVPlayer)(libkn_kref_com_jiang_nowinkotlin_core_platform_OhosVideoAVPlayerFactory thiz);
                } OhosVideoAVPlayerFactory;
                struct {
                  libkn_KType* (*_type)(void);
                  libkn_KLong (*currentTime)(libkn_kref_com_jiang_nowinkotlin_core_platform_OhosVideoAVPlayer thiz);
                  libkn_KLong (*duration)(libkn_kref_com_jiang_nowinkotlin_core_platform_OhosVideoAVPlayer thiz);
                  libkn_KBoolean (*isBuffering)(libkn_kref_com_jiang_nowinkotlin_core_platform_OhosVideoAVPlayer thiz);
                  libkn_KBoolean (*isPlaying)(libkn_kref_com_jiang_nowinkotlin_core_platform_OhosVideoAVPlayer thiz);
                  void (*pause)(libkn_kref_com_jiang_nowinkotlin_core_platform_OhosVideoAVPlayer thiz);
                  void (*play)(libkn_kref_com_jiang_nowinkotlin_core_platform_OhosVideoAVPlayer thiz);
                  void (*release)(libkn_kref_com_jiang_nowinkotlin_core_platform_OhosVideoAVPlayer thiz);
                  void (*seekTo)(libkn_kref_com_jiang_nowinkotlin_core_platform_OhosVideoAVPlayer thiz, libkn_KLong position);
                  void (*setUpVideo)(libkn_kref_com_jiang_nowinkotlin_core_platform_OhosVideoAVPlayer thiz, const char* surfaceId, const char* url);
                } OhosVideoAVPlayer;
                struct {
                  struct {
                    libkn_KType* (*_type)(void);
                    libkn_kref_com_jiang_nowinkotlin_core_platform_PlatformContext_Companion (*_instance)();
                    libkn_kref_com_jiang_nowinkotlin_core_platform_PlatformContext (*get_INSTANCE)(libkn_kref_com_jiang_nowinkotlin_core_platform_PlatformContext_Companion thiz);
                  } Companion;
                  libkn_KType* (*_type)(void);
                } PlatformContext;
                libkn_KInt (*com_jiang_nowinkotlin_core_platform_OHOSPlatform$stableprop_getter)();
                libkn_KInt (*com_jiang_nowinkotlin_core_platform_OhosServicesProxy$stableprop_getter)();
                libkn_KInt (*com_jiang_nowinkotlin_core_platform_OhosVideoAVPlayerFactoryProxy$stableprop_getter)();
                libkn_KInt (*com_jiang_nowinkotlin_core_platform_OhosVideoAVPlayerProxy$stableprop_getter)();
                libkn_KInt (*com_jiang_nowinkotlin_core_platform_PlatformContext$stableprop_getter)();
                libkn_KInt (*com_jiang_nowinkotlin_core_platform_SetScreenOrientationProxy$stableprop_getter)();
                libkn_kref_com_jiang_nowinkotlin_core_platform_OhosVideoAVPlayerProxy (*asOhosVideoAVPlayer)(libkn_kref_com_tencent_tmm_knoi_type_JSValue thiz);
                libkn_KInt (*com_jiang_nowinkotlin_core_platform_OHOSPlatform$stableprop_getter_)();
                libkn_KInt (*com_jiang_nowinkotlin_core_platform_OhosServicesProxy$stableprop_getter_)();
                libkn_KInt (*com_jiang_nowinkotlin_core_platform_OhosVideoAVPlayerFactoryProxy$stableprop_getter_)();
                libkn_KInt (*com_jiang_nowinkotlin_core_platform_OhosVideoAVPlayerProxy$stableprop_getter_)();
                libkn_KInt (*com_jiang_nowinkotlin_core_platform_PlatformContext$stableprop_getter_)();
                libkn_KInt (*com_jiang_nowinkotlin_core_platform_SetScreenOrientationProxy$stableprop_getter_)();
                libkn_kref_com_jiang_nowinkotlin_core_platform_OhosServices (*getOhosServicesApi)();
                libkn_kref_com_jiang_nowinkotlin_core_platform_OhosVideoAVPlayerFactory (*getOhosVideoAVPlayerFactoryApi)();
                libkn_kref_com_jiang_nowinkotlin_core_platform_SetScreenOrientation (*getSetScreenOrientationApi)();
                libkn_KInt (*com_jiang_nowinkotlin_core_platform_OHOSPlatform$stableprop_getter__)();
                libkn_KInt (*com_jiang_nowinkotlin_core_platform_OhosServicesProxy$stableprop_getter__)();
                libkn_KInt (*com_jiang_nowinkotlin_core_platform_OhosVideoAVPlayerFactoryProxy$stableprop_getter__)();
                libkn_KInt (*com_jiang_nowinkotlin_core_platform_OhosVideoAVPlayerProxy$stableprop_getter__)();
                libkn_KInt (*com_jiang_nowinkotlin_core_platform_PlatformContext$stableprop_getter__)();
                libkn_KInt (*com_jiang_nowinkotlin_core_platform_SetScreenOrientationProxy$stableprop_getter__)();
                libkn_KInt (*com_jiang_nowinkotlin_core_platform_OHOSPlatform$stableprop_getter___)();
                libkn_KInt (*com_jiang_nowinkotlin_core_platform_OhosServicesProxy$stableprop_getter___)();
                libkn_KInt (*com_jiang_nowinkotlin_core_platform_OhosVideoAVPlayerFactoryProxy$stableprop_getter___)();
                libkn_KInt (*com_jiang_nowinkotlin_core_platform_OhosVideoAVPlayerProxy$stableprop_getter___)();
                libkn_KInt (*com_jiang_nowinkotlin_core_platform_PlatformContext$stableprop_getter___)();
                libkn_KInt (*com_jiang_nowinkotlin_core_platform_SetScreenOrientationProxy$stableprop_getter___)();
                libkn_KInt (*com_jiang_nowinkotlin_core_platform_OHOSPlatform$stableprop_getter____)();
                libkn_KInt (*com_jiang_nowinkotlin_core_platform_OhosServicesProxy$stableprop_getter____)();
                libkn_KInt (*com_jiang_nowinkotlin_core_platform_OhosVideoAVPlayerFactoryProxy$stableprop_getter____)();
                libkn_KInt (*com_jiang_nowinkotlin_core_platform_OhosVideoAVPlayerProxy$stableprop_getter____)();
                libkn_KInt (*com_jiang_nowinkotlin_core_platform_PlatformContext$stableprop_getter____)();
                libkn_KInt (*com_jiang_nowinkotlin_core_platform_SetScreenOrientationProxy$stableprop_getter____)();
                libkn_KInt (*com_jiang_nowinkotlin_core_platform_OHOSPlatform$stableprop_getter_____)();
                libkn_KInt (*com_jiang_nowinkotlin_core_platform_OhosServicesProxy$stableprop_getter_____)();
                libkn_KInt (*com_jiang_nowinkotlin_core_platform_OhosVideoAVPlayerFactoryProxy$stableprop_getter_____)();
                libkn_KInt (*com_jiang_nowinkotlin_core_platform_OhosVideoAVPlayerProxy$stableprop_getter_____)();
                libkn_KInt (*com_jiang_nowinkotlin_core_platform_PlatformContext$stableprop_getter_____)();
                libkn_KInt (*com_jiang_nowinkotlin_core_platform_SetScreenOrientationProxy$stableprop_getter_____)();
                libkn_KInt (*com_jiang_nowinkotlin_core_platform_OHOSPlatform$stableprop_getter______)();
                libkn_KInt (*com_jiang_nowinkotlin_core_platform_OhosServicesProxy$stableprop_getter______)();
                libkn_KInt (*com_jiang_nowinkotlin_core_platform_OhosVideoAVPlayerFactoryProxy$stableprop_getter______)();
                libkn_KInt (*com_jiang_nowinkotlin_core_platform_OhosVideoAVPlayerProxy$stableprop_getter______)();
                libkn_KInt (*com_jiang_nowinkotlin_core_platform_PlatformContext$stableprop_getter______)();
                libkn_KInt (*com_jiang_nowinkotlin_core_platform_SetScreenOrientationProxy$stableprop_getter______)();
                libkn_kref_androidx_compose_runtime_ProvidableCompositionLocal (*get_LocalPlatformContext)();
                libkn_KInt (*com_jiang_nowinkotlin_core_platform_OHOSPlatform$stableprop_getter_______)();
                libkn_KInt (*com_jiang_nowinkotlin_core_platform_OhosServicesProxy$stableprop_getter_______)();
                libkn_KInt (*com_jiang_nowinkotlin_core_platform_OhosVideoAVPlayerFactoryProxy$stableprop_getter_______)();
                libkn_KInt (*com_jiang_nowinkotlin_core_platform_OhosVideoAVPlayerProxy$stableprop_getter_______)();
                libkn_KInt (*com_jiang_nowinkotlin_core_platform_PlatformContext$stableprop_getter_______)();
                libkn_KInt (*com_jiang_nowinkotlin_core_platform_SetScreenOrientationProxy$stableprop_getter_______)();
              } platform;
              struct {
                struct {
                  struct {
                    libkn_KType* (*_type)(void);
                    libkn_kref_com_jiang_nowinkotlin_core_ui_components_BottomNavItem (*BottomNavItem)(const char* title, libkn_kref_androidx_compose_ui_graphics_vector_ImageVector icon, const char* route);
                    libkn_kref_androidx_compose_ui_graphics_vector_ImageVector (*get_icon)(libkn_kref_com_jiang_nowinkotlin_core_ui_components_BottomNavItem thiz);
                    const char* (*get_route)(libkn_kref_com_jiang_nowinkotlin_core_ui_components_BottomNavItem thiz);
                    const char* (*get_title)(libkn_kref_com_jiang_nowinkotlin_core_ui_components_BottomNavItem thiz);
                    const char* (*component1)(libkn_kref_com_jiang_nowinkotlin_core_ui_components_BottomNavItem thiz);
                    libkn_kref_androidx_compose_ui_graphics_vector_ImageVector (*component2)(libkn_kref_com_jiang_nowinkotlin_core_ui_components_BottomNavItem thiz);
                    const char* (*component3)(libkn_kref_com_jiang_nowinkotlin_core_ui_components_BottomNavItem thiz);
                    libkn_kref_com_jiang_nowinkotlin_core_ui_components_BottomNavItem (*copy)(libkn_kref_com_jiang_nowinkotlin_core_ui_components_BottomNavItem thiz, const char* title, libkn_kref_androidx_compose_ui_graphics_vector_ImageVector icon, const char* route);
                    libkn_KBoolean (*equals)(libkn_kref_com_jiang_nowinkotlin_core_ui_components_BottomNavItem thiz, libkn_kref_kotlin_Any other);
                    libkn_KInt (*hashCode)(libkn_kref_com_jiang_nowinkotlin_core_ui_components_BottomNavItem thiz);
                    const char* (*toString)(libkn_kref_com_jiang_nowinkotlin_core_ui_components_BottomNavItem thiz);
                  } BottomNavItem;
                  libkn_KInt (*com_jiang_nowinkotlin_core_ui_components_AsyncImageState_Error$stableprop_getter)();
                  libkn_KInt (*com_jiang_nowinkotlin_core_ui_components_AsyncImageState_Loading$stableprop_getter)();
                  libkn_KInt (*com_jiang_nowinkotlin_core_ui_components_AsyncImageState_Success$stableprop_getter)();
                  libkn_KInt (*com_jiang_nowinkotlin_core_ui_components_BottomNavItem$stableprop_getter)();
                  libkn_kref_kotlin_collections_List (*get_bottomNavItems)();
                  libkn_KInt (*com_jiang_nowinkotlin_core_ui_components_AsyncImageState_Error$stableprop_getter_)();
                  libkn_KInt (*com_jiang_nowinkotlin_core_ui_components_AsyncImageState_Loading$stableprop_getter_)();
                  libkn_KInt (*com_jiang_nowinkotlin_core_ui_components_AsyncImageState_Success$stableprop_getter_)();
                  libkn_KInt (*com_jiang_nowinkotlin_core_ui_components_BottomNavItem$stableprop_getter_)();
                  libkn_KInt (*com_jiang_nowinkotlin_core_ui_components_AsyncImageState_Error$stableprop_getter__)();
                  libkn_KInt (*com_jiang_nowinkotlin_core_ui_components_AsyncImageState_Loading$stableprop_getter__)();
                  libkn_KInt (*com_jiang_nowinkotlin_core_ui_components_AsyncImageState_Success$stableprop_getter__)();
                  libkn_KInt (*com_jiang_nowinkotlin_core_ui_components_BottomNavItem$stableprop_getter__)();
                  libkn_KInt (*com_jiang_nowinkotlin_core_ui_components_AsyncImageState_Error$stableprop_getter___)();
                  libkn_KInt (*com_jiang_nowinkotlin_core_ui_components_AsyncImageState_Loading$stableprop_getter___)();
                  libkn_KInt (*com_jiang_nowinkotlin_core_ui_components_AsyncImageState_Success$stableprop_getter___)();
                  libkn_KInt (*com_jiang_nowinkotlin_core_ui_components_BottomNavItem$stableprop_getter___)();
                } components;
                struct {
                  libkn_KULong (*get_BorderPrimary)();
                  libkn_KULong (*get_BorderSecondary)();
                  libkn_KULong (*get_KotlinAccent)();
                  libkn_KULong (*get_KotlinDark)();
                  libkn_KULong (*get_KotlinPrimary)();
                  libkn_KULong (*get_KotlinSecondary)();
                  libkn_KULong (*get_KotlinSurface)();
                  libkn_KULong (*get_SemanticError)();
                  libkn_KULong (*get_SemanticSuccess)();
                  libkn_KULong (*get_SemanticWarning)();
                  libkn_KULong (*get_SurfaceOverlay)();
                  libkn_KULong (*get_SurfaceOverlay10)();
                  libkn_KULong (*get_SurfaceOverlay15)();
                  libkn_KULong (*get_TextDisabled)();
                  libkn_KULong (*get_TextPrimary)();
                  libkn_KULong (*get_TextSecondary)();
                  libkn_KULong (*get_TextTertiary)();
                  libkn_kref_androidx_compose_material_Typography (*get_Typography)();
                } theme;
              } ui;
              struct {
                void* (*get_nativeResourceManager)();
                void (*set_nativeResourceManager)(void* set);
              } util;
            } core;
            struct {
              struct {
                struct {
                  struct {
                    libkn_KType* (*_type)(void);
                    libkn_kref_com_jiang_nowinkotlin_features_audio_platform_OhosAVPlayerProxy (*OhosAVPlayerProxy)();
                    libkn_KLong (*currentTime)(libkn_kref_com_jiang_nowinkotlin_features_audio_platform_OhosAVPlayerProxy thiz);
                    libkn_KLong (*duration)(libkn_kref_com_jiang_nowinkotlin_features_audio_platform_OhosAVPlayerProxy thiz);
                    void (*pause)(libkn_kref_com_jiang_nowinkotlin_features_audio_platform_OhosAVPlayerProxy thiz);
                    void (*play)(libkn_kref_com_jiang_nowinkotlin_features_audio_platform_OhosAVPlayerProxy thiz);
                    void (*prepareAndPlay)(libkn_kref_com_jiang_nowinkotlin_features_audio_platform_OhosAVPlayerProxy thiz, libkn_KInt index, const char* title, const char* audioUrl, const char* imageUrl, libkn_KBoolean playImmediately);
                    void (*seekTo)(libkn_kref_com_jiang_nowinkotlin_features_audio_platform_OhosAVPlayerProxy thiz, libkn_KLong positionMs);
                  } OhosAVPlayerProxy;
                  struct {
                    libkn_KType* (*_type)(void);
                    libkn_kref_com_jiang_nowinkotlin_features_audio_platform_OhosStateListenerProvider (*OhosStateListenerProvider)();
                    libkn_KInt (*getMinParamsSize)(libkn_kref_com_jiang_nowinkotlin_features_audio_platform_OhosStateListenerProvider thiz, const char* method);
                    libkn_kref_kotlin_Array (*getParamsTypeList)(libkn_kref_com_jiang_nowinkotlin_features_audio_platform_OhosStateListenerProvider thiz, const char* method);
                    libkn_kref_kotlin_reflect_KClass (*getReturnType)(libkn_kref_com_jiang_nowinkotlin_features_audio_platform_OhosStateListenerProvider thiz, const char* method);
                    libkn_kref_kotlin_Any (*invoke)(libkn_kref_com_jiang_nowinkotlin_features_audio_platform_OhosStateListenerProvider thiz, const char* method, libkn_kref_kotlin_Array params);
                  } OhosStateListenerProvider;
                  struct {
                    libkn_KType* (*_type)(void);
                    libkn_kref_com_jiang_nowinkotlin_features_audio_platform_MediaCommandHandlerProvider (*MediaCommandHandlerProvider)();
                    libkn_KInt (*getMinParamsSize)(libkn_kref_com_jiang_nowinkotlin_features_audio_platform_MediaCommandHandlerProvider thiz, const char* method);
                    libkn_kref_kotlin_Array (*getParamsTypeList)(libkn_kref_com_jiang_nowinkotlin_features_audio_platform_MediaCommandHandlerProvider thiz, const char* method);
                    libkn_kref_kotlin_reflect_KClass (*getReturnType)(libkn_kref_com_jiang_nowinkotlin_features_audio_platform_MediaCommandHandlerProvider thiz, const char* method);
                    libkn_kref_kotlin_Any (*invoke)(libkn_kref_com_jiang_nowinkotlin_features_audio_platform_MediaCommandHandlerProvider thiz, const char* method, libkn_kref_kotlin_Array params);
                  } MediaCommandHandlerProvider;
                  struct {
                    libkn_KType* (*_type)(void);
                    libkn_KLong (*currentTime)(libkn_kref_com_jiang_nowinkotlin_features_audio_platform_OhosAVPlayer thiz);
                    libkn_KLong (*duration)(libkn_kref_com_jiang_nowinkotlin_features_audio_platform_OhosAVPlayer thiz);
                    void (*pause)(libkn_kref_com_jiang_nowinkotlin_features_audio_platform_OhosAVPlayer thiz);
                    void (*play)(libkn_kref_com_jiang_nowinkotlin_features_audio_platform_OhosAVPlayer thiz);
                    void (*prepareAndPlay)(libkn_kref_com_jiang_nowinkotlin_features_audio_platform_OhosAVPlayer thiz, libkn_KInt index, const char* title, const char* audioUrl, const char* imageUrl, libkn_KBoolean playImmediately);
                    void (*seekTo)(libkn_kref_com_jiang_nowinkotlin_features_audio_platform_OhosAVPlayer thiz, libkn_KLong positionMs);
                  } OhosAVPlayer;
                  struct {
                    libkn_KType* (*_type)(void);
                    libkn_kref_com_jiang_nowinkotlin_features_audio_platform_OhosStateListener (*OhosStateListener)();
                    void (*updatePlaybackState)(libkn_kref_com_jiang_nowinkotlin_features_audio_platform_OhosStateListener thiz, libkn_KBoolean isPlaying, libkn_KLong positionMs, libkn_KLong duration);
                  } OhosStateListener;
                  struct {
                    libkn_KType* (*_type)(void);
                    libkn_kref_com_jiang_nowinkotlin_features_audio_platform_MediaCommandHandler (*MediaCommandHandler)();
                    void (*onNext)(libkn_kref_com_jiang_nowinkotlin_features_audio_platform_MediaCommandHandler thiz);
                    void (*onPrevious)(libkn_kref_com_jiang_nowinkotlin_features_audio_platform_MediaCommandHandler thiz);
                  } MediaCommandHandler;
                  struct {
                    libkn_KType* (*_type)(void);
                    libkn_kref_com_jiang_nowinkotlin_features_audio_platform_OhosPlayerStateManager (*OhosPlayerStateManager)();
                    void (*cleanup)(libkn_kref_com_jiang_nowinkotlin_features_audio_platform_OhosPlayerStateManager thiz);
                    libkn_KLong (*getCurrentPosition)(libkn_kref_com_jiang_nowinkotlin_features_audio_platform_OhosPlayerStateManager thiz);
                    libkn_KLong (*getDuration)(libkn_kref_com_jiang_nowinkotlin_features_audio_platform_OhosPlayerStateManager thiz);
                    void (*initializePlayer)(libkn_kref_com_jiang_nowinkotlin_features_audio_platform_OhosPlayerStateManager thiz, libkn_KLong positionMs);
                    void (*pause)(libkn_kref_com_jiang_nowinkotlin_features_audio_platform_OhosPlayerStateManager thiz);
                    void (*play)(libkn_kref_com_jiang_nowinkotlin_features_audio_platform_OhosPlayerStateManager thiz);
                    void (*prepareAndPlay)(libkn_kref_com_jiang_nowinkotlin_features_audio_platform_OhosPlayerStateManager thiz, libkn_KInt index, const char* title, const char* audioUrl, const char* imageUrl, libkn_KBoolean playImmediately);
                    void (*setPosition)(libkn_kref_com_jiang_nowinkotlin_features_audio_platform_OhosPlayerStateManager thiz, libkn_KLong positionMs);
                    void (*stop)(libkn_kref_com_jiang_nowinkotlin_features_audio_platform_OhosPlayerStateManager thiz);
                  } OhosPlayerStateManager;
                  struct {
                    libkn_KType* (*_type)(void);
                    libkn_kref_com_jiang_nowinkotlin_features_audio_platform_PlaylistManager (*_instance)();
                    libkn_KInt (*get_currentIndex)(libkn_kref_com_jiang_nowinkotlin_features_audio_platform_PlaylistManager thiz);
                    void (*clear)(libkn_kref_com_jiang_nowinkotlin_features_audio_platform_PlaylistManager thiz);
                    libkn_kref_com_jiang_nowinkotlin_shared_data_Episode (*getCurrentMusic)(libkn_kref_com_jiang_nowinkotlin_features_audio_platform_PlaylistManager thiz);
                    libkn_kref_kotlin_Int (*getNextIndex)(libkn_kref_com_jiang_nowinkotlin_features_audio_platform_PlaylistManager thiz);
                    libkn_kref_kotlin_collections_List (*getPlaylist)(libkn_kref_com_jiang_nowinkotlin_features_audio_platform_PlaylistManager thiz);
                    libkn_kref_kotlin_Int (*getPreviousIndex)(libkn_kref_com_jiang_nowinkotlin_features_audio_platform_PlaylistManager thiz);
                    libkn_KBoolean (*hasNext)(libkn_kref_com_jiang_nowinkotlin_features_audio_platform_PlaylistManager thiz);
                    libkn_KBoolean (*hasPrevious)(libkn_kref_com_jiang_nowinkotlin_features_audio_platform_PlaylistManager thiz);
                    void (*setCurrentIndex)(libkn_kref_com_jiang_nowinkotlin_features_audio_platform_PlaylistManager thiz, libkn_KInt index);
                    void (*updatePlaylist)(libkn_kref_com_jiang_nowinkotlin_features_audio_platform_PlaylistManager thiz, libkn_kref_kotlin_collections_List musics, libkn_KInt startIndex);
                  } PlaylistManager;
                  libkn_KInt (*com_jiang_nowinkotlin_features_audio_platform_MediaCommandHandler$stableprop_getter)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_audio_platform_MediaCommandHandlerProvider$stableprop_getter)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_audio_platform_OhosAVPlayerProxy$stableprop_getter)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_audio_platform_OhosPlayerStateManager$stableprop_getter)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_audio_platform_OhosStateListener$stableprop_getter)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_audio_platform_OhosStateListenerProvider$stableprop_getter)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_audio_platform_PlaylistManager$stableprop_getter)();
                  libkn_kref_com_jiang_nowinkotlin_features_audio_platform_OhosAVPlayer (*getOhosAVPlayerApi)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_audio_platform_MediaCommandHandler$stableprop_getter_)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_audio_platform_MediaCommandHandlerProvider$stableprop_getter_)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_audio_platform_OhosAVPlayerProxy$stableprop_getter_)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_audio_platform_OhosPlayerStateManager$stableprop_getter_)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_audio_platform_OhosStateListener$stableprop_getter_)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_audio_platform_OhosStateListenerProvider$stableprop_getter_)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_audio_platform_PlaylistManager$stableprop_getter_)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_audio_platform_MediaCommandHandler$stableprop_getter__)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_audio_platform_MediaCommandHandlerProvider$stableprop_getter__)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_audio_platform_OhosAVPlayerProxy$stableprop_getter__)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_audio_platform_OhosPlayerStateManager$stableprop_getter__)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_audio_platform_OhosStateListener$stableprop_getter__)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_audio_platform_OhosStateListenerProvider$stableprop_getter__)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_audio_platform_PlaylistManager$stableprop_getter__)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_audio_platform_MediaCommandHandler$stableprop_getter___)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_audio_platform_MediaCommandHandlerProvider$stableprop_getter___)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_audio_platform_OhosAVPlayerProxy$stableprop_getter___)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_audio_platform_OhosPlayerStateManager$stableprop_getter___)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_audio_platform_OhosStateListener$stableprop_getter___)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_audio_platform_OhosStateListenerProvider$stableprop_getter___)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_audio_platform_PlaylistManager$stableprop_getter___)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_audio_platform_MediaCommandHandler$stableprop_getter____)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_audio_platform_MediaCommandHandlerProvider$stableprop_getter____)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_audio_platform_OhosAVPlayerProxy$stableprop_getter____)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_audio_platform_OhosPlayerStateManager$stableprop_getter____)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_audio_platform_OhosStateListener$stableprop_getter____)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_audio_platform_OhosStateListenerProvider$stableprop_getter____)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_audio_platform_PlaylistManager$stableprop_getter____)();
                } platform;
                struct {
                  struct {
                    libkn_KType* (*_type)(void);
                    libkn_kref_com_jiang_nowinkotlin_features_audio_player_KmpAudioPlayer (*_instance)();
                    libkn_kref_com_jiang_nowinkotlin_features_audio_player_MediaPlaybackController (*get_playbackController)(libkn_kref_com_jiang_nowinkotlin_features_audio_player_KmpAudioPlayer thiz);
                    libkn_kref_kotlinx_coroutines_flow_StateFlow (*get_playbackState)(libkn_kref_com_jiang_nowinkotlin_features_audio_player_KmpAudioPlayer thiz);
                    void (*init)(libkn_kref_com_jiang_nowinkotlin_features_audio_player_KmpAudioPlayer thiz, libkn_kref_com_jiang_nowinkotlin_core_platform_PlatformContext context);
                  } KmpAudioPlayer;
                  struct {
                    libkn_KType* (*_type)(void);
                    void (*next)(libkn_kref_com_jiang_nowinkotlin_features_audio_player_MediaPlaybackController thiz);
                    void (*pause)(libkn_kref_com_jiang_nowinkotlin_features_audio_player_MediaPlaybackController thiz);
                    void (*play)(libkn_kref_com_jiang_nowinkotlin_features_audio_player_MediaPlaybackController thiz);
                    void (*prepare)(libkn_kref_com_jiang_nowinkotlin_features_audio_player_MediaPlaybackController thiz, libkn_kref_kotlin_collections_List musics, libkn_KInt index, libkn_KLong positionMs);
                    void (*previous)(libkn_kref_com_jiang_nowinkotlin_features_audio_player_MediaPlaybackController thiz);
                    void (*release)(libkn_kref_com_jiang_nowinkotlin_features_audio_player_MediaPlaybackController thiz);
                    void (*seekTo)(libkn_kref_com_jiang_nowinkotlin_features_audio_player_MediaPlaybackController thiz, libkn_KLong positionMs);
                    void (*skipTo)(libkn_kref_com_jiang_nowinkotlin_features_audio_player_MediaPlaybackController thiz, libkn_KInt musicIndex, libkn_KBoolean playImmediately);
                    void (*stop)(libkn_kref_com_jiang_nowinkotlin_features_audio_player_MediaPlaybackController thiz);
                  } MediaPlaybackController;
                  struct {
                    struct {
                      libkn_kref_com_jiang_nowinkotlin_features_audio_player_PlayingStatus (*get)(); /* enum entry for PLAYING. */
                    } PLAYING;
                    struct {
                      libkn_kref_com_jiang_nowinkotlin_features_audio_player_PlayingStatus (*get)(); /* enum entry for PAUSED. */
                    } PAUSED;
                    struct {
                      libkn_kref_com_jiang_nowinkotlin_features_audio_player_PlayingStatus (*get)(); /* enum entry for BUFFERING. */
                    } BUFFERING;
                    struct {
                      libkn_kref_com_jiang_nowinkotlin_features_audio_player_PlayingStatus (*get)(); /* enum entry for IDLE. */
                    } IDLE;
                    struct {
                      libkn_kref_com_jiang_nowinkotlin_features_audio_player_PlayingStatus (*get)(); /* enum entry for ENDED. */
                    } ENDED;
                    libkn_KType* (*_type)(void);
                  } PlayingStatus;
                  struct {
                    libkn_KType* (*_type)(void);
                    libkn_kref_com_jiang_nowinkotlin_features_audio_player_PlaybackState (*PlaybackState)(libkn_kref_com_jiang_nowinkotlin_shared_data_Episode episode, libkn_kref_com_jiang_nowinkotlin_features_audio_player_PlayingStatus playingStatus, libkn_KInt currentIndex, libkn_KBoolean hasPrevious, libkn_KBoolean hasNext, libkn_KLong position, libkn_KLong duration);
                    libkn_KInt (*get_currentIndex)(libkn_kref_com_jiang_nowinkotlin_features_audio_player_PlaybackState thiz);
                    const char* (*get_currentTime)(libkn_kref_com_jiang_nowinkotlin_features_audio_player_PlaybackState thiz);
                    libkn_KLong (*get_duration)(libkn_kref_com_jiang_nowinkotlin_features_audio_player_PlaybackState thiz);
                    libkn_kref_com_jiang_nowinkotlin_shared_data_Episode (*get_episode)(libkn_kref_com_jiang_nowinkotlin_features_audio_player_PlaybackState thiz);
                    libkn_KBoolean (*get_hasNext)(libkn_kref_com_jiang_nowinkotlin_features_audio_player_PlaybackState thiz);
                    libkn_KBoolean (*get_hasPrevious)(libkn_kref_com_jiang_nowinkotlin_features_audio_player_PlaybackState thiz);
                    libkn_KBoolean (*get_isPlaying)(libkn_kref_com_jiang_nowinkotlin_features_audio_player_PlaybackState thiz);
                    libkn_kref_com_jiang_nowinkotlin_features_audio_player_PlayingStatus (*get_playingStatus)(libkn_kref_com_jiang_nowinkotlin_features_audio_player_PlaybackState thiz);
                    libkn_KLong (*get_position)(libkn_kref_com_jiang_nowinkotlin_features_audio_player_PlaybackState thiz);
                    libkn_KFloat (*get_progress)(libkn_kref_com_jiang_nowinkotlin_features_audio_player_PlaybackState thiz);
                    const char* (*get_totalTime)(libkn_kref_com_jiang_nowinkotlin_features_audio_player_PlaybackState thiz);
                    libkn_kref_com_jiang_nowinkotlin_shared_data_Episode (*component1)(libkn_kref_com_jiang_nowinkotlin_features_audio_player_PlaybackState thiz);
                    libkn_kref_com_jiang_nowinkotlin_features_audio_player_PlayingStatus (*component2)(libkn_kref_com_jiang_nowinkotlin_features_audio_player_PlaybackState thiz);
                    libkn_KInt (*component3)(libkn_kref_com_jiang_nowinkotlin_features_audio_player_PlaybackState thiz);
                    libkn_KBoolean (*component4)(libkn_kref_com_jiang_nowinkotlin_features_audio_player_PlaybackState thiz);
                    libkn_KBoolean (*component5)(libkn_kref_com_jiang_nowinkotlin_features_audio_player_PlaybackState thiz);
                    libkn_KLong (*component6)(libkn_kref_com_jiang_nowinkotlin_features_audio_player_PlaybackState thiz);
                    libkn_KLong (*component7)(libkn_kref_com_jiang_nowinkotlin_features_audio_player_PlaybackState thiz);
                    libkn_kref_com_jiang_nowinkotlin_features_audio_player_PlaybackState (*copy)(libkn_kref_com_jiang_nowinkotlin_features_audio_player_PlaybackState thiz, libkn_kref_com_jiang_nowinkotlin_shared_data_Episode episode, libkn_kref_com_jiang_nowinkotlin_features_audio_player_PlayingStatus playingStatus, libkn_KInt currentIndex, libkn_KBoolean hasPrevious, libkn_KBoolean hasNext, libkn_KLong position, libkn_KLong duration);
                    libkn_KBoolean (*equals)(libkn_kref_com_jiang_nowinkotlin_features_audio_player_PlaybackState thiz, libkn_kref_kotlin_Any other);
                    libkn_KInt (*hashCode)(libkn_kref_com_jiang_nowinkotlin_features_audio_player_PlaybackState thiz);
                    const char* (*toString)(libkn_kref_com_jiang_nowinkotlin_features_audio_player_PlaybackState thiz);
                  } PlaybackState;
                  struct {
                    libkn_KType* (*_type)(void);
                    libkn_kref_com_jiang_nowinkotlin_features_audio_player_PlatformMediaPlaybackController (*PlatformMediaPlaybackController)();
                    void (*next)(libkn_kref_com_jiang_nowinkotlin_features_audio_player_PlatformMediaPlaybackController thiz);
                    void (*pause)(libkn_kref_com_jiang_nowinkotlin_features_audio_player_PlatformMediaPlaybackController thiz);
                    void (*play)(libkn_kref_com_jiang_nowinkotlin_features_audio_player_PlatformMediaPlaybackController thiz);
                    void (*prepare)(libkn_kref_com_jiang_nowinkotlin_features_audio_player_PlatformMediaPlaybackController thiz, libkn_kref_kotlin_collections_List musics, libkn_KInt index, libkn_KLong positionMs);
                    void (*previous)(libkn_kref_com_jiang_nowinkotlin_features_audio_player_PlatformMediaPlaybackController thiz);
                    void (*release)(libkn_kref_com_jiang_nowinkotlin_features_audio_player_PlatformMediaPlaybackController thiz);
                    void (*seekTo)(libkn_kref_com_jiang_nowinkotlin_features_audio_player_PlatformMediaPlaybackController thiz, libkn_KLong positionMs);
                    void (*skipTo)(libkn_kref_com_jiang_nowinkotlin_features_audio_player_PlatformMediaPlaybackController thiz, libkn_KInt musicIndex, libkn_KBoolean playImmediately);
                    void (*stop)(libkn_kref_com_jiang_nowinkotlin_features_audio_player_PlatformMediaPlaybackController thiz);
                  } PlatformMediaPlaybackController;
                  libkn_KInt (*com_jiang_nowinkotlin_features_audio_player_KmpAudioPlayer$stableprop_getter)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_audio_player_PlatformMediaPlaybackController$stableprop_getter)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_audio_player_PlaybackState$stableprop_getter)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_audio_player_PlaybackStateManager$stableprop_getter)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_audio_player_KmpAudioPlayer$stableprop_getter_)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_audio_player_PlatformMediaPlaybackController$stableprop_getter_)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_audio_player_PlaybackState$stableprop_getter_)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_audio_player_PlaybackStateManager$stableprop_getter_)();
                  libkn_KInt (*get_CURRENT_INDEX_UNSET)();
                  libkn_KLong (*get_TIME_UNSET)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_audio_player_KmpAudioPlayer$stableprop_getter__)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_audio_player_PlatformMediaPlaybackController$stableprop_getter__)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_audio_player_PlaybackState$stableprop_getter__)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_audio_player_PlaybackStateManager$stableprop_getter__)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_audio_player_KmpAudioPlayer$stableprop_getter___)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_audio_player_PlatformMediaPlaybackController$stableprop_getter___)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_audio_player_PlaybackState$stableprop_getter___)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_audio_player_PlaybackStateManager$stableprop_getter___)();
                } player;
                struct {
                  libkn_KInt (*com_jiang_nowinkotlin_features_audio_ui_AudioPlayerScreen$stableprop_getter)();
                } ui;
              } audio;
              struct {
                struct {
                  libkn_KInt (*com_jiang_nowinkotlin_features_home_viewmodel_HomeUIState$stableprop_getter)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_home_viewmodel_HomeViewModel$stableprop_getter)();
                } viewmodel;
              } home;
              struct {
                struct {
                  libkn_kref_androidx_compose_ui_graphics_vector_ImageVector (*get_ChevronRight)(libkn_kref_androidx_compose_material_icons_Icons_Filled thiz);
                } ui;
                struct {
                  libkn_KInt (*com_jiang_nowinkotlin_features_monthlyreport_viewmodel_MonthlyReportUIState$stableprop_getter)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_monthlyreport_viewmodel_MonthlyReportViewModel$stableprop_getter)();
                } viewmodel;
              } monthlyreport;
              struct {
                struct {
                  struct {
                    libkn_KType* (*_type)(void);
                    libkn_kref_com_jiang_nowinkotlin_features_video_player_Video (*Video)(libkn_KInt id, const char* title, const char* url);
                    libkn_KInt (*get_id)(libkn_kref_com_jiang_nowinkotlin_features_video_player_Video thiz);
                    const char* (*get_title)(libkn_kref_com_jiang_nowinkotlin_features_video_player_Video thiz);
                    const char* (*get_url)(libkn_kref_com_jiang_nowinkotlin_features_video_player_Video thiz);
                    libkn_KInt (*component1)(libkn_kref_com_jiang_nowinkotlin_features_video_player_Video thiz);
                    const char* (*component2)(libkn_kref_com_jiang_nowinkotlin_features_video_player_Video thiz);
                    const char* (*component3)(libkn_kref_com_jiang_nowinkotlin_features_video_player_Video thiz);
                    libkn_kref_com_jiang_nowinkotlin_features_video_player_Video (*copy)(libkn_kref_com_jiang_nowinkotlin_features_video_player_Video thiz, libkn_KInt id, const char* title, const char* url);
                    libkn_KBoolean (*equals)(libkn_kref_com_jiang_nowinkotlin_features_video_player_Video thiz, libkn_kref_kotlin_Any other);
                    libkn_KInt (*hashCode)(libkn_kref_com_jiang_nowinkotlin_features_video_player_Video thiz);
                    const char* (*toString)(libkn_kref_com_jiang_nowinkotlin_features_video_player_Video thiz);
                  } Video;
                  struct {
                    struct {
                      libkn_kref_com_jiang_nowinkotlin_features_video_player_ScreenOrientation (*get)(); /* enum entry for PORTRAIT. */
                    } PORTRAIT;
                    struct {
                      libkn_kref_com_jiang_nowinkotlin_features_video_player_ScreenOrientation (*get)(); /* enum entry for LANDSCAPE. */
                    } LANDSCAPE;
                    struct {
                      libkn_kref_com_jiang_nowinkotlin_features_video_player_ScreenOrientation (*get)(); /* enum entry for UNSPECIFIED. */
                    } UNSPECIFIED;
                    libkn_KType* (*_type)(void);
                  } ScreenOrientation;
                  struct {
                    libkn_KType* (*_type)(void);
                    libkn_kref_androidx_compose_runtime_State (*get_currentPosition)(libkn_kref_com_jiang_nowinkotlin_features_video_player_VideoPlayerController thiz);
                    libkn_kref_androidx_compose_runtime_State (*get_duration)(libkn_kref_com_jiang_nowinkotlin_features_video_player_VideoPlayerController thiz);
                    libkn_kref_androidx_compose_runtime_State (*get_isBuffering)(libkn_kref_com_jiang_nowinkotlin_features_video_player_VideoPlayerController thiz);
                    libkn_kref_androidx_compose_runtime_State (*get_isPlaying)(libkn_kref_com_jiang_nowinkotlin_features_video_player_VideoPlayerController thiz);
                    libkn_kref_androidx_compose_runtime_State (*get_progress)(libkn_kref_com_jiang_nowinkotlin_features_video_player_VideoPlayerController thiz);
                    void (*playPause)(libkn_kref_com_jiang_nowinkotlin_features_video_player_VideoPlayerController thiz);
                    void (*release)(libkn_kref_com_jiang_nowinkotlin_features_video_player_VideoPlayerController thiz);
                    void (*seekTo)(libkn_kref_com_jiang_nowinkotlin_features_video_player_VideoPlayerController thiz, libkn_KLong position);
                    void (*seekToProgress)(libkn_kref_com_jiang_nowinkotlin_features_video_player_VideoPlayerController thiz, libkn_KFloat progress);
                  } VideoPlayerController;
                  struct {
                    libkn_KType* (*_type)(void);
                    libkn_kref_com_jiang_nowinkotlin_features_video_player_OHOSVideoPlayerController (*OHOSVideoPlayerController)(const char* videoUrl);
                    libkn_kref_androidx_compose_runtime_State (*get_currentPosition)(libkn_kref_com_jiang_nowinkotlin_features_video_player_OHOSVideoPlayerController thiz);
                    libkn_kref_androidx_compose_runtime_State (*get_duration)(libkn_kref_com_jiang_nowinkotlin_features_video_player_OHOSVideoPlayerController thiz);
                    libkn_kref_androidx_compose_runtime_State (*get_isBuffering)(libkn_kref_com_jiang_nowinkotlin_features_video_player_OHOSVideoPlayerController thiz);
                    libkn_kref_androidx_compose_runtime_State (*get_isPlaying)(libkn_kref_com_jiang_nowinkotlin_features_video_player_OHOSVideoPlayerController thiz);
                    libkn_kref_com_jiang_nowinkotlin_core_platform_OhosVideoAVPlayer (*get_player)(libkn_kref_com_jiang_nowinkotlin_features_video_player_OHOSVideoPlayerController thiz);
                    libkn_kref_androidx_compose_runtime_State (*get_progress)(libkn_kref_com_jiang_nowinkotlin_features_video_player_OHOSVideoPlayerController thiz);
                    const char* (*get_videoUrl)(libkn_kref_com_jiang_nowinkotlin_features_video_player_OHOSVideoPlayerController thiz);
                    void (*playPause)(libkn_kref_com_jiang_nowinkotlin_features_video_player_OHOSVideoPlayerController thiz);
                    void (*release)(libkn_kref_com_jiang_nowinkotlin_features_video_player_OHOSVideoPlayerController thiz);
                    void (*seekTo)(libkn_kref_com_jiang_nowinkotlin_features_video_player_OHOSVideoPlayerController thiz, libkn_KLong position);
                    void (*seekToProgress)(libkn_kref_com_jiang_nowinkotlin_features_video_player_OHOSVideoPlayerController thiz, libkn_KFloat progress);
                    void (*updateProgress)(libkn_kref_com_jiang_nowinkotlin_features_video_player_OHOSVideoPlayerController thiz);
                  } OHOSVideoPlayerController;
                  libkn_KInt (*com_jiang_nowinkotlin_features_video_player_OHOSVideoPlayerController$stableprop_getter)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_video_player_Video$stableprop_getter)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_video_player_OHOSVideoPlayerController$stableprop_getter_)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_video_player_Video$stableprop_getter_)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_video_player_OHOSVideoPlayerController$stableprop_getter__)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_video_player_Video$stableprop_getter__)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_video_player_OHOSVideoPlayerController$stableprop_getter___)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_video_player_Video$stableprop_getter___)();
                  void (*setScreenOrientation)(libkn_kref_com_jiang_nowinkotlin_features_video_player_ScreenOrientation orientation);
                  void (*setSystemBarsVisibility)(libkn_KBoolean visible);
                  libkn_KInt (*com_jiang_nowinkotlin_features_video_player_OHOSVideoPlayerController$stableprop_getter____)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_video_player_Video$stableprop_getter____)();
                } player;
                struct {
                  libkn_KInt (*com_jiang_nowinkotlin_features_video_ui_VideoPlayerScreen$stableprop_getter)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_video_ui_VideoPlayerScreen$stableprop_getter_)();
                } ui;
              } video;
              struct {
                struct {
                  struct {
                    libkn_KType* (*_type)(void);
                    void (*get_webView)(libkn_kref_com_jiang_nowinkotlin_features_webview_component_IWebView thiz);
                    void (*loadUrl)(libkn_kref_com_jiang_nowinkotlin_features_webview_component_IWebView thiz, const char* url);
                  } IWebView;
                  struct {
                    struct {
                      libkn_KType* (*_type)(void);
                      libkn_kref_com_jiang_nowinkotlin_features_webview_component_LoadingState_Initializing (*_instance)();
                      libkn_KBoolean (*equals)(libkn_kref_com_jiang_nowinkotlin_features_webview_component_LoadingState_Initializing thiz, libkn_kref_kotlin_Any other);
                      libkn_KInt (*hashCode)(libkn_kref_com_jiang_nowinkotlin_features_webview_component_LoadingState_Initializing thiz);
                      const char* (*toString)(libkn_kref_com_jiang_nowinkotlin_features_webview_component_LoadingState_Initializing thiz);
                    } Initializing;
                    struct {
                      libkn_KType* (*_type)(void);
                      libkn_kref_com_jiang_nowinkotlin_features_webview_component_LoadingState_Loading (*_instance)();
                      libkn_KBoolean (*equals)(libkn_kref_com_jiang_nowinkotlin_features_webview_component_LoadingState_Loading thiz, libkn_kref_kotlin_Any other);
                      libkn_KInt (*hashCode)(libkn_kref_com_jiang_nowinkotlin_features_webview_component_LoadingState_Loading thiz);
                      const char* (*toString)(libkn_kref_com_jiang_nowinkotlin_features_webview_component_LoadingState_Loading thiz);
                    } Loading;
                    struct {
                      libkn_KType* (*_type)(void);
                      libkn_kref_com_jiang_nowinkotlin_features_webview_component_LoadingState_Finished (*_instance)();
                      libkn_KBoolean (*equals)(libkn_kref_com_jiang_nowinkotlin_features_webview_component_LoadingState_Finished thiz, libkn_kref_kotlin_Any other);
                      libkn_KInt (*hashCode)(libkn_kref_com_jiang_nowinkotlin_features_webview_component_LoadingState_Finished thiz);
                      const char* (*toString)(libkn_kref_com_jiang_nowinkotlin_features_webview_component_LoadingState_Finished thiz);
                    } Finished;
                    libkn_KType* (*_type)(void);
                    libkn_kref_com_jiang_nowinkotlin_features_webview_component_LoadingState (*LoadingState)();
                  } LoadingState;
                  struct {
                    libkn_KType* (*_type)(void);
                    libkn_kref_com_jiang_nowinkotlin_features_webview_component_WebViewState (*WebViewState)(const char* url);
                    libkn_KBoolean (*get_isPageFinished)(libkn_kref_com_jiang_nowinkotlin_features_webview_component_WebViewState thiz);
                    libkn_kref_com_jiang_nowinkotlin_features_webview_component_LoadingState (*get_loadingState)(libkn_kref_com_jiang_nowinkotlin_features_webview_component_WebViewState thiz);
                    void (*get_nativeWebView)(libkn_kref_com_jiang_nowinkotlin_features_webview_component_WebViewState thiz);
                    const char* (*get_webUrl)(libkn_kref_com_jiang_nowinkotlin_features_webview_component_WebViewState thiz);
                    void (*set_webUrl)(libkn_kref_com_jiang_nowinkotlin_features_webview_component_WebViewState thiz, const char* set);
                  } WebViewState;
                  struct {
                    libkn_KType* (*_type)(void);
                    libkn_kref_com_jiang_nowinkotlin_features_webview_component_IOSWebView (*IOSWebView)();
                    void (*get_webView)(libkn_kref_com_jiang_nowinkotlin_features_webview_component_IOSWebView thiz);
                    void (*loadUrl)(libkn_kref_com_jiang_nowinkotlin_features_webview_component_IOSWebView thiz, const char* url);
                  } IOSWebView;
                  libkn_KInt (*com_jiang_nowinkotlin_features_webview_component_IOSWebView$stableprop_getter)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_webview_component_LoadingState$stableprop_getter)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_webview_component_LoadingState_Finished$stableprop_getter)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_webview_component_LoadingState_Initializing$stableprop_getter)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_webview_component_LoadingState_Loading$stableprop_getter)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_webview_component_WebViewState$stableprop_getter)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_webview_component_IOSWebView$stableprop_getter_)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_webview_component_LoadingState$stableprop_getter_)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_webview_component_LoadingState_Finished$stableprop_getter_)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_webview_component_LoadingState_Initializing$stableprop_getter_)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_webview_component_LoadingState_Loading$stableprop_getter_)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_webview_component_WebViewState$stableprop_getter_)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_webview_component_IOSWebView$stableprop_getter__)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_webview_component_LoadingState$stableprop_getter__)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_webview_component_LoadingState_Finished$stableprop_getter__)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_webview_component_LoadingState_Initializing$stableprop_getter__)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_webview_component_LoadingState_Loading$stableprop_getter__)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_webview_component_WebViewState$stableprop_getter__)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_webview_component_IOSWebView$stableprop_getter___)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_webview_component_LoadingState$stableprop_getter___)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_webview_component_LoadingState_Finished$stableprop_getter___)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_webview_component_LoadingState_Initializing$stableprop_getter___)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_webview_component_LoadingState_Loading$stableprop_getter___)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_webview_component_WebViewState$stableprop_getter___)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_webview_component_IOSWebView$stableprop_getter____)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_webview_component_LoadingState$stableprop_getter____)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_webview_component_LoadingState_Finished$stableprop_getter____)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_webview_component_LoadingState_Initializing$stableprop_getter____)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_webview_component_LoadingState_Loading$stableprop_getter____)();
                  libkn_KInt (*com_jiang_nowinkotlin_features_webview_component_WebViewState$stableprop_getter____)();
                } component;
              } webview;
            } features;
            struct {
              struct {
                struct {
                  struct {
                    libkn_KType* (*_type)(void);
                    libkn_kref_com_jiang_nowinkotlin_shared_data_MonthlyReportItem_$serializer (*_instance)();
                    libkn_kref_kotlinx_serialization_descriptors_SerialDescriptor (*get_descriptor)(libkn_kref_com_jiang_nowinkotlin_shared_data_MonthlyReportItem_$serializer thiz);
                    libkn_kref_kotlin_Array (*childSerializers)(libkn_kref_com_jiang_nowinkotlin_shared_data_MonthlyReportItem_$serializer thiz);
                    libkn_kref_com_jiang_nowinkotlin_shared_data_MonthlyReportItem (*deserialize)(libkn_kref_com_jiang_nowinkotlin_shared_data_MonthlyReportItem_$serializer thiz, libkn_kref_kotlinx_serialization_encoding_Decoder decoder);
                    void (*serialize)(libkn_kref_com_jiang_nowinkotlin_shared_data_MonthlyReportItem_$serializer thiz, libkn_kref_kotlinx_serialization_encoding_Encoder encoder, libkn_kref_com_jiang_nowinkotlin_shared_data_MonthlyReportItem value);
                  } $serializer;
                  struct {
                    libkn_KType* (*_type)(void);
                    libkn_kref_com_jiang_nowinkotlin_shared_data_MonthlyReportItem_Companion (*_instance)();
                    libkn_kref_kotlinx_serialization_KSerializer (*serializer)(libkn_kref_com_jiang_nowinkotlin_shared_data_MonthlyReportItem_Companion thiz);
                  } Companion;
                  libkn_KType* (*_type)(void);
                  libkn_kref_com_jiang_nowinkotlin_shared_data_MonthlyReportItem (*MonthlyReportItem)(const char* permalink, const char* publishDate, libkn_kref_kotlin_collections_List tags, const char* title);
                  const char* (*get_displayYMD)(libkn_kref_com_jiang_nowinkotlin_shared_data_MonthlyReportItem thiz);
                  const char* (*get_month)(libkn_kref_com_jiang_nowinkotlin_shared_data_MonthlyReportItem thiz);
                  const char* (*get_permalink)(libkn_kref_com_jiang_nowinkotlin_shared_data_MonthlyReportItem thiz);
                  const char* (*get_publishDate)(libkn_kref_com_jiang_nowinkotlin_shared_data_MonthlyReportItem thiz);
                  libkn_kref_kotlin_collections_List (*get_tags)(libkn_kref_com_jiang_nowinkotlin_shared_data_MonthlyReportItem thiz);
                  const char* (*get_title)(libkn_kref_com_jiang_nowinkotlin_shared_data_MonthlyReportItem thiz);
                  const char* (*get_year)(libkn_kref_com_jiang_nowinkotlin_shared_data_MonthlyReportItem thiz);
                  const char* (*component1)(libkn_kref_com_jiang_nowinkotlin_shared_data_MonthlyReportItem thiz);
                  const char* (*component2)(libkn_kref_com_jiang_nowinkotlin_shared_data_MonthlyReportItem thiz);
                  libkn_kref_kotlin_collections_List (*component3)(libkn_kref_com_jiang_nowinkotlin_shared_data_MonthlyReportItem thiz);
                  const char* (*component4)(libkn_kref_com_jiang_nowinkotlin_shared_data_MonthlyReportItem thiz);
                  libkn_kref_com_jiang_nowinkotlin_shared_data_MonthlyReportItem (*copy)(libkn_kref_com_jiang_nowinkotlin_shared_data_MonthlyReportItem thiz, const char* permalink, const char* publishDate, libkn_kref_kotlin_collections_List tags, const char* title);
                  libkn_KBoolean (*equals)(libkn_kref_com_jiang_nowinkotlin_shared_data_MonthlyReportItem thiz, libkn_kref_kotlin_Any other);
                  libkn_KInt (*hashCode)(libkn_kref_com_jiang_nowinkotlin_shared_data_MonthlyReportItem thiz);
                  const char* (*toString)(libkn_kref_com_jiang_nowinkotlin_shared_data_MonthlyReportItem thiz);
                } MonthlyReportItem;
                struct {
                  libkn_KType* (*_type)(void);
                  libkn_kref_com_jiang_nowinkotlin_shared_data_Episode (*Episode)(libkn_KInt index, libkn_KInt size, const char* episodeTitle, const char* pubDate, libkn_KInt episodeDuration, const char* imageUrl, const char* audioUrl, const char* description, libkn_kref_kotlin_collections_List tags);
                  const char* (*get_audioUrl)(libkn_kref_com_jiang_nowinkotlin_shared_data_Episode thiz);
                  const char* (*get_date)(libkn_kref_com_jiang_nowinkotlin_shared_data_Episode thiz);
                  const char* (*get_description)(libkn_kref_com_jiang_nowinkotlin_shared_data_Episode thiz);
                  const char* (*get_displayDescription)(libkn_kref_com_jiang_nowinkotlin_shared_data_Episode thiz);
                  const char* (*get_duration)(libkn_kref_com_jiang_nowinkotlin_shared_data_Episode thiz);
                  libkn_KInt (*get_episodeDuration)(libkn_kref_com_jiang_nowinkotlin_shared_data_Episode thiz);
                  const char* (*get_episodeNumber)(libkn_kref_com_jiang_nowinkotlin_shared_data_Episode thiz);
                  const char* (*get_episodeTitle)(libkn_kref_com_jiang_nowinkotlin_shared_data_Episode thiz);
                  const char* (*get_id)(libkn_kref_com_jiang_nowinkotlin_shared_data_Episode thiz);
                  const char* (*get_imageUrl)(libkn_kref_com_jiang_nowinkotlin_shared_data_Episode thiz);
                  libkn_KInt (*get_index)(libkn_kref_com_jiang_nowinkotlin_shared_data_Episode thiz);
                  const char* (*get_pubDate)(libkn_kref_com_jiang_nowinkotlin_shared_data_Episode thiz);
                  libkn_KInt (*get_size)(libkn_kref_com_jiang_nowinkotlin_shared_data_Episode thiz);
                  libkn_kref_kotlin_collections_List (*get_tags)(libkn_kref_com_jiang_nowinkotlin_shared_data_Episode thiz);
                  const char* (*get_title)(libkn_kref_com_jiang_nowinkotlin_shared_data_Episode thiz);
                  libkn_KInt (*component1)(libkn_kref_com_jiang_nowinkotlin_shared_data_Episode thiz);
                  libkn_KInt (*component2)(libkn_kref_com_jiang_nowinkotlin_shared_data_Episode thiz);
                  const char* (*component3)(libkn_kref_com_jiang_nowinkotlin_shared_data_Episode thiz);
                  const char* (*component4)(libkn_kref_com_jiang_nowinkotlin_shared_data_Episode thiz);
                  libkn_KInt (*component5)(libkn_kref_com_jiang_nowinkotlin_shared_data_Episode thiz);
                  const char* (*component6)(libkn_kref_com_jiang_nowinkotlin_shared_data_Episode thiz);
                  const char* (*component7)(libkn_kref_com_jiang_nowinkotlin_shared_data_Episode thiz);
                  const char* (*component8)(libkn_kref_com_jiang_nowinkotlin_shared_data_Episode thiz);
                  libkn_kref_kotlin_collections_List (*component9)(libkn_kref_com_jiang_nowinkotlin_shared_data_Episode thiz);
                  libkn_kref_com_jiang_nowinkotlin_shared_data_Episode (*copy)(libkn_kref_com_jiang_nowinkotlin_shared_data_Episode thiz, libkn_KInt index, libkn_KInt size, const char* episodeTitle, const char* pubDate, libkn_KInt episodeDuration, const char* imageUrl, const char* audioUrl, const char* description, libkn_kref_kotlin_collections_List tags);
                  libkn_KBoolean (*equals)(libkn_kref_com_jiang_nowinkotlin_shared_data_Episode thiz, libkn_kref_kotlin_Any other);
                  libkn_KInt (*hashCode)(libkn_kref_com_jiang_nowinkotlin_shared_data_Episode thiz);
                  const char* (*toString)(libkn_kref_com_jiang_nowinkotlin_shared_data_Episode thiz);
                } Episode;
                struct {
                  struct {
                    libkn_KType* (*_type)(void);
                    libkn_kref_com_jiang_nowinkotlin_shared_data_EpisodeModel_$serializer (*_instance)();
                    libkn_kref_kotlinx_serialization_descriptors_SerialDescriptor (*get_descriptor)(libkn_kref_com_jiang_nowinkotlin_shared_data_EpisodeModel_$serializer thiz);
                    libkn_kref_kotlin_Array (*childSerializers)(libkn_kref_com_jiang_nowinkotlin_shared_data_EpisodeModel_$serializer thiz);
                    libkn_kref_com_jiang_nowinkotlin_shared_data_EpisodeModel (*deserialize)(libkn_kref_com_jiang_nowinkotlin_shared_data_EpisodeModel_$serializer thiz, libkn_kref_kotlinx_serialization_encoding_Decoder decoder);
                    void (*serialize)(libkn_kref_com_jiang_nowinkotlin_shared_data_EpisodeModel_$serializer thiz, libkn_kref_kotlinx_serialization_encoding_Encoder encoder, libkn_kref_com_jiang_nowinkotlin_shared_data_EpisodeModel value);
                  } $serializer;
                  struct {
                    libkn_KType* (*_type)(void);
                    libkn_kref_com_jiang_nowinkotlin_shared_data_EpisodeModel_Companion (*_instance)();
                    libkn_kref_kotlinx_serialization_KSerializer (*serializer)(libkn_kref_com_jiang_nowinkotlin_shared_data_EpisodeModel_Companion thiz);
                  } Companion;
                  libkn_KType* (*_type)(void);
                  libkn_kref_com_jiang_nowinkotlin_shared_data_EpisodeModel (*EpisodeModel)(const char* title, const char* pubDate, const char* author, const char* thumbnail, const char* description, libkn_kref_com_jiang_nowinkotlin_shared_data_AudioResourceModel audioResource);
                  libkn_kref_com_jiang_nowinkotlin_shared_data_AudioResourceModel (*get_audioResource)(libkn_kref_com_jiang_nowinkotlin_shared_data_EpisodeModel thiz);
                  const char* (*get_author)(libkn_kref_com_jiang_nowinkotlin_shared_data_EpisodeModel thiz);
                  const char* (*get_description)(libkn_kref_com_jiang_nowinkotlin_shared_data_EpisodeModel thiz);
                  const char* (*get_pubDate)(libkn_kref_com_jiang_nowinkotlin_shared_data_EpisodeModel thiz);
                  const char* (*get_thumbnail)(libkn_kref_com_jiang_nowinkotlin_shared_data_EpisodeModel thiz);
                  const char* (*get_title)(libkn_kref_com_jiang_nowinkotlin_shared_data_EpisodeModel thiz);
                  const char* (*component1)(libkn_kref_com_jiang_nowinkotlin_shared_data_EpisodeModel thiz);
                  const char* (*component2)(libkn_kref_com_jiang_nowinkotlin_shared_data_EpisodeModel thiz);
                  const char* (*component3)(libkn_kref_com_jiang_nowinkotlin_shared_data_EpisodeModel thiz);
                  const char* (*component4)(libkn_kref_com_jiang_nowinkotlin_shared_data_EpisodeModel thiz);
                  const char* (*component5)(libkn_kref_com_jiang_nowinkotlin_shared_data_EpisodeModel thiz);
                  libkn_kref_com_jiang_nowinkotlin_shared_data_AudioResourceModel (*component6)(libkn_kref_com_jiang_nowinkotlin_shared_data_EpisodeModel thiz);
                  libkn_kref_com_jiang_nowinkotlin_shared_data_EpisodeModel (*copy)(libkn_kref_com_jiang_nowinkotlin_shared_data_EpisodeModel thiz, const char* title, const char* pubDate, const char* author, const char* thumbnail, const char* description, libkn_kref_com_jiang_nowinkotlin_shared_data_AudioResourceModel audioResource);
                  libkn_KBoolean (*equals)(libkn_kref_com_jiang_nowinkotlin_shared_data_EpisodeModel thiz, libkn_kref_kotlin_Any other);
                  libkn_KInt (*hashCode)(libkn_kref_com_jiang_nowinkotlin_shared_data_EpisodeModel thiz);
                  const char* (*toString)(libkn_kref_com_jiang_nowinkotlin_shared_data_EpisodeModel thiz);
                } EpisodeModel;
                struct {
                  struct {
                    libkn_KType* (*_type)(void);
                    libkn_kref_com_jiang_nowinkotlin_shared_data_AudioResourceModel_$serializer (*_instance)();
                    libkn_kref_kotlinx_serialization_descriptors_SerialDescriptor (*get_descriptor)(libkn_kref_com_jiang_nowinkotlin_shared_data_AudioResourceModel_$serializer thiz);
                    libkn_kref_kotlin_Array (*childSerializers)(libkn_kref_com_jiang_nowinkotlin_shared_data_AudioResourceModel_$serializer thiz);
                    libkn_kref_com_jiang_nowinkotlin_shared_data_AudioResourceModel (*deserialize)(libkn_kref_com_jiang_nowinkotlin_shared_data_AudioResourceModel_$serializer thiz, libkn_kref_kotlinx_serialization_encoding_Decoder decoder);
                    void (*serialize)(libkn_kref_com_jiang_nowinkotlin_shared_data_AudioResourceModel_$serializer thiz, libkn_kref_kotlinx_serialization_encoding_Encoder encoder, libkn_kref_com_jiang_nowinkotlin_shared_data_AudioResourceModel value);
                  } $serializer;
                  struct {
                    libkn_KType* (*_type)(void);
                    libkn_kref_com_jiang_nowinkotlin_shared_data_AudioResourceModel_Companion (*_instance)();
                    libkn_kref_kotlinx_serialization_KSerializer (*serializer)(libkn_kref_com_jiang_nowinkotlin_shared_data_AudioResourceModel_Companion thiz);
                  } Companion;
                  libkn_KType* (*_type)(void);
                  libkn_kref_com_jiang_nowinkotlin_shared_data_AudioResourceModel (*AudioResourceModel)(const char* link, const char* type, libkn_KInt duration);
                  libkn_KInt (*get_duration)(libkn_kref_com_jiang_nowinkotlin_shared_data_AudioResourceModel thiz);
                  const char* (*get_link)(libkn_kref_com_jiang_nowinkotlin_shared_data_AudioResourceModel thiz);
                  const char* (*get_type)(libkn_kref_com_jiang_nowinkotlin_shared_data_AudioResourceModel thiz);
                  const char* (*component1)(libkn_kref_com_jiang_nowinkotlin_shared_data_AudioResourceModel thiz);
                  const char* (*component2)(libkn_kref_com_jiang_nowinkotlin_shared_data_AudioResourceModel thiz);
                  libkn_KInt (*component3)(libkn_kref_com_jiang_nowinkotlin_shared_data_AudioResourceModel thiz);
                  libkn_kref_com_jiang_nowinkotlin_shared_data_AudioResourceModel (*copy)(libkn_kref_com_jiang_nowinkotlin_shared_data_AudioResourceModel thiz, const char* link, const char* type, libkn_KInt duration);
                  libkn_KBoolean (*equals)(libkn_kref_com_jiang_nowinkotlin_shared_data_AudioResourceModel thiz, libkn_kref_kotlin_Any other);
                  libkn_KInt (*hashCode)(libkn_kref_com_jiang_nowinkotlin_shared_data_AudioResourceModel thiz);
                  const char* (*toString)(libkn_kref_com_jiang_nowinkotlin_shared_data_AudioResourceModel thiz);
                } AudioResourceModel;
                libkn_KInt (*com_jiang_nowinkotlin_shared_data_AudioResourceModel$stableprop_getter)();
                libkn_KInt (*com_jiang_nowinkotlin_shared_data_AudioResourceModel_$serializer$stableprop_getter)();
                libkn_KInt (*com_jiang_nowinkotlin_shared_data_DisplayItem$stableprop_getter)();
                libkn_KInt (*com_jiang_nowinkotlin_shared_data_DisplaySection$stableprop_getter)();
                libkn_KInt (*com_jiang_nowinkotlin_shared_data_Episode$stableprop_getter)();
                libkn_KInt (*com_jiang_nowinkotlin_shared_data_EpisodeModel$stableprop_getter)();
                libkn_KInt (*com_jiang_nowinkotlin_shared_data_EpisodeModel_$serializer$stableprop_getter)();
                libkn_KInt (*com_jiang_nowinkotlin_shared_data_MonthlyReportItem$stableprop_getter)();
                libkn_KInt (*com_jiang_nowinkotlin_shared_data_MonthlyReportItem_$serializer$stableprop_getter)();
              } data;
              struct {
                libkn_kref_androidx_compose_ui_graphics_vector_ImageVector (*get_FastForward)(libkn_kref_androidx_compose_material_icons_Icons_Filled thiz);
                libkn_kref_androidx_compose_ui_graphics_vector_ImageVector (*get_FastRewind)(libkn_kref_androidx_compose_material_icons_Icons_Filled thiz);
                libkn_kref_androidx_compose_ui_graphics_vector_ImageVector (*get_Newspaper)(libkn_kref_androidx_compose_material_icons_Icons_Filled thiz);
                libkn_kref_androidx_compose_ui_graphics_vector_ImageVector (*get_Pause)(libkn_kref_androidx_compose_material_icons_Icons_Filled thiz);
                libkn_kref_androidx_compose_ui_graphics_vector_ImageVector (*get_SkipNext)(libkn_kref_androidx_compose_material_icons_Icons_Filled thiz);
                libkn_kref_androidx_compose_ui_graphics_vector_ImageVector (*get_SkipPrevious)(libkn_kref_androidx_compose_material_icons_Icons_Filled thiz);
                libkn_kref_androidx_compose_ui_graphics_vector_ImageVector (*get_Whatshot)(libkn_kref_androidx_compose_material_icons_Icons_Filled thiz);
              } icons;
              struct {
                struct {
                  libkn_KType* (*_type)(void);
                  void (*onCreate)(libkn_kref_com_jiang_nowinkotlin_shared_viewmodel_LifecycleAware thiz);
                  void (*onDestroy)(libkn_kref_com_jiang_nowinkotlin_shared_viewmodel_LifecycleAware thiz);
                } LifecycleAware;
              } viewmodel;
            } shared;
            libkn_KInt (*com_jiang_nowinkotlin_Greeting$stableprop_getter)();
            libkn_KInt (*com_jiang_nowinkotlin_Greeting$stableprop_getter_)();
          } nowinkotlin;
        } jiang;
        struct {
          struct {
            struct {
              struct {
                struct {
                  void (*registerOhosAVPlayerProxy)();
                  void (*registerOhosServicesProxy)();
                  void (*registerOhosVideoAVPlayerFactoryProxy)();
                  void (*registerSetScreenOrientationProxy)();
                  void (*registerMediaCommandHandlerProvider)();
                  void (*registerOhosStateListenerProvider)();
                } composeApp;
                void (*initComposeApp)();
              } modules;
              void (*initBridge)();
              void (*initEnvExport)(void* env, void* value, libkn_KBoolean debug);
              void (*initialize)();
              void (*preInitEnv)(void* env, libkn_KBoolean debug);
            } knoi;
          } tmm;
        } tencent;
      } com;
      struct {
        struct {
          struct {
            struct {
              libkn_KInt (*nowinkotlin_composeapp_generated_resources_Res$stableprop_getter)();
              libkn_KInt (*nowinkotlin_composeapp_generated_resources_Res_drawable$stableprop_getter)();
              libkn_KInt (*nowinkotlin_composeapp_generated_resources_Res_font$stableprop_getter)();
              libkn_KInt (*nowinkotlin_composeapp_generated_resources_Res_string$stableprop_getter)();
              libkn_KInt (*nowinkotlin_composeapp_generated_resources_Res$stableprop_getter_)();
              libkn_KInt (*nowinkotlin_composeapp_generated_resources_Res_drawable$stableprop_getter_)();
              libkn_KInt (*nowinkotlin_composeapp_generated_resources_Res_font$stableprop_getter_)();
              libkn_KInt (*nowinkotlin_composeapp_generated_resources_Res_string$stableprop_getter_)();
            } resources;
          } generated;
        } composeapp;
      } nowinkotlin;
    } root;
  } kotlin;
} libkn_ExportedSymbols;
extern libkn_ExportedSymbols* libkn_symbols(void);
#ifdef __cplusplus
}  /* extern "C" */
#endif
#endif  /* KONAN_LIBKN_H */
