package com.example.dailyquiz

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
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

        animatedComposable("quiz_host_screen") {
            PassingQuizHostScreen(navController)
        }

        animatedComposable("history_quiz_screen") {
            HistoryQuizScreen(navController)
        }

        animatedComposable(
            route = "detail_quiz_screen/{quizId}",
            arguments = listOf(
                navArgument("quizId") { type = NavType.LongType }
            )
        ) { backStackEntry ->
            val quizId = backStackEntry.arguments?.getLong("quizId") ?: 0L
            DetailQuizScreen(navController, id = quizId)
        }

    }
}

fun NavGraphBuilder.animatedComposable(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    composable(
        route = route,
        arguments = arguments,
        enterTransition = {
            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, tween(400))
        },
        exitTransition = {
            slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left, tween(400))
        },
        popEnterTransition = {
            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right, tween(400))
        },
        popExitTransition = {
            slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right, tween(400))
        },
        content = content
    )
}