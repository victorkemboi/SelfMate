package com.selfmate.mes.selfmate.Models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.selfmate.mes.selfmate.Models.TypeConverters.DateTypeConverter;

import java.util.Date;

/**
 * Created by vicki_mes on 11/16/2017.
 */

@Entity(tableName = "moodlog")
@TypeConverters({DateTypeConverter.class})
public class MoodLog {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "mood")
    private int mood;

    @ColumnInfo(name = "time")
    private Date time;

    @Ignore
    public MoodLog() {
    }

    public MoodLog(int mood, Date time) {
        this.mood = mood;
        this.time = time;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getMood() {
        return mood;
    }

    public void setMood(int mood) {
        this.mood = mood;
    }
}
