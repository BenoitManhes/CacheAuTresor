package com.benoitmanhes.cacheautresor.common.viewModel

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import androidx.core.app.ActivityCompat
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.common.composable.modalbottomsheet.ClassicModalBottomSheet
import com.benoitmanhes.cacheautresor.common.extensions.toModel
import com.benoitmanhes.cacheautresor.screen.modalbottomsheet.ModalBottomSheetManager
import com.benoitmanhes.cacheautresor.utils.AppConstants
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.designsystem.molecule.button.primarybutton.PrimaryButtonState
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.domain.model.Coordinates
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import kotlin.time.Duration

class LocationAccessViewModelDelegateImpl @Inject constructor(
    private val locationManager: LocationManager,
    private val modalBottomSheetManager: ModalBottomSheetManager,
) : LocationAccessViewModelDelegate, LocationListener {

    private val minTimeInterval: Duration = AppConstants.Location.defaultMinDurationInterval
    private val minDistanceIntervalMeter: Float = AppConstants.Location.defaultMinDistanceIntervalMeter

    private val _currentLocation = MutableStateFlow<Coordinates?>(null)
    override val currentLocation: StateFlow<Coordinates?> get() = _currentLocation.asStateFlow()

    private val _launchLocationPermission = MutableStateFlow<Boolean?>(null)
    override val launchLocationPermission: StateFlow<Boolean?> get() = _launchLocationPermission.asStateFlow()

    private var shouldShowLocationModal: Boolean = false

    override fun initLocationListener(
        context: Context,
        onAccessLocationRefused: () -> Unit,
    ) {
        if (
            ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            onAccessLocationRefused()
        } else {
            val locationProvider = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                LocationManager.FUSED_PROVIDER
            } else {
                LocationManager.GPS_PROVIDER
            }
            locationManager.requestLocationUpdates(
                locationProvider,
                minTimeInterval.inWholeMilliseconds,
                minDistanceIntervalMeter,
                this
            )
            locationManager.getLastKnownLocation(locationProvider)?.let(::onLocationChanged)
        }
    }

    override fun requestLocation() {
        shouldShowLocationModal = true
        _launchLocationPermission.value = true
    }

    override fun onLocationChanged(location: Location) {
        _currentLocation.value = location.toModel()
    }

    override fun onLocationPermissionRefused(
        onConfirm: () -> Unit,
    ) {
        if (shouldShowLocationModal) {
            modalBottomSheetManager.showModal(
                ClassicModalBottomSheet(
                    icon = { CTTheme.icon.Location },
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

    override fun consumeLaunchPermission() {
        _launchLocationPermission.value = null
    }
}
