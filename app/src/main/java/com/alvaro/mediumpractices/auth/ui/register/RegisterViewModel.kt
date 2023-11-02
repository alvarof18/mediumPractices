package com.alvaro.mediumpractices.auth.ui.register


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alvaro.mediumpractices.auth.domain.model.UserSignIn
import com.alvaro.mediumpractices.auth.domain.useCase.CreateAccountUseCase
import com.alvaro.mediumpractices.auth.domain.useCase.ValidateEmail
import com.alvaro.mediumpractices.auth.domain.useCase.ValidatePassword
import com.alvaro.mediumpractices.auth.domain.useCase.ValidateRepeatedPassword
import com.alvaro.mediumpractices.auth.domain.useCase.ValidateUsername
import com.alvaro.mediumpractices.auth.ui.register.ui.model.RegisterResponseState

import com.alvaro.mediumpractices.auth.ui.register.ui.model.RegisterState
import com.alvaro.mediumpractices.auth.ui.register.ui.model.RegistrationFormEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword,
    private val validateRepeatedPassword: ValidateRepeatedPassword,
    private val validateUsername: ValidateUsername,
    private val createUserUseCase: CreateAccountUseCase
) : ViewModel() {

    private val _uiStateRegister = MutableStateFlow(RegisterState())
    val uiStateRegister: StateFlow<RegisterState> = _uiStateRegister

    var showDialogError by mutableStateOf(false)
    var messageDialogError by mutableStateOf("")


    fun onEvent(event: RegistrationFormEvent) {
        when (event) {
            is RegistrationFormEvent.EmailChanged -> _uiStateRegister.update { it.copy(email = event.email) }
            is RegistrationFormEvent.PasswordChanged ->
                _uiStateRegister.update { it.copy(password = event.password) }


            is RegistrationFormEvent.RepeatPasswordChanged -> _uiStateRegister.update {
                it.copy(
                    repeatPassword = event.repeatedPassword
                )
            }

            is RegistrationFormEvent.UsernameChanged -> _uiStateRegister.update { it.copy(username = event.username) }
            is RegistrationFormEvent.Submit -> submitRegister(event.navigateTo)
        }
    }

    private fun submitRegister(onClick: () -> Unit) {
        val emailResult = validateEmail(email = _uiStateRegister.value.email)
        val passwordResult = validatePassword(password = _uiStateRegister.value.password)
        val repeatPasswordResult = validateRepeatedPassword(
            password = _uiStateRegister.value.password,
            repeatedPassword = _uiStateRegister.value.repeatPassword
        )
        val usernameResult = validateUsername(username = _uiStateRegister.value.username)

        val hasError = listOf(
            emailResult,
            passwordResult,
            repeatPasswordResult,
            usernameResult
        ).any { !it.successful }

        if (hasError) {
            _uiStateRegister.update {
                it.copy(
                    emailError = emailResult.errorMessage,
                    passwordError = passwordResult.errorMessage,
                    repeatPasswordError = repeatPasswordResult.errorMessage,
                    usernameError = usernameResult.errorMessage
                )
            }
            return
        }

        viewModelScope.launch {
            _uiStateRegister.update { it.copy(isLoading = true) }
            when (val result = createUserUseCase(
                newUser = UserSignIn(
                    email = _uiStateRegister.value.email,
                    password = _uiStateRegister.value.password,
                    username = _uiStateRegister.value.username,
                )
            )) {
                is RegisterResponseState.Error -> {
                    messageDialogError = result.error
                    showDialogError = true
                    _uiStateRegister.update { it.copy(emailError = result.error) }
                }

                RegisterResponseState.Successful -> onClick()
            }
            _uiStateRegister.update { it.copy(isLoading = false) }
        }
    }
}