package com.selfmate.mes.selfmate.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.selfmate.mes.selfmate.models.tc.DateTypeConverter
import java.util.*

/**
 * Created by vicki_mes on 11/16/2017.
 */

@Entity(tableName = "moodlog")
@TypeConverters(DateTypeConverter::class)
data class MoodLog(


        @ColumnInfo(name = "time")
        var time: Date,

        @ColumnInfo(name = "mood")
        var mood: Int,

        @PrimaryKey(autoGenerate = true)
        var uid: Long? = null

)