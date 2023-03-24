package com.benoitmanhes.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.benoitmanhes.storage.model.RoomCacheCoop
import kotlinx.coroutines.flow.Flow

@Dao
interface CacheCoopDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClassical(entity: RoomCacheCoop)

    @Query("DELETE FROM caches_coop WHERE id = :id")
    fun delete(id: String)

    @Query("SELECT * FROM caches_coop WHERE id = :id")
    suspend fun findWithId(id: String): RoomCacheCoop?

    @Query("SELECT * FROM caches_coop WHERE id = :id")
    fun findWithIdFlow(id: String): Flow<RoomCacheCoop>
}