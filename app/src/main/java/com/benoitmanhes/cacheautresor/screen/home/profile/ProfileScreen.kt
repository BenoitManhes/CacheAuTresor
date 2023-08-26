package com.benoitmanhes.cacheautresor.screen.home.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.benoitmanhes.designsystem.molecule.button.primarybutton.CTPrimaryButton
import com.benoitmanhes.designsystem.utils.TextSpec

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel(),
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CTPrimaryButton(
            text = TextSpec.RawString("Se déconnecter"),
            onClick = { viewModel.logout() },
        )
    }
}

@Preview
@Composable
private fun PreviewProfileScreen() {
    ProfileScreen()
}
