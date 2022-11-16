plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation(Android.tools.build.gradlePlugin)
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:_")
    implementation(Google.dagger.hilt.android.gradlePlugin)
}
