package com.benoitmanhes.domain.uimodel

import com.benoitmanhes.domain.model.Cache
import com.benoitmanhes.domain.model.CacheUserData
import com.benoitmanhes.domain.model.CacheUserProgress

data class UICacheDetails(
    val cache: Cache,
    val explorerName: String?,
    val status: Status,
    val steps: List<UIStep>,
    val currentStep: UIStep,
    val cacheProgress: CacheUserProgress,
    val userData: CacheUserData,
) : UIModel {

    enum class Status {
        Available, Started, Found, Owned
    }
}
