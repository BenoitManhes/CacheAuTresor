package com.benoitmanhes.di.remote

import com.benoitmanhes.domain.interfaces.remotedatasource.AppControlRemoteDataSource
import com.benoitmanhes.domain.interfaces.remotedatasource.AuthRemoteDataSource
import com.benoitmanhes.domain.interfaces.remotedatasource.CacheRemoteDataSource
import com.benoitmanhes.domain.interfaces.remotedatasource.CacheUserProgressRemoteDataSource
import com.benoitmanhes.domain.interfaces.remotedatasource.ExplorerRemoteDataSource
import com.benoitmanhes.domain.interfaces.remotedatasource.StepRemoteDataSource
import com.benoitmanhes.server.extensions.StepRemoteDataSourceImpl
import com.benoitmanhes.server.firestore.AppControlRemoteDataSourceImpl
import com.benoitmanhes.server.firestore.AuthRemoteDataSourceImpl
import com.benoitmanhes.server.firestore.CacheRemoteDataSourceImpl
import com.benoitmanhes.server.firestore.CacheUserProgressRemoteDataSourceImpl
import com.benoitmanhes.server.firestore.ExplorerRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RemoteDataSourceModule {

    @Binds
    @Singleton
    fun bindAuthRemoteDataSource(authRemoteDataSourceImpl: AuthRemoteDataSourceImpl): AuthRemoteDataSource

    @Binds
    @Singleton
    fun bindExploreRemoteDataSource(exploreRemoteDataSourceImpl: ExplorerRemoteDataSourceImpl): ExplorerRemoteDataSource

    @Binds
    @Singleton
    fun bindCacheRemoteDataSource(cacheRemoteDataSourceImpl: CacheRemoteDataSourceImpl): CacheRemoteDataSource

    @Binds
    @Singleton
    fun bindInstructionRemoteDataSource(dataSourceImpl: StepRemoteDataSourceImpl): StepRemoteDataSource

    @Binds
    @Singleton
    fun bindCacheUserProgressRemoteDataSource(dataSourceImpl: CacheUserProgressRemoteDataSourceImpl): CacheUserProgressRemoteDataSource

    @Binds
    @Singleton
    fun bindAppControlRemoteDataSource(dataSourceImpl: AppControlRemoteDataSourceImpl): AppControlRemoteDataSource
}
