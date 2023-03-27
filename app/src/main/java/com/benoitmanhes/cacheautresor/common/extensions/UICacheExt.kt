package com.benoitmanhes.cacheautresor.common.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.benoitmanhes.designsystem.res.icons.iconpack.Coop
import com.benoitmanhes.designsystem.res.icons.iconpack.Crown
import com.benoitmanhes.designsystem.res.icons.iconpack.Ensign
import com.benoitmanhes.designsystem.res.icons.iconpack.Mystery
import com.benoitmanhes.designsystem.res.icons.iconpack.Parchment
import com.benoitmanhes.designsystem.res.icons.iconpack.Piste
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.IconSpec
import com.benoitmanhes.domain.model.Cache
import com.benoitmanhes.domain.uimodel.UICache


@Composable
internal fun UICache.getIconCache(): IconSpec {
    val vector = when (userStatus) {
        UICache.CacheUserStatus.Owned -> CTTheme.icon.Ensign
        UICache.CacheUserStatus.Found -> CTTheme.icon.Crown
        else -> {
            when (cache) {
                is Cache.Classical -> CTTheme.icon.Parchment
                is Cache.Coop -> CTTheme.icon.Coop
                is Cache.Mystery -> CTTheme.icon.Mystery
                is Cache.Piste -> CTTheme.icon.Piste
            }
        }
    }
    return IconSpec.VectorIcon(vector, null)
}

@Composable
internal fun UICache.getColor(): Color =
    when (userStatus) {
        UICache.CacheUserStatus.Owned -> CTTheme.color.primaryOwner
        UICache.CacheUserStatus.Found -> CTTheme.color.primaryFound
        else -> {
            when (cache) {
                is Cache.Classical -> CTTheme.color.primaryClassical
                is Cache.Coop -> CTTheme.color.primaryCoop
                is Cache.Mystery -> CTTheme.color.primaryMystery
                is Cache.Piste -> CTTheme.color.primaryPiste
            }
        }
    }