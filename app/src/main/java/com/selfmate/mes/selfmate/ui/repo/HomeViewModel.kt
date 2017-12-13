package com.selfmate.mes.selfmate.ui.repo


import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.selfmate.mes.selfmate.models.MoodLog
import com.selfmate.mes.selfmate.models.Question
import com.selfmate.mes.selfmate.models.Results
import java.util.*
import javax.inject.Inject

/**
 * Created by vicki_mes on 11/18/2017.
 */

class HomeViewModel @Inject
constructor(private val moodLogRepository: MoodLogRepository, private val resultsRepository: ResultsRepository) : ViewModel() {


    lateinit var resultsLiveData: LiveData<Results>



    var hotQuizStack: LinkedList<Question>? = null

    var mood: Int = 0



    fun addMoodLog(moodLog: MoodLog) {
        // executor.execute(() -> {moodLogRepository.insert(moodLog);});

        object : Thread() {
            override fun run() {

                moodLogRepository.insert(moodLog)
            }
        }.start()


    }



    fun initHotQuizStack(questionListm: List<Question>) {
        hotQuizStack = LinkedList(questionListm)
    }

    fun setResultsLiveData() {


        resultsLiveData = resultsRepository.results

        //Log.d("Has obs", "" + resultsLiveData.hasActiveObservers())




    }

    fun addEngagement(engagement: Int) {

        if (::resultsLiveData.isInitialized) {

        }
    }



}
