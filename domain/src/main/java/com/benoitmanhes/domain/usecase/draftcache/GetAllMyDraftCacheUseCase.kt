package com.benoitmanhes.domain.usecase.draftcache

import com.benoitmanhes.core.result.CTResult
import com.benoitmanhes.domain.interfaces.repository.DraftCacheRepository
import com.benoitmanhes.domain.model.DraftCache
import com.benoitmanhes.domain.usecase.CTUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllMyDraftCacheUseCase @Inject constructor(
    private val draftCacheRepository: DraftCacheRepository,
) : CTUseCase() {
    operator fun invoke(): Flow<CTResult<List<DraftCache>>> =
        draftCacheRepository.getAllDraftCacheFlow()
            .map { CTResult.Success(it) }
            .useCaseCatch { CTResult.Failure(it) }
}
