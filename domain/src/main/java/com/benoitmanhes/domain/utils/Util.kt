package com.benoitmanhes.domain.utils

import java.security.SecureRandom
import java.util.Random

object Util {
    private const val AUTO_ID_LENGTH = 20
    private const val AUTO_ID_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"

    private val rand: Random = SecureRandom()

    fun autoId(): String {
        val builder = StringBuilder()
        val maxRandom = AUTO_ID_ALPHABET.length
        repeat(AUTO_ID_LENGTH) {
            builder.append(AUTO_ID_ALPHABET[rand.nextInt(maxRandom)])
        }
        return builder.toString()
    }
}
