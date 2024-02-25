package com.benoitmanhes.cacheautresor.screen.home.edit.pickstepcoordinates

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.composable.alertdialog.ClassicAlertDialog
import com.benoitmanhes.cacheautresor.common.composable.alertdialog.CommonAlertDialogAction
import com.benoitmanhes.cacheautresor.common.composable.bottombar.BottomActionBarState
import com.benoitmanhes.cacheautresor.common.composable.card.CoordinatesCardState
import com.benoitmanhes.cacheautresor.common.composable.modalbottomsheet.PickCoordinatesModalBottomSheet
import com.benoitmanhes.cacheautresor.common.extensions.getMarkerIcon
import com.benoitmanhes.cacheautresor.common.extensions.toCacheType
import com.benoitmanhes.cacheautresor.common.maps.CacheMarkerIcon
import com.benoitmanhes.cacheautresor.common.uimodel.UIMarker
import com.benoitmanhes.cacheautresor.navigation.creation.EditCacheDestination
import com.benoitmanhes.cacheautresor.screen.alertdialog.AlertDialogManager
import com.benoitmanhes.cacheautresor.screen.loading.LoadingManager
import com.benoitmanhes.cacheautresor.screen.modalbottomsheet.ModalBottomSheetManager
import com.benoitmanhes.cacheautresor.screen.snackbar.SnackbarManager
import com.benoitmanhes.cacheautresor.screen.snackbar.showError
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.common.kotlin.safeLet
import com.benoitmanhes.core.error.CTDomainError
import com.benoitmanhes.core.result.CTResult
import com.benoitmanhes.designsystem.molecule.button.primarybutton.ButtonStatus
import com.benoitmanhes.designsystem.molecule.button.primarybutton.PrimaryButtonState
import com.benoitmanhes.designsystem.molecule.button.primarybutton.PrimaryButtonType
import com.benoitmanhes.designsystem.theme.CTColorTheme
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.extensions.getTypeColorTheme
import com.benoitmanhes.domain.model.Coordinates
import com.benoitmanhes.domain.model.DraftCache
import com.benoitmanhes.domain.uimodel.UIDraftStepDetail
import com.benoitmanhes.domain.usecase.cache.GetForbiddenFinalPlacesUseCase
import com.benoitmanhes.domain.usecase.coordinates.ParseStringToCoordinatesUseCase
import com.benoitmanhes.domain.usecase.draftcache.ChangeStepCoordinatesUseCase
import com.benoitmanhes.domain.usecase.draftcache.GetAllDraftStepDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PickStepCoordinatesViewModel @Inject constructor(
    private val modalBottomSheetManager: ModalBottomSheetManager,
    private val parseStringToCoordinatesUseCase: ParseStringToCoordinatesUseCase,
    private val getAllDraftStepDetailUseCase: GetAllDraftStepDetailUseCase,
    private val changeStepCoordinatesUseCase: ChangeStepCoordinatesUseCase,
    private val loadingManager: LoadingManager,
    private val snackbarManager: SnackbarManager,
    private val alertDialogManager: AlertDialogManager,
    getForbiddenFinalPlacesUseCase: GetForbiddenFinalPlacesUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val draftCacheId: String = savedStateHandle.get<String>(
        EditCacheDestination.PickStepCoordinates.draftCacheIdArg
    ).orEmpty()
    private val draftStepId: String = savedStateHandle.get<String>(
        EditCacheDestination.PickStepCoordinates.draftStepIdArg
    ).orEmpty()

    private var currentStepDetail: UIDraftStepDetail? = null
    private val initialCoordinates: Coordinates?
        get() = currentStepDetail?.draftStep?.coordinates

    val uiState: StateFlow<PickStepCoordinatesViewModelState> get() = _uiState.asStateFlow()
    private val _uiState = MutableStateFlow(
        PickStepCoordinatesViewModelState(
            bottomActionBar = BottomActionBarState(
                message = TextSpec.Resources(R.string.pickCoordinates_bottomBar_description),
                primaryButton = saveButton.copy(status = ButtonStatus.DISABLE),
            ),
            currentCoordinates = CoordinatesCardState(
                coordinates = null,
                format = Coordinates.Format.DM,
                onClick = ::updateCoordinatesFormat,
            ),
        )
    )

    private val _navigateBack = MutableStateFlow<Boolean?>(null)
    val navigateBack: StateFlow<Boolean?> get() = _navigateBack.asStateFlow()

    init {
        viewModelScope.launch {
            val allStepsDetail = getAllDraftStepDetailUseCase(draftCacheId = draftCacheId)
            currentStepDetail = allStepsDetail.first { it.draftStep.stepDraftId == draftStepId }
            currentStepDetail?.let { currentStep ->
                val allStepMarkers = allStepsDetail
                    .filter { it.draftStep.stepDraftId != draftStepId }
                    .mapNotNull { stepDetail ->
                        stepDetail.getUIMarker()
                    }

                _uiState.value = uiState.value.copy(
                    bottomActionBar = uiState.value.bottomActionBar.copy(
                        title = getBottomBarTitle(currentStep),
                    ),
                    otherMarkers = allStepMarkers + getInitialStepMarkers(currentStep.draftCache),
                )
                currentStep.draftStep.coordinates?.let(::updateCoordinates)

                // Forbidden zones
                val forbiddenPlaces = if (currentStepDetail?.type is UIDraftStepDetail.Type.Final) {
                    getForbiddenFinalPlacesUseCase().data ?: emptyList()
                } else {
                    emptyList()
                }
                _uiState.value = uiState.value.copy(
                    forbiddenPlaces = forbiddenPlaces,
                )
            }
        }
    }

    fun editCoordinatesManually() {
        showPickCoordinatesModal(error = false)
    }

    fun updateCoordinates(coordinates: Coordinates?) {
        val uiMarker = safeLet(currentStepDetail, coordinates) { stepDetail, _coordinates ->
            UIMarker(
                coordinates = _coordinates,
                isSelected = true,
                iconMarker = stepDetail.getIconMarker(),
            )
        }
        _uiState.value = uiState.value.copy(
            currentCoordinates = uiState.value.currentCoordinates.copy(coordinates = coordinates),
            bottomActionBar = uiState.value.bottomActionBar.copy(
                primaryButton = saveButton.copy(
                    status = if (coordinates != null && coordinates != initialCoordinates) {
                        ButtonStatus.ENABLE
                    } else {
                        ButtonStatus.DISABLE
                    }
                ),
                secondaryButton = reinitializeButton.copy(
                    status = if (coordinates != initialCoordinates) {
                        ButtonStatus.ENABLE
                    } else {
                        ButtonStatus.DISABLE
                    }
                ),
            ),
            uiMarker = uiMarker,
        )
    }

    private fun saveCoordinates() {
        viewModelScope.launch {
            changeStepCoordinatesUseCase(
                draftCacheId = draftCacheId,
                draftStepId = draftStepId,
                newCoordinates = uiState.value.currentCoordinates.coordinates,
            ).collect { result ->
                loadingManager.handleLoadingFromResult(result)
                when (result) {
                    is CTResult.Failure -> {
                        if (result.error?.code == CTDomainError.Code.INVALID_COORDINATES) {
                            alertDialogManager.showDialog(
                                ClassicAlertDialog(
                                    title = TextSpec.Resources(R.string.pickCoordinates_alertArea_title),
                                    message = TextSpec.Resources(R.string.pickCoordinates_alertArea_message),
                                    icon = { CTTheme.icon.LocationWrong },
                                    actions = listOf(CommonAlertDialogAction.gotIt {}),
                                )
                            )
                        } else {
                            snackbarManager.showError(result.error)
                        }
                        cancel()
                    }

                    is CTResult.Success -> {
                        _navigateBack.value = true
                        cancel()
                    }

                    is CTResult.Loading -> {} /* no-op */
                }
            }
        }
    }

    private fun showPickCoordinatesModal(error: Boolean) {
        modalBottomSheetManager.showModal(
            PickCoordinatesModalBottomSheet(
                onValidate = ::parseCoordinatesPickedManually,
                initialText = uiState.value.currentCoordinates.text,
                errorText = TextSpec.Resources(R.string.common_invalidFormat).takeIf { error },
            )
        )
    }

    private fun updateCoordinatesFormat() {
        _uiState.value = uiState.value.copy(
            currentCoordinates = uiState.value.currentCoordinates.copy(
                format = Coordinates.Format.next(uiState.value.currentCoordinates.format),
            )
        )
    }

    private fun parseCoordinatesPickedManually(rawCoordinates: String, closeModal: () -> Unit) {
        viewModelScope.launch {
            val coordinatesParsed = parseStringToCoordinatesUseCase(rawCoordinates)
            if (coordinatesParsed == null) {
                showPickCoordinatesModal(error = true)
            } else {
                closeModal()
                updateCoordinates(coordinatesParsed)
            }
        }
    }

    private val reinitializeButton: PrimaryButtonState
        get() = PrimaryButtonState(
            text = TextSpec.Resources(R.string.common_reinitialize),
            type = PrimaryButtonType.OUTLINED,
            onClick = { updateCoordinates(initialCoordinates) }
        )

    private val saveButton: PrimaryButtonState
        get() = PrimaryButtonState(
            text = TextSpec.Resources(R.string.common_save),
            type = PrimaryButtonType.COLORED,
            onClick = ::saveCoordinates,
        )

    fun consumeNavigation() {
        _navigateBack.value = null
    }

    private fun getBottomBarTitle(uiCacheStep: UIDraftStepDetail): TextSpec? {
        val stepType = uiCacheStep.type
        return when (stepType) {
            UIDraftStepDetail.Type.Classical,
            UIDraftStepDetail.Type.MysteryEnigma,
            -> null

            UIDraftStepDetail.Type.Final -> TextSpec.Resources(R.string.pickStepCoordinates_bottomBar_title_final)
            is UIDraftStepDetail.Type.Piste -> {
                TextSpec.Resources(R.string.pickStepCoordinates_bottomBar_title_intermediary, stepType.index + 1)
            }

            is UIDraftStepDetail.Type.Coop -> {
                TextSpec.Resources(R.string.pickStepCoordinates_bottomBar_title_intermediary, stepType.index + 1)
            }
        }
    }

    private fun UIDraftStepDetail.getUIMarker(): UIMarker? =
        draftStep.coordinates?.let { coordinates ->
            UIMarker(
                coordinates = coordinates,
                iconMarker = this.getIconMarker(),
                isSelected = false,
            )
        }

    private fun UIDraftStepDetail.getIconMarker(): CacheMarkerIcon {
        val typeValue = type
        return when (typeValue) {
            UIDraftStepDetail.Type.Classical -> CacheMarkerIcon.Classical(CTColorTheme.Classical.dayColorScheme.primary)
            UIDraftStepDetail.Type.MysteryEnigma -> CacheMarkerIcon.Mystery(CTColorTheme.Mystery.dayColorScheme.primary)
            is UIDraftStepDetail.Type.Piste -> CacheMarkerIcon.Empty(
                color = CTColorTheme.Piste.dayColorScheme.primary,
                iconText = (typeValue.index + 1).toString(),
            )

            is UIDraftStepDetail.Type.Coop -> CacheMarkerIcon.Empty(
                color = CTColorTheme.Coop.dayColorScheme.primary,
                iconText = (typeValue.index + 1).toString(),
            )

            is UIDraftStepDetail.Type.Final -> CacheMarkerIcon.Owner(
                color = (draftCache.type?.toCacheType()?.getTypeColorTheme() ?: CTColorTheme.Cartography).dayColorScheme.primary
            )
        }
    }

    private fun getInitialStepMarkers(draftCache: DraftCache): List<UIMarker> {
        val uiMarker = draftCache.coordinates?.let { coordinates ->
            draftCache.type?.let { type ->
                UIMarker(
                    coordinates = coordinates,
                    iconMarker = type.getMarkerIcon(),
                    isSelected = false,
                )
            }
        }.takeIf {
            draftCache.type is DraftCache.Type.Coop || draftCache.type is DraftCache.Type.Piste
        }
        return listOfNotNull(uiMarker)
    }
}
