package com.benoitmanhes.cacheautresor.screen.modalbottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.benoitmanhes.designsystem.theme.CTTheme
import com.benoitmanhes.designsystem.utils.UiConstants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalBottomSheetView(
    viewModel: ModalBottomSheetViewModel = hiltViewModel(),
) {
    val modalBottomSheetState by viewModel.modalState.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    CTModalBottomSheetView(
        modalBottomSheetState = modalBottomSheetState,
        coroutineScope = coroutineScope,
        onDismiss = viewModel::consumeModal,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CTModalBottomSheetView(
    modalBottomSheetState: ModalBottomSheetState?,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    sheetState: SheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
        confirmValueChange = { modalBottomSheetState?.option?.contains(ModalBottomSheetOption.Lock)?.not() ?: true },
    ),
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
            containerColor = Color.Transparent,
            contentColor = CTTheme.color.textOnSurface,
            dragHandle = {},
            windowInsets = WindowInsets(0),
        ) {
            BoxWithConstraints(
                modifier = Modifier.statusBarsPadding(),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(
                            max = this@BoxWithConstraints.maxHeight.times(
                                uiState.option
                                    .filterIsInstance<ModalBottomSheetOption.MaxHeightRatio>()
                                    .firstOrNull()
                                    ?.ratio ?: 1f
                            ),
                        )
                ) {
                    Surface(
                        shape = CTTheme.shape.mountain,
                        color = CTTheme.color.surface,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(UiConstants.Shape.mountainRatio),
                    ) {}
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(CTTheme.color.surface)
                            .navigationBarsPadding()
                    ) {
                        //                    Spacer(modifier = Modifier.size(Dimens.Corner.large))
                        uiState.Content(this) {
                            coroutineScope.launch {
                                sheetState.hide()
                            }
                            uiState.onDismiss()
                            onDismiss()
                        }
                    }
                }
            }
        }
    }
}
