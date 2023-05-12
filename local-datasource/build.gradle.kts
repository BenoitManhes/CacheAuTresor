plugins {
    `android-library`
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

dependencies {
    // DataStore
    implementation(AndroidX.dataStore.preferences)

    // Room
    implementation(AndroidX.room.runtime)
    implementation(AndroidX.room.ktx)
    kapt(AndroidX.room.compiler)

    // Hilt
    implementation(Google.dagger.hilt.android)
    kapt(Google.dagger.hilt.compiler)

    implementation(project(":core"))
    implementation(project(":domain"))
    implementation(project(":logger:logger-timber"))
}
