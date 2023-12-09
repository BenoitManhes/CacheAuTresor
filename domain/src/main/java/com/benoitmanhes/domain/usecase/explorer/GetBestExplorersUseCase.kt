package com.benoitmanhes.domain.usecase.explorer

import com.benoitmanhes.domain.interfaces.repository.ExplorerRepository
import com.benoitmanhes.domain.model.Explorer
import com.benoitmanhes.domain.usecase.CTUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetBestExplorersUseCase @Inject constructor(
    private val explorerRepository: ExplorerRepository,
) : CTUseCase() {
    operator fun invoke(): Flow<List<Explorer>> =
        explorerRepository.getAllExplorerFlow()
            .map { list ->
                list
                    .sortedByDescending { it.cachesFoundMap.values.sum() }
            }
            .useCaseCatch { emptyList() }
}
