package com.benoitmanhes.domain.interfaces.localdatasource

import java.util.Date

interface SyncLocalDataSource {
    suspend fun getLastSyncCache(): Date?
    suspend fun getLastSyncUserPoints(): Date?
    suspend fun getLastSyncAllUsers(): Date?
    suspend fun setLastSyncCache(date: Date)
    suspend fun setLastSyncUserPoints(date: Date)
    suspend fun setLastSyncAllUsers(date: Date)
}
