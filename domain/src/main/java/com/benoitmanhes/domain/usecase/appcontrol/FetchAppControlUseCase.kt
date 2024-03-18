package com.benoitmanhes.domain.usecase.appcontrol

import com.benoitmanhes.domain.interfaces.repository.AppControlRepository
import com.benoitmanhes.domain.usecase.CTUseCase
import javax.inject.Inject

class FetchAppControlUseCase @Inject constructor(
    private val appControlRepository: AppControlRepository,
) : CTUseCase() {
    suspend operator fun invoke() {
        appControlRepository.fetchAppControl()
    }
}
