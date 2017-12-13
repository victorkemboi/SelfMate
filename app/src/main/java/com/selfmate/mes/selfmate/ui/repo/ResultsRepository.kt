package com.selfmate.mes.selfmate.ui.repo

import android.arch.lifecycle.LiveData

import com.selfmate.mes.selfmate.models.Results

/**
 * Created by vicki_mes on 11/20/2017.
 */

interface ResultsRepository {

    val results: LiveData<Results>

    val liveResults: Results


    fun insert(results: Results): Long

    fun updateResults(results: Results)
}
