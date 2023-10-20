package com.alvaro.mediumpractices.auth.ui

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
import com.alvaro.mediumpractices.auth.ui.common.AuthButtons
import com.alvaro.mediumpractices.auth.ui.common.HeaderAuth
import com.alvaro.mediumpractices.auth.ui.common.TextFieldLogin
import com.alvaro.mediumpractices.auth.ui.common.authTopBar
import com.alvaro.mediumpractices.ui.theme.Urbanist

@Composable
fun CreateNewPasswordScreen(){
    Scaffold(topBar = { authTopBar()}) {
        Column(Modifier.padding(it)) {
            HeaderCreateNewPassword()
            Spacer(modifier = Modifier.height(16.dp))
            FormCreateNewPassword()
        }
    }

}

@Composable
fun HeaderCreateNewPassword(){
    Column(Modifier.fillMaxWidth()) {
        HeaderAuth(title = "Create new password")
        Spacer(Modifier.height(4.dp))
        Text(
            text = "Your new password must be unique from those previously used.",
            modifier = Modifier.padding(horizontal = 12.dp),
            fontFamily = Urbanist,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xff8391A1)
        )
    }
}

@Composable
fun FormCreateNewPassword(){
    var newPassword by remember {
        mutableStateOf("")
    }

    var confirmNewPassword by remember {
        mutableStateOf("")
    }

    Column(Modifier.padding(horizontal = 12.dp)) {
        TextFieldLogin(value = newPassword, onValueChange ={newPassword = it} , placeholder ="New Password" )
        Spacer(modifier = Modifier.height(8.dp))
        TextFieldLogin(value = confirmNewPassword, onValueChange ={confirmNewPassword = it} , placeholder ="Confirm Password" )
        Spacer(modifier = Modifier.height(16.dp))
        AuthButtons(label = "Reset Password", onClick = { /*TODO*/ })
    }
}

@Preview
@Composable
fun PreviewCreateNewPasswordScreen(){
    CreateNewPasswordScreen()
    }