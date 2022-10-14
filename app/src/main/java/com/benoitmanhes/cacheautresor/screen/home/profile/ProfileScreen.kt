package com.benoitmanhes.cacheautresor.screen.home.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.benoitmanhes.cacheautresor.common.composable.button.FilledButton

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel(),
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        FilledButton(
            text = "Se déconnecter",
            onClick = { viewModel.logout() },
        )
    }
}

@Preview
@Composable
private fun PreviewProfileScreen() {
    ProfileScreen()
}