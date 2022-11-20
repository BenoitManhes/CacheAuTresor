plugins {
    `android-library`
}

android {
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "_"
    }
}

dependencies {

    // Compose
    implementation(AndroidX.compose.ui)
    implementation(AndroidX.compose.ui.tooling)
    implementation(AndroidX.compose.ui.toolingPreview)
    implementation(AndroidX.compose.material)
    implementation(AndroidX.compose.material3)
    implementation(AndroidX.Activity.compose)
    implementation(AndroidX.compose.material.icons.extended)
    implementation(AndroidX.compose.foundation)

    // Material Design
    implementation(Google.android.material)

    // Testing
    testImplementation(Testing.junit4)
    androidTestImplementation(AndroidX.test.ext.junit)
    androidTestImplementation(AndroidX.test.espresso.core)
    androidTestImplementation(AndroidX.compose.ui.testJunit4)

    implementation(project(":domain"))
    implementation(project(":core"))
    implementation(project(":logger:logger-timber"))
}