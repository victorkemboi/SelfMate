package com.selfmate.mes.selfmate.ui.repo

import android.arch.lifecycle.LiveData

import com.selfmate.mes.selfmate.models.Results
import com.selfmate.mes.selfmate.models.ResultsDao

import javax.inject.Inject

/**
 * Created by vicki_mes on 11/20/2017.
 */

class ResultsDataSource @Inject
constructor(private val resultsDao: ResultsDao) : ResultsRepository {
    override val liveResults: Results
        get() = resultsDao.getLiveResults()

    override val results: LiveData<Results>
        get() = resultsDao.getResults()

    override fun insert(results: Results): Long {
        return resultsDao.insertResults(results)
    }

    override fun updateResults(results: Results) {
        resultsDao.updateResults(results)
    }
}
