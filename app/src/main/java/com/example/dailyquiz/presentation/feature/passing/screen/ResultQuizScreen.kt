package com.example.dailyquiz.presentation.feature.passing.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.dailyquiz.presentation.feature.passing.viewmodel.PassingQuizViewModel

@Composable
fun ResultQuizScreen(viewModel: PassingQuizViewModel) {
    val correctCount by viewModel.correctAnswersCount.collectAsState()
    val totalQuestions = viewModel.userAnswers.size
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Ваш результат: $correctCount из $totalQuestions",
        )
        Text(
            text = viewModel.userAnswers.get(4).userAnswer,
        )
    }
}