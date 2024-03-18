package com.benoitmanhes.cacheautresor.common.extensions

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build

fun Context.appVersionCode(): Long = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
    packageInfo().longVersionCode
} else {
    @Suppress("DEPRECATION")
    packageInfo().versionCode.toLong()
}

private fun Context.packageInfo(): PackageInfo {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        packageManager.getPackageInfo(packageName, PackageManager.PackageInfoFlags.of(0))
    } else {
        @Suppress("DEPRECATION")
        packageManager.getPackageInfo(this.packageName, 0)
    }
}
