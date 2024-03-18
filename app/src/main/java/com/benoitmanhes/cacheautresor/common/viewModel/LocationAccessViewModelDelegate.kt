package com.benoitmanhes.cacheautresor.common.viewModel

import android.content.Context
import com.benoitmanhes.domain.model.Coordinates
import kotlinx.coroutines.flow.StateFlow

interface LocationAccessViewModelDelegate {
    val currentLocation: StateFlow<Coordinates?>
    val launchLocationPermission: StateFlow<Boolean?>

    fun initLocationListener(
        context: Context,
        onAccessLocationRefused: () -> Unit,
    )

    fun requestLocation()

    fun onLocationPermissionRefused(
        onConfirm: () -> Unit,
    )

    fun consumeLaunchPermission()
}
