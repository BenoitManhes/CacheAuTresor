package com.benoitmanhes.cacheautresor.common.extensions

import android.content.Context
import androidx.compose.runtime.Composable
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
import com.benoitmanhes.domain.uimodel.UIExploreCache
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

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

internal fun UIExploreCache.getCacheMarker(): CacheMarker = when (userStatus) {
    UIExploreCache.CacheUserStatus.Owned -> CacheMarker.Owner
    UIExploreCache.CacheUserStatus.Found -> CacheMarker.Found
    else -> cache.getCacheMarker()
}

internal fun UIExploreCache.getOSMMarker(
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
