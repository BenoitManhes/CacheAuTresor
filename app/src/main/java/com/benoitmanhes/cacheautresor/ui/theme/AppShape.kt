package com.benoitmanhes.cacheautresor.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import com.benoitmanhes.cacheautresor.ui.res.Dimens

object AppShape {
    val smallRoundedCornerShape: RoundedCornerShape
        @Composable
        get() = RoundedCornerShape(Dimens.Radius.small)

    val mediumRoundedCornerShape: RoundedCornerShape
        @Composable
        get() = RoundedCornerShape(Dimens.Radius.medium)

    val largeRoundedCornerShape: RoundedCornerShape
        @Composable
        get() = RoundedCornerShape(Dimens.Radius.large)
}
