package com.benoitmanhes.cacheautresor.common.composable.row

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.benoitmanhes.cacheautresor.common.composable.map.SmallMap
import com.benoitmanhes.cacheautresor.common.uimodel.UIMarker
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.atoms.CTIcon
import com.benoitmanhes.designsystem.atoms.CTIconSlot
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.theme.composed
import com.benoitmanhes.designsystem.utils.extensions.ctClickable

@Composable
fun MapRowPicker(
    text: TextSpec,
    uiMarker: UIMarker?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    paddingValues: PaddingValues? = null,
) {
    val padding = paddingValues ?: PaddingValues(
        horizontal = CTTheme.spacing.large,
        vertical = CTTheme.spacing.small,
    )
    val textColor = remember(enabled) {
        if (enabled) CTTheme.composed { color.textOnBackground } else CTTheme.composed { color.textDisable }
    }

    Row(
        modifier = modifier
            .ctClickable(enabled = enabled, onClick = onClick)
            .padding(padding)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(CTTheme.spacing.large),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (uiMarker != null) {
            SmallMap(uiMarker = uiMarker)
        } else {
            CTIconSlot(
                icon = CTTheme.icon.LocationUnknown,
                size = Dimens.IconSlotSize.Large,
                contentColor = CTTheme.color.primary,
            )
        }

        CTTextView(
            text = text,
            style = CTTheme.typography.body,
            color = textColor(),
            modifier = Modifier.weight(1f)
        )

        CTIcon(
            icon = CTTheme.icon.Chevron,
            size = Dimens.IconSize.Medium,
            color = textColor(),
        )
    }
}

@Stable
data class MapRowPickerState(
    val uiMarker: UIMarker?,
    val text: TextSpec,
    val key: Any,
    val enabled: Boolean = true,
    val onClick: () -> Unit,
) {
    companion object {
        private const val contentType: String = "MapRowPicker"
    }

    @Composable
    fun Content(
        modifier: Modifier = Modifier,
        paddingValues: PaddingValues? = null,
    ) {
        MapRowPicker(
            text = text,
            uiMarker = uiMarker,
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
            paddingValues = paddingValues,
        )
    }

    fun lazyItem(
        scope: LazyListScope,
    ) {
        scope.item(
            contentType = contentType,
            key = key,
        ) {
            Content()
        }
    }
}
