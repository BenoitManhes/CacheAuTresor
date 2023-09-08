plugins {
    `android-library`
}

android {
    namespace = "com.benoitmanhes.common.compose"

    buildFeatures {
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
}

dependencies {

    // Coil
    implementation(COIL.compose)

    // Compose
    implementation(AndroidX.compose.ui)
    implementation(AndroidX.compose.ui.tooling)
    implementation(AndroidX.compose.ui.toolingPreview)
    implementation(AndroidX.Activity.compose)
    implementation(AndroidX.compose.material.icons.extended)
    implementation(AndroidX.compose.foundation)
    implementation(AndroidX.constraintLayout.compose)

    // Lottie
    implementation("com.airbnb.android:lottie-compose:_")

    // Material Design
    implementation(AndroidX.compose.material)
    implementation(AndroidX.compose.material3)

    // Testing
    testImplementation(Testing.junit4)
    androidTestImplementation(AndroidX.test.ext.junit)
    androidTestImplementation(AndroidX.test.espresso.core)
    androidTestImplementation(AndroidX.compose.ui.testJunit4)
}
