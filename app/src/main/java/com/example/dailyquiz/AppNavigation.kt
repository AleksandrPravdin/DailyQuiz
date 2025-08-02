package com.example.dailyquiz

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dailyquiz.presentation.feature.passing.PassingQuizHostScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "quiz_host_screen") {

        composable("quiz_host_screen") {
            PassingQuizHostScreen()
        }

    }
}