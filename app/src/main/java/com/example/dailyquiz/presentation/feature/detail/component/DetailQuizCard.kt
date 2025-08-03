package com.example.dailyquiz.presentation.feature.detail.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dailyquiz.R
import com.example.dailyquiz.data.local.entities.UserAnswerEntity

@Composable
fun DetailQuizCard(userAnswerEntity: UserAnswerEntity, index: Int) {
    Box(
        modifier = Modifier.padding(start = 25.dp, end = 25.dp, top = 25.dp)
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(40.dp)
                )
                .fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(Modifier.height(20.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp),
                    horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Вопрос ${index} из 5",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                    Image(
                        painter = painterResource(id = R.drawable.right_answer),
                        modifier = Modifier.size(24.dp),
                        contentDescription = null
                    )
                }
                Spacer(Modifier.height(20.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = userAnswerEntity.questionText,
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
                    userAnswerEntity.getAllOptionsList().forEach { option ->
                        Card(
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                                .border(
                                    width = 1.dp,
                                    color = when {
                                        option == userAnswerEntity.userAnswer && option == userAnswerEntity.correctAnswer ->
                                            MaterialTheme.colorScheme.onTertiary

                                        option == userAnswerEntity.userAnswer && option != userAnswerEntity.correctAnswer ->
                                            MaterialTheme.colorScheme.onSecondary

                                        else -> MaterialTheme.colorScheme.primary
                                    },
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .clip(RoundedCornerShape(16.dp)),
                            colors = CardDefaults.cardColors(
                                containerColor = when {
                                    userAnswerEntity.userAnswer == option ->
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
                                    option == userAnswerEntity.userAnswer && option == userAnswerEntity.correctAnswer ->
                                        R.drawable.right_answer

                                    option == userAnswerEntity.userAnswer && option != userAnswerEntity.correctAnswer ->
                                        R.drawable.wrong_answer

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
                Spacer(Modifier.height(16.dp))
            }
        }
    }
}