package com.example.dailyquiz.domain.model

enum class QuizDifficult(val id: String, val displayName: String) {
    GENERAL_KNOWLEDGE("easy", "Easy"),
    BOOKS("medium", "Medium"),
    FILM("hard", "Hard");

    override fun toString(): String = displayName
}