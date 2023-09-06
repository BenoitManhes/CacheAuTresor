package com.benoitmanhes.domain.model

import com.benoitmanhes.domain.interfaces.Model
import kotlin.math.roundToLong

data class Distance(
    val millimeters: Long,
) : Model {

    val meterRounded: Long get() = convertDistanceUnitRound(DistanceUnit.METER)
    val kmsRounded: Long get() = convertDistanceUnitRound(DistanceUnit.KILOMETER)

    val meters: Double get() = convertDistanceUnit(DistanceUnit.METER)
    val kms: Double get() = convertDistanceUnit(DistanceUnit.KILOMETER)

    private fun convertDistanceUnit(unit: DistanceUnit): Double = millimeters / unit.inMillimeter.toDouble()
    private fun convertDistanceUnitRound(unit: DistanceUnit): Long = millimeters / unit.inMillimeter

    companion object {
        inline val Long.meters: Distance
            get() = buildFromDurationUnit(DistanceUnit.METER, this)

        inline val Int.meters: Distance
            get() = buildFromDurationUnit(DistanceUnit.METER, this.toLong())

        inline val Double.meters: Distance
            get() = buildFromDurationUnit(DistanceUnit.METER, this)
        inline val Float.meters: Distance
            get() = buildFromDurationUnit(DistanceUnit.METER, this.toDouble())

        fun buildFromDurationUnit(unit: DistanceUnit, value: Long): Distance =
            Distance(value * unit.inMillimeter)

        fun buildFromDurationUnit(unit: DistanceUnit, value: Double): Distance =
            Distance((value * unit.inMillimeter).roundToLong())

    }
}

enum class DistanceUnit(val inMillimeter: Long) {
    METER(1000),
    KILOMETER(1000000),
}
