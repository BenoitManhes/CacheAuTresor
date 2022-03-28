plugins {
    id("java-library")
    id("kotlin")
}

dependencies {
    implementation(Kotlin.stdlib.jdk8)
    implementation(KotlinX.coroutines.core)

    implementation("io.github.microutils:kotlin-logging-jvm:_")
}