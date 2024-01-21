package com.benoitmanhes.domain.usecase.authentication

import com.benoitmanhes.core.result.CTResult
import com.benoitmanhes.domain.interfaces.repository.AuthRepository
import com.benoitmanhes.domain.interfaces.repository.CacheUserDataRepository
import com.benoitmanhes.domain.interfaces.repository.CacheUserProgressRepository
import com.benoitmanhes.domain.interfaces.repository.DraftCacheRepository
import com.benoitmanhes.domain.interfaces.repository.DraftCacheStepRepository
import com.benoitmanhes.domain.interfaces.repository.SyncRepository
import com.benoitmanhes.domain.usecase.CTUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val userDataRepository: CacheUserDataRepository,
    private val userProgressRepository: CacheUserProgressRepository,
    private val draftCacheRepository: DraftCacheRepository,
    private val draftCacheStepRepository: DraftCacheStepRepository,
    private val syncRepository: SyncRepository,
) : CTUseCase() {

    operator fun invoke(): Flow<CTResult<Unit>> = useCaseFlow {
        runCatch {
            userDataRepository.clearCacheUserData()
        }
        runCatch {
            userProgressRepository.clearCacheUserProgress()
        }
        runCatch {
            draftCacheRepository.clearAll()
        }
        runCatch {
            draftCacheStepRepository.deleteAll()
        }
        syncRepository.clearLastSyncUserPoints()
        authRepository.logout()
    }
}
