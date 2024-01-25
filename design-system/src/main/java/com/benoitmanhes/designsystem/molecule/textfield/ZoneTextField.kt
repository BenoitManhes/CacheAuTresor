package com.benoitmanhes.designsystem.molecule.textfield

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import com.benoitmanhes.designsystem.theme.CTTheme

@Composable
fun ZoneTextField(
    value: String?,
    onValueUpdated: (String?) -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        shape = CTTheme.shape.medium,
        elevation = CTTheme.elevation.none,
        color = CTTheme.color.surface,
        border = BorderStroke(CTTheme.stroke.thin, CTTheme.color.strokeDivider),
    ) {
        CTBasicTextField(
            value = value,
            onValueChange = onValueUpdated,
            modifier = Modifier.fillMaxSize(),
            colors = CTTextFieldColors(
                textColor = CTTheme.color.textOnSurface,
                color = CTTheme.color.textOnSurface,
            ),
            imeAction = ImeAction.Default,
        )
    }
}
