package com.example.dailyquiz.domain.usecases

import com.example.dailyquiz.data.local.entities.QuizResultEntity
import com.example.dailyquiz.domain.repository.QuizResultRepository
import javax.inject.Inject

class DeleteQuizResultUseCase @Inject constructor(
    private val repository: QuizResultRepository
) {
    suspend operator fun invoke(quizResult: QuizResultEntity) {
        repository.deleteQuizResult(quizResult)
    }
}