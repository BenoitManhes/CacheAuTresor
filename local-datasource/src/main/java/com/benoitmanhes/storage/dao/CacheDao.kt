package com.benoitmanhes.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.benoitmanhes.storage.model.RoomCacheClassical
import kotlinx.coroutines.flow.Flow

@Dao
interface CacheDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClassical(entity: RoomCacheClassical)

    @Query("DELETE FROM CLASSICAL_CACHES WHERE id = :id")
    fun delete(id: String)

    @Query("SELECT * FROM CLASSICAL_CACHES WHERE id = :id")
    suspend fun findWithId(id: String): RoomCacheClassical?

    @Query("SELECT * FROM CLASSICAL_CACHES WHERE id = :id")
    fun findWithIdFlow(id: String): Flow<RoomCacheClassical>
}