package com.benoitmanhes.cacheautresor.di

import com.benoitmanhes.domain.interfaces.repository.AuthRepository
import com.benoitmanhes.domain.usecase.authentication.CheckAuthCodeUseCase
import com.benoitmanhes.domain.usecase.authentication.LoginUseCase
import com.benoitmanhes.domain.usecase.authentication.IsAuthenticatedUseCase
import com.benoitmanhes.domain.usecase.authentication.LogoutUseCase
import com.benoitmanhes.domain.usecase.register.CreateAccountUseCase
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

    @Provides
    fun provideCheckCodeUseCase(
        authRepository: AuthRepository,
    ): CheckAuthCodeUseCase = CheckAuthCodeUseCase(authRepository)

    @Provides
    fun provideCreateAccountUseCase(
        authRepository: AuthRepository,
    ): CreateAccountUseCase = CreateAccountUseCase(authRepository)
}
