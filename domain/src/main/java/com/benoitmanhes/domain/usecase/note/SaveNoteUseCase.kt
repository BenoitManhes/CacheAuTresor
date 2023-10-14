package com.benoitmanhes.domain.usecase.note

import com.benoitmanhes.core.result.CTSuspendResult
import com.benoitmanhes.domain.interfaces.repository.CacheUserDataRepository
import com.benoitmanhes.domain.model.CacheUserData
import com.benoitmanhes.domain.usecase.CTUseCase
import javax.inject.Inject

class SaveNoteUseCase @Inject constructor(
    private val userDataRepository: CacheUserDataRepository,
) : CTUseCase() {
    suspend operator fun invoke(cacheId: String, note: String?): CTSuspendResult<Unit> = runCatchSuspendResult {
        val userData = userDataRepository.getCacheUserData(cacheId) ?: CacheUserData(cacheId = cacheId)
        userDataRepository.saveCacheUserData(userData.copy(note = note))
        CTSuspendResult.Success(Unit)
    }
}
