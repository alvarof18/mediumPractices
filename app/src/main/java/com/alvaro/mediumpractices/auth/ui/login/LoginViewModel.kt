package com.alvaro.mediumpractices.auth.ui.login

import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alvaro.mediumpractices.auth.domain.useCase.LoginUserUseCase
import com.alvaro.mediumpractices.auth.domain.useCase.ValidateEmail
import com.alvaro.mediumpractices.auth.domain.useCase.ValidatePassword
import com.alvaro.mediumpractices.auth.ui.register.ui.model.RegisterResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUsesCase: LoginUserUseCase,
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword,
) : ViewModel() {

    private val _uiStateLogin = MutableStateFlow(LoginUiState())
    val uiStateLogin: StateFlow<LoginUiState> = _uiStateLogin

    var errorMessage by mutableStateOf("")
    var showErrorMessage by mutableStateOf(false)

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailChanged -> _uiStateLogin.update {
                it.copy(
                    email = event.email,
                    emailError = null
                )
            }

            is LoginEvent.PasswordChanged -> _uiStateLogin.update {
                it.copy(
                    password = event.password,
                    passwordError = null
                )
            }

            is LoginEvent.Submit -> loginSubmit(event.navigateTo)
        }
    }

    private fun loginSubmit(onClick: () -> Unit) {
        val emailResult = validateEmail(email = _uiStateLogin.value.email)
        val passwordResult = validatePassword(password = _uiStateLogin.value.password)

        val hasError = listOf(
            emailResult,
            passwordResult
        ).any { !it.successful }

        if (hasError) {
            _uiStateLogin.update {
                it.copy(
                    emailError = emailResult.errorMessage,
                    passwordError = passwordResult.errorMessage
                )
            }
            return
        }

        viewModelScope.launch {
            _uiStateLogin.update { it.copy(isLoading = true) }
            when (val resp =
                loginUsesCase(_uiStateLogin.value.email, _uiStateLogin.value.password)) {
                is RegisterResponseState.Error -> {
                    showErrorMessage = true
                    errorMessage = resp.error;
                }
                RegisterResponseState.Successful -> onClick()
            }
             delay(1000)
            _uiStateLogin.update { it.copy(isLoading = false) }
        }
    }
}