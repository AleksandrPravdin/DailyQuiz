package com.example.dailyquiz.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailyquiz.domain.model.Question
import com.example.dailyquiz.domain.usecases.GetQuizQuestionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PassingQuizViewModel @Inject constructor(
    private val getQuizQuestionsUseCase: GetQuizQuestionsUseCase
) : ViewModel() {

    private val _firstQuestionText = MutableStateFlow<String?>(null)
    val firstQuestionText: StateFlow<String?> = _firstQuestionText

    var questions: List<Question> = emptyList()
        private set

    fun loadQuestions(
    amount: Int = 3,
    categoryId: Int? = null,
    difficulty: String? = null
    ) {
        viewModelScope.launch {
            try {
                Log.d("PassingQuizViewModel", "Загрузка: category=$categoryId, difficulty=$difficulty")

                questions = getQuizQuestionsUseCase(
                    amount = amount,
                    category = categoryId,
                    difficulty = difficulty
                )

                Log.d("PassingQuizViewModel", "Успешно: ${questions.size} вопросов")
                _firstQuestionText.value = questions.firstOrNull()?.text ?: "Нет вопросов"
            } catch (e: Exception) {
                Log.e("PassingQuizViewModel", "Ошибка при загрузке вопросов", e)
                _firstQuestionText.value = "Ошибка! Попробуйте ещё раз"
            }
        }
    }
}
