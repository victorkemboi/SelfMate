package com.selfmate.mes.selfmate.di

import android.app.Application
import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.opencsv.CSVReader
import com.selfmate.mes.selfmate.models.MoodLogDao
import com.selfmate.mes.selfmate.models.QuestionDao
import com.selfmate.mes.selfmate.models.ResultsDao
import com.selfmate.mes.selfmate.models.SelfMateDatabase
import com.selfmate.mes.selfmate.ui.repo.*
import dagger.Module
import dagger.Provides
import java.io.IOException
import java.io.InputStreamReader
import java.util.*
import javax.inject.Singleton

/**
 * Created by vicki_mes on 11/18/2017.
 */

@Module
class RoomModule(mApplication: Application) {


    private val selfMateDatabase: SelfMateDatabase

    init {
        selfMateDatabase = Room.databaseBuilder(mApplication, SelfMateDatabase::class.java, DATABASE_NAME)
                .addCallback(object : RoomDatabase.Callback() {

                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)

                        val results = ContentValues()
                        results.put("uid", 1)
                        val date = Date().time
                        results.put("timeCreated", date)
                        results.put("timeUpdated", date)
                        results.put("engagement", 0)
                        results.put("rxpoints", 0)

                        Log.d("results", results.toString())

                        db.insert("results", SQLiteDatabase.CONFLICT_REPLACE, results)

                        try {
                            val inputStream = mApplication.assets.open("quiz_db.csv")
                            val reader = CSVReader(InputStreamReader(inputStream))
                            var nextLine: Array<String>

                            try {
                                while (reader.readNext() != null) {
                                    nextLine = reader.readNext()
                                    val question = nextLine[0]
                                    val topic = nextLine[1]
                                    val option1 = nextLine[2]
                                    val option2 = nextLine[3]
                                    val option3 = nextLine[4]
                                    val typeQuestion = java.lang.Boolean.parseBoolean(nextLine[5])
                                    val answer = Integer.parseInt(nextLine[6])
                                    val moodAsked = 0
                                    val subTopic = "general"
                                    val moodAnswered = 0
                                    val failed = false
                                    val views = 0
                                    val answered = false


                                    val contentValues = ContentValues()
                                    contentValues.put("question", question)
                                    contentValues.put("topic", topic)
                                    contentValues.put("sub_topic", subTopic)
                                    contentValues.put("mood_asked", moodAsked)
                                    contentValues.put("mood_answered", moodAnswered)
                                    contentValues.put("option_1", option1)
                                    contentValues.put("option_2", option2)
                                    contentValues.put("option_3", option3)
                                    contentValues.put("type_question", typeQuestion)
                                    contentValues.put("answer", answer)
                                    contentValues.put("failed", failed)
                                    contentValues.put("views", views)
                                    contentValues.put("answered", answered)

                                    Log.d("results", contentValues.toString())

                                    db.insert("question", SQLiteDatabase.CONFLICT_REPLACE, contentValues)


                                }


                            } catch (e: IOException) {
                                e.printStackTrace()
                            }

                        } catch (e: IOException) {
                            e.printStackTrace()
                        }


                    }

                })
                .build()
    }

    @Singleton
    @Provides
    internal fun providesRoomDatabase(): SelfMateDatabase = selfMateDatabase

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

    @Singleton
    @Provides
    internal fun providesViewModel(questionRepo: QuestionRepository, moodLogRepository: MoodLogRepository, resultsRepository: ResultsRepository): MateViewModel =
            MateViewModel(questionRepo, moodLogRepository, resultsRepository)

    /*
    @Singleton
    @Provides
    internal fun providesExecutor(): Thread {

        val  thread : Thread = Thread()

        return { thread }
    }

    */

    companion object {
        private val DATABASE_NAME = "SelfMate"
    }


}
