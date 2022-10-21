package com.benoitmanhes.cacheautresor.common.composable.bottomsheet

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AppModalBottomSheetLayout(
    displayBottomSheet: Boolean,
    onBottomSheetDisplayChange: (isDisplayed: Boolean) -> Unit,
    sheetContent: @Composable () -> Unit,
    content: @Composable () -> Unit,
) {

    val modalBottomSheetState: ModalBottomSheetState by remember {
        mutableStateOf(
            ModalBottomSheetState(
                initialValue = ModalBottomSheetValue.Hidden,
                confirmStateChange = { newState ->
                    // Called before bottom sheet disappeared.
                    if (newState == ModalBottomSheetValue.Hidden) {
                        onBottomSheetDisplayChange(false)
                    }
                    true // confirm state change
                },
            )
        )
    }

    // Use a LaunchedEffect instead of coroutineScope (https://stackoverflow.com/a/73338719/4249202)
    LaunchedEffect(key1 = displayBottomSheet) {
        if (displayBottomSheet && !modalBottomSheetState.isVisible) {
            modalBottomSheetState.show()
        } else {
            if (modalBottomSheetState.isVisible) {
                modalBottomSheetState.hide()
            }
        }
    }

    ModalBottomSheetLayout(
        sheetContent = { sheetContent() },
        sheetState = modalBottomSheetState,
        content = content,
        sheetBackgroundColor = MaterialTheme.colorScheme.surface,
    )
}