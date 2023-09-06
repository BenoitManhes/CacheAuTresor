package com.benoitmanhes.domain.model

import com.benoitmanhes.domain.interfaces.Model
import kotlinx.serialization.Serializable
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

typealias DecimalDegree = Double
typealias DegreeMinutes = Pair<Int, Double>
typealias DegreeMinutesSeconds = Triple<Int, Int, Double>

/**
 * There is 3 GPS format
 * [DecimalDegree] DD,DDDDDD
 * [DegreeMinutes] DD°MM,MMMM
 * [DegreeMinutesSeconds]kDD°MM'SS,SS
 * @param latitude in [DecimalDegree] by default
 * @param longitude in [DecimalDegree] by default
 */
@Serializable
data class Coordinates(
    val latitude: DecimalDegree,
    val longitude: DecimalDegree,
) : Model {

    val latitudeDM: DegreeMinutes
        get() = decimalDegreesToDegreesMinutes(latitude)

    val longitudeDM: DegreeMinutes
        get() = decimalDegreesToDegreesMinutes(longitude)

    val latitudeDMS: DegreeMinutesSeconds
        get() = decimalDegreesToDMS(latitude)

    val longitudeDMS: DegreeMinutesSeconds
        get() = decimalDegreesToDMS(longitude)

    private fun decimalDegreesToDegreesMinutes(decimalDegrees: DecimalDegree): DegreeMinutes {
        val degrees = decimalDegrees.toInt()
        val minutes = (decimalDegrees - degrees) * MINUTES_IN_DEGREE
        return degrees to minutes
    }

    private fun decimalDegreesToDMS(decimalDegrees: DecimalDegree): DegreeMinutesSeconds {
        val degrees = decimalDegrees.toInt()
        val remainingMinutes = (decimalDegrees - degrees) * MINUTES_IN_DEGREE
        val minutes = remainingMinutes.toInt()
        val seconds = (remainingMinutes - minutes) * SECONDS_IN_MINUTES
        return Triple(degrees, minutes, seconds)
    }

    private fun dmsToDecimalDegrees(dms: DegreeMinutesSeconds): DecimalDegree =
        dms.first + dms.second / MINUTES_IN_DEGREE + dms.third / SECOND_IN_DEGREE

    private fun degreeMinutesToDecimalDegree(dm: DegreeMinutes): DecimalDegree =
        dm.first + dm.second / MINUTES_IN_DEGREE

    enum class Format {
        DD, DM, DMS;

        companion object {
            fun next(current: Format): Format {
                val allFormats = Format.values()
                val currentIndex = allFormats.indexOf(current)

                val nextIndex = (currentIndex + 1) % allFormats.size
                return allFormats[nextIndex]
            }
        }
    }
}

private const val SECONDS_IN_MINUTES: Long = 60
private const val MINUTES_IN_DEGREE: Long = 60
private const val SECOND_IN_DEGREE: Long = SECONDS_IN_MINUTES * MINUTES_IN_DEGREE
