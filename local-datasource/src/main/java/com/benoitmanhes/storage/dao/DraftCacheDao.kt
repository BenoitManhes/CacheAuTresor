package com.benoitmanhes.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.benoitmanhes.storage.model.RoomDraftCache
import kotlinx.coroutines.flow.Flow

@Dao
interface DraftCacheDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: RoomDraftCache)

    @Query("DELETE FROM `DRAFT-CACHE` WHERE draftCacheId = :id")
    fun delete(id: String)

    @Query("DELETE FROM `DRAFT-CACHE`")
    fun deleteAll()

    @Query("SELECT * FROM `DRAFT-CACHE` WHERE draftCacheId = :id")
    fun findWithId(id: String): RoomDraftCache?

    @Query("SELECT * FROM `DRAFT-CACHE` WHERE draftCacheId = :id")
    fun findWithIdFlow(id: String): Flow<RoomDraftCache?>

    @Query("SELECT * FROM `DRAFT-CACHE`")
    fun findAllFlow(): Flow<List<RoomDraftCache>>
}
