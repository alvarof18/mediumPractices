package com.alvaro.mediumpractices.auth.domain.useCase

import com.alvaro.mediumpractices.auth.data.RepositoryImp
import javax.inject.Inject

class ForgotPasswordUseCase @Inject constructor(private val authRepository:RepositoryImp){
    suspend operator fun invoke(email:String){
        authRepository.sendEmailForgotPassword(email)
    }
}