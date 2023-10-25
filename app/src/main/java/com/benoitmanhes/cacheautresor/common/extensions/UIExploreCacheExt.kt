package com.benoitmanhes.cacheautresor.common.extensions

import androidx.compose.runtime.Composable
import com.benoitmanhes.cacheautresor.screen.home.explore.CacheMarkerIcon
import com.benoitmanhes.designsystem.res.icons.iconpack.Coop
import com.benoitmanhes.designsystem.res.icons.iconpack.Crown
import com.benoitmanhes.designsystem.res.icons.iconpack.Ensign
import com.benoitmanhes.designsystem.res.icons.iconpack.Mystery
import com.benoitmanhes.designsystem.res.icons.iconpack.Parchment
import com.benoitmanhes.designsystem.res.icons.iconpack.Piste
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.IconSpec
import com.benoitmanhes.domain.model.Cache
import com.benoitmanhes.domain.uimodel.UIExploreCache

@Composable
internal fun UIExploreCache.getIconCache(): IconSpec {
    val vector = when (userStatus) {
        UIExploreCache.CacheUserStatus.Owned -> CTTheme.icon.Ensign
        UIExploreCache.CacheUserStatus.Found -> CTTheme.icon.Crown
        else -> {
            when (cache.type) {
                is Cache.Type.Classical -> CTTheme.icon.Parchment
                is Cache.Type.Coop -> CTTheme.icon.Coop
                is Cache.Type.Mystery -> CTTheme.icon.Mystery
                is Cache.Type.Piste -> CTTheme.icon.Piste
            }
        }
    }
    return IconSpec.VectorIcon(vector, null)
}

internal fun UIExploreCache.getCacheMarker(): CacheMarkerIcon = when (userStatus) {
    UIExploreCache.CacheUserStatus.Owned -> CacheMarkerIcon.Owner
    UIExploreCache.CacheUserStatus.Found -> CacheMarkerIcon.Found
    UIExploreCache.CacheUserStatus.Started -> cache.getCacheMarkerStarted()
    else -> cache.getCacheMarker()
}
