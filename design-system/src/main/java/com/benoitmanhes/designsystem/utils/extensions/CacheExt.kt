package com.benoitmanhes.designsystem.utils.extensions

import com.benoitmanhes.designsystem.theme.CTColorTheme
import com.benoitmanhes.domain.model.Cache
import com.benoitmanhes.domain.model.CacheUserStatus

fun Cache.Type.getTypeColorTheme(): CTColorTheme = when (this) {
    is Cache.Type.Classical -> CTColorTheme.Classical
    is Cache.Type.Coop -> CTColorTheme.Coop
    is Cache.Type.Mystery -> CTColorTheme.Mystery
    is Cache.Type.Piste -> CTColorTheme.Piste
}

fun Cache.getCacheColorTheme(): CTColorTheme = type.getTypeColorTheme()

fun Cache.getColorTheme(cacheUserStatus: CacheUserStatus): CTColorTheme {
    val cacheColorTheme = getCacheColorTheme()
    return when (cacheUserStatus) {
        CacheUserStatus.Owned -> CTColorTheme.Cartography
        CacheUserStatus.Found -> CTColorTheme.Explore

        CacheUserStatus.Started,
        CacheUserStatus.Available,
        -> cacheColorTheme

        CacheUserStatus.Locked -> CTColorTheme.Lock
        CacheUserStatus.Hidden -> CTColorTheme.Lock
    }
}
