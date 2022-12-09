package com.benoitmanhes.designsystem.theme

import androidx.compose.foundation.layout.PaddingValues
import com.benoitmanhes.designsystem.res.Dimens

object CTPadding {
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
    val zero: PaddingValues = PaddingValues(Dimens.Padding.none)
}
