package com.example.dailyquiz.presentation.screen

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.dailyquiz.R
import com.example.dailyquiz.domain.model.QuizCategory
import com.example.dailyquiz.domain.model.QuizDifficult
import com.example.dailyquiz.presentation.viewmodel.PassingQuizViewModel

@Composable
fun FilterScreen(navController: NavController, viewModel: PassingQuizViewModel = hiltViewModel()) {
    var selectedDifficulty by remember { mutableStateOf<QuizDifficult?>(null) }
    var selectedCategory by remember { mutableStateOf<QuizCategory?>(null) }

    val categories = QuizCategory.values().toList()
    val difficulties = QuizDifficult.values().toList()
    val firstQuestion by viewModel.firstQuestionText.collectAsState()

    val isButtonEnabled = selectedCategory != null && selectedDifficulty != null
    val buttonColor =
        if (isButtonEnabled) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.surface

    LaunchedEffect(firstQuestion) {
        firstQuestion?.let {
            Toast.makeText(navController.context, it, Toast.LENGTH_LONG).show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(Modifier.height(100.dp))

        Image(
            painter = painterResource(id = R.drawable.dqlogo),
            modifier = Modifier
                .width(180.dp),
            contentDescription = null
        )

        Spacer(Modifier.height(80.dp))

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
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(id = R.string.almost_ready),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(id = R.string.choose_category),
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                    Spacer(Modifier.height(20.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                    ) {
                        ExpandableDropdown(
                            options = categories.map { it.displayName },
                            selectedOption = selectedCategory?.displayName ?: stringResource(id = R.string.category),
                            onOptionSelected = { selectedName ->
                                selectedCategory =
                                    categories.find { it.displayName == selectedName }
                            },
                            label = stringResource(id = R.string.category)
                        )
                    }

                    Spacer(Modifier.height(16.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                    ) {
                        ExpandableDropdown(
                            options = difficulties.map { it.displayName },
                            selectedOption = selectedDifficulty?.displayName ?: stringResource(id = R.string.difficult),
                            onOptionSelected = { selectedName ->
                                selectedDifficulty =
                                    difficulties.find { it.displayName == selectedName }
                            },
                            label = stringResource(id = R.string.difficult)
                        )
                    }
                    Spacer(Modifier.height(20.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 30.dp, end = 30.dp, bottom = 20.dp)
                    ) {
                        Button(
                            onClick = {
                                viewModel.loadQuestions(
                                    categoryId = selectedCategory?.id,
                                    difficulty = selectedDifficulty?.id
                                )
                            },
                            shape = RoundedCornerShape(15.dp),
                            enabled = isButtonEnabled,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = buttonColor
                            ), modifier = Modifier.height(54.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = stringResource(id = R.string.next),
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
}

@Composable
fun ExpandableDropdown(
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    label: String
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.surface)
            .clickable { expanded = !expanded }
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (selectedOption.isNotBlank()) selectedOption else label,
                modifier = Modifier.weight(1f),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Icon(

                painter = if (expanded) {
                    painterResource(id = R.drawable.arrow_drop_up)
                } else {
                    painterResource(id = R.drawable.arrow_drop_down)
                },
                tint = MaterialTheme.colorScheme.onSurface,
                contentDescription = null
            )
        }

        AnimatedVisibility(visible = expanded) {
            Column(modifier = Modifier.padding(top = 8.dp)) {
                Box(
                    modifier = Modifier
                        .heightIn(max = 100.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Column {
                        options.forEach { option ->
                            Text(
                                text = option,
                                fontSize = 14.sp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        onOptionSelected(option)
                                        expanded = false
                                    }
                                    .padding(vertical = 8.dp),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
        }
    }
}

