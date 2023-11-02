package com.alvaro.mediumpractices.auth.ui.forgotpassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alvaro.mediumpractices.auth.domain.useCase.ForgotPasswordUseCase
import com.alvaro.mediumpractices.auth.domain.useCase.ValidateEmail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val emailValidateEmail: ValidateEmail,
    private val forgotPasswordUseCase: ForgotPasswordUseCase
) :
    ViewModel() {

    private val _uiForgotPasswordState = MutableStateFlow(ForgotPasswordUiState())
    val uiForgotPasswordState: StateFlow<ForgotPasswordUiState> = _uiForgotPasswordState

    //Events
    fun onEvent(event: ForgotPasswordEvent) {
        when (event) {
            is ForgotPasswordEvent.EmailChanged -> _uiForgotPasswordState.update {
                it.copy(
                    email = event.email,
                    emailError = null
                )
            }

            is ForgotPasswordEvent.Submit -> submit()
        }
    }

    private fun submit() {
        val emailResult = emailValidateEmail(_uiForgotPasswordState.value.email)
        if (!emailResult.successful) {
            _uiForgotPasswordState.update { it.copy(emailError = emailResult.errorMessage) }
            return
        }

        viewModelScope.launch {
            forgotPasswordUseCase(_uiForgotPasswordState.value.email)

        }

    }


}