package com.paweljablonski.summary.di

import com.google.firebase.firestore.FirebaseFirestore
import com.paweljablonski.summary.repository.FireRepository
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
    = FireRepository(queryUser = FirebaseFirestore.getInstance().collection("users"))

}