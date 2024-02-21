package com.benoitmanhes.cacheautresor.error

import com.benoitmanhes.cacheautresor.R
import com.benoitmanhes.common.compose.text.TextSpec
import com.benoitmanhes.core.error.CTDomainError

fun CTDomainError.localizedTitle(): TextSpec = when (code) {
    CTDomainError.Code.ACCOUNT_CREATION_INVALID_TOKEN -> null
    CTDomainError.Code.ACCOUNT_CREATION_EXPLORER_NAME_UNAVAILABLE -> null
    CTDomainError.Code.AUTHENTICATION_INVALID_CREDENTIAL -> null
    CTDomainError.Code.AUTHENTICATION_EMAIL_INVALID_FORM -> null
    CTDomainError.Code.AUTHENTICATION_USER_EMAIL_NO_EXIST -> null
    CTDomainError.Code.CACHE_INVALID_LOG_CODE -> null
    CTDomainError.Code.CACHE_INVALID_UNLOCK_CODE -> null
    CTDomainError.Code.CACHE_NOT_FOUND -> null
    CTDomainError.Code.CACHE_STEP_NOT_FOUND -> null
    CTDomainError.Code.CREW_MEMBER_NAME_UNAVAILABLE -> null
    CTDomainError.Code.EXPLORER_NOT_FOUND -> null
    CTDomainError.Code.INITIAL_COORDINATES_INVALID -> TextSpec.Resources(R.string.pickInitCoordinates_alertArea_title)
    CTDomainError.Code.NO_AUTHENTICATION -> null
    CTDomainError.Code.NO_INTERNET -> TextSpec.Resources(id = R.string.error_networkConnection_title)
    CTDomainError.Code.SERVER_ERROR -> null
    CTDomainError.Code.UNEXPECTED -> null
    CTDomainError.Code.UNKNOWN -> null
    CTDomainError.Code.CACHE_USER_PROGRESS_NOT_FOUND -> null
} ?: TextSpec.Resources(id = R.string.error_defaultTitle)

fun CTDomainError.localizedDescription(): TextSpec = when (code) {
    CTDomainError.Code.ACCOUNT_CREATION_INVALID_TOKEN -> TextSpec.Resources(id = R.string.errorMessage_authCodeInvalid)
    CTDomainError.Code.ACCOUNT_CREATION_EXPLORER_NAME_UNAVAILABLE -> TextSpec.Resources(
        id = R.string.accountCreation_name_error
    )

    CTDomainError.Code.AUTHENTICATION_INVALID_CREDENTIAL -> TextSpec.Resources(
        id = R.string.authentication_error_invalidPassword
    )

    CTDomainError.Code.AUTHENTICATION_EMAIL_INVALID_FORM -> TextSpec.Resources(
        id = R.string.authentication_error_emailFormInvalid
    )

    CTDomainError.Code.AUTHENTICATION_USER_EMAIL_NO_EXIST -> TextSpec.Resources(
        id = R.string.authentication_error_noUserEmail
    )

    CTDomainError.Code.CACHE_INVALID_UNLOCK_CODE -> TextSpec.Resources(R.string.logModal_error_final)
    CTDomainError.Code.CACHE_INVALID_LOG_CODE -> TextSpec.Resources(R.string.logModal_error_final)
    CTDomainError.Code.CACHE_NOT_FOUND -> null
    CTDomainError.Code.CACHE_STEP_NOT_FOUND -> null
    CTDomainError.Code.CACHE_USER_PROGRESS_NOT_FOUND -> null
    CTDomainError.Code.CREW_MEMBER_NAME_UNAVAILABLE -> TextSpec.Resources(R.string.modalEditCrewName_error)
    CTDomainError.Code.EXPLORER_NOT_FOUND -> null
    CTDomainError.Code.INITIAL_COORDINATES_INVALID -> TextSpec.Resources(R.string.pickInitCoordinates_alertArea_message)
    CTDomainError.Code.NO_AUTHENTICATION -> null
    CTDomainError.Code.NO_INTERNET -> TextSpec.Resources(id = R.string.error_networkConnection_description)
    CTDomainError.Code.SERVER_ERROR -> null
    CTDomainError.Code.UNEXPECTED -> null
    CTDomainError.Code.UNKNOWN -> null
} ?: TextSpec.Resources(id = R.string.error_defaultMessage)
