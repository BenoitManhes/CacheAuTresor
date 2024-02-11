package com.benoitmanhes.cacheautresor.screen.home.explore.editnote

import androidx.compose.runtime.Immutable

@Immutable
data class EditNoteViewModelState(
    val note: String? = null,
    val onTextChanged: (String?) -> Unit,
    val onSaved: () -> Unit,
)
