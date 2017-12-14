package com.selfmate.mes.selfmate.ui


import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.animation.AnimationUtils
import com.selfmate.mes.selfmate.App
import com.selfmate.mes.selfmate.R
import com.selfmate.mes.selfmate.models.MoodLog
import com.selfmate.mes.selfmate.models.Results
import com.selfmate.mes.selfmate.si.MOOD
import com.selfmate.mes.selfmate.ui.repo.HomeViewModel
import kotlinx.android.synthetic.main.activity_home.*
import java.util.*
import javax.inject.Inject


class Home : AppCompatActivity() {
    @Inject
    lateinit var viewModel: HomeViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home)

        App.INSTANCE.daggerAppComponent.inject(this)
        toolbar.title = ""
        setSupportActionBar(toolbar)

        viewModel.setResultsLiveData()

        viewModel.resultsLiveData.observe(this, Observer<Results>
        { results ->

            if (results != null) {
                tvMainRxpointsResults.text = results.rxpoints.toString()
            }
        })

        laugh_emoji.setOnClickListener { onLaughMood() }
        happy_emoji.setOnClickListener { onHappyMood() }
        sad_emoji.setOnClickListener { onSadMood() }
        angry_emoji.setOnClickListener { onAngryMood() }

        imgFeeling.setOnClickListener {
            chooseGameType.visibility = View.GONE
            introMoodLayout.visibility = View.VISIBLE
            userResultsMainLayout.visibility = View.VISIBLE


        }

        gameTypeClassic.setOnClickListener {

            // gameTypeClassic.background = ContextCompat.getDrawable(applicationContext, R.drawable.tagbg)
            val rightSwipe = AnimationUtils.loadAnimation(this@Home,
                    R.anim.left_slide
            )
            gameTypeClassic.startAnimation(rightSwipe)
            val handler = Handler()
            handler.postDelayed({

                startClassicGame()

            }, 300)
        }

        gameTypeBack.setOnClickListener {
            changeToolBarTitle()
            chooseGameType.visibility = View.GONE

            val handler = Handler()
            handler.postDelayed({

                introMoodLayout.visibility = View.VISIBLE

                val handlerii = Handler()
                handlerii.postDelayed({

                    userResultsMainLayout.visibility = View.VISIBLE


                }, 500)


            }, 200)


        }

        gameTypeAsk.setOnClickListener {
            val rightSwipe = AnimationUtils.loadAnimation(this@Home,
                    R.anim.left_slide)
            gameTypeAsk.startAnimation(rightSwipe)
            val handler = Handler()
            handler.postDelayed({

                startCreateQuiz()

            }, 200)
        }

        gameTypeButtonSwitch()


    }

    override fun onResume() {
        super.onResume()

        gameTypeButtonSwitch()

        viewModel.setResultsLiveData()

        viewModel.resultsLiveData.observe(this, Observer<Results>
        { results ->

            if (results != null) {
                tvMainRxpointsResults.text = results.rxpoints.toString()
                //tvBarRxpointsResults.text = results.rxpoints.toString()
                //tvMainEngagementResults.text = results.engagement.toString()
                //tvBarEngagementResults.text = results.engagement.toString()
            }
        })

    }

    override fun onBackPressed() {

        if (chooseGameType.visibility == View.VISIBLE) {

            changeToolBarTitle()
            chooseGameType.visibility = View.GONE
            val handler = Handler()
            handler.postDelayed({

                introMoodLayout.visibility = View.VISIBLE

                val handlerii = Handler()
                handlerii.postDelayed({

                    userResultsMainLayout.visibility = View.VISIBLE


                }, 500)


            }, 200)


        } else {


            super.onBackPressed()

        }


    }

    private fun startClassicGame() {

        val intent = Intent(this, ClassicRandomActivity::class.java)
        startActivity(intent)

    }

    private fun startCreateQuiz() {

        val intent = Intent(this, AskQuizActivity::class.java)
        intent.putExtra("mood", viewModel.mood)
        startActivity(intent)

    }

    private fun onLaughMood() {
        changeToolBarTitle()
        viewModel.mood = MOOD.LAUGH
        val newLaughMood = MoodLog(Date(), MOOD.LAUGH)

        addMoodLog(newLaughMood)

        val uri = "@drawable/laugh_emoji"

        val imageResource = resources.getIdentifier(uri, null, packageName)

        val res = ContextCompat.getDrawable(applicationContext, imageResource)
        imgFeeling.setImageDrawable(res)
        moodAndFeelingLayoutSwitch()
        // generateTeaser("LAUGH");
    }

    private fun onHappyMood() {

        changeToolBarTitle()
        viewModel.mood = MOOD.HAPPY
        val newHappyMood = MoodLog(Date(), MOOD.HAPPY)

        addMoodLog(newHappyMood)


        val uri = "@drawable/happy_emoji"  // where myresource (without the extension) is the file

        val imageResource = resources.getIdentifier(uri, null, packageName)

        val res = ContextCompat.getDrawable(applicationContext, imageResource)
        imgFeeling!!.setImageDrawable(res)
        moodAndFeelingLayoutSwitch()
        //generateTeaser("HAPPY");

    }

    private fun onSadMood() {
        changeToolBarTitle()
        viewModel.mood = MOOD.SAD
        val newSadMood = MoodLog(Date(), MOOD.SAD)

        addMoodLog(newSadMood)


        val uri = "@drawable/sad_emoji"  // where myresource (without the extension) is the file

        val imageResource = resources.getIdentifier(uri, null, packageName)

        val res = ContextCompat.getDrawable(applicationContext, imageResource)
        imgFeeling!!.setImageDrawable(res)
        moodAndFeelingLayoutSwitch()

        //generateTeaser("SAD");

    }

    private fun onAngryMood() {
        changeToolBarTitle()
        viewModel.mood = MOOD.ANGRY
        val newAngryMood = MoodLog(Date(), MOOD.ANGRY)

        addMoodLog(newAngryMood)

        val uri = "@drawable/angry_emoji"  // where myresource (without the extension) is the file

        val imageResource = resources.getIdentifier(uri, null, packageName)

        val res = ContextCompat.getDrawable(applicationContext, imageResource)
        imgFeeling!!.setImageDrawable(res)
        moodAndFeelingLayoutSwitch()

        // generateTeaser("ANGRY");

    }

    private fun moodAndFeelingLayoutSwitch() {

        val handler = Handler()
        handler.postDelayed({

            userResultsMainLayout.visibility = View.GONE

            val handlerii = Handler()
            handlerii.postDelayed({

                introMoodLayout.visibility = View.GONE

                val handleriii = Handler()
                handleriii.postDelayed({

                    chooseGameType.visibility = View.VISIBLE


                }, 100)

            }, 500)


        }, 200)


    }

    private fun gameTypeButtonSwitch() {

        gameTypeClassic.background = ContextCompat.getDrawable(applicationContext, R.drawable.play_back)
        gameTypeAsk.background = ContextCompat.getDrawable(applicationContext, R.drawable.menu_ask)

    }

    private fun addMoodLog(newMood: MoodLog) {

        viewModel.addMoodLog(newMood)
    }

    private fun changeToolBarTitle() {
        if (toolbar_title.text.toString() == "HOME") {
            toolbar_title.text = "MENU"
            toolbar_ic.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.ic_apps))
        } else {
            toolbar_title.text = "HOME"
            toolbar_ic.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.ic_home))
        }
    }
    //#FF037131
    //#094d50

}
