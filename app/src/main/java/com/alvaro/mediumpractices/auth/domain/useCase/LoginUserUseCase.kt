package com.alvaro.mediumpractices.auth.domain.useCase

import android.util.Log
import com.alvaro.mediumpractices.auth.data.RepositoryImp
import com.alvaro.mediumpractices.auth.domain.model.AuthResponse

import com.alvaro.mediumpractices.auth.ui.register.ui.model.RegisterResponseState
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(private val authRepository: RepositoryImp) {
    suspend operator fun invoke(email: String, password: String): RegisterResponseState {
        return when (val respo = authRepository.login(email, password)) {
            is AuthResponse.Error -> {
                Log.i("AuthLoginError", respo.error)
                RegisterResponseState.Error(error = "Email or Password invalid")
            }
            AuthResponse.Successful -> {
                RegisterResponseState.Successful
            }
            null -> RegisterResponseState.Error(error = "Nose")
        }
    }
}