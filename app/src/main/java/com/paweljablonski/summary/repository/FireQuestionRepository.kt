package com.paweljablonski.summary.repository

import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import com.paweljablonski.summary.data.DataOrException
import com.paweljablonski.summary.model.MQuestion
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class FireQuestionRepository @Inject constructor(
    private val queryQuestion: Query
) {
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

}
