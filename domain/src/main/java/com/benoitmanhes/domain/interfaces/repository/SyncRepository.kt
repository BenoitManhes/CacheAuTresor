package com.benoitmanhes.domain.interfaces.repository

interface SyncRepository {
    suspend fun needToSyncCache(): Boolean
    suspend fun needToSyncUserPoints(): Boolean
    suspend fun resetLastSyncCache()
    suspend fun resetLastSyncUserPoints()
}
