package com.benoitmanhes.di.local

import android.content.Context
import androidx.room.Room
import com.benoitmanhes.storage.dao.ExplorerDao
import com.benoitmanhes.storage.database.MainDatabase
import com.benoitmanhes.storage.utils.RoomConverters
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object RoomModule {

    @Provides
    @ActivityRetainedScoped
    fun provideMainDatabase(
        @ApplicationContext appContext: Context,
    ): MainDatabase = Room
        .databaseBuilder(
            appContext,
            MainDatabase::class.java,
            "bc943e798-a4f0-402e-9f5b-kjfqm86",
        )
        .addTypeConverter(RoomConverters)
        .build()
}

@Module
@InstallIn(ActivityRetainedComponent::class)
internal object RoomDaoModule {

    @Provides
    fun provideExplorerDao(mainDatabase: MainDatabase): ExplorerDao {
        return mainDatabase.explorerDao()
    }
}
