package com.alvaro.mediumpractices.auth.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.alvaro.mediumpractices.ui.theme.Urbanist

@Composable
fun HomeScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "You are Logged!!", fontFamily = Urbanist, fontSize = 32.sp)
    }
}