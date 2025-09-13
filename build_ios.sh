#!/bin/sh

set -e

echo "--- 切换到 iOS 目录 ---"
cd iosApp

echo "--- 正在为 arm64 模拟器构建 .app 文件 (Debug 模式)... "
xcodebuild build \
  -workspace "iosApp.xcworkspace" \
  -scheme "iosApp" \
  -sdk iphonesimulator \
  -arch "arm64" \
  -configuration "Debug" \
  CONFIGURATION_BUILD_DIR="../build/app-simulator"

echo "--- ✅ 模拟器专用的 .app 文件构建成功！ ---"
echo "你可以在项目根目录下的 'build/app-simulator' 文件夹中找到 iosApp.app 文件。"
