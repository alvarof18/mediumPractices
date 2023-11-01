package com.alvaro.mediumpractices.auth.data.datasource.api

import com.alvaro.mediumpractices.auth.domain.model.AuthResponse
import com.alvaro.mediumpractices.auth.domain.model.UserSignIn
import com.alvaro.mediumpractices.auth.domain.repository.AuthRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject



class AuthenticationService @Inject constructor(private val firebaseClient: FirebaseClient) :
    AuthRepository {
    override suspend fun login(email: String, password: String) {
    }

    override suspend fun createAccount(newUser:UserSignIn): AuthResponse?{
        runCatching {
            val user = hashMapOf(
                "email" to newUser.email,
                "username" to newUser.username
            )
            firebaseClient.auth.createUserWithEmailAndPassword(newUser.email, newUser.password).await()
            firebaseClient.db.collection("AlvaroDB").add(user).await()
        }.onSuccess { return AuthResponse.Successful }
         .onFailure { return AuthResponse.Error(it.message!!) }
        return null
    }

}