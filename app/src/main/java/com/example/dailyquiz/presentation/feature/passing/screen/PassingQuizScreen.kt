package com.example.dailyquiz.presentation.feature.passing.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dailyquiz.R
import com.example.dailyquiz.presentation.feature.passing.component.NextButton
import com.example.dailyquiz.presentation.feature.passing.viewmodel.PassingQuizViewModel
import kotlinx.coroutines.delay

@Composable
fun PassingQuizScreen(
    viewModel: PassingQuizViewModel
) {
    val currentState by viewModel.uiState.collectAsState()
    val currentQuestion = currentState.currentQuestion
    val questionIndex = currentState.currentQuestionIndex
    val totalQuestions = currentState.totalQuestions

    var selectedOption by remember { mutableStateOf<String?>(null) }
    var showAnswerFeedback by remember { mutableStateOf(false) }
    var isAnswerCorrect by remember { mutableStateOf(false) }
    var isNextButtonEnabled by remember { mutableStateOf(false) }

    LaunchedEffect(showAnswerFeedback) {
        if (showAnswerFeedback) {
            delay(2000)
            showAnswerFeedback = false
            selectedOption = null
            viewModel.nextQuestion()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(100.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
        ) {
            Box(
                Modifier
                    .padding(start = 20.dp)
                    .fillMaxHeight(), contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_back_icon),
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.CenterStart)
                        .clickable {
                            viewModel.resetQuiz()
                            viewModel.goToFilterScreen()
                        },
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            Image(
                painter = painterResource(id = R.drawable.dqlogo),
                modifier = Modifier
                    .width(180.dp)
                    .align(Alignment.Center),
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.height(40.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(30.dp)
                    )
                    .fillMaxWidth()
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Spacer(Modifier.height(20.dp))
                    Text(
                        text = "Вопрос ${questionIndex + 1} из $totalQuestions",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                    Spacer(Modifier.height(20.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = currentQuestion?.text ?: "",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    }
                    Spacer(Modifier.height(20.dp))
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                    ) {
                        currentQuestion?.allAnswers?.forEach { option ->
                            Card(
                                shape = RoundedCornerShape(16.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp)
                                    .then(
                                        if (selectedOption == option)
                                            Modifier.border(
                                                width = 1.dp,
                                                color = when {
                                                    showAnswerFeedback && option == selectedOption && isAnswerCorrect ->
                                                        MaterialTheme.colorScheme.onTertiary

                                                    showAnswerFeedback && option == selectedOption && !isAnswerCorrect ->
                                                        MaterialTheme.colorScheme.onSecondary

                                                    else -> MaterialTheme.colorScheme.onSurface
                                                },
                                                shape = RoundedCornerShape(12.dp)
                                            )
                                        else Modifier
                                    )
                                    .clip(RoundedCornerShape(16.dp))
                                    .clickable(
                                        enabled = !showAnswerFeedback,
                                        onClick = {
                                            selectedOption = option
                                            isNextButtonEnabled = true
                                        }
                                    ),
                                colors = CardDefaults.cardColors(
                                    containerColor = when {
                                        selectedOption == option ->
                                            MaterialTheme.colorScheme.primary

                                        else ->
                                            MaterialTheme.colorScheme.surface
                                    }
                                )
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .padding(16.dp)
                                ) {
                                    val iconRes = when {
                                        showAnswerFeedback && option == selectedOption && isAnswerCorrect ->
                                            R.drawable.right_answer

                                        showAnswerFeedback && option == selectedOption && !isAnswerCorrect ->
                                            R.drawable.wrong_answer

                                        selectedOption == option ->
                                            R.drawable.chosen_option

                                        else ->
                                            R.drawable.empty_choise
                                    }

                                    Image(
                                        painter = painterResource(id = iconRes),
                                        modifier = Modifier.size(24.dp),
                                        contentDescription = null
                                    )

                                    Spacer(modifier = Modifier.width(12.dp))

                                    Text(
                                        text = option,
                                        fontSize = 14.sp,
                                        color = Color.Black
                                    )
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(60.dp))
                    NextButton(
                        onClick = {
                            selectedOption?.let { answer ->
                                isAnswerCorrect = viewModel.checkAnswer(answer)
                                showAnswerFeedback = true
                                isNextButtonEnabled = false
                            }
                        },
                        isEnabled = isNextButtonEnabled && !showAnswerFeedback,
                        buttonColor = if (isNextButtonEnabled && !showAnswerFeedback) {
                            MaterialTheme.colorScheme.background
                        } else {
                            MaterialTheme.colorScheme.secondary
                        },
                        string = if (questionIndex == totalQuestions-1) {
                            stringResource(id = R.string.finish)
                        } else {
                            stringResource(id = R.string.next)
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = stringResource(id = R.string.mention_about_passing),
            fontSize = 10.sp,
            color = MaterialTheme.colorScheme.primary
        )
    }
}