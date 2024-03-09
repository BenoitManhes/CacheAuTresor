package com.benoitmanhes.domain.extension

import java.text.Normalizer

fun String.removeAccent(): String {
    val normalizedString = Normalizer.normalize(this, Normalizer.Form.NFD)
    val pattern = "\\p{InCombiningDiacriticalMarks}+".toRegex()
    return pattern.replace(normalizedString, "")
}
