package com.benoitmanhes.domain.usecase.register

import com.benoitmanhes.domain.extension.convert
import com.benoitmanhes.domain.interfaces.repository.AuthRepository
import com.benoitmanhes.domain.interfaces.repository.ExplorerRepository
import com.benoitmanhes.domain.model.Explorer
import com.benoitmanhes.domain.structure.BError
import com.benoitmanhes.domain.structure.BResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CreateAccountUseCase @Inject constructor(
    private val explorerRepository: ExplorerRepository,
    private val authRepository: AuthRepository,
) {
    operator fun invoke(
        tokenAccount: String,
        email: String,
        password: String,
        explorerName: String,
    ): Flow<BResult<Unit>> = flow {
        emit(BResult.Loading())
        handleResult(authRepository.isAuthCodeValid(tokenAccount)) {
            handleResult(explorerRepository.isExplorerNameAvailable(explorerName = explorerName)) {
                handleResult(explorerRepository.createExplorer(explorer = newExplorer(explorerName))) { createExplorerSuccess ->
                    handleResult(
                        result = authRepository.createAuthAccount(
                            email = email,
                            password = password,
                            explorerId = createExplorerSuccess.successData.explorerId,
                        ),
                        onError = { failure ->
                            explorerRepository.deleteExplorer(explorerId = createExplorerSuccess.successData.explorerId)
                            emit(failure.convert())
                        },
                        onSuccess = {
                            authRepository.deleteAuthCode(tokenAccount)
                            emit(BResult.Success(Unit))
                        },
                    )
                }
            }
        }
    }

    private fun newExplorer(explorerName: String): Explorer = Explorer(
        // The id is defined by datasource if empty
        explorerId = "",
        name = explorerName,
    )
}

private suspend fun <R, U> FlowCollector<BResult<U>>.handleResult(
    result: BResult<R>,
    onError: suspend FlowCollector<BResult<U>>.(BResult.Failure<R>) -> Unit = { failure ->
        emit(failure.convert())
    },
    onSuccess: suspend FlowCollector<BResult<U>>.(BResult.Success<R>) -> Unit,
) {
    when (result) {
        is BResult.Failure -> onError(result)
        is BResult.Success -> onSuccess(result)
        else -> {
            onError(BResult.Failure(BError.UnexpectedResult))
        }
    }
}
