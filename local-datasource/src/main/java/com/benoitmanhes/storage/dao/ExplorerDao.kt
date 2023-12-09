package com.benoitmanhes.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.benoitmanhes.storage.model.RoomExplorer
import kotlinx.coroutines.flow.Flow

@Dao
interface ExplorerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: RoomExplorer)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entities: List<RoomExplorer>)

    @Query("DELETE FROM EXPLORERS WHERE id = :id")
    fun delete(id: String)

    @Query("SELECT * FROM EXPLORERS WHERE id = :id")
    suspend fun findWithId(id: String): RoomExplorer?

    @Query("SELECT * FROM EXPLORERS")
    fun findAllFlow(): Flow<List<RoomExplorer>>

    @Query("SELECT * FROM EXPLORERS WHERE id = :id")
    fun loadByName(id: String): Flow<RoomExplorer>
}
