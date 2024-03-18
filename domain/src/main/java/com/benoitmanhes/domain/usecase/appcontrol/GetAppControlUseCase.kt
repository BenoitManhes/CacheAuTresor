package com.benoitmanhes.domain.usecase.appcontrol

import com.benoitmanhes.domain.interfaces.repository.AppControlRepository
import com.benoitmanhes.domain.model.AppControl
import com.benoitmanhes.domain.usecase.CTUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAppControlUseCase @Inject constructor(
    private val appControlRepository: AppControlRepository,
) : CTUseCase() {
    operator fun invoke(): Flow<AppControl?> = appControlRepository.getAppControlAsFlow().useCaseCatch { null }
}
