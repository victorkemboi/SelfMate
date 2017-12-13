package com.selfmate.mes.selfmate.si

/**
 * Created by vicki_mes on 11/20/2017.
 */

object TOPIC {
    val BRANDS = 1
    val FINANCE = 2
    val FOOD = 3
    val MOVIE = 4
    val SCIENCE = 5
    val SPORT = 6
    val TECHNOLOGY = 7
    val HOT = 8

    fun getTopic(i: Int): String = when (i) {
        1 -> "BRANDS"
        2 -> "FINANCE"
        3 -> "FOOD"
        4 -> "MOVIE"
        5 -> "SCIENCE"
        6 -> "SPORT"
        7 -> "TECHNOLOGY"
        8 -> "HOT"
        else -> "N.O.N"
    }
}
