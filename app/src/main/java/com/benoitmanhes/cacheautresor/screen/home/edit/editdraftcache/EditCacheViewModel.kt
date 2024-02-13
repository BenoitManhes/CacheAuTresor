package com.benoitmanhes.cacheautresor.screen.home.edit.editdraftcache

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benoitmanhes.cacheautresor.common.composable.row.MapRowPickerState
import com.benoitmanhes.cacheautresor.common.composable.row.StickerRowPickerState
import com.benoitmanhes.cacheautresor.common.composable.row.TextRowPickerState
import com.benoitmanhes.cacheautresor.common.extensions.format
import com.benoitmanhes.cacheautresor.common.extensions.orPlaceHolder
import com.benoitmanhes.cacheautresor.common.extensions.toCacheType
import com.benoitmanhes.cacheautresor.navigation.creation.EditCacheDestination
import com.benoitmanhes.common.compose.extensions.textSpec
import com.benoitmanhes.designsystem.theme.CTColorTheme
import com.benoitmanhes.designsystem.utils.extensions.getTypeColorTheme
import com.benoitmanhes.domain.model.Coordinates
import com.benoitmanhes.domain.usecase.draftcache.GetDraftCacheUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class EditCacheViewModel @Inject constructor(
    getDraftCacheUseCase: GetDraftCacheUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val draftCacheId: String = savedStateHandle.get<String>(EditCacheDestination.EditDraftCache.arg).orEmpty()

    val editCacheState: StateFlow<EditCacheViewModelState> = getDraftCacheUseCase.asFlow(draftCacheId)
        .map { draftCache ->
            EditCacheViewModelState(
                cacheName = TextRowPickerState(
                    text = draftCache?.title?.textSpec().orPlaceHolder(),
                    onClick = { _navigation.value = EditCacheNavigation.PickName(draftCacheId) },
                ),
                cacheType = StickerRowPickerState(
                    sticker = draftCache?.type?.let(::getTypeSticker),
                    colorTheme = draftCache?.type?.toCacheType()?.getTypeColorTheme() ?: CTColorTheme.Cartography,
                    onClick = { _navigation.value = EditCacheNavigation.PickType(draftCacheId) },
                ),
                initCoordinates = MapRowPickerState(
                    uiMarker = draftCache?.let(::getInitCoordinatesUIMarker),
                    text = draftCache?.coordinates?.format(Coordinates.Format.DM).orPlaceHolder(),
                    onClick = { _navigation.value = EditCacheNavigation.PickInitCoordinates(draftCacheId) }
                ),
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = EditCacheViewModelState(),
        )

    private val _navigation = MutableStateFlow<EditCacheNavigation?>(null)
    val navigation: StateFlow<EditCacheNavigation?> get() = _navigation.asStateFlow()
    fun consumeNavigation() {
        _navigation.value = null
    }
}

sealed interface EditCacheNavigation {
    @JvmInline
    value class PickName(val draftCacheId: String) : EditCacheNavigation

    @JvmInline
    value class PickType(val draftCacheId: String) : EditCacheNavigation

    @JvmInline
    value class PickInitCoordinates(val draftCacheId: String) : EditCacheNavigation
}
