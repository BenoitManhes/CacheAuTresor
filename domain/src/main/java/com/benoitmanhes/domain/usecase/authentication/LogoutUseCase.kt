package com.benoitmanhes.domain.usecase.authentication

import com.benoitmanhes.core.result.CTResult
import com.benoitmanhes.domain.interfaces.repository.AuthRepository
import com.benoitmanhes.domain.interfaces.repository.CacheUserDataRepository
import com.benoitmanhes.domain.interfaces.repository.CacheUserProgressRepository
import com.benoitmanhes.domain.interfaces.repository.SyncRepository
import com.benoitmanhes.domain.usecase.CTUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val userDataRepository: CacheUserDataRepository,
    private val userProgressRepository: CacheUserProgressRepository,
    private val syncRepository: SyncRepository,
) : CTUseCase() {

    operator fun invoke(): Flow<CTResult<Unit>> = useCaseFlow {
        runCatch {
            userDataRepository.clearCacheUserData()
        }
        runCatch {
            userProgressRepository.clearCacheUserProgress()
        }
        syncRepository.clearLastSyncUserPoints()
        authRepository.logout()
    }
}
