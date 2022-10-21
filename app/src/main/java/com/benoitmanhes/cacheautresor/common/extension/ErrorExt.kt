package com.benoitmanhes.cacheautresor.common.extension

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.domain.structure.BError

@Composable
fun BError.getUIErrorMessage(): String = when (this) {
    is BError.AuthenticationCodeInvalidError -> stringResource(id = R.string.errorMessage_authCodeInvalid)
    else -> message ?: stringResource(id = R.string.errorMessage_default)
}