plugins {
    id("java-library")
    id("kotlin")
}

dependencies {
    implementation("javax.inject:javax.inject:_")

    implementation(Kotlin.stdlib.jdk8)
    implementation(KotlinX.coroutines.core)

    implementation("io.github.microutils:kotlin-logging-jvm:_")

    implementation(project(":common"))
}