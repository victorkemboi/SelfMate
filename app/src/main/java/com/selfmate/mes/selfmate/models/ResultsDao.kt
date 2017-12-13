package com.selfmate.mes.selfmate.models

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*


/**
 * Created by vicki_mes on 11/20/2017.
 */
@Dao
interface ResultsDao {

    @Query("SELECT * FROM results WHERE uid=1")
    fun getResults(): LiveData<Results>

    @Query("SELECT * FROM results WHERE uid=1")
    fun getLiveResults(): Results


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertResults(results: Results): Long

    @Update
    fun updateResults(results: Results)

}
