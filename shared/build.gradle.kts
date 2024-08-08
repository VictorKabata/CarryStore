@file:OptIn(ExperimentalComposeLibrary::class)

import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.nativeCocoapod)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlinX.serialization)
    alias(libs.plugins.sqlDelight)
}

@OptIn(ExperimentalKotlinGradlePluginApi::class)
kotlin {
    applyDefaultHierarchyTemplate()

    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    )

    cocoapods {
        summary = "E-commerce application"
        homepage = "https://github.com/VictorKabata/CarryStore"
        version =  System.getenv("VERSION_NAME")?.toString() ?: "0.0.1"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../app-ios/Podfile")
        framework {
            baseName = "shared"
            isStatic = false
        }
    }

    sourceSets {
        commonMain.dependencies {
            // Jetpack Compose - UI
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.components.resources)
            implementation(compose.materialIconsExtended)

            implementation(libs.coroutines)

            // Dependency injection
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.composeViewModel)

            // Logging
            api(libs.napier)

            // Networking
            implementation(libs.bundles.ktor)
            implementation(libs.kotlinX.serializationJson)

            implementation(libs.sqlDelight.coroutine)

            implementation(libs.navigation)

            implementation(libs.bundles.coil)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(kotlin("test-annotations-common"))
            implementation(libs.coroutines.test)
            implementation(compose.uiTest)
            implementation(libs.ktor.mock)
            implementation(libs.assertK)
            implementation(libs.sqlDelight.runtime)
            implementation(libs.turbine)
            implementation(compose.uiTest)
        }

        androidMain.dependencies {
            implementation(libs.ktor.android)
            implementation(libs.sqlDelight.android)
        }

        sourceSets["androidUnitTest"].dependencies {
            implementation(libs.kotlin.test)
            implementation(kotlin("test-annotations-common"))

            implementation(compose.uiTest)

            implementation(libs.sqlDelight.sqliteDriver)
            implementation(libs.androidx.ui.test.junit4)
            implementation(libs.androidx.ui.test.manifest)
            implementation(libs.robolectric)
        }

        sourceSets["androidInstrumentedTest"].dependencies {
            implementation(libs.sqlDelight.sqliteDriver)
        }

        iosMain.dependencies {
            implementation(libs.ktor.darwin)
            implementation(libs.sqlDelight.native)
            implementation(libs.kotlin.test)
            implementation(kotlin("test-annotations-common"))
            implementation(libs.coroutines.test)
            implementation(compose.uiTest)
        }
    }
}

android {
    namespace = "com.vickbt.carrystore"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    testOptions.unitTests.isIncludeAndroidResources = true
}

sqldelight {
    databases {
        create("AppDatabase") {
            packageName.set("com.vickbt.shared.data.cache.sqldelight")
            srcDirs.setFrom("src/commonMain/kotlin")
        }
    }
}
