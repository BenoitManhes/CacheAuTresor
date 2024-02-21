package com.benoitmanhes.cacheautresor.screen.home.edit.pickinitcoordinates

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
import com.benoitmanhes.cacheautresor.common.uimodel.UIMarker
import com.benoitmanhes.cacheautresor.navigation.creation.EditCacheDestination
import com.benoitmanhes.cacheautresor.screen.alertdialog.AlertDialogManager
import com.benoitmanhes.cacheautresor.screen.loading.LoadingManager
import com.benoitmanhes.cacheautresor.screen.modalbottomsheet.ModalBottomSheetManager
import com.benoitmanhes.cacheautresor.screen.snackbar.SnackbarManager
import com.benoitmanhes.cacheautresor.screen.snackbar.showError
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.core.error.CTDomainError
import com.benoitmanhes.core.result.CTResult
import com.benoitmanhes.core.result.CTSuspendResult
import com.benoitmanhes.designsystem.molecule.button.primarybutton.ButtonStatus
import com.benoitmanhes.designsystem.molecule.button.primarybutton.PrimaryButtonState
import com.benoitmanhes.designsystem.molecule.button.primarybutton.PrimaryButtonType
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.domain.model.Coordinates
import com.benoitmanhes.domain.model.DraftCache
import com.benoitmanhes.domain.usecase.cache.GetForbiddenInitPlacesUseCase
import com.benoitmanhes.domain.usecase.coordinates.ParseStringToCoordinates
import com.benoitmanhes.domain.usecase.draftcache.ChangeInitCoordinatesDraftCacheUseCase
import com.benoitmanhes.domain.usecase.draftcache.GetDraftCacheUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PickInitCoordinatesViewModel @Inject constructor(
    private val modalBottomSheetManager: ModalBottomSheetManager,
    private val snackbarManager: SnackbarManager,
    private val loadingManager: LoadingManager,
    private val alertDialogManager: AlertDialogManager,
    private val getDraftCacheUseCase: GetDraftCacheUseCase,
    private val parseStringToCoordinates: ParseStringToCoordinates,
    private val changeInitCoordinatesDraftCacheUseCase: ChangeInitCoordinatesDraftCacheUseCase,
    getForbiddenInitPlacesUseCase: GetForbiddenInitPlacesUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val draftCacheId: String = savedStateHandle.get<String>(
        EditCacheDestination.PickNameDraftCache.arg
    ).orEmpty()

    private var initialCoordinates: Coordinates? = null
    private var draftCacheType: DraftCache.Type? = null

    val uiState: StateFlow<PickInitCoordinatesViewModelState> get() = _uiState.asStateFlow()
    private val _uiState = MutableStateFlow<PickInitCoordinatesViewModelState>(
        PickInitCoordinatesViewModelState(
            forbiddenPlaces = emptyList(),
            bottomActionBar = BottomActionBarState(
                title = TextSpec.Resources(R.string.pickInitCoordinates_bottomBar_title),
                message = TextSpec.Resources(R.string.pickInitCoordinates_bottomBar_description),
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
            val draftCache = getDraftCacheUseCase(draftCacheId)
            initialCoordinates = draftCache?.coordinates
            draftCacheType = draftCache?.type

            val result = getForbiddenInitPlacesUseCase()
            when (result) {
                is CTSuspendResult.Success -> _uiState.value = _uiState.value.copy(
                    forbiddenPlaces = result.successData,
                )

                is CTSuspendResult.Failure -> {
                    snackbarManager.showError(result.error)
                }
            }
            updateCoordinates(draftCache?.coordinates)
        }
    }

    private fun updateCoordinatesFormat() {
        _uiState.value = uiState.value.copy(
            currentCoordinates = uiState.value.currentCoordinates.copy(
                format = Coordinates.Format.next(uiState.value.currentCoordinates.format),
            )
        )
    }

    fun updateCoordinates(coordinates: Coordinates?) {
        val uiMarker = coordinates?.let {
            UIMarker(
                coordinates = coordinates,
                isSelected = true,
                iconMarker = draftCacheType.getMarkerIcon()
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
            changeInitCoordinatesDraftCacheUseCase(
                draftCacheId = draftCacheId,
                newCoordinates = uiState.value.currentCoordinates.coordinates,
            ).collect { result ->
                loadingManager.handleLoadingFromResult(result)
                when (result) {
                    is CTResult.Failure -> {
                        if (result.error?.code == CTDomainError.Code.INITIAL_COORDINATES_INVALID) {
                            alertDialogManager.showDialog(
                                ClassicAlertDialog(
                                    title = TextSpec.Resources(R.string.pickInitCoordinates_alertArea_title),
                                    message = TextSpec.Resources(R.string.pickInitCoordinates_alertArea_message),
                                    icon = { CTTheme.icon.LocationWrong },
                                    actions = listOf(CommonAlertDialogAction.gotIt {}),
                                )
                            )
                        } else {
                            snackbarManager.showError(result.error)
                        }
                    }

                    is CTResult.Success -> _navigateBack.value = true
                    is CTResult.Loading -> {} /* no-op */
                }
            }
        }
    }

    fun editCoordinatesManually() {
        showPickCoordinatesModal(error = false)
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

    private fun parseCoordinatesPickedManually(rawCoordinates: String, closeModal: () -> Unit) {
        viewModelScope.launch {
            val coordinatesParsed = parseStringToCoordinates(rawCoordinates)
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
}
