plugins {
    `android-library`
    kotlin("kapt")
}

android {
    namespace = "com.benoitmanhes.cacheautresor.storage"

    compileOptions {
        sourceCompatibility = ProjectConfig.JDK_VERSION
        targetCompatibility = ProjectConfig.JDK_VERSION
    }
    kotlinOptions {
        jvmTarget = ProjectConfig.JDK_TARGET
    }
}

dependencies {
    // DataStore
    implementation(AndroidX.dataStore.preferences)

    // GSON
    implementation("com.google.code.gson:gson:_")

    // Room
    implementation(AndroidX.room.runtime)
    implementation(AndroidX.room.ktx)
    kapt(AndroidX.room.compiler)

    implementation(project(":core"))
    implementation(project(":domain"))
    implementation(project(":logger:logger-timber"))
}
