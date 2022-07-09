package com.paweljablonski.summary.repository

import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import com.paweljablonski.summary.data.DataOrException
import com.paweljablonski.summary.model.MOutcome
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class FireOutcomeRepository @Inject constructor(
    private val queryOutcome: Query
) {
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
}
