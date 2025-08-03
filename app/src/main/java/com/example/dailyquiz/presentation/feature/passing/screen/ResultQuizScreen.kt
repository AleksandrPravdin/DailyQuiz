package com.example.dailyquiz.presentation.feature.passing.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dailyquiz.R
import com.example.dailyquiz.presentation.feature.passing.component.PassingResultCard
import com.example.dailyquiz.presentation.feature.passing.viewmodel.PassingQuizViewModel

@Composable
fun ResultQuizScreen(viewModel: PassingQuizViewModel) {

    BackHandler(enabled = true) {
        viewModel.goToFilterScreen()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(Modifier.height(100.dp))

        Text(
            text = stringResource(id = R.string.result),
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp, color = MaterialTheme.colorScheme.primary
        )

        Spacer(Modifier.height(20.dp))

        PassingResultCard(viewModel)
    }
}