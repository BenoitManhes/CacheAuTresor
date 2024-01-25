package com.benoitmanhes.cacheautresor.navigation

import android.net.Uri

interface CTDestination {
    val route: String
}

interface CTSingleArgDestination : CTDestination {
    val arg: String
    val path: String
    override val route: String get() = "$path?$arg={$arg}"
    fun getRoute(argValue: String): String =
        Uri.Builder().apply {
            path(path)
            appendQueryParameter(arg, argValue)
        }.build().toString()
}
