package com.benoitmanhes.cacheautresor.ui.res

import androidx.compose.foundation.layout.PaddingValues

object AppPadding {
    val textFieldDefault: PaddingValues = PaddingValues(
        start = Dimens.Padding.horizontalTextFieldDefault,
        end = Dimens.Padding.horizontalTextFieldDefault,
        top = Dimens.Padding.topTextFieldDefault,
        bottom = Dimens.Padding.bottomTextFieldDefault,
    )
    val textFieldLabel: PaddingValues = PaddingValues(
        start = Dimens.Padding.horizontalTextFieldDefault,
        end = Dimens.Padding.horizontalTextFieldDefault,
        top = Dimens.Padding.topTextFieldLabel,
        bottom = Dimens.Padding.bottomTextFieldDefault,
    )
}