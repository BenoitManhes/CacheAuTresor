package com.benoitmanhes.domain.usecase.register

import com.benoitmanhes.core.error.CTDomainError
import com.benoitmanhes.core.result.CTResult
import com.benoitmanhes.domain.utils.Util
import com.benoitmanhes.domain.interfaces.repository.AuthRepository
import com.benoitmanhes.domain.interfaces.repository.ExplorerRepository
import com.benoitmanhes.domain.model.Explorer
import com.benoitmanhes.domain.usecase.CTUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateAccountUseCase @Inject constructor(
    private val explorerRepository: ExplorerRepository,
    private val authRepository: AuthRepository,
) : CTUseCase() {

    operator fun invoke(
        tokenAccount: String,
        email: String,
        password: String,
        explorerName: String,
    ): Flow<CTResult<Unit>> = useCaseFlow {
        if (!authRepository.isAuthCodeValid(tokenAccount)) {
            throw CTDomainError(CTDomainError.Code.ACCOUNT_CREATION_INVALID_TOKEN)
        }
        if (!explorerRepository.isExplorerNameAvailable(explorerName)) {
            throw CTDomainError(CTDomainError.Code.ACCOUNT_CREATION_EXPLORER_NAME_UNAVAILABLE)
        }
        val explorerCreated = explorerRepository.createUserExplorer(explorer = newExplorer(explorerName))
        try {
            authRepository.createAuthAccount(
                email = email,
                password = password,
                explorerId = explorerCreated.explorerId,
            )
        } catch (e: Exception) {
            explorerRepository.deleteExplorer(explorerCreated.explorerId)
            throw e
        }
        authRepository.deleteAuthCode(tokenAccount)
        emit(CTResult.Success(Unit))
    }

    private fun newExplorer(explorerName: String): Explorer = Explorer(
        explorerId = Util.autoId(),
        name = explorerName,
        cachesMap = emptyMap(),
        cachesFoundMap = emptyMap(),
    )
}
