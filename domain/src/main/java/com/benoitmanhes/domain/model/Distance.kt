package com.benoitmanhes.domain.model

import com.benoitmanhes.domain.interfaces.Model
import kotlin.math.roundToLong

@Suppress("MemberVisibilityCanBePrivate")
@JvmInline
value class Distance(
    val millimeters: Long,
) : Model, Comparable<Distance> {

    val meterRounded: Long get() = convertDistanceUnitRound(DistanceUnit.METER)
    val kmsRounded: Long get() = convertDistanceUnitRound(DistanceUnit.KILOMETER)

    val meters: Double get() = convertDistanceUnit(DistanceUnit.METER)
    val kms: Double get() = convertDistanceUnit(DistanceUnit.KILOMETER)

    private fun convertDistanceUnit(unit: DistanceUnit): Double = millimeters / unit.inMillimeter.toDouble()
    private fun convertDistanceUnitRound(unit: DistanceUnit): Long = millimeters / unit.inMillimeter

    companion object {
        val ZERO: Distance = Distance(0)
        val INFINITE: Distance = Distance(Long.MAX_VALUE / 2)

        inline val Long.meters: Distance
            get() = buildFromDistanceUnit(DistanceUnit.METER, this)

        inline val Int.meters: Distance
            get() = buildFromDistanceUnit(DistanceUnit.METER, this.toLong())

        inline val Double.meters: Distance
            get() = buildFromDistanceUnit(DistanceUnit.METER, this)
        inline val Float.meters: Distance
            get() = buildFromDistanceUnit(DistanceUnit.METER, this.toDouble())

        inline val Int.kms: Distance
            get() = buildFromDistanceUnit(DistanceUnit.KILOMETER, this.toLong())

        fun buildFromDistanceUnit(unit: DistanceUnit, value: Long): Distance =
            Distance(value * unit.inMillimeter)

        fun buildFromDistanceUnit(unit: DistanceUnit, value: Double): Distance =
            Distance((value * unit.inMillimeter).roundToLong())
    }

    override fun compareTo(other: Distance): Int {
        return this.millimeters.compareTo(other.millimeters)
    }
}

enum class DistanceUnit(val inMillimeter: Long) {
    METER(1_000),
    KILOMETER(1_000_000),
}
