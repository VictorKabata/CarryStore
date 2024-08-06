plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.googleServices.plugin)
    alias(libs.plugins.firebase.crashlytics.plugin)
    alias(libs.plugins.firebase.appDistribution.plugin)
    alias(libs.plugins.firebase.performance.plugin)
}

android {
    namespace = "com.vickbt.carrystore.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.vickbt.carrystore.android"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    implementation(projects.shared)

    implementation(libs.compose.activity)
    implementation(libs.appCompat)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.performance)
}
