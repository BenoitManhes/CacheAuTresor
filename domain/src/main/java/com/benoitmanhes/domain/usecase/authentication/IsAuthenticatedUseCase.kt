package com.benoitmanhes.domain.usecase.authentication

import com.benoitmanhes.domain.interfaces.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class IsAuthenticatedUseCase @Inject constructor(
    private val userRepository: AuthRepository,
) {
    operator fun invoke(): Flow<Boolean> = userRepository.getAuthAccount().map { it != null }
}
