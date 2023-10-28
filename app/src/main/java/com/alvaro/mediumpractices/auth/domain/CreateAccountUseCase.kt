package com.alvaro.mediumpractices.auth.domain

import com.alvaro.mediumpractices.auth.data.network.firebase.FirebaseResponse
import com.alvaro.mediumpractices.auth.data.network.firebase.RepositoryImp
import javax.inject.Inject

class CreateAccountUseCase @Inject constructor(private val authRepository: RepositoryImp) {
    suspend operator fun invoke(email: String, password: String):FirebaseResponse {
       return authRepository.createAccount(email, password)
      }
}