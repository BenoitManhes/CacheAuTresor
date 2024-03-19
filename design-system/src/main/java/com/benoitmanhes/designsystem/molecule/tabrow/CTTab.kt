package com.benoitmanhes.designsystem.molecule.tabrow

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Tab
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.atoms.CTIcon
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.IconSpec

@Composable
fun CTTab(
    text: TextSpec,
    selected: Boolean,
    enabled: Boolean = true,
    trailingIcon: IconSpec? = null,
    onClick: () -> Unit,
) {
    val contentColor = if (enabled) CTTheme.color.textDefault else CTTheme.color.textDisable
    val textStyle = if (selected) CTTheme.typography.bodyBold else CTTheme.typography.body

    Tab(
        selected = selected,
        onClick = onClick,
        enabled = enabled,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(CTTheme.spacing.extraSmall),
            modifier = Modifier
                .fillMaxSize()
                .heightIn(min = minTabHeight)
                .padding(horizontal = CTTheme.spacing.medium),
        ) {
            CTTextView(
                text = text,
                color = contentColor,
                style = textStyle,
            )

            trailingIcon?.let {
                CTIcon(
                    icon = trailingIcon,
                    size = Dimens.IconSize.Small,
                    color = contentColor,
                )
            }
        }
    }
}

private val minTabHeight: Dp = 42.dp
