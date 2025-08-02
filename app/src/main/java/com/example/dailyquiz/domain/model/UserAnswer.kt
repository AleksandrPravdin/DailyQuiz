package com.example.dailyquiz.domain.model

data class UserAnswer(
    val question: Question,
    val userAnswer: String,
    val isCorrect: Boolean
)