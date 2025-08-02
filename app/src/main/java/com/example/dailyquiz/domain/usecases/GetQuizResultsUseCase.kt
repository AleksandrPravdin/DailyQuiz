package com.example.dailyquiz.domain.usecases

import com.example.dailyquiz.data.local.entities.QuizResultEntity
import com.example.dailyquiz.domain.repository.QuizResultRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetQuizResultsUseCase @Inject constructor(
    private val repository: QuizResultRepository
) {
    operator fun invoke(): Flow<List<QuizResultEntity>> {
        return repository.getAllQuizResults()
    }
}