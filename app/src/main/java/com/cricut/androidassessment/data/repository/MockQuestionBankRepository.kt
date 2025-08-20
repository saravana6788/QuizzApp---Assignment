package com.cricut.androidassessment.data.repository

import com.cricut.androidassessment.domain.model.Choice
import com.cricut.androidassessment.domain.model.Question
import com.cricut.androidassessment.domain.repository.AssessmentRepository
import com.cricut.androidassessment.shared.QuestionType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class MockQuestionBankRepository : AssessmentRepository {
    override suspend fun getQuestions(): Flow<List<Question>> {
        return flow{
            val questionBank = listOf(
            Question(
                id = 1,
                questionType = QuestionType.TRUE_FALSE,
                question = "Kotlin supports Null Safety",
                choices = listOf(
                    Choice(id = 1, choiceText = "True"),
                    Choice(id = 2, choiceText = "False")
                ),
                correctChoices = listOf(1),
            ),
            Question(
                id = 2,
                questionType = QuestionType.SINGLE_CHOICE,
                question = "What is the correct way to declare a nullable variable in Kotlin?",
                choices = listOf(
                    Choice(id = 1, choiceText = "val name:String"),
                    Choice(id = 2, choiceText = "var name:String?"),
                    Choice(id = 3, choiceText = "var name:String"),
                    Choice(id = 4, choiceText = "None of the above"),
                ),
                correctChoices= listOf(2),
            ),
            Question(
                id = 3,
                questionType = QuestionType.MULTI_CHOICE,
                question = "Which of the following are valid Kotlin collection types?",
                choices = listOf(
                    Choice(id = 1, choiceText = "List"),
                    Choice(id = 2, choiceText = "Set"),
                    Choice(id = 3, choiceText = "Dictionary"),
                    Choice(id = 4, choiceText = "Map"),
                ),
                correctChoices = listOf(1,2,4),
            ),
            Question(
                id = 4,
                questionType = QuestionType.OPEN_ENDED,
                question = "What is the use of Sealed class in Kotlin?",
                openAnswerKeys = listOf("restricted","hierarchy","compile-time")
            ),

            )

            emit(questionBank)

        }
    }

}