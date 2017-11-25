package com.selfmate.mes.selfmate.ui.repo;

import android.arch.lifecycle.LiveData;

import com.selfmate.mes.selfmate.models.Question;

import java.util.List;

/**
 * Created by vicki_mes on 11/18/2017.
 */

public interface QuestionRepository {

    LiveData<Question> findById(int id);

    LiveData<List<Question>> findAll();

    LiveData<List<Question>> setupHot10();

    LiveData<Question> getRandom();

    long insert(Question question);

    int delete(Question question);


}
