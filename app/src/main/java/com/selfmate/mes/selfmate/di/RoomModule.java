package com.selfmate.mes.selfmate.di;

import android.app.Application;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.util.Log;

import com.opencsv.CSVReader;
import com.selfmate.mes.selfmate.Models.MoodLogDao;
import com.selfmate.mes.selfmate.Models.Question;
import com.selfmate.mes.selfmate.Models.QuestionDao;
import com.selfmate.mes.selfmate.Models.ResultsDao;
import com.selfmate.mes.selfmate.Models.SelfMateDatabase;
import com.selfmate.mes.selfmate.ui.repo.MateViewModel;
import com.selfmate.mes.selfmate.ui.repo.MoodLogDataSource;
import com.selfmate.mes.selfmate.ui.repo.MoodLogRepository;
import com.selfmate.mes.selfmate.ui.repo.QuestionDataSource;
import com.selfmate.mes.selfmate.ui.repo.QuestionRepository;
import com.selfmate.mes.selfmate.ui.repo.ResultsDataSource;
import com.selfmate.mes.selfmate.ui.repo.ResultsRepository;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Executor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by vicki_mes on 11/18/2017.
 */

@Module
public class RoomModule {


    private SelfMateDatabase selfMateDatabase;
    private static final String DATABASE_NAME = "SelfMate";

    public RoomModule(Application mApplication) {
        selfMateDatabase  = Room.databaseBuilder(mApplication, SelfMateDatabase.class, DATABASE_NAME)
                .addCallback(new RoomDatabase.Callback() {

                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);

                        ContentValues results = new ContentValues();
                        results.put("uid",1);
                        Long date = new Date().getTime();
                        results.put("timeCreated",date);
                        results.put("timeUpdated",date);
                        results.put("engagement",0);
                        results.put("rxpoints",0);

                        Log.d("results", results.toString());

                        db.insert("results",SQLiteDatabase.CONFLICT_REPLACE,results);

                        //Log.d("try read csv.", "Setting up quizes");
                       // Log.d("try read csv.", "setup succes");
                        try {
                            InputStream inputStream = mApplication.getAssets().open("quiz_db.csv");
                            CSVReader reader = new CSVReader(new InputStreamReader(inputStream));
                            String[] nextLine;

                            try {
                                while ((nextLine = reader.readNext()) != null) {
                                    // nextLine[] is an array of values from the line
                                    String question = nextLine[0];
                                    String topic = nextLine[1];
                                    String option_1 = nextLine[2];
                                    String option_2 = nextLine[3];
                                    String option_3 = nextLine[4];
                                   // String image_name = nextLine[];
                                    boolean type_question = Boolean.parseBoolean(nextLine[5]) ;
                                    int answer = Integer.parseInt(nextLine[6]) ;
                                    int moodAsked = 0;
                                    String subTopic = "general";
                                    int mood_answered = 0 ;
                                    boolean failed = false ;
                                    int views = 0 ;
                                    boolean answered = false ;


                                    ContentValues contentValues = new ContentValues();
                                    contentValues.put("question", question);
                                    contentValues.put("topic", topic);
                                    contentValues.put("sub_topic", subTopic);
                                    contentValues.put("mood_asked", moodAsked);
                                    contentValues.put("mood_answered", mood_answered);
                                    contentValues.put("option_1", option_1);
                                    contentValues.put("option_2", option_2);
                                    contentValues.put("option_3", option_3);
                                    contentValues.put("type_question", type_question);
                                   // contentValues.put("image_name", image_name);
                                    contentValues.put("answer", answer);
                                    contentValues.put("failed", failed);
                                    contentValues.put("views", views);
                                    contentValues.put("answered", answered);

                                    Log.d("results", contentValues.toString());

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
    }

    @Singleton
    @Provides
    SelfMateDatabase providesRoomDatabase() {
        return selfMateDatabase;
    }

    @Singleton
    @Provides
    QuestionDao providesQuestionDao(SelfMateDatabase selfMateDatabasem) {
        return selfMateDatabasem.questionDao();
    }

    @Singleton
    @Provides
    ResultsDao providesResultsDao(SelfMateDatabase selfMateDatabasem) {
        return selfMateDatabasem.resultsDao();
    }


    @Singleton
    @Provides
    MoodLogDao providesMoodLodDao(SelfMateDatabase selfMateDatabasem) {
        return selfMateDatabasem.moodLogDao();
    }

    @Singleton
    @Provides
    QuestionRepository questionRepository(QuestionDao questionDao) {
        return new QuestionDataSource(questionDao);
    }

    @Singleton
    @Provides
    MoodLogRepository moodLogRepository(MoodLogDao moodLogDao) {
        return new MoodLogDataSource(moodLogDao);
    }



    @Singleton
    @Provides
    ResultsRepository resultsRepository(ResultsDao resultsDao) {
        return new ResultsDataSource(resultsDao);
    }

    @Singleton
    @Provides
    MateViewModel providesViewModel(QuestionRepository questionRepo, MoodLogRepository moodLogRepository,ResultsRepository resultsRepository, Executor executor) {
        return new MateViewModel(questionRepo,moodLogRepository,resultsRepository,executor);
    }

    @Singleton
    @Provides
    Executor providesExecutor() {
        return new Executor() {
            @Override
            public void execute(@NonNull Runnable runnable) {

                new Thread(runnable).start();
            }
        };
    }




}
