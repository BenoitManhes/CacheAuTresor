package com.benoitmanhes.domain.usecase.coordinates

import com.benoitmanhes.domain.model.Coordinates
import com.benoitmanhes.domain.model.DegreeMinutes
import com.benoitmanhes.domain.model.DegreeMinutesSeconds
import com.benoitmanhes.domain.usecase.CTUseCase
import java.lang.Exception
import javax.inject.Inject

class ParseStringToCoordinatesUseCase @Inject constructor() : CTUseCase() {
    operator fun invoke(input: String): Coordinates? = try {
        val digitsGroups = input
            .replace(specialSymbolRegex, " ")
            .replace(letterSymbolRegex, "")
            .replace(",", ".")
            .split(" ")
            .filter { it.isNotBlank() }
            .map { it.toDouble() }

        when (digitsGroups.count()) {
            2 -> Coordinates(digitsGroups[0], digitsGroups[1])
            4 -> Coordinates.fromDegreeMinute(
                dmLatitude = DegreeMinutes(digitsGroups[0].toInt(), digitsGroups[1]),
                dmLongitude = DegreeMinutes(digitsGroups[2].toInt(), digitsGroups[3]),
            )

            6 -> Coordinates.fromDMS(
                dmsLatitude = DegreeMinutesSeconds(digitsGroups[0].toInt(), digitsGroups[1].toInt(), digitsGroups[2]),
                dmsLongitude = DegreeMinutesSeconds(digitsGroups[3].toInt(), digitsGroups[4].toInt(), digitsGroups[5]),
            )

            else -> null
        }
    } catch (e: Exception) {
        null
    }

    companion object {
        private val specialSymbolRegex = Regex("[^\\w.,]")
        private val letterSymbolRegex = Regex("[a-zA-Z]")
    }
}
