package com.example.dailyquiz.data.repository

import android.util.Log
import com.example.dailyquiz.data.remote.api.QuizApiService
import com.example.dailyquiz.data.remote.mapper.QuizMapper
import com.example.dailyquiz.domain.model.Question
import com.example.dailyquiz.domain.repository.QuizPassingRepository
import javax.inject.Inject

class QuizPassingRepositoryImpl @Inject constructor(
    private val apiService: QuizApiService,
    private val mapper: QuizMapper
) : QuizPassingRepository {
    override suspend fun getQuestions(
        amount: Int,
        category: Int?,
        difficulty: String?
    ): List<Question> {
        val response = apiService.getQuestions(amount, category, difficulty)

        if (response.response_code != 0) {
            val errorMessage = when (response.response_code) {
                1 -> "Нет доступных вопросов для выбранной категории/сложности"
                2 -> "Недопустимые параметры запроса"
                3 -> "Не найден токен (не используется сейчас)"
                4 -> "Токен истёк (не используется сейчас)"
                else -> "Неизвестная ошибка"
            }
            throw Exception("API response error: $errorMessage (код ${response.response_code})")
        }

        return mapper.mapDtoListToDomain(response.results)
    }
}