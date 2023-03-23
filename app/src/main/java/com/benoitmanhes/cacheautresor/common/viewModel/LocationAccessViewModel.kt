package com.benoitmanhes.cacheautresor.common.viewModel

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel
import com.benoitmanhes.cacheautresor.utils.AppConstants
import kotlin.time.Duration

abstract class LocationAccessViewModel(
    private val locationManager: LocationManager,
) : ViewModel(), LocationListener {

    protected open val minTimeInterval: Duration = AppConstants.Location.defaultMinDurationInterval
    protected open val minDistanceIntervalMeter: Float = AppConstants.Location.defaultMinDistanceIntervalMeter

    fun initLocationListener(context: Context) {
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
            locationManager.requestLocationUpdates(locationProvider, minTimeInterval.inWholeMilliseconds, minDistanceIntervalMeter, this)
            locationManager.getLastKnownLocation(locationProvider)?.let(::onLocationChanged)
        }
    }

    abstract fun onAccessLocationRefused()
}