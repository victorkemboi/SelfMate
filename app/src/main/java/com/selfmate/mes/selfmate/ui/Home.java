package com.selfmate.mes.selfmate.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.selfmate.mes.selfmate.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Home extends AppCompatActivity {
    Toolbar toolbar;

    @BindView(R.id.userResultsMainLayout)
    LinearLayout userResultsMainLayout;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
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

    }

    void  onLaughMood(){
        moodLayout.setVisibility(View.GONE);
        userResultsMainLayout.setVisibility(View.GONE);

        String uri = "@drawable/laugh_emoji";  // where myresource (without the extension) is the file

        int imageResource = getResources().getIdentifier(uri, null, getPackageName());

        Drawable res = getResources().getDrawable(imageResource);
        imgViewFeeling.setImageDrawable(res);
        imgViewFeeling.setClickable(true);
        imgViewFeeling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                feelingLayout.setVisibility(View.GONE);
                moodLayout.setVisibility(View.VISIBLE);
                teaserLayout.setVisibility(View.GONE);
                userResultsMainLayout.setVisibility(View.VISIBLE);
            }
        });

        feelingLayout.setVisibility(View.VISIBLE);
        generateTeaser("LAUGH");
    }
    void onHappyMood(){

        moodLayout.setVisibility(View.GONE);
        userResultsMainLayout.setVisibility(View.GONE);
        String uri = "@drawable/happy_emoji";  // where myresource (without the extension) is the file

        int imageResource = getResources().getIdentifier(uri, null, getPackageName());

        Drawable res = getResources().getDrawable(imageResource);
        imgViewFeeling.setImageDrawable(res);
        imgViewFeeling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                feelingLayout.setVisibility(View.GONE);
                moodLayout.setVisibility(View.VISIBLE);
                teaserLayout.setVisibility(View.GONE);
                userResultsMainLayout.setVisibility(View.VISIBLE);
            }
        });

        feelingLayout.setVisibility(View.VISIBLE);
        generateTeaser("HAPPY");

    }
    void onSadMood(){

        moodLayout.setVisibility(View.GONE);
        userResultsMainLayout.setVisibility(View.GONE);
        String uri = "@drawable/sad_emoji";  // where myresource (without the extension) is the file

        int imageResource = getResources().getIdentifier(uri, null, getPackageName());

        Drawable res = getResources().getDrawable(imageResource);
        imgViewFeeling.setImageDrawable(res);
        imgViewFeeling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                feelingLayout.setVisibility(View.GONE);
                moodLayout.setVisibility(View.VISIBLE);
                teaserLayout.setVisibility(View.GONE);
                userResultsMainLayout.setVisibility(View.VISIBLE);
            }
        });

        feelingLayout.setVisibility(View.VISIBLE);
        generateTeaser("SAD");

    }
    void onAngryMood(){

        moodLayout.setVisibility(View.GONE);
        userResultsMainLayout.setVisibility(View.GONE);
        String uri = "@drawable/angry_emoji";  // where myresource (without the extension) is the file

        int imageResource = getResources().getIdentifier(uri, null, getPackageName());

        Drawable res = getResources().getDrawable(imageResource);
        imgViewFeeling.setImageDrawable(res);
        imgViewFeeling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                feelingLayout.setVisibility(View.GONE);
                moodLayout.setVisibility(View.VISIBLE);
                teaserLayout.setVisibility(View.GONE);
                userResultsMainLayout.setVisibility(View.VISIBLE);

            }
        });

        feelingLayout.setVisibility(View.VISIBLE);
        generateTeaser("ANGRY");

    }

    void generateTeaser(String mood){
        if (mood.equalsIgnoreCase("LAUGH")){
            teaserQuizOneTv.setText("You like Comedy?");
            teaserQuizOneTv1.setText("Yes");
            teaserQuizOneTv2.setText("No");
            teaserQuizOneTv3.setText("Not Sure");
        }
        teaserLayout.setVisibility(View.VISIBLE);
    }



}
