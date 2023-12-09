package com.benoitmanhes.domain.uimodel

import com.benoitmanhes.domain.model.Cache
import com.benoitmanhes.domain.model.CacheUserStatus

data class UIExploreCache(
    val cache: Cache,
    val explorerName: String?,
    val userStatus: CacheUserStatus,
    val distance: Double?,
    val ptsWin: Int?,
) : UIModel
