package com.paweljablonski.summary.di

import com.google.firebase.firestore.FirebaseFirestore
import com.paweljablonski.summary.repository.FireCompetenceRepository
import com.paweljablonski.summary.repository.FireOutcomeRepository
import com.paweljablonski.summary.repository.FireQuestionRepository
import com.paweljablonski.summary.repository.FireUserRepository
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
    = FireUserRepository(queryUser = FirebaseFirestore.getInstance().collection("users"))

    @Singleton
    @Provides
    fun provideFireOutcomeRepository()
    = FireOutcomeRepository(queryOutcome = FirebaseFirestore.getInstance().collection("outcomes"))

    @Singleton
    @Provides
    fun provideFireCompetenceRepository()
    = FireCompetenceRepository(queryCompetence = FirebaseFirestore.getInstance().collection("competences"))

    @Singleton
    @Provides
    fun provideFireQuestionsRepository()
    = FireQuestionRepository(queryQuestion = FirebaseFirestore.getInstance().collection("questions"))

}