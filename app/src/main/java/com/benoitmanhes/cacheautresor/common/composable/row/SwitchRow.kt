package com.benoitmanhes.cacheautresor.common.composable.row

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.molecule.switchbutton.CTSwitch
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.extensions.ctClickable

@Composable
fun SwitchRow(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit),
    label: TextSpec,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .ctClickable(
                onClick = { onCheckedChange(!checked) },
                enabled = enabled,
            )
            .padding(
                horizontal = CTTheme.spacing.large,
                vertical = CTTheme.spacing.small,
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CTTextView(text = label)
        CTSwitch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            enabled = enabled,
        )
    }
}

@Immutable
data class SwitchRowState(
    val checked: Boolean,
    val onCheckedChange: (Boolean) -> Unit,
    val label: TextSpec,
    val enabled: Boolean = true,
    val key: Any = label.hashCode(),
) {
    @Composable
    fun Content() {
        SwitchRow(
            checked = checked,
            onCheckedChange = onCheckedChange,
            label = label,
            enabled = enabled,
        )
    }

    fun lazyItem(scope: LazyListScope) {
        scope.item(key = key, contentType = contentType) {
            Content()
        }
    }

    companion object {
        private const val contentType: String = "SwitchRow"
    }
}
