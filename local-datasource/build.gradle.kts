plugins {
    `android-library`
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

dependencies {
    // Hilt
    implementation(Google.dagger.hilt.android)
    kapt(Google.dagger.hilt.compiler)

    implementation(project(":domain"))
    implementation(project(":common"))
    implementation(project(":common-android"))
}