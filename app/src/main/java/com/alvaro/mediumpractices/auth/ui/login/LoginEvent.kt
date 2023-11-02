package com.alvaro.mediumpractices.auth.ui.login

sealed class LoginEvent{
    data class EmailChanged(val email: String) : LoginEvent()
    data class PasswordChanged(val password: String) : LoginEvent()
    data class Submit(val navigateTo:()->Unit) : LoginEvent()
}
