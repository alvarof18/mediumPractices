package com.alvaro.mediumpractices.config

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.alvaro.mediumpractices.auth.ui.forgotpassword.CreateNewPasswordScreen
import com.alvaro.mediumpractices.auth.ui.forgotpassword.screens.ForgotPasswordScreen
import com.alvaro.mediumpractices.auth.ui.HomeScreen
import com.alvaro.mediumpractices.auth.ui.login.LoginScreen
import com.alvaro.mediumpractices.auth.ui.forgotpassword.OtpVerificationScreen
import com.alvaro.mediumpractices.auth.ui.forgotpassword.PasswordChangedScreen
import com.alvaro.mediumpractices.auth.ui.register.RegisterScreen
import com.alvaro.mediumpractices.auth.ui.WelcomeScreen
import com.alvaro.mediumpractices.main.MainScreen

@Composable
fun PracticeAppNav(navController: NavHostController){
    NavHost(navController = navController, startDestination = Routes.Main.route){
        composable(Routes.Main.route){ MainScreen(navController=navController)}
        auth(navController = navController)
    }

}

fun NavGraphBuilder.auth(navController:NavController){
    navigation(startDestination = AuthRoutes.WelcomeScreen.route, route = Routes.Auth.route){
    composable(route = AuthRoutes.WelcomeScreen.route){ WelcomeScreen(navController = navController)}
    composable(route = AuthRoutes.LoginScreen.route){ LoginScreen(navController = navController) }
    composable(route = AuthRoutes.RegisterScreen.route){ RegisterScreen(navController = navController) }
    composable(route = AuthRoutes.ForgotScreen.route){ ForgotPasswordScreen(navController = navController) }
    composable(route = AuthRoutes.OtpValidationScreen.route){ OtpVerificationScreen(navController = navController) }
    composable(route = AuthRoutes.PasswordChangedScreen.route){ CreateNewPasswordScreen(navController = navController) }
    composable(route = AuthRoutes.SuccessfulPasswordChangedScreen.route){ PasswordChangedScreen(navController = navController) }
    composable(route = AuthRoutes.HomeScreen.route){ HomeScreen() }

    }
}





