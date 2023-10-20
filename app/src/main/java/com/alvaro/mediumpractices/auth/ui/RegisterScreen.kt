package com.alvaro.mediumpractices.auth.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
import com.alvaro.mediumpractices.auth.ui.common.SocialAuth
import com.alvaro.mediumpractices.auth.ui.common.TextFieldLogin
import com.alvaro.mediumpractices.auth.ui.common.authTopBar
import com.alvaro.mediumpractices.config.AuthRoutes
import com.alvaro.mediumpractices.ui.theme.Urbanist

@Composable
fun RegisterScreen(navController: NavController) {
    Scaffold(topBar = { authTopBar(navController) }) {
        Column(
            Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            HeaderAuth(title = "Hello! Register to get started")
            Spacer(modifier = Modifier.height(32.dp))
            RegisterForm(navController)
            Spacer(modifier = Modifier.weight(1f))
            FooterAuth(label = "Already have an account?", labelClickable ="Login now", onClick = {} )
        }
    }

}

@Composable
fun RegisterForm(navController: NavController){
    var username by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var confirmarpassword by remember {
        mutableStateOf("")
    }

    Column(Modifier.padding(horizontal = 12.dp)) {
        TextFieldLogin(value = username , onValueChange = {username = it} , placeholder = "Username" )
        Spacer(modifier = Modifier.height(8.dp))
        TextFieldLogin(value = email , onValueChange = {email = it} , placeholder = "Email" )
        Spacer(modifier = Modifier.height(8.dp))
        TextFieldLogin(value = password , onValueChange = {password = it} , placeholder = "Password" )
        Spacer(modifier = Modifier.height(8.dp))
        TextFieldLogin(value = confirmarpassword , onValueChange = {confirmarpassword = it} , placeholder = "Confirm Password" )
        Spacer(modifier = Modifier.height(16.dp))
        AuthButtons(onClick = { navController.navigate(AuthRoutes.SuccessfulPasswordChangedScreen.route) }, label = "Register")
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
        SocialAuth()
    }
}

@Preview
@Composable
fun PreviewRegisterScreen() {
    val navController = rememberNavController()
    RegisterScreen(navController)
}