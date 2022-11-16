package com.benoitmanhes.server.extensions

import com.benoitmanhes.core.error.CTRemoteError
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.firestore.FirebaseFirestoreException

fun Throwable.toCTError(defaultError: Throwable = this): Throwable =
    when (this) {
        is FirebaseAuthInvalidUserException -> CTRemoteError.AuthenticationUserEmailNoExist(this)
        is FirebaseAuthInvalidCredentialsException -> handleFirebaseAuthInvalidCredentialsException(this)
        is FirebaseNetworkException -> CTRemoteError.NetworkException(this)
        is FirebaseFirestoreException -> handleFirebaseFirestoreException(this)
        else -> null
    } ?: defaultError

private fun handleFirebaseFirestoreException(e: FirebaseFirestoreException): CTRemoteError? = when (e.code) {
    FirebaseFirestoreException.Code.UNAVAILABLE -> CTRemoteError.NetworkException(e)
    else -> null
}

private fun handleFirebaseAuthInvalidCredentialsException(e: FirebaseAuthInvalidCredentialsException): CTRemoteError? =
    when (e.errorCode) {
        "ERROR_USER_NOT_FOUND" -> CTRemoteError.AuthenticationUserEmailNoExist(e)
        "ERROR_INVALID_EMAIL" -> CTRemoteError.AuthenticationEmailInvalidForm(e)
        else -> CTRemoteError.AuthenticationInvalidCredentialError(e)
    }
