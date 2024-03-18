package com.benoitmanhes.cacheautresor.common.viewModel

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext

@Composable
fun LocationAccessView(
    viewModel: LocationAccessViewModelDelegate,
) {
    val context = LocalContext.current
    val launchLocationPermission by viewModel.launchLocationPermission.collectAsState()

    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
    ) { granted ->
        if (!granted) {
            viewModel.onLocationPermissionRefused {
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                intent.data = Uri.fromParts("package", context.packageName, null)
                context.startActivity(intent)
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.initLocationListener(
            context = context,
            onAccessLocationRefused = {
                locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        )
    }

    LaunchedEffect(locationPermissionLauncher) {
        if (launchLocationPermission == true) {
            locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            viewModel.consumeLaunchPermission()
        }
    }
}
