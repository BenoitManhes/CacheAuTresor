package com.benoitmanhes.cacheautresor.screen.home.edit.availablefinalplaces

import androidx.compose.runtime.Immutable
import com.benoitmanhes.designsystem.molecule.button.primarybutton.PrimaryButtonState
import com.benoitmanhes.domain.model.Coordinates

@Immutable
data class AvailableFinalPlacesViewModelState(
    val forbiddenPlaces: List<Coordinates>,
    val bottomBarButton: PrimaryButtonState,
)
