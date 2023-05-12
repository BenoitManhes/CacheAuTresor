package com.benoitmanhes.cacheautresor.common.extensions

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.benoitmanhes.cacheautresor.screen.home.explore.CacheMarker
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
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

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

internal fun UICache.getCacheMarker(): CacheMarker = when (userStatus) {
    UICache.CacheUserStatus.Owned -> CacheMarker.Owner
    UICache.CacheUserStatus.Found -> CacheMarker.Found
    else -> cache.getCacheMarker()
}

internal fun UICache.getOSMMarker(
    context: Context,
    mapViewState: MapView,
    isSelected: Boolean,
    onClick: (() -> Unit)? = null,
): Marker = Marker(mapViewState).apply {
    position = cache.coordinates.toGeoPoint()
    setAnchor(
        Marker.ANCHOR_CENTER,
        if (isSelected) Marker.ANCHOR_BOTTOM else Marker.ANCHOR_CENTER,
    )
    icon = getCacheMarker().getDrawable(
        isSelected = isSelected,
        context = context,
    )
    onClick?.let {
        setOnMarkerClickListener { _, _ ->
            onClick()
            true
        }
    }
}
