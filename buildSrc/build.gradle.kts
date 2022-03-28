plugins{
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    google()
    mavenCentral()
}
dependencies {
    implementation(Android.tools.build.gradlePlugin)
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:_")
}