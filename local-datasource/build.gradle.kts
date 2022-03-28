plugins {
    `android-library`
    kotlin("kapt")
}

dependencies {

    implementation(project(":domain"))
    implementation(project(":common"))
    implementation(project(":common-android"))
}