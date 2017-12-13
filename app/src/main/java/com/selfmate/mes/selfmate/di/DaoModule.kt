package com.selfmate.mes.selfmate.di

import com.selfmate.mes.selfmate.models.MoodLogDao
import com.selfmate.mes.selfmate.models.QuestionDao
import com.selfmate.mes.selfmate.models.ResultsDao
import com.selfmate.mes.selfmate.models.SelfMateDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by vicki_mes on 12/9/2017.
 */
@Module
class DaoModule {
    @Singleton
    @Provides
    internal fun providesQuestionDao(selfMateDatabasem: SelfMateDatabase): QuestionDao =
            selfMateDatabasem.questionDao()

    @Singleton
    @Provides
    internal fun providesResultsDao(selfMateDatabasem: SelfMateDatabase): ResultsDao {
        return selfMateDatabasem.resultsDao()
    }


    @Singleton
    @Provides
    internal fun providesMoodLodDao(selfMateDatabasem: SelfMateDatabase): MoodLogDao {
        return selfMateDatabasem.moodLogDao()
    }
}