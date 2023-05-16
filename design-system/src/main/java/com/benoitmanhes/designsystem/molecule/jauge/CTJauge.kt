package com.benoitmanhes.designsystem.molecule.jauge

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.benoitmanhes.designsystem.atoms.CTIcon
import com.benoitmanhes.designsystem.atoms.text.CTResponsiveText
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.res.icons.iconpack.Difficulty
import com.benoitmanhes.designsystem.res.icons.iconpack.Mountain
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.IconSpec
import com.benoitmanhes.designsystem.utils.TextSpec
import com.benoitmanhes.designsystem.utils.UiConstants

@Composable
fun CTJauge(
    step: CTJaugeStep,
    icon: IconSpec,
    text: TextSpec,
    modifier: Modifier = Modifier,
) {
    val progress = remember { Animatable(0f) }
    LaunchedEffect(step) {
        progress.animateTo(
            targetValue = step.progress,
            animationSpec = tween(durationMillis = UiConstants.Jauge.progressAnimDurationMillis)
        )
    }
    ConstraintLayout(
        modifier = modifier.size(Dimens.Jauge.size),
        constraintSet = constraints(),
    ) {
        CircularJauge(
            progress = progress.value,
            modifier = Modifier.layoutId(CircularIndicatorId),
        )

        CTIcon(
            icon = icon,
            size = Dimens.IconSize.Huge,
            modifier = Modifier.layoutId(IconId),
        )

        CTResponsiveText(
            text = text,
            minFontSize = Dimens.Font.captionSmallFontSize,
            modifier = Modifier
                .layoutId(TextId)
                .widthIn(max = Dimens.Jauge.maxTextSize),
            style = CTTheme.typography.caption,
            textAlign = TextAlign.Center,
            maxLines = 1,
        )
    }
}

private fun constraints(): ConstraintSet = ConstraintSet {
    val jauge = createRefFor(CircularIndicatorId)
    val icon = createRefFor(IconId)
    val text = createRefFor(TextId)

    constrain(jauge) {
        height = Dimension.value(Dimens.Jauge.circleIndicatorSize)
        top.linkTo(parent.top)
    }

    constrain(icon) {
        centerTo(parent)
    }

    constrain(text) {
        centerHorizontallyTo(parent)
        bottom.linkTo(parent.bottom)
    }
}

private const val CircularIndicatorId: String = "circularIndicator.id"
private const val IconId: String = "icon.id"
private const val TextId: String = "text.id"

@Preview
@Composable
private fun PreviewCTJauge() {
    CTTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(Dimens.Spacing.large)) {
                CTJauge(
                    step = CTJaugeStep.TWO_HALF,
                    icon = IconSpec.VectorIcon(CTTheme.icon.Mountain, null),
                    text = TextSpec.RawString("Dangereux"),
                )
                CTJauge(
                    step = CTJaugeStep.FOUR,
                    icon = IconSpec.VectorIcon(CTTheme.icon.Difficulty, null),
                    text = TextSpec.RawString("Expert"),
                )
            }
        }
    }
}
