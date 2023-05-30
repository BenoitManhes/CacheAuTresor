plugins {
    `android-library`
}

android {
    namespace = "com.benoitmanhes.cacheautresor.server"
}

dependencies {

    // Firebase
    implementation(platform(Google.firebase.bom))
    implementation(Google.firebase.authenticationKtx)
    implementation(Google.firebase.cloudFirestoreKtx)

    implementation(project(":core"))
    implementation(project(":domain"))
    implementation(project(":logger:logger-timber"))
}
