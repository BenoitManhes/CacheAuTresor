plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
}

android {

    namespace = "com.benoitmanhes.cacheautresor"

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
        debug {
            applicationIdSuffix = ".debug"
            isDebuggable = true
        }
    }

    buildFeatures {
        buildConfig = true
        compose = true
    }

    compileOptions {
        sourceCompatibility = ProjectConfig.JDK_VERSION
        targetCompatibility = ProjectConfig.JDK_VERSION
    }
    kotlinOptions {
        jvmTarget = ProjectConfig.JDK_TARGET
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
    implementation(AndroidX.core.ktx)
    implementation(AndroidX.lifecycle.process)
    implementation(AndroidX.lifecycle.runtimeKtx)
    implementation("androidx.lifecycle:lifecycle-runtime-compose:_")

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
    implementation(AndroidX.constraintLayout.compose)

    // Firebase
    implementation(platform(Google.firebase.bom))
    implementation(Google.firebase.authenticationKtx)
    implementation(Google.firebase.cloudFirestoreKtx)

    // Map
    implementation("org.osmdroid:osmdroid-android:_")

    // Hilt
    implementation(Google.dagger.hilt.android)
    kapt(Google.dagger.hilt.compiler)

    // Material Design
    implementation(Google.android.material)

    // Testing
    testImplementation(Testing.junit4)
    androidTestImplementation(AndroidX.test.ext.junit)
    androidTestImplementation(AndroidX.test.espresso.core)
    androidTestImplementation(AndroidX.compose.ui.testJunit4)

    implementation(project(":common_bm:compose"))
    implementation(project(":common_bm:kotlin"))
    implementation(project(":core"))
    implementation(project(":dependencies-injection"))
    implementation(project(":design-system"))
    implementation(project(":domain"))
    implementation(project(":logger:logger-timber"))
}
