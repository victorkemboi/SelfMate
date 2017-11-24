package com.selfmate.mes.selfmate.Models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.selfmate.mes.selfmate.Models.TypeConverters.DateTypeConverter;

import java.util.Date;

/**
 * Created by vicki_mes on 11/20/2017.
 */

@Entity(tableName = "results")
@TypeConverters({DateTypeConverter.class})
public class Results {
    @PrimaryKey()
    private int uid;

    @ColumnInfo(name = "timeCreated")
    private Date timeCreated;

    @ColumnInfo(name = "timeUpdated")
    private Date timeUpdated;

    @ColumnInfo(name = "engagement")
    private int engagement;

    @ColumnInfo(name = "rxpoints")
    private int rxpoints;

    public Results( Date timeCreated, Date timeUpdated, int engagement, int rxpoints,int uid) {
        this.timeCreated = timeCreated;
        this.timeUpdated = timeUpdated;
        this.engagement = engagement;
        this.rxpoints = rxpoints;
        this.uid =uid;
    }


    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }



    public int getEngagement() {
        return engagement;
    }

    public void setEngagement(int engagement) {
        this.engagement = engagement;
    }

    public int getRxpoints() {
        return rxpoints;
    }

    public void setRxpoints(int rxpoints) {
        this.rxpoints = rxpoints;
    }

    public Date getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }

    public Date getTimeUpdated() {
        return timeUpdated;
    }

    public void setTimeUpdated(Date timeUpdated) {
        this.timeUpdated = timeUpdated;
    }
}
