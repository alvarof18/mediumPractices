package com.alvaro.mediumpractices.auth.data.network.firebase



import com.alvaro.mediumpractices.auth.domain.AuthRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RepositoryImp @Inject constructor(private val firebase: FirebaseClient) : AuthRepository {

    override suspend fun login(email: String, password: String) {
        val auth = firebase.auth.signInWithEmailAndPassword(email, password).await()
    }

    override suspend fun createAccount(email: String, password: String): FirebaseResponse =
       runCatching {
            firebase.auth.createUserWithEmailAndPassword(email, password).await()
            FirebaseResponse.Success
        }.getOrElse {
           FirebaseResponse.Error(it.message!!)
        }
}

