package com.benoitmanhes.designsystem.atoms

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import com.benoitmanhes.designsystem.res.Dimens

@Composable
internal fun CTSelector(
    color: Color,
    shape: Shape,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier
            .fillMaxWidth(),
        color = Color.Transparent,
        border = BorderStroke(Dimens.Stroke.strong, color),
        shape = shape,
    ) { }
}
