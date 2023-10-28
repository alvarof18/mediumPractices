package com.alvaro.mediumpractices.auth.login.ui

import android.util.Patterns
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() :ViewModel() {

    private val _email = MutableStateFlow( "" )
    val email:StateFlow<String> = _email.asStateFlow()

    private val _password = MutableStateFlow( "" )
    val password:StateFlow<String> = _password.asStateFlow()

    private val _enableLogin = MutableStateFlow(false)
    val enableLogin:StateFlow<Boolean> = _enableLogin.asStateFlow()

    fun onChangedLogin(email:String, password:String){
        _email.value = email
        _password.value = password
        _enableLogin.value = enableLoginButton(email, password)
    }

    private fun enableLoginButton(email:String, password: String) =
       Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 6

}