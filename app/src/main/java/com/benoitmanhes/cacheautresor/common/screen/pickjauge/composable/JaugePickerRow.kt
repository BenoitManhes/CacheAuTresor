package com.benoitmanhes.cacheautresor.common.screen.pickjauge.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.benoitmanhes.cacheautresor.common.extensions.toStringShort
import com.benoitmanhes.cacheautresor.utils.AppDimens
import com.benoitmanhes.common.compose.extensions.textSpec
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.extensions.ctSurface

@Composable
fun JaugePickerRow(
    rateText: TextSpec,
    descriptionText: TextSpec,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val surfaceColor = if (isSelected) CTTheme.color.surfacePrimarySoft else CTTheme.color.surface
    Row(
        modifier = modifier
            .fillMaxWidth()
            .ctSurface(
                shape = CTTheme.shape.small,
                backgroundColor = surfaceColor,
                onClick = onClick,
            )
            .padding(
                horizontal = CTTheme.spacing.small,
                vertical = CTTheme.spacing.large,
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(CTTheme.spacing.small),
    ) {
        CTTextView(
            text = rateText,
            style = CTTheme.typography.bodyBold,
            color = CTTheme.color.textDefault,
            modifier = Modifier.widthIn(min = AppDimens.PickJauge.rateTextWidth),
        )

        CTTextView(
            text = descriptionText,
            style = CTTheme.typography.body,
            color = CTTheme.color.textDefault,
            modifier = Modifier.weight(1f),
        )
    }
}

@Immutable
data class JaugePickerRowState(
    val rate: Float,
    val descriptionText: TextSpec,
    val isSelected: Boolean,
    val onClick: () -> Unit,
) {

    @Composable
    fun Content(modifier: Modifier = Modifier) {
        JaugePickerRow(
            rateText = rate.toStringShort().textSpec(),
            descriptionText = descriptionText,
            isSelected = isSelected,
            onClick = onClick,
            modifier = modifier,
        )
    }

    fun lazyItem(
        scope: LazyListScope,
        modifier: Modifier = Modifier,
        key: Any = rate,
    ) {
        scope.item(key = key, contentType = contentType) {
            Content(modifier)
        }
    }

    companion object {
        private const val contentType: String = "JaugePickerRow"
    }
}
