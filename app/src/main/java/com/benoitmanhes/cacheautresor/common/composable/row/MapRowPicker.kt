package com.benoitmanhes.cacheautresor.common.composable.row

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.benoitmanhes.cacheautresor.common.composable.map.SmallMap
import com.benoitmanhes.cacheautresor.common.uimodel.UIMarker
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.atoms.CTIcon
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.res.icons.iconpack.Chevron
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.extensions.ctClickable
import com.benoitmanhes.designsystem.utils.extensions.toIconSpec

@Composable
fun MapRowPicker(
    text: TextSpec,
    uiMarker: UIMarker?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .ctClickable(onClick)
            .padding(
                horizontal = CTTheme.spacing.large,
                vertical = CTTheme.spacing.small,
            )
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(CTTheme.spacing.large),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        uiMarker?.let {
            SmallMap(uiMarker)
        }

        CTTextView(
            text = text,
            style = CTTheme.typography.body,
            color = CTTheme.color.textOnBackground,
            modifier = Modifier.weight(1f)
        )

        CTIcon(
            icon = CTTheme.icon.Chevron.toIconSpec(),
            size = Dimens.IconSize.Medium,
            color = CTTheme.color.textOnBackground,
        )
    }
}

@Stable
data class MapRowPickerState(
    val uiMarker: UIMarker?,
    val text: TextSpec,
    val onClick: () -> Unit,
) {
    companion object {
        private const val contentType: String = "MapRowPicker"
    }

    @Composable
    fun Content(modifier: Modifier = Modifier) {
        MapRowPicker(
            text = text,
            uiMarker = uiMarker,
            onClick = onClick,
            modifier = modifier,
        )
    }

    fun lazyItem(
        scope: LazyListScope,
        key: Any,
    ) {
        scope.item(
            contentType = contentType,
            key = key,
        ) {
            Content()
        }
    }
}
