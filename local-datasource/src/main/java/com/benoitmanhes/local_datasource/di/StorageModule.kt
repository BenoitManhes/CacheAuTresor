package com.benoitmanhes.local_datasource.di

import com.benoitmanhes.domain.interfaces.localdatasource.AuthLocalDataSource
import com.benoitmanhes.local_datasource.datasource.AuthLocalDataSourceImpl
import com.benoitmanhes.local_datasource.datastore.AuthDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object StorageModule {

    @Provides
    fun provideUserLocaleDataSource(
        dataStore: AuthDataStore
    ): AuthLocalDataSource = AuthLocalDataSourceImpl(dataStore)
}