package com.benoitmanhes.cacheautresor.screen.home.explore.cachedetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benoitmanhes.cacheautresor.BuildConfig
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.composable.alertdialog.CacheFinishAlertDialog
import com.benoitmanhes.cacheautresor.common.composable.alertdialog.ClassicAlertDialog
import com.benoitmanhes.cacheautresor.common.composable.alertdialog.CommonAlertDialogAction
import com.benoitmanhes.cacheautresor.common.composable.alertdialog.StartCoopAlertDialog
import com.benoitmanhes.cacheautresor.common.composable.alertdialog.StepCompleteAlertDialog
import com.benoitmanhes.cacheautresor.common.composable.bottombar.BottomActionBarState
import com.benoitmanhes.cacheautresor.common.composable.modalbottomsheet.ClassicModalBottomSheet
import com.benoitmanhes.cacheautresor.common.composable.modalbottomsheet.LogModalBottomSheet
import com.benoitmanhes.cacheautresor.common.composable.modalbottomsheet.StartCoopModalBottomSheet
import com.benoitmanhes.cacheautresor.common.composable.modalbottomsheet.UnlockCacheModalBottomSheet
import com.benoitmanhes.cacheautresor.common.extensions.getCacheMarker
import com.benoitmanhes.cacheautresor.common.extensions.getCacheMarkerFocus
import com.benoitmanhes.cacheautresor.common.extensions.getIcon
import com.benoitmanhes.cacheautresor.common.extensions.getTypeText
import com.benoitmanhes.cacheautresor.common.extensions.mediumFormat
import com.benoitmanhes.cacheautresor.common.extensions.toDifficultyText
import com.benoitmanhes.cacheautresor.common.extensions.toGroundText
import com.benoitmanhes.cacheautresor.common.extensions.toJaugeRate
import com.benoitmanhes.cacheautresor.common.extensions.toSizeText
import com.benoitmanhes.cacheautresor.common.maps.CacheMarkerIcon
import com.benoitmanhes.cacheautresor.common.uimodel.UIMarker
import com.benoitmanhes.cacheautresor.common.viewModel.LocationAccessViewModelDelegate
import com.benoitmanhes.cacheautresor.common.viewModel.LocationAccessViewModelDelegateImpl
import com.benoitmanhes.cacheautresor.navigation.explore.ExploreDestination
import com.benoitmanhes.cacheautresor.screen.alertdialog.AlertDialogManager
import com.benoitmanhes.cacheautresor.screen.home.explore.cachededailinstructions.section.InstructionSectionState
import com.benoitmanhes.cacheautresor.screen.home.explore.cachededailinstructions.section.NoteSectionState
import com.benoitmanhes.cacheautresor.screen.home.explore.cachedetailrecap.section.CacheTypeSectionState
import com.benoitmanhes.cacheautresor.screen.home.explore.cachedetailrecap.section.CartographerSectionState
import com.benoitmanhes.cacheautresor.screen.home.explore.cachedetails.section.CacheDetailHeaderState
import com.benoitmanhes.cacheautresor.screen.loading.LoadingManager
import com.benoitmanhes.cacheautresor.screen.modalbottomsheet.ModalBottomSheetManager
import com.benoitmanhes.cacheautresor.screen.snackbar.SnackbarManager
import com.benoitmanhes.cacheautresor.screen.snackbar.showError
import com.benoitmanhes.common.compose.extensions.textSpec
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.core.error.CTDomainError
import com.benoitmanhes.core.result.CTResult
import com.benoitmanhes.core.result.CTSuspendResult
import com.benoitmanhes.designsystem.molecule.button.fabbutton.FabButtonState
import com.benoitmanhes.designsystem.molecule.button.primarybutton.PrimaryButtonState
import com.benoitmanhes.designsystem.molecule.card.InfoCardState
import com.benoitmanhes.designsystem.molecule.jauge.CTJaugeState
import com.benoitmanhes.designsystem.molecule.row.CTRowState
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.theme.composed
import com.benoitmanhes.designsystem.utils.extensions.getColorTheme
import com.benoitmanhes.domain.model.Cache
import com.benoitmanhes.domain.model.CacheUserProgress
import com.benoitmanhes.domain.model.CacheUserStatus
import com.benoitmanhes.domain.model.Distance
import com.benoitmanhes.domain.uimodel.UICacheDetails
import com.benoitmanhes.domain.uimodel.UIStep
import com.benoitmanhes.domain.usecase.UseClueUseCase
import com.benoitmanhes.domain.usecase.cache.GetSelectedUICacheUseCase
import com.benoitmanhes.domain.usecase.cache.LogCacheUseCase
import com.benoitmanhes.domain.usecase.cache.StartCacheUseCase
import com.benoitmanhes.domain.usecase.cache.StartCoopCacheUseCase
import com.benoitmanhes.domain.usecase.cache.UnlockCacheUseCase
import com.benoitmanhes.domain.usecase.coordinates.CalculateDistanceUseCase
import com.benoitmanhes.domain.utils.DomainConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CacheDetailViewModel @Inject constructor(
    private val startCacheUseCase: StartCacheUseCase,
    private val startCoopCacheUseCase: StartCoopCacheUseCase,
    private val useClueUseCase: UseClueUseCase,
    private val loadingManager: LoadingManager,
    private val modalBottomSheetManager: ModalBottomSheetManager,
    private val alertDialogManager: AlertDialogManager,
    private val snackbarManager: SnackbarManager,
    private val logCacheUseCase: LogCacheUseCase,
    private val unlockCacheUseCase: UnlockCacheUseCase,
    private val calculateDistanceUseCase: CalculateDistanceUseCase,
    getSelectedUICacheUseCase: GetSelectedUICacheUseCase,
    locationAccessViewModelDelegateImpl: LocationAccessViewModelDelegateImpl,
    savedStateHandle: SavedStateHandle,
) : LocationAccessViewModelDelegate by locationAccessViewModelDelegateImpl,
    ViewModel() {

    private val cacheId: String = savedStateHandle.get<String>(
        ExploreDestination.CacheDetails.cacheDetailsArgument
    ).orEmpty()

    private val _uiState = MutableStateFlow<CacheDetailsViewModelState>(CacheDetailsViewModelState.Initialize)
    val uiState: StateFlow<CacheDetailsViewModelState> get() = _uiState.asStateFlow()

    private val _navigation = MutableStateFlow<CacheDetailNavigation?>(null)
    val navigation: StateFlow<CacheDetailNavigation?> get() = _navigation.asStateFlow()

    private var cache: Cache? = null

    init {
        viewModelScope.launch(Dispatchers.Default) {
            getSelectedUICacheUseCase(cacheId)
                .collect { result ->
                    cache = result.data?.cache
                    _uiState.emit(result.mapToUIState())
                }
        }
    }

    fun consumeNavigation() {
        _navigation.value = null
    }

    private fun startCache() {
        val currentCacheType = cache?.type
        when (currentCacheType) {
            is Cache.Type.Coop -> {
                modalBottomSheetManager.showModal(
                    StartCoopModalBottomSheet(
                        crewPositions = currentCacheType.crewStepsMap.keys,
                        onClickCrewPosition = { position ->
                            alertDialogManager.showDialog(
                                StartCoopAlertDialog(
                                    cacheTitle = cache!!.title,
                                    crewPosition = position,
                                    onConfirm = { startCoopCache(position) },
                                )
                            )
                        },
                    )
                )
            }

            else -> startRegularCache()
        }
    }

    private fun clickUnlockCache(cacheDetails: UICacheDetails) {
        val distance = currentLocation.value?.let {
            calculateDistanceUseCase(cacheDetails.cache.coordinates, it)
        }
        when (distance) {
            null -> ::requestLocation
            in Distance.ZERO..DomainConstants.Cache.unlockingAvailableDistance -> showUnlockCacheModal(
                cacheDetails,
                isError = false
            )

            else -> {
                alertDialogManager.showDialog(
                    ClassicAlertDialog(
                        title = TextSpec.Resources(R.string.explore_tooFarDialog_title),
                        message = TextSpec.Resources(R.string.explore_tooFarDialog_message),
                        icon = CTTheme.composed { icon.Location },
                        actions = listOf(
                            CommonAlertDialogAction.gotIt {},
                        ),
                    )
                )
            }
        }
    }

    private fun showUnlockCacheModal(
        cacheDetails: UICacheDetails,
        isError: Boolean,
    ) {
        modalBottomSheetManager.showModal(
            UnlockCacheModalBottomSheet(
                lockInstruction = cacheDetails.cache.lockDescription.textSpec(),
                onValidate = { inputLockCode ->
                    unlockCache(cacheDetails, inputLockCode)
                },
                isError = isError,
            )
        )
    }

    private fun unlockCache(cacheDetails: UICacheDetails, inputLockCode: String) {
        viewModelScope.launch {
            loadingManager.showLoading()
            val result = unlockCacheUseCase(cacheId = cacheDetails.cache.cacheId, lockCodeInput = inputLockCode)
            loadingManager.hideLoading()
            when (result) {
                is CTSuspendResult.Failure -> result.handleUnlockCacheError(cacheDetails)
                is CTSuspendResult.Success -> {
                    alertDialogManager.showDialog(
                        ClassicAlertDialog(
                            icon = cacheDetails.cache.getIcon(),
                            title = TextSpec.Resources(R.string.dialog_cacheUnlocked_title),
                            message = TextSpec.Resources(
                                R.string.dialog_cacheUnlocked_message,
                                cacheDetails.cache.getTypeText(),
                                cacheDetails.cache.title,
                            ),
                            actions = listOf(
                                CommonAlertDialogAction.letsGo {
                                    startCache()
                                }
                            )
                        )
                    )
                }
            }
        }
    }

    private fun CTSuspendResult.Failure<Unit>.handleUnlockCacheError(cacheDetails: UICacheDetails) {
        when (error?.code) {
            CTDomainError.Code.CACHE_INVALID_UNLOCK_CODE -> {
                showUnlockCacheModal(cacheDetails, isError = true)
            }

            else -> {
                snackbarManager.showError(error)
            }
        }
    }

    private fun startCoopCache(crewPosition: String) {
        viewModelScope.launch {
            loadingManager.showLoading()
            startCoopCacheUseCase(cacheId = cacheId, crewPosition = crewPosition)
            loadingManager.hideLoading()
        }
    }

    private fun startRegularCache() {
        viewModelScope.launch {
            loadingManager.showLoading()
            startCacheUseCase(cacheId)
            loadingManager.hideLoading()
        }
    }

    private fun logCache(uiStep: UIStep, codeLog: String) {
        viewModelScope.launch {
            loadingManager.showLoading()
            val result = logCacheUseCase(cacheId = cacheId, codeLog = codeLog)
            when (result) {
                is CTSuspendResult.Success -> {
                    showAlertSuccess(uiStep, result.successData)
                }

                is CTSuspendResult.Failure -> {
                    when (result.error?.code) {
                        CTDomainError.Code.CACHE_INVALID_LOG_CODE -> showLogModalBottomSheet(uiStep, isError = true)
                        else -> result.error?.let(snackbarManager::showError)
                    }
                }
            }
            loadingManager.hideLoading()
        }
    }

    private val uiStateData: CacheDetailsViewModelState.Data?
        get() = uiState.value as? CacheDetailsViewModelState.Data

    private fun CTResult<UICacheDetails>.mapToUIState(): CacheDetailsViewModelState {
        return when (this) {
            is CTResult.Success -> CacheDetailsViewModelState.Data(
                cacheColorTheme = successData.cache.getColorTheme(successData.status.cacheUserStatus),
                headerState = CacheDetailHeaderState(
                    title = TextSpec.RawString(successData.cache.title),
                    subTitle = TextSpec.RawString(successData.cache.cacheId),
                ),
                uiMarkers = successData.getStepMarkers(),
                bottomBarState = BottomActionBarState(
                    primaryButton = PrimaryButtonState(
                        text = successData.currentStep.getLogButtonLabel(),
                        onClick = {
                            showLogModalBottomSheet(uiStep = successData.currentStep, isError = false)
                        },
                    )
                ).takeIf { successData.status is UICacheDetails.Status.Started },
                fabButtonState = FabButtonState(
                    icon = CTTheme.composed { icon.Logo },
                    text = TextSpec.Resources(R.string.cacheDetail_startFab),
                    onClick = { clickUnlockCache(successData) },
                ).takeIf { successData.status == UICacheDetails.Status.Available },
                difficultyJaugeState = CTJaugeState(
                    rate = successData.cache.difficulty,
                    icon = CTTheme.composed { icon.Difficulty },
                    text = successData.cache.difficulty.toDifficultyText(),
                ),
                groundJaugeState = CTJaugeState(
                    rate = successData.cache.ground,
                    icon = CTTheme.composed { icon.Mountain },
                    text = successData.cache.ground.toGroundText()
                ),
                sizeJaugeState = CTJaugeState(
                    rate = successData.cache.size.toJaugeRate(),
                    icon = CTTheme.composed { icon.BoxSmall },
                    text = successData.cache.size.toSizeText(),
                ),
                infoCardState = getInfoCard(successData),
                typeSectionState = CacheTypeSectionState(
                    typeIcon = successData.cache.getIcon(),
                    typeText = successData.cache.getTypeText(),
                    stickerLabel = TextSpec.Resources(R.string.cacheDetail_cacheTypeSection_ongoing).takeIf {
                        successData.status is UICacheDetails.Status.Started
                    }
                ),
                cartographerSectionState = CartographerSectionState(
                    creatorName = TextSpec.RawString(successData.explorerName),
                    creationDateText = TextSpec.RawString(successData.cache.createDate.mediumFormat()),
                ),
                cacheCoordinates = successData.cache.coordinates,
                distanceText = null, // TODO distance
                description = successData.cache.description.textSpec(),
                characteristics = listOf(
                    // TODO Tags
                    CTRowState(
                        leadingIcon = CTTheme.composed { icon.Mountain },
                        text = TextSpec.RawString("Mountain")
                    ),
                    CTRowState(
                        leadingIcon = CTTheme.composed { icon.Piste },
                        text = TextSpec.RawString("Sportif")
                    ),
                    CTRowState(
                        leadingIcon = CTTheme.composed { icon.Difficulty },
                        text = TextSpec.RawString("Rapide")
                    ),
                    CTRowState(
                        leadingIcon = CTTheme.composed { icon.Mountain },
                        text = TextSpec.RawString("Mountain2")
                    ),
                    CTRowState(
                        leadingIcon = CTTheme.composed { icon.Piste },
                        text = TextSpec.RawString("Sportif2")
                    ),
                    CTRowState(
                        leadingIcon = CTTheme.composed { icon.Difficulty },
                        text = TextSpec.RawString("Rapide2")
                    ),
                ).takeIf { BuildConfig.DEBUG }.orEmpty(),
                stepInstructions = if (successData.status != UICacheDetails.Status.Available) {
                    successData.steps.map { step ->
                        InstructionSectionState(
                            title = step.getStepTitle(),
                            cacheInstructions = step.instructions,
                            clue = step.getClueSection(successData.cache.cacheId).takeIf {
                                this.successData.status !is UICacheDetails.Status.Found
                            },
                            status = step.status,
                            code = step.code.textSpec().takeIf { successData.status == UICacheDetails.Status.Owned },
                            onReport = {}, // TODO Report
                        )
                    }
                } else {
                    emptyList()
                },
                noteSectionState = NoteSectionState(
                    initialNoteValue = successData.userData.note.orEmpty().textSpec(),
                    onClickNote = {
                        _navigation.value = CacheDetailNavigation.EditNote(successData.cache.cacheId)
                    },
                    onClickInstruments = {}, // TODO Instrument
                    onClickMarker = {}, // TODO Add marker
                ).takeIf { successData.status != UICacheDetails.Status.Owned },
                initialPageIndex = successData.steps.indexOfFirst { it.status == UIStep.Status.Current } + 1,
            )

            is CTResult.Loading -> CacheDetailsViewModelState.Initialize
            is CTResult.Failure -> CacheDetailsViewModelState.Empty(TextSpec.RawString(error?.message))
        }
    }

    private fun getInfoCard(uiCacheDetails: UICacheDetails): InfoCardState? {
        val status = uiCacheDetails.status
        return when {
            status is UICacheDetails.Status.Found -> InfoCardState(
                icon = CTTheme.composed { icon.Crown },
                message = TextSpec.Resources(
                    R.string.cacheDetail_foundInfoCard_message,
                    status.foundDate.mediumFormat(),
                ),
                trailingText = status.pts?.let { pts ->
                    TextSpec.Resources(R.string.cacheDetail_foundInfoCard_points, pts)
                },
            )

            !uiCacheDetails.cache.discovered -> {
                InfoCardState(
                    icon = CTTheme.composed { icon.Ensign },
                    message = TextSpec.Resources(R.string.cacheDetail_neverFoundInfoCard_message),
                )
            }

            else -> null
        }
    }

    private fun updateData(block: (CacheDetailsViewModelState.Data) -> CacheDetailsViewModelState) {
        val value = _uiState.value
        _uiState.value = when (value) {
            is CacheDetailsViewModelState.Data -> block(value)
            is CacheDetailsViewModelState.Empty -> value
            is CacheDetailsViewModelState.Initialize -> value
        }
    }

    private fun UIStep.getClueSection(cacheId: String): InstructionSectionState.Clue? = clue?.let { _clue ->
        if (showClue) {
            InstructionSectionState.Clue.Revealed(_clue.textSpec())
        } else {
            InstructionSectionState.Clue.Unrevealed {
                modalBottomSheetManager.showModal(
                    ClassicModalBottomSheet(
                        icon = CTTheme.composed { icon.Parchment },
                        title = TextSpec.Resources(R.string.cacheDetail_clueModal_title),
                        message = TextSpec.Resources(R.string.cacheDetail_clueModal_message),
                        cancelAction = PrimaryButtonState(
                            text = TextSpec.Resources(R.string.common_finallyNo),
                            onClick = {},
                        ),
                        confirmAction = PrimaryButtonState(
                            text = TextSpec.Resources(R.string.cacheDetail_clueModal_confirmMessage),
                            onClick = {
                                revealClue(stepId = stepId, cacheId = cacheId)
                            }
                        )
                    )
                )
            }
        }
    }

    private fun revealClue(stepId: String, cacheId: String) {
        viewModelScope.launch {
            loadingManager.showLoading()
            useClueUseCase(cacheStepId = stepId, cacheId = cacheId)
            loadingManager.hideLoading()
        }
    }

    private fun UIStep.getStepTitle(): TextSpec {
        val cacheType = type
        return when (cacheType) {
            is UIStep.Type.Classical -> TextSpec.Resources(R.string.cacheDetail_instructionsSection_title_classical)
            is UIStep.Type.Mystery -> TextSpec.Resources(R.string.cacheDetail_instructionsSection_title_mystery)
            is UIStep.Type.Final -> TextSpec.Resources(R.string.cacheDetail_instructionsSection_title_final)
            is UIStep.Type.Piste -> TextSpec.Resources(
                R.string.cacheDetail_instructionsSection_title_piste,
                cacheType.index + 1
            )

            is UIStep.Type.Coop -> TextSpec.Resources(
                R.string.cacheDetail_instructionsSection_title_coop,
                cacheType.crewPosition,
                cacheType.index + 1,
            )
        }
    }

    private fun showLogModalBottomSheet(uiStep: UIStep, isError: Boolean) {
        modalBottomSheetManager.showModal(
            LogModalBottomSheet(
                message = uiStep.getLogBottomSheetMessage(),
                errorMessage = uiStep.getLogBottomSheetError(),
                isError = isError,
                onValidate = { logCache(uiStep, codeLog = it) },
            )
        )
    }

    private fun UIStep.isLast(): Boolean =
        type in setOf(UIStep.Type.Classical, UIStep.Type.Final)

    private fun UIStep.getLogButtonLabel(): TextSpec =
        if (isLast()) {
            TextSpec.Resources(R.string.cacheDetail_logButton_validate)
        } else {
            TextSpec.Resources(R.string.cacheDetail_logButton_answer)
        }

    private fun UIStep.getLogBottomSheetMessage(): TextSpec =
        if (isLast()) {
            TextSpec.Resources(R.string.logModal_message_final)
        } else {
            TextSpec.Resources(R.string.logModal_message_step)
        }

    private fun UIStep.getLogBottomSheetError(): TextSpec =
        if (isLast()) {
            TextSpec.Resources(R.string.logModal_error_final)
        } else {
            TextSpec.Resources(R.string.logModal_error_step)
        }

    private fun showAlertSuccess(
        uiStep: UIStep,
        userProgress: CacheUserProgress,
    ) {
        val alertDialog = if (userProgress.foundDate != null) {
            CacheFinishAlertDialog(
                cacheName = cache?.title.orEmpty(),
                ptsWin = TextSpec.Resources(R.string.cacheDetail_cacheFinish_alertDialog_pts, userProgress.ptsWin ?: 0),
            )
        } else {
            StepCompleteAlertDialog(stepName = uiStep.getStepTitle())
        }
        alertDialogManager.showDialog(alertDialog)
    }

    private fun UICacheDetails.getStepMarkers(): List<UIMarker> = when (status) {
        is UICacheDetails.Status.Available -> listOf(
            UIMarker(
                coordinates = cache.coordinates,
                iconMarker = cache.getCacheMarker(CacheUserStatus.Available),
                isSelected = false,
            )
        )

        is UICacheDetails.Status.Started -> {
            steps
                .filterNot {
                    it.status == UIStep.Status.Lock && it.type == UIStep.Type.Final
                }
                .mapIndexed { index, uiStep ->
                    uiStep.toMarker(cache, status.cacheUserStatus, index + 1)
                }
        }

        is UICacheDetails.Status.Found -> steps.mapIndexed { index, uiStep ->
            uiStep.toMarker(cache, status.cacheUserStatus, index + 1)
        }

        is UICacheDetails.Status.Owned ->
            steps
                .mapIndexed { index, uiStep ->
                    UIMarker(
                        coordinates = uiStep.coordinates,
                        isSelected = false,
                        iconMarker = if (uiStep.type is UIStep.Type.Final || uiStep.type is UIStep.Type.Classical) {
                            cache.getCacheMarker(CacheUserStatus.Owned)
                        } else {
                            CacheMarkerIcon.Empty(
                                (index + 1).toString(),
                                cache.getColorTheme(CacheUserStatus.Owned).dayColorScheme.primary,
                            )
                        },
                    )
                }
    }

    private fun UIStep.toMarker(cache: Cache, cacheUserStatus: CacheUserStatus, index: Int): UIMarker {
        val stepUserStatus = cacheUserStatus.takeIf { status != UIStep.Status.Lock }
            ?: CacheUserStatus.Hidden
        return UIMarker(
            coordinates = coordinates,
            isSelected = false,
            iconMarker = getIconMarker(cache, index, stepUserStatus),
        )
    }

    private fun UIStep.getIconMarker(cache: Cache, index: Int, stepUserStatus: CacheUserStatus): CacheMarkerIcon {
        val tint = cache.getColorTheme(stepUserStatus).dayColorScheme.primary
        return if (type is UIStep.Type.Final || type is UIStep.Type.Classical) {
            if (status == UIStep.Status.Current) {
                cache.getCacheMarkerFocus()
            } else {
                cache.getCacheMarker(stepUserStatus)
            }
        } else {
            when (status) {
                UIStep.Status.Lock -> CacheMarkerIcon.Empty(index.toString(), tint)
                UIStep.Status.Current -> CacheMarkerIcon.Current(index.toString(), tint)
                UIStep.Status.Done -> CacheMarkerIcon.Done(tint)
            }
        }
    }
}
