package com.benoitmanhes.domain.uimodel

import com.benoitmanhes.domain.model.Cache
import com.benoitmanhes.domain.model.CacheUserData
import com.benoitmanhes.domain.model.CacheUserProgress
import com.benoitmanhes.domain.model.CacheUserStatus
import java.util.Date

data class UICacheDetails(
    val cache: Cache,
    val explorerName: String?,
    val status: Status,
    val steps: List<UIStep>,
    val userData: CacheUserData,
) : UIModel {

    val currentStep: UIStep = steps.firstOrNull { it.status == UIStep.Status.Current } ?: steps.first()

    sealed interface Status {
        val cacheUserStatus: CacheUserStatus

        object Available : Status {
            override val cacheUserStatus: CacheUserStatus = CacheUserStatus.Available
        }

        data class Started(val userProgress: CacheUserProgress) : Status {
            override val cacheUserStatus: CacheUserStatus = CacheUserStatus.Started
        }

        data class Found(val foundDate: Date, val pts: Int?) : Status {
            override val cacheUserStatus: CacheUserStatus = CacheUserStatus.Found
        }

        object Owned : Status {
            override val cacheUserStatus: CacheUserStatus = CacheUserStatus.Owned
        }
    }
}
