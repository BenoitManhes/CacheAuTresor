package com.benoitmanhes.storage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.benoitmanhes.storage.dao.CacheDao
import com.benoitmanhes.storage.dao.CacheUserDataDao
import com.benoitmanhes.storage.dao.CacheUserProgressDao
import com.benoitmanhes.storage.dao.DraftCacheDao
import com.benoitmanhes.storage.dao.DraftCacheStepDao
import com.benoitmanhes.storage.dao.ExplorerDao
import com.benoitmanhes.storage.model.RoomCache
import com.benoitmanhes.storage.model.RoomCacheUserData
import com.benoitmanhes.storage.model.RoomCacheUserProgress
import com.benoitmanhes.storage.model.RoomDraftCache
import com.benoitmanhes.storage.model.RoomDraftCacheStep
import com.benoitmanhes.storage.model.RoomExplorer
import com.benoitmanhes.storage.utils.RoomConverters

@TypeConverters(RoomConverters::class)
@Database(
    version = 1,
    entities = [
        RoomExplorer::class,
        RoomCache::class,
        RoomCacheUserData::class,
        RoomCacheUserProgress::class,
        RoomDraftCache::class,
        RoomDraftCacheStep::class,
    ],
    exportSchema = true
)
abstract class MainDatabase : RoomDatabase() {
    abstract fun explorerDao(): ExplorerDao
    abstract fun cacheDao(): CacheDao
    abstract fun cacheUserDataDao(): CacheUserDataDao
    abstract fun cacheUserProgressDao(): CacheUserProgressDao
    abstract fun draftCacheDao(): DraftCacheDao
    abstract fun draftCacheStepDao(): DraftCacheStepDao
}
