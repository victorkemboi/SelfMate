package com.selfmate.mes.selfmate.ui.repo;

import android.arch.lifecycle.LiveData;

import com.selfmate.mes.selfmate.models.Results;

/**
 * Created by vicki_mes on 11/20/2017.
 */

public interface ResultsRepository {

    LiveData<Results> getResults();


    long insert(Results results);

    void updateResults(Results results);
}
