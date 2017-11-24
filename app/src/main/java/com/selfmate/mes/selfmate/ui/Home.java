package com.selfmate.mes.selfmate.ui;


import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.selfmate.mes.selfmate.Models.Question;
import com.selfmate.mes.selfmate.Models.Results;
import com.selfmate.mes.selfmate.STATIC_RESOURCES.GAME_TYPE;
import com.selfmate.mes.selfmate.STATIC_RESOURCES.TOPIC;
import com.selfmate.mes.selfmate.di.AppModule;
import com.selfmate.mes.selfmate.Models.MoodLog;
import com.selfmate.mes.selfmate.R;
import com.selfmate.mes.selfmate.di.DaggerAppComponent;
import com.selfmate.mes.selfmate.di.RoomModule;
import com.selfmate.mes.selfmate.STATIC_RESOURCES.MOOD;
import com.selfmate.mes.selfmate.ui.repo.MateViewModel;

import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

import java.util.Date;
import java.util.Iterator;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nl.dionsegijn.konfetti.KonfettiView;

public class Home extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.userProfileLayout)
    LinearLayout userProfileLayout;

    @BindView(R.id.userResultsMainLayout)
    LinearLayout userResultsMainLayout;


    @BindView(R.id.introMoodLayout)
    LinearLayout moodLayout;

    @BindView(R.id.userFeelingLayout)
    LinearLayout feelingLayout;

    @BindView(R.id.exploreTopics)
    HorizontalScrollView exploreTopics;

    @BindView(R.id.laugh_emoji)
    ImageButton laughEmoji;

    @BindView(R.id.happy_emoji)
    ImageButton happy_emoji;

    @BindView(R.id.sad_emoji)
    ImageButton sad_emoji;

    @BindView(R.id.angry_emoji)
    ImageButton angry_emoji;

    @BindView(R.id.imgFeeling)
    ImageView imgViewFeeling;


    @BindView(R.id.teaserLayout)
    LinearLayout teaserLayout;




    @BindView(R.id.teaserQuizOneTv)
    TextView teaserQuizOneTv;


    @BindView(R.id.teaserQuizOneTv1)
    TextView teaserQuizOneTv1;

    @BindView(R.id.teaserQuizOneTv2)
    TextView teaserQuizOneTv2;

    @BindView(R.id.teaserQuizOneTv3)
    TextView teaserQuizOneTv3;

    @BindView(R.id.gameTypeClassic)
    TextView gameTypeClassic;

    @BindView(R.id.gameType30Sec)
    TextView gameType30Sec;

    @BindView(R.id.gameTypeHot)
    TextView gameTypeHot;


    @BindView(R.id.userProfile)
    ImageView userProfile;

    @BindView(R.id.introMoodLayoutResults)
    LinearLayout introMoodLayoutResults;

    @BindView(R.id.chooseGameType)
    LinearLayout chooseGameType;


    @BindView(R.id.tvMainRxpointsResults)
    TextView tvMainRxpointsResults;

    @BindView(R.id.tvMainEngagementResults)
    TextView tvMainEngagementResults;

    @BindView(R.id.tvBarEngagementResults)
    TextView tvBarEngagementResults;

    @BindView(R.id.tvBarRxpointsResults)
    TextView tvBarRxpointsResults;

    @BindView(R.id.viewKonfetti)
    KonfettiView viewKonfetti;


    @BindView(R.id.nextBtn)
    Button nextBtn;

    @BindView(R.id.tvTopic)
    TextView tvTopic;


    @Inject
    public MateViewModel viewModel;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        DaggerAppComponent.builder()
                .appModule(new AppModule(getApplication()))
                .roomModule(new RoomModule(getApplication()))
                .build()
                .inject(this);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        viewModel.setResultsLiveData();

        viewModel.getResultsLiveData().observe(this, results -> {

            if (results != null) {
                tvMainRxpointsResults.setText(String.valueOf(results.getRxpoints()));
                tvBarRxpointsResults.setText(String.valueOf(results.getRxpoints()));
                tvMainEngagementResults.setText(String.valueOf(results.getEngagement()) );
                tvBarEngagementResults.setText(String.valueOf(results.getEngagement()));
            }
        });





        laughEmoji.setOnClickListener(view -> onLaughMood());
        happy_emoji.setOnClickListener(view -> onHappyMood());
        sad_emoji.setOnClickListener(view -> onSadMood());
        angry_emoji.setOnClickListener(view -> onAngryMood());

        imgViewFeeling.setOnClickListener(view -> {
            feelingLayout.setVisibility(View.GONE);
            moodLayout.setVisibility(View.VISIBLE);
            teaserLayout.setVisibility(View.GONE);
            userResultsMainLayout.setVisibility(View.VISIBLE);
            chooseGameType.setVisibility(View.GONE);
           // exploreTopics.setVisibility(View.GONE);

        });



    }



    @OnClick({R.id.gameTypeHot})
    void startHotGame(){

        viewModel.populateHotQuizList();

        viewModel.gameType = GAME_TYPE.HOT;
        viewModel.listIndexCounter = 0;
        viewModel.currentListSize = 9;
        viewModel.getHotQuizList().observe(this,list->{
            if (list != null) {
                viewModel.initQuestionOne(list.get(viewModel.listIndexCounter));
                setUpQuestion(list.get(viewModel.listIndexCounter));

            }
        });



        OpenGame();



    }

    @OnClick({R.id.gameTypeClassic})
    void startClassicGame(){
        viewModel.gameType = GAME_TYPE.CLASSIC;
        nextClassicQuestion();
        OpenGame();

    }


    void OpenGame(){
        chooseGameType.setVisibility(View.GONE);
        teaserLayout.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.teaserQuizOneTv1,R.id.teaserQuizOneTv2,R.id.teaserQuizOneTv3})
    void checkAnswer(TextView textView){

        if(issaQuiz()){

            if (checkResult(textView)){
                success(textView);
            }else {
                failed(textView);
            }
        }else {
            userChoice(textView);
         }


    }

    @OnClick(R.id.nextBtn)
    void nextQuiz() {

       // viewModel.initQuestionOne();
       // setUpQuestionOne();

    }

    boolean checkResult(TextView textView){

        Question test = viewModel.getQuestionOne();

    if(test!=null){
        int ans = test.getAnswer();

        switch (ans){
            case 1 :
                return test.getOption1() == textView.getText();
            case 2 :
                return test.getOption2() == textView.getText();
            case 3 :
                return test.getOption3() == textView.getText();
        }

        }
        return false;
    }

    boolean issaQuiz() {

        Question test = viewModel.getQuestionOne();

        return test != null && test.isType_question();

    }

    void failed(TextView textView){


        textView.setBackground(getResources().getDrawable(R.drawable.red_back));

        textView.setText("Wrong !");

       // viewModel.initQuestionOne();

        viewKonfetti.build()
                .addColors(Color.BLACK,Color.RED)
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(200L)
                .addShapes(Shape.RECT, Shape.CIRCLE)
                .addSizes(new Size(5, 5f))
                .setPosition(-50f, viewKonfetti.getWidth() + 50f, -50f, -50f)
                .stream(300, 300L);



        final Handler handler = new Handler();
        handler.postDelayed(() -> {

            textView.setBackground(getResources().getDrawable(R.drawable.rounded_grey));
            chooseNextQuiz();


        }, 500);


    }


    void userChoice(TextView textView){


                 Results results = viewModel.getResultsLiveData().getValue();

                if (results != null) {

                int res = results.getRxpoints();
                res = res + 3;
                results.setRxpoints(res);
                viewModel.updateResults(results);

                   }
        textView.setBackground(getResources().getDrawable(R.drawable.choice));


        viewKonfetti.build()
                .addColors(Color.BLUE,Color.MAGENTA)
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(200L)
                .addShapes(Shape.RECT, Shape.CIRCLE)
                .addSizes(new Size(5, 5f))
                .setPosition(-50f, viewKonfetti.getWidth() + 50f, -50f, -50f)
                .stream(800, 300L);



        final Handler handler = new Handler();
        handler.postDelayed(() -> {

            textView.setBackground(getResources().getDrawable(R.drawable.rounded_grey));

            chooseNextQuiz();


        }, 500);


    }

    void success(TextView textView){

        textView.setBackground(getResources().getDrawable(R.drawable.success_back));

        textView.setText("Correct");

        viewKonfetti.build()
                .addColors(Color.YELLOW, Color.GREEN, Color.BLUE)
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(200L)
                .addShapes(Shape.RECT, Shape.CIRCLE)
                .addSizes(new Size(5, 5f))
                .setPosition(viewKonfetti.getX() + viewKonfetti.getWidth() / 2, viewKonfetti.getY() + viewKonfetti.getHeight() / 3)
                .burst(500);




        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            textView.setBackground(getResources().getDrawable(R.drawable.rounded_grey));

            chooseNextQuiz();


        }, 500);




    }

    void chooseNextQuiz(){


        switch (viewModel.gameType) {

            case 1:
                nextHotQuestion();
            case 2:
                nextClassicQuestion();



        }
    }

    void nextHotQuestion(){

        viewModel.listIndexCounter++;
        viewModel.currentListSize = 9;
        if(viewModel.listIndexCounter <= viewModel.currentListSize) {
            viewModel.getHotQuizList().observe(this, list -> {
                if (list != null) {
                    viewModel.initQuestionOne(list.get(viewModel.listIndexCounter));
                    setUpQuestion(list.get(viewModel.listIndexCounter));

                }
            });
        }else {

        }
    }

    void nextClassicQuestion(){


        viewModel.setRandomQuestionLiveData();

        viewModel.getRandomQuestionLiveData().observe(this, question -> {

            if (question != null) {
                viewModel.initQuestionOne(question);
                setUpQuestion(question);
            }
        });



    }

    void onLaughMood() {
        MoodLog newLaughMood = new MoodLog();
        newLaughMood.setMood(MOOD.LAUGH);
        newLaughMood.setTime(new Date());

        String uri = "@drawable/laugh_emoji";

        int imageResource = getResources().getIdentifier(uri, null, getPackageName());

        Drawable res = getResources().getDrawable(imageResource);
        imgViewFeeling.setImageDrawable(res);
        moodAndFeelingLayoutSwitch();
       // generateTeaser("LAUGH");
    }

    void onHappyMood() {

        MoodLog newHappyMood = new MoodLog();
        newHappyMood.setMood(MOOD.HAPPY);
        newHappyMood.setTime(new Date());



        String uri = "@drawable/happy_emoji";  // where myresource (without the extension) is the file

        int imageResource = getResources().getIdentifier(uri, null, getPackageName());

        Drawable res = getResources().getDrawable(imageResource);
        imgViewFeeling.setImageDrawable(res);
        moodAndFeelingLayoutSwitch();
        //generateTeaser("HAPPY");

    }

    void onSadMood() {

        MoodLog newSadMood = new MoodLog();
        newSadMood.setMood(MOOD.SAD);
        newSadMood.setTime(new Date());



        String uri = "@drawable/sad_emoji";  // where myresource (without the extension) is the file

        int imageResource = getResources().getIdentifier(uri, null, getPackageName());

        Drawable res = getResources().getDrawable(imageResource);
        imgViewFeeling.setImageDrawable(res);
        moodAndFeelingLayoutSwitch();

        //generateTeaser("SAD");

    }

    void onAngryMood() {

        MoodLog newAngryMood = new MoodLog();
        newAngryMood.setMood(MOOD.ANGRY);
        newAngryMood.setTime(new Date());

        addMoodLog(newAngryMood);

        String uri = "@drawable/angry_emoji";  // where myresource (without the extension) is the file

        int imageResource = getResources().getIdentifier(uri, null, getPackageName());

        Drawable res = getResources().getDrawable(imageResource);
        imgViewFeeling.setImageDrawable(res);
        moodAndFeelingLayoutSwitch();

       // generateTeaser("ANGRY");

    }


    void moodAndFeelingLayoutSwitch() {
        moodLayout.setVisibility(View.GONE);
        userResultsMainLayout.setVisibility(View.GONE);
        introMoodLayoutResults.setVisibility(View.VISIBLE);
        feelingLayout.setVisibility(View.VISIBLE);
        chooseGameType.setVisibility(View.VISIBLE);
        //exploreTopics.setVisibility(View.VISIBLE);


    }


    void setUpQuestion(Question question) {



                            teaserQuizOneTv.setText(question.getQuestion());
                            teaserQuizOneTv1.setText(question.getOption1());
                            teaserQuizOneTv2.setText(question.getOption2());
                            teaserQuizOneTv3.setText(question.getOption3());
                            tvTopic.setText(TOPIC.getTopic( question.getTopic() ));
                            //loadQuizImage(question.getImage_name());



    }

    void addMoodLog(MoodLog newMood) {


        viewModel.addMoodLog(newMood);
    }





}
