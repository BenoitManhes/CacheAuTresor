package com.benoitmanhes.cacheautresor.ui.theme


import androidx.compose.material3.Typography
import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.ui.res.Dimens

private val LeagueSpartan = FontFamily(
    Font(R.font.league_spartan_light, FontWeight.W300),
    Font(R.font.league_spartan_regular, FontWeight.W400),
    Font(R.font.league_spartan_medium, FontWeight.W500),
)

@Immutable
object AppTypography {
    val header1: TextStyle = TextStyle(
        fontFamily = LeagueSpartan,
        fontWeight = FontWeight.W400,
        fontSize = Dimens.Font.header1FontSize,
    )
    val body: TextStyle = TextStyle(
        fontFamily = LeagueSpartan,
        fontWeight = FontWeight.W300,
        fontSize = Dimens.Font.bodyFontSize,
    )
    val bodyBold: TextStyle = TextStyle(
        fontFamily = LeagueSpartan,
        fontWeight = FontWeight.W500,
        fontSize = Dimens.Font.bodyFontSize,
    )
    val caption: TextStyle = TextStyle(
        fontFamily = LeagueSpartan,
        fontWeight = FontWeight.W300,
        fontSize = Dimens.Font.captionFontSize,
    )
    val captionBold: TextStyle = TextStyle(
        fontFamily = LeagueSpartan,
        fontWeight = FontWeight.W500,
        fontSize = Dimens.Font.captionFontSize,
    )
}
