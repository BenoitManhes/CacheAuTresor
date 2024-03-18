package com.benoitmanhes.cacheautresor

import android.app.Application
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ProcessLifecycleOwner
import com.benoitmanhes.domain.usecase.appcontrol.FetchAppControlUseCase
import com.benoitmanhes.logger.CTLogger
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class TresorApp : Application() {

    @Inject
    lateinit var fetchAppControlUseCase: FetchAppControlUseCase

    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    override fun onCreate() {
        super.onCreate()
        initLogs()
        FirebaseApp.initializeApp(this)
        coroutineScope.launch {
            ProcessLifecycleOwner.get().lifecycle.currentStateFlow.collect { state ->
                when (state) {
                    Lifecycle.State.RESUMED -> fetchAppControlUseCase()
                    else -> {} // nothing
                }
            }
        }
    }

    private fun initLogs() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}

private val logger = CTLogger.get<TresorApp>()
