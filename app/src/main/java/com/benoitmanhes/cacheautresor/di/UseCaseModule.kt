package com.benoitmanhes.cacheautresor.di

import com.benoitmanhes.domain.interfaces.repository.AuthRepository
import com.benoitmanhes.domain.usecase.user.LoginUseCase
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
        userRepository: AuthRepository,
    ): IsAuthenticatedUseCase = IsAuthenticatedUseCase(userRepository)

    @Provides
    fun provideAuthenticationUseCase(
        userRepository: AuthRepository,
    ): LoginUseCase = LoginUseCase(userRepository)

    @Provides
    fun provideLogoutUseCase(
        authRepository: AuthRepository,
    ): LogoutUseCase = LogoutUseCase(authRepository)
}