package com.selfmate.mes.selfmate.ui

import android.arch.lifecycle.Observer
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import com.selfmate.mes.selfmate.R
import com.selfmate.mes.selfmate.di.AppModule
import com.selfmate.mes.selfmate.di.DaggerAppComponent
import com.selfmate.mes.selfmate.di.RoomModule
import com.selfmate.mes.selfmate.models.Question
import com.selfmate.mes.selfmate.si.GAME_TYPE
import com.selfmate.mes.selfmate.si.TOPIC
import com.selfmate.mes.selfmate.ui.repo.MateViewModel
import kotlinx.android.synthetic.main.activity_quiz.*
import nl.dionsegijn.konfetti.models.Shape
import nl.dionsegijn.konfetti.models.Size
import javax.inject.Inject

class QuizActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: MateViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        DaggerAppComponent.builder()
                .appModule(AppModule(application))
                .roomModule(RoomModule(application))
                .build()
                .inject(this)

        toolbar1.title = ""
        setSupportActionBar(toolbar1)

        if (viewModel.gameType == GAME_TYPE.HOT) {

            viewModel.populateHotQuizList()


            viewModel.listIndexCounter = 0
            viewModel.hotQuizList.observe(this, Observer<List<Question>> { list ->
                if (list != null) {
                    //Log.d("hotQuizList", "" + list.size)
                    viewModel.currentListSize = list.size
                    viewModel.initQuestionOne(list[viewModel.listIndexCounter])
                    setUpQuestion(list[viewModel.listIndexCounter])

                }
            })
        } else if (viewModel.gameType == GAME_TYPE.CLASSIC) {
            nextClassicQuestion()
        }



        teaserQuizOneTv1.setOnClickListener { view -> checkAnswer(view as TextView) }
        teaserQuizOneTv2.setOnClickListener { view -> checkAnswer(view as TextView) }
        teaserQuizOneTv3.setOnClickListener { view -> checkAnswer(view as TextView) }
    }


    private fun checkAnswer(textView: TextView) {
        if (viewModel.questionOne.value!!.isType_question) {

            if (checkResult(textView)) {
                success(textView)
            } else {
                failed(textView)
            }
        } else {

            userChoice(textView)
        }


    }


    private fun checkResult(textView: TextView): Boolean {

        val test = viewModel.questionOne.value

        val ans = test!!.answer

        when (ans) {
            1 -> return test.option1 === textView.text
            2 -> return test.option2 === textView.text
            3 -> return test.option3 === textView.text
        }

        return false
    }

    private fun issaQuiz(): Boolean {

        /*viewModel.questionOne.observe(
                this, Observer<Question> {
            quiz->
            if (quiz!= null)  return quiz
        }
        ) */
        return false


    }

    private fun failed(textView: TextView) {


        textView.background = ContextCompat.getDrawable(applicationContext, R.drawable.red_back)

        textView.text = "Wrong !"

        // viewModel.initQuestionOne();

        viewKonfetti!!.build()
                .addColors(Color.BLACK, Color.RED)
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(200L)
                .addShapes(Shape.RECT, Shape.CIRCLE)
                .addSizes(Size(5, 5f))
                .setPosition(-50f, viewKonfetti!!.width + 50f, -50f, -50f)
                .stream(300, 300L)


        val handler = Handler()
        handler.postDelayed({

            textView.background = ContextCompat.getDrawable(applicationContext, R.drawable.rounded_grey)
            chooseNextQuiz()


        }, 500)


    }


    private fun userChoice(textView: TextView) {


        val results = viewModel.resultsLiveData.value

        if (results != null) {

            var res = results.rxpoints
            res += 3
            results.rxpoints = res
            viewModel.updateResults(results)

        }
        textView.background = ContextCompat.getDrawable(applicationContext, R.drawable.choice)


        viewKonfetti!!.build()
                .addColors(Color.BLUE, Color.MAGENTA)
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(200L)
                .addShapes(Shape.RECT, Shape.CIRCLE)
                .addSizes(Size(5, 5f))
                .setPosition(-50f, viewKonfetti!!.width + 50f, -50f, -50f)
                .stream(800, 300L)


        val handler = Handler()
        handler.postDelayed({

            textView.background = ContextCompat.getDrawable(applicationContext, R.drawable.rounded_grey)

            chooseNextQuiz()


        }, 500)


    }

    private fun success(textView: TextView) {
        val results = viewModel.resultsLiveData.value

        if (results != null) {

            var res = results.rxpoints
            res += 5
            results.rxpoints = res
            viewModel.updateResults(results)

        }

        textView.background = ContextCompat.getDrawable(applicationContext, R.drawable.success_back)

        textView.text = "Correct"

        viewKonfetti!!.build()
                .addColors(Color.YELLOW, Color.GREEN, Color.BLUE)
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(200L)
                .addShapes(Shape.RECT, Shape.CIRCLE)
                .addSizes(Size(5, 5f))
                .setPosition(viewKonfetti!!.x + viewKonfetti!!.width / 2, viewKonfetti!!.y + viewKonfetti!!.height / 3)
                .burst(500)


        val handler = Handler()
        handler.postDelayed({
            textView.background = ContextCompat.getDrawable(applicationContext, R.drawable.rounded_grey)

            chooseNextQuiz()


        }, 500)


    }

    private fun chooseNextQuiz() {


        when (viewModel.gameType) {

            1 -> {
                nextHotQuestion()
            }
            2 -> nextClassicQuestion()
        }
    }

    private fun nextHotQuestion() {

        viewModel.listIndexCounter += 1
        //viewModel.currentListSize = 9
        if (viewModel.listIndexCounter + 1 <= viewModel.currentListSize) {

            Log.d("hotQuizList", "" + viewModel.currentListSize)
            Log.d("listIndexCounter", "" + viewModel.listIndexCounter)
            val list = viewModel.hotQuizList.value

            if (list != null) {
                viewModel.initQuestionOne(list[viewModel.listIndexCounter])
                setUpQuestion(list[viewModel.listIndexCounter])

            }

        } else {
            return

        }
    }

    private fun nextClassicQuestion() {


        viewModel.setRandomQuestionLiveData()

        viewModel.randomQuestionLiveData.observe(this, Observer<Question> { quiz ->
            if (quiz != null) {
                //viewModel.questionOne.value = quiz
                viewModel.initQuestionOne(quiz)
                setUpQuestion(quiz)

            }
        })


    }


    private fun setUpQuestion(question: Question) {


        teaserQuizOneTv.text = question.question
        teaserQuizOneTv1.text = question.option1
        teaserQuizOneTv2.text = question.option2
        teaserQuizOneTv3.text = question.option3
        tvTopic.text = TOPIC.getTopic(question.topic)
        //loadQuizImage(question.getImage_name());


    }


}
