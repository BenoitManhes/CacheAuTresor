package com.benoitmanhes.designsystem.molecule.button.primarybutton

import androidx.compose.runtime.Stable
import com.benoitmanhes.designsystem.utils.IconSpec

@Stable
sealed interface PrimaryButtonOption {
    data class LeadingIcon(val icon: IconSpec) : PrimaryButtonOption
}
