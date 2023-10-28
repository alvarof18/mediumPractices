package com.alvaro.mediumpractices.auth.ui.register.ui.model

data class RegisterUserModel(
    val username:String = "",
    val email: String = "",
    val password: String = "",
    val name:String = "",
    val confirmPassword: String = "",
    val enableButton: Boolean = false
)
