package com.example.dailyquiz.domain.usecases

import com.example.dailyquiz.domain.model.UserAnswer
import com.example.dailyquiz.domain.repository.QuizResultRepository
import javax.inject.Inject

class SaveQuizResultUseCase @Inject constructor(
    private val repository: QuizResultRepository
) {
    suspend operator fun invoke(
        sessionName: String,
        category: String,
        difficulty: String,
        correctAnswers: Int,
        totalQuestions: Int,
        userAnswers: List<UserAnswer>
    ): Long {
        return repository.saveQuizResult(
            sessionName,
            category,
            difficulty,
            correctAnswers,
            totalQuestions,
            userAnswers
        )
    }
}
