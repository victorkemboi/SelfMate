package com.selfmate.mes.selfmate.ui.repo

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.selfmate.mes.selfmate.models.Question
import com.selfmate.mes.selfmate.models.Results
import javax.inject.Inject


/**
 * Created by vicki_mes on 11/29/2017.
 */
class ClassicRandomViewModel @Inject
constructor(private val questionRepo: QuestionRepository, private val resultsRepository: ResultsRepository, firebaseFirestore: FirebaseFirestore) : ViewModel() {


    private val db = firebaseFirestore

    var questionOne: MutableLiveData<Question> = MutableLiveData()


    var questionsRef = db.collection("questions")


    var query = questionsRef.whereEqualTo("answered", false).limit(1).get()


    lateinit var results: LiveData<Results>

    lateinit var randomQuestionLiveData: LiveData<Question>


    fun setResultsLiveData() {


        results = resultsRepository.results

    }
    fun initQuestionOne(question: Question) {

        questionOne.postValue(question)

    }

    fun setRandomQuestionLiveData() {
        this.randomQuestionLiveData = questionRepo.random
    }

    fun updateResults(results: Results) {

        object : Thread() {
            override fun run() {

                resultsRepository.updateResults(results)
                //Log.d("Logging","Updating Results")
            }
        }.start()

    }

    fun updateQuestionInfo(question: Question) {
        object : Thread() {
            override fun run() {

                questionRepo.update(question)
                Log.d("Logging", "Updating Question")
            }
        }.start()
    }


}
