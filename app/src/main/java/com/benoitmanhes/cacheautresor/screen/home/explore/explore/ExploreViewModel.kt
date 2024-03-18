package com.benoitmanhes.cacheautresor.screen.home.explore.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.composable.alertdialog.ClassicAlertDialog
import com.benoitmanhes.cacheautresor.common.composable.alertdialog.CommonAlertDialogAction
import com.benoitmanhes.cacheautresor.common.composable.modalbottomsheet.UnlockCacheModalBottomSheet
import com.benoitmanhes.cacheautresor.common.extensions.getIcon
import com.benoitmanhes.cacheautresor.common.extensions.getTypeText
import com.benoitmanhes.cacheautresor.common.extensions.toOneDecimalFormat
import com.benoitmanhes.cacheautresor.common.extensions.toSizeText
import com.benoitmanhes.cacheautresor.common.extensions.toText
import com.benoitmanhes.cacheautresor.common.viewModel.LocationAccessViewModelDelegate
import com.benoitmanhes.cacheautresor.common.viewModel.LocationAccessViewModelDelegateImpl
import com.benoitmanhes.cacheautresor.screen.alertdialog.AlertDialogManager
import com.benoitmanhes.cacheautresor.screen.home.explore.explore.section.CacheBannerState
import com.benoitmanhes.cacheautresor.screen.loading.LoadingManager
import com.benoitmanhes.cacheautresor.screen.modalbottomsheet.ModalBottomSheetManager
import com.benoitmanhes.cacheautresor.screen.snackbar.SnackbarManager
import com.benoitmanhes.cacheautresor.screen.snackbar.showError
import com.benoitmanhes.common.compose.extensions.textSpec
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.core.error.CTDomainError
import com.benoitmanhes.core.result.CTResult
import com.benoitmanhes.core.result.CTSuspendResult
import com.benoitmanhes.designsystem.theme.CTColorTheme
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.theme.composed
import com.benoitmanhes.designsystem.utils.extensions.getColorTheme
import com.benoitmanhes.domain.model.CacheUserStatus
import com.benoitmanhes.domain.model.Coordinates
import com.benoitmanhes.domain.model.Distance
import com.benoitmanhes.domain.uimodel.UIExploreCache
import com.benoitmanhes.domain.usecase.cache.GetAllUICachesUseCase
import com.benoitmanhes.domain.usecase.cache.SortCacheUseCase
import com.benoitmanhes.domain.usecase.cache.UnlockCacheUseCase
import com.benoitmanhes.domain.usecase.cache.UpdateCachesDistancesUseCase
import com.benoitmanhes.domain.utils.DomainConstants
import com.benoitmanhes.logger.CTLogger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

