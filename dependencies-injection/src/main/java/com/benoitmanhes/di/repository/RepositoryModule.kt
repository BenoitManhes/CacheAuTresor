package com.benoitmanhes.di.repository

import com.benoitmanhes.domain.interfaces.repository.AuthRepository
import com.benoitmanhes.domain.interfaces.repository.CacheRepository
import com.benoitmanhes.domain.interfaces.repository.CacheUserDataRepository
import com.benoitmanhes.domain.interfaces.repository.CacheUserProgressRepository
import com.benoitmanhes.repository.CacheUserProgressRepositoryImpl
import com.benoitmanhes.domain.interfaces.repository.ExplorerRepository
import com.benoitmanhes.domain.interfaces.repository.StepRepository
import com.benoitmanhes.repository.AuthRepositoryImpl
import com.benoitmanhes.repository.CacheRepositoryImpl
import com.benoitmanhes.repository.CacheUserDataRepositoryImpl
import com.benoitmanhes.repository.ExplorerRepositoryImpl
import com.benoitmanhes.repository.StepRepositoryImpl
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

    @Binds
    @ActivityRetainedScoped
    fun bindInstructionRepository(repoImpl: StepRepositoryImpl): StepRepository

    @Binds
    @ActivityRetainedScoped
    fun bindCacheUserDataRepository(repoImpl: CacheUserDataRepositoryImpl): CacheUserDataRepository

    @Binds
    @ActivityRetainedScoped
    fun bindCacheUserProgressRepository(repoImpl: CacheUserProgressRepositoryImpl): CacheUserProgressRepository
}
