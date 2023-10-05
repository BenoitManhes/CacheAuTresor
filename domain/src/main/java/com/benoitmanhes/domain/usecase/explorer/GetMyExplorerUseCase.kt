package com.benoitmanhes.domain.usecase.explorer

import com.benoitmanhes.core.error.CTDomainError
import com.benoitmanhes.domain.interfaces.repository.AuthRepository
import com.benoitmanhes.domain.interfaces.repository.ExplorerRepository
import com.benoitmanhes.domain.model.Explorer
import com.benoitmanhes.domain.usecase.CTUseCase
import com.benoitmanhes.domain.usecase.CTUseCaseImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMyExplorerUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val explorerRepository: ExplorerRepository,
    useCaseImpl: CTUseCaseImpl,
) : CTUseCase by useCaseImpl {

    suspend operator fun invoke(): Flow<Explorer> {
        val myExplorerId = authRepository.getAuthAccount()?.explorerId ?: throw CTDomainError(
            CTDomainError.Code.NO_AUTHENTICATION
        )
        return explorerRepository.getUserExplorerFlow(myExplorerId)
    }
}
