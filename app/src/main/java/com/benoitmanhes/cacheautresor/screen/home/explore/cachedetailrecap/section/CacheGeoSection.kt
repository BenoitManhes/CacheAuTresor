package com.benoitmanhes.cacheautresor.screen.home.explore.cachedetailrecap.section

import androidx.compose.foundation.lazy.LazyListScope
import com.benoitmanhes.cacheautresor.common.composable.row.CoordinatesRow
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.molecule.row.CTRow
import com.benoitmanhes.designsystem.molecule.row.CTRowState
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.domain.model.Coordinates

object CacheGeoSection {
    fun item(
        scope: LazyListScope,
        coordinates: Coordinates,
        distanceText: TextSpec?,
    ) {
        CoordinatesRow.item(
            scope = scope,
            coordinates = coordinates,
            key = "CoordinatesRow",
        )
        distanceText?.let {
            CTRow.item(
                scope = scope,
                state = CTRowState(
                    leadingIcon = { CTTheme.icon.Location },
                    text = distanceText,
                ),
                key = "LocationRow",
            )
        }
    }
}
