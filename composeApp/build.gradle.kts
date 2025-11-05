/*
 * Tencent is pleased to support the open source community by making ovCompose available.
 * Copyright (C) 2025 THL A29 Limited, a Tencent company. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.databinding.tool.ext.capitalizeUS
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.cocoapods)
    alias(libs.plugins.kotlin.serialization)
    id("com.tencent.kuiklybase.knoi.plugin")
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
        iosTarget.compilations.getByName("main") {
            val nskeyvalueobserving by cinterops.creating {
                defFile(file("src/iosMain/cinterop/nskeyvalueobserving.def"))
            }
        }
    }

    cocoapods {
        homepage = "something must not be null"
        summary = "something must not be null"
        version = "1.0"
        ios.deploymentTarget = "13.0"
        framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    ohosArm64 {
        binaries.sharedLib {
            baseName = "kn"
            export(libs.compose.multiplatform.export)
            linkerOpts(
                "-L${rootDir}/composeApp/libs/",
                "-lpbcurlwrapper",
                "-lc++_shared",
                "-lc++",
                "-lc++abi"
            )
            if(debuggable){
                freeCompilerArgs += "-Xadd-light-debug=enable"
                freeCompilerArgs += "-Xbinary=sourceInfoType=libbacktrace"
            }else{
                freeCompilerArgs += "-Xadd-light-debug=enable"
                freeCompilerArgs += "-Xbinary=sourceInfoType=noop"
            }
        }

        val main by compilations.getting

        val resource by main.cinterops.creating {
            defFile(file("src/ohosArm64Main/cinterop/resource.def"))
            includeDirs(file("src/ohosArm64Main/cinterop/include"))
        }
    }

    sourceSets {

        androidMain.dependencies {
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.androidx.media3.common)
            implementation(libs.androidx.media3.session)
            implementation(libs.androidx.media3.exoplayer)
            implementation(libs.androidx.media3.ui)
            implementation(libs.kotlinx.coroutines.guava)
            implementation(libs.kermit)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.atomicFu)
            implementation(libs.network)
            implementation(libs.kotlinx.serialization.json)
        }

        iosMain.dependencies {
        }

        val ohosArm64Main by getting {
            dependencies {
                api(libs.compose.multiplatform.export)
            }
        }
    }
}

android {
    namespace = "com.jiang.nowinkotlin"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.jiang.nowinkotlin"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 11
        versionName = "1.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    signingConfigs{
        create("release") {
            val keyStoreFile = file(System.getenv("SIGNING_KEY_STORE_PATH") ?: "${project.rootDir}/nowinkotlin.jks")
            if (keyStoreFile.exists()) {
                storeFile = keyStoreFile
                storePassword = System.getenv("SIGNING_KEY_STORE_PASSWORD")
                keyAlias = System.getenv("SIGNING_KEY_ALIAS")
                keyPassword = System.getenv("SIGNING_KEY_PASSWORD")
            }
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("release")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    debugImplementation(libs.compose.ui.tooling)
}

arrayOf("debug", "release").forEach { type ->
    tasks.register<Copy>("publish${type.capitalizeUS()}BinariesToHarmonyApp") {
        group = "harmony"
        dependsOn("link${type.capitalizeUS()}SharedOhosArm64")
        into(rootProject.file("harmonyApp"))
        from("build/bin/ohosArm64/${type}Shared/libkn_api.h") {
            into("entry/src/main/cpp/include/")
        }
        from(project.file("build/bin/ohosArm64/${type}Shared/libkn.so")) {
            into("/entry/libs/arm64-v8a/")
        }
    }
}
