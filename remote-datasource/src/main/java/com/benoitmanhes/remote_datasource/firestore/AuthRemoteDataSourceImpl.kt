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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class AuthRemoteDataSourceImpl(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
) : AuthRemoteDataSource {

    companion object {
        const val ACCOUNT_TOKENS_COLLECTION: String = "accountTokens"
    }

    override fun getCurrentUserAccount(): Account? = auth.currentUser?.toAccount()

    override fun createAuthAccount(email: String, password: String): Flow<BResult<Account>> = flow {
        emit(BResult.Loading())
        val authResult = auth.createUserWithEmailAndPassword(email, password).fetchAccountResult()
        emit(authResult)
    }

    override fun login(email: String, password: String): Flow<BResult<Account>> = flow {
        emit(BResult.Loading())
        val authResult = auth.signInWithEmailAndPassword(email, password).fetchAccountResult()
        emit(authResult)
    }

    override fun logout(): Unit = auth.signOut()

    override fun getAuthCode(code: String): Flow<BResult<Unit>> = flow {
        emit(BResult.Loading())
        val result: BResult<Unit> = suspendCoroutine { cont ->
            firestore.collection(ACCOUNT_TOKENS_COLLECTION)
                .document(code)
                .get()
                .addOnSuccessListener { accountToken ->
                    cont.resume(
                        if (accountToken.exists()) BResult.Success(Unit) else BResult.Failure(BError.ObjectNotFound)
                    )
                }
                .addOnFailureListener {
                    cont.resume(BResult.Failure(BError.UnknownError(cause = it)))
                }
        }
        emit(result)
    }

    override fun deleteAuthCode(code: String) {
        firestore.collection(ACCOUNT_TOKENS_COLLECTION)
            .document(code)
            .delete()
    }

    private fun FirebaseUser.toAccount(): Account = Account(
        explorerId = this.displayName,
        email = this.email!!,
    )

    private suspend fun Task<AuthResult>.fetchAccountResult(): BResult<Account> = suspendCoroutine { continuation ->
        this.addOnCompleteListener { task ->
            when {
                !task.isSuccessful -> {
                    continuation.resume(BResult.Failure(BError.AuthenticationError(cause = task.exception)))
                }
                else -> {
                    val accountResult = getCurrentUserAccount()?.let {
                        BResult.Success(it)
                    } ?: BResult.Failure(BError.UnexpectedResult)
                    continuation.resume(accountResult)
                }
            }
        }
    }
}
