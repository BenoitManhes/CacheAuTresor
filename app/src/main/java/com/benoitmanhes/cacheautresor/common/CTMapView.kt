package com.benoitmanhes.cacheautresor.common

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.benoitmanhes.cacheautresor.R
import org.osmdroid.config.Configuration
import org.osmdroid.views.MapView

@Composable
fun CTMapView(
    modifier: Modifier = Modifier,
    mapViewState: MapView = rememberMapViewWithLifecycle(),
) {
    AndroidView(
        modifier = modifier,
        factory = { mapViewState },
    )
}

@Composable
fun rememberMapViewWithLifecycle(
    context: Context = LocalContext.current,
    lifecycle: Lifecycle = LocalLifecycleOwner.current.lifecycle,
): MapView {
    val mapView: MapView = remember {
        MapView(context).apply {
            id = R.id.osm_map_view
            clipToOutline = true
        }
    }

    val lifecycleObserver = rememberMapLifecycleObserver(mapView)
    DisposableEffect(lifecycle) {
        lifecycle.addObserver(lifecycleObserver)
        onDispose {
            lifecycle.removeObserver(lifecycleObserver)
        }
    }

    return mapView
}

@Composable
private fun rememberMapLifecycleObserver(mapView: MapView): LifecycleEventObserver =
    remember(mapView) {
        val sharedPref = mapView.context.getSharedPreferences("map shared pref", Context.MODE_PRIVATE)
        val configuration = Configuration.getInstance()
        LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> {
                    Configuration.getInstance().load(mapView.context, sharedPref)
                    mapView.onResume()
                }

                Lifecycle.Event.ON_PAUSE -> {
                    configuration.save(mapView.context, sharedPref)
                    mapView.onPause()
                }

                else -> {} /* no-op */
            }
        }
    }
