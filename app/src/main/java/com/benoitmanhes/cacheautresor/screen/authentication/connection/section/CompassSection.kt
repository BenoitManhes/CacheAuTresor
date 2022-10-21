package com.benoitmanhes.cacheautresor.screen.authentication.connection.section

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.cacheautresor.ui.theme.AppTheme
import kotlinx.coroutines.flow.collectLatest

@Composable
fun CompassSection(
    modifier: Modifier = Modifier,
    viewModel: CompassViewModel = hiltViewModel(),
) {
    val context = LocalContext.current

    DisposableEffect(true) {
        val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        val magneticField = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)

        val sensorEventListener = object : SensorEventListener {
            override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
                // No-op
            }

            override fun onSensorChanged(event: SensorEvent) {
                when (event.sensor.type) {
                    Sensor.TYPE_ACCELEROMETER -> {
                        viewModel.updateAccelerometer(event.values)
                    }
                    Sensor.TYPE_MAGNETIC_FIELD -> {
                        viewModel.updateMagnetometer(event.values)
                    }
                }
            }
        }

        sensorManager.registerListener(sensorEventListener, accelerometer, SensorManager.SENSOR_DELAY_UI)
        sensorManager.registerListener(sensorEventListener, magneticField, SensorManager.SENSOR_DELAY_UI)

        onDispose {
            sensorManager.unregisterListener(sensorEventListener, accelerometer)
            sensorManager.unregisterListener(sensorEventListener, magneticField)
        }
    }

    val smoothHeading by smoothRotation(viewModel.compassRotation)
    val angle: Float by animateFloatAsState(
        targetValue = smoothHeading,
        animationSpec = tween(
            durationMillis = COMPASS_ANIMATION_DURATION,
            easing = LinearEasing,
        ),
    )


    Image(
        modifier = modifier
            .rotate(angle),
        painter = painterResource(id = R.drawable.logo_monochrome),
        contentDescription = null,
        colorFilter = ColorFilter.tint(AppTheme.colors.onBackground),
    )
}

@Composable
private fun smoothRotation(rotation: Float): MutableState<Float> {
    val storedRotation = remember { mutableStateOf(rotation) }

    LaunchedEffect(rotation) {
        snapshotFlow { rotation }
            .collectLatest { newRotation ->
                val diff = newRotation - storedRotation.value
                val shortestDiff = when {
                    diff > 180 -> diff - 360
                    diff < -180 -> diff + 360
                    else -> diff
                }
                storedRotation.value = storedRotation.value + shortestDiff
            }
    }

    return storedRotation
}

private const val COMPASS_ANIMATION_DURATION: Int = 100
