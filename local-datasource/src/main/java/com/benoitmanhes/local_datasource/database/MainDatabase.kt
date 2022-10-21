package com.benoitmanhes.local_datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.benoitmanhes.local_datasource.dao.ExplorerDao
import com.benoitmanhes.local_datasource.model.RoomExplorer

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
