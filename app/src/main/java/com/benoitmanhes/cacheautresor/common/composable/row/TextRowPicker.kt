package com.benoitmanhes.cacheautresor.common.composable.row

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.atoms.CTIcon
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.res.icons.iconpack.Chevron
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.extensions.toIconSpec

@Composable
fun TextRowPicker(
    text: TextSpec,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(
                horizontal = CTTheme.spacing.large,
                vertical = CTTheme.spacing.small,
            ),
        horizontalArrangement = Arrangement.spacedBy(CTTheme.spacing.small),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CTTextView(
            text = text,
            color = CTTheme.color.textOnBackground,
            style = CTTheme.typography.body,
            modifier = Modifier.weight(1f),
        )

        CTIcon(
            icon = CTTheme.icon.Chevron.toIconSpec(),
            size = Dimens.IconSize.Medium,
            color = CTTheme.color.textOnBackground,
        )
    }
}

@Stable
data class TextRowPickerState(
    val text: TextSpec,
    val onClick: () -> Unit,
) {
    companion object {
        private const val contentType: String = "TextRowPicker"
    }

    fun lazyItem(
        scope: LazyListScope,
        key: Any,
    ) {
        scope.item(
            key = key,
            contentType = contentType,
        ) {
            TextRowPicker(text = text, onClick = onClick)
        }
    }
}
