package com.alvaro.mediumpractices.auth.domain.common

import android.util.Patterns
import javax.inject.Inject


class ValidateFields @Inject constructor(){
    fun validateEmail(email:String):Boolean = email.isNotEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()
    fun validatePassword(password:String):Boolean = password.isNotEmpty() && password.length < 6
    fun confirmPassword(originalPassword:String, confirmPassword:String):Boolean =
        confirmPassword != originalPassword


}