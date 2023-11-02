package com.alvaro.mediumpractices.auth.ui.forgotpassword

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.alvaro.mediumpractices.R
import com.alvaro.mediumpractices.auth.ui.common.AuthButtons
import com.alvaro.mediumpractices.config.AuthRoutes
import com.alvaro.mediumpractices.ui.theme.Urbanist

@Composable
fun PasswordChangedScreen(navController: NavController) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.sticker),
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Password Changed!",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = Urbanist
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Your password has been changed successfully.",
            modifier = Modifier.padding(horizontal = 64.dp),
            textAlign = TextAlign.Center,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xff8391A1),
            fontFamily = Urbanist

        )
        Spacer(modifier = Modifier.height(32.dp))
        AuthButtons(label = "Back to Login", onClick = { navController.navigate(AuthRoutes.LoginScreen.route) })
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPasswordChangedScreen() {
    val navController = rememberNavController()
    PasswordChangedScreen(navController)

}