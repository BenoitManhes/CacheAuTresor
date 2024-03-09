package com.benoitmanhes.domain.usecase.explorer

import com.benoitmanhes.core.result.CTResult
import com.benoitmanhes.domain.interfaces.repository.ExplorerRepository
import com.benoitmanhes.domain.model.Explorer
import com.benoitmanhes.domain.usecase.CTUseCase
import com.benoitmanhes.domain.usecase.common.GetMyExplorerIdUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMyExplorerUseCase @Inject constructor(
    private val getMyExplorerIdUseCase: GetMyExplorerIdUseCase,
    private val explorerRepository: ExplorerRepository,
) : CTUseCase() {

    suspend operator fun invoke(): Explorer? = runCatchNullable {
        explorerRepository.getExplorer(getMyExplorerIdUseCase())
    }

    fun asFlow(): Flow<CTResult<Explorer>> = useCaseFlow {
        val myExplorerId = getMyExplorerIdUseCase()
        emitAll(
            explorerRepository.getUserExplorerFlow(myExplorerId, fetch = false).map {
                CTResult.Success(it)
            }
        )
    }
}
