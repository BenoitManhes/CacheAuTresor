package com.benoitmanhes.cacheautresor.screen.home.explore.cachedetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.composable.bottombar.BottomActionBarState
import com.benoitmanhes.cacheautresor.common.extensions.mediumFormat
import com.benoitmanhes.cacheautresor.common.extensions.toJaugeRate
import com.benoitmanhes.cacheautresor.common.extensions.toText
import com.benoitmanhes.cacheautresor.navigation.explore.ExploreDestination
import com.benoitmanhes.cacheautresor.screen.home.explore.cachedetailrecap.section.CacheTypeSectionState
import com.benoitmanhes.cacheautresor.screen.home.explore.cachedetailrecap.section.CartographerSectionState
import com.benoitmanhes.cacheautresor.screen.home.explore.cachedetails.section.CacheDetailHeaderState
import com.benoitmanhes.cacheautresor.utils.AppConstants
import com.benoitmanhes.core.result.CTResult
import com.benoitmanhes.designsystem.molecule.button.fabbutton.FabButtonState
import com.benoitmanhes.designsystem.molecule.jauge.CTJaugeState
import com.benoitmanhes.designsystem.molecule.row.CTRowState
import com.benoitmanhes.designsystem.molecule.selector.SelectorItem
import com.benoitmanhes.designsystem.molecule.selector.TabSelectorState
import com.benoitmanhes.designsystem.res.icons.CTIconPack
import com.benoitmanhes.designsystem.res.icons.iconpack.BoxSmall
import com.benoitmanhes.designsystem.res.icons.iconpack.Difficulty
import com.benoitmanhes.designsystem.res.icons.iconpack.Logo
import com.benoitmanhes.designsystem.res.icons.iconpack.Mountain
import com.benoitmanhes.designsystem.res.icons.iconpack.Piste
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.theme.CTTheme.icon
import com.benoitmanhes.designsystem.theme.composed
import com.benoitmanhes.designsystem.utils.IconSpec
import com.benoitmanhes.designsystem.utils.TextSpec
import com.benoitmanhes.designsystem.utils.extensions.getPrimaryColor
import com.benoitmanhes.domain.model.Distance.Companion.meters
import com.benoitmanhes.domain.uimodel.UICacheDetails
import com.benoitmanhes.domain.usecase.cache.GetSelectedUICacheUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CacheDetailViewModel @Inject constructor(
    getSelectedUICacheUseCase: GetSelectedUICacheUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val cacheId: String = savedStateHandle.get<String>(ExploreDestination.CacheDetails.cacheDetailsArgument).orEmpty()

    private val _uiState = MutableStateFlow<CacheDetailsViewModelState>(CacheDetailsViewModelState.Initialize)
    val uiState: StateFlow<CacheDetailsViewModelState> get() = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getSelectedUICacheUseCase(cacheId)
                .collect { result ->
                    _uiState.emit(result.mapToUIState())
                }
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
                uiMarkers = listOf(),
                bottomBarState = null,
                fabButtonState = FabButtonState(
                    icon = IconSpec.VectorIcon(CTIconPack.Logo, null),
                    text = TextSpec.RawString("Fab"),
                    onClick = {},
                ),
                tabSelectorState = TabSelectorState(
                    items = tabSelectorsItems,
                    selectedItem = uiStateData?.tabSelectorState?.selectedItem ?: tabSelectorsItems.first(),
                    onSelectedItem = ::switchTab,
                ),

                difficultyJaugeState = CTJaugeState(
                    rate = successData.cache.difficulty,
                    icon = IconSpec.VectorIcon(CTIconPack.Difficulty),
                    text = TextSpec.RawString("diff")
                ),
                groundJaugeState = CTJaugeState(
                    rate = successData.cache.ground,
                    icon = IconSpec.VectorIcon(CTIconPack.Mountain),
                    text = TextSpec.RawString("terrain"),
                ),
                sizeJaugeState = CTJaugeState(
                    rate = successData.cache.size.toJaugeRate(),
                    icon = IconSpec.VectorIcon(CTIconPack.BoxSmall),
                    text = null,
                ),
                infoCardState = null,
                typeSectionState = CacheTypeSectionState(
                    typeIcon = IconSpec.VectorIcon(CTIconPack.Logo),
                    typeText = TextSpec.loreumIpsum(1),
                    stickerLabel = null
                ),
                cartographerSectionState = CartographerSectionState(
                    creatorName = TextSpec.RawString(successData.explorerName),
                    creationDateText = TextSpec.RawString(successData.cache.createDate.mediumFormat()),
                ),
                cacheCoordinates = successData.cache.coordinates,
                distanceText = 800.421.meters.toText(),
                description = TextSpec.loreumIpsum(50),
                characteristics = listOf(
                    CTRowState(leadingIcon = IconSpec.VectorIcon(CTIconPack.Mountain), text = TextSpec.RawString("Mountain")),
                    CTRowState(leadingIcon = IconSpec.VectorIcon(CTIconPack.Piste), text = TextSpec.RawString("Sportif")),
                    CTRowState(leadingIcon = IconSpec.VectorIcon(CTIconPack.Difficulty), text = TextSpec.RawString("Rapide")),
                    CTRowState(leadingIcon = IconSpec.VectorIcon(CTIconPack.Mountain), text = TextSpec.RawString("Mountain2")),
                    CTRowState(leadingIcon = IconSpec.VectorIcon(CTIconPack.Piste), text = TextSpec.RawString("Sportif2")),
                    CTRowState(leadingIcon = IconSpec.VectorIcon(CTIconPack.Difficulty), text = TextSpec.RawString("Rapide2")),
                )
            )

            is CTResult.Loading -> CacheDetailsViewModelState.Initialize
            is CTResult.Failure -> CacheDetailsViewModelState.Empty(TextSpec.RawString(error?.message))
        }
    }

    private fun updateData(block: (CacheDetailsViewModelState.Data) -> CacheDetailsViewModelState.Data) {
        uiStateData?.let {
            _uiState.value = block(it)
        }
    }
}

private val tabSelectorsItems: List<SelectorItem> = listOf(
    SelectorItem(TextSpec.RawString("Recap")),
    SelectorItem(TextSpec.RawString("Instructions")),
)
