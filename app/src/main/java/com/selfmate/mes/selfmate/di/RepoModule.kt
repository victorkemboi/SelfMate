package com.selfmate.mes.selfmate.di

import com.selfmate.mes.selfmate.models.MoodLogDao
import com.selfmate.mes.selfmate.models.QuestionDao
import com.selfmate.mes.selfmate.models.ResultsDao
import com.selfmate.mes.selfmate.ui.repo.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by vicki_mes on 12/9/2017.
 */
@Module
class RepoModule {
    @Singleton
    @Provides
    internal fun questionRepository(questionDao: QuestionDao): QuestionRepository =
            QuestionDataSource(questionDao)

    @Singleton
    @Provides
    internal fun moodLogRepository(moodLogDao: MoodLogDao): MoodLogRepository =
            MoodLogDataSource(moodLogDao)

    @Singleton
    @Provides
    internal fun resultsRepository(resultsDao: ResultsDao): ResultsRepository =
            ResultsDataSource(resultsDao)
}
