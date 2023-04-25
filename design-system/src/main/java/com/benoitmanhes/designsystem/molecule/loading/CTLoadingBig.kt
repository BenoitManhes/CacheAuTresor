package com.benoitmanhes.designsystem.molecule.loading

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.benoitmanhes.designsystem.atoms.animation.LoadingDotAnimation
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.theme.CTTheme

@Composable
fun CTLoadingBig(
    modifier: Modifier = Modifier,
) {
    LoadingDotAnimation(
        modifier = modifier
            .size(Dimens.Size.lottieAnimationBigLoading),
        color = CTTheme.color.primary,
    )
}
