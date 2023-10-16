plugins {
    id("java-library")
    id("kotlin")
    kotlin("plugin.serialization")
}

dependencies {
    implementation("javax.inject:javax.inject:_")

    implementation(Kotlin.stdlib.jdk8)
    implementation(KotlinX.coroutines.core)

    // Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:_")

    implementation("io.github.microutils:kotlin-logging-jvm:_")

    testImplementation("org.jetbrains.kotlin:kotlin-test:_")
    testImplementation(Testing.junit4)
    testImplementation(Testing.mockK)

    implementation(project(":common_bm:kotlin"))
    implementation(project(":core"))
    implementation(project(":logger"))
}
