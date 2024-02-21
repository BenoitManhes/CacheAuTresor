package com.benoitmanhes.cacheautresor.common.composable.card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.benoitmanhes.cacheautresor.common.extensions.format
import com.benoitmanhes.cacheautresor.common.extensions.orPlaceHolder
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.atoms.CTIcon
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.theme.composed
import com.benoitmanhes.domain.model.Coordinates

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CoordinatesCard(
    coordinatesText: TextSpec?,
    modifier: Modifier = Modifier,
    format: Coordinates.Format,
    onClick: () -> Unit,
) {
    val icon = remember(format) {
        when (format) {
            Coordinates.Format.DD -> CTTheme.composed { icon.GlobeDD }
            Coordinates.Format.DM -> CTTheme.composed { icon.GlobeDM }
            Coordinates.Format.DMS -> CTTheme.composed { icon.GlobeDMS }
        }
    }

    Surface(
        modifier = modifier,
        shape = CTTheme.shape.small,
        color = CTTheme.color.surface,
        border = BorderStroke(CTTheme.stroke.medium, CTTheme.color.strokeBorder),
        onClick = onClick,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = CTTheme.spacing.extraSmall, horizontal = CTTheme.spacing.small),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(CTTheme.spacing.small),
        ) {
            CTIcon(icon = icon(), size = Dimens.IconSize.Medium)
            CTTextView(text = coordinatesText, style = CTTheme.typography.body)
        }
    }
}

@Stable
data class CoordinatesCardState(
    val coordinates: Coordinates?,
    val format: Coordinates.Format,
    val onClick: () -> Unit,
) {
    val text: TextSpec? get() = coordinates?.format(format)

    @Composable
    fun Content(modifier: Modifier = Modifier) {
        CoordinatesCard(
            coordinatesText = text.orPlaceHolder(),
            format = format,
            onClick = onClick,
            modifier = modifier,
        )
    }
}
