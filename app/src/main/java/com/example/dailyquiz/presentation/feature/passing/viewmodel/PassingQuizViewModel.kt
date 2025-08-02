package com.example.dailyquiz.presentation.feature.passing.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailyquiz.domain.model.Question
import com.example.dailyquiz.domain.model.QuizCategory
import com.example.dailyquiz.domain.model.QuizDifficult
import com.example.dailyquiz.domain.model.UserAnswer
import com.example.dailyquiz.domain.usecases.GetLastQuizNumberUseCase
import com.example.dailyquiz.domain.usecases.GetQuizQuestionsUseCase
import com.example.dailyquiz.domain.usecases.SaveQuizResultUseCase
import com.example.dailyquiz.presentation.feature.passing.PassingQuizScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class PassingQuizViewModel @Inject constructor(
    private val getQuizQuestionsUseCase: GetQuizQuestionsUseCase,
    private val saveQuizResultUseCase: SaveQuizResultUseCase,
    private val getLastQuizNumberUseCase: GetLastQuizNumberUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(PassingQuizUiState())
    val uiState: StateFlow<PassingQuizUiState> = _uiState

    private val _screenState = MutableStateFlow(PassingQuizScreenState.START)
    val screenState: StateFlow<PassingQuizScreenState> = _screenState

    private val _userAnswers = mutableListOf<UserAnswer>()
    val userAnswers: List<UserAnswer> get() = _userAnswers.toList()

    private val _correctAnswersCount = MutableStateFlow(0)
    val correctAnswersCount: StateFlow<Int> = _correctAnswersCount

    private var currentCategory: String = ""
    private var currentDifficulty: String = ""
    fun goToStartScreen() {
        _screenState.value = PassingQuizScreenState.START
    }

    fun goToFilterScreen() {
        _screenState.value = PassingQuizScreenState.FILTER
    }

    fun goToPassingScreen() {
        _screenState.value = PassingQuizScreenState.PASSING
    }

    fun goToResultScreen() {
        _screenState.value = PassingQuizScreenState.RESULT
    }

    fun loadQuestions(
        amount: Int = 5,
        categoryId: Int? = null,
        difficulty: String? = null
    ) {
        viewModelScope.launch {
            _uiState.value.error=null
            currentCategory = categoryId?.let { QuizCategory.fromId(it) } ?: "Any Category"
            currentDifficulty = difficulty?.let { QuizDifficult.fromId(it) } ?: "Any Difficulty"
            try {
                val questions = getQuizQuestionsUseCase(
                    amount = amount,
                    category = categoryId,
                    difficulty = difficulty
                )

                _uiState.update {
                    it.copy(
                        questions = questions,
                        currentQuestion = questions.firstOrNull(),
                        totalQuestions = questions.size,
                    )
                }
                goToPassingScreen()
            } catch (e: Exception) {
                _uiState.update { it.copy(error = "Ошибка! Попробуйте ещё раз") }
            }
        }
    }

    private suspend fun getNextQuizNumber(): Int {
        return getLastQuizNumberUseCase() + 1
    }

    fun checkAnswer(userAnswer: String): Boolean {
        val currentQuestion = _uiState.value.currentQuestion ?: return false
        val isCorrect = userAnswer == currentQuestion.correctAnswer
        if (isCorrect) {
            _correctAnswersCount.value += 1
        }
        _userAnswers.add(
            UserAnswer(
                question = currentQuestion,
                userAnswer = userAnswer,
                isCorrect = isCorrect
            )
        )
        return isCorrect
    }

    fun resetQuiz() {
        _correctAnswersCount.value = 0
        _userAnswers.clear()
        _uiState.value = PassingQuizUiState()
    }


    fun nextQuestion() {
        val currentIndex = _uiState.value.currentQuestionIndex
        val questions = _uiState.value.questions

        if (currentIndex + 1 < questions.size) {
            _uiState.update {
                it.copy(
                    currentQuestionIndex = currentIndex + 1,
                    currentQuestion = questions[currentIndex + 1]
                )
            }
        } else {
            saveQuizResult()
            goToResultScreen()
        }
    }

    private fun saveQuizResult() {
        viewModelScope.launch {
            val nextQuizNumber = getNextQuizNumber()
            val sessionName = "Quiz $nextQuizNumber"
            saveQuizResultUseCase(
                sessionName = sessionName,
                category = currentCategory,
                difficulty = currentDifficulty,
                correctAnswers = _correctAnswersCount.value,
                totalQuestions = _uiState.value.totalQuestions,
                userAnswers = _userAnswers
            )
        }
    }
}

data class PassingQuizUiState(
    val questions: List<Question> = emptyList(),
    val currentQuestion: Question? = null,
    val currentQuestionIndex: Int = 0,
    val totalQuestions: Int = 0,
    var error: String? = null
)