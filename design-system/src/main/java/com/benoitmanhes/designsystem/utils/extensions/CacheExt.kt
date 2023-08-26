package com.benoitmanhes.designsystem.utils.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.benoitmanhes.designsystem.res.Colors
import com.benoitmanhes.domain.model.Cache
import com.benoitmanhes.domain.uimodel.UICacheDetails

@Composable
private fun Cache.getCachePaletteColor(): Pair<Color, Color> = when (this) {
    is Cache.Classical -> Colors.LaSalleGreen to Colors.MiddleGreen
    is Cache.Coop -> Colors.LightSeaGreen to Colors.PewterBlue
    is Cache.Mystery -> Colors.MetallicViolet to Colors.FrenchLilac
    is Cache.Piste -> Colors.WatermelonRed to Colors.NewYorkPink
}

@Composable
fun UICacheDetails.getPrimaryColor(): Color {
    val (availableColor, startedColor) = cache.getCachePaletteColor()
    return when (status) {
        UICacheDetails.Status.Owned -> Colors.BlackOlive
        UICacheDetails.Status.Found -> Colors.Marigold
        UICacheDetails.Status.Available -> availableColor
        UICacheDetails.Status.Started -> startedColor
    }
}
