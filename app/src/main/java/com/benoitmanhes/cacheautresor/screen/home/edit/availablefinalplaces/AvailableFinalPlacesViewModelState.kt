package com.benoitmanhes.cacheautresor.screen.home.edit.availablefinalplaces

import com.benoitmanhes.designsystem.molecule.button.primarybutton.PrimaryButtonState
import com.benoitmanhes.domain.model.Coordinates
import javax.annotation.concurrent.Immutable

@Immutable
data class AvailableFinalPlacesViewModelState(
    val forbiddenPlaces: List<Coordinates>,
    val bottomBarButton: PrimaryButtonState,
)
