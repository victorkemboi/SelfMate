package com.selfmate.mes.selfmate.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import com.selfmate.mes.selfmate.App
import com.selfmate.mes.selfmate.R
import com.selfmate.mes.selfmate.si.TOPIC
import com.selfmate.mes.selfmate.ui.repo.AskQuizViewModel
import kotlinx.android.synthetic.main.activity_ask_quiz.*
import javax.inject.Inject


class AskQuizActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModel: AskQuizViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ask_quiz)
        App.INSTANCE.daggerAppComponent.inject(this)
        viewModel.moodAsked = intent.getIntExtra("mood", 0)
        val topics: MutableList<String> = mutableListOf(
                "Select Topic",
                TOPIC.getTopic(TOPIC.BRANDS),
                TOPIC.getTopic(TOPIC.FINANCE),
                TOPIC.getTopic(TOPIC.FOOD),
                TOPIC.getTopic(TOPIC.MOVIE),
                TOPIC.getTopic(TOPIC.SCIENCE),
                TOPIC.getTopic(TOPIC.SPORT),
                TOPIC.getTopic(TOPIC.TECHNOLOGY)
        )
        spTopic.setItems(topics)
        spTopic.setDropdownHeight(500)
        spTopic.setOnItemSelectedListener { view, position, id, item ->
            //Snackbar.make(view, "Clicked $item $id $position", Snackbar.LENGTH_LONG).show()
            viewModel.topic = position

        }

        btnPostQuiz.setOnClickListener {
            if (checkAllFieldsFilled()) {
                setQuizData()
                viewModel.postQuestion()
            }


        }
    }

    private fun setQuizData() {
        viewModel.question = edQuiz.text.toString()
        // Log.d("Quiz",viewModel.question)
        viewModel.option1 = edAnswerOne.text.toString()
        viewModel.option1 = edAnswerOne.text.toString()
        viewModel.option2 = edAnswerTwo.text.toString()
        viewModel.option3 = edAnswerThree.text.toString()

    }

    fun onQuestionTypeRadioButtonClicked(view: View) {

        val checked = (view as RadioButton).isChecked


        when (view.getId()) {
            R.id.closedQuizRb -> {
                if (checked) {
                    viewModel.isClosedQuestion = true
                    answerThreeTextInputLayout.hint = "Answer"
                }

            }
            R.id.openQuizRb ->
                if (checked) {
                    viewModel.isClosedQuestion = false
                    answerThreeTextInputLayout.hint = "Choice C"
                }
        }
    }

    private fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(editable: Editable?) {
                afterTextChanged.invoke(editable.toString())
            }
        })
    }


    private fun checkAllFieldsFilled(): Boolean {
        if (edQuiz.text.toString() == "") {
            quizTextInputLayout.error = "Question cannot be empty!"
            edQuiz.afterTextChanged { quizTextInputLayout.error = null }
            return false
        }

        if (edAnswerOne.text.toString() == "") {
            answerOneTextInputLayout.error = "Provide Choice A!"
            edAnswerOne.afterTextChanged { answerOneTextInputLayout.error = null }
            return false
        }

        if (edAnswerTwo.text.toString() == "") {
            answerTwoTextInputLayout.error = "Provide Choice B!"
            edAnswerTwo.afterTextChanged { answerTwoTextInputLayout.error = null }
            return false
        }
        if (edAnswerThree.text.toString() == "") {
            if (viewModel.isClosedQuestion) {
                answerThreeTextInputLayout.error = "Provide the Answer!"
            } else {
                answerThreeTextInputLayout.error = "Provide Choice C!"
            }

            edAnswerThree.afterTextChanged { edAnswerThree.error = null }
            return false
        }


        return true
    }
}
