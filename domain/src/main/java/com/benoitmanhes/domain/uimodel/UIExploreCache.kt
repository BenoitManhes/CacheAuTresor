package com.benoitmanhes.domain.uimodel

import com.benoitmanhes.domain.model.Cache
import com.benoitmanhes.domain.model.CacheUserStatus
import com.benoitmanhes.domain.model.Distance

data class UIExploreCache(
    val cache: Cache,
    val explorerName: String?,
    val userStatus: CacheUserStatus,
    val distance: Distance?,
    val ptsWin: Int?,
) : UIModel
