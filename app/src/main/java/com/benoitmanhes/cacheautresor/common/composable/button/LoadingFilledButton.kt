package com.benoitmanhes.cacheautresor.common.composable.button

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.benoitmanhes.cacheautresor.common.composable.divider.Spacer
import com.benoitmanhes.cacheautresor.common.composable.textview.TextView
import com.benoitmanhes.cacheautresor.ui.res.Dimens
import com.benoitmanhes.cacheautresor.ui.res.animation.LoaderAnimation
import com.benoitmanhes.cacheautresor.ui.res.icons.AppIconPack
import com.benoitmanhes.cacheautresor.ui.res.icons.appiconpack.IconLogo
import com.benoitmanhes.cacheautresor.ui.theme.AppTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun LoadingFilledButton(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = AppTheme.colors.primary,
    textColor: Color = AppTheme.colors.onPrimary,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    onClick: () -> Unit = { },
) {
    Button(
        onClick = {
            if (!isLoading) {
                onClick()
            }
        },
        modifier = modifier
            .heightIn(Dimens.ComponentSize.buttonHeight),
        enabled = enabled,
        colors = buttonColors(
            contentColor = textColor,
            containerColor = color,
            disabledContainerColor = AppTheme.colors.disable,
            disabledContentColor = AppTheme.colors.onDisable,
        ),
        shape = AppTheme.shape.mediumRoundedCornerShape,
    ) {

        TextView(
            text = text,
            style = AppTheme.typography.bodyBold,
            color = textColor,
        )
        if (isLoading) {
            Spacer(size = Dimens.Margin.medium)
        }
        AnimatedVisibility(
            visible = isLoading,
            enter = fadeIn() + scaleIn(),
            exit = fadeOut() + scaleOut(),
        ) {
            LoaderAnimation(
                vector = AppIconPack.IconLogo,
                color = textColor,
                modifier = Modifier
                    .size(Dimens.ComponentSize.buttonLoaderSize),
            )
        }
    }
}

@Preview
@Composable
private fun PreviewLoadingFilledButton() {
    AppTheme {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(verticalArrangement = Arrangement.spacedBy(Dimens.Margin.medium), modifier = Modifier.padding(Dimens.Margin.huge)) {
                LoadingFilledButton(text = "Button")
                LoadingFilledButton(text = "Loading", isLoading = true)
            }
        }
    }
}
