package com.benoitmanhes.cacheautresor.screen.home.edit.editdraftstep

import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.extensions.toCacheType
import com.benoitmanhes.cacheautresor.common.maps.CacheMarkerIcon
import com.benoitmanhes.cacheautresor.common.uimodel.UIMarker
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.theme.CTColorTheme
import com.benoitmanhes.designsystem.utils.extensions.getTypeColorTheme
import com.benoitmanhes.domain.uimodel.UIDraftStepDetail

internal fun EditDraftStepViewModel.getStepName(stepType: UIDraftStepDetail.Type): TextSpec =
    when (stepType) {
        UIDraftStepDetail.Type.Classical -> TextSpec.Resources(R.string.cacheEditor_stepsClassical_label)
        UIDraftStepDetail.Type.MysteryEnigma -> TextSpec.Resources(R.string.cacheEditor_stepsMystery_label)
        UIDraftStepDetail.Type.Final -> TextSpec.Resources(R.string.cacheEditor_stepsFinal_label)
        is UIDraftStepDetail.Type.Piste -> TextSpec.Resources(R.string.cacheEditor_stepsPiste_label, stepType.index + 1)
        is UIDraftStepDetail.Type.Coop -> TextSpec.Resources(
            R.string.stepEditor_stepsCoop_title,
            stepType.crewRef,
            stepType.index + 1,
        )
    }

internal fun EditDraftStepViewModel.getStepMarker(
    stepDetail: UIDraftStepDetail,
): UIMarker? = stepDetail.draftStep.coordinates?.let { coordinates ->
    val type = stepDetail.type
    val colorTheme = stepDetail.draftCache.type?.toCacheType()?.getTypeColorTheme() ?: CTColorTheme.Cartography
    val markerColor = colorTheme.dayColorScheme.primary
    val iconMarker = when (type) {
        UIDraftStepDetail.Type.Classical -> CacheMarkerIcon.Classical(markerColor)
        UIDraftStepDetail.Type.MysteryEnigma -> CacheMarkerIcon.Mystery(markerColor)

        is UIDraftStepDetail.Type.Piste -> CacheMarkerIcon.Empty(
            color = markerColor,
            iconText = (type.index + 1).toString(),
        )

        is UIDraftStepDetail.Type.Coop -> CacheMarkerIcon.Empty(
            color = markerColor,
            iconText = (type.index + 1).toString(),
        )

        UIDraftStepDetail.Type.Final -> CacheMarkerIcon.Owner(markerColor)
    }
    UIMarker(
        coordinates = coordinates,
        iconMarker = iconMarker,
        isSelected = false,
    )
}
