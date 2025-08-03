package com.example.dailyquiz.data.repository

import com.example.dailyquiz.data.local.dao.QuizResultDao
import com.example.dailyquiz.data.local.entities.QuizResultEntity
import com.example.dailyquiz.data.local.entities.UserAnswerEntity
import com.example.dailyquiz.domain.model.UserAnswer
import com.example.dailyquiz.domain.repository.QuizResultRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QuizResultRepositoryImpl @Inject constructor(
    private val quizResultDao: QuizResultDao
) : QuizResultRepository {
    override suspend fun saveQuizResult(
        sessionName: String,
        category: String,
        difficulty: String,
        correctAnswers: Int,
        totalQuestions: Int,
        userAnswers: List<UserAnswer>
    ): Long {
        val quizResultId = quizResultDao.insertQuizResult(
            QuizResultEntity(
                sessionName = sessionName,
                timestamp = System.currentTimeMillis(),
                category = category,
                difficulty = difficulty,
                correctAnswers = correctAnswers,
                totalQuestions = totalQuestions
            )
        )

        val answerEntities = userAnswers.map { answer ->
            UserAnswerEntity.create(
                quizResultId = quizResultId,
                questionText = answer.question.text,
                userAnswer = answer.userAnswer,
                correctAnswer = answer.question.correctAnswer,
                isCorrect = answer.isCorrect,
                allOptions = answer.question.allAnswers
            )
        }

        quizResultDao.insertUserAnswers(answerEntities)
        return quizResultId
    }

    override suspend fun getUserAnswers(resultId: Long): List<UserAnswerEntity> {
        return quizResultDao.getUserAnswersForResult(resultId)
    }

    override suspend fun getLastQuizNumber(): Int {
        return return (quizResultDao.getLastQuizNumber() ?: 0).toInt()
    }

    override suspend fun getQuizResultById(id: Long): QuizResultEntity? {
        return quizResultDao.getQuizResultById(id)
    }

    override fun getAllQuizResults(): Flow<List<QuizResultEntity>> {
        return quizResultDao.getAllQuizResults()
    }


    override suspend fun deleteQuizResult(quizResult: QuizResultEntity) {
        quizResultDao.deleteQuizResult(quizResult)
    }
}