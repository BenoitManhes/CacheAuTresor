package com.benoitmanhes.cacheautresor.screen.home.edit.editdraftcache.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.benoitmanhes.designsystem.molecule.jauge.CTJauge
import com.benoitmanhes.designsystem.molecule.jauge.CTJaugeState
import com.benoitmanhes.designsystem.theme.CTTheme

@Composable
fun DraftPropertiesSection(
    jauges: List<CTJaugeState>,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(CTTheme.spacing.large),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        jauges.map { jauge ->
            CTJauge(state = jauge)
        }
    }
}

@Immutable
data class DraftPropertiesSectionState(
    val difficulty: CTJaugeState,
    val ground: CTJaugeState,
    val size: CTJaugeState,
) {
    @Composable
    fun Content() {
        DraftPropertiesSection(
            jauges = listOf(difficulty, ground, size),
        )
    }

    fun lazyItem(
        scope: LazyListScope,
        key: Any = contentType,
    ) {
        scope.item(key = key, contentType = contentType) {
            Content()
        }
    }

    companion object {
        private val contentType: String = "DraftPropertiesSection"
    }
}
