package com.benoitmanhes.storage.di

import com.benoitmanhes.domain.interfaces.localdatasource.AuthLocalDataSource
import com.benoitmanhes.domain.interfaces.localdatasource.ExplorerLocalDataSource
import com.benoitmanhes.storage.dao.ExplorerDao
import com.benoitmanhes.storage.datasource.AuthLocalDataSourceImpl
import com.benoitmanhes.storage.datasource.ExplorerLocalDataSourceImpl
import com.benoitmanhes.storage.datastore.AuthDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object LocalDataSourceModule {

    @Provides
    fun provideUserLocaleDataSource(
        dataStore: AuthDataStore
    ): AuthLocalDataSource = AuthLocalDataSourceImpl(dataStore)

    @Provides
    fun provideExplorerLocaleDataSource(
        explorerDao: ExplorerDao
    ): ExplorerLocalDataSource = ExplorerLocalDataSourceImpl(explorerDao)
}
