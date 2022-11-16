package com.benoitmanhes.storage.di

import android.content.Context
import androidx.room.Room
import com.benoitmanhes.storage.dao.ExplorerDao
import com.benoitmanhes.storage.database.MainDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Singleton
    fun provideMainDatabase(
        @ApplicationContext appContext: Context,
    ): MainDatabase {
        return Room.databaseBuilder(
            appContext,
            MainDatabase::class.java,
            "bc943e798-a4f0-402e-9f5b-kjfqm86",
        ).build()
    }
}

@Module
@InstallIn(SingletonComponent::class)
internal object RoomDaoModule {

    @Provides
    fun provideExplorerDao(mainDatabase: MainDatabase): ExplorerDao {
        return mainDatabase.explorerDao()
    }
}
