package com.alvaro.mediumpractices.auth.login.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.alvaro.mediumpractices.auth.ui.common.AuthButtons
import com.alvaro.mediumpractices.auth.ui.common.FooterAuth
import com.alvaro.mediumpractices.auth.ui.common.HeaderAuth
import com.alvaro.mediumpractices.auth.ui.common.SocialAuth
import com.alvaro.mediumpractices.auth.ui.common.TextFieldLogin
import com.alvaro.mediumpractices.auth.ui.common.authTopBar
import com.alvaro.mediumpractices.config.AuthRoutes
import com.alvaro.mediumpractices.ui.theme.Urbanist

@Composable
fun LoginScreen(navController: NavController) {
    Scaffold(topBar = { authTopBar(navController) }) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            HeaderAuth(title = "Welcome Back! Glad to see you, Again!")
            Spacer(modifier = Modifier.height(32.dp))
            LoginForm()
            Spacer(modifier = Modifier.weight(1f))
            FooterAuth(
                label = "Don't have an account?",
                labelClickable = "Register Now",
                onClick = {navController.navigate(AuthRoutes.RegisterScreen.route)})
        }
    }
}


@Composable
fun LoginForm(loginViewModel: LoginViewModel = viewModel()) {

    val email by loginViewModel.email.collectAsState(initial = "")
    val password by loginViewModel.password.collectAsState(initial = "")
    val isEnableLogin by loginViewModel.enableLogin.collectAsState(initial = false)

    Column(modifier = Modifier.padding(horizontal = 12.dp)) {
        TextFieldLogin(
            value = email,
            placeholder = "Enter your email",
            onValueChange = { loginViewModel.onChangedLogin(email = it, password = password) }, errorMessage = "")
        Spacer(modifier = Modifier.height(8.dp))
        TextFieldLogin(
            value = password,
            placeholder = "Enter your password",
            onValueChange = { loginViewModel.onChangedLogin(email = email, password = it) },
            isPasswordField = true, errorMessage = ""
        )
        Spacer(modifier = Modifier.height(12.dp))
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd) {
            Text(
                text = "Forgot Password?",
                fontFamily = Urbanist,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xff6A707C),
                modifier = Modifier.clickable { }
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        AuthButtons(onClick = {}, label = "Login", enabled = isEnableLogin)
        Spacer(modifier = Modifier.height(32.dp))
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Divider(
                modifier = Modifier
                    .weight(1f)
                    .height(1.dp),
                color = Color(0xffE8ECF4)
            )
            Text(
                text = "Or Login with",
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
        SocialAuth()
    }
}


@Composable
fun LoginSocialButtons(@DrawableRes icon: Int) {
    IconButton(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .height(56.dp)
            .width(100.dp)
            .border(1.dp, color = Color(0xffE8ECF4), shape = RoundedCornerShape(8.dp))
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.size(26.dp),
        )
    }
}


@Preview(showBackground = true)
@Composable
fun previewLoginScreen() {
    val navController = rememberNavController()
    LoginScreen(navController)
}