package com.benoitmanhes.di.repository

import com.benoitmanhes.domain.interfaces.repository.AuthRepository
import com.benoitmanhes.domain.interfaces.repository.CacheRepository
import com.benoitmanhes.domain.interfaces.repository.CacheUserDataRepository
import com.benoitmanhes.domain.interfaces.repository.CacheUserProgressRepository
import com.benoitmanhes.domain.interfaces.repository.DraftCacheRepository
import com.benoitmanhes.domain.interfaces.repository.DraftCacheStepRepository
import com.benoitmanhes.domain.interfaces.repository.ExplorerRepository
import com.benoitmanhes.domain.interfaces.repository.StepRepository
import com.benoitmanhes.domain.interfaces.repository.SyncRepository
import com.benoitmanhes.repository.AuthRepositoryImpl
import com.benoitmanhes.repository.CacheRepositoryImpl
import com.benoitmanhes.repository.CacheUserDataRepositoryImpl
import com.benoitmanhes.repository.CacheUserProgressRepositoryImpl
import com.benoitmanhes.repository.DraftCacheRepositoryImpl
import com.benoitmanhes.repository.DraftCacheStepRepositoryImpl
import com.benoitmanhes.repository.ExplorerRepositoryImpl
import com.benoitmanhes.repository.StepRepositoryImpl
import com.benoitmanhes.repository.SyncRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
interface RepositoryActivityModule {

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

    @Binds
    @ActivityRetainedScoped
    fun bindSyncRepository(repoImpl: SyncRepositoryImpl): SyncRepository

    @Binds
    @ActivityRetainedScoped
    fun bindDraftCacheRepository(repoImpl: DraftCacheRepositoryImpl): DraftCacheRepository

    @Binds
    @ActivityRetainedScoped
    fun bindDraftCacheStepRepository(repoImpl: DraftCacheStepRepositoryImpl): DraftCacheStepRepository
}
