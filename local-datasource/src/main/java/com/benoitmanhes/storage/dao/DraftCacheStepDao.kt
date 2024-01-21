package com.benoitmanhes.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.benoitmanhes.storage.model.RoomDraftCacheStep
import kotlinx.coroutines.flow.Flow

@Dao
interface DraftCacheStepDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: RoomDraftCacheStep)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entities: List<RoomDraftCacheStep>)

    @Query("DELETE FROM `draft-cache-step` WHERE stepDraftId = :id")
    fun delete(id: String)

    @Query("DELETE FROM `draft-cache-step` WHERE stepDraftId IN (:ids)")
    fun delete(ids: List<String>)

    @Query("DELETE FROM `draft-cache-step`")
    fun deleteAll()

    @Query("SELECT * FROM `draft-cache-step` WHERE stepDraftId = :id")
    fun findWithId(id: String): RoomDraftCacheStep?

    @Query("SELECT * FROM `draft-cache-step` WHERE stepDraftId = :id")
    fun findWithIdFlow(id: String): Flow<RoomDraftCacheStep?>

    @Query("SELECT * FROM `draft-cache-step` WHERE stepDraftId IN (:ids)")
    fun findWithIdsFlow(ids: List<String>): Flow<List<RoomDraftCacheStep>>
}
