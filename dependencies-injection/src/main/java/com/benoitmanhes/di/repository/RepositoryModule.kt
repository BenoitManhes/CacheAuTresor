package com.benoitmanhes.di.repository

import com.benoitmanhes.domain.interfaces.repository.AuthRepository
import com.benoitmanhes.domain.interfaces.repository.CacheRepository
import com.benoitmanhes.domain.interfaces.repository.ExplorerRepository
import com.benoitmanhes.repository.AuthRepositoryImpl
import com.benoitmanhes.repository.CacheRepositoryImpl
import com.benoitmanhes.repository.ExplorerRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
interface RepositoryModule {

    @Binds
    @ActivityRetainedScoped
    fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    @ActivityRetainedScoped
    fun bindExploreRepository(explorerRepositoryImpl: ExplorerRepositoryImpl): ExplorerRepository

    @Binds
    @ActivityRetainedScoped
    fun bindCacheRepository(cacheRepositoryImpl: CacheRepositoryImpl): CacheRepository
}
