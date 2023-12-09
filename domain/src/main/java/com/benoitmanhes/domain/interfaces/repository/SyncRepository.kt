package com.benoitmanhes.domain.interfaces.repository

interface SyncRepository {
    suspend fun needToSyncCache(): Boolean
    suspend fun needToSyncUserPoints(): Boolean
    suspend fun needToSyncAllUsers(): Boolean
    suspend fun resetLastSyncCache()
    suspend fun resetLastSyncUserPoints()
    suspend fun resetLastSyncAllUsers()
    suspend fun clearLastSyncUserPoints()
}
