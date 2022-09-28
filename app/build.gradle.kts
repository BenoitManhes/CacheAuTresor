plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {

    compileSdk = AndroidConfig.COMPILE_SDK
    buildToolsVersion = AndroidConfig.BUILD_TOOLS_VERSION

    defaultConfig {
        applicationId = "com.benoitmanhes.cacheautresor"
        minSdk = AndroidConfig.MIN_SDK
        targetSdk = AndroidConfig.TARGET_SDK

        versionCode = AndroidConfig.VERSION_CODE
        versionName = AndroidConfig.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "_"
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {

    // AndroidX
    implementation (AndroidX.core.ktx)
    implementation (AndroidX.lifecycle.runtimeKtx)

    // Compose
    implementation(AndroidX.compose.ui)
    implementation(AndroidX.compose.ui.tooling)
    implementation(AndroidX.compose.ui.toolingPreview)
    implementation(AndroidX.compose.material)
    implementation(AndroidX.compose.material3)
    implementation(AndroidX.Activity.compose)
    implementation(AndroidX.hilt.navigationCompose)
    implementation(AndroidX.compose.material.icons.extended)
    implementation(AndroidX.compose.foundation)
    implementation("com.google.accompanist:accompanist-navigation-animation:_")
    implementation(Google.accompanist.systemuicontroller)
    implementation (AndroidX.constraintLayout.compose)

    // Google map
    implementation (Google.android.maps.compose)
    implementation (Google.android.playServices.maps)

    // Hilt
    implementation(Google.dagger.hilt.android)
    kapt(Google.dagger.hilt.compiler)

    // Material Design
    implementation(Google.android.material)

    // Testing
    testImplementation (Testing.junit4)
    androidTestImplementation (AndroidX.test.ext.junit)
    androidTestImplementation (AndroidX.test.espresso.core)
    androidTestImplementation (AndroidX.compose.ui.testJunit4)

    implementation(project(":domain"))
    implementation(project(":common"))
    implementation(project(":common-android"))
    runtimeOnly(project(":local-datasource"))
    runtimeOnly(project(":remote-datasource"))
    runtimeOnly(project(":repository"))
}