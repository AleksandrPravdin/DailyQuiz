package com.example.dailyquiz.presentation.feature.passing.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dailyquiz.R
import com.example.dailyquiz.presentation.feature.passing.viewmodel.PassingQuizViewModel

@Composable
fun PassingResultCard(viewModel: PassingQuizViewModel) {
    val correctAnswerCount by viewModel.correctAnswersCount.collectAsState()
    val totalQuestions = viewModel.userAnswers.size

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 25.dp)
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(40.dp)
                )
                .fillMaxWidth()
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    repeat(correctAnswerCount) {
                        Image(
                            painter = painterResource(id = R.drawable.active_star),
                            modifier = Modifier
                                .size(46.dp),
                            contentDescription = null
                        )
                    }
                    repeat(totalQuestions - correctAnswerCount) {
                        Image(
                            painter = painterResource(id = R.drawable.inactive_star),
                            modifier = Modifier
                                .size(46.dp),
                            contentDescription = null
                        )
                    }
                }
                Text(
                    text = "${correctAnswerCount} из $totalQuestions", fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(20.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = when (correctAnswerCount) {
                            5 -> stringResource(id = R.string.res_5_logo)
                            4 -> stringResource(id = R.string.res_4_logo)
                            3 -> stringResource(id = R.string.res_3_logo)
                            2 -> stringResource(id = R.string.res_2_logo)
                            1 -> stringResource(id = R.string.res_1_logo)
                            else -> stringResource(id = R.string.res_0_logo)
                        },
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Spacer(Modifier.height(10.dp))
                    Text(
                        text = when (correctAnswerCount) {
                            5 -> stringResource(id = R.string.res_5_text)
                            4 -> stringResource(id = R.string.res_4_text)
                            3 -> stringResource(id = R.string.res_3_text)
                            2 -> stringResource(id = R.string.res_2_text)
                            1 -> stringResource(id = R.string.res_1_text)
                            else -> stringResource(id = R.string.res_0_text)
                        },
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(Modifier.height(60.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 30.dp, end = 30.dp, bottom = 25.dp)
                ) {
                    Button(
                        onClick = {
                            viewModel.resetQuiz()
                            viewModel.goToStartScreen()
                        },
                        shape = RoundedCornerShape(15.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.background
                        ), modifier = Modifier.height(54.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = stringResource(id = R.string.restart_quiz),
                                color = MaterialTheme.colorScheme.primary,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }
        }
    }
}