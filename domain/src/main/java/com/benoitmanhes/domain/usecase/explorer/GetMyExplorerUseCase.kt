package com.benoitmanhes.domain.usecase.explorer

import com.benoitmanhes.domain.interfaces.repository.ExplorerRepository
import com.benoitmanhes.domain.model.Explorer
import com.benoitmanhes.domain.usecase.CTUseCase
import com.benoitmanhes.domain.usecase.common.GetMyExplorerIdUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMyExplorerUseCase @Inject constructor(
    private val getMyExplorerIdUseCase: GetMyExplorerIdUseCase,
    private val explorerRepository: ExplorerRepository,
) : CTUseCase() {

    suspend operator fun invoke(): Flow<Explorer> {
        val myExplorerId = getMyExplorerIdUseCase()
        return explorerRepository.getUserExplorerFlow(myExplorerId)
    }
}
