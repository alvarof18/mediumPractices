package com.alvaro.mediumpractices.auth.domain.useCase

data class ValidationResult(val successful: Boolean, val errorMessage: String? = null)
