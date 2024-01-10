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
    fun insert(entity: RoomCacheUserProgress)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entities: List<RoomCacheUserProgress>)

    @Query("DELETE FROM `cache-user-progress` WHERE cacheId = :cacheId AND explorerId = :explorerId")
    fun delete(explorerId: String, cacheId: String)

    @Query("DELETE FROM `cache-user-progress`")
    fun clear()

    @Query("SELECT * FROM `cache-user-progress` WHERE cacheId = :cacheId AND explorerId = :explorerId")
    fun findWithId(explorerId: String, cacheId: String): RoomCacheUserProgress?

    @Query("SELECT * FROM `cache-user-progress` WHERE cacheId = :cacheId AND explorerId = :explorerId")
    fun findWithIdFlow(explorerId: String, cacheId: String): Flow<RoomCacheUserProgress?>

    @Query("SELECT * FROM `cache-user-progress` WHERE explorerId = :explorerId")
    fun findAllForExplorer(explorerId: String): Flow<List<RoomCacheUserProgress>>
}
