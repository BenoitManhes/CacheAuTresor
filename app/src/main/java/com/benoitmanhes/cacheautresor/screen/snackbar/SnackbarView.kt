package com.benoitmanhes.cacheautresor.screen.snackbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.benoitmanhes.designsystem.molecule.snackbar.ErrorSnackbar
import com.benoitmanhes.designsystem.molecule.snackbar.InfoSnackbar
import com.benoitmanhes.common.compose.text.TextSpec

@Composable
fun SnackbarView(
    bottomPadding: Int = 0,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    snackbarViewModel: SnackbarViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val snackbarState by snackbarViewModel.shownSnackBar.collectAsStateWithLifecycle()
    var lastData: BBSnackbarData by remember {
        mutableStateOf(BBSnackbarData.Error(TextSpec.RawString("")))
    }

    LaunchedEffect(snackbarState) {
        snackbarState?.let { _state ->
            lastData = _state
            _state.message.string(context)?.let { text ->
                snackbarHostState.showSnackbar(message = text)
            }
            snackbarViewModel.consumeSnackBar()
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
    ) {
        with(LocalDensity.current) {
            SnackbarHost(
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(bottom = bottomPadding.toDp())
                    .align(Alignment.BottomStart),
                hostState = snackbarHostState,
            ) { data ->
                when (lastData) {
                    is BBSnackbarData.Info -> InfoSnackbar(data)
                    is BBSnackbarData.Error -> ErrorSnackbar(data)
                }
            }
        }
    }
}
