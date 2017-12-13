package com.selfmate.mes.selfmate.models

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import java.util.*

/**
 * Created by vicki_mes on 11/16/2017.
 */
@Dao
interface QuestionDao {

    @get:Query("Select * from question")
    val questions: LiveData<List<Question>>

    @get:Query("Select * from question WHERE  topic=8 ")
    val hotQuestions: LiveData<List<Question>>

    @get:Query("SELECT * FROM question WHERE answered='0'  ORDER BY RANDOM() LIMIT  1")
    val randomQuestion: LiveData<Question>

    //topic<>8
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertQuestion(question: Question): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertQuestions(question: Question, questions: ArrayList<Question>)

    @Update
    fun updateQuestion(question: Question)


    @Query("SELECT * FROM question where uid= :uid")
    fun getOneQuestion(uid: Int): LiveData<Question>


}
