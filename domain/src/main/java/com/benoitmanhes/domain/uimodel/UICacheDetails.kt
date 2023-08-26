package com.benoitmanhes.domain.uimodel

import com.benoitmanhes.domain.model.Cache
import com.benoitmanhes.domain.model.CacheUserData

data class UICacheDetails(
    val cache: Cache,
    val userData: CacheUserData,
    val explorerName: String?,
    val status: Status,
) : UIModel {
    enum class Status {
        Available, Started, Found, Owned;
    }
}
