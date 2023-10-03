package com.benoitmanhes.storage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.benoitmanhes.storage.dao.CacheUserDataDao
import com.benoitmanhes.storage.dao.ExplorerDao
import com.benoitmanhes.storage.model.RoomCacheUserData
import com.benoitmanhes.storage.model.RoomExplorer
import com.benoitmanhes.storage.utils.RoomConverters

@TypeConverters(RoomConverters::class)
@Database(
    version = 1,
    entities = [
        RoomExplorer::class
        RoomExplorer::class,
        RoomCacheUserData::class,
    ],
    exportSchema = true
)
abstract class MainDatabase : RoomDatabase() {
    abstract fun explorerDao(): ExplorerDao
    abstract fun cacheUserDataDao(): CacheUserDataDao
}
