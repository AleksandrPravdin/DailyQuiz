package com.example.dailyquiz.domain.usecases

import com.example.dailyquiz.data.local.entities.QuizResultEntity
import com.example.dailyquiz.domain.repository.QuizResultRepository
import javax.inject.Inject

class GetQuizResultByIdUseCase @Inject constructor(
    private val repository: QuizResultRepository
) {
    suspend operator fun invoke(id: Long): QuizResultEntity? {
        return repository.getQuizResultById(id)
    }
}