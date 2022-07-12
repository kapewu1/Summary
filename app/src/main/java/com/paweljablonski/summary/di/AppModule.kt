package com.paweljablonski.summary.di

import com.google.firebase.firestore.FirebaseFirestore
import com.paweljablonski.summary.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {





    @Singleton
    @Provides
    fun provideFireUserRepository()
    = FireStoreRepository(
        queryUser = FirebaseFirestore.getInstance().collection("users"),
        queryOutcome = FirebaseFirestore.getInstance().collection("outcomes"),
        queryCompetence = FirebaseFirestore.getInstance().collection("competences"),
        queryQuestion = FirebaseFirestore.getInstance().collection("questions"),
        queryCompetenceResult = FirebaseFirestore.getInstance().collection("competence_result")
    )

//    @Singletond
//    @Provides
//    fun provideFireOutcomeRepository()
//    = FireOutcomeRepository(queryOutcome = FirebaseFirestore.getInstance().collection("outcomes"))
//
//    @Singleton
//    @Provides
//    fun provideFireCompetenceRepository()
//    = FireCompetenceRepository(queryCompetence = FirebaseFirestore.getInstance().collection("competences"))
//
//   @Singleton
//    @Provides
//    fun provideFireCompetenceResultRepository()
//    = FireCompetenceResultRepository(queryCompetenceResult = FirebaseFirestore.getInstance().collection("competence_result"))
//
//    @Singleton
//    @Provides
//    fun provideFireQuestionsRepository()
//    = FireQuestionRepository(queryQuestion = FirebaseFirestore.getInstance().collection("questions"))


//
//
//
//    @Singleton
//    @Provides
//    fun provideFireUserRepository()
//    = FireUserRepository(queryUser = FirebaseFirestore.getInstance().collection("users"))
//
//    @Singleton
//    @Provides
//    fun provideFireOutcomeRepository()
//    = FireOutcomeRepository(queryOutcome = FirebaseFirestore.getInstance().collection("outcomes"))
//
//    @Singleton
//    @Provides
//    fun provideFireCompetenceRepository()
//    = FireCompetenceRepository(queryCompetence = FirebaseFirestore.getInstance().collection("competences"))
//
//   @Singleton
//    @Provides
//    fun provideFireCompetenceResultRepository()
//    = FireCompetenceResultRepository(queryCompetenceResult = FirebaseFirestore.getInstance().collection("competence_result"))
//
//    @Singleton
//    @Provides
//    fun provideFireQuestionsRepository()
//    = FireQuestionRepository(queryQuestion = FirebaseFirestore.getInstance().collection("questions"))
//
//
//

}