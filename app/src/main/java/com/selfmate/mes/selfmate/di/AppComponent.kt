package com.selfmate.mes.selfmate.di

import android.app.Application
import com.selfmate.mes.selfmate.models.SelfMateDatabase
import com.selfmate.mes.selfmate.ui.Home
import com.selfmate.mes.selfmate.ui.repo.MateViewModel
import com.selfmate.mes.selfmate.ui.repo.MoodLogRepository
import com.selfmate.mes.selfmate.ui.repo.QuestionRepository
import dagger.Component
import javax.inject.Singleton

/**
 * Created by vicki_mes on 11/18/2017.
 */

@Singleton
@Component(dependencies = arrayOf(), modules = arrayOf(AppModule::class, RoomModule::class))
interface AppComponent {

    fun inject(mainActivity: Home)

    // QuestionDao questionDao();

    fun selfMateDatabase(): SelfMateDatabase

    fun questionRepository(): QuestionRepository

    fun moodLogRepository(): MoodLogRepository

    fun mateViewModel(): MateViewModel

    fun application(): Application

}