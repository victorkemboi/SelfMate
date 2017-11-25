package com.selfmate.mes.selfmate.si

/**
 * Created by vicki_mes on 11/20/2017.
 */

object TOPIC {
    var MOVIE = 1
    var FINANCE = 2
    var TECHNOLOGY = 3
    var FOOD = 4
    var SPORT = 5
    var SCIENCE = 6
    var BRANDS = 7
    var HOT = 8

    fun getTopic(i: Int): String = when (i) {
        1 -> "MOVIE"

        2 -> "FINANCE"

        3 -> "TECHNOLOGY"
        4 -> "FOOD"
        5 -> "SPORT"
        6 -> "SCIENCE"
        7 -> "BRANDS"
        8 -> "HOT"
        else -> "N.O.N"
    }
}
