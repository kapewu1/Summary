package com.paweljablonski.summary.screens.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paweljablonski.summary.data.DataOrException
import com.paweljablonski.summary.model.MCompetence
import com.paweljablonski.summary.model.MOutcome
import com.paweljablonski.summary.model.MUser
import com.paweljablonski.summary.repository.FireStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

import javax.inject.Inject
import kotlin.Exception


@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: FireStoreRepository
    ):ViewModel(){

    val userData: MutableState<DataOrException<List<MUser>, Boolean, Exception>>
        = mutableStateOf(
            DataOrException(listOf(), true, Exception("")))
    val outcomeData: MutableState<DataOrException<List<MOutcome>, Boolean, Exception>>
        = mutableStateOf(
            DataOrException(listOf(), true, Exception("")))

    val competenceData: MutableState<DataOrException<List<MCompetence>, Boolean, Exception>>
            = mutableStateOf(
                DataOrException(listOf(), true, Exception("")))

    init {
        getAllUsersFromDatabase()
        getAllOutcomesFromDatabase()
        getAllCompetenceFromDatabase()
    }


    private fun getAllUsersFromDatabase() {
        viewModelScope.launch {
            userData.value.loading = true
            userData.value = repository.getAllUsersFromDatabase()
            if (!userData.value.data.isNullOrEmpty()) userData.value.loading = false
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
}