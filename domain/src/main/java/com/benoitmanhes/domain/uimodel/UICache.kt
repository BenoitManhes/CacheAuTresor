package com.benoitmanhes.domain.uimodel

import com.benoitmanhes.domain.model.Cache

data class UICache(
    val cache: Cache,
    val explorerName: String?,
    val userStatus: CacheUserStatus,
    val distance: Double?,
) {
    enum class CacheUserStatus {
        Available, Found, Lock, Owned;
    }
}
