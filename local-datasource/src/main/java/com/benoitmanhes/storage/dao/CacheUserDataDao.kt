package com.benoitmanhes.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.benoitmanhes.storage.model.RoomCacheUserData
import kotlinx.coroutines.flow.Flow

@Dao
interface CacheUserDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: RoomCacheUserData)

    @Query("DELETE FROM `cache-user-data` WHERE cacheId = :cacheId")
    fun delete(cacheId: String)

    @Query("SELECT * FROM `cache-user-data` WHERE cacheId = :cacheId")
    suspend fun findWithId(cacheId: String): RoomCacheUserData?

    @Query("SELECT * FROM `cache-user-data` WHERE cacheId = :cacheId")
    fun findWithIdFlow(cacheId: String): Flow<RoomCacheUserData>
}
