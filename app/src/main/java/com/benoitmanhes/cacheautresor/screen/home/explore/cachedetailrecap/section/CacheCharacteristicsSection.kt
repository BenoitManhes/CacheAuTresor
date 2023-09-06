package com.benoitmanhes.cacheautresor.screen.home.explore.cachedetailrecap.section

import androidx.compose.foundation.lazy.LazyListScope
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.composable.section.Section
import com.benoitmanhes.designsystem.molecule.row.CTRow
import com.benoitmanhes.designsystem.molecule.row.CTRowState
import com.benoitmanhes.designsystem.utils.TextSpec

object CacheCharacteristicsSection {
    fun item(scope: LazyListScope, characteristics: List<CTRowState>) {
        Section.item(
            scope = scope,
            title = TextSpec.Resources(R.string.cacheDetail_characteristicSection_title),
            content = {},
        )
        characteristics.forEach { characteristic ->
            CTRow.item(
                scope = scope,
                state = characteristic,
                key = characteristic.hashCode(),
            )
        }
    }
}