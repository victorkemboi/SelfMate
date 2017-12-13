package com.selfmate.mes.selfmate.ui.repo

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.selfmate.mes.selfmate.models.Question
import com.selfmate.mes.selfmate.models.Results
import javax.inject.Inject

/**
 * Created by vicki_mes on 11/30/2017.
 */
class HotGameViewModel @Inject
constructor(private val questionRepo: QuestionRepository, private val resultsRepository: ResultsRepository) : ViewModel() {
    var listIndexCounter = 1
    var currentListSize = 0

    var marksEarned = 0

    var results: LiveData<Results> = resultsRepository.results
    var questionOne: MutableLiveData<Question> = MutableLiveData()

    lateinit var hotQuizList: LiveData<List<Question>>

    fun initQuestionOne(question: Question) {

        questionOne.postValue(question)

    }


    fun populateHotQuizList() {
        hotQuizList = questionRepo.setupHot10()


    }

    fun updateResults(results: Results) {


        object : Thread() {
            override fun run() {

                resultsRepository.updateResults(results)
            }
        }.start()


    }


}
