package com.example.dailyquiz.domain.repository

import com.example.dailyquiz.data.local.entities.QuizResultEntity
import com.example.dailyquiz.data.local.entities.UserAnswerEntity
import com.example.dailyquiz.domain.model.UserAnswer
import kotlinx.coroutines.flow.Flow

interface QuizResultRepository {
    suspend fun saveQuizResult(
        sessionName: String,
        category: String,
        difficulty: String,
        correctAnswers: Int,
        totalQuestions: Int,
        userAnswers: List<UserAnswer>
    ): Long

    fun getAllQuizResults(): Flow<List<QuizResultEntity>>

    suspend fun getUserAnswers(resultId: Long): List<UserAnswerEntity>

    suspend fun deleteQuizResult(quizResult: QuizResultEntity)

    suspend fun getLastQuizNumber(): Int
}