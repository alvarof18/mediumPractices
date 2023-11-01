package com.alvaro.mediumpractices.auth.ui.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.alvaro.mediumpractices.auth.ui.common.AuthButtons
import com.alvaro.mediumpractices.auth.ui.common.FooterAuth
import com.alvaro.mediumpractices.auth.ui.common.HeaderAuth
import com.alvaro.mediumpractices.auth.ui.common.LoginDialog
import com.alvaro.mediumpractices.auth.ui.common.SocialAuth
import com.alvaro.mediumpractices.auth.ui.common.TextFieldLogin
import com.alvaro.mediumpractices.auth.ui.common.authTopBar
import com.alvaro.mediumpractices.auth.ui.register.ui.model.RegistrationFormEvent
import com.alvaro.mediumpractices.config.AuthRoutes
import com.alvaro.mediumpractices.ui.theme.Urbanist

@Composable
fun RegisterScreen(navController: NavController) {

    Scaffold(
        topBar = { authTopBar(navController) }) {
        Column(
            Modifier
                .padding(it)
                .fillMaxSize()
                .imePadding()
                .verticalScroll(rememberScrollState())
        ) {
            HeaderAuth(title = "Hello! Register to get started")
            Spacer(modifier = Modifier.height(32.dp))
            RegisterForm(navController)
            Spacer(modifier = Modifier.weight(1f))
            FooterAuth(label = "Already have an account?", labelClickable = "Login now", onClick = {
                navController.navigate(AuthRoutes.LoginScreen.route) {

                }
            })
        }

    }

}

@Composable
fun RegisterForm(
    navController: NavController,
    registerViewModel: RegisterViewModel = hiltViewModel()
) {

    val uiStateRegister by registerViewModel.uiStateRegister.collectAsState()


    Box(contentAlignment = Alignment.Center) {
        Column(Modifier.padding(horizontal = 12.dp)) {
            TextFieldLogin(
                value = uiStateRegister.username,
                onValueChange = {
                    registerViewModel.onEvent(event = RegistrationFormEvent.UsernameChanged(it))
                },
                placeholder = "Username",
                isError = uiStateRegister.usernameError != null,
                errorMessage = uiStateRegister.usernameError ?: "",
                enabled = !uiStateRegister.isLoading
            )
            Spacer(modifier = Modifier.height(8.dp))
            /*
                TextFieldLogin(
                    value = uiStateRegister.value.username,
                    onValueChange = {},
                    placeholder = "Name",
                )
                Spacer(modifier = Modifier.height(8.dp))*/

            TextFieldLogin(
                value = uiStateRegister.email,
                onValueChange = {
                    registerViewModel.onEvent(RegistrationFormEvent.EmailChanged(email = it))
                },
                placeholder = "Email",
                keyboardType = KeyboardType.Email,
                isError = uiStateRegister.emailError != null,
                errorMessage = uiStateRegister.emailError ?: "",
                enabled = !uiStateRegister.isLoading

            )
            Spacer(modifier = Modifier.height(8.dp))
            TextFieldLogin(
                value = uiStateRegister.password,
                onValueChange = {
                    registerViewModel.onEvent(RegistrationFormEvent.PasswordChanged(password = it))
                },
                placeholder = "Password",
                isError = uiStateRegister.passwordError != null,
                errorMessage = uiStateRegister.passwordError ?: "",
                enabled = !uiStateRegister.isLoading
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextFieldLogin(
                value = uiStateRegister.repeatPassword,
                onValueChange = {
                    registerViewModel.onEvent(
                        RegistrationFormEvent.RepeatPasswordChanged(
                            repeatedPassword = it
                        )
                    )
                },
                placeholder = "Confirm Password",
                isError = uiStateRegister.repeatPasswordError != null,
                errorMessage = uiStateRegister.repeatPasswordError ?: "",
                enabled = !uiStateRegister.isLoading
            )
            Spacer(modifier = Modifier.height(16.dp))
            AuthButtons(
                onClick = {
                    registerViewModel.onEvent(RegistrationFormEvent.Submit {
                        navController.navigate(
                            AuthRoutes.SuccessfulPasswordChangedScreen.route
                        )
                    })
                },
                label = "Register",
                enabled = !uiStateRegister.isLoading
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Divider(
                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp),
                    color = Color(0xffE8ECF4)
                )
                Text(
                    text = "Or Register with",
                    fontFamily = Urbanist,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(horizontal = 8.dp),
                    color = Color(0xff6A707C)
                )
                Divider(
                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp), color = Color(0xffE8ECF4)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            if (registerViewModel.showDialogError) {
                LoginDialog(
                    dialogText = registerViewModel.messageDialogError,
                    onConfirmation = { registerViewModel.showDialogError = false },
                    onDismissRequest = { registerViewModel.showDialogError = false },
                    title = "Error"
                )
            }
            SocialAuth()
        }

        if (uiStateRegister.isLoading) {
           CircularProgressIndicator()
        }
    }
}


@Preview
@Composable
fun PreviewRegisterScreen() {
    val navController = rememberNavController()
    RegisterScreen(navController)
}

