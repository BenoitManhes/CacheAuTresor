package com.benoitmanhes.di.repository

import com.benoitmanhes.domain.interfaces.repository.AppControlRepository
import com.benoitmanhes.repository.AppControlRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositorySingletonModule {
    @Binds
    @Singleton
    fun bindAppControlRepository(repoImpl: AppControlRepositoryImpl): AppControlRepository
}