private val logger = CTLogger.get<ExploreViewModel>()

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val unlockCacheUseCase: UnlockCacheUseCase,
    private val updateCachesDistancesUseCase: UpdateCachesDistancesUseCase,
    private val sortCacheUseCase: SortCacheUseCase,
    private val modalBottomSheetManager: ModalBottomSheetManager,
    private val snackbarManager: SnackbarManager,
    private val loadingManager: LoadingManager,
    private val alertDialogManager: AlertDialogManager,
    locationAccessViewModelDelegateImpl: LocationAccessViewModelDelegateImpl,
    getAllUICacheUseCase: GetAllUICachesUseCase,
) : LocationAccessViewModelDelegate by locationAccessViewModelDelegateImpl,
    ViewModel() {

    private val _uiState = MutableStateFlow(ExploreUIState())
    val uiState: StateFlow<ExploreUIState> get() = _uiState.asStateFlow()

    private val _navigation = MutableStateFlow<ExploreNavigation?>(null)
    val navigation: StateFlow<ExploreNavigation?> get() = _navigation.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.Default) {
            combine(
                getAllUICacheUseCase(),
                currentLocation,
            ) { result, location ->
                when (result) {
                    is CTResult.Success -> {
                        val cachesWithDistance = sortCacheUseCase(
                            updateCachesDistancesUseCase(
                                currentLocation = location,
                                uiExploreCaches = result.successData,
                            )
                        )
                        _uiState.value = uiState.value.copy(
                            caches = cachesWithDistance,
                            cacheList = cachesWithDistance.mapNotNull { it.toBannerCache() },
                            isLoading = false,
                            currentPosition = location,
                        )
                    }

                    is CTResult.Loading -> {
                        _uiState.value = uiState.value.copy(isLoading = true)
                    }

                    is CTResult.Failure -> {
                        snackbarManager.showError(result.error)
                        _uiState.value = uiState.value.copy(isLoading = false)
                    }
                }
            }.collect {}
        }
    }

    fun onMapPositionChange(position: Coordinates) {
        _uiState.value = uiState.value.copy(mapPosition = position)
    }

    fun selectCache(uiExploreCache: UIExploreCache) {
        _uiState.value = uiState.value.copy(
            cacheSelected = uiExploreCache,
            cacheBanner = uiExploreCache.toBannerCache(),
        )
    }

    private fun UIExploreCache.toBannerCache(): CacheBannerState? {
        return if (userStatus in bannerHideCacheStatus) {
            CacheBannerState(
                cacheId = cache.cacheId,
                icon = CTTheme.composed { icon.Logo },
                creatorText = TextSpec.RawString("-"),
                titleText = TextSpec.RawString("???"),
                difficultyText = TextSpec.RawString("-"),
                groundText = TextSpec.RawString("-"),
                sizeText = TextSpec.RawString("-"),
                distanceText = distance?.toText()
                    .takeIf { userStatus == CacheUserStatus.Available },
                stickerText = null,
                colorTheme = CTColorTheme.Lock,
                onClick = { clickUnlockCache(this) },
            )
        } else {
            CacheBannerState(
                cacheId = cache.cacheId,
                icon = cache.getIcon(),
                creatorText = TextSpec.RawString(explorerName ?: "-"),
                titleText = TextSpec.RawString(cache.title),
                difficultyText = TextSpec.RawString(cache.difficulty.toOneDecimalFormat()),
                groundText = TextSpec.RawString(cache.ground.toOneDecimalFormat()),
                sizeText = cache.size.toSizeText(),
                distanceText = distance?.toText()
                    .takeIf { userStatus == CacheUserStatus.Available },
                stickerText = when (userStatus) {
                    CacheUserStatus.Found -> ptsWin?.let {
                        TextSpec.Resources(R.string.cacheDetail_foundInfoCard_points, it)
                    }

                    CacheUserStatus.Started -> TextSpec.Resources(
                        R.string.cacheDetail_cacheTypeSection_ongoing
                    )

                    else -> null
                },
                colorTheme = cache.getColorTheme(userStatus),
                onClick = {
                    _navigation.value = ExploreNavigation.CacheDetail(cache.cacheId)
                },
            )
        }
    }

    private fun clickUnlockCache(uiExploreCache: UIExploreCache) {
        val distance = uiExploreCache.distance
        when (distance) {
            null,
            Distance.INFINITE,
            -> ::requestLocation

            in Distance.ZERO..DomainConstants.Cache.unlockingAvailableDistance -> showUnlockCacheModal(
                uiExploreCache,
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
        uiExploreCache: UIExploreCache,
        isError: Boolean,
    ) {
        modalBottomSheetManager.showModal(
            UnlockCacheModalBottomSheet(
                lockInstruction = uiExploreCache.cache.lockDescription.textSpec(),
                onValidate = { inputLockCode ->
                    unlockCache(uiExploreCache, inputLockCode)
                },
                isError = isError,
            )
        )
    }

    private fun unlockCache(uiExploreCache: UIExploreCache, inputLockCode: String) {
        viewModelScope.launch {
            loadingManager.showLoading()
            val result = unlockCacheUseCase(cacheId = uiExploreCache.cache.cacheId, lockCodeInput = inputLockCode)
            loadingManager.hideLoading()
            when (result) {
                is CTSuspendResult.Failure -> result.handleUnlockCacheError(uiExploreCache)
                is CTSuspendResult.Success -> {
                    alertDialogManager.showDialog(
                        ClassicAlertDialog(
                            icon = uiExploreCache.cache.getIcon(),
                            title = TextSpec.Resources(R.string.dialog_cacheUnlocked_title),
                            message = TextSpec.Resources(
                                R.string.dialog_cacheUnlocked_message,
                                uiExploreCache.cache.getTypeText(),
                                uiExploreCache.cache.title,
                            ),
                            actions = listOf(
                                CommonAlertDialogAction.letsGo {
                                    _navigation.value = ExploreNavigation.CacheDetail(uiExploreCache.cache.cacheId)
                                }
                            )
                        )
                    )
                }
            }
        }
    }

    private fun CTSuspendResult.Failure<Unit>.handleUnlockCacheError(uiExploreCache: UIExploreCache) {
        when (error?.code) {
            CTDomainError.Code.CACHE_INVALID_UNLOCK_CODE -> {
                showUnlockCacheModal(uiExploreCache, isError = true)
            }

            else -> {
                snackbarManager.showError(error)
            }
        }
    }

    fun unselectCache() {
        _uiState.value = uiState.value.copy(
            cacheSelected = null,
            cacheBanner = null,
        )
    }

    fun consumeNavigation() {
        _navigation.value = null
    }
}

private val bannerHideCacheStatus: Set<CacheUserStatus> = setOf(
    CacheUserStatus.Locked,
    CacheUserStatus.Hidden,
)
