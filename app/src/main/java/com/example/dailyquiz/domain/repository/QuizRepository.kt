package com.example.dailyquiz.domain.repository

import com.example.dailyquiz.domain.model.Question

interface QuizRepository {
    suspend fun getQuestions(
        amount: Int,
        category: Int? = null,
        difficulty: String? = null
    ): List<Question>
}