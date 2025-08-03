package com.example.dailyquiz.data.remote.mapper

import android.text.Html
import com.example.dailyquiz.data.remote.dto.QuestionDto
import com.example.dailyquiz.domain.model.Question

class QuizMapper {
    fun mapDtoToDomain(dto: QuestionDto): Question {
        return Question(
            type = dto.type,
            difficulty = dto.difficulty,
            category = dto.category,
            text = replaceSpecialSymbols(dto.question),
            correctAnswer = replaceSpecialSymbols(dto.correct_answer),
            incorrectAnswers = dto.incorrect_answers.map { replaceSpecialSymbols(it) },
            allAnswers = (dto.incorrect_answers.map { replaceSpecialSymbols(it) } +
                    replaceSpecialSymbols(dto.correct_answer)).shuffled()
        )
    }

    fun mapDtoListToDomain(dtoList: List<QuestionDto>): List<Question> {
        return dtoList.map { mapDtoToDomain(it) }
    }

    private fun replaceSpecialSymbols(text: String): String {
        return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY).toString()
        } else {
            @Suppress("DEPRECATION")
            Html.fromHtml(text).toString()
        }
    }
}