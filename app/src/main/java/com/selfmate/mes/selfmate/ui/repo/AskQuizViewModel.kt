package com.selfmate.mes.selfmate.ui.repo

import android.arch.lifecycle.ViewModel
import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.selfmate.mes.selfmate.models.Question
import javax.inject.Inject


/**
 * Created by vicki_mes on 12/9/2017.
 */


class AskQuizViewModel @Inject
constructor(private val questionRepo: QuestionRepository, firebaseFirestore: FirebaseFirestore) : ViewModel() {
    lateinit var askedQuiz: Question

    var question: String = ""

    var topic: Int = 0

    var subTopic: String = ""

    var moodAsked: Int = 0

    var moodAnswered: Int = 0


    var option1: String = ""

    var option2: String = ""

    var option3: String = ""

    var isClosedQuestion: Boolean = true

    var answer: Int = 3

    var isFailed: Boolean = false

    var views: Int = 0

    var answered: Boolean = false

    private val db = firebaseFirestore


    fun postQuestion(): Boolean {

        askedQuiz = Question(question, topic, subTopic, moodAsked, moodAnswered, option1, option2,
                option3, isClosedQuestion, answer, isFailed, views, answered)

        var boolean = false

        db.collection("questions")
                .add(askedQuiz)
                .addOnSuccessListener {

                    documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.id)
                    boolean = true
                }
                .addOnFailureListener { e -> Log.w(TAG, "Error adding document", e) }

        return boolean

    }


}