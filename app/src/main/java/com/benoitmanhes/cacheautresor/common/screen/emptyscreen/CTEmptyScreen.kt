package com.benoitmanhes.cacheautresor.common.screen.emptyscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.atoms.CTImage
import com.benoitmanhes.designsystem.atoms.text.CTMarkdownText
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.ImageSpec

@Composable
fun CTEmptyScreen(
    illustration: ImageSpec,
    message: TextSpec,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(CTTheme.color.background),
    ) {
        ConstraintLayout(
            constraintSet = constraintsSet(),
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding(),
        ) {
            CTImage(
                image = illustration,
                modifier = Modifier
                    .layoutId(Refs.Illustration)
                    .padding(horizontal = CTTheme.spacing.huge)
                    .aspectRatio(1f),
            )

            CTMarkdownText(
                markdown = message,
                style = CTTheme.typography.bodyBold,
                modifier = Modifier
                    .layoutId(Refs.Message)
                    .padding(horizontal = CTTheme.spacing.large)
                    .padding(top = textPaddingTop),
                textAlign = TextAlign.Center,
            )
        }
    }
}

private fun constraintsSet(): ConstraintSet = ConstraintSet {
    val image = createRefFor(Refs.Illustration)
    val text = createRefFor(Refs.Message)
    val guideline = createGuidelineFromTop(0.45f)

    constrain(image) {
        centerAround(guideline)
    }

    constrain(text) {
        top.linkTo(image.bottom)
        width = Dimension.matchParent
    }
}

private val textPaddingTop: Dp = 24.dp

private object Refs {
    const val Illustration: String = "Illustration.Ref"
    const val Message: String = "Message.Ref"
}

@Preview
@Composable
private fun PreviewEmptyScreen() {
    CTTheme {
        CTEmptyScreen(
            illustration = ImageSpec.ResImage(R.drawable.illustr_cache_created),
            message = TextSpec.loreumIpsum(8),
        )
    }
}
