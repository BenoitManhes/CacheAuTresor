package com.benoitmanhes.di.remote

import com.benoitmanhes.domain.interfaces.remotedatasource.AuthRemoteDataSource
import com.benoitmanhes.domain.interfaces.remotedatasource.CacheRemoteDataSource
import com.benoitmanhes.domain.interfaces.remotedatasource.ExplorerRemoteDataSource
import com.benoitmanhes.server.firestore.AuthRemoteDataSourceImpl
import com.benoitmanhes.server.firestore.CacheRemoteDataSourceImpl
import com.benoitmanhes.server.firestore.ExplorerRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
interface RemoteDataSourceModule {

    @Binds
    @ActivityRetainedScoped
    fun bindAuthRemoteDataSource(authRemoteDataSourceImpl: AuthRemoteDataSourceImpl): AuthRemoteDataSource

    @Binds
    @ActivityRetainedScoped
    fun bindExploreRemoteDataSource(exploreRemoteDataSourceImpl: ExplorerRemoteDataSourceImpl): ExplorerRemoteDataSource

    @Binds
    @ActivityRetainedScoped
    fun bindCacheRemoteDataSource(cacheRemoteDataSourceImpl: CacheRemoteDataSourceImpl): CacheRemoteDataSource
}
