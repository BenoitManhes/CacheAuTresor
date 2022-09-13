package com.benoitmanhes.repository

import com.benoitmanhes.domain.interfaces.datasource.UserLocalDataSource
import com.benoitmanhes.domain.interfaces.repository.UserRepository
import com.benoitmanhes.repository.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideUserRepository(
        userLocalDataSource: UserLocalDataSource,
    ): UserRepository = UserRepositoryImpl(userLocalDataSource)
}