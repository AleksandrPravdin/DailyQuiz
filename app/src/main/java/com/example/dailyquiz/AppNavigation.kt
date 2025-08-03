package com.example.dailyquiz

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.dailyquiz.presentation.feature.detail.screen.DetailQuizScreen
import com.example.dailyquiz.presentation.feature.history.screen.HistoryQuizScreen
import com.example.dailyquiz.presentation.feature.passing.PassingQuizHostScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "quiz_host_screen") {

        composable("quiz_host_screen") {
            PassingQuizHostScreen(navController)
        }

        composable("history_quiz_screen") {
            HistoryQuizScreen(navController)
        }

        composable(
            route = "detail_quiz_screen/{quizId}",
            arguments = listOf(
                navArgument("quizId") {
                    type = NavType.LongType
                }
            )
        ) { backStackEntry ->
            val quizId = backStackEntry.arguments?.getLong("quizId") ?: 0L
            DetailQuizScreen(navController, id = quizId)
        }

    }
}