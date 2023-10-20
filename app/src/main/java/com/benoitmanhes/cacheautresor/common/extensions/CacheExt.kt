package com.benoitmanhes.cacheautresor.common.extensions

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.QuestionMark
import androidx.compose.runtime.Composable
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.screen.home.explore.CacheMarker
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
import com.benoitmanhes.designsystem.utils.TextSpec
import com.benoitmanhes.domain.model.Cache
import com.benoitmanhes.domain.model.CacheSize

internal fun Cache.getCacheMarker(): CacheMarker = when (type) {
    is Cache.Type.Classical -> CacheMarker.Classical
    is Cache.Type.Coop -> CacheMarker.Coop
    is Cache.Type.Mystery -> CacheMarker.Mystery
    is Cache.Type.Piste -> CacheMarker.Piste
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
