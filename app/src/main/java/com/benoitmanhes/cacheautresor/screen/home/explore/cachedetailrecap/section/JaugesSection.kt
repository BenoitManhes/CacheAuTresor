package com.benoitmanhes.cacheautresor.screen.home.explore.cachedetailrecap.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.benoitmanhes.cacheautresor.screen.home.explore.cachedetails.CacheDetailsViewModelState
import com.benoitmanhes.designsystem.molecule.jauge.CTJauge
import com.benoitmanhes.designsystem.theme.CTTheme

internal fun jaugesSection(scope: LazyListScope, data: CacheDetailsViewModelState.Data) {
    scope.item(key = "jaugesSection", contentType = "jaugesSection") {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = CTTheme.spacing.large),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            CTJauge(data.difficultyJaugeState)
            CTJauge(data.groundJaugeState)
            CTJauge(data.sizeJaugeState)
        }
    }
}