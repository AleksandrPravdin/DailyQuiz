package com.example.dailyquiz.domain.usecases

import com.example.dailyquiz.domain.repository.QuizResultRepository
import javax.inject.Inject

class GetLastQuizNumberUseCase @Inject constructor(
    private val repository: QuizResultRepository
) {
    suspend operator fun invoke(): Int {
        return repository.getLastQuizNumber()
    }
}