package com.benoitmanhes.cacheautresor.common.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.benoitmanhes.cacheautresor.screen.home.explore.CacheMarker
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.domain.uimodel.UICacheDetails

@Composable
internal fun UICacheDetails.getColor(): Color =
    when (userStatus) {
        UICacheDetails.CacheDetailsUserStatus.Owned -> CTTheme.color.primaryOwner
        UICacheDetails.CacheDetailsUserStatus.Found -> CTTheme.color.primaryFound
        else -> cache.getCacheColor()
    }

internal fun UICacheDetails.getCacheMarker(): CacheMarker = when (userStatus) {
    UICacheDetails.CacheDetailsUserStatus.Owned -> CacheMarker.Owner
    UICacheDetails.CacheDetailsUserStatus.Found -> CacheMarker.Found
    else -> cache.getCacheMarker()
}
