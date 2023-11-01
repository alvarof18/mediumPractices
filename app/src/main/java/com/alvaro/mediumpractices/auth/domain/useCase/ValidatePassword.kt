package com.alvaro.mediumpractices.auth.domain.useCase


import javax.inject.Inject

class ValidatePassword  @Inject constructor() {
    operator fun invoke(password: String): ValidationResult {
        if (password.isBlank()) {
            return ValidationResult(successful = false, errorMessage = "The password can't be blank")
        }

        if (password.length < 6){
            return ValidationResult(successful = false, errorMessage = "Password must be at least 6 characters")
        }

        //PAra validar que contenga una letra y un numero
        val containsLetterAndDigits = password.any { it.isDigit() } && password.any{it.isLetter()}
        return ValidationResult(successful = true)
    }
}