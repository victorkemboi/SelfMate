package com.selfmate.mes.selfmate.ui.repo;

import android.arch.lifecycle.LiveData;

import com.selfmate.mes.selfmate.models.MoodLog;
import com.selfmate.mes.selfmate.models.MoodLogDao;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by vicki_mes on 11/18/2017.
 */

public class MoodLogDataSource implements MoodLogRepository {
    private MoodLogDao moodLogDao;

    @Inject
    public MoodLogDataSource(MoodLogDao moodLogDao) {
        this.moodLogDao = moodLogDao;
    }

    @Override
    public LiveData<MoodLog> findById(int id) {
        return null;
    }

    @Override
    public LiveData<List<MoodLog>> findAll() {
        return null;
    }

    @Override
    public long insert(MoodLog moodLog) {
        return moodLogDao.insertMoodToLog(moodLog);
    }

    @Override
    public int delete(MoodLog moodLog) {
        return 0;
    }
}
