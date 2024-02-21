package com.benoitmanhes.cacheautresor.common.composable.row

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.tooling.preview.Preview
import com.benoitmanhes.cacheautresor.common.extensions.format
import com.benoitmanhes.designsystem.molecule.row.CTRow
import com.benoitmanhes.designsystem.molecule.row.CTRowState
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.domain.model.Coordinates

@Composable
fun CoordinatesRow(
    coordinates: Coordinates,
    initialFormat: Coordinates.Format = Coordinates.Format.DM,
) {
    var format by remember {
        mutableStateOf(initialFormat)
    }

    CTRow(
        state = CTRowState(
            leadingIcon = { CTTheme.icon.Globe },
            text = coordinates.format(format),
            onClick = {
                format = Coordinates.Format.next(format)
            },
        ),
    )
}

object CoordinatesRow {
    private const val contentType: String = "CoordinatesRow"

    fun item(
        scope: LazyListScope,
        coordinates: Coordinates,
        initialFormat: Coordinates.Format = Coordinates.Format.DM,
        key: Any?,
    ) {
        scope.item(
            key = key,
            contentType = contentType,
        ) {
            CoordinatesRow(
                coordinates = coordinates,
                initialFormat = initialFormat,
            )
        }
    }
}

@Preview
@Composable
private fun PreviewCoordinatesRow() {
    CTTheme {
        CoordinatesRow(
            coordinates = Coordinates(
                latitude = 45.763417,
                longitude = 4.831617,
            )
        )
    }
}
