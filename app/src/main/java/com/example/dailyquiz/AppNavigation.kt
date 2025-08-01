package com.example.dailyquiz

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dailyquiz.presentation.screen.FilterScreen
import com.example.dailyquiz.presentation.screen.StartScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "start_screen") {

        composable("start_screen") {
            StartScreen(navController)
        }
        composable("filter_screen") {
            FilterScreen(navController)
        }

    }
}