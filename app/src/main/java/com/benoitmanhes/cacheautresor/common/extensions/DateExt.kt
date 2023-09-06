package com.benoitmanhes.cacheautresor.common.extensions

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date

private val mediumFormat = SimpleDateFormat.getDateInstance(DateFormat.MEDIUM)

fun Date.mediumFormat(): String = mediumFormat.format(this)
