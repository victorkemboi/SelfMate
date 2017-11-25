package com.selfmate.mes.selfmate.ui.repo;

import android.arch.lifecycle.LiveData;

import com.selfmate.mes.selfmate.models.MoodLog;

import java.util.List;

/**
 * Created by vicki_mes on 11/18/2017.
 */

public interface MoodLogRepository {
    LiveData<MoodLog> findById(int id);

    LiveData<List<MoodLog>> findAll();

    long insert(MoodLog moodLog);

    int delete(MoodLog moodLog);
}
