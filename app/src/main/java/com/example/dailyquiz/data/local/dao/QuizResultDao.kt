package com.example.dailyquiz.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.dailyquiz.data.local.entities.QuizResultEntity
import com.example.dailyquiz.data.local.entities.UserAnswerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface QuizResultDao {
    @Insert
    suspend fun insertQuizResult(quizResult: QuizResultEntity): Long

    @Insert
    suspend fun insertUserAnswers(answers: List<UserAnswerEntity>)

    @Query("SELECT * FROM quiz_results ORDER BY timestamp DESC")
    fun getAllQuizResults(): Flow<List<QuizResultEntity>>

    @Query("SELECT * FROM quiz_results WHERE id = :id")
    suspend fun getQuizResultById(id: Long): QuizResultEntity?

    @Query("SELECT * FROM user_answers WHERE quizResultId = :resultId")
    suspend fun getUserAnswersForResult(resultId: Long): List<UserAnswerEntity>

    @Query("SELECT MAX(id) FROM quiz_results")
    suspend fun getLastQuizNumber(): Long?

    @Delete
    suspend fun deleteQuizResult(quizResult: QuizResultEntity)
}