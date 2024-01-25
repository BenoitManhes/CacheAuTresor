package com.benoitmanhes.cacheautresor.screen.home.explore.cachedetails.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.benoitmanhes.cacheautresor.utils.AppDimens
import com.benoitmanhes.designsystem.atoms.text.CTResponsiveText
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.theme.CTTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CacheDetailHeader(
    state: CacheDetailHeaderState,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(AppDimens.CacheDetail.bottomSheetHeaderHeight),
        elevation = CTTheme.elevation.none,
        color = CTTheme.color.surface,
        onClick = onClick,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            CTResponsiveText(
                text = state.title,
                minFontSize = CTTheme.typography.body.fontSize,
                color = CTTheme.color.textOnSurface,
                style = CTTheme.typography.header1,
                maxLines = 1,
            )
            CTTextView(
                text = state.subTitle,
                style = CTTheme.typography.caption,
                color = CTTheme.color.textOnSurface,
            )
        }
    }
}
