package com.alvaro.mediumpractices.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.alvaro.mediumpractices.config.AuthRoutes
import com.alvaro.mediumpractices.config.Routes
import com.alvaro.mediumpractices.ui.theme.Urbanist

@Composable
fun MainScreen(navController: NavHostController) {

    Box(
        modifier = Modifier
            .systemBarsPadding() //Para colocar una safeArea
            .fillMaxSize()
            .padding().clickable { navController.navigate(Routes.Auth.route) }
    ) {
        Card(
            modifier = Modifier
                .height(100.dp)
                .width(150.dp)

        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Login",
                    fontFamily = Urbanist,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 32.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    val navController: NavHostController = rememberNavController()
    MainScreen(navController)
}
