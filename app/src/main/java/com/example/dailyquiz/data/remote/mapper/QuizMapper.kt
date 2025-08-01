package com.example.dailyquiz.data.remote.mapper

import com.example.dailyquiz.data.remote.dto.QuestionDto
import com.example.dailyquiz.domain.model.Question

class QuizMapper {
    fun mapDtoToDomain(dto: QuestionDto): Question {
        return Question(
            type = dto.type,
            difficulty = dto.difficulty,
            category = dto.category,
            text = dto.question,
            correctAnswer = dto.correct_answer,
            incorrectAnswers = dto.incorrect_answers,
            allAnswers = (dto.incorrect_answers + dto.correct_answer).shuffled()
        )
    }

    fun mapDtoListToDomain(dtoList: List<QuestionDto>): List<Question> {
        return dtoList.map { mapDtoToDomain(it) }
    }
}