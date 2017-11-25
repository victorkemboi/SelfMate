package com.selfmate.mes.selfmate.ui.repo;

import android.arch.lifecycle.LiveData;

import com.selfmate.mes.selfmate.models.Question;
import com.selfmate.mes.selfmate.models.QuestionDao;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by vicki_mes on 11/18/2017.
 */

public class QuestionDataSource implements QuestionRepository {

    private QuestionDao questionDao;

    @Inject
   public QuestionDataSource (QuestionDao questionDaom){

        questionDao = questionDaom;
    }

    @Override
    public LiveData<Question> findById(int id) {
        return questionDao.getOneQuestion(id);
    }

    @Override
    public LiveData<List<Question>> findAll() {
        return questionDao.getQuestions();
    }

    @Override
    public LiveData<List<Question>> setupHot10() {
        return questionDao.getHotQuestions();
    }

    @Override
    public LiveData<Question> getRandom() {
        return questionDao.getRandomQuestion();
    }

    @Override
    public long insert(Question question) {
        return questionDao.insertQuestion(question);
    }

    @Override
    public int delete(Question question) {
        return 0;
    }
}
