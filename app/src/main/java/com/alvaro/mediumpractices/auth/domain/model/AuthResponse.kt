package com.alvaro.mediumpractices.auth.domain.model

sealed class AuthResponse {
    data class Error(val error: String) : AuthResponse()
    object Successful : AuthResponse()
}
