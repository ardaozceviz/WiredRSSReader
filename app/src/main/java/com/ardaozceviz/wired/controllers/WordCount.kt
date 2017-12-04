package com.ardaozceviz.wired.controllers

/**
 * Created by arda on 04/12/2017.
 */
object WordCount {
    fun phrase(phrase: String): Map<String, Int> {
        return toWords(phrase).
                groupBy { it }.
                mapValues({ it.value.size })
    }

    private fun toWords(phrase: String): List<String> {
        return phrase.
                toLowerCase().replace(Regex("[^\\w']"), " ").trim().
                split(Regex("\\s+"))
    }
}