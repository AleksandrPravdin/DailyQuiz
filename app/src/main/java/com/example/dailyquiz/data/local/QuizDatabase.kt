package com.example.dailyquiz.data.local

import androidx.room.Database
import com.example.dailyquiz.data.local.dao.QuizResultDao
import com.example.dailyquiz.data.local.entities.QuizResultEntity
import com.example.dailyquiz.data.local.entities.UserAnswerEntity
import androidx.room.RoomDatabase

@Database(
    entities = [QuizResultEntity::class, UserAnswerEntity::class],
    version = 2
)
abstract class QuizDatabase : RoomDatabase() {
    abstract fun quizResultDao(): QuizResultDao
}