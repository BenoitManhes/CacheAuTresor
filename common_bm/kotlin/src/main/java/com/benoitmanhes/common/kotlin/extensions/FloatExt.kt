package com.benoitmanhes.common.kotlin.extensions

fun Float.safeNaN(): Float = if (this.isNaN()) 0f else this