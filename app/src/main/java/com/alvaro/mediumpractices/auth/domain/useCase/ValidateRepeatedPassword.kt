package com.alvaro.mediumpractices.auth.domain.useCase

import javax.inject.Inject


class ValidateRepeatedPassword @Inject constructor(){
    operator fun invoke(password: String, repeatedPassword:String): ValidationResult {
        if (password != repeatedPassword) {
            return ValidationResult(successful = false, errorMessage = "The passwords don't match")
        }

        val containsLetterAndDigits = password.any { it.isDigit() } && password.any{it.isLetter()}
        return ValidationResult(successful = true)
    }
}