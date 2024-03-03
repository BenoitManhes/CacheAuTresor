package com.benoitmanhes.designsystem.molecule.switchbutton

import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.benoitmanhes.designsystem.atoms.CTIcon
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.IconSpec

@Composable
fun CTSwitch(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    thumbIcon: IconSpec? = CTTheme.icon.Done,
) {
    Switch(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier,
        enabled = enabled,
        colors = SwitchDefaults.colors(
            checkedThumbColor = CTTheme.color.surface,
            checkedTrackColor = CTTheme.color.surfacePrimary,
            checkedIconColor = CTTheme.color.textPrimary,
            checkedBorderColor = Color.Transparent,
            uncheckedBorderColor = CTTheme.color.strokeBorder,
            uncheckedIconColor = CTTheme.color.textOnSurfacePrimary,
            uncheckedThumbColor = CTTheme.color.strokeBorder,
            uncheckedTrackColor = CTTheme.color.surface,
        ),
        thumbContent = thumbIcon?.takeIf { checked }?.let { icon ->
            {
                CTIcon(
                    icon = icon,
                    size = Dimens.IconSize.Small,
                    color = CTTheme.color.textPrimary,
                )
            }
        }
    )
}

@Immutable
data class CTSwitchState(
    val checked: Boolean,
    val onCheckedChange: (Boolean) -> Unit,
    val enabled: Boolean = true,
) {
    @Composable
    fun Content(modifier: Modifier = Modifier) {
        CTSwitch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            enabled = enabled,
            modifier = modifier,
        )
    }
}

@Composable
@Preview
private fun PreviewSwitch() {
    CTTheme {
        var checked by remember { mutableStateOf(false) }
        CTSwitch(
            checked = checked,
            onCheckedChange = { checked = it },
        )
    }
}