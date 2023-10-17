package com.benoitmanhes.cacheautresor.screen.modalbottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.benoitmanhes.designsystem.res.Dimens
import com.benoitmanhes.designsystem.theme.CTTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalBottomSheetView(
    viewModel: ModalBottomSheetViewModel = hiltViewModel(),
) {
    val modalBottomSheetState by viewModel.modalState.collectAsState()

    CTModalBottomSheetView(
        modalBottomSheetState = modalBottomSheetState,
        onDismiss = viewModel::consumeModal,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CTModalBottomSheetView(
    modalBottomSheetState: ModalBottomSheetState?,
    sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    onDismiss: () -> Unit = {},
) {
    modalBottomSheetState?.let { uiState ->
        ModalBottomSheet(
            onDismissRequest = {
                uiState.onDismiss()
                onDismiss()
            },
            sheetState = sheetState,
            shape = CTTheme.shape.bottomSheet,
            containerColor = CTTheme.color.surface,
            contentColor = CTTheme.color.onSurface,
            dragHandle = {},
            windowInsets = WindowInsets(0),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = Dimens.ModalBottomSheet.minHeight)
                    .background(CTTheme.color.surface)
            ) {
                Spacer(modifier = Modifier.size(Dimens.Corner.large))
                uiState.Content(this) {
                    coroutineScope.launch {
                        sheetState.hide()
                        uiState.onDismiss()
                        onDismiss()
                    }
                }
            }
        }
    }
}
