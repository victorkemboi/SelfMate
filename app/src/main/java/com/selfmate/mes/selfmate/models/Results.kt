package com.selfmate.mes.selfmate.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.selfmate.mes.selfmate.models.tc.DateTypeConverter
import java.util.*

/**
 * Created by vicki_mes on 11/20/2017.
 */

@Entity(tableName = "results")
@TypeConverters(DateTypeConverter::class)
class Results(
        @ColumnInfo(name = "timeCreated")
        var timeCreated: Date = Date(),

        @ColumnInfo(name = "timeUpdated")
        var timeUpdated: Date = Date(),
        @ColumnInfo(name = "engagement")
        var engagement: Int = 0,
        @ColumnInfo(name = "rxpoints")
        var rxpoints: Int = 0) {
    @PrimaryKey(autoGenerate = true)
    var uid: Long? = null
}
