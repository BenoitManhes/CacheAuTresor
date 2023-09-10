package com.benoitmanhes.domain.uimodel

import com.benoitmanhes.domain.model.Cache
import com.benoitmanhes.domain.model.CacheInstructions
import com.benoitmanhes.domain.model.CacheStep
import com.benoitmanhes.domain.model.CacheUserData
import com.benoitmanhes.domain.model.InstructionContent

data class UICacheDetails(
    val cache: Cache,
    val userData: CacheUserData,
    val explorerName: String?,
    val status: Status,
    val steps: List<UIStep>,
) : UIModel {

    enum class Status {
        Available, Started, Found, Owned;
    }
}
