package com.benoitmanhes.cacheautresor.common.viewModel

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationListener
import android.location.LocationManager
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel

abstract class LocationAccessViewModel(
    private val locationManager: LocationManager,
) : ViewModel(), LocationListener {

    protected open val minTimeIntervalMillis: Long = 5000
    protected open val minDistanceIntervalMeter: Float = 5f

    fun initLocationListener(context: Context) {
        if (
            ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            onAccessLocationRefused()
        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTimeIntervalMillis, minDistanceIntervalMeter, this)
            locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)?.let(::onLocationChanged)
        }
    }

    abstract fun onAccessLocationRefused()
}