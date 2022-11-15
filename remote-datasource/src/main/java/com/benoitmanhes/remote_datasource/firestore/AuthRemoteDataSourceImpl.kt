package com.benoitmanhes.remote_datasource.firestore

import com.benoitmanhes.core.error.CTRemoteError
import com.benoitmanhes.domain.interfaces.remotedatasource.AuthRemoteDataSource
import com.benoitmanhes.domain.model.Account
import com.benoitmanhes.remote_datasource.extensions.withCoroutine
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore

class AuthRemoteDataSourceImpl(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
) : AuthRemoteDataSource {

    companion object {
        const val ACCOUNT_TOKENS_COLLECTION: String = "accountTokens"
    }

    override fun getCurrentAccount(): Account? = auth.currentUser?.toAccount()

    override suspend fun createAuthAccount(email: String, password: String, explorerId: String): Account {
        auth.createUserWithEmailAndPassword(email, password).withCoroutine()
        (auth.currentUser ?: throw CTRemoteError.UnexpectedResult(message = "No user after user creation"))
            .updateProfile(
                userProfileChangeRequest {
                    displayName = explorerId
                }
            ).withCoroutine()

        return getCurrentAccount() ?: throw CTRemoteError.UnexpectedResult(message = "No account after account update")
    }

    override suspend fun login(email: String, password: String): Account {
        auth.signInWithEmailAndPassword(email, password).withCoroutine()
        return getCurrentAccount() ?: throw CTRemoteError.UnexpectedResult(message = "No account after login")
    }

    override fun logout(): Unit = auth.signOut()

    override suspend fun isAuthCodeValid(code: String): Boolean {
        val accountToken = firestore.collection(ACCOUNT_TOKENS_COLLECTION)
            .document(code)
            .get()
            .withCoroutine()
        return accountToken.exists()
    }

    override suspend fun deleteAuthCode(code: String) {
        firestore.collection(ACCOUNT_TOKENS_COLLECTION)
            .document(code)
            .delete()
            .withCoroutine()
    }

    private fun FirebaseUser.toAccount(): Account =
        Account(
            explorerId = this.displayName ?: throw CTRemoteError.ParsingFailed("Failed to parse account, displayName null"),
            email = this.email ?: throw CTRemoteError.ParsingFailed("Failed to parse account, email null"),
        )
}
