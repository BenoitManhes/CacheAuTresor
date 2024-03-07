package com.benoitmanhes.cacheautresor.screen.home.edit.pickground

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.composable.bottombar.BottomActionBarState
import com.benoitmanhes.cacheautresor.common.extensions.toGroundText
import com.benoitmanhes.cacheautresor.common.screen.pickjauge.composable.JaugePickerRowState
import com.benoitmanhes.cacheautresor.navigation.creation.EditCacheDestination
import com.benoitmanhes.cacheautresor.screen.snackbar.SnackbarManager
import com.benoitmanhes.cacheautresor.screen.snackbar.showError
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.core.result.CTSuspendResult
import com.benoitmanhes.designsystem.molecule.button.primarybutton.ButtonStatus
import com.benoitmanhes.designsystem.molecule.button.primarybutton.PrimaryButtonState
import com.benoitmanhes.designsystem.molecule.jauge.CTJaugeState
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.theme.composed
import com.benoitmanhes.domain.model.DraftCache
import com.benoitmanhes.domain.usecase.draftcache.GetDraftCacheUseCase
import com.benoitmanhes.domain.usecase.draftcache.SaveDraftCacheUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PickGroundViewModel @Inject constructor(
    private val saveDraftCacheUseCase: SaveDraftCacheUseCase,
    private val snackbarManager: SnackbarManager,
    getDraftCacheUseCase: GetDraftCacheUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val draftCacheId: String = savedStateHandle.get<String>(EditCacheDestination.PickGround.arg).orEmpty()

    private var currentDraftCache: DraftCache? = null

    val uiState: StateFlow<PickGroundViewModelState> get() = _uiState.asStateFlow()
    private val _uiState = MutableStateFlow(
        PickGroundViewModelState(
            jaugeState = CTJaugeState(
                rate = null,
                icon = CTTheme.composed { icon.Mountain },
                text = TextSpec.Resources(R.string.common_noValue_placeHolder),
            ),
            jaugeRow = initialRows,
            topBarTitle = TextSpec.Resources(R.string.pickDifficulty_topBar_title),
            bottomActionBar = BottomActionBarState(
                title = TextSpec.Resources(R.string.pickDifficulty_bottomActionBar_title),
                message = TextSpec.Resources(R.string.pickDifficulty_bottomActionBar_description),
                primaryButton = PrimaryButtonState(
                    text = TextSpec.Resources(R.string.common_validate),
                    onClick = ::validate,
                    status = ButtonStatus.DISABLE,
                ),
                secondaryButton = PrimaryButtonState(
                    text = TextSpec.Resources(R.string.common_reinitialize),
                    onClick = { currentDraftCache?.difficulty?.let(::pickARow) },
                ),
            )
        )
    )

    private val _navigate = MutableStateFlow<Boolean?>(null)
    val navigate: StateFlow<Boolean?> get() = _navigate.asStateFlow()

    init {
        viewModelScope.launch {
            getDraftCacheUseCase.asFlow(draftCacheId).collect { draftCache ->
                currentDraftCache = draftCache
                draftCache?.ground?.let {
                    pickARow(it)
                }
            }
        }
    }

    fun consumeNavigation() {
        _navigate.value = null
    }

    private fun validate() {
        viewModelScope.launch {
            currentDraftCache?.copy(
                ground = uiState.value.jaugeState.rate,
            )?.let { newDraftCache ->
                val result = saveDraftCacheUseCase(newDraftCache)
                when (result) {
                    is CTSuspendResult.Success -> {
                        _navigate.value = true
                    }

                    is CTSuspendResult.Failure -> {
                        snackbarManager.showError(result.error)
                    }
                }
            }
        }
    }

    private fun pickARow(rate: Float) {
        if (rate != uiState.value.jaugeState.rate) {
            _uiState.value = uiState.value.copy(
                jaugeState = uiState.value.jaugeState.copy(
                    rate = rate,
                    text = rate.toGroundText(),
                ),
                jaugeRow = uiState.value.jaugeRow.map { rowState ->
                    rowState.copy(isSelected = rowState.rate == rate)
                },
                bottomActionBar = uiState.value.bottomActionBar.copy(
                    primaryButton = uiState.value.bottomActionBar.primaryButton?.copy(
                        status = if (rate != currentDraftCache?.ground) ButtonStatus.ENABLE else ButtonStatus.DISABLE,
                    ),
                    secondaryButton = uiState.value.bottomActionBar.secondaryButton?.copy(
                        status = if (rate != currentDraftCache?.ground) ButtonStatus.ENABLE else ButtonStatus.DISABLE,
                    ),
                ),
            )
        }
    }

    private val initialRows: List<JaugePickerRowState>
        get() = mapRate.map { (rate, textRes) ->
            JaugePickerRowState(
                rate = rate,
                descriptionText = TextSpec.Resources(textRes),
                isSelected = false,
                onClick = { pickARow(rate) },
            )
        }.sortedBy { it.rate }

    private val mapRate: Map<Float, Int>
        get() = mapOf(
            1f to R.string.pickGround_row_1,
            1.5f to R.string.pickGround_row_1half,
            2f to R.string.pickGround_row_2,
            2.5f to R.string.pickGround_row_2half,
            3f to R.string.pickGround_row_3,
            3.5f to R.string.pickGround_row_3half,
            4f to R.string.pickGround_row_4,
            4.5f to R.string.pickGround_row_4half,
            5f to R.string.pickGround_row_5,
        )
}
