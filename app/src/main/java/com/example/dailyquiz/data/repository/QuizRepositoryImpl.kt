package com.example.dailyquiz.data.repository

import android.util.Log
import com.example.dailyquiz.data.remote.api.QuizApiService
import com.example.dailyquiz.data.remote.mapper.QuizMapper
import com.example.dailyquiz.domain.model.Question
import com.example.dailyquiz.domain.repository.QuizRepository
import javax.inject.Inject

class QuizRepositoryImpl @Inject constructor(
    private val apiService: QuizApiService,
    private val mapper: QuizMapper
) : QuizRepository {
    override suspend fun getQuestions(
        amount: Int,
        category: Int?,
        difficulty: String?
    ): List<Question> {
        Log.d("QuizRepositoryImpl", "Отправка запроса: amount=$amount, category=$category, difficulty=$difficulty")

        val response = apiService.getQuestions(amount, category, difficulty)

        Log.d("QuizRepositoryImpl", "Код ответа от API: ${response.response_code}")

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