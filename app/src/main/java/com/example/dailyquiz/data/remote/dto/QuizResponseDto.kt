package com.example.dailyquiz.data.remote.dto

data class QuizResponseDto(
    val response_code: Int,
    val results: List<QuestionDto>
)