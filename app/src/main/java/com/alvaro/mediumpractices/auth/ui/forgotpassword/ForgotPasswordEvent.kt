package com.alvaro.mediumpractices.auth.ui.forgotpassword

sealed class ForgotPasswordEvent{
    data class EmailChanged(val email:String) : ForgotPasswordEvent()
    data class Submit(val navigateTo:()->Unit) : ForgotPasswordEvent()

}
