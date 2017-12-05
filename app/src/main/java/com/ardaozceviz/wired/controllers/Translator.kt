package com.ardaozceviz.wired.controllers

import android.content.Context
import android.util.Log
import com.ardaozceviz.wired.models.TAG_C_TRANSLATION
import com.ardaozceviz.wired.ui.controller.UserInterface
import kotlinx.coroutines.experimental.async
import org.jsoup.Jsoup

/**
 * Created by arda on 05/12/2017.
 */
class Translator(private val context: Context, private val url: String) {
    private val server = Server(context)
    var repetitiveWords = ""

    suspend fun start() {
        Log.d(TAG_C_TRANSLATION, "start() is executed.")
        val text = getWebText()
        repetitiveWords = getRepetitiveWords(text)
        translateWords(repetitiveWords)
    }

    private suspend fun getWebText(): String {
        Log.d(TAG_C_TRANSLATION, "getWebText() is executed.")
        val document = async { Jsoup.connect(url).get() }
        return document.await().select("article.article-body-component").text()
    }

    private fun getRepetitiveWords(text: String): String {
        Log.d(TAG_C_TRANSLATION, "getRepetitiveWords() is executed.")
        var map = WordCount.phrase(text)
        var wordCounter = 0
        val topFiveWordList = mutableListOf<String>()
        var repetitiveWords = ""

        map = map.toList().sortedByDescending { (_, value) -> value }.toMap()
        mainLoop@
        for (item in map) {
            when (item.key) {
                "the", "of", "a", "is", "to", "i", "for", "are", "than", "that", "and", "this", "he", "she", "his", "her", "their", "your", "them", "they", "it", "on", "or", "in", "be", "at", "you", "if", "what", "not", "can", "it's", "but", "with", "s", "bb", "was", "were" -> continue@mainLoop
                else -> {
                    wordCounter++
                    if (wordCounter > 5) {
                        break@mainLoop
                    }
                    topFiveWordList.add(item.key)
                }
            }
        }
        for (item in topFiveWordList) {
            repetitiveWords += "$item, "
        }
        return repetitiveWords.removeSuffix(", ").trim()
    }

    private fun translateWords(text: String) {
        Log.d(TAG_C_TRANSLATION, "translateWords() is executed.")
        if (text != "") {
            server.translate(text.removeSuffix(", "))
        } else {
            UserInterface(context).updateTranslate(isFailed = true)
        }
    }
}