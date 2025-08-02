package com.example.dailyquiz.domain.repository

import com.example.dailyquiz.domain.model.Question

interface QuizPassingRepository {
    suspend fun getQuestions(
        amount: Int,
        category: Int? = null,
        difficulty: String? = null
    ): List<Question>
}