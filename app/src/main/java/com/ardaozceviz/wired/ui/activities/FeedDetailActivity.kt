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
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
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

        doAsync {
            val document = Jsoup.connect(url).get()
            val text = document.select("article.article-body-component").text()
            uiThread {
                Log.d("FeedDetailActivity", "uiThread{} is executed.")
                var map = WordCount.phrase(text)
                var wordCounter = 0
                val topFiveWordList = mutableListOf<String>()
                map = map.toList().sortedByDescending { (_, value) -> value }.toMap()
                mainLoop@ for (item in map) {
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
                var repetitiveWords = ""
                for (item in topFiveWordList) {
                    repetitiveWords += "$item, "
                }
                repetitiveWordsTextView.text = repetitiveWords.removeSuffix(", ")
                progressBarEn.visibility = View.GONE
                server.translate(repetitiveWords.removeSuffix(", "))
            }
        }
    }
}
