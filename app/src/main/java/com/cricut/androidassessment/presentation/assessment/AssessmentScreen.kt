package com.cricut.androidassessment.presentation.assessment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.cricut.androidassessment.R
import com.cricut.androidassessment.data.repository.MockQuestionBankRepository
import com.cricut.androidassessment.domain.usecases.GetAssessmentQuestionsUseCase
import com.cricut.androidassessment.presentation.Screen.Screen
import com.cricut.androidassessment.presentation.components.AnswerTextField
import com.cricut.androidassessment.presentation.components.ChoiceButton
import com.cricut.androidassessment.presentation.components.QuestionNavButton
import com.cricut.androidassessment.presentation.components.QuestionTextView
import com.cricut.androidassessment.shared.QuestionType
import com.cricut.androidassessment.ui.theme.AndroidAssessmentTheme

@Composable
fun AssessmentScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: AssessmentViewModel = hiltViewModel()
) {


    val questions by viewModel.questionsState.collectAsState()

    val currentQuestionIndex by viewModel.currentQuestionIndex.collectAsState()

    val singleChoiceAnswerListState by viewModel.singleChoiceAnswerListState.collectAsState()

    val multiChoiceSelectedAnswers by viewModel.multipleChoiceAnswersState.collectAsState()

    val openEndedAnswerState by viewModel.openEndedAnswerState.collectAsState()

    val isQuestionAnswered by viewModel.questionAnswered.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = modifier
                .weight(1f)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            if (questions.isNotEmpty()) {
                val currentQuestion = questions[currentQuestionIndex]

                QuestionTextView(questionText = currentQuestion.question)

                Spacer(modifier = Modifier.height(16.dp))

                when (currentQuestion.questionType) {
                    QuestionType.TRUE_FALSE -> {
                        if (currentQuestion.choices.isNotEmpty()) {
                            currentQuestion.choices.forEach { choice ->
                                ChoiceButton(
                                    title = choice.choiceText,
                                    onClick = {
                                        viewModel.onChoiceSelect(
                                            choice.id,
                                            questionType = QuestionType.TRUE_FALSE
                                        )
                                    },
                                    onSelected = singleChoiceAnswerListState[currentQuestionIndex] == choice.id
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                            }
                        }
                    }

                    QuestionType.SINGLE_CHOICE -> {
                        if (currentQuestion.choices.isNotEmpty()) {
                            currentQuestion.choices.forEach { choice ->
                                ChoiceButton(
                                    title = choice.choiceText,
                                    onClick = {
                                        viewModel.onChoiceSelect(
                                            choice.id,
                                            questionType = QuestionType.SINGLE_CHOICE
                                        )
                                    },
                                    onSelected = singleChoiceAnswerListState[currentQuestionIndex] == choice.id
                                )

                                Spacer(modifier = Modifier.height(8.dp))
                            }
                        }
                    }

                    QuestionType.MULTI_CHOICE -> {
                        if (currentQuestion.choices.isNotEmpty()) {
                            currentQuestion.choices.forEach { choice ->
                                ChoiceButton(
                                    title = choice.choiceText,
                                    onClick = {
                                        viewModel.onChoiceSelect(
                                            choice.id,
                                            questionType = QuestionType.MULTI_CHOICE
                                        )
                                    },
                                    onSelected = multiChoiceSelectedAnswers[currentQuestionIndex]?.contains(
                                        choice.id
                                    ) ?: false
                                )

                                Spacer(modifier = Modifier.height(8.dp))
                            }
                        }
                    }

                    QuestionType.OPEN_ENDED -> {
                        AnswerTextField(
                            value = openEndedAnswerState,
                            onAnswerChanged = {
                                viewModel.onFillAnswer(it)
                            },
                            placeholder = stringResource(R.string.enter_your_answer_here)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

            }

        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            if (currentQuestionIndex > 0) {
                QuestionNavButton(
                    title = stringResource(R.string.prev_question),
                    onClick = { viewModel.getPrevQuestion() },

                    )
            } else {
                Spacer(modifier.weight(1f))
            }

            val isLastQuestion = currentQuestionIndex == questions.size - 1
            QuestionNavButton(
                title = if (isLastQuestion) stringResource(R.string.finish_quiz) else stringResource(
                    R.string.next_question
                ),
                onClick = {
                    if (isLastQuestion) {
                        navController.navigate(Screen.ResultScreen.route)
                    } else {
                        viewModel.getNextQuestion()
                    }
                },
                isEnabled = isQuestionAnswered[currentQuestionIndex]?:false
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun AssessmentScreenPreview() {
    AndroidAssessmentTheme {
        AssessmentScreen(
            navController = rememberNavController(),
            viewModel = AssessmentViewModel(GetAssessmentQuestionsUseCase(MockQuestionBankRepository()))
        )
    }
}
