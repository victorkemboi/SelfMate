package com.selfmate.mes.selfmate.ui

import android.arch.lifecycle.Observer
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.TextView
import com.selfmate.mes.selfmate.App
import com.selfmate.mes.selfmate.R
import com.selfmate.mes.selfmate.models.Question
import com.selfmate.mes.selfmate.models.Results
import com.selfmate.mes.selfmate.si.TOPIC
import com.selfmate.mes.selfmate.ui.repo.ClassicRandomViewModel
import kotlinx.android.synthetic.main.activity_classic_random.*
import nl.dionsegijn.konfetti.models.Shape
import nl.dionsegijn.konfetti.models.Size
import javax.inject.Inject

class ClassicRandomActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: ClassicRandomViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_classic_random)

        App.INSTANCE.daggerAppComponent.inject(this)

        classicRandomToolbar.title = ""
        setSupportActionBar(classicRandomToolbar)



        nextClassicQuestion()




        classicRandomTeaserQuizOneTv1.setOnClickListener { view -> checkAnswer(view as TextView) }
        classicRandomTeaserQuizOneTv2.setOnClickListener { view -> checkAnswer(view as TextView) }
        classicRandomTeaserQuizOneTv3.setOnClickListener { view -> checkAnswer(view as TextView) }
    }



    private fun checkAnswer(textView: TextView) {
        if (viewModel.questionOne.value!!.isClosedQuestion) {

            if (checkResult(textView)) {
                success()
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

        Log.d("Ans", ans.toString())

        Log.d("Ans tex", textView.text.toString())

        when (ans) {
            1 -> return test.option1 === textView.text.toString()
            2 -> return test.option2 === textView.text.toString()
            3 -> return test.option3 === textView.text.toString()
        }

        return false
    }

    private fun failed(textView: TextView) {


        viewModel.results.observe(this, Observer<Results> { result ->
            if (result != null) {
                var engagement = result.engagement
                engagement += 1
                result.engagement = engagement
                viewModel.updateResults(result)
                viewModel.results.removeObservers(this)

            }
        })

        textView.background = ContextCompat.getDrawable(applicationContext, R.drawable.red_back)

        textView.text = "Wrong !"

        // viewModel.initQuestionOne();

        classicRandomViewKonfetti!!.build()
                .addColors(Color.BLACK, Color.RED)
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(200L)
                .addShapes(Shape.RECT, Shape.CIRCLE)
                .addSizes(Size(5, 5f))
                .setPosition(-50f, classicRandomViewKonfetti!!.width + 50f, -50f, -50f)
                .stream(300, 300L)


        val handler = Handler()
        handler.postDelayed({

            textView.background = ContextCompat.getDrawable(applicationContext, R.drawable.rounded_grey)
            nextClassicQuestion()


        }, 500)


    }

    private fun userChoice(textView: TextView) {


        viewModel.results.observe(this, Observer { result ->
            if (result != null) {
                var res = result.rxpoints
                var engagement = result.engagement
                engagement += 1
                res += 3
                result.rxpoints = res
                viewModel.updateResults(result)
                viewModel.results.removeObservers(this)

            }
        })

        textView.background = ContextCompat.getDrawable(applicationContext, R.drawable.choice)


        classicRandomViewKonfetti!!.build()
                .addColors(Color.BLUE, Color.MAGENTA)
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(200L)
                .addShapes(Shape.RECT, Shape.CIRCLE)
                .addSizes(Size(5, 5f))
                .setPosition(-50f, classicRandomViewKonfetti!!.width + 50f, -50f, -50f)
                .stream(800, 300L)


        val handler = Handler()
        handler.postDelayed({

            textView.background = ContextCompat.getDrawable(applicationContext, R.drawable.rounded_grey)

            nextClassicQuestion()


        }, 500)


    }

    private fun success() {
        classicRandomAnswersLayout.visibility = View.GONE
        classicRandomResultsFeedBack.visibility = View.VISIBLE

        viewModel.results.observe(this, Observer<Results> { result ->
            if (result != null) {
                var engagement = result.engagement
                engagement += 1
                var res = result.rxpoints
                res += 5
                result.engagement = engagement
                result.rxpoints = res
                viewModel.updateResults(result)
                viewModel.results.removeObservers(this)

            }
        })


        classicRandomResultsFeedBack.background = ContextCompat.getDrawable(applicationContext, R.drawable.success_back)

        classicRandomResultsFeedBack.text = "Correct"

        classicRandomViewKonfetti!!.build()
                .addColors(Color.YELLOW, Color.GREEN, Color.BLUE)
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(200L)
                .addShapes(Shape.RECT, Shape.CIRCLE)
                .addSizes(Size(5, 5f))
                .setPosition(classicRandomViewKonfetti.x + classicRandomViewKonfetti.width / 2, classicRandomViewKonfetti.y + classicRandomViewKonfetti.height / 3)
                .burst(500)


        val quiz = viewModel.questionOne.value
        if (quiz != null) {
            quiz.answered = true

            viewModel.updateQuestionInfo(quiz)


        }


        classicRandomResultsFeedBack.setOnClickListener { nextClassicQuestion() }

        /* val handler = Handler()
         handler.postDelayed({
             textView.background = ContextCompat.getDrawable(applicationContext, R.drawable.rounded_grey)
             Log.d("Log","Calling Next quiz")
             nextClassicQuestion()



         }, 500)*/


    }

    private fun nextClassicQuestion() {
        classicRandomAnswersLayout.visibility = View.VISIBLE
        classicRandomResultsFeedBack.visibility = View.GONE

        viewModel.setRandomQuestionLiveData()


        viewModel.randomQuestionLiveData.observe(this, Observer<Question> { quiz ->
            if (quiz != null) {
                viewModel.initQuestionOne(quiz)
                setUpQuestion(quiz)
                viewModel.randomQuestionLiveData.removeObservers(this)

            }
        })


    }

    private fun setUpQuestion(question: Question) {


        classicRandomTeaserQuizOneTv.text = question.question
        classicRandomTeaserQuizOneTv1.text = question.option1
        classicRandomTeaserQuizOneTv2.text = question.option2
        classicRandomTeaserQuizOneTv3.text = question.option3
        classicRandomTvTopic.text = TOPIC.getTopic(question.topic)


    }


}
