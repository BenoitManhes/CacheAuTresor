package com.benoitmanhes.di.local

import com.benoitmanhes.domain.interfaces.localdatasource.AuthLocalDataSource
import com.benoitmanhes.domain.interfaces.localdatasource.ExplorerLocalDataSource
import com.benoitmanhes.storage.datasource.AuthLocalDataSourceImpl
import com.benoitmanhes.storage.datasource.ExplorerLocalDataSourceImpl
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
}
