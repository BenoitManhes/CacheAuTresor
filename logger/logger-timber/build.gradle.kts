plugins {
    `android-library`
    kotlin("kapt")
}

android {
    resourcePrefix("logger")

    defaultConfig {
        buildConfigField("String", "SLF4j_VERSION", "\"${de.fayard.refreshVersions.core.versionFor("version.org.slf4j..slf4j-api")}\"")
    }
}

dependencies {
    // Timber
    api(JakeWharton.timber)

    api(project(":logger"))
}
