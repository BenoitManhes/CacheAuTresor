package com.benoitmanhes.cacheautresor.screen.home.explore.cachedetails.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import com.benoitmanhes.cacheautresor.common.extensions.getSizeIcon
import com.benoitmanhes.cacheautresor.common.extensions.toDifficultyString
import com.benoitmanhes.cacheautresor.common.extensions.toJaugeRate
import com.benoitmanhes.cacheautresor.common.extensions.toSizeText
import com.benoitmanhes.cacheautresor.screen.home.explore.cachedetails.CacheDetailsViewModelState
import com.benoitmanhes.designsystem.atoms.bbDivider
import com.benoitmanhes.designsystem.atoms.spacer.spacerLargeItem
import com.benoitmanhes.designsystem.molecule.jauge.CTJauge
import com.benoitmanhes.designsystem.res.icons.iconpack.Box
import com.benoitmanhes.designsystem.res.icons.iconpack.Difficulty
import com.benoitmanhes.designsystem.res.icons.iconpack.Mountain
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.IconSpec

fun cacheDetailsContent(
    scope: LazyListScope,
    uiState: CacheDetailsViewModelState,
) {
    with(scope) {
//        spacerLargeItem()
//        specSection(data)
//        spacerLargeItem()
//        bbDivider(modifier = Modifier.horizontalScreenPadding())
//        spacerLargeItem()
    }
}

private fun LazyListScope.specSection(data: CacheDetailsViewModelState.Data) {
//    item(key = "specSection", contentType = "specSection") {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .horizontalScreenPadding(),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.SpaceEvenly,
//        ) {
//            CTJauge(
//                rate = data?.uiCacheDetails?.cache?.difficulty,
//                icon = IconSpec.VectorIcon(CTTheme.icon.Difficulty, null),
//                text = data?.uiCacheDetails?.cache?.difficulty?.toDifficultyString(),
//            )
//            CTJauge(
//                rate = data?.uiCacheDetails?.cache?.ground,
//                icon = IconSpec.VectorIcon(CTTheme.icon.Mountain, null),
//                text = data?.uiCacheDetails?.cache?.ground?.toDifficultyString(),
//            )
//            CTJauge(
//                rate = data?.uiCacheDetails?.cache?.size?.toJaugeRate(),
//                icon = data?.uiCacheDetails?.cache?.getSizeIcon() ?: IconSpec.VectorIcon(CTTheme.icon.Box, null),
//                text = data?.uiCacheDetails?.cache?.size?.toSizeText(),
//            )
//        }
//    }
}

private fun Modifier.horizontalScreenPadding() = composed { this.padding(horizontal = CTTheme.spacing.large) }
