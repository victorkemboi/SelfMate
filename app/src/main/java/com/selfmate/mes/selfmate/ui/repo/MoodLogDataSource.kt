package com.selfmate.mes.selfmate.ui.repo

import android.arch.lifecycle.LiveData

import com.selfmate.mes.selfmate.models.MoodLog
import com.selfmate.mes.selfmate.models.MoodLogDao

import javax.inject.Inject

/**
 * Created by vicki_mes on 11/18/2017.
 */

class MoodLogDataSource @Inject
constructor(private val moodLogDao: MoodLogDao) : MoodLogRepository {


    override fun findAll(): LiveData<List<MoodLog>> {
        return moodLogDao.moogLog
    }

    override fun insert(moodLog: MoodLog): Long {
        return moodLogDao.insertMoodToLog(moodLog)
    }

    override fun delete(moodLog: MoodLog): Int {
        return 0
    }
}
