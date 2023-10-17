package com.benoitmanhes.cacheautresor.screen.home.explore.cachedetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.composable.alertdialog.StartCoopAlertDialog
import com.benoitmanhes.cacheautresor.common.composable.bottombar.BottomActionBarState
import com.benoitmanhes.cacheautresor.common.composable.modalbottomsheet.LogModalBottomSheet
import com.benoitmanhes.cacheautresor.common.composable.modalbottomsheet.StartCoopModalBottomSheet
import com.benoitmanhes.cacheautresor.common.extensions.getCacheMarker
import com.benoitmanhes.cacheautresor.common.extensions.getIcon
import com.benoitmanhes.cacheautresor.common.extensions.getTypeText
import com.benoitmanhes.cacheautresor.common.extensions.mediumFormat
import com.benoitmanhes.cacheautresor.common.extensions.textSpec
import com.benoitmanhes.cacheautresor.common.extensions.toDifficultyText
import com.benoitmanhes.cacheautresor.common.extensions.toGroundText
import com.benoitmanhes.cacheautresor.common.extensions.toJaugeRate
import com.benoitmanhes.cacheautresor.common.extensions.toSizeText
import com.benoitmanhes.cacheautresor.common.uimodel.UIMarker
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
import com.benoitmanhes.core.error.CTDomainError
import com.benoitmanhes.core.result.CTResult
import com.benoitmanhes.core.result.CTSuspendResult
import com.benoitmanhes.designsystem.molecule.button.fabbutton.FabButtonState
import com.benoitmanhes.designsystem.molecule.button.primarybutton.PrimaryButtonState
import com.benoitmanhes.designsystem.molecule.card.InfoCardState
import com.benoitmanhes.designsystem.molecule.jauge.CTJaugeState
import com.benoitmanhes.designsystem.molecule.row.CTRowState
import com.benoitmanhes.designsystem.molecule.selector.SelectorItem
import com.benoitmanhes.designsystem.molecule.selector.TabSelectorState
import com.benoitmanhes.designsystem.res.icons.CTIconPack
import com.benoitmanhes.designsystem.res.icons.iconpack.BoxSmall
import com.benoitmanhes.designsystem.res.icons.iconpack.Crown
import com.benoitmanhes.designsystem.res.icons.iconpack.Difficulty
import com.benoitmanhes.designsystem.res.icons.iconpack.Ensign
import com.benoitmanhes.designsystem.res.icons.iconpack.Logo
import com.benoitmanhes.designsystem.res.icons.iconpack.Mountain
import com.benoitmanhes.designsystem.res.icons.iconpack.Piste
import com.benoitmanhes.designsystem.utils.IconSpec
import com.benoitmanhes.designsystem.utils.TextSpec
import com.benoitmanhes.designsystem.utils.extensions.getPrimaryColor
import com.benoitmanhes.domain.model.Cache
import com.benoitmanhes.domain.uimodel.UICacheDetails
import com.benoitmanhes.domain.uimodel.UIStep
import com.benoitmanhes.domain.usecase.UseClueUseCase
import com.benoitmanhes.domain.usecase.cache.GetSelectedUICacheUseCase
import com.benoitmanhes.domain.usecase.cache.LogCacheUseCase
import com.benoitmanhes.domain.usecase.cache.StartCacheUseCase
import com.benoitmanhes.domain.usecase.cache.StartCoopCacheUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
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
    getSelectedUICacheUseCase: GetSelectedUICacheUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val cacheId: String = savedStateHandle.get<String>(ExploreDestination.CacheDetails.cacheDetailsArgument).orEmpty()

    private val _uiState = MutableStateFlow<CacheDetailsViewModelState>(CacheDetailsViewModelState.Initialize)
    val uiState: StateFlow<CacheDetailsViewModelState> get() = _uiState.asStateFlow()

    private val _navigation = MutableStateFlow<CacheDetailNavigation?>(null)
    val navigation: StateFlow<CacheDetailNavigation?> get() = _navigation.asStateFlow()

    private val _logModalState = MutableStateFlow<LogModalBottomSheet?>(null)
    val logModalState: StateFlow<LogModalBottomSheet?> get() = _logModalState.asStateFlow()

    private var switchToInstruction = false
    private var cache: Cache? = null

    init {
        viewModelScope.launch {
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
        val currentCache = cache
        when (currentCache) {
            is Cache.Coop -> {
                modalBottomSheetManager.showModal(
                    StartCoopModalBottomSheet(
                        crewPositions = currentCache.crewStepRefs.keys,
                        onClickCrewPosition = { position ->
                            alertDialogManager.showDialog(
                                StartCoopAlertDialog(
                                    cacheTitle = currentCache.title,
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

    private fun startCoopCache(crewPosition: String) {
        viewModelScope.launch {
            loadingManager.showLoading()
            switchToInstruction = true
            startCoopCacheUseCase(cacheId = cacheId, crewPosition = crewPosition)
            loadingManager.hideLoading()
        }
    }

    private fun startRegularCache() {
        viewModelScope.launch {
            loadingManager.showLoading()
            switchToInstruction = true
            startCacheUseCase(cacheId)
            loadingManager.hideLoading()
        }
    }

    private fun logCache(uiStep: UIStep, codeLog: String) {
        viewModelScope.launch {
            _logModalState.value = null
            loadingManager.showLoading()
            val result = logCacheUseCase(cacheId = cacheId, codeLog = codeLog)
            when (result) {
                is CTSuspendResult.Success -> {
                    // TODO show alert success
                    _logModalState.value = null
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

    private fun switchTab(selectedItem: SelectorItem) {
        updateData { dataState ->
            dataState.copy(
                tabSelectorState = dataState.tabSelectorState?.copy(
                    selectedItem = selectedItem,
                )
            )
        }
    }

    private fun CTResult<UICacheDetails>.mapToUIState(): CacheDetailsViewModelState {
        return when (this) {
            is CTResult.Success -> CacheDetailsViewModelState.Data(
                cacheColor = { successData.getPrimaryColor() },
                headerState = CacheDetailHeaderState(
                    title = TextSpec.RawString(successData.cache.title),
                    subTitle = TextSpec.RawString(successData.cache.cacheId),
                ),
                uiMarkers = listOf(
                    UIMarker(
                        coordinates = successData.cache.coordinates,
                        marker = successData.cache.getCacheMarker(),
                        isSelected = false,
                        onClick = {},
                    )
                ),
                bottomBarState = BottomActionBarState(
                    firstButtonState = PrimaryButtonState(
                        text = successData.currentStep.getLogButtonLabel(),
                        onClick = {
                            showLogModalBottomSheet(uiStep = successData.currentStep, isError = false)
                        },
                    )
                ).takeIf { successData.status is UICacheDetails.Status.Started },
                fabButtonState = FabButtonState(
                    icon = IconSpec.VectorIcon(CTIconPack.Logo, null),
                    text = TextSpec.Resources(R.string.cacheDetail_startFab),
                    onClick = ::startCache,
                ).takeIf { successData.status == UICacheDetails.Status.Available },
                tabSelectorState = TabSelectorState(
                    items = tabSelectorsItems,
                    selectedItem = if (switchToInstruction) {
                        switchToInstruction = false
                        tabSelectorsItems.last()
                    } else {
                        uiStateData?.tabSelectorState?.selectedItem ?: tabSelectorsItems.first()
                    },
                    onSelectedItem = ::switchTab,
                ).takeIf { successData.status != UICacheDetails.Status.Available },
                difficultyJaugeState = CTJaugeState(
                    rate = successData.cache.difficulty,
                    icon = IconSpec.VectorIcon(CTIconPack.Difficulty),
                    text = successData.cache.difficulty.toDifficultyText(),
                ),
                groundJaugeState = CTJaugeState(
                    rate = successData.cache.ground,
                    icon = IconSpec.VectorIcon(CTIconPack.Mountain),
                    text = successData.cache.ground.toGroundText()
                ),
                sizeJaugeState = CTJaugeState(
                    rate = successData.cache.size.toJaugeRate(),
                    icon = IconSpec.VectorIcon(CTIconPack.BoxSmall),
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
                        leadingIcon = IconSpec.VectorIcon(CTIconPack.Mountain),
                        text = TextSpec.RawString("Mountain")
                    ),
                    CTRowState(
                        leadingIcon = IconSpec.VectorIcon(CTIconPack.Piste),
                        text = TextSpec.RawString("Sportif")
                    ),
                    CTRowState(
                        leadingIcon = IconSpec.VectorIcon(CTIconPack.Difficulty),
                        text = TextSpec.RawString("Rapide")
                    ),
                    CTRowState(
                        leadingIcon = IconSpec.VectorIcon(CTIconPack.Mountain),
                        text = TextSpec.RawString("Mountain2")
                    ),
                    CTRowState(
                        leadingIcon = IconSpec.VectorIcon(CTIconPack.Piste),
                        text = TextSpec.RawString("Sportif2")
                    ),
                    CTRowState(
                        leadingIcon = IconSpec.VectorIcon(CTIconPack.Difficulty),
                        text = TextSpec.RawString("Rapide2")
                    ),
                ),
                instructionsSectionState = InstructionSectionState(
                    title = successData.currentStep.getStepTitle(),
                    cacheInstructions = successData.currentStep.instructions,
                    clue = successData.currentStep.getClueSection(successData.cache.cacheId),
                    onReport = {}, // TODO Report
                ),
                noteSectionState = NoteSectionState(
                    initialNoteValue = successData.userData.note.orEmpty().textSpec(),
                    onClickNote = {
                        _navigation.value = CacheDetailNavigation.EditNote(successData.cache.cacheId)
                    },
                    onClickInstruments = {}, // TODO Instrument
                    onClickMarker = {}, // TODO Add marker
                )
            )

            is CTResult.Loading -> CacheDetailsViewModelState.Initialize
            is CTResult.Failure -> CacheDetailsViewModelState.Empty(TextSpec.RawString(error?.message))
        }
    }

    private fun getInfoCard(uiCacheDetails: UICacheDetails): InfoCardState? {
        val status = uiCacheDetails.status
        return when {
            status is UICacheDetails.Status.Found -> InfoCardState(
                icon = IconSpec.VectorIcon(CTIconPack.Crown),
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
                    icon = IconSpec.VectorIcon(CTIconPack.Ensign),
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
                revealClue(stepId = stepId, cacheId = cacheId)
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

    private fun UIStep.getStepTitle(): TextSpec =
        if (type.isFinal && type != UIStep.Type.Classical) {
            TextSpec.Resources(R.string.cacheDetail_instructionsSection_title_final)
        } else {
            val cacheType = type
            when (cacheType) {
                is UIStep.Type.Classical -> TextSpec.Resources(R.string.cacheDetail_instructionsSection_title_classical)
                is UIStep.Type.Mystery -> TextSpec.Resources(R.string.cacheDetail_instructionsSection_title_mystery)
                is UIStep.Type.Piste -> TextSpec.Resources(
                    R.string.cacheDetail_instructionsSection_title_piste,
                    cacheType.index + 1
                )
                is UIStep.Type.Coop -> TextSpec.Resources(
                    R.string.cacheDetail_instructionsSection_title_piste,
                    cacheType.crewPosition,
                    cacheType.index + 1,
                )
            }
        }

    private fun showLogModalBottomSheet(uiStep: UIStep, isError: Boolean) {
        _logModalState.value =
            LogModalBottomSheet(
                message = uiStep.getLogBottomSheetMessage(),
                errorMessage = uiStep.getLogBottomSheetError(),
                isError = isError,
                onDismiss = {
                    _logModalState.value = null
                },
                onValidate = { logCache(uiStep, codeLog = it) },
                hideError = {
                    _logModalState.value = logModalState.value?.copy(isError = false)
                },
            )
    }

    private fun UIStep.getLogButtonLabel(): TextSpec =
        if (type.isFinal) {
            TextSpec.Resources(R.string.cacheDetail_logButton_validate)
        } else {
            TextSpec.Resources(R.string.cacheDetail_logButton_answer)
        }

    private fun UIStep.getLogBottomSheetMessage(): TextSpec =
        if (type.isFinal) {
            TextSpec.Resources(R.string.logModal_message_final)
        } else {
            TextSpec.Resources(R.string.logModal_message_step)
        }

    private fun UIStep.getLogBottomSheetError(): TextSpec =
        if (type.isFinal) {
            TextSpec.Resources(R.string.logModal_error_final)
        } else {
            TextSpec.Resources(R.string.logModal_error_step)
        }
}

private val tabSelectorsItems: List<SelectorItem> = listOf(
    SelectorItem(TextSpec.RawString("Recap")),
    SelectorItem(TextSpec.RawString("Instructions")),
)
