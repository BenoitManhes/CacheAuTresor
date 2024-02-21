package com.benoitmanhes.cacheautresor.common.extensions

import androidx.compose.ui.graphics.Color
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.maps.CacheMarkerIcon
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.IconSpec
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.theme.ComposeProvider
import com.benoitmanhes.designsystem.theme.composed
import com.benoitmanhes.designsystem.utils.extensions.getColorTheme
import com.benoitmanhes.domain.model.Cache
import com.benoitmanhes.domain.model.CacheSize
import com.benoitmanhes.domain.model.CacheUserStatus

internal fun Cache.Type.getCacheMarker(color: Color): CacheMarkerIcon = when (this) {
    is Cache.Type.Classical -> CacheMarkerIcon.Classical(color)
    is Cache.Type.Coop -> CacheMarkerIcon.Coop(color)
    is Cache.Type.Mystery -> CacheMarkerIcon.Mystery(color)
    is Cache.Type.Piste -> CacheMarkerIcon.Piste(color)
}

internal fun Cache.Type.getCacheMarkerFocus(color: Color): CacheMarkerIcon = when (this) {
    is Cache.Type.Classical -> CacheMarkerIcon.ClassicalFocus(color)
    is Cache.Type.Coop -> CacheMarkerIcon.CoopFocus(color)
    is Cache.Type.Mystery -> CacheMarkerIcon.MysteryFocus(color)
    is Cache.Type.Piste -> CacheMarkerIcon.PisteFocus(color)
}

internal fun Cache.getCacheMarker(status: CacheUserStatus): CacheMarkerIcon {
    val tint = getColorTheme(status).dayColorScheme.primary
    return when (status) {
        CacheUserStatus.Owned -> CacheMarkerIcon.Owner(tint)
        CacheUserStatus.Found -> CacheMarkerIcon.Found(tint)
        CacheUserStatus.Locked -> CacheMarkerIcon.Locked(tint)
        else -> type.getCacheMarker(tint)
    }
}

internal fun Cache.getCacheMarkerFocus(): CacheMarkerIcon {
    val tint = getColorTheme(CacheUserStatus.Started).dayColorScheme.primary
    return type.getCacheMarkerFocus(tint)
}

fun Cache.getIcon(): ComposeProvider<IconSpec> = type.getIcon()
fun Cache.Type.getIcon(): ComposeProvider<IconSpec> = when (this) {
    is Cache.Type.Classical -> CTTheme.composed { icon.Parchment }
    is Cache.Type.Coop -> CTTheme.composed { icon.Coop }
    is Cache.Type.Mystery -> CTTheme.composed { icon.Mystery }
    is Cache.Type.Piste -> CTTheme.composed { icon.Piste }
}

fun Cache.getSizeIcon(): ComposeProvider<IconSpec> = when (this.size) {
    CacheSize.Micro -> CTTheme.composed { icon.BoxMicro }
    CacheSize.Small -> CTTheme.composed { icon.BoxSmall }
    CacheSize.Regular -> CTTheme.composed { icon.BoxMedium }
    CacheSize.Big -> CTTheme.composed { icon.BoxBig }
    CacheSize.Undefined -> CTTheme.composed { icon.Question }
}

fun Cache.getTypeText(): TextSpec = type.getTypeText()
fun Cache.Type.getTypeText(): TextSpec = when (this) {
    is Cache.Type.Classical -> TextSpec.Resources(R.string.cache_type_classical)
    is Cache.Type.Coop -> TextSpec.Resources(R.string.cache_type_coop)
    is Cache.Type.Mystery -> TextSpec.Resources(R.string.cache_type_mystery)
    is Cache.Type.Piste -> TextSpec.Resources(R.string.cache_type_piste)
}
