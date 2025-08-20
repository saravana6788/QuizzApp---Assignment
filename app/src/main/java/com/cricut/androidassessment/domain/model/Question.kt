package com.cricut.androidassessment.domain.model

import com.cricut.androidassessment.shared.QuestionType

data class Question(
    val id:Int,
    val questionType: QuestionType,
    val question: String,
    val choices: List<Choice> = emptyList(),
    val correctChoices : List<Int> = emptyList(),
    val openAnswerKeys:List<String> = emptyList(),
)




