package com.benoitmanhes.cacheautresor.navigation.creation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.benoitmanhes.cacheautresor.screen.home.edit.availablefinalplaces.AvailableFinalPlacesRoute
import com.benoitmanhes.cacheautresor.screen.home.edit.editdraftcache.EditDraftCacheRoute
import com.benoitmanhes.cacheautresor.screen.home.edit.editdraftstep.EditDraftStepRoute
import com.benoitmanhes.cacheautresor.screen.home.edit.editinstructions.EditInstructionsRoute
import com.benoitmanhes.cacheautresor.screen.home.edit.pickdifficulty.PickDifficultyRoute
import com.benoitmanhes.cacheautresor.screen.home.edit.pickground.PickGroundRoute
import com.benoitmanhes.cacheautresor.screen.home.edit.pickinitcoordinates.PickInitCoordinatesRoute
import com.benoitmanhes.cacheautresor.screen.home.edit.pickname.PickDraftCacheNameRoute
import com.benoitmanhes.cacheautresor.screen.home.edit.pickstepclue.PickStepClueRoute
import com.benoitmanhes.cacheautresor.screen.home.edit.pickstepcoordinates.PickStepCoordinatesRoute
import com.benoitmanhes.cacheautresor.screen.home.edit.picktype.PickTypeDraftCacheScreen
import com.benoitmanhes.cacheautresor.screen.home.edit.pickvalidationcode.PickStepValidationCodeRoute

fun NavGraphBuilder.creationNavGraph(
    navController: NavHostController,
) {
    composable(EditCacheDestination.AvailableFinalPlaces.route) {
        AvailableFinalPlacesRoute(
            navigateBack = navController::popBackStack,
            navigateDraftCacheDetail = { draftCacheId ->
                navController.navigate(
                    route = EditCacheDestination.EditDraftCache.getRoute(draftCacheId),
                ) {
                    popUpTo(EditCacheDestination.AvailableFinalPlaces.route) { inclusive = true }
                }
            },
        )
    }

    composable(EditCacheDestination.EditDraftCache.route) {
        EditDraftCacheRoute(
            navigateBack = navController::popBackStack,
            navigateToPickName = { draftCacheId ->
                navController.navigate(EditCacheDestination.PickNameDraftCache.getRoute(draftCacheId))
            },
            navigateToPickType = { draftCacheId ->
                navController.navigate(EditCacheDestination.PickTypeDraftCache.getRoute(draftCacheId))
            },
            navigateToPickInitCoordinates = { draftCacheId ->
                navController.navigate(EditCacheDestination.PickInitCoordinatesDraftCache.getRoute(draftCacheId))
            },
            navigateToEditDraftStep = { draftCacheId, draftStepId ->
                navController.navigate(
                    EditCacheDestination.EditDraftStep.getRoute(
                        draftCacheId = draftCacheId,
                        draftStepId = draftStepId,
                    )
                )
            },
            navigateToPickDifficulty = { draftCacheId ->
                navController.navigate(
                    EditCacheDestination.PickDifficulty.getRoute(draftCacheId)
                )
            },
            navigateToPickGround = { draftCacheId ->
                navController.navigate(
                    EditCacheDestination.PickGround.getRoute(draftCacheId)
                )
            },
            navigateToPickSize = {},
        )
    }

    composable(EditCacheDestination.PickNameDraftCache.route) {
        PickDraftCacheNameRoute(
            navigateBack = navController::popBackStack,
        )
    }

    composable(EditCacheDestination.PickTypeDraftCache.route) {
        PickTypeDraftCacheScreen(
            navigateBack = navController::popBackStack,
        )
    }

    composable(EditCacheDestination.PickInitCoordinatesDraftCache.route) {
        PickInitCoordinatesRoute(
            navigateBack = navController::popBackStack,
        )
    }

    composable(EditCacheDestination.EditDraftStep.route) {
        EditDraftStepRoute(
            navigateBack = navController::popBackStack,
            navigateToPickStepCoordinates = { draftCacheId, draftStepId ->
                navController.navigate(
                    EditCacheDestination.PickStepCoordinates.getRoute(
                        draftCacheId = draftCacheId,
                        draftStepId = draftStepId,
                    )
                )
            },
            navigateToEditInstructions = { draftCacheId, draftStepId ->
                navController.navigate(
                    EditCacheDestination.EditInstructions.getRoute(
                        draftCacheId = draftCacheId,
                        draftStepId = draftStepId,
                    )
                )
            },
            navigateToPickClue = { draftCacheId, draftStepId ->
                navController.navigate(
                    EditCacheDestination.PickStepClue.getRoute(
                        draftCacheId = draftCacheId,
                        draftStepId = draftStepId,
                    )
                )
            },
            navigateToPickValidationCode = { draftCacheId, draftStepId ->
                navController.navigate(
                    EditCacheDestination.PickStepValidationCode.getRoute(
                        draftCacheId = draftCacheId,
                        draftStepId = draftStepId,
                    )
                )
            },
        )
    }

    composable(EditCacheDestination.PickStepCoordinates.route) {
        PickStepCoordinatesRoute(navigateBack = navController::popBackStack)
    }

    composable(EditCacheDestination.EditInstructions.route) {
        EditInstructionsRoute(navigateBack = navController::popBackStack)
    }

    composable(EditCacheDestination.PickStepClue.route) {
        PickStepClueRoute(navigateBack = navController::popBackStack)
    }

    composable(EditCacheDestination.PickStepValidationCode.route) {
        PickStepValidationCodeRoute(navigateBack = navController::popBackStack)
    }

    composable(EditCacheDestination.PickDifficulty.route) {
        PickDifficultyRoute(navigateBack = navController::popBackStack)
    }

    composable(EditCacheDestination.PickGround.route) {
        PickGroundRoute(navigateBack = navController::popBackStack)
    }
}
