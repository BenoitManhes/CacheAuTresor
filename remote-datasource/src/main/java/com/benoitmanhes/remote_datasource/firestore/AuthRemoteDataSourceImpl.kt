package com.benoitmanhes.remote_datasource.firestore

import com.benoitmanhes.domain.interfaces.remotedatasource.AuthRemoteDataSource
import com.benoitmanhes.domain.model.Account
import com.benoitmanhes.domain.structure.BError
import com.benoitmanhes.domain.structure.BResult
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class AuthRemoteDataSourceImpl(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
) : AuthRemoteDataSource {

    companion object {
        const val ACCOUNT_TOKENS_COLLECTION: String = "accountTokens"
    }

    override fun getCurrentAccount(): Account? = auth.currentUser?.toAccount()

    override suspend fun createAuthAccount(email: String, password: String): BResult<Account> =
        auth.createUserWithEmailAndPassword(email, password).fetchAccountResult()

    override suspend fun login(email: String, password: String): BResult<Account> =
        auth.signInWithEmailAndPassword(email, password).fetchAccountResult()

    override fun logout(): Unit = auth.signOut()

    override suspend fun isAuthCodeValid(code: String): BResult<Unit> =
        suspendCoroutine { cont ->
            firestore.collection(ACCOUNT_TOKENS_COLLECTION)
                .document(code)
                .get()
                .addOnSuccessListener { accountToken ->
                    cont.resume(
                        if (accountToken.exists()) BResult.Success(Unit) else BResult.Failure(BError.AuthenticationCodeInvalidError)
                    )
                }
                .addOnFailureListener {
                    cont.resume(BResult.Failure(BError.UnknownError(cause = it)))
                }
        }

    override suspend fun deleteAuthCode(code: String): BResult<Unit> = suspendCoroutine { continuation ->
        firestore.collection(ACCOUNT_TOKENS_COLLECTION)
            .document(code)
            .delete()
            .addOnFailureListener {
                continuation.resume(BResult.Failure(BError.RemoteObjectEditingError(message = "deleteAuthCode failed", cause = it)))
            }
            .addOnSuccessListener {
                continuation.resume(BResult.Success(Unit))
            }
    }

    private fun FirebaseUser.toAccount(): Account = Account(
        explorerId = this.displayName,
        email = this.email!!,
    )

    private suspend fun Task<AuthResult>.fetchAccountResult(): BResult<Account> = suspendCoroutine { continuation ->
        this.addOnFailureListener { e ->
            continuation.resume(BResult.Failure(BError.AuthenticationError(cause = e)))
        }
            .addOnSuccessListener {
                val accountResult = getCurrentAccount()?.let {
                    BResult.Success(it)
                } ?: BResult.Failure(BError.UnexpectedResult)
                continuation.resume(accountResult)
            }
    }
}
