package com.alvaro.mediumpractices.auth.ui.register


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alvaro.mediumpractices.auth.data.network.firebase.FirebaseResponse
import com.alvaro.mediumpractices.auth.domain.CreateAccountUseCase
import com.alvaro.mediumpractices.auth.domain.common.ValidateFields
import com.alvaro.mediumpractices.auth.ui.register.ui.model.RegisterUserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val createAccountUseCase: CreateAccountUseCase,
    private val validations: ValidateFields
) :
    ViewModel() {

    private val _uiStateRegister = MutableStateFlow(RegisterUserModel())
    val uiStateRegister: StateFlow<RegisterUserModel> = _uiStateRegister

    var isConfirmPassword by mutableStateOf(false)
    var isEmailError by mutableStateOf(false)
    var isPasswordError by mutableStateOf(false)
    var isUsernameError by mutableStateOf(false)
    var isNameError by mutableStateOf(false)
    var showDialog by mutableStateOf(false)
    var messageError by mutableStateOf("")

    fun onChangedRegister(
        email: String,
        password: String,
        username: String,
        confirmPassword: String
    ) {
        _uiStateRegister.update {
            it.copy(
                email = email,
                password = password,
                username = username,
                confirmPassword = confirmPassword,
                )
        }
        isEmailError = validations.validateEmail(_uiStateRegister.value.email)
        isPasswordError = validations.validatePassword(_uiStateRegister.value.password)
        isConfirmPassword = validations.confirmPassword(
            originalPassword = _uiStateRegister.value.password,
            confirmPassword = _uiStateRegister.value.confirmPassword
        )
        _uiStateRegister.update { it.copy(enableButton = enableButton()) }
    }

    private fun enableButton(): Boolean = !isConfirmPassword && !isEmailError && !isPasswordError

    private fun validateTextField(value:String):Boolean = value.isNotEmpty() && value.length > 3

    fun onSubmitRegister(onClick: () -> Unit) {
        viewModelScope.launch {
            when (val resp = createAccountUseCase.invoke(
                _uiStateRegister.value.email,
                _uiStateRegister.value.password
            )) {
                is FirebaseResponse.Error -> {
                    showDialog = true
                    messageError = resp.error
                    isEmailError = true
                }
                FirebaseResponse.Success -> {
                 onClick()
                }
            }
        }
    }
}