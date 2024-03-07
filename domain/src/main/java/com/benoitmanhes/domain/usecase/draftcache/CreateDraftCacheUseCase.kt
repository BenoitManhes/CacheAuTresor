package com.benoitmanhes.domain.usecase.draftcache

import com.benoitmanhes.core.result.CTSuspendResult
import com.benoitmanhes.domain.interfaces.repository.DraftCacheRepository
import com.benoitmanhes.domain.model.DraftCache
import com.benoitmanhes.domain.usecase.CTUseCase
import java.util.UUID
import javax.inject.Inject

class CreateDraftCacheUseCase @Inject constructor(
    private val draftCacheRepository: DraftCacheRepository,
) : CTUseCase() {
    suspend operator fun invoke(): CTSuspendResult<DraftCache> = runCatchSuspendResult {
        val newDraftCache = DraftCache(
            draftCacheId = UUID.randomUUID().toString(),
            title = null,
            coordinates = null,
            difficulty = null,
            ground = null,
            size = null,
            startCreatingDate = null,
            cacheIdsRequired = null,
            tagIds = null,
            finalStepRef = null,
            description = null,
            lockDescription = null,
            lockCode = null,
            type = null,
            progress = 0.0f,
        )
        draftCacheRepository.saveDraftCache(newDraftCache)
        CTSuspendResult.Success(newDraftCache)
    }
}
