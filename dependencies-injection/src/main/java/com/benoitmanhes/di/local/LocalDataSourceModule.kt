package com.benoitmanhes.di.local

import com.benoitmanhes.domain.interfaces.localdatasource.AuthLocalDataSource
import com.benoitmanhes.domain.interfaces.localdatasource.CacheLocalDataSource
import com.benoitmanhes.domain.interfaces.localdatasource.CacheUserDataLocalDataSource
import com.benoitmanhes.domain.interfaces.localdatasource.CacheUserProgressLocaleDataSource
import com.benoitmanhes.domain.interfaces.localdatasource.ExplorerLocalDataSource
import com.benoitmanhes.domain.interfaces.localdatasource.SyncLocalDataSource
import com.benoitmanhes.storage.datasource.AuthLocalDataSourceImpl
import com.benoitmanhes.storage.datasource.CacheLocalDataSourceImpl
import com.benoitmanhes.storage.datasource.CacheUserDataLocalDataSourceImpl
import com.benoitmanhes.storage.datasource.CacheUserProgressLocaleDataSourceImpl
import com.benoitmanhes.storage.datasource.ExplorerLocalDataSourceImpl
import com.benoitmanhes.storage.datasource.SyncLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
interface LocalDataSourceModule {

    @Binds
    @ActivityRetainedScoped
    fun bindAuthLocalDataSource(authLocalDataSource: AuthLocalDataSourceImpl): AuthLocalDataSource

    @Binds
    @ActivityRetainedScoped
    fun bindExplorerLocalDataSource(explorerLocalDataSource: ExplorerLocalDataSourceImpl): ExplorerLocalDataSource

    @Binds
    @ActivityRetainedScoped
    fun bindCacheLocalDataSource(dataSourceImpl: CacheLocalDataSourceImpl): CacheLocalDataSource

    @Binds
    @ActivityRetainedScoped
    fun bindCacheUserDataLocalDataSource(dataSourceImpl: CacheUserDataLocalDataSourceImpl): CacheUserDataLocalDataSource

    @Binds
    @ActivityRetainedScoped
    fun bindCacheUserProgressLocaleDataSource(dataSourceImpl: CacheUserProgressLocaleDataSourceImpl): CacheUserProgressLocaleDataSource

    @Binds
    @ActivityRetainedScoped
    fun bindSyncLocaleDataSource(dataSourceImpl: SyncLocalDataSourceImpl): SyncLocalDataSource
}
