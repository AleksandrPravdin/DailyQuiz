package com.example.dailyquiz.presentation.feature.passing

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dailyquiz.presentation.feature.passing.screen.FilterScreen
import com.example.dailyquiz.presentation.feature.passing.screen.PassingQuizScreen
import com.example.dailyquiz.presentation.feature.passing.screen.ResultQuizScreen
import com.example.dailyquiz.presentation.feature.passing.screen.StartScreen
import com.example.dailyquiz.presentation.feature.passing.viewmodel.PassingQuizViewModel

@Composable
fun PassingQuizHostScreen() {
    val viewModel: PassingQuizViewModel = hiltViewModel()
    val screenState by viewModel.screenState.collectAsState()

    when (screenState) {
        PassingQuizScreenState.START -> StartScreen(viewModel)
        PassingQuizScreenState.FILTER -> FilterScreen(viewModel)
        PassingQuizScreenState.PASSING -> PassingQuizScreen(viewModel)
        PassingQuizScreenState.RESULT -> ResultQuizScreen(viewModel)
    }
}