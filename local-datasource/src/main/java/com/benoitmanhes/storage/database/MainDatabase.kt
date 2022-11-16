package com.benoitmanhes.storage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.benoitmanhes.storage.dao.ExplorerDao
import com.benoitmanhes.storage.model.RoomExplorer

@Database(
    version = 1,
    entities = [
        RoomExplorer::class
    ],
    exportSchema = true
)
abstract class MainDatabase : RoomDatabase() {
    internal abstract fun explorerDao(): ExplorerDao
}
