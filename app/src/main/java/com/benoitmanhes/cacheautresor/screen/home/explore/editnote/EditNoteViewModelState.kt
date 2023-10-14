package com.benoitmanhes.cacheautresor.screen.home.explore.editnote

data class EditNoteViewModelState(
    val note: String? = null,
    val onTextChanged: (String?) -> Unit,
    val onSaved: () -> Unit,
)
