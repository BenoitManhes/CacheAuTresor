package com.benoitmanhes.designsystem.molecule.textfield

import com.benoitmanhes.designsystem.utils.IconSpec

sealed interface TextFieldOption {

    data class LeadingIcon(
        val icon: IconSpec,
    ) : TextFieldOption

    data class ActionIcon(
        val icon: IconSpec,
        val onClick: () -> Unit,
    ) : TextFieldOption
}
