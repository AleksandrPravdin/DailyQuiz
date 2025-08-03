package com.example.dailyquiz.presentation.feature.passing.screen

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dailyquiz.R
import com.example.dailyquiz.domain.model.QuizCategory
import com.example.dailyquiz.domain.model.QuizDifficult
import com.example.dailyquiz.presentation.feature.passing.component.ExpandableDropdown
import com.example.dailyquiz.presentation.feature.passing.component.NextButton
import com.example.dailyquiz.presentation.feature.passing.viewmodel.PassingQuizViewModel

@Composable
fun FilterScreen(viewModel: PassingQuizViewModel) {
    var selectedDifficulty by remember { mutableStateOf<QuizDifficult?>(null) }
    var selectedCategory by remember { mutableStateOf<QuizCategory?>(null) }

    val categories = QuizCategory.values().toList()
    val difficulties = QuizDifficult.values().toList()

    val isButtonEnabled = selectedCategory != null && selectedDifficulty != null
    val buttonColor =
        if (isButtonEnabled) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.surface
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    var isLoadingQuiz by remember { mutableStateOf(false) }

    BackHandler(enabled = true) {
        viewModel.goToStartScreen()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isLoadingQuiz&&uiState.error==null) {
            Spacer(modifier = Modifier.height(300.dp))
            Image(
                painter = painterResource(id = R.drawable.dqlogo),
                modifier = Modifier
                    .width(300.dp)
                    .height(80.dp),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(100.dp))
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .background(Color.Transparent),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(50.dp),
                    strokeWidth = 2.dp
                )
            }
        } else {
            if(uiState.error!=null){
                Toast.makeText(context,uiState.error,Toast.LENGTH_SHORT ).show()
            }
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
                            shape = RoundedCornerShape(40.dp)
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
                                selectedOption = selectedCategory?.displayName
                                    ?: stringResource(id = R.string.category),
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
                                selectedOption = selectedDifficulty?.displayName ?: stringResource(
                                    id = R.string.difficult
                                ),
                                onOptionSelected = { selectedName ->
                                    selectedDifficulty =
                                        difficulties.find { it.displayName == selectedName }
                                },
                                label = stringResource(id = R.string.difficult)
                            )
                        }
                        Spacer(Modifier.height(20.dp))
                        NextButton(onClick = {
                            isLoadingQuiz = true
                            viewModel.loadQuestions(
                                categoryId = selectedCategory?.id,
                                difficulty = selectedDifficulty?.id
                            )

                        }, isEnabled = isButtonEnabled, buttonColor = buttonColor,
                            string = stringResource(id = R.string.next))
                    }
                }
            }
        }
    }
}

