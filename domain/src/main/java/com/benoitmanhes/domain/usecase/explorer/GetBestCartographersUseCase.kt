package com.benoitmanhes.domain.usecase.explorer

import com.benoitmanhes.domain.interfaces.repository.ExplorerRepository
import com.benoitmanhes.domain.model.Explorer
import com.benoitmanhes.domain.usecase.CTUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetBestCartographersUseCase @Inject constructor(
    private val explorerRepository: ExplorerRepository,
) : CTUseCase() {
    operator fun invoke(): Flow<List<Explorer>> =
        explorerRepository.getAllExplorerFlow()
            .map { list ->
                list.sortedByDescending { it.cachesMap.values.sum() }
            }
            .useCaseCatch { emptyList() }
}
