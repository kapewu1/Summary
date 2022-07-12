package com.paweljablonski.summary.repository

import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import com.paweljablonski.summary.data.DataOrException
import com.paweljablonski.summary.model.*
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FireStoreRepository @Inject constructor(
    private val queryCompetence: Query,
    private val queryCompetenceResult: Query,
    private val queryOutcome: Query,
    private val queryQuestion: Query,
    private val queryUser: Query
) {


    suspend fun getAllUsersFromDatabase(): DataOrException<List<MUser>, Boolean, Exception> {
        val dataOrException = DataOrException<List<MUser>, Boolean, Exception>()

        try {
            dataOrException.loading = true
            dataOrException.data =  queryUser.get().await().documents.map { documentSnapshot ->
                documentSnapshot.toObject(MUser::class.java)!!
            }
            if (!dataOrException.data.isNullOrEmpty()) dataOrException.loading = false


        }catch (exception: FirebaseFirestoreException){
            dataOrException.e = exception
        }
        return dataOrException

    }


    suspend fun getAllQuestionsFromDatabase(): DataOrException<List<MQuestion>, Boolean, Exception> { //ew ArrayList
        val dataOrException = DataOrException<List<MQuestion>, Boolean, Exception>()

        try {
            dataOrException.loading = true
            dataOrException.data =  queryQuestion.get().await().documents.map { documentSnapshot ->
                documentSnapshot.toObject(MQuestion::class.java)!!
            }
            if (!dataOrException.data.isNullOrEmpty()) dataOrException.loading = false


        }catch (exception: FirebaseFirestoreException){
            dataOrException.e = exception
        }
        return dataOrException

    }

    suspend fun getAllOutcomesFromDatabase(): DataOrException<List<MOutcome>, Boolean, Exception> {
        val dataOrException = DataOrException<List<MOutcome>, Boolean, Exception>()

        try {
            dataOrException.loading = true
            dataOrException.data =  queryOutcome.get().await().documents.map { documentSnapshot ->
                documentSnapshot.toObject(MOutcome::class.java)!!
            }

            if (!dataOrException.data.isNullOrEmpty()) dataOrException.loading = false


        }catch (exception: FirebaseFirestoreException){
            dataOrException.e = exception
        }
        return dataOrException
    }

    suspend fun getAllCompetenceResultFromDatabase(): DataOrException<List<MCompetenceResult>, Boolean, Exception> {
        val dataOrException = DataOrException<List<MCompetenceResult>, Boolean, Exception>()

        try {
            dataOrException.loading = true
            dataOrException.data =  queryCompetenceResult.get().await().documents.map { documentSnapshot ->
                documentSnapshot.toObject(MCompetenceResult::class.java)!!
            }

            if (!dataOrException.data.isNullOrEmpty()) dataOrException.loading = false


        }catch (exception: FirebaseFirestoreException){
            dataOrException.e = exception
        }
        return dataOrException
    }


    suspend fun getAllCompetenceFromDatabase(): DataOrException<List<MCompetence>, Boolean, Exception> {
        val dataOrException = DataOrException<List<MCompetence>, Boolean, Exception>()

        try {
            dataOrException.loading = true
            dataOrException.data =  queryCompetence.get().await().documents.map { documentSnapshot ->
                documentSnapshot.toObject(MCompetence::class.java)!!
            }

            if (!dataOrException.data.isNullOrEmpty()) dataOrException.loading = false


        }catch (exception: FirebaseFirestoreException){
            dataOrException.e = exception
        }
        return dataOrException
    }
}

