package com.alvaro.mediumpractices.auth.ui.forgotpassword

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.alvaro.mediumpractices.auth.ui.common.AuthButtons
import com.alvaro.mediumpractices.auth.ui.common.FooterAuth
import com.alvaro.mediumpractices.auth.ui.common.HeaderAuth
import com.alvaro.mediumpractices.auth.ui.common.TextFieldLogin
import com.alvaro.mediumpractices.auth.ui.common.authTopBar
import com.alvaro.mediumpractices.ui.theme.Urbanist

@Composable
fun ForgotPasswordScreen(navController: NavController) {

    Scaffold(topBar = { authTopBar(navController) }) {
        Column(Modifier.padding(it)) {
            HeaderForgotPassword()
            Spacer(modifier = Modifier.height(16.dp))
            ForgotForm()
            Spacer(modifier = Modifier.weight(1f))
            FooterAuth(label = "Remember Password", labelClickable ="Login In", onClick = {} )
        }
    }
}

@Composable
fun HeaderForgotPassword() {
    Column(Modifier.fillMaxWidth()) {
        HeaderAuth(title = "Forgot Password?")
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Don't worry! It occurs. Please enter the email address linked with your account.",
            modifier = Modifier.padding(horizontal = 12.dp),
            fontFamily = Urbanist,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xff8391A1)
        )
    }
}

@Composable
fun ForgotForm(){
    var email by remember {
        mutableStateOf("")
    }

    Column(Modifier.padding(horizontal = 12.dp)) {
        TextFieldLogin(value = email, onValueChange = {email = it} , placeholder ="Email")
        Spacer(modifier = Modifier.height(24.dp))
        AuthButtons(label = "Send Code", onClick = { /*TODO*/ })
    }

}

@Preview
@Composable
fun PreviewForgotPasswordScreen() {
    val navController = rememberNavController()
    ForgotPasswordScreen(navController)
}