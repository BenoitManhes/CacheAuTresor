package com.benoitmanhes.cacheautresor.common.composable.divider

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.composable.textview.TextView
import com.benoitmanhes.cacheautresor.ui.res.Dimens
import com.benoitmanhes.cacheautresor.ui.theme.AppTheme

@Composable
fun TextDivider(
    modifier: Modifier = Modifier,
    @StringRes textRes: Int = R.string.orSeparator_label,
) {
    Row(
        modifier = modifier.height(Dimens.Margin.large),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(Dimens.Margin.medium)
    ) {
        HorizontalDivider(modifier = Modifier.weight(1f))
        TextView(
            textRes = textRes,
            style = AppTheme.typography.caption,
            color = AppTheme.colors.placeholder,
            textAlign = TextAlign.Center,
        )
        HorizontalDivider(modifier = Modifier.weight(1f))
    }
}