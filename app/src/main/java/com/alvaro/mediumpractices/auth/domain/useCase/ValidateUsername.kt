package com.alvaro.mediumpractices.auth.domain.useCase

import javax.inject.Inject

class ValidateUsername @Inject constructor(){
    operator fun invoke(username: String): ValidationResult {
        if (username.isBlank()) {
            return ValidationResult(successful = false, errorMessage = "The username can't be blank")

        }

        return ValidationResult(successful = true)
    }
}