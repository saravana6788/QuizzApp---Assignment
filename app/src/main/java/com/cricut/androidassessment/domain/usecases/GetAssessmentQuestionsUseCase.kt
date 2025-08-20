package com.cricut.androidassessment.domain.usecases

import com.cricut.androidassessment.domain.model.Question
import com.cricut.androidassessment.domain.repository.AssessmentRepository
import kotlinx.coroutines.flow.Flow

class GetAssessmentQuestionsUseCase(private val repository: AssessmentRepository) {

    suspend operator fun invoke():Flow<List<Question>>{
        return repository.getQuestions()
    }
}