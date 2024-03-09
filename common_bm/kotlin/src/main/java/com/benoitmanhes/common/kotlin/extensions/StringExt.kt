package com.benoitmanhes.common.kotlin.extensions

fun String?.nullIfBlank(): String? = this?.takeIf { it.isNotBlank() }
