package com.alvaro.mediumpractices.auth.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun RegisterScreen() {

    Box(
        modifier = Modifier

            .fillMaxSize()
            .background(Color.Cyan), contentAlignment = Alignment.Center
    ) {
        Text(text = "Register Screen")


    }
}