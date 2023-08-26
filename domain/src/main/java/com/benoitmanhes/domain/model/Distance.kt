package com.benoitmanhes.domain.model

import com.benoitmanhes.domain.interfaces.Model

data class Distance(
    val rawValue: Long,
) : Model {

    fun inMeter(): Double = convertDurationUnit(DistanceUnit.METER)
    fun inKm(): Double = convertDurationUnit(DistanceUnit.KILOMETER)

    private fun convertDurationUnit(unit: DistanceUnit): Double = rawValue / unit.inMillimeter.toDouble()

    companion object {
        fun Long.meters(): Distance = buildFromDurationUnit(DistanceUnit.METER, this)
        fun Int.meters(): Distance = buildFromDurationUnit(DistanceUnit.METER, this.toLong())

        private fun buildFromDurationUnit(unit: DistanceUnit, value: Long): Distance =
            Distance(value * unit.inMillimeter)

    }
}

enum class DistanceUnit(val inMillimeter: Long) {
    MILLIMETER(1),
    METER(1000),
    KILOMETER(1000000),
}
