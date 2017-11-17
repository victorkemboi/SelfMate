package com.selfmate.mes.selfmate.Models;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Created by vicki_mes on 11/16/2017.
 */
@Dao
public interface QuestionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertQuestion(Question question);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertQuestions(Question question, ArrayList<Question> questions);

    @Update
    void updateQuestion(Question question);

    @Query("Select * from question")
    Flowable<List<Question>> getQuestions();

    @Query("SELECT * FROM question where uid=:uid")
    Single<Question> getOneQuestion(int uid);

}
