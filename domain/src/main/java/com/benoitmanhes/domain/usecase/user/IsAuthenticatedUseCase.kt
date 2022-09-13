package com.benoitmanhes.domain.usecase.user

import com.benoitmanhes.domain.interfaces.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class IsAuthenticatedUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    operator fun invoke(): Flow<Boolean> = userRepository.isAuthenticated().map { it == true }
}