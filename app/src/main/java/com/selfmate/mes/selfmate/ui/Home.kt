package com.selfmate.mes.selfmate.ui


import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.selfmate.mes.selfmate.R
import com.selfmate.mes.selfmate.di.AppModule
import com.selfmate.mes.selfmate.di.DaggerAppComponent
import com.selfmate.mes.selfmate.di.RoomModule
import com.selfmate.mes.selfmate.models.MoodLog
import com.selfmate.mes.selfmate.models.Results
import com.selfmate.mes.selfmate.si.GAME_TYPE
import com.selfmate.mes.selfmate.si.MOOD
import com.selfmate.mes.selfmate.ui.repo.MateViewModel
import kotlinx.android.synthetic.main.activity_home.*
import java.util.*
import javax.inject.Inject

class Home : AppCompatActivity() {
    @Inject
    lateinit var viewModel: MateViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home)

        DaggerAppComponent.builder()
                .appModule(AppModule(application))
                .roomModule(RoomModule(application))
                .build()
                .inject(this)
        toolbar.title = ""
        setSupportActionBar(toolbar)

        viewModel.setResultsLiveData()

        viewModel.resultsLiveData.observe(this, Observer<Results>
        { results ->

            if (results != null) {
                tvMainRxpointsResults.text = results.rxpoints.toString()
                tvBarRxpointsResults.text = results.rxpoints.toString()
                tvMainEngagementResults.text = results.engagement.toString()
                tvBarEngagementResults.text = results.engagement.toString()
            }
        })





        laugh_emoji.setOnClickListener { onLaughMood() }
        happy_emoji.setOnClickListener { onHappyMood() }
        sad_emoji.setOnClickListener { onSadMood() }
        angry_emoji.setOnClickListener { onAngryMood() }

        imgFeeling.setOnClickListener {
            userFeelingLayout.visibility = View.GONE
            introMoodLayout.visibility = View.VISIBLE
            userResultsMainLayout.visibility = View.VISIBLE
            chooseGameType.visibility = View.GONE

        }



        gameTypeHot.setOnClickListener { startHotGame() }
        gameTypeClassic.setOnClickListener { startClassicGame() }


    }


    private fun startHotGame() {

        viewModel.gameType = GAME_TYPE.HOT
        val intent = Intent(this, QuizActivity::class.java)
        startActivity(intent)

    }

    private fun startClassicGame() {
        viewModel.gameType = GAME_TYPE.CLASSIC
        val intent = Intent(this, QuizActivity::class.java)
        startActivity(intent)

    }

    private fun onLaughMood() {
        val newLaughMood = MoodLog(Date(), MOOD.LAUGH)

        addMoodLog(newLaughMood)

        val uri = "@drawable/laugh_emoji"

        val imageResource = resources.getIdentifier(uri, null, packageName)

        val res = ContextCompat.getDrawable(applicationContext, imageResource)
        imgFeeling!!.setImageDrawable(res)
        moodAndFeelingLayoutSwitch()
        // generateTeaser("LAUGH");
    }

    private fun onHappyMood() {

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
        introMoodLayout.visibility = View.GONE
        userResultsMainLayout.visibility = View.GONE
        introMoodLayoutResults.visibility = View.VISIBLE
        userFeelingLayout.visibility = View.VISIBLE
        chooseGameType.visibility = View.VISIBLE


    }

    private fun addMoodLog(newMood: MoodLog) {


        viewModel.addMoodLog(newMood)
    }


}
