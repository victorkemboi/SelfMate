package com.selfmate.mes.selfmate.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by vicki_mes on 11/16/2017.
 *
 */

@Entity(tableName = "question")
data class Question(
        @ColumnInfo(name = "question")
        var question: String = "",

        @ColumnInfo(name = "topic")
        var topic: Int = 0,

        @ColumnInfo(name = "sub_topic")
        var subTopic: String = "",

        @ColumnInfo(name = "mood_asked")
        var moodAsked: Int = 0,

        @ColumnInfo(name = "mood_answered")
        var moodAnswered: Int = 0,

        @ColumnInfo(name = "option_1")
        var option1: String = "",

        @ColumnInfo(name = "option_2")
        var option2: String = "",

        @ColumnInfo(name = "option_3")
        var option3: String = "",

        @ColumnInfo(name = "closed_question")
        var isClosedQuestion: Boolean = false,

        @ColumnInfo(name = "answer")
        var answer: Int = 0,

        @ColumnInfo(name = "failed")
        var isFailed: Boolean = false,

        @ColumnInfo(name = "views")
        var views: Int = 0,

        @ColumnInfo(name = "answered")
        var answered: Boolean = false,

        @PrimaryKey(autoGenerate = true)
        var uid: Long? = null
)

