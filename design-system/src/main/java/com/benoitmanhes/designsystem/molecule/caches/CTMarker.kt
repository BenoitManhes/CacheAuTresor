package com.benoitmanhes.designsystem.molecule.caches

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.benoitmanhes.designsystem.atoms.CTIcon
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.IconSpec

@Composable
fun CTMarker(
    icon: IconSpec,
    color: Color,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier
            .size(Dimens.Size.markerSize),
        shape = CTTheme.shape.circle,
        color = color,
        border = BorderStroke(
            width = CTTheme.stroke.medium,
            color = CTTheme.color.strokeOnPrimary,
        ),
        elevation = CTTheme.elevation.none,
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            CTIcon(
                icon = icon,
                color = CTTheme.color.textOnSurfacePrimary,
                size = Dimens.IconSize.Small,
            )
        }
    }
}

@Preview
@Composable
private fun PreviewCTMarker() {
    CTTheme {
        CTMarker(
            icon = CTTheme.icon.Parchment,
            color = CTTheme.color.primary,
        )
    }
}
