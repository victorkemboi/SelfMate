package com.selfmate.mes.selfmate.ui.repo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.selfmate.mes.selfmate.Models.Results;
import com.selfmate.mes.selfmate.Models.ResultsDao;

import javax.inject.Inject;

/**
 * Created by vicki_mes on 11/20/2017.
 */

public class ResultsDataSource implements ResultsRepository {
    private ResultsDao resultsDao;

    @Inject
    public ResultsDataSource(ResultsDao resultsDao) {
        this.resultsDao = resultsDao;
    }

    @Override
    public LiveData<Results> getResults() {
        return resultsDao.getResults();
    }

    @Override
    public long insert(Results results) {
        return resultsDao.insertResults(results);
    }

    @Override
    public void updateResults(Results results) {
         resultsDao.updateResults(results);
    }
}
