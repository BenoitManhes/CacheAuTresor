plugins {
    `android-library`
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

dependencies {
    // DataStore
    implementation("androidx.datastore:datastore-preferences:_")

    // Hilt
    implementation(Google.dagger.hilt.android)
    kapt(Google.dagger.hilt.compiler)

    implementation(project(":domain"))
    implementation(project(":common"))
    implementation(project(":common-android"))
}