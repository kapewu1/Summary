package com.paweljablonski.summary.screens.home

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class HomeScreenViewModel : ViewModel() {
    private val auth: FirebaseAuth = Firebase.auth

//    fun userLogout() {
//        signOut(auth).
//    }
}