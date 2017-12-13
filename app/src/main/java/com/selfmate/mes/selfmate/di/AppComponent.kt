package com.selfmate.mes.selfmate.di

import android.app.Application
import com.google.firebase.firestore.FirebaseFirestore
import com.selfmate.mes.selfmate.models.SelfMateDatabase
import com.selfmate.mes.selfmate.ui.AskQuizActivity
import com.selfmate.mes.selfmate.ui.ClassicRandomActivity
import com.selfmate.mes.selfmate.ui.Home
import com.selfmate.mes.selfmate.ui.HotGameActivity
import com.selfmate.mes.selfmate.ui.repo.*
import dagger.Component
import javax.inject.Singleton

/**
 * Created by vicki_mes on 11/18/2017.
 */

@Singleton
@Component(dependencies = [], modules = [(AppModule::class), (RoomModule::class), (DaoModule::class)
    , (RepoModule::class), (ViewModelModule::class), (FireStoreModule::class)])
interface AppComponent {

    fun inject(mainActivity: Home)

    fun inject(classicRandomActivity: ClassicRandomActivity)

    fun inject(hotGameActivity: HotGameActivity)

    fun inject(askQuizActivity: AskQuizActivity)

    fun fireStore(): FirebaseFirestore

    fun askQuizViewModel(): AskQuizViewModel

    fun selfMateDatabase(): SelfMateDatabase

    fun questionRepository(): QuestionRepository

    fun moodLogRepository(): MoodLogRepository

    fun homeViewModel(): HomeViewModel

    fun classicRandomViewModel(): ClassicRandomViewModel

    fun hotGameViewModel(): HotGameViewModel

    fun application(): Application

}