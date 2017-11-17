package com.selfmate.mes.selfmate.Models.TypeConverters;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by vicki_mes on 11/16/2017.
 */


public class DateTypeConverter {

    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}