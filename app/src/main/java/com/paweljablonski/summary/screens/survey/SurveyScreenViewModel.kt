package com.paweljablonski.summary.screens.survey

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paweljablonski.summary.data.DataOrException
import com.paweljablonski.summary.model.MQuestion
import com.paweljablonski.summary.repository.FireQuestionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SurveyScreenViewModel @Inject constructor(
    private val questionRepository: FireQuestionRepository
): ViewModel() {
        val questionData: MutableState<DataOrException<List<MQuestion>, Boolean, Exception>> = mutableStateOf(
            DataOrException(null, true, Exception(""))
        )

    init {
        getAllQuestions()
    }

    private fun getAllQuestions(){
        viewModelScope.launch {
            questionData.value.loading = true
            questionData.value = questionRepository.getAllQuestionsFromDatabase()
            if (!questionData.value.data.isNullOrEmpty()) questionData.value.loading = false
        }
    }

    fun getTotalQuestionCount(): Int{
        return questionData.value.data?.toMutableList()!!.size
    }


}
