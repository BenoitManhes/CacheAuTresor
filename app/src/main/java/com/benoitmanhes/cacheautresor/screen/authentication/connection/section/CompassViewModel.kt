package com.benoitmanhes.cacheautresor.screen.authentication.connection.section

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.benoitmanhes.cacheautresor.utils.CompassHelper
import com.benoitmanhes.common.kotlin.extensions.safeNaN
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CompassViewModel @Inject constructor() : ViewModel() {

    var compassRotation: Float by mutableStateOf(0f)
        private set

    private var accelerometerReading: FloatArray = FloatArray(3)
    private var magnetometerReading: FloatArray = FloatArray(3)

    fun updateAccelerometer(values: FloatArray) {
        accelerometerReading = CompassHelper.lowPassFilter(values.clone(), accelerometerReading)
        updateHeading()
    }

    fun updateMagnetometer(values: FloatArray) {
        magnetometerReading = CompassHelper.lowPassFilter(values.clone(), magnetometerReading)
        updateHeading()
    }

    private fun updateHeading() {
        var heading = CompassHelper.calculateHeading(accelerometerReading, magnetometerReading)
        heading = CompassHelper.convertRadToDeg(heading)
        heading = CompassHelper.map180to360(heading)

        compassRotation = heading.safeNaN() * -1
    }
}
