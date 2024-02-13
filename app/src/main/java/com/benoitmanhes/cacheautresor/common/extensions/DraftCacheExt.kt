package com.benoitmanhes.cacheautresor.common.extensions

import com.benoitmanhes.cacheautresor.common.maps.CacheMarkerIcon
import com.benoitmanhes.designsystem.theme.CTColorTheme
import com.benoitmanhes.designsystem.utils.extensions.getTypeColorTheme
import com.benoitmanhes.domain.model.Cache
import com.benoitmanhes.domain.model.DraftCache

fun DraftCache.Type.toCacheType(): Cache.Type = when (this) {
    is DraftCache.Type.Classical -> Cache.Type.Classical
    is DraftCache.Type.Piste -> Cache.Type.Piste(intermediateDraftStepIds)
    is DraftCache.Type.Mystery -> Cache.Type.Mystery(enigmaDraftStepId ?: "")
    is DraftCache.Type.Coop -> Cache.Type.Coop(crewDraftStepsMap)
}

fun DraftCache.Type?.getMarkerIcon(): CacheMarkerIcon {
    val color = (this?.toCacheType()?.getTypeColorTheme() ?: CTColorTheme.Cartography).dayColorScheme.primary
    return this?.toCacheType()?.getCacheMarker(color) ?: CacheMarkerIcon.Owner(color)
}
