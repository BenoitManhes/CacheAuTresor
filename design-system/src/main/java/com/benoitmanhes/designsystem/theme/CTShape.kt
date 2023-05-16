package com.benoitmanhes.designsystem.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import com.benoitmanhes.designsystem.res.Dimens

object CTShape {
    val small: RoundedCornerShape = RoundedCornerShape(Dimens.Corner.small)
    val medium: RoundedCornerShape = RoundedCornerShape(Dimens.Corner.medium)
    val large: RoundedCornerShape = RoundedCornerShape(Dimens.Corner.large)
    val circle: RoundedCornerShape = RoundedCornerShape(Dimens.Corner.percentRounded)
    val bottomSheet: RoundedCornerShape = RoundedCornerShape(
        topEnd = Dimens.Corner.large,
        topStart = Dimens.Corner.large
    )
}
