plugins {
    `android-library`
}

android {
    namespace = "com.benoitmanhes.cacheautresor.repository"
}

dependencies {

    implementation(project(":core"))
    implementation(project(":domain"))
    implementation(project(":logger:logger-timber"))
}
