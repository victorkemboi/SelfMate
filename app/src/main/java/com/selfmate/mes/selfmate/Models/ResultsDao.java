package com.selfmate.mes.selfmate.Models;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;


/**
 * Created by vicki_mes on 11/20/2017.
 */
@Dao
public interface ResultsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertResults(Results results);

    @Update
    void updateResults(Results results);

    @Query("SELECT * FROM results WHERE uid=1")
    LiveData<Results> getResults();

}
