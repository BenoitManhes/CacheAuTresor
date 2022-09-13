package com.benoitmanhes.domain.usecase.user

import com.benoitmanhes.domain.interfaces.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    operator fun invoke() {
        CoroutineScope(Dispatchers.IO).launch {
            userRepository.saveIsAuthenticated(false)
        }
    }
}