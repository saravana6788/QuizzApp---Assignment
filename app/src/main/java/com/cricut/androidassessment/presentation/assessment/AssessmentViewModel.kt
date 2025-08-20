package com.cricut.androidassessment.presentation.assessment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cricut.androidassessment.domain.model.Question
import com.cricut.androidassessment.domain.usecases.GetAssessmentQuestionsUseCase
import com.cricut.androidassessment.shared.QuestionType
import com.cricut.androidassessment.shared.QuestionType.MULTI_CHOICE
import com.cricut.androidassessment.shared.QuestionType.OPEN_ENDED
import com.cricut.androidassessment.shared.QuestionType.SINGLE_CHOICE
import com.cricut.androidassessment.shared.QuestionType.TRUE_FALSE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AssessmentViewModel
@Inject constructor(val questionsUseCase: GetAssessmentQuestionsUseCase) : ViewModel() {

    private val _questionsListState = MutableStateFlow<List<Question>>(emptyList())
    val questionsState:StateFlow<List<Question>> = _questionsListState


    private val _currentQuestionIndex = MutableStateFlow(0)
    val currentQuestionIndex:StateFlow<Int> = _currentQuestionIndex


    private val _openEndedAnswerState = MutableStateFlow("")
    val openEndedAnswerState:StateFlow<String> =  _openEndedAnswerState

    private val _singleChoiceAnswerListState = MutableStateFlow<Map<Int,Int>>(emptyMap())
    val singleChoiceAnswerListState:StateFlow<Map<Int,Int>> = _singleChoiceAnswerListState

    private val _multipleChoiceAnswersState = MutableStateFlow<Map<Int,Set<Int>>>(emptyMap())
    val multipleChoiceAnswersState:StateFlow<Map<Int,Set<Int>>> = _multipleChoiceAnswersState

    private val _isQuestionAnswered = MutableStateFlow<Map<Int,Boolean>>(emptyMap())
    val questionAnswered:StateFlow<Map<Int,Boolean>> = _isQuestionAnswered



    init {
        getQuestions()
    }

    private fun getQuestions(){
        viewModelScope.launch {
            questionsUseCase().collect{
                _questionsListState.value = it
            }
        }
    }


    fun getNextQuestion(){
        if(currentQuestionIndex.value<questionsState.value.size -1 ){
            _currentQuestionIndex.value++
        }else{
            _currentQuestionIndex.value = -1
        }


    }

    fun getPrevQuestion(){
        if(currentQuestionIndex.value>0){
            _currentQuestionIndex.value--
        }else{
            _currentQuestionIndex.value = -1
        }

    }

    fun onChoiceSelect(choiceId:Int,  questionType: QuestionType){

        when(questionType){
            TRUE_FALSE, SINGLE_CHOICE -> {
                _singleChoiceAnswerListState.update{
                    val map = it.toMutableMap()
                    map[_currentQuestionIndex.value] = choiceId
                    map.toMap()
                }
                _isQuestionAnswered.update {
                    val map = it.toMutableMap()
                    map[_currentQuestionIndex.value] = true
                    map.toMap()
                }
            }
            MULTI_CHOICE -> {
                val index = currentQuestionIndex.value
                _multipleChoiceAnswersState.update {
                    val map = it.toMutableMap()
                    val currentSet = it[index]?: emptySet()
                    val mutableCurrentSet = currentSet.toMutableSet()
                    if(currentSet.contains(choiceId)){
                        mutableCurrentSet -=  choiceId
                    }else {
                        mutableCurrentSet += choiceId
                    }
                    map[index] = mutableCurrentSet.toSet()
                    map.toMap()
                }
                _isQuestionAnswered.update {
                    val map = it.toMutableMap()
                    map[_currentQuestionIndex.value] = multipleChoiceAnswersState.value[currentQuestionIndex.value]?.isNotEmpty()?:false
                    map.toMap()
                }

            }
            OPEN_ENDED -> {}
        }

    }

    fun onFillAnswer(answer:String){
        _openEndedAnswerState.value = answer
        _isQuestionAnswered.update {
            val map = it.toMutableMap()
            map[_currentQuestionIndex.value] = answer.isNotEmpty()
            map.toMap()
        }
    }


}
