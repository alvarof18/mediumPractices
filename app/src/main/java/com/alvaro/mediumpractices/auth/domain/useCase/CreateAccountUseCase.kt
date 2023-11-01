package com.alvaro.mediumpractices.auth.domain.useCase

import com.alvaro.mediumpractices.auth.data.RepositoryImp
import com.alvaro.mediumpractices.auth.domain.model.AuthResponse
import com.alvaro.mediumpractices.auth.domain.model.UserSignIn
import com.alvaro.mediumpractices.auth.ui.register.ui.model.RegisterResponseState

import javax.inject.Inject

class CreateAccountUseCase @Inject constructor(private val authRepository: RepositoryImp) {
    suspend operator fun invoke(newUser: UserSignIn): RegisterResponseState {
         return when( val respo = authRepository.createAccount(newUser))  {
             is AuthResponse.Error -> RegisterResponseState.Error(error = respo.error)
             AuthResponse.Successful -> {RegisterResponseState.Successful}
             null -> RegisterResponseState.Error(error = "Nose")
         }
    }
}