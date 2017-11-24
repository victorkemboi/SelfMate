package com.selfmate.mes.selfmate.Models;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Created by vicki_mes on 11/16/2017.
 */
@Dao
public interface QuestionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertQuestion(Question question);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertQuestions(Question question, ArrayList<Question> questions);

    @Update
    void updateQuestion(Question question);

    @Query("Select * from question")
    LiveData<List<Question>> getQuestions();

    @Query("Select * from question WHERE  topic=8 AND answered='0' LIMIT  10")
    LiveData<List<Question>> getHotQuestions();


    @Query("SELECT * FROM question where uid=:uid")
    LiveData<Question> getOneQuestion(int uid);

    @Query("SELECT * FROM question where answered='0' AND topic<>8 ORDER BY RANDOM() LIMIT  1 ")
    LiveData<Question> getRandomQuestion();



}
