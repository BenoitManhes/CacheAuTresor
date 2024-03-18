package com.benoitmanhes.di.local

import com.benoitmanhes.domain.interfaces.localdatasource.AppControlLocalDataSource
import com.benoitmanhes.domain.interfaces.localdatasource.AuthLocalDataSource
import com.benoitmanhes.domain.interfaces.localdatasource.CacheLocalDataSource
import com.benoitmanhes.domain.interfaces.localdatasource.CacheUserDataLocalDataSource
import com.benoitmanhes.domain.interfaces.localdatasource.CacheUserProgressLocaleDataSource
import com.benoitmanhes.domain.interfaces.localdatasource.DraftCacheLocalDataSource
import com.benoitmanhes.domain.interfaces.localdatasource.DraftCacheStepLocalDataSource
import com.benoitmanhes.domain.interfaces.localdatasource.ExplorerLocalDataSource
import com.benoitmanhes.domain.interfaces.localdatasource.SyncLocalDataSource
import com.benoitmanhes.storage.datasource.AppControlLocalDataSourceImpl
import com.benoitmanhes.storage.datasource.AuthLocalDataSourceImpl
import com.benoitmanhes.storage.datasource.CacheLocalDataSourceImpl
import com.benoitmanhes.storage.datasource.CacheUserDataLocalDataSourceImpl
import com.benoitmanhes.storage.datasource.CacheUserProgressLocaleDataSourceImpl
import com.benoitmanhes.storage.datasource.DraftCacheLocalDataSourceImpl
import com.benoitmanhes.storage.datasource.DraftCacheStepLocalDataSourceImpl
import com.benoitmanhes.storage.datasource.ExplorerLocalDataSourceImpl
import com.benoitmanhes.storage.datasource.SyncLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface LocalDataSourceModule {

    @Binds
    @Singleton
    fun bindAuthLocalDataSource(authLocalDataSource: AuthLocalDataSourceImpl): AuthLocalDataSource

    @Binds
    @Singleton
    fun bindExplorerLocalDataSource(explorerLocalDataSource: ExplorerLocalDataSourceImpl): ExplorerLocalDataSource

    @Binds
    @Singleton
    fun bindCacheLocalDataSource(dataSourceImpl: CacheLocalDataSourceImpl): CacheLocalDataSource

    @Binds
    @Singleton
    fun bindCacheUserDataLocalDataSource(dataSourceImpl: CacheUserDataLocalDataSourceImpl): CacheUserDataLocalDataSource

    @Binds
    @Singleton
    fun bindCacheUserProgressLocaleDataSource(dataSourceImpl: CacheUserProgressLocaleDataSourceImpl): CacheUserProgressLocaleDataSource

    @Binds
    @Singleton
    fun bindSyncLocaleDataSource(dataSourceImpl: SyncLocalDataSourceImpl): SyncLocalDataSource

    @Binds
    @Singleton
    fun bindDraftCacheLocalDataSource(dataSourceImpl: DraftCacheLocalDataSourceImpl): DraftCacheLocalDataSource

    @Binds
    @Singleton
    fun bindDraftCacheStepLocaleDataSource(dataSourceImpl: DraftCacheStepLocalDataSourceImpl): DraftCacheStepLocalDataSource

    @Binds
    @Singleton
    fun bindAppControlDataSource(dataSourceImpl: AppControlLocalDataSourceImpl): AppControlLocalDataSource
}
