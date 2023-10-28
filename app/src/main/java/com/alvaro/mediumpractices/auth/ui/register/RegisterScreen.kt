package com.alvaro.mediumpractices.auth.ui.register

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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.alvaro.mediumpractices.auth.ui.common.AuthButtons
import com.alvaro.mediumpractices.auth.ui.common.FooterAuth
import com.alvaro.mediumpractices.auth.ui.common.HeaderAuth
import com.alvaro.mediumpractices.auth.ui.common.LoginDialog
import com.alvaro.mediumpractices.auth.ui.common.SocialAuth
import com.alvaro.mediumpractices.auth.ui.common.TextFieldLogin
import com.alvaro.mediumpractices.auth.ui.common.authTopBar
import com.alvaro.mediumpractices.config.AuthRoutes
import com.alvaro.mediumpractices.ui.theme.Urbanist
import kotlinx.coroutines.launch

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

    Column(Modifier.padding(horizontal = 12.dp)) {
        TextFieldLogin(
            value = uiStateRegister.username,
            onValueChange = {
                registerViewModel.onChangedRegister(
                    email = uiStateRegister.email,
                    password = uiStateRegister.password,
                    confirmPassword = uiStateRegister.confirmPassword,
                    username = it
                )
            },
            placeholder = "Username",
        )
        Spacer(modifier = Modifier.height(8.dp))

        /*TextFieldLogin(
            value = uiStateRegister.username,
            onValueChange = {},
            placeholder = "Name",
        )
        Spacer(modifier = Modifier.height(8.dp))*/

        TextFieldLogin(
            value = uiStateRegister.email,
            onValueChange = {
                registerViewModel.onChangedRegister(
                    email = it,
                    password = uiStateRegister.password,
                    username = uiStateRegister.username,
                    confirmPassword = uiStateRegister.confirmPassword
                )
            },
            placeholder = "Email",
            keyboardType = KeyboardType.Email,
            isError = registerViewModel.isEmailError,
            errorMessage = "That's not a valid email"

        )
        Spacer(modifier = Modifier.height(8.dp))
        TextFieldLogin(
            value = uiStateRegister.password,
            onValueChange = {
                registerViewModel.onChangedRegister(
                    email = uiStateRegister.email,
                    username = uiStateRegister.username,
                    password = it,
                    confirmPassword = uiStateRegister.confirmPassword
                )
            },
            placeholder = "Password",
            isError = registerViewModel.isPasswordError,
            errorMessage = "Password must be at least 6 characters"
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextFieldLogin(
            value = uiStateRegister.confirmPassword,
            onValueChange = {
                registerViewModel.onChangedRegister(
                    email = uiStateRegister.email,
                    username = uiStateRegister.username,
                    password = uiStateRegister.password,
                    confirmPassword = it
                )
            },
            placeholder = "Confirm Password",
            isError = registerViewModel.isConfirmPassword,
            errorMessage = "password is not the same"
        )
        Spacer(modifier = Modifier.height(16.dp))
        AuthButtons(
            onClick = {
                registerViewModel.onSubmitRegister(onClick = { navController.navigate(AuthRoutes.SuccessfulPasswordChangedScreen.route) })

            },
            label = "Register",
            enabled = uiStateRegister.enableButton
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

        if (registerViewModel.showDialog) {
            LoginDialog(
                dialogText = registerViewModel.messageError,
                onConfirmation = { registerViewModel.showDialog = false },
                onDismissRequest = { registerViewModel.showDialog = false }, title = "Error"
            )

        }
        SocialAuth()
    }
}


@Preview
@Composable
fun PreviewRegisterScreen() {
    val navController = rememberNavController()
    RegisterScreen(navController)
}