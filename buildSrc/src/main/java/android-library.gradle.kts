plugins {
    id("com.android.library")
    id("kotlin-android")
    kotlin("kapt")
}

android {
    compileSdk = AndroidConfig.COMPILE_SDK
    buildToolsVersion = AndroidConfig.BUILD_TOOLS_VERSION

    defaultConfig {
        minSdk = AndroidConfig.MIN_SDK
        targetSdk = AndroidConfig.TARGET_SDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

kapt {
    correctErrorTypes = true
}