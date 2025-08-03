package com.example.dailyquiz.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Entity(
    tableName = "user_answers",
    foreignKeys = [
        ForeignKey(
            entity = QuizResultEntity::class,
            parentColumns = ["id"],
            childColumns = ["quizResultId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class UserAnswerEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val quizResultId: Long,
    val questionText: String,
    val userAnswer: String,
    val correctAnswer: String,
    val isCorrect: Boolean,
    val allAnswerOptions: String
){
    fun getAllOptionsList(): List<String> {
        return try {
            Json.decodeFromString(allAnswerOptions)
        } catch (e: Exception) {
            emptyList()
        }
    }

    companion object {
        fun create(
            quizResultId: Long,
            questionText: String,
            userAnswer: String,
            correctAnswer: String,
            isCorrect: Boolean,
            allOptions: List<String>
        ): UserAnswerEntity {
            return UserAnswerEntity(
                quizResultId = quizResultId,
                questionText = questionText,
                userAnswer = userAnswer,
                correctAnswer = correctAnswer,
                isCorrect = isCorrect,
                allAnswerOptions = Json.encodeToString(allOptions)
            )
        }
    }
}