plugins {
    `android-library`
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

dependencies {
    // DataStore
    implementation("androidx.datastore:datastore-preferences:_")

    // Room
    implementation("androidx.room:room-runtime:_")
    implementation("androidx.room:room-ktx:_")
    kapt("androidx.room:room-compiler:_")

    // Hilt
    implementation(Google.dagger.hilt.android)
    kapt(Google.dagger.hilt.compiler)

    implementation(project(":core"))
    implementation(project(":domain"))
}