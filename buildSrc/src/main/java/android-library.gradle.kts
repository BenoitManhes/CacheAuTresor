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

dependencies {

    // Kotlin
    implementation(Kotlin.stdlib.jdk8)
    implementation(KotlinX.coroutines.core)

    // Timber
    implementation(JakeWharton.timber)
}
