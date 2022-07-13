package com.paweljablonski.summary.screens.survey

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.paweljablonski.summary.data.DataOrException
import com.paweljablonski.summary.model.MCompetence
import com.paweljablonski.summary.model.MCompetenceResult
import com.paweljablonski.summary.model.MOutcome
import com.paweljablonski.summary.model.MQuestion
import com.paweljablonski.summary.navigation.SummaryScreens
import com.paweljablonski.summary.repository.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.absoluteValue


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


        private val _results = mutableStateListOf<Map<String, Any>>()
        val results : List<Map<String, Any>> = _results




    init {
        getAllQuestions()
        getAllOutcomesFromDatabase()
        getAllCompetenceFromDatabase()
        getAllCompetenceResultFromDatabase()
    }


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
        if (!competenceData.value.data.isNullOrEmpty()){
            competenceData.value.data?.toMutableList()?.forEach { mCompetence ->
                val competenceId = mCompetence.competenceId
                val userId = Firebase.auth.currentUser?.uid

                if (!competenceResultData.value.data.isNullOrEmpty()){
                    val results = competenceResultData.value?.data!!.toMutableList().filter { mCompetenceResult ->
                        mCompetenceResult.competenceId.trim() == competenceId.trim() && mCompetenceResult.userId.trim() == userId
                    }
                    if (!results.isNullOrEmpty()){
                        val result = results.first()
                        result.score = getCompetenceScore(
                            competenceId = result.competenceId,
                            userId = result.userId
                        )
                        updateResultToFirebase(result)

                    } else {

                        val result = MCompetenceResult(
                            competenceId = competenceId,
                            docId = "",
                            name = mCompetence.name,
                            score = getCompetenceScore(competenceId, userId.toString()),
                            userId = userId.toString()
                        )
                        putResultToFirebase(result)
                    }
                }
            }
        }
    }



    private fun getCompetenceScore(competenceId: String, userId: String): Int {

        //todo: brakuje dodanych obiektów
        var score = 0.0
        var size = 0
        if (!outcomeData.value.data.isNullOrEmpty()) {
            val data = outcomeData.value.data?.toMutableList()?.filter { mOutcome ->
                mOutcome.competenceId.trim() == competenceId.trim() && mOutcome.userId?.trim() == userId.trim()
            }?.forEach {
                Log.d("RESULT", "Score: ${it.score}")
                score += it.score!!
                size++
            }
        }

        Log.d("RESULT", "Score SIZE: ${size}")
        return (score / size).toInt()
    }
    private fun updateResultToFirebase(mCompetenceResult: MCompetenceResult){
        FirebaseFirestore.getInstance().collection("competence_result").document(mCompetenceResult.docId)
            .update(mCompetenceResult.toMap()).addOnSuccessListener {
                Log.d("RESULT", "Outcome is successfully updated in Firebase")
        }

    }

    private fun putResultToFirebase(mCompetenceResult: MCompetenceResult){
        val collection = FirebaseFirestore.getInstance().collection("competence_result")
        collection
            .add(mCompetenceResult.toMap()).addOnSuccessListener { documentReference ->
                collection.document(documentReference.id).update("docId", documentReference.id)
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
