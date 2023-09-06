package com.benoitmanhes.cacheautresor.screen.home.explore.cachedetailrecap.section

import androidx.compose.foundation.lazy.LazyListScope
import com.benoitmanhes.cacheautresor.common.composable.row.CoordinatesRow
import com.benoitmanhes.designsystem.molecule.row.CTRow
import com.benoitmanhes.designsystem.molecule.row.CTRowState
import com.benoitmanhes.designsystem.res.icons.CTIconPack
import com.benoitmanhes.designsystem.res.icons.iconpack.Location
import com.benoitmanhes.designsystem.utils.IconSpec
import com.benoitmanhes.designsystem.utils.TextSpec
import com.benoitmanhes.domain.model.Coordinates

object CacheGeoSection {
    fun item(
        scope: LazyListScope,
        coordinates: Coordinates,
        distanceText: TextSpec,
    ) {
        CoordinatesRow.item(
            scope = scope,
            coordinates = coordinates,
            key = "CoordinatesRow",
        )
        CTRow.item(
            scope = scope,
            state = CTRowState(
                leadingIcon = IconSpec.VectorIcon(CTIconPack.Location),
                text = distanceText,
            ),
            key = "LocationRow",
        )
    }
}