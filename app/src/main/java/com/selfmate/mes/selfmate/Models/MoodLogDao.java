package com.selfmate.mes.selfmate.Models;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by vicki_mes on 11/16/2017.
 */


@Dao
public interface MoodLogDao {

    @Insert
    void insertMoodToLog(MoodLog moodLog);

    @Update
    void updateMoodToLog(MoodLog moodLog);

    @Query("Select * from moodlog")
    List<MoodLog> getMoogLog();
}
