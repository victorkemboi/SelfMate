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
    private String topic;

    @ColumnInfo(name = "sub_topic")
    private String subTopic;

    @ColumnInfo(name = "mood_asked")
    private String moodAsked;

    @ColumnInfo(name = "mood_answered")
    private String moodAnswered;

    @ColumnInfo(name = "option_1")
    private String option1;

    @ColumnInfo(name = "option_2")
    private String option2;

    @ColumnInfo(name = "option_3")
    private String option3;

    @ColumnInfo(name = "type")
    private String type;

    @Ignore
    @ColumnInfo(name = "answer")
    private int answer;

    @Ignore
    @ColumnInfo(name = "user_answer")
    private int userAnswer;

    @Ignore
    @ColumnInfo(name = "views")
    private int views;


    public Question(String question, String topic, String subTopic, String moodAsked, String option1, String option2, String option3, String type) {
        this.question = question;
        this.topic = topic;
        this.subTopic = subTopic;
        this.moodAsked = moodAsked;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.type = type;
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

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getSubTopic() {
        return subTopic;
    }

    public void setSubTopic(String subTopic) {
        this.subTopic = subTopic;
    }

    public String getMoodAsked() {
        return moodAsked;
    }

    public void setMoodAsked(String moodAsked) {
        this.moodAsked = moodAsked;
    }

    public String getMoodAnswered() {
        return moodAnswered;
    }

    public void setMoodAnswered(String moodAnswered) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(int userAnswer) {
        this.userAnswer = userAnswer;
    }
}
