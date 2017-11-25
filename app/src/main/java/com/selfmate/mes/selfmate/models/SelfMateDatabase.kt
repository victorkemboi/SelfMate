package com.selfmate.mes.selfmate.models

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

/**
 * Created by vicki_mes on 11/15/2017.
 */
@Database(entities = arrayOf(MoodLog::class, Question::class, Results::class), version = 1, exportSchema = false)
abstract class SelfMateDatabase : RoomDatabase() {


    abstract fun moodLogDao(): MoodLogDao

    abstract fun questionDao(): QuestionDao
    abstract fun resultsDao(): ResultsDao


}
