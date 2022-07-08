package com.paweljablonski.summary.repository

import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import com.google.firebase.ktx.Firebase
import com.paweljablonski.summary.data.DataOrException
import com.paweljablonski.summary.model.MCompetence
import com.paweljablonski.summary.model.MUser
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class FireRepository @Inject constructor(
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

}

//class FireRepository @Inject constructor(
//    private val queryUser: Query
//){
//
//    suspend fun getAllUsersFromDatabase(): DataOrException<List<MUser>, Boolean, Exception>{
//        val dataOrException = DataOrException<List<MUser>, Boolean, Exception>()
//        try {
//            dataOrException.loading = true
//            dataOrException.data = queryUser.get().await().documents.map{ documentSnapshot ->
//                documentSnapshot.toObject(MUser::class.java)!!
//            }
//            if (!dataOrException.data.isNullOrEmpty()) dataOrException.loading = false
//
//        } catch (exception: FirebaseFirestoreException){
//            dataOrException.e = exception
//        }
//        return dataOrException
//    }
//
//
//    suspend fun getAllCompetenceFromDatabase(): DataOrException<List<MCompetence>, Boolean, Exception>{
//        val dataOrException = DataOrException<List<MCompetence>, Boolean, Exception>()
//        try {
//            dataOrException.loading = true
//            dataOrException.data = queryUser.get().await().documents.map{ documentSnapshot ->
//                documentSnapshot.toObject(MCompetence::class.java)!!
//            }
//            if (!dataOrException.data.isNullOrEmpty()) dataOrException.loading = false
//
//        } catch (exception: FirebaseFirestoreException){
//            dataOrException.e = exception
//        }
//        return dataOrException
//    }
//}