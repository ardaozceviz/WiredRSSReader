package com.ardaozceviz.wired.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import android.widget.TextView
import com.ardaozceviz.wired.R
import com.ardaozceviz.wired.controllers.Server
import com.ardaozceviz.wired.controllers.WordCount
import com.ardaozceviz.wired.models.EXTRA_URL
import com.ardaozceviz.wired.models.TAG_AC_FEED_DETAIL
import com.ardaozceviz.wired.ui.controller.UserInterface
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jsoup.Jsoup


class FeedDetailActivity : AppCompatActivity() {

    lateinit var server: Server

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed_detail)

        val repetitiveWordsTextView = findViewById<TextView>(R.id.feed_detail_repetitive_words_en)
        val progressBarEn = findViewById<ProgressBar>(R.id.feed_detail_progress_en)

        server = Server(this)

        val webView = findViewById<WebView>(R.id.feed_detail_web_view)
        val url = intent.getStringExtra(EXTRA_URL)

        webView.webViewClient = WebViewClient()
        webView.loadUrl(url)

        async(UI) {
            val webText = getWebText(url)
            val repetitiveWords = getRepetitiveWords(webText)
            //val translation = translateWords(repetitiveWords).await()
            repetitiveWordsTextView.text = repetitiveWords
            progressBarEn.visibility = View.GONE
            translateWords(repetitiveWords)
        }
    }

    private suspend fun getWebText(url: String): String {
        Log.d(TAG_AC_FEED_DETAIL, "getWebText() is executed.")
        val document = async { Jsoup.connect(url).get() }
        return document.await().select("article.article-body-component").text()
    }

    private suspend fun getRepetitiveWords(text: String): String {
        Log.d(TAG_AC_FEED_DETAIL, "getRepetitiveWords() is executed.")
        var map = WordCount.phrase(text)
        var wordCounter = 0
        val topFiveWordList = mutableListOf<String>()
        var repetitiveWords = ""
        return async {
            map = map.toList().sortedByDescending { (_, value) -> value }.toMap()
            mainLoop@
            for (item in map) {
                when (item.key) {
                    "the", "of", "a", "is", "to", "i", "for", "are", "than", "that", "and", "this", "he", "she", "his", "her", "their", "them", "they", "it", "on", "or", "in", "be", "at", "you", "if", "what", "not", "can", "it's", "but", "with", "s", "bb", "was", "were" -> continue@mainLoop
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
            repetitiveWords.removeSuffix(", ").trim()
        }.await()
    }

    fun translateWords(text: String) {
        Log.d(TAG_AC_FEED_DETAIL, "translateWords() is executed.")
        if (text != "") {
            server.translate(text.removeSuffix(", "))
        } else {
            UserInterface(this@FeedDetailActivity).updateTranslate(isFailed = true)
        }
    }
}
