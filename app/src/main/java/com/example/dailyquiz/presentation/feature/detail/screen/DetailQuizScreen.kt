package com.example.dailyquiz.presentation.feature.detail.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.dailyquiz.R
import com.example.dailyquiz.presentation.feature.detail.component.DetailAnswerCard
import com.example.dailyquiz.presentation.feature.detail.component.DetailResultCard
import com.example.dailyquiz.presentation.feature.detail.viewmodel.DetailQuizViewModel

@Composable
fun DetailQuizScreen(
    navController: NavController,
    id: Long,
    viewModel: DetailQuizViewModel = hiltViewModel()
) {
    val quizResult by viewModel.quizResult.collectAsState()
    val userAnswers by viewModel.userAnswers.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    val correctAnswerCount = quizResult?.correctAnswers ?: 0
    val totalQuestions = quizResult?.totalQuestions ?: 5

    LaunchedEffect(id) {
        viewModel.loadQuizData(id)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            if (isLoading) {
                Spacer(Modifier.height(100.dp))

                Text(
                    text = stringResource(id = R.string.result),
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp, color = MaterialTheme.colorScheme.primary
                )
                Spacer(Modifier.height(15.dp))

                Text(
                    text = "Категория: ${quizResult?.category}",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Сложность: ${quizResult?.difficulty}",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(Modifier.height(20.dp))

                DetailResultCard(
                    correctAnswerCount = correctAnswerCount,
                    totalQuestions = totalQuestions,
                    navController = navController
                )

            }
        }
        itemsIndexed(userAnswers) { index, result ->
            DetailAnswerCard(userAnswerEntity = result, index = index + 1)
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(25.dp)
            ) {
                Button(
                    onClick = {
                        navController.popBackStack("quiz_host_screen", inclusive = false)
                    },
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ), modifier = Modifier.height(54.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.restart_quiz),
                            color = MaterialTheme.colorScheme.background,
                            fontSize = 16.sp
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(120.dp))
        }
    }
}