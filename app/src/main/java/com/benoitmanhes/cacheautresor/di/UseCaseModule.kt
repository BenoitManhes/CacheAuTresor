package com.benoitmanhes.cacheautresor.di

import com.benoitmanhes.domain.interfaces.repository.UserRepository
import com.benoitmanhes.domain.usecase.user.AuthenticationUseCase
import com.benoitmanhes.domain.usecase.user.IsAuthenticatedUseCase
import com.benoitmanhes.domain.usecase.user.LogoutUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideIsAuthenticatedUseCase(
        userRepository: UserRepository,
    ): IsAuthenticatedUseCase = IsAuthenticatedUseCase(userRepository)

    @Provides
    fun provideAuthenticationUseCase(
        userRepository: UserRepository,
    ): AuthenticationUseCase = AuthenticationUseCase(userRepository)

    @Provides
    fun provideLogoutUseCase(
        userRepository: UserRepository,
    ): LogoutUseCase = LogoutUseCase(userRepository)
}