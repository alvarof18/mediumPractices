package com.alvaro.mediumpractices.auth.ui.register.ui.model

sealed class RegistrationFormEvent {
    data class EmailChanged(val email: String) : RegistrationFormEvent()
    data class PasswordChanged(val password: String) : RegistrationFormEvent()
    data class RepeatPasswordChanged(val repeatedPassword: String) : RegistrationFormEvent()
    data class UsernameChanged(val username: String) : RegistrationFormEvent()
    data class Submit(val navigateTo:()->Unit) : RegistrationFormEvent()

}
