package com.paweljablonski.summary.screens.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.paweljablonski.summary.model.MUser

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class LoginScreenViewModel: ViewModel() {
//    val loadingState = MutableStateFlow(LoadingState.IDLE)
    private val auth: FirebaseAuth = Firebase.auth

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading


    fun signInWithEmailAndPassword(email: String, password: String, home: () -> Unit)
    = viewModelScope.launch {
        try {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        Log.d("FB", "signInWithEmailAndPassword - SUCCESS ${task.result.toString()}")
                        home()
                    } else {
                        Log.d("FB", "signInWithEmailAndPassword - FAILED")
                    }
                }
        } catch (ex: Exception){
            Log.d("FB", "signInWithEmailAndPassword: ${ex.message}")
        }
    }

    private fun createUser(displayName: String?) {
        val userId = auth.currentUser?.uid
        val user = MUser(
            userId = userId.toString(),
            displayName = "Tester1",
            bio = "Lorem ipsum dksdkakdasmaskdaskdmk",
            avatarUrl = "https://www.firebase.com/dsdaw2d2aaa",
            department = null,
            id = null
        ).toMap()

        FirebaseFirestore.getInstance().collection("users")
            .add(user)
    }

    fun createUserWithEmailAndPassword(
        email: String,
        password: String,
        home: () -> Unit
    ){
        if (_loading.value == false){
            _loading.value = true
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        val displayName = task.result?.user?.email?.split('@')?.get(0)
                        createUser(displayName)
                        home()
                    }else{
                        Log.d("FB", "createUserWithEmailAndPassword: FAILED")
                    }
                }
            _loading.value = false
        }
    }

}