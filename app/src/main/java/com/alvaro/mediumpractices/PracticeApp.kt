package com.alvaro.mediumpractices

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.alvaro.mediumpractices.config.PracticeAppNav

@Composable
fun PracticeApp(navController: NavHostController = rememberNavController()){
        PracticeAppNav(navController = navController)
}