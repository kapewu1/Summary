package com.paweljablonski.summary.repository

import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import com.paweljablonski.summary.data.DataOrException
import com.paweljablonski.summary.model.MCompetence
import com.paweljablonski.summary.model.MOutcome
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class FireCompetenceRepository @Inject constructor(
    private val queryCompetence: Query
) {
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


//db.collection("cities")
//.add(data)
//.addOnSuccessListener { documentReference ->
//    Log.d(TAG, "DocumentSnapshot written with ID: ${documentReference.id}")
//}
//.addOnFailureListener { e ->
//    Log.w(TAG, "Error adding document", e)
//}
