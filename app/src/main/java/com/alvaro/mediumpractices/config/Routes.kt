package com.alvaro.mediumpractices.config

sealed class Routes(val route:String){
    object Main:Routes("home")
    object Auth:Routes("auth")

}

sealed class AuthRoutes(val route:String){
    object WelcomeScreen:Routes("welcome")
    object LoginScreen:Routes("login")
    object RegisterScreen:Routes("register")
    object ForgotScreen:Routes("forgot")


}
