package com.selfmate.mes.selfmate.Models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by vicki_mes on 11/16/2017.
 */

@Entity(tableName = "question")
public class Question {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "question")
    private String question;

    @ColumnInfo(name = "topic")
    private int topic;

    @ColumnInfo(name = "sub_topic")
    private String subTopic;

    @ColumnInfo(name = "mood_asked")
    private int moodAsked;

    @ColumnInfo(name = "mood_answered")
    private int moodAnswered;

    @ColumnInfo(name = "option_1")
    private String option1;

    @ColumnInfo(name = "option_2")
    private String option2;

    @ColumnInfo(name = "option_3")
    private String option3;

    @ColumnInfo(name = "type_question")
    private boolean type_question;

    @Ignore
    @ColumnInfo(name = "image_name")
    private String image_name;

    @ColumnInfo(name = "answer")
    private int answer;


    @ColumnInfo(name = "failed")
    private boolean failed;


    @ColumnInfo(name = "views")
    private int views;


    @ColumnInfo(name = "answered")
    private boolean answered;


    public Question(String question, int topic, String subTopic, int moodAsked, int moodAnswered, String option1, String option2, String option3, boolean type_question, int answer, boolean failed, int views, boolean answered) {
        this.question = question;
        this.topic = topic;
        this.subTopic = subTopic;
        this.moodAsked = moodAsked;
        this.moodAnswered = moodAnswered;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.type_question = type_question;
        this.answer = answer;
        this.failed = failed;
        this.views = views;
        this.answered = answered;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getTopic() {
        return topic;
    }

    public void setTopic(int topic) {
        this.topic = topic;
    }

    public String getSubTopic() {
        return subTopic;
    }

    public void setSubTopic(String subTopic) {
        this.subTopic = subTopic;
    }

    public int getMoodAsked() {
        return moodAsked;
    }

    public void setMoodAsked(int moodAsked) {
        this.moodAsked = moodAsked;
    }

    public int getMoodAnswered() {
        return moodAnswered;
    }

    public void setMoodAnswered(int moodAnswered) {
        this.moodAnswered = moodAnswered;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public boolean isType_question() {
        return type_question;
    }

    public void setType_question(boolean type_question) {
        this.type_question = type_question;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public boolean isFailed() {
        return failed;
    }

    public void setFailed(boolean failed) {
        this.failed = failed;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public boolean getAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }
}
