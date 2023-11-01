package com.alvaro.mediumpractices.auth.domain.model

data class UserSignIn(
    val username:String = "",
    val email: String = "",
    val password: String = "",
    //val realName:String = "",
)
