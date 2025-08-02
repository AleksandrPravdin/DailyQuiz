package com.example.dailyquiz.presentation.feature.history.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailyquiz.data.local.entities.QuizResultEntity
import com.example.dailyquiz.data.local.entities.UserAnswerEntity
import com.example.dailyquiz.domain.usecases.DeleteQuizResultUseCase
import com.example.dailyquiz.domain.usecases.GetQuizResultsUseCase
import com.example.dailyquiz.domain.usecases.GetUserAnswersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryQuizViewModel @Inject constructor(
    private val getQuizResultsUseCase: GetQuizResultsUseCase,
    private val getUserAnswersUseCase: GetUserAnswersUseCase,
    private val deleteQuizResultUseCase: DeleteQuizResultUseCase
) : ViewModel() {
    val quizResults = getQuizResultsUseCase()

    fun deleteQuizResult(quizResult: QuizResultEntity) {
        viewModelScope.launch {
            deleteQuizResultUseCase(quizResult)
        }
    }

    suspend fun getUserAnswers(resultId: Long): List<UserAnswerEntity> {
        return getUserAnswersUseCase(resultId)
    }
}