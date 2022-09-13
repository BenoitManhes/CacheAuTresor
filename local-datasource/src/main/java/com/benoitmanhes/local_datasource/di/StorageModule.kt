package com.benoitmanhes.local_datasource.di

import com.benoitmanhes.domain.interfaces.datasource.UserLocalDataSource
import com.benoitmanhes.local_datasource.datasource.UserLocalDataSourceImpl
import com.benoitmanhes.local_datasource.datastore.UserDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object StorageModule {

    @Provides
    fun provideUserLocaleDataSource(
        dataStore: UserDataStore
    ): UserLocalDataSource = UserLocalDataSourceImpl(dataStore)
}