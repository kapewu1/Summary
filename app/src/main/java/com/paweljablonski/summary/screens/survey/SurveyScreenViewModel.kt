package com.paweljablonski.summary.screens.survey

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
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
        private val _outcomes = mutableStateListOf<Map<String, Any>>()
        val outcomes : List<Map<String, Any>> = _outcomes


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

    fun putOutcomesToFirebase(){
        outcomes.forEach {  outCome ->
            if (!outCome.isNullOrEmpty()){
                try {
                    FirebaseFirestore.getInstance().collection("outcomes")
                        .add(outCome).addOnSuccessListener {
                            Log.d("OUTCOME", "Outcome is successfully added to Firebase ${it.id}")
                        }
                } catch (ex : Exception){
                    Log.d("OUTCOME", ex.toString())
                }
            }
        }
    }

    fun addOutcome(outcome: Map<String, Any>){
        _outcomes.add(outcome)
        Log.d("TAG", "Outcome has been added: ${_outcomes.last().keys}")
    }

    fun getTotalQuestionCount(): Int{
        return questionData.value.data?.toMutableList()!!.size
    }


}
