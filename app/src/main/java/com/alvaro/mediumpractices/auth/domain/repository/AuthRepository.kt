package com.alvaro.mediumpractices.auth.domain.repository

import com.alvaro.mediumpractices.auth.domain.model.AuthResponse
import com.alvaro.mediumpractices.auth.domain.model.UserSignIn

interface AuthRepository {
    suspend fun login(email:String, password:String)
    suspend fun createAccount(newUser:UserSignIn): AuthResponse?
}