package com.benoitmanhes.cacheautresor.screen.home.edit.picktype

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.composable.bottombar.BottomActionBarState
import com.benoitmanhes.cacheautresor.navigation.creation.EditCacheDestination
import com.benoitmanhes.cacheautresor.screen.loading.LoadingManager
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.molecule.button.primarybutton.PrimaryButtonState
import com.benoitmanhes.designsystem.molecule.card.CTSelectionCardState
import com.benoitmanhes.designsystem.res.icons.iconpack.Coop
import com.benoitmanhes.designsystem.res.icons.iconpack.Mystery
import com.benoitmanhes.designsystem.res.icons.iconpack.Parchment
import com.benoitmanhes.designsystem.res.icons.iconpack.Piste
import com.benoitmanhes.designsystem.theme.CTColorTheme
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.IconSpec
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
class PickTypeDraftCacheViewModel @Inject constructor(
    private val saveDraftCacheUseCase: SaveDraftCacheUseCase,
    private val getDraftCacheUseCase: GetDraftCacheUseCase,
    private val loadingManager: LoadingManager,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val draftCacheId: String = savedStateHandle.get<String>(
        EditCacheDestination.PickTypeDraftCache.arg
    ).orEmpty()

    val uiState: StateFlow<PickTypeDraftCacheViewModelState> get() = _uiState.asStateFlow()

    private val _uiState = MutableStateFlow(
        PickTypeDraftCacheViewModelState(
            typeSelectionCards = typesMap,
            bottomActionBar = BottomActionBarState(
                firstButton = PrimaryButtonState(
                    text = TextSpec.Resources(R.string.common_validate),
                    onClick = ::saveType,
                )
            )
        )
    )

    private val _navigateBack = MutableStateFlow<Boolean?>(null)
    val navigateBack: StateFlow<Boolean?> get() = _navigateBack.asStateFlow()

    init {
        viewModelScope.launch {
            getDraftCacheUseCase(draftCacheId)?.type?.let { type ->
                updateType(type::class.java.simpleName)
            }
        }
    }

    fun consumeNavigation() {
        _navigateBack.value = null
    }

    private fun updateType(typeSelected: String) {
        _uiState.value = uiState.value.copy(
            typeSelectionCards = uiState.value.typeSelectionCards.mapValues { (stringType, card) ->
                card.copy(
                    isSelected = typeSelected == stringType,
                )
            }
        )
    }

    private fun saveType() {
        viewModelScope.launch {
            loadingManager.showLoading()
            getDraftCacheUseCase(draftCacheId)?.also { draftCache ->
                val typeSelectedRaw = uiState.value.typeSelectionCards
                    .filterValues { it.isSelected }
                    .firstNotNullOf { it.key }
                val type = when (typeSelectedRaw) {
                    DraftCache.Type.Classical::class.java.simpleName -> DraftCache.Type.Classical
                    DraftCache.Type.Piste::class.java.simpleName -> DraftCache.Type.Piste(emptyList())
                    DraftCache.Type.Mystery::class.java.simpleName -> DraftCache.Type.Mystery(null)
                    DraftCache.Type.Coop::class.java.simpleName -> DraftCache.Type.Coop(emptyMap())
                    else -> null
                }
                saveDraftCacheUseCase(
                    draftCache.copy(type = type)
                )
                _navigateBack.value = true
            }
            loadingManager.hideLoading()
        }
    }

    private val typesMap
        get() = mapOf(
            DraftCache.Type.Classical::class.java.simpleName to CTSelectionCardState(
                icon = IconSpec.ComposeIcon { CTTheme.icon.Parchment },
                title = TextSpec.Resources(R.string.cache_type_classical),
                description = TextSpec.Resources(R.string.pickTypeDraftCache_classical_description),
                isSelected = false,
                colorTheme = CTColorTheme.Classical,
                onClick = { updateType(DraftCache.Type.Classical::class.java.simpleName) },
            ),
            DraftCache.Type.Piste::class.java.simpleName to CTSelectionCardState(
                icon = IconSpec.ComposeIcon { CTTheme.icon.Piste },
                title = TextSpec.Resources(R.string.cache_type_piste),
                description = TextSpec.Resources(R.string.pickTypeDraftCache_piste_description),
                isSelected = false,
                colorTheme = CTColorTheme.Piste,
                onClick = { updateType(DraftCache.Type.Piste::class.java.simpleName) },
            ),
            DraftCache.Type.Mystery::class.java.simpleName to CTSelectionCardState(
                icon = IconSpec.ComposeIcon { CTTheme.icon.Mystery },
                title = TextSpec.Resources(R.string.cache_type_mystery),
                description = TextSpec.Resources(R.string.pickTypeDraftCache_mystery_description),
                isSelected = false,
                colorTheme = CTColorTheme.Mystery,
                onClick = { updateType(DraftCache.Type.Mystery::class.java.simpleName) },
            ),
            DraftCache.Type.Coop::class.java.simpleName to CTSelectionCardState(
                icon = IconSpec.ComposeIcon { CTTheme.icon.Coop },
                title = TextSpec.Resources(R.string.cache_type_coop),
                description = TextSpec.Resources(R.string.pickTypeDraftCache_coop_description),
                isSelected = false,
                colorTheme = CTColorTheme.Coop,
                onClick = { updateType(DraftCache.Type.Coop::class.java.simpleName) },
            ),
        )
}
