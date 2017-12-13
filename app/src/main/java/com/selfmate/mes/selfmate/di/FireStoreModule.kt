package com.selfmate.mes.selfmate.di

import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by vicki_mes on 12/10/2017.
 */
@Module
class FireStoreModule {
    @Singleton
    @Provides
    internal fun provideFireStoreDb(): FirebaseFirestore = FirebaseFirestore.getInstance()
}