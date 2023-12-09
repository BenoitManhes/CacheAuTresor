package com.benoitmanhes.designsystem.utils.extensions

import androidx.compose.ui.graphics.Color
import com.benoitmanhes.designsystem.res.Colors
import com.benoitmanhes.domain.model.Cache
import com.benoitmanhes.domain.model.CacheUserStatus

private fun Cache.getCachePaletteColor(): Pair<Color, Color> = when (type) {
    is Cache.Type.Classical -> Colors.LaSalleGreen to Colors.MiddleGreen
    is Cache.Type.Coop -> Colors.LightSeaGreen to Colors.PewterBlue
    is Cache.Type.Mystery -> Colors.MetallicViolet to Colors.FrenchLilac
    is Cache.Type.Piste -> Colors.WatermelonRed to Colors.NewYorkPink
}

fun Cache.getCacheColor(cacheUserStatus: CacheUserStatus): Color {
    val (availableColor, startedColor) = getCachePaletteColor()
    return when (cacheUserStatus) {
        CacheUserStatus.Owned -> Colors.SpaceCadet
        CacheUserStatus.Found -> Colors.Marigold
        CacheUserStatus.Started -> startedColor
        CacheUserStatus.Available -> availableColor
        CacheUserStatus.Locked -> Colors.QuickSilver
        CacheUserStatus.Hidden -> Colors.QuickSilver
    }
}
