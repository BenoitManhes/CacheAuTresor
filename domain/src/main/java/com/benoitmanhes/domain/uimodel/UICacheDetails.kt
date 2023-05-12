package com.benoitmanhes.domain.uimodel

import com.benoitmanhes.domain.model.Cache

data class UICacheDetails(
    val cache: Cache,
    val explorerName: String?,
    val userStatus: CacheDetailsUserStatus,
) : UIModel {
    enum class CacheDetailsUserStatus {
        Available, Started, Found, Owned;
    }
}
