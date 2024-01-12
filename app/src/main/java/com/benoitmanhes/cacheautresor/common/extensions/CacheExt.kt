package com.benoitmanhes.cacheautresor.common.extensions

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.QuestionMark
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.maps.CacheMarkerIcon
import com.benoitmanhes.designsystem.res.icons.iconpack.BoxBig
import com.benoitmanhes.designsystem.res.icons.iconpack.BoxMedium
import com.benoitmanhes.designsystem.res.icons.iconpack.BoxMicro
import com.benoitmanhes.designsystem.res.icons.iconpack.BoxSmall
import com.benoitmanhes.designsystem.res.icons.iconpack.Coop
import com.benoitmanhes.designsystem.res.icons.iconpack.Mystery
import com.benoitmanhes.designsystem.res.icons.iconpack.Parchment
import com.benoitmanhes.designsystem.res.icons.iconpack.Piste
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.theme.composed
import com.benoitmanhes.designsystem.utils.IconSpec
import com.benoitmanhes.common.compose.text.TextSpec
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

fun Cache.getIcon(): IconSpec = when (type) {
    is Cache.Type.Classical -> IconSpec.ComposeIcon(CTTheme.composed { icon.Parchment })
    is Cache.Type.Coop -> IconSpec.ComposeIcon(CTTheme.composed { icon.Coop })
    is Cache.Type.Mystery -> IconSpec.ComposeIcon(CTTheme.composed { icon.Mystery })
    is Cache.Type.Piste -> IconSpec.ComposeIcon(CTTheme.composed { icon.Piste })
}

@Composable
fun Cache.getSizeIcon(): IconSpec = when (this.size) {
    CacheSize.Micro -> IconSpec.VectorIcon(CTTheme.icon.BoxMicro, null)
    CacheSize.Small -> IconSpec.VectorIcon(CTTheme.icon.BoxSmall, null)
    CacheSize.Regular -> IconSpec.VectorIcon(CTTheme.icon.BoxMedium, null)
    CacheSize.Big -> IconSpec.VectorIcon(CTTheme.icon.BoxBig, null)
    CacheSize.Undefined -> IconSpec.VectorIcon(Icons.Rounded.QuestionMark, null)
}

fun Cache.getTypeText(): TextSpec = when (type) {
    is Cache.Type.Classical -> TextSpec.Resources(R.string.cache_type_classical)
    is Cache.Type.Coop -> TextSpec.Resources(R.string.cache_type_coop)
    is Cache.Type.Mystery -> TextSpec.Resources(R.string.cache_type_mystery)
    is Cache.Type.Piste -> TextSpec.Resources(R.string.cache_type_piste)
}
