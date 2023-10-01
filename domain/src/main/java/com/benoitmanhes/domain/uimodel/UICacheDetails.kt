package com.benoitmanhes.domain.uimodel

import com.benoitmanhes.domain.model.Cache

data class UICacheDetails(
    val cache: Cache,
    val explorerName: String?,
    val status: Status,
    val steps: List<UIStep>,
    val currentStep: UIStep,
) : UIModel {

    enum class Status {
        Available, Started, Found, Owned
    }
}
