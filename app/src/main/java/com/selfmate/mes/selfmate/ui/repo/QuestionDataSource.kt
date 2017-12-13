package com.selfmate.mes.selfmate.ui.repo

import android.arch.lifecycle.LiveData

import com.selfmate.mes.selfmate.models.Question
import com.selfmate.mes.selfmate.models.QuestionDao

import javax.inject.Inject

/**
 * Created by vicki_mes on 11/18/2017.
 */

class QuestionDataSource @Inject
constructor(private val questionDao: QuestionDao) : QuestionRepository {
    override fun update(question: Question) {
        questionDao.updateQuestion(question)
    }

    override val random: LiveData<Question>
        get() = questionDao.randomQuestion

    override fun findById(id: Int): LiveData<Question> {
        return questionDao.getOneQuestion(id)
    }

    override fun findAll(): LiveData<List<Question>> {
        return questionDao.questions
    }

    override fun setupHot10(): LiveData<List<Question>> {
        return questionDao.hotQuestions
    }

    override fun insert(question: Question): Long {
        return questionDao.insertQuestion(question)
    }

    override fun delete(question: Question): Int {
        return 0
    }
}
