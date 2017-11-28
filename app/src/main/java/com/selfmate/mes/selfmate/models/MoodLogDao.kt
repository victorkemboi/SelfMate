package com.selfmate.mes.selfmate.models

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update

/**
 * Created by vicki_mes on 11/16/2017.
 */


@Dao
interface MoodLogDao {

    @get:Query("Select * from moodlog")
    val moogLog: LiveData<List<MoodLog>>

    @Insert
    fun insertMoodToLog(moodLog: MoodLog): Long

    @Update
    fun updateMoodToLog(moodLog: MoodLog): Int

    @Query("select * from moodlog ORDER BY time LIMIT  1")
    fun getLatestMood(): LiveData<MoodLog>
}
