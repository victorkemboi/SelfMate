package com.selfmate.mes.selfmate.ui;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.selfmate.mes.selfmate.App;
import com.selfmate.mes.selfmate.Models.MoodLog;
import com.selfmate.mes.selfmate.Models.MoodLogDao;
import com.selfmate.mes.selfmate.Models.Question;
import com.selfmate.mes.selfmate.Models.QuestionDao;
import com.selfmate.mes.selfmate.R;

import java.lang.ref.WeakReference;
import java.util.Date;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.functions.Consumer;

public class Home extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.userResultsMainLayout)
    LinearLayout userResultsMainLayout;

    @BindView(R.id.mainResponseLayout)
    LinearLayout mainResponseLayout;

    @BindView(R.id.introMoodLayout)
    LinearLayout moodLayout;

    @BindView(R.id.userFeelingLayout)
    LinearLayout feelingLayout;

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
    LinearLayout  teaserLayout;

    @BindView(R.id.teaserQuizOneTv)
    TextView  teaserQuizOneTv;

    @BindView(R.id.teaserQuizOneTv1)
    TextView  teaserQuizOneTv1;

    @BindView(R.id.teaserQuizOneTv2)
    TextView  teaserQuizOneTv2;

    @BindView(R.id.teaserQuizOneTv3)
    TextView  teaserQuizOneTv3;

    @BindView(R.id.userProfile)
    ImageView userProfile;

    @BindView(R.id.introMoodLayoutResults)
    LinearLayout introMoodLayoutResults;

    @BindView(R.id.userDetailsTile)
    LinearLayout userDetailsTile;

    Flowable<List<Question>> questionsList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);


        laughEmoji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLaughMood();
            }
        });
        happy_emoji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { onHappyMood(); }
        });
        sad_emoji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { onSadMood(); }
        });
        angry_emoji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { onAngryMood(); }
        });

        userProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userProfile();
            }
        });
        imgViewFeeling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                feelingLayout.setVisibility(View.GONE);
                moodLayout.setVisibility(View.VISIBLE);
                teaserLayout.setVisibility(View.GONE);
                userResultsMainLayout.setVisibility(View.VISIBLE);

            }
        });

        userDetailsTile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //addUserDetails();
            }
        });

    }

    void onLaughMood() {
        MoodLog newLaughMood = new MoodLog();
        newLaughMood.setMood("laugh");
        newLaughMood.setTime(new Date());

        AddMoodToLog newLaughMoodTask = new AddMoodToLog(this);
        newLaughMoodTask.addMoodToLog(newLaughMood);
        newLaughMoodTask.execute();

        String uri = "@drawable/laugh_emoji";

        int imageResource = getResources().getIdentifier(uri, null, getPackageName());

        Drawable res = getResources().getDrawable(imageResource);
        imgViewFeeling.setImageDrawable(res);
        moodAndFeelingLayoutSwitch();
        generateTeaser("LAUGH");
    }
    void onHappyMood(){

        MoodLog newHappyMood = new MoodLog();
        newHappyMood.setMood("happy");
        newHappyMood.setTime(new Date());

        AddMoodToLog newHappyMoodTask = new AddMoodToLog(this);
        newHappyMoodTask.addMoodToLog(newHappyMood);
        newHappyMoodTask.execute();

        String uri = "@drawable/happy_emoji";  // where myresource (without the extension) is the file

        int imageResource = getResources().getIdentifier(uri, null, getPackageName());

        Drawable res = getResources().getDrawable(imageResource);
        imgViewFeeling.setImageDrawable(res);
        moodAndFeelingLayoutSwitch();
        generateTeaser("HAPPY");

    }
    void onSadMood(){

        MoodLog newSadMood = new MoodLog();
        newSadMood.setMood("sad");
        newSadMood.setTime(new Date());

        AddMoodToLog newSadMoodTask = new AddMoodToLog(this);
        newSadMoodTask.addMoodToLog(newSadMood);
        newSadMoodTask.execute();

        String uri = "@drawable/sad_emoji";  // where myresource (without the extension) is the file

        int imageResource = getResources().getIdentifier(uri, null, getPackageName());

        Drawable res = getResources().getDrawable(imageResource);
        imgViewFeeling.setImageDrawable(res);
        moodAndFeelingLayoutSwitch();

        generateTeaser("SAD");

    }
    void onAngryMood(){

        MoodLog newAngryMood = new MoodLog();
        newAngryMood.setMood("angry");
        newAngryMood.setTime(new Date());

        addMoodLog(newAngryMood);

        String uri = "@drawable/angry_emoji";  // where myresource (without the extension) is the file

        int imageResource = getResources().getIdentifier(uri, null, getPackageName());

        Drawable res = getResources().getDrawable(imageResource);
        imgViewFeeling.setImageDrawable(res);
        moodAndFeelingLayoutSwitch();

        generateTeaser("ANGRY");

    }

    void generateTeaser(String mood){
        setUpQuestions();
        teaserLayout.setVisibility(View.VISIBLE);
    }


    void userProfile() {
        if (userResultsMainLayout.getVisibility() == View.VISIBLE) {
            userResultsMainLayout.setVisibility(View.GONE);
            introMoodLayoutResults.setVisibility(View.VISIBLE);
            teaserLayout.setVisibility(View.VISIBLE);

        } else {
            teaserLayout.setVisibility(View.GONE);
            userResultsMainLayout.setVisibility(View.VISIBLE);
            introMoodLayoutResults.setVisibility(View.INVISIBLE);

        }

    }

    void moodAndFeelingLayoutSwitch() {
        moodLayout.setVisibility(View.GONE);
        userResultsMainLayout.setVisibility(View.GONE);
        introMoodLayoutResults.setVisibility(View.VISIBLE);
        feelingLayout.setVisibility(View.VISIBLE);
        mainResponseLayout.removeAllViews();
        mainResponseLayout.setVisibility(View.GONE);


    }

    void addUserDetails() {
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);

        View childLayout = null;
        if (inflater != null) {
            childLayout = inflater.inflate(R.layout.add_user_details, (ConstraintLayout) findViewById(R.id.add_user_details));
        }

        mainResponseLayout.addView(childLayout);
        userResultsMainLayout.setVisibility(View.GONE);
    }

    void setUpQuestions() {
        final QuestionDao questionDao = App.get().getDB().questionDao();
        Random rand = new Random();

        int n = rand.nextInt(9) + 1;
        Single<Question> question = questionDao.getOneQuestion(n);
        // questionsList.subscribe(result->);
        question.doOnSuccess(new Consumer<Question>() {

            @Override
            public void accept(Question quiz) throws Exception {
                System.out.println(quiz.getQuestion());
                teaserQuizOneTv.setText(quiz.getQuestion());
                teaserQuizOneTv1.setText(quiz.getOption1());
                teaserQuizOneTv2.setText(quiz.getOption2());
                teaserQuizOneTv3.setText(quiz.getOption3());
            }
        });

    }

    void addMoodLog(MoodLog newMood) {
        final MoodLogDao moodLogDaoi = App.get().getDB().moodLogDao();
        moodLogDaoi.insertMoodToLog(newMood);


    }

    private static class AddMoodToLog extends AsyncTask<Void, Void, List<MoodLog>> {

        final MoodLogDao moodLogDaoi = App.get().getDB().moodLogDao();
        MoodLog newMood;
        private WeakReference<Home> activityReference;

        // only retain a weak reference to the activity
        AddMoodToLog(Home context) {
            activityReference = new WeakReference<>(context);
        }

        @Override
        protected List<MoodLog> doInBackground(Void... params) {

            moodLogDaoi.insertMoodToLog(newMood);
            return moodLogDaoi.getMoogLog();
        }

        @Override
        protected void onPostExecute(List<MoodLog> result) {

            // get a reference to the activity if it is still there
            Home activity = activityReference.get();
            if (activity == null) return;

            if (result.size() > 0) {
                //2: If it already exists then prompt user
                Log.d("Mood", result.toString());
                Toast.makeText(activity, newMood.getMood(), Toast.LENGTH_LONG).show();
                Toast.makeText(activity, ("" + result.size()), Toast.LENGTH_LONG).show();
            } else {
                Log.d("Mood", result.toString());
                Toast.makeText(activity, "Agent does not exist! Hurray :)", Toast.LENGTH_LONG).show();

            }
        }

        void addMoodToLog(final MoodLog newMood) {
            this.newMood = newMood;
        }


    }






}
