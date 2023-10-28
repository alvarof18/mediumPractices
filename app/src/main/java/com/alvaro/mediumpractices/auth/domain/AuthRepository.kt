package com.alvaro.mediumpractices.auth.domain

import com.alvaro.mediumpractices.auth.data.network.firebase.FirebaseResponse
import com.google.firebase.auth.AuthResult

interface AuthRepository {
    suspend fun login(email:String, password:String)
    suspend fun createAccount(email: String, password: String) : FirebaseResponse

}