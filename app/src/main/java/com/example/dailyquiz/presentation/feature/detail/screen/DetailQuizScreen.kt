package com.example.dailyquiz.presentation.feature.detail.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.dailyquiz.R
import com.example.dailyquiz.presentation.feature.detail.component.DetailQuizCard
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
                                    .padding(start = 30.dp, end = 30.dp)
                            ) {
                                Button(
                                    onClick = {
                                        navController.navigate("quiz_host_screen")
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
                            Spacer(Modifier.height(25.dp))
                        }
                    }
                }
            }
        }
        itemsIndexed(userAnswers) {index, result ->
            DetailQuizCard(userAnswerEntity = result, index = index+1)
        }
        item{
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(25.dp)
            ) {
                Button(
                    onClick = {
                        navController.navigate("quiz_host_screen")
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
        }
    }
}