package com.benoitmanhes.cacheautresor.screen.home.edit.editdraftcache.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.common.compose.extensions.surface
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.atoms.CTIcon
import com.benoitmanhes.designsystem.atoms.text.CTTextView
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.extensions.ctClickable

@Composable
fun CrewStepsCardPlaceHolder(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .ctClickable(onClick)
            .surface(
                shape = CTTheme.shape.medium,
                backgroundColor = CTTheme.color.surface,
            )
            .padding(CTTheme.spacing.large),
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(CTTheme.spacing.small),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CTIcon(
                icon = CTTheme.icon.PersonAdd,
                size = Dimens.IconSize.Immense,
                color = CTTheme.color.textLight,
            )
            CTTextView(
                text = TextSpec.Resources(R.string.cacheEditor_stepsCoop_addCrew),
                style = CTTheme.typography.bodyBold,
                color = CTTheme.color.textLight,
                textAlign = TextAlign.Center,
            )
        }
    }
}
