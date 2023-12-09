package com.benoitmanhes.cacheautresor.screen.home.explore.explore

import android.location.Location
import android.location.LocationManager
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.composable.alertdialog.ClassicAlertDialog
import com.benoitmanhes.cacheautresor.common.composable.alertdialog.CommonAlertDialogAction
import com.benoitmanhes.cacheautresor.common.composable.modalbottomsheet.ClassicModalBottomSheet
import com.benoitmanhes.cacheautresor.common.composable.modalbottomsheet.UnlockCacheModalBottomSheet
import com.benoitmanhes.cacheautresor.common.extensions.getIcon
import com.benoitmanhes.cacheautresor.common.extensions.getTypeText
import com.benoitmanhes.cacheautresor.common.extensions.toDistanceText
import com.benoitmanhes.cacheautresor.common.extensions.toModel
import com.benoitmanhes.cacheautresor.common.extensions.toOneDecimalFormat
import com.benoitmanhes.cacheautresor.common.extensions.toSizeText
import com.benoitmanhes.cacheautresor.common.viewModel.LocationAccessViewModel
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
import com.benoitmanhes.designsystem.molecule.button.primarybutton.PrimaryButtonState
import com.benoitmanhes.designsystem.res.icons.CTIconPack
import com.benoitmanhes.designsystem.res.icons.iconpack.Location
import com.benoitmanhes.designsystem.res.icons.iconpack.Logo
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.theme.composed
import com.benoitmanhes.designsystem.utils.extensions.getCacheColor
import com.benoitmanhes.designsystem.utils.extensions.toIconSpec
import com.benoitmanhes.domain.model.CacheUserStatus
import com.benoitmanhes.domain.model.Coordinates
import com.benoitmanhes.domain.uimodel.UIExploreCache
import com.benoitmanhes.domain.usecase.cache.GetAllUICachesUseCase
import com.benoitmanhes.domain.usecase.cache.SortCacheUseCase
import com.benoitmanhes.domain.usecase.cache.UnlockCacheUseCase
import com.benoitmanhes.domain.usecase.cache.UpdateCachesDistancesUseCase
import com.benoitmanhes.domain.utils.DomainConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    locationManager: LocationManager,
    getAllUICacheUseCase: GetAllUICachesUseCase,
    private val unlockCacheUseCase: UnlockCacheUseCase,
    private val updateCachesDistancesUseCase: UpdateCachesDistancesUseCase,
    private val sortCacheUseCase: SortCacheUseCase,
    private val modalBottomSheetManager: ModalBottomSheetManager,
    private val snackbarManager: SnackbarManager,
    private val loadingManager: LoadingManager,
    private val alertDialogManager: AlertDialogManager,
) : LocationAccessViewModel(locationManager) {

    var uiState: ExploreUIState by mutableStateOf(ExploreUIState())
        private set

    private val _navigation = MutableStateFlow<ExploreNavigation?>(null)
    val navigation: StateFlow<ExploreNavigation?> get() = _navigation.asStateFlow()

    private var shouldShowLocationModal: Boolean = false

    init {
        viewModelScope.launch {
            getAllUICacheUseCase().collect { result ->
                when (result) {
                    is CTResult.Success -> {
                        val cachesWithDistance = uiState.currentPosition?.let { _currentPosition ->
                            updateCachesDistancesUseCase(
                                currentLocation = _currentPosition,
                                uiExploreCaches = result.successData,
                            )
                        } ?: result.successData

                        val caches = sortCacheUseCase(cachesWithDistance)
                        uiState = uiState.copy(
                            caches = caches,
                            cacheList = caches.mapNotNull { it.toBannerCache() },
                            isLoading = false,
                        )
                    }

                    is CTResult.Loading -> {
                        uiState = uiState.copy(isLoading = true)
                    }

                    is CTResult.Failure -> {
                        snackbarManager.showError(result.error)
                        uiState = uiState.copy(isLoading = false)
                    }
                }
            }
        }
    }

    fun onMapPositionChange(position: Coordinates) {
        uiState = uiState.copy(mapPosition = position)
    }

    fun selectCache(uiExploreCache: UIExploreCache) {
        uiState = uiState.copy(
            cacheSelected = uiExploreCache,
            cacheBanner = uiExploreCache.toBannerCache(),
        )
    }

    private fun UIExploreCache.toBannerCache(): CacheBannerState? {
        return if (userStatus in bannerHideCacheStatus) {
            CacheBannerState(
                cacheId = cache.cacheId,
                icon = CTTheme.composed { icon.Logo.toIconSpec() },
                creatorText = TextSpec.RawString("-"),
                titleText = TextSpec.RawString("???"),
                difficultyText = TextSpec.RawString("-"),
                groundText = TextSpec.RawString("-"),
                sizeText = TextSpec.RawString("-"),
                distanceText = distance?.toDistanceText()
                    .takeIf { userStatus == CacheUserStatus.Available },
                stickerText = null,
                color = CTTheme.composed { color.placeholder },
                onClick = { clickUnlockCache(this) },
            )
        } else {
            CacheBannerState(
                cacheId = cache.cacheId,
                icon = { cache.getIcon() },
                creatorText = TextSpec.RawString(explorerName ?: "-"),
                titleText = TextSpec.RawString(cache.title),
                difficultyText = TextSpec.RawString(cache.difficulty.toOneDecimalFormat()),
                groundText = TextSpec.RawString(cache.ground.toOneDecimalFormat()),
                sizeText = cache.size.toSizeText(),
                distanceText = distance?.toDistanceText()
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
                color = { cache.getCacheColor(userStatus) },
                onClick = {
                    _navigation.value = ExploreNavigation.CacheDetail(cache.cacheId)
                },
            )
        }
    }

    private fun clickUnlockCache(uiExploreCache: UIExploreCache) {
        val distance = uiExploreCache.distance
        when (distance) {
            null -> ::requestLocation
            in 0.0..DomainConstants.Cache.unlockingAvailableDistance -> showUnlockCacheModal(
                uiExploreCache,
                isError = false
            )

            else -> {
                alertDialogManager.showDialog(
                    ClassicAlertDialog(
                        title = TextSpec.Resources(R.string.explore_tooFarDialog_title),
                        message = TextSpec.Resources(R.string.explore_tooFarDialog_message),
                        icon = CTTheme.composed { icon.Location.toIconSpec() },
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
                            icon = { uiExploreCache.cache.getIcon() },
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
        uiState = uiState.copy(
            cacheSelected = null,
            cacheBanner = null,
        )
    }

    fun consumeNavigation() {
        _navigation.value = null
    }

    fun locationPermissionRefused(onConfirm: () -> Unit) {
        if (shouldShowLocationModal) {
            modalBottomSheetManager.showModal(
                ClassicModalBottomSheet(
                    icon = CTIconPack.Location.toIconSpec(),
                    title = TextSpec.Resources(R.string.locationModal_title),
                    message = TextSpec.Resources(R.string.locationModal_message),
                    cancelAction = PrimaryButtonState(
                        text = TextSpec.Resources(R.string.common_refuse),
                        onClick = {},
                    ),
                    confirmAction = PrimaryButtonState(
                        text = TextSpec.Resources(R.string.locationModal_confirmAction),
                        onClick = onConfirm,
                    ),
                )
            )
            shouldShowLocationModal = false
        }
    }

    fun requestLocation() {
        shouldShowLocationModal = true
        _navigation.value = ExploreNavigation.RequestLocation(openSettings = false)
    }

    override fun onAccessLocationRefused() {
        _navigation.value = ExploreNavigation.RequestLocation(openSettings = false)
    }

    override fun onLocationChanged(p0: Location) {
        val caches = sortCacheUseCase(
            updateCachesDistancesUseCase(
                currentLocation = p0.toModel(),
                uiExploreCaches = uiState.caches,
            )
        )
        uiState = uiState.copy(
            currentPosition = p0.toModel(),
            caches = caches,
        )
    }
}

private val bannerHideCacheStatus: Set<CacheUserStatus> = setOf(
    CacheUserStatus.Locked,
    CacheUserStatus.Hidden,
)
