package com.example.dailyquiz.presentation.feature.history.component

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dailyquiz.R
import com.example.dailyquiz.data.local.entities.QuizResultEntity
import com.example.dailyquiz.utils.toDayMonthString
import com.example.dailyquiz.utils.toTimeString


@Composable
fun QuizResultCard(
    result: QuizResultEntity, onDelete: () -> Unit, navController: NavController
) {
    val correctAnswerCount = result.correctAnswers
    val totalQuestions = result.totalQuestions
    val context = LocalContext.current
    var showContextMenu by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 25.dp, end = 25.dp, bottom = 25.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = { showContextMenu = true },
                    onTap = {navController.navigate("detail_quiz_screen/${result.id}")}
                )
            }
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
                modifier = Modifier.padding(horizontal = 25.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, bottom = 15.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = "${result.sessionName}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp, color = MaterialTheme.colorScheme.onSurface
                    )

                    Row(
                        modifier = Modifier
                            .wrapContentSize(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        repeat(correctAnswerCount) {
                            Box(modifier = Modifier.padding(start = 8.dp)) {
                                Image(
                                    painter = painterResource(id = R.drawable.active_star),
                                    modifier = Modifier
                                        .size(16.dp),
                                    contentDescription = null
                                )
                            }
                        }
                        repeat(totalQuestions - correctAnswerCount) {
                            Box(modifier = Modifier.padding(start = 8.dp)) {
                                Image(
                                    painter = painterResource(id = R.drawable.inactive_star),
                                    modifier = Modifier
                                        .size(16.dp),
                                    contentDescription = null
                                )
                            }
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = result.timestamp.toDayMonthString(),
                        fontSize = 12.sp
                    )
                    Text(
                        text = result.timestamp.toTimeString(),
                        fontSize = 12.sp
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = "Категория: ${result.category}",
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Сложность: ${result.difficulty}",
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.height(20.dp))
            }
        }

        DropdownMenu(
            expanded = showContextMenu,
            onDismissRequest = { showContextMenu = false },
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(12.dp)
                )
                .clip(RoundedCornerShape(12.dp))
                .width(230.dp),
        ) {
            DropdownMenuItem(
                text = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(15.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.delete_icon),
                            contentDescription = null,
                            tint = Color.Black,
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            text = stringResource(R.string.delete),
                            color = Color.Black,
                            fontSize = 14.sp
                        )
                    }
                },
                onClick = {
                    showContextMenu = false
                    onDelete()
                    Toast.makeText(context, "Попытка удалена", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier
                    .padding(horizontal = 8.dp)
            )
        }
    }
}