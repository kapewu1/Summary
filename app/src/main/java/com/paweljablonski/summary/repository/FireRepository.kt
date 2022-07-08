package com.paweljablonski.summary.repository

import com.google.firebase.firestore.Query
import javax.inject.Inject

class FireRepository @Inject constructor(
    private val queryUser: Query
){
}