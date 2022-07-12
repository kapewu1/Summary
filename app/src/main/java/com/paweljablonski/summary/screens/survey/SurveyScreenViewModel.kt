package com.paweljablonski.summary.screens.survey

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.paweljablonski.summary.data.DataOrException
import com.paweljablonski.summary.model.MCompetence
import com.paweljablonski.summary.model.MCompetenceResult
import com.paweljablonski.summary.model.MOutcome
import com.paweljablonski.summary.model.MQuestion
import com.paweljablonski.summary.repository.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SurveyScreenViewModel @Inject constructor(
    private val repository: FireStoreRepository
): ViewModel() {

        val questionData: MutableState<DataOrException<List<MQuestion>, Boolean, Exception>> = mutableStateOf(
            DataOrException(null, true, Exception(""))
        )

        val outcomeData: MutableState<DataOrException<List<MOutcome>, Boolean, Exception>>
            = mutableStateOf(
        DataOrException(listOf(), true, Exception("")))

        val competenceData: MutableState<DataOrException<List<MCompetence>, Boolean, Exception>>
            = mutableStateOf(
        DataOrException(listOf(), true, Exception("")))

        val competenceResultData: MutableState<DataOrException<List<MCompetenceResult>, Boolean, Exception>>
            = mutableStateOf(
        DataOrException(listOf(), true, Exception("")))

        private val _outcomes = mutableStateListOf<Map<String, Any>>()
        val outcomes : List<Map<String, Any>> = _outcomes




    init {
        getAllQuestions()
        getAllOutcomesFromDatabase()
        getAllCompetenceFromDatabase()
        getAllCompetenceResultFromDatabase()
    }
//
//    private fun getCompetenceResults(): List<Map<String, Any>>{
//
//    }

    private fun getAllQuestions(){
        viewModelScope.launch {
            questionData.value.loading = true
            questionData.value = repository.getAllQuestionsFromDatabase()
            if (!questionData.value.data.isNullOrEmpty()) questionData.value.loading = false
        }
    }

    private fun getAllOutcomesFromDatabase() {
        viewModelScope.launch {
            outcomeData.value.loading = true
            outcomeData.value = repository.getAllOutcomesFromDatabase()
            if (!outcomeData.value.data.isNullOrEmpty()) outcomeData.value.loading = false
        }
    }
    private fun getAllCompetenceFromDatabase() {
        viewModelScope.launch {
            competenceData.value.loading = true
            competenceData.value = repository.getAllCompetenceFromDatabase()
            if (!competenceData.value.data.isNullOrEmpty()) competenceData.value.loading = false
        }
    }
    private fun getAllCompetenceResultFromDatabase() {
        viewModelScope.launch {
            competenceResultData.value.loading = true
            competenceResultData.value = repository.getAllCompetenceResultFromDatabase()
            if (!competenceResultData.value.data.isNullOrEmpty()) competenceResultData.value.loading = false
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

    fun putCompetenceToFirebase(){

        if (!competenceResultData.value.data.isNullOrEmpty()){
        competenceResultData.value.data?.toMutableList()?.forEach { result ->
                try {

                    Log.d("RESULT", "Value of record : ${result.name}")

                } catch (ex : Exception){
                    Log.d("RESULT", ex.toString())
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
