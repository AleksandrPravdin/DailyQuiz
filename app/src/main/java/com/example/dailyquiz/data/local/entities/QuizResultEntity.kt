package com.example.dailyquiz.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quiz_results")
data class QuizResultEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val sessionName: String,
    val timestamp: Long,
    val category: String,
    val difficulty: String,
    val correctAnswers: Int,
    val totalQuestions: Int
)