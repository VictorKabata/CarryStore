import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.nativeCocoapod)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlinX.serialization)
    // alias(libs.plugins.sqlDelight)
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    applyDefaultHierarchyTemplate()

    androidTarget{
        compilations.all {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_1_8.toString()
            }
        }
    }

    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
        when {
            System.getenv("SDK_NAME")?.startsWith("iphoneos") == true -> ::iosArm64
            System.getenv("NATIVE_ARCH")?.startsWith("arm") == true -> ::iosSimulatorArm64
            else -> ::iosX64
        }
    iosTarget("ios") {}

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "16.0"
        podfile = project.file("../app-ios/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        sourceSets["commonMain"].dependencies {
            // Jetpack Compose - UI
            api(compose.runtime)
            api(compose.foundation)
            api(compose.material3)
            api(compose.components.resources)

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
        }

        sourceSets["commonTest"].dependencies {
            implementation(libs.kotlin.test)
        }

        sourceSets["androidMain"].dependencies{
            implementation(libs.ktor.android)
            implementation(libs.sqlDelight.android)
        }

        sourceSets["iosMain"].dependencies{
            implementation(libs.ktor.darwin)
            implementation(libs.sqlDelight.native)
        }
    }
}

android {
    namespace = "com.vickbt.carrystore"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

/*sqldelight {
    databases {
        create("AppDatabase") {
            packageName.set("com.vickbt.shared.data.cache.sqldelight")
            srcDirs.setFrom("src/commonMain/kotlin")
        }
    }
}*/
