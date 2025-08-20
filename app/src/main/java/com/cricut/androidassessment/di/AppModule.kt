package com.cricut.androidassessment.di

import android.app.Application
import android.content.Context
import com.cricut.androidassessment.data.repository.MockQuestionBankRepository
import com.cricut.androidassessment.domain.repository.AssessmentRepository
import com.cricut.androidassessment.domain.usecases.GetAssessmentQuestionsUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    fun providesContext(application : Application) : Context = application


    @Singleton
    @Provides
    fun providesAssessmentRepository():AssessmentRepository =
        MockQuestionBankRepository()


    @Provides
    @Singleton
    fun providesMockQuestionBankRepository(): MockQuestionBankRepository =  MockQuestionBankRepository()

    @Singleton
    @Provides
    fun providesGetAssessmentQuestionsUseCase(assessmentRepository: MockQuestionBankRepository):GetAssessmentQuestionsUseCase =
        GetAssessmentQuestionsUseCase(assessmentRepository)
}