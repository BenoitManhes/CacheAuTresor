package com.benoitmanhes.cacheautresor.common.extensions

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.QuestionMark
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
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
import com.benoitmanhes.designsystem.utils.IconSpec
import com.benoitmanhes.designsystem.utils.TextSpec
import com.benoitmanhes.domain.model.Cache
import com.benoitmanhes.domain.model.CacheSize

internal fun Cache.getCacheMarker(): CacheMarker = when (this) {
    is Cache.Classical -> CacheMarker.Classical
    is Cache.Coop -> CacheMarker.Coop
    is Cache.Mystery -> CacheMarker.Mystery
    is Cache.Piste -> CacheMarker.Piste
}

@Composable
fun Cache.getIcon(): IconSpec = when (this) {
    is Cache.Classical -> IconSpec.VectorIcon(CTTheme.icon.Parchment, null)
    is Cache.Coop -> IconSpec.VectorIcon(CTTheme.icon.Coop, null)
    is Cache.Mystery -> IconSpec.VectorIcon(CTTheme.icon.Mystery, null)
    is Cache.Piste -> IconSpec.VectorIcon(CTTheme.icon.Piste, null)
}

@Composable
fun Cache.getSizeIcon(): IconSpec = when (this.size) {
    CacheSize.Micro -> IconSpec.VectorIcon(CTTheme.icon.BoxMicro, null)
    CacheSize.Small -> IconSpec.VectorIcon(CTTheme.icon.BoxSmall, null)
    CacheSize.Regular -> IconSpec.VectorIcon(CTTheme.icon.BoxMedium, null)
    CacheSize.Big -> IconSpec.VectorIcon(CTTheme.icon.BoxBig, null)
    CacheSize.Undefined -> IconSpec.VectorIcon(Icons.Rounded.QuestionMark, null)
}

fun Cache.getTypeText(): TextSpec = when (this) {
    is Cache.Classical -> TextSpec.Resources(R.string.cache_type_classical)
    is Cache.Coop -> TextSpec.Resources(R.string.cache_type_coop)
    is Cache.Mystery -> TextSpec.Resources(R.string.cache_type_mystery)
    is Cache.Piste -> TextSpec.Resources(R.string.cache_type_piste)
}
