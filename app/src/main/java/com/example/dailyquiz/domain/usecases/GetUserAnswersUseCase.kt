package com.example.dailyquiz.domain.usecases

import com.example.dailyquiz.data.local.entities.UserAnswerEntity
import com.example.dailyquiz.domain.repository.QuizResultRepository
import javax.inject.Inject

class GetUserAnswersUseCase @Inject constructor(
    private val repository: QuizResultRepository
) {
    suspend operator fun invoke(resultId: Long): List<UserAnswerEntity> {
        return repository.getUserAnswers(resultId)
    }
}