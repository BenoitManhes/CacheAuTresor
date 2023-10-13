package com.benoitmanhes.domain.uimodel

import com.benoitmanhes.domain.model.Cache
import com.benoitmanhes.domain.model.CacheUserData
import com.benoitmanhes.domain.model.CacheUserProgress
import java.util.Date

data class UICacheDetails(
    val cache: Cache,
    val explorerName: String?,
    val status: Status,
    val steps: List<UIStep>,
    val userData: CacheUserData,
) : UIModel {

    val currentStep: UIStep = steps.firstOrNull { it.status == UIStep.Status.Current } ?: steps.last()

    sealed interface Status {
        object Available : Status
        data class Started(val userProgress: CacheUserProgress) : Status
        data class Found(val foundDate: Date, val pts: Int?) : Status
        object Owned : Status
    }
}
