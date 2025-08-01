package com.example.dailyquiz.domain.model

data class Question(
    val type: String,
    val difficulty: String,
    val category: String,
    val text: String,
    val correctAnswer: String,
    val incorrectAnswers: List<String>,
    val allAnswers: List<String>
)