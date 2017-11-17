package com.selfmate.mes.selfmate.Models;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by vicki_mes on 11/15/2017.
 */
@Database(entities = {User.class, MoodLog.class, Question.class}, version = 1)
public abstract class SelfMateDatabase extends RoomDatabase {


    public abstract UserDao userDao();

    public abstract MoodLogDao moodLogDao();

    public abstract QuestionDao questionDao();


}
