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
} libkn_kref_kotlin_collections_List;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_components_BottomNavItem;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_androidx_compose_ui_graphics_vector_ImageVector;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_kotlin_Any;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_androidx_compose_material_icons_Icons_Filled;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_mainpage_sectionItem_PieceData;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_mainpage_sectionItem_Game;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_androidx_compose_runtime_snapshots_SnapshotStateList;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_mainpage_Episode;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_com_jiang_nowinkotlin_mainpage_MonthlyReport;
typedef struct {
  libkn_KNativePtr pinned;
} libkn_kref_androidx_compose_material_Typography;

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
              struct {
                libkn_KType* (*_type)(void);
                libkn_kref_com_jiang_nowinkotlin_components_BottomNavItem (*BottomNavItem)(const char* title, libkn_kref_androidx_compose_ui_graphics_vector_ImageVector icon, const char* route);
                libkn_kref_androidx_compose_ui_graphics_vector_ImageVector (*get_icon)(libkn_kref_com_jiang_nowinkotlin_components_BottomNavItem thiz);
                const char* (*get_route)(libkn_kref_com_jiang_nowinkotlin_components_BottomNavItem thiz);
                const char* (*get_title)(libkn_kref_com_jiang_nowinkotlin_components_BottomNavItem thiz);
                const char* (*component1)(libkn_kref_com_jiang_nowinkotlin_components_BottomNavItem thiz);
                libkn_kref_androidx_compose_ui_graphics_vector_ImageVector (*component2)(libkn_kref_com_jiang_nowinkotlin_components_BottomNavItem thiz);
                const char* (*component3)(libkn_kref_com_jiang_nowinkotlin_components_BottomNavItem thiz);
                libkn_kref_com_jiang_nowinkotlin_components_BottomNavItem (*copy)(libkn_kref_com_jiang_nowinkotlin_components_BottomNavItem thiz, const char* title, libkn_kref_androidx_compose_ui_graphics_vector_ImageVector icon, const char* route);
                libkn_KBoolean (*equals)(libkn_kref_com_jiang_nowinkotlin_components_BottomNavItem thiz, libkn_kref_kotlin_Any other);
                libkn_KInt (*hashCode)(libkn_kref_com_jiang_nowinkotlin_components_BottomNavItem thiz);
                const char* (*toString)(libkn_kref_com_jiang_nowinkotlin_components_BottomNavItem thiz);
              } BottomNavItem;
              libkn_kref_kotlin_collections_List (*get_bottomNavItems)();
              libkn_KInt (*com_jiang_nowinkotlin_components_BottomNavItem$stableprop_getter)();
              libkn_KInt (*com_jiang_nowinkotlin_components_BottomNavItem$stableprop_getter_)();
              libkn_KInt (*com_jiang_nowinkotlin_components_BottomNavItem$stableprop_getter__)();
            } components;
            struct {
              libkn_KInt (*com_jiang_nowinkotlin_data_DisplayItem$stableprop_getter)();
              libkn_KInt (*com_jiang_nowinkotlin_data_DisplaySection$stableprop_getter)();
            } data;
            struct {
              struct {
                struct {
                  libkn_KType* (*_type)(void);
                  libkn_kref_com_jiang_nowinkotlin_mainpage_sectionItem_PieceData (*PieceData)(libkn_kref_com_jiang_nowinkotlin_mainpage_sectionItem_Game game, libkn_KFloat velocity, libkn_KULong color);
                  libkn_KBoolean (*get_clicked)(libkn_kref_com_jiang_nowinkotlin_mainpage_sectionItem_PieceData thiz);
                  void (*set_clicked)(libkn_kref_com_jiang_nowinkotlin_mainpage_sectionItem_PieceData thiz, libkn_KBoolean set);
                  libkn_KULong (*get_color)(libkn_kref_com_jiang_nowinkotlin_mainpage_sectionItem_PieceData thiz);
                  libkn_kref_com_jiang_nowinkotlin_mainpage_sectionItem_Game (*get_game)(libkn_kref_com_jiang_nowinkotlin_mainpage_sectionItem_PieceData thiz);
                  libkn_KFloat (*get_position)(libkn_kref_com_jiang_nowinkotlin_mainpage_sectionItem_PieceData thiz);
                  void (*set_position)(libkn_kref_com_jiang_nowinkotlin_mainpage_sectionItem_PieceData thiz, libkn_KFloat set);
                  libkn_KFloat (*get_velocity)(libkn_kref_com_jiang_nowinkotlin_mainpage_sectionItem_PieceData thiz);
                  void (*click)(libkn_kref_com_jiang_nowinkotlin_mainpage_sectionItem_PieceData thiz);
                  libkn_kref_com_jiang_nowinkotlin_mainpage_sectionItem_Game (*component1)(libkn_kref_com_jiang_nowinkotlin_mainpage_sectionItem_PieceData thiz);
                  libkn_KFloat (*component2)(libkn_kref_com_jiang_nowinkotlin_mainpage_sectionItem_PieceData thiz);
                  libkn_KULong (*component3)(libkn_kref_com_jiang_nowinkotlin_mainpage_sectionItem_PieceData thiz);
                  libkn_kref_com_jiang_nowinkotlin_mainpage_sectionItem_PieceData (*copy)(libkn_kref_com_jiang_nowinkotlin_mainpage_sectionItem_PieceData thiz, libkn_kref_com_jiang_nowinkotlin_mainpage_sectionItem_Game game, libkn_KFloat velocity, libkn_KULong color);
                  libkn_KBoolean (*equals)(libkn_kref_com_jiang_nowinkotlin_mainpage_sectionItem_PieceData thiz, libkn_kref_kotlin_Any other);
                  libkn_KInt (*hashCode)(libkn_kref_com_jiang_nowinkotlin_mainpage_sectionItem_PieceData thiz);
                  const char* (*toString)(libkn_kref_com_jiang_nowinkotlin_mainpage_sectionItem_PieceData thiz);
                  void (*update)(libkn_kref_com_jiang_nowinkotlin_mainpage_sectionItem_PieceData thiz, libkn_KLong dt);
                } PieceData;
                struct {
                  libkn_KType* (*_type)(void);
                  libkn_kref_com_jiang_nowinkotlin_mainpage_sectionItem_Game (*Game)();
                  libkn_KLong (*get_elapsed)(libkn_kref_com_jiang_nowinkotlin_mainpage_sectionItem_Game thiz);
                  void (*set_elapsed)(libkn_kref_com_jiang_nowinkotlin_mainpage_sectionItem_Game thiz, libkn_KLong set);
                  libkn_KBoolean (*get_finished)(libkn_kref_com_jiang_nowinkotlin_mainpage_sectionItem_Game thiz);
                  void (*set_finished)(libkn_kref_com_jiang_nowinkotlin_mainpage_sectionItem_Game thiz, libkn_KBoolean set);
                  libkn_KFloat (*get_height)(libkn_kref_com_jiang_nowinkotlin_mainpage_sectionItem_Game thiz);
                  void (*set_height)(libkn_kref_com_jiang_nowinkotlin_mainpage_sectionItem_Game thiz, libkn_KFloat set);
                  libkn_KFloat (*get_numBlocks)(libkn_kref_com_jiang_nowinkotlin_mainpage_sectionItem_Game thiz);
                  void (*set_numBlocks)(libkn_kref_com_jiang_nowinkotlin_mainpage_sectionItem_Game thiz, libkn_KFloat set);
                  libkn_KBoolean (*get_paused)(libkn_kref_com_jiang_nowinkotlin_mainpage_sectionItem_Game thiz);
                  void (*set_paused)(libkn_kref_com_jiang_nowinkotlin_mainpage_sectionItem_Game thiz, libkn_KBoolean set);
                  libkn_kref_androidx_compose_runtime_snapshots_SnapshotStateList (*get_pieces)(libkn_kref_com_jiang_nowinkotlin_mainpage_sectionItem_Game thiz);
                  libkn_KInt (*get_score)(libkn_kref_com_jiang_nowinkotlin_mainpage_sectionItem_Game thiz);
                  void (*set_score)(libkn_kref_com_jiang_nowinkotlin_mainpage_sectionItem_Game thiz, libkn_KInt set);
                  libkn_KBoolean (*get_started)(libkn_kref_com_jiang_nowinkotlin_mainpage_sectionItem_Game thiz);
                  void (*set_started)(libkn_kref_com_jiang_nowinkotlin_mainpage_sectionItem_Game thiz, libkn_KBoolean set);
                  libkn_KFloat (*get_width)(libkn_kref_com_jiang_nowinkotlin_mainpage_sectionItem_Game thiz);
                  void (*set_width)(libkn_kref_com_jiang_nowinkotlin_mainpage_sectionItem_Game thiz, libkn_KFloat set);
                  void (*clicked)(libkn_kref_com_jiang_nowinkotlin_mainpage_sectionItem_Game thiz, libkn_kref_com_jiang_nowinkotlin_mainpage_sectionItem_PieceData piece);
                  void (*start)(libkn_kref_com_jiang_nowinkotlin_mainpage_sectionItem_Game thiz);
                  void (*update)(libkn_kref_com_jiang_nowinkotlin_mainpage_sectionItem_Game thiz, libkn_KLong deltaTimeNanos);
                } Game;
                libkn_KInt (*com_jiang_nowinkotlin_mainpage_sectionItem_DropdownItem$stableprop_getter)();
                libkn_KInt (*com_jiang_nowinkotlin_mainpage_sectionItem_Game$stableprop_getter)();
                libkn_KInt (*com_jiang_nowinkotlin_mainpage_sectionItem_PieceData$stableprop_getter)();
                libkn_KInt (*com_jiang_nowinkotlin_mainpage_sectionItem_DropdownItem$stableprop_getter_)();
                libkn_KInt (*com_jiang_nowinkotlin_mainpage_sectionItem_Game$stableprop_getter_)();
                libkn_KInt (*com_jiang_nowinkotlin_mainpage_sectionItem_PieceData$stableprop_getter_)();
                libkn_KInt (*com_jiang_nowinkotlin_mainpage_sectionItem_DropdownItem$stableprop_getter__)();
                libkn_KInt (*com_jiang_nowinkotlin_mainpage_sectionItem_Game$stableprop_getter__)();
                libkn_KInt (*com_jiang_nowinkotlin_mainpage_sectionItem_PieceData$stableprop_getter__)();
                libkn_KInt (*com_jiang_nowinkotlin_mainpage_sectionItem_DropdownItem$stableprop_getter___)();
                libkn_KInt (*com_jiang_nowinkotlin_mainpage_sectionItem_Game$stableprop_getter___)();
                libkn_KInt (*com_jiang_nowinkotlin_mainpage_sectionItem_PieceData$stableprop_getter___)();
                libkn_KInt (*com_jiang_nowinkotlin_mainpage_sectionItem_DropdownItem$stableprop_getter____)();
                libkn_KInt (*com_jiang_nowinkotlin_mainpage_sectionItem_Game$stableprop_getter____)();
                libkn_KInt (*com_jiang_nowinkotlin_mainpage_sectionItem_PieceData$stableprop_getter____)();
                libkn_KInt (*com_jiang_nowinkotlin_mainpage_sectionItem_DropdownItem$stableprop_getter_____)();
                libkn_KInt (*com_jiang_nowinkotlin_mainpage_sectionItem_Game$stableprop_getter_____)();
                libkn_KInt (*com_jiang_nowinkotlin_mainpage_sectionItem_PieceData$stableprop_getter_____)();
                libkn_KInt (*com_jiang_nowinkotlin_mainpage_sectionItem_DropdownItem$stableprop_getter______)();
                libkn_KInt (*com_jiang_nowinkotlin_mainpage_sectionItem_Game$stableprop_getter______)();
                libkn_KInt (*com_jiang_nowinkotlin_mainpage_sectionItem_PieceData$stableprop_getter______)();
                libkn_KInt (*com_jiang_nowinkotlin_mainpage_sectionItem_DropdownItem$stableprop_getter_______)();
                libkn_KInt (*com_jiang_nowinkotlin_mainpage_sectionItem_Game$stableprop_getter_______)();
                libkn_KInt (*com_jiang_nowinkotlin_mainpage_sectionItem_PieceData$stableprop_getter_______)();
                libkn_KInt (*com_jiang_nowinkotlin_mainpage_sectionItem_DropdownItem$stableprop_getter________)();
                libkn_KInt (*com_jiang_nowinkotlin_mainpage_sectionItem_Game$stableprop_getter________)();
                libkn_KInt (*com_jiang_nowinkotlin_mainpage_sectionItem_PieceData$stableprop_getter________)();
                libkn_KInt (*com_jiang_nowinkotlin_mainpage_sectionItem_DropdownItem$stableprop_getter_________)();
                libkn_KInt (*com_jiang_nowinkotlin_mainpage_sectionItem_Game$stableprop_getter_________)();
                libkn_KInt (*com_jiang_nowinkotlin_mainpage_sectionItem_PieceData$stableprop_getter_________)();
                libkn_KInt (*com_jiang_nowinkotlin_mainpage_sectionItem_DropdownItem$stableprop_getter__________)();
                libkn_KInt (*com_jiang_nowinkotlin_mainpage_sectionItem_Game$stableprop_getter__________)();
                libkn_KInt (*com_jiang_nowinkotlin_mainpage_sectionItem_PieceData$stableprop_getter__________)();
                libkn_KInt (*com_jiang_nowinkotlin_mainpage_sectionItem_DropdownItem$stableprop_getter___________)();
                libkn_KInt (*com_jiang_nowinkotlin_mainpage_sectionItem_Game$stableprop_getter___________)();
                libkn_KInt (*com_jiang_nowinkotlin_mainpage_sectionItem_PieceData$stableprop_getter___________)();
                libkn_KInt (*com_jiang_nowinkotlin_mainpage_sectionItem_DropdownItem$stableprop_getter____________)();
                libkn_KInt (*com_jiang_nowinkotlin_mainpage_sectionItem_Game$stableprop_getter____________)();
                libkn_KInt (*com_jiang_nowinkotlin_mainpage_sectionItem_PieceData$stableprop_getter____________)();
                libkn_KInt (*com_jiang_nowinkotlin_mainpage_sectionItem_DropdownItem$stableprop_getter_____________)();
                libkn_KInt (*com_jiang_nowinkotlin_mainpage_sectionItem_Game$stableprop_getter_____________)();
                libkn_KInt (*com_jiang_nowinkotlin_mainpage_sectionItem_PieceData$stableprop_getter_____________)();
                libkn_KInt (*com_jiang_nowinkotlin_mainpage_sectionItem_DropdownItem$stableprop_getter______________)();
                libkn_KInt (*com_jiang_nowinkotlin_mainpage_sectionItem_Game$stableprop_getter______________)();
                libkn_KInt (*com_jiang_nowinkotlin_mainpage_sectionItem_PieceData$stableprop_getter______________)();
              } sectionItem;
              struct {
                libkn_KType* (*_type)(void);
                libkn_kref_com_jiang_nowinkotlin_mainpage_Episode (*Episode)(const char* id, const char* title, const char* episodeNumber, const char* date, const char* duration, const char* imageUrl, libkn_kref_kotlin_collections_List tags);
                const char* (*get_date)(libkn_kref_com_jiang_nowinkotlin_mainpage_Episode thiz);
                const char* (*get_duration)(libkn_kref_com_jiang_nowinkotlin_mainpage_Episode thiz);
                const char* (*get_episodeNumber)(libkn_kref_com_jiang_nowinkotlin_mainpage_Episode thiz);
                const char* (*get_id)(libkn_kref_com_jiang_nowinkotlin_mainpage_Episode thiz);
                const char* (*get_imageUrl)(libkn_kref_com_jiang_nowinkotlin_mainpage_Episode thiz);
                libkn_kref_kotlin_collections_List (*get_tags)(libkn_kref_com_jiang_nowinkotlin_mainpage_Episode thiz);
                const char* (*get_title)(libkn_kref_com_jiang_nowinkotlin_mainpage_Episode thiz);
                const char* (*component1)(libkn_kref_com_jiang_nowinkotlin_mainpage_Episode thiz);
                const char* (*component2)(libkn_kref_com_jiang_nowinkotlin_mainpage_Episode thiz);
                const char* (*component3)(libkn_kref_com_jiang_nowinkotlin_mainpage_Episode thiz);
                const char* (*component4)(libkn_kref_com_jiang_nowinkotlin_mainpage_Episode thiz);
                const char* (*component5)(libkn_kref_com_jiang_nowinkotlin_mainpage_Episode thiz);
                const char* (*component6)(libkn_kref_com_jiang_nowinkotlin_mainpage_Episode thiz);
                libkn_kref_kotlin_collections_List (*component7)(libkn_kref_com_jiang_nowinkotlin_mainpage_Episode thiz);
                libkn_kref_com_jiang_nowinkotlin_mainpage_Episode (*copy)(libkn_kref_com_jiang_nowinkotlin_mainpage_Episode thiz, const char* id, const char* title, const char* episodeNumber, const char* date, const char* duration, const char* imageUrl, libkn_kref_kotlin_collections_List tags);
                libkn_KBoolean (*equals)(libkn_kref_com_jiang_nowinkotlin_mainpage_Episode thiz, libkn_kref_kotlin_Any other);
                libkn_KInt (*hashCode)(libkn_kref_com_jiang_nowinkotlin_mainpage_Episode thiz);
                const char* (*toString)(libkn_kref_com_jiang_nowinkotlin_mainpage_Episode thiz);
              } Episode;
              struct {
                libkn_KType* (*_type)(void);
                libkn_kref_com_jiang_nowinkotlin_mainpage_MonthlyReport (*MonthlyReport)(libkn_KInt year, libkn_KInt month, const char* date, const char* title, const char* source, libkn_kref_kotlin_collections_List tags, libkn_KBoolean isLatest);
                const char* (*get_date)(libkn_kref_com_jiang_nowinkotlin_mainpage_MonthlyReport thiz);
                libkn_KBoolean (*get_isLatest)(libkn_kref_com_jiang_nowinkotlin_mainpage_MonthlyReport thiz);
                libkn_KInt (*get_month)(libkn_kref_com_jiang_nowinkotlin_mainpage_MonthlyReport thiz);
                const char* (*get_source)(libkn_kref_com_jiang_nowinkotlin_mainpage_MonthlyReport thiz);
                libkn_kref_kotlin_collections_List (*get_tags)(libkn_kref_com_jiang_nowinkotlin_mainpage_MonthlyReport thiz);
                const char* (*get_title)(libkn_kref_com_jiang_nowinkotlin_mainpage_MonthlyReport thiz);
                libkn_KInt (*get_year)(libkn_kref_com_jiang_nowinkotlin_mainpage_MonthlyReport thiz);
                libkn_KInt (*component1)(libkn_kref_com_jiang_nowinkotlin_mainpage_MonthlyReport thiz);
                libkn_KInt (*component2)(libkn_kref_com_jiang_nowinkotlin_mainpage_MonthlyReport thiz);
                const char* (*component3)(libkn_kref_com_jiang_nowinkotlin_mainpage_MonthlyReport thiz);
                const char* (*component4)(libkn_kref_com_jiang_nowinkotlin_mainpage_MonthlyReport thiz);
                const char* (*component5)(libkn_kref_com_jiang_nowinkotlin_mainpage_MonthlyReport thiz);
                libkn_kref_kotlin_collections_List (*component6)(libkn_kref_com_jiang_nowinkotlin_mainpage_MonthlyReport thiz);
                libkn_KBoolean (*component7)(libkn_kref_com_jiang_nowinkotlin_mainpage_MonthlyReport thiz);
                libkn_kref_com_jiang_nowinkotlin_mainpage_MonthlyReport (*copy)(libkn_kref_com_jiang_nowinkotlin_mainpage_MonthlyReport thiz, libkn_KInt year, libkn_KInt month, const char* date, const char* title, const char* source, libkn_kref_kotlin_collections_List tags, libkn_KBoolean isLatest);
                libkn_KBoolean (*equals)(libkn_kref_com_jiang_nowinkotlin_mainpage_MonthlyReport thiz, libkn_kref_kotlin_Any other);
                libkn_KInt (*hashCode)(libkn_kref_com_jiang_nowinkotlin_mainpage_MonthlyReport thiz);
                const char* (*toString)(libkn_kref_com_jiang_nowinkotlin_mainpage_MonthlyReport thiz);
              } MonthlyReport;
              libkn_KInt (*com_jiang_nowinkotlin_mainpage_Episode$stableprop_getter)();
              libkn_KInt (*com_jiang_nowinkotlin_mainpage_MonthlyReport$stableprop_getter)();
              libkn_kref_kotlin_collections_List (*get_sampleEpisodes)();
              libkn_KInt (*com_jiang_nowinkotlin_mainpage_Episode$stableprop_getter_)();
              libkn_KInt (*com_jiang_nowinkotlin_mainpage_MonthlyReport$stableprop_getter_)();
              libkn_KInt (*com_jiang_nowinkotlin_mainpage_Episode$stableprop_getter__)();
              libkn_KInt (*com_jiang_nowinkotlin_mainpage_MonthlyReport$stableprop_getter__)();
              libkn_KInt (*com_jiang_nowinkotlin_mainpage_Episode$stableprop_getter___)();
              libkn_KInt (*com_jiang_nowinkotlin_mainpage_MonthlyReport$stableprop_getter___)();
              libkn_kref_androidx_compose_ui_graphics_vector_ImageVector (*get_ChevronRight)(libkn_kref_androidx_compose_material_icons_Icons_Filled thiz);
              libkn_KInt (*com_jiang_nowinkotlin_mainpage_Episode$stableprop_getter____)();
              libkn_KInt (*com_jiang_nowinkotlin_mainpage_MonthlyReport$stableprop_getter____)();
              libkn_KInt (*com_jiang_nowinkotlin_mainpage_Episode$stableprop_getter_____)();
              libkn_KInt (*com_jiang_nowinkotlin_mainpage_MonthlyReport$stableprop_getter_____)();
            } mainpage;
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
            libkn_KInt (*com_jiang_nowinkotlin_Greeting$stableprop_getter)();
            libkn_KInt (*com_jiang_nowinkotlin_OHOSPlatform$stableprop_getter)();
            libkn_KInt (*com_jiang_nowinkotlin_Greeting$stableprop_getter_)();
            libkn_KInt (*com_jiang_nowinkotlin_OHOSPlatform$stableprop_getter_)();
            libkn_KInt (*com_jiang_nowinkotlin_Greeting$stableprop_getter__)();
            libkn_KInt (*com_jiang_nowinkotlin_OHOSPlatform$stableprop_getter__)();
            libkn_KInt (*com_jiang_nowinkotlin_Greeting$stableprop_getter___)();
            libkn_KInt (*com_jiang_nowinkotlin_OHOSPlatform$stableprop_getter___)();
            libkn_KInt (*com_jiang_nowinkotlin_Greeting$stableprop_getter____)();
            libkn_KInt (*com_jiang_nowinkotlin_OHOSPlatform$stableprop_getter____)();
            libkn_KInt (*com_jiang_nowinkotlin_Greeting$stableprop_getter_____)();
            libkn_KInt (*com_jiang_nowinkotlin_OHOSPlatform$stableprop_getter_____)();
            libkn_KInt (*com_jiang_nowinkotlin_Greeting$stableprop_getter______)();
            libkn_KInt (*com_jiang_nowinkotlin_OHOSPlatform$stableprop_getter______)();
            void* (*get_nativeResourceManager)();
            void (*set_nativeResourceManager)(void* set);
            libkn_KInt (*com_jiang_nowinkotlin_Greeting$stableprop_getter_______)();
            libkn_KInt (*com_jiang_nowinkotlin_OHOSPlatform$stableprop_getter_______)();
            libkn_KInt (*com_jiang_nowinkotlin_Greeting$stableprop_getter________)();
            libkn_KInt (*com_jiang_nowinkotlin_OHOSPlatform$stableprop_getter________)();
            libkn_KInt (*com_jiang_nowinkotlin_Greeting$stableprop_getter_________)();
            libkn_KInt (*com_jiang_nowinkotlin_OHOSPlatform$stableprop_getter_________)();
            libkn_KInt (*com_jiang_nowinkotlin_Greeting$stableprop_getter__________)();
            libkn_KInt (*com_jiang_nowinkotlin_OHOSPlatform$stableprop_getter__________)();
            libkn_KInt (*com_jiang_nowinkotlin_Greeting$stableprop_getter___________)();
            libkn_KInt (*com_jiang_nowinkotlin_OHOSPlatform$stableprop_getter___________)();
            void* (*MainArkUIViewController_)(void* env);
            libkn_KInt (*com_jiang_nowinkotlin_Greeting$stableprop_getter____________)();
            libkn_KInt (*com_jiang_nowinkotlin_OHOSPlatform$stableprop_getter____________)();
            void (*initResourceManager)(void* resourceManager);
            libkn_KInt (*com_jiang_nowinkotlin_Greeting$stableprop_getter_____________)();
            libkn_KInt (*com_jiang_nowinkotlin_OHOSPlatform$stableprop_getter_____________)();
          } nowinkotlin;
        } jiang;
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
