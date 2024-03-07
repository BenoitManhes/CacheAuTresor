package com.benoitmanhes.domain.usecase.draftcache

import com.benoitmanhes.core.result.CTResult
import com.benoitmanhes.domain.interfaces.repository.CacheRepository
import com.benoitmanhes.domain.interfaces.repository.DraftCacheRepository
import com.benoitmanhes.domain.interfaces.repository.DraftCacheStepRepository
import com.benoitmanhes.domain.model.Cache
import com.benoitmanhes.domain.usecase.CTUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

//class CreateCacheUseCase @Inject constructor(
//    private val draftCacheRepository: DraftCacheRepository,
//    private val draftCacheStepRepository: DraftCacheStepRepository,
//    private val cacheRepository: CacheRepository,
//) : CTUseCase() {
//    operator fun invoke(): Flow<CTResult<Cache>> {
//
//    }
//}