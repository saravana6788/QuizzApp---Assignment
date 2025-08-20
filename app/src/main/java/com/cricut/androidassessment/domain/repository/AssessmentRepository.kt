package com.cricut.androidassessment.domain.repository

import com.cricut.androidassessment.domain.model.Question
import kotlinx.coroutines.flow.Flow

interface AssessmentRepository {
    suspend fun getQuestions():Flow<List<Question>>
}