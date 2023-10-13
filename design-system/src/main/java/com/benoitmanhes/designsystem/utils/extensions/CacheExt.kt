package com.benoitmanhes.designsystem.utils.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.benoitmanhes.designsystem.res.Colors
import com.benoitmanhes.domain.model.Cache
import com.benoitmanhes.domain.uimodel.UICacheDetails
import com.benoitmanhes.domain.uimodel.UIExploreCache

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
        is UICacheDetails.Status.Found -> Colors.Marigold
        UICacheDetails.Status.Available -> availableColor
        is UICacheDetails.Status.Started -> startedColor
    }
}

@Composable
fun UIExploreCache.getPrimaryColor(): Color {
    val (availableColor, startedColor) = cache.getCachePaletteColor()
    return when (userStatus) {
        UIExploreCache.CacheUserStatus.Owned -> Colors.BlackOlive
        UIExploreCache.CacheUserStatus.Found -> Colors.Marigold
        else -> availableColor
    }
}
