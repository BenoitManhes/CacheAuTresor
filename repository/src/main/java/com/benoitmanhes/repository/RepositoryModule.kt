package com.benoitmanhes.repository

import com.benoitmanhes.domain.interfaces.localdatasource.AuthLocalDataSource
import com.benoitmanhes.domain.interfaces.localdatasource.ExplorerLocalDataSource
import com.benoitmanhes.domain.interfaces.remotedatasource.AuthRemoteDataSource
import com.benoitmanhes.domain.interfaces.remotedatasource.ExplorerRemoteDataSource
import com.benoitmanhes.domain.interfaces.repository.AuthRepository
import com.benoitmanhes.domain.interfaces.repository.ExplorerRepository
import com.benoitmanhes.repository.repository.AuthRepositoryImpl
import com.benoitmanhes.repository.repository.ExplorerRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideAuthRepository(
        authLocalDataSource: AuthLocalDataSource,
        authRemoteDataSource: AuthRemoteDataSource,
    ): AuthRepository = AuthRepositoryImpl(
        authLocalDataSource = authLocalDataSource,
        authRemoteDataSource = authRemoteDataSource,
    )

    @Provides
    fun provideExplorerRepository(
        localDataSource: ExplorerLocalDataSource,
        remoteDataSource: ExplorerRemoteDataSource,
    ): ExplorerRepository = ExplorerRepositoryImpl(
        localDataSource = localDataSource,
        remoteDataSource = remoteDataSource,
    )
}
