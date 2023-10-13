plugins {
    `android-library`
}

android {
    namespace = "com.benoitmanhes.cacheautresor.repository"
}

dependencies {

    implementation(project(":common_bm:kotlin"))
    implementation(project(":core"))
    implementation(project(":domain"))
    implementation(project(":logger:logger-timber"))
}
