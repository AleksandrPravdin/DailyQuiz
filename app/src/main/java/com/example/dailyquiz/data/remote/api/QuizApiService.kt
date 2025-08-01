package com.example.dailyquiz.data.remote.api

import com.example.dailyquiz.data.remote.dto.QuizResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface QuizApiService {
    @GET("api.php")
    suspend fun getQuestions(
        @Query("amount") amount: Int,
        @Query("category") category: Int? = null,
        @Query("difficulty") difficulty: String? = null
    ): QuizResponseDto
}