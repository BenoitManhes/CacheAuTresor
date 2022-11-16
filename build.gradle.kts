import io.gitlab.arturbosch.detekt.Detekt

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(Android.tools.build.gradlePlugin)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:_")
        classpath(Google.dagger.hilt.android.gradlePlugin)
        classpath(Google.playServicesGradlePlugin)
    }
}

plugins {
    id("io.gitlab.arturbosch.detekt").version("1.21.0")
}

dependencies {
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:_")
}

detekt {
    parallel = true
    source = files(rootProject.rootDir)
    buildUponDefaultConfig = true
    config = files("$projectDir/detekt-config.yml")
    autoCorrect = true
    ignoreFailures = true
}

tasks.withType<Detekt>().configureEach {
    outputs.upToDateWhen { false }

    exclude("**/build/**")

    reports {
        xml.required.set(true)
        xml.outputLocation.set(file("$buildDir/reports/detekt/detekt-report.xml"))

        html.required.set(true)
        html.outputLocation.set(file("$buildDir/reports/detekt/detekt-report.html"))
    }
}
