package com.example.dailyquiz.presentation.feature.detail.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailyquiz.data.local.entities.QuizResultEntity
import com.example.dailyquiz.data.local.entities.UserAnswerEntity
import com.example.dailyquiz.domain.usecases.GetQuizResultByIdUseCase
import com.example.dailyquiz.domain.usecases.GetUserAnswersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailQuizViewModel @Inject constructor(
    private val getUserAnswersUseCase: GetUserAnswersUseCase,
    private val getQuizResultByIdUseCase: GetQuizResultByIdUseCase,
) : ViewModel() {
    private val _quizResult = MutableStateFlow<QuizResultEntity?>(null)
    val quizResult: StateFlow<QuizResultEntity?> = _quizResult

    private val _userAnswers = MutableStateFlow<List<UserAnswerEntity>>(emptyList())
    val userAnswers: StateFlow<List<UserAnswerEntity>> = _userAnswers

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun loadQuizData(id: Long) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val resultDeferred = async { getQuizResultByIdUseCase(id) }
                val answersDeferred = async { getUserAnswersUseCase(id) }

                _quizResult.value = resultDeferred.await()
                _userAnswers.value = answersDeferred.await()
            } catch (e: Exception) {
                Log.e("DetailQuizViewModel", "Error loading quiz data", e)
            } finally {
            }
        }
    }
}