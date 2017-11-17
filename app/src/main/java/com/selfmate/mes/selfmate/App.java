package com.selfmate.mes.selfmate;

import android.app.Application;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.util.Log;

import com.opencsv.CSVReader;
import com.selfmate.mes.selfmate.Models.Question;
import com.selfmate.mes.selfmate.Models.QuestionDao;
import com.selfmate.mes.selfmate.Models.SelfMateDatabase;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by vicki_mes on 11/15/2017.
 */

public class App extends Application {

    private static final String DATABASE_NAME = "SelfMate";
    public static App INSTANCE;
    private SelfMateDatabase database;

    public static App get() {
        return INSTANCE;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("starting app", "start");
        // create database
        database = Room.databaseBuilder(getApplicationContext(), SelfMateDatabase.class, DATABASE_NAME)
                .addCallback(new RoomDatabase.Callback() {

                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Log.d("try read csv.", "Setting up quizes");
                        Log.d("try read csv.", "setup succes");
                        try {
                            InputStream inputStream = getAssets().open("quiz_db.csv");
                            CSVReader reader = new CSVReader(new InputStreamReader(inputStream));
                            String[] nextLine;
                            ArrayList<Question> initialQuestionList = new ArrayList<Question>();

                            final QuestionDao questionDao;
                            try {
                                while ((nextLine = reader.readNext()) != null) {
                                    // nextLine[] is an array of values from the line
                                    String question = nextLine[0];
                                    String topic = nextLine[1];
                                    String subTopic = nextLine[2];
                                    String moodAsked = nextLine[3];
                                    String option_1 = nextLine[4];
                                    String option_2 = nextLine[5];
                                    String option_3 = nextLine[6];
                                    String type = nextLine[7];

                                    Question initialQuestion = new Question(question, topic, subTopic, moodAsked, option_1, option_2, option_3, type);

                                    ContentValues contentValues = new ContentValues();
                                    contentValues.put("question", question);
                                    contentValues.put("topic", topic);
                                    contentValues.put("sub_topic", subTopic);
                                    contentValues.put("mood_asked", moodAsked);
                                    contentValues.put("option_1", option_1);
                                    contentValues.put("option_2", option_2);
                                    contentValues.put("option_3", option_3);
                                    contentValues.put("type", type);

                                    db.insert("question", SQLiteDatabase.CONFLICT_REPLACE, contentValues);

                                }


                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onOpen(@NonNull SupportSQLiteDatabase db) {
                        super.onOpen(db);
                    }
                })
                .build();

        INSTANCE = this;
    }

    public SelfMateDatabase getDB() {
        return database;
    }


}