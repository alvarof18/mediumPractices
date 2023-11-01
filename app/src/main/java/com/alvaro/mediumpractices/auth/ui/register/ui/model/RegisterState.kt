package com.alvaro.mediumpractices.auth.ui.register.ui.model

data class RegisterState(
    val username:String = "",
    val email: String = "",
    val password: String = "",
    val realName:String = "",
    val repeatPassword:String = "",

    val usernameError: String? = null,
    val emailError: String? = null,
    val passwordError: String? = null,
    val repeatPasswordError: String? = null,
    val nameError:String? = null,

    val isLoading:Boolean = false
)

sealed class RegisterResponseState{
    object Successful:RegisterResponseState()
    data class Error(val error:String):RegisterResponseState()
}
