plugins {
    `android-library`
    kotlin("kapt")
}

android {
    namespace = "com.benoitmanhes.cacheautresor.di"
}

dependencies {

    // Datastore
    implementation(AndroidX.dataStore.preferences)

    // Room
    implementation(AndroidX.room.ktx)

    implementation(project(":domain"))
    implementation(project(":local-datasource"))
    implementation(project(":remote-datasource"))
    implementation(project(":repository"))
}
