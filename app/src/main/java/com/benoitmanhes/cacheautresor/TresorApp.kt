package com.benoitmanhes.cacheautresor

import android.app.Application
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class TresorApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initLogs()
        FirebaseApp.initializeApp(this)
    }

    private fun initLogs() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
