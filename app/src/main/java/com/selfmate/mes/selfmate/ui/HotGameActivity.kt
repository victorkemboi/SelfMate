package com.selfmate.mes.selfmate.ui

import android.arch.lifecycle.Observer
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import com.selfmate.mes.selfmate.App
import com.selfmate.mes.selfmate.R
import com.selfmate.mes.selfmate.models.Question
import com.selfmate.mes.selfmate.si.TOPIC
import com.selfmate.mes.selfmate.ui.repo.HotGameViewModel
import kotlinx.android.synthetic.main.activity_hot_game.*
import nl.dionsegijn.konfetti.models.Shape
import nl.dionsegijn.konfetti.models.Size
import javax.inject.Inject

class HotGameActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: HotGameViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hot_game)


        App.INSTANCE.daggerAppComponent.inject(this)

        hotGameToolbar.title = ""
        setSupportActionBar(hotGameToolbar)


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




        hotGameTeaserQuizOneTv1.setOnClickListener { view -> checkAnswer(view as TextView) }
        hotGameTeaserQuizOneTv2.setOnClickListener { view -> checkAnswer(view as TextView) }
        hotGameTeaserQuizOneTv3.setOnClickListener { view -> checkAnswer(view as TextView) }
    }

    private fun checkAnswer(textView: TextView) {
        if (viewModel.questionOne.value!!.isClosedQuestion) {

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


    private fun failed(textView: TextView) {


        textView.background = ContextCompat.getDrawable(applicationContext, R.drawable.red_back)

        textView.text = "Wrong !"

        // viewModel.initQuestionOne();

        hotGameViewKonfetti!!.build()
                .addColors(Color.BLACK, Color.RED)
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(200L)
                .addShapes(Shape.RECT, Shape.CIRCLE)
                .addSizes(Size(5, 5f))
                .setPosition(-50f, hotGameViewKonfetti!!.width + 50f, -50f, -50f)
                .stream(300, 300L)


        val handler = Handler()
        handler.postDelayed({

            textView.background = ContextCompat.getDrawable(applicationContext, R.drawable.rounded_grey)
            nextHotQuestion()


        }, 500)


    }


    private fun userChoice(textView: TextView) {


        viewModel.results.observe(this, Observer { result ->
            if (result != null) {
                var res = result.rxpoints
                res += 3
                result.rxpoints = res
                viewModel.updateResults(result)
                viewModel.results.removeObservers(this)

            }
        })

        textView.background = ContextCompat.getDrawable(applicationContext, R.drawable.choice)


        hotGameViewKonfetti!!.build()
                .addColors(Color.BLUE, Color.MAGENTA)
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(200L)
                .addShapes(Shape.RECT, Shape.CIRCLE)
                .addSizes(Size(5, 5f))
                .setPosition(-50f, hotGameViewKonfetti!!.width + 50f, -50f, -50f)
                .stream(800, 300L)


        val handler = Handler()
        handler.postDelayed({

            textView.background = ContextCompat.getDrawable(applicationContext, R.drawable.rounded_grey)

            nextHotQuestion()


        }, 500)


    }

    private fun success(textView: TextView) {

        viewModel.results.observe(this, Observer { result ->
            if (result != null) {
                var res = result.rxpoints
                res += 5
                result.rxpoints = res
                viewModel.updateResults(result)
                viewModel.results.removeObservers(this)

            }
        })

        textView.background = ContextCompat.getDrawable(applicationContext, R.drawable.success_back)

        textView.text = "Correct"

        hotGameViewKonfetti!!.build()
                .addColors(Color.YELLOW, Color.GREEN, Color.BLUE)
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(200L)
                .addShapes(Shape.RECT, Shape.CIRCLE)
                .addSizes(Size(5, 5f))
                .setPosition(hotGameViewKonfetti.x + hotGameViewKonfetti.width / 2, hotGameViewKonfetti.y + hotGameViewKonfetti.height / 3)
                .burst(500)


        val handler = Handler()
        handler.postDelayed({
            textView.background = ContextCompat.getDrawable(applicationContext, R.drawable.rounded_grey)

            nextHotQuestion()


        }, 500)


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


    private fun setUpQuestion(question: Question) {


        hotGameTeaserQuizOneTv.text = question.question
        hotGameTeaserQuizOneTv1.text = question.option1
        hotGameTeaserQuizOneTv2.text = question.option2
        hotGameTeaserQuizOneTv3.text = question.option3
        hotGameTvTopic.text = TOPIC.getTopic(question.topic)


    }


}
