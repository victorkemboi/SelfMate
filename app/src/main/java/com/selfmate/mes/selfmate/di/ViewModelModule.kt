package com.selfmate.mes.selfmate.di

import com.google.firebase.firestore.FirebaseFirestore
import com.selfmate.mes.selfmate.ui.repo.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by vicki_mes on 12/9/2017.
 */
@Module
class ViewModelModule {
    @Singleton
    @Provides
    internal fun providesHomeViewModel(moodLogRepository: MoodLogRepository, resultsRepository: ResultsRepository): HomeViewModel =
            HomeViewModel(moodLogRepository, resultsRepository)

    @Singleton
    @Provides
    internal fun providesClassicRandomViewModel(questionRepo: QuestionRepository, resultsRepository: ResultsRepository, firebaseFirestore: FirebaseFirestore): ClassicRandomViewModel =
            ClassicRandomViewModel(questionRepo, resultsRepository, firebaseFirestore)

    @Singleton
    @Provides
    internal fun providesHotGameViewModel(questionRepo: QuestionRepository, resultsRepository: ResultsRepository): HotGameViewModel =
            HotGameViewModel(questionRepo, resultsRepository)

    @Singleton
    @Provides
    internal fun providesAskQuizViewModel(questionRepo: QuestionRepository, firebaseFirestore: FirebaseFirestore): AskQuizViewModel =
            AskQuizViewModel(questionRepo, firebaseFirestore)
}