package com.benoitmanhes.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.benoitmanhes.storage.model.RoomCache
import kotlinx.coroutines.flow.Flow

@Dao
interface CacheDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: RoomCache)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entities: List<RoomCache>)

    @Query("SELECT * FROM CACHES WHERE cacheId = :cacheId")
    suspend fun findWithId(cacheId: String): RoomCache?

    @Query("SELECT * FROM CACHES WHERE cacheId = :cacheId")
    fun findWithIdFlow(cacheId: String): Flow<RoomCache?>

    @Query("SELECT * FROM CACHES")
    fun findAllFlow(): Flow<List<RoomCache>>
}
