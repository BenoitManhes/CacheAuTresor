package com.benoitmanhes.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.benoitmanhes.storage.model.RoomCacheUserProgress
import kotlinx.coroutines.flow.Flow

@Dao
interface CacheUserProgressDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: RoomCacheUserProgress)

    @Query("DELETE FROM `cache-user-progress` WHERE cacheId = :cacheId AND explorerId = :explorerId")
    fun delete(explorerId: String, cacheId: String)

    @Query("SELECT * FROM `cache-user-progress` WHERE cacheId = :cacheId AND explorerId = :explorerId")
    suspend fun findWithId(explorerId: String, cacheId: String): RoomCacheUserProgress?

    @Query("SELECT * FROM `cache-user-progress` WHERE cacheId = :cacheId AND explorerId = :explorerId")
    fun findWithIdFlow(explorerId: String, cacheId: String): Flow<RoomCacheUserProgress?>
}
