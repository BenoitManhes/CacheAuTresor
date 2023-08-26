package com.benoitmanhes.domain.uimodel

import com.benoitmanhes.domain.model.Cache

data class UIExploreCache(
    val cache: Cache,
    val explorerName: String?,
    val userStatus: CacheUserStatus,
    val distance: Double?,
) : UIModel {
    enum class CacheUserStatus {
        Available, Found, Lock, Owned;
    }
}