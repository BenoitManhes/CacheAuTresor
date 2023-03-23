plugins {
    `android-library`
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

dependencies {

    // Datastore
    implementation(AndroidX.dataStore.preferences)

    // Hilt
    implementation(Google.dagger.hilt.android)
    kapt(Google.dagger.hilt.compiler)

    // Room
    implementation(AndroidX.room.ktx)

    implementation(project(":domain"))
    implementation(project(":local-datasource"))
    implementation(project(":remote-datasource"))
    implementation(project(":repository"))
}