package com.selfmate.mes.selfmate.ui.repo

import android.arch.lifecycle.LiveData

import com.selfmate.mes.selfmate.models.MoodLog

/**
 * Created by vicki_mes on 11/18/2017.
 */

interface MoodLogRepository {

    fun findAll(): LiveData<List<MoodLog>>

    fun insert(moodLog: MoodLog): Long

    fun delete(moodLog: MoodLog): Int
}
