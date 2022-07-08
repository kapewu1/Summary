package com.paweljablonski.summary.screens.home

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.paweljablonski.summary.data.DataOrException
import com.paweljablonski.summary.model.MUser
import com.paweljablonski.summary.repository.FireRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

import javax.inject.Inject
import kotlin.Exception


@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: FireRepository):ViewModel(){
    val data: MutableState<DataOrException<List<MUser>, Boolean, Exception>>
        = mutableStateOf(
            DataOrException(listOf(), true, Exception("")))
    init {
        getAllUsersFromDatabase()
    }

    private fun getAllUsersFromDatabase() {
        viewModelScope.launch {
            data.value.loading = true
            data.value = repository.getAllUsersFromDatabase()
            if (!data.value.data.isNullOrEmpty()) data.value.loading = false
        }
        Log.d("HomeScreenViewModel", "getAllUsersFromDatabase: ${data.value.data?.toList().toString()}")

    }

}