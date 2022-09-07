package com.benoitmanhes.cacheautresor.screen.login


@Composable
fun LoginScreen(
    viewModel: LoginViewModel = viewModel(),
) {
    val focusRequester = remember { FocusRequester() }

    Column(
        modifier = Modifier
            .imePadding()
            .fillMaxSize(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.4f),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                modifier = Modifier.size(Dimens.Size.loginImageSize),
                painter = painterResource(id = R.drawable.logo_monochrome),
                contentDescription = null,
                colorFilter = ColorFilter.tint(AppTheme.colors.onBackground),
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.6f),
            contentAlignment = Alignment.TopCenter
        ) {
            LoginInputSection(
                modifier = Modifier
                    .padding(horizontal = Dimens.Margin.huge),
            )
        }
    }
}

@Preview
@Composable
private fun PreviewLoginScreen() {
    AppTheme {
        LoginScreen()
    }
}
