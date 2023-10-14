package com.benoitmanhes.domain.usecase.note

import com.benoitmanhes.core.result.CTSuspendResult
import com.benoitmanhes.domain.interfaces.repository.CacheUserDataRepository
import com.benoitmanhes.domain.usecase.CTUseCase
import javax.inject.Inject

class GetCacheNoteUseCase @Inject constructor(
    private val userDataRepository: CacheUserDataRepository,
) : CTUseCase() {

    suspend operator fun invoke(cacheId: String): CTSuspendResult<String?> = runCatchSuspendResult {
        val userData = userDataRepository.getCacheUserData(cacheId)
        CTSuspendResult.Success(userData?.note)
    }
}
