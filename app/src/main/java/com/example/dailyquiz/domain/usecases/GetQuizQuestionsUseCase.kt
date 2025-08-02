package com.example.dailyquiz.domain.usecases

import com.example.dailyquiz.domain.model.Question
import com.example.dailyquiz.domain.repository.QuizPassingRepository
import javax.inject.Inject

class GetQuizQuestionsUseCase @Inject constructor(
    private val repository: QuizPassingRepository
) {
    suspend operator fun invoke(
        amount: Int,
        category: Int? = null,
        difficulty: String? = null
    ): List<Question> {
        return repository.getQuestions(amount, category, difficulty)
    }
}