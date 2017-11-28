package com.selfmate.mes.selfmate.ui.repo


import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.selfmate.mes.selfmate.models.MoodLog
import com.selfmate.mes.selfmate.models.Question
import com.selfmate.mes.selfmate.models.Results
import com.selfmate.mes.selfmate.si.GAME_TYPE
import java.util.*
import javax.inject.Inject

/**
 * Created by vicki_mes on 11/18/2017.
 */

class MateViewModel @Inject
constructor(private val questionRepo: QuestionRepository, private val moodLogRepository: MoodLogRepository, private val resultsRepository: ResultsRepository) : ViewModel() {


    var listIndexCounter = 1
    var currentListSize = 0
    var gameType = GAME_TYPE.INIT
    var marksEarned = 0

    val questionOne: MutableLiveData<Question> = MutableLiveData()

    lateinit var resultsLiveData: LiveData<Results>

    lateinit var randomQuestionLiveData: LiveData<Question>

    lateinit var hotQuizList: LiveData<List<Question>>

    var hotQuizStack: LinkedList<Question>? = null

    fun initQuestionOne(question: Question) {

        questionOne.postValue(question)

    }

    fun addMoodLog(moodLog: MoodLog) {
        // executor.execute(() -> {moodLogRepository.insert(moodLog);});

        object : Thread() {
            override fun run() {

                moodLogRepository.insert(moodLog)
            }
        }.start()


    }

    fun setResultsLiveData() {


        resultsLiveData = resultsRepository.results

        Log.d("Has obs", "" + resultsLiveData.hasActiveObservers())



        if (resultsLiveData.value == null) {
            Log.d("Check Live Data mp Null", "It is Null.")

        } else {
            Log.d("Check Live Data mp Null", resultsLiveData.value.toString())

        }

    }

    fun updateResults(results: Results) {

        // executor.execute(() -> resultsRepository.updateResults(results));

        //this.resultsLiveDataresult);

        object : Thread() {
            override fun run() {

                resultsRepository.updateResults(results)
            }
        }.start()


    }

    fun populateHotQuizList() {
        hotQuizList = questionRepo.setupHot10()


    }

    fun initHotQuizStack(questionListm: List<Question>) {
        hotQuizStack = LinkedList(questionListm)
    }

    fun setRandomQuestionLiveData() {
        this.randomQuestionLiveData = questionRepo.random
    }
}
