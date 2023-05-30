plugins {
    id("com.android.library")
    id("kotlin-android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = AndroidConfig.COMPILE_SDK
    buildToolsVersion = AndroidConfig.BUILD_TOOLS_VERSION

    defaultConfig {
        minSdk = AndroidConfig.MIN_SDK
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = ProjectConfig.JDK_VERSION
        targetCompatibility = ProjectConfig.JDK_VERSION
    }
    kotlinOptions {
        jvmTarget = ProjectConfig.JDK_TARGET
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {
    // Hilt
    implementation(Google.dagger.hilt.android)
    kapt(Google.dagger.hilt.compiler)

    // Kotlin
    implementation(Kotlin.stdlib.jdk8)
    implementation(KotlinX.coroutines.core)

    // Timber
    implementation(JakeWharton.timber)
}
