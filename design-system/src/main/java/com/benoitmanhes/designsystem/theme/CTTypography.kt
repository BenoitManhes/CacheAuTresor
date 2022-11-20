package com.benoitmanhes.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.benoitmanhes.designsystem.R

private val LeagueSpartan = FontFamily(
    Font(R.font.league_spartan_light, FontWeight.W300),
    Font(R.font.league_spartan_regular, FontWeight.W400),
    Font(R.font.league_spartan_medium, FontWeight.W500),
)

@Immutable
object CTTypography {
    val header1: TextStyle = TextStyle(
        fontFamily = LeagueSpartan,
        fontWeight = FontWeight.W400,
        fontSize = Dimens.Font.header1FontSize,
        platformStyle = PlatformTextStyle(includeFontPadding = false),
    )
    val body: TextStyle = TextStyle(
        fontFamily = LeagueSpartan,
        fontWeight = FontWeight.W300,
        fontSize = Dimens.Font.bodyFontSize,
        platformStyle = PlatformTextStyle(includeFontPadding = false),
    )
    val bodyBold: TextStyle = TextStyle(
        fontFamily = LeagueSpartan,
        fontWeight = FontWeight.W500,
        fontSize = Dimens.Font.bodyFontSize,
        platformStyle = PlatformTextStyle(includeFontPadding = false),
    )
    val bodySmall: TextStyle = TextStyle(
        fontFamily = LeagueSpartan,
        fontWeight = FontWeight.W300,
        fontSize = Dimens.Font.bodySmallFontSize,
        platformStyle = PlatformTextStyle(includeFontPadding = false),
    )
    val caption: TextStyle = TextStyle(
        fontFamily = LeagueSpartan,
        fontWeight = FontWeight.W300,
        fontSize = Dimens.Font.captionFontSize,
        platformStyle = PlatformTextStyle(includeFontPadding = false),
    )
    val captionBold: TextStyle = TextStyle(
        fontFamily = LeagueSpartan,
        fontWeight = FontWeight.W500,
        fontSize = Dimens.Font.captionFontSize,
        platformStyle = PlatformTextStyle(includeFontPadding = false),
    )
}
