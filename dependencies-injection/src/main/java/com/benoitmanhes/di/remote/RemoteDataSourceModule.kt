package com.benoitmanhes.di.remote

import com.benoitmanhes.domain.interfaces.remotedatasource.AuthRemoteDataSource
import com.benoitmanhes.domain.interfaces.remotedatasource.CacheClassicalRemoteDataSource
import com.benoitmanhes.domain.interfaces.remotedatasource.CacheCoopRemoteDataSource
import com.benoitmanhes.domain.interfaces.remotedatasource.CacheMysteryRemoteDataSource
import com.benoitmanhes.domain.interfaces.remotedatasource.CachePisteRemoteDataSource
import com.benoitmanhes.domain.interfaces.remotedatasource.ExplorerRemoteDataSource
import com.benoitmanhes.server.firestore.AuthRemoteDataSourceImpl
import com.benoitmanhes.server.firestore.CacheClassicalRemoteDataSourceImpl
import com.benoitmanhes.server.firestore.CacheMysteryRemoteDataSourceImpl
import com.benoitmanhes.server.firestore.CachePisteRemoteDataSourceImpl
import com.benoitmanhes.server.firestore.ExplorerRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import com.benoitmanhes.server.firestore.CacheCoopRemoteDataSourceImpl as CacheCoopRemoteDataSourceImpl1

@Module
@InstallIn(ActivityRetainedComponent::class)
interface RemoteDataSourceModule {

    @Binds
    @ActivityRetainedScoped
    fun bindAuthRemoteDataSource(authRemoteDataSourceImpl: AuthRemoteDataSourceImpl): AuthRemoteDataSource

    @Binds
    @ActivityRetainedScoped
    fun bindExploreRemoteDataSource(exploreRemoteDataSourceImpl: ExplorerRemoteDataSourceImpl): ExplorerRemoteDataSource
}