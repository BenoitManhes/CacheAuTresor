package com.benoitmanhes.repository

import kotlin.time.Duration
import kotlin.time.Duration.Companion.hours

internal object RepositoryConstants {
    object Sync {
        val cacheSyncDelay: Duration = 1.hours
        val userPointsSyncDelay: Duration = 1.hours
        val allUsersSyncDelay: Duration = 1.hours
    }
}
