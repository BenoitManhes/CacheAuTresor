package com.benoitmanhes.designsystem.utils.extensions

import com.benoitmanhes.designsystem.theme.CTColorTheme
import com.benoitmanhes.domain.model.Cache
import com.benoitmanhes.domain.model.CacheUserStatus

private fun Cache.getCacheColorTheme(): Pair<CTColorTheme, CTColorTheme> = when (type) {
    is Cache.Type.Classical -> CTColorTheme.Classical to CTColorTheme.ClassicalStarted
    is Cache.Type.Coop -> CTColorTheme.Coop to CTColorTheme.CoopStarted
    is Cache.Type.Mystery -> CTColorTheme.Mystery to CTColorTheme.MysteryStarted
    is Cache.Type.Piste -> CTColorTheme.Piste to CTColorTheme.PisteStarted
}

fun Cache.getColorTheme(cacheUserStatus: CacheUserStatus): CTColorTheme {
    val (availableColor, startedColor) = getCacheColorTheme()
    return when (cacheUserStatus) {
        CacheUserStatus.Owned -> CTColorTheme.Cartography
        CacheUserStatus.Found -> CTColorTheme.Explore
        CacheUserStatus.Started -> startedColor
        CacheUserStatus.Available -> availableColor
        CacheUserStatus.Locked -> CTColorTheme.Lock
        CacheUserStatus.Hidden -> CTColorTheme.Lock
    }
}
