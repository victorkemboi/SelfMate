package com.selfmate.mes.selfmate.ui.repo

import android.arch.lifecycle.LiveData

import com.selfmate.mes.selfmate.models.Question

/**
 * Created by vicki_mes on 11/18/2017.
 */

interface QuestionRepository {

    val random: LiveData<Question>

    fun findById(id: Int): LiveData<Question>

    fun findAll(): LiveData<List<Question>>

    fun setupHot10(): LiveData<List<Question>>

    fun insert(question: Question): Long

    fun delete(question: Question): Int


}
