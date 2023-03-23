package com.benoitmanhes.cacheautresor.error

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.res.stringResource
import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.core.error.CTDomainError

@Composable
@ReadOnlyComposable
fun CTDomainError.localizedTitle(): String = when (code) {
    CTDomainError.Code.ACCOUNT_CREATION_INVALID_TOKEN -> null
    CTDomainError.Code.ACCOUNT_CREATION_EXPLORER_NAME_UNAVAILABLE -> null
    CTDomainError.Code.AUTHENTICATION_INVALID_CREDENTIAL -> null
    CTDomainError.Code.AUTHENTICATION_EMAIL_INVALID_FORM -> null
    CTDomainError.Code.AUTHENTICATION_USER_EMAIL_NO_EXIST -> null
    CTDomainError.Code.EXPLORER_NOT_FOUND -> null
    CTDomainError.Code.NO_INTERNET -> stringResource(id = R.string.error_networkConnection_title)
    CTDomainError.Code.SERVER_ERROR -> null
    CTDomainError.Code.UNEXPECTED -> null
    CTDomainError.Code.UNKNOWN -> null
} ?: stringResource(id = R.string.error_defaultTitle)

@Composable
@ReadOnlyComposable
fun CTDomainError.localizedDescription(): String = when (code) {
    CTDomainError.Code.ACCOUNT_CREATION_INVALID_TOKEN -> stringResource(id = R.string.errorMessage_authCodeInvalid)
    CTDomainError.Code.ACCOUNT_CREATION_EXPLORER_NAME_UNAVAILABLE -> stringResource(
        id = R.string.accountCreation_name_error
    )
    CTDomainError.Code.AUTHENTICATION_INVALID_CREDENTIAL -> stringResource(
        id = R.string.authentication_error_invalidPassword
    )
    CTDomainError.Code.AUTHENTICATION_EMAIL_INVALID_FORM -> stringResource(
        id = R.string.authentication_error_emailFormInvalid
    )
    CTDomainError.Code.AUTHENTICATION_USER_EMAIL_NO_EXIST -> stringResource(
        id = R.string.authentication_error_noUserEmail
    )
    CTDomainError.Code.EXPLORER_NOT_FOUND -> null
    CTDomainError.Code.NO_INTERNET -> stringResource(id = R.string.error_networkConnection_description)
    CTDomainError.Code.SERVER_ERROR -> null
    CTDomainError.Code.UNEXPECTED -> null
    CTDomainError.Code.UNKNOWN -> null
} ?: stringResource(id = R.string.error_defaultMessage)
