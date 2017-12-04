package com.ardaozceviz.wired.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import com.ardaozceviz.wired.R
import com.ardaozceviz.wired.controllers.WordCount
import com.ardaozceviz.wired.models.EXTRA_URL
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.jsoup.Jsoup


class FeedDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed_detail)

        val webView = findViewById<WebView>(R.id.feed_detail_web_view)
        val url = intent.getStringExtra(EXTRA_URL)

        webView.webViewClient = WebViewClient()
        webView.loadUrl(url)

        doAsync {
            val document = Jsoup.connect(url).get()
            val text = document.select("article.article-body-component").text()
            // article-body-component article-body-component
            uiThread {
                //Log.d("FeedDetailActivity", "Title: ${text}")
                var map = WordCount.phrase(text)
                map = map.toList().sortedBy { (key, value) -> value }.toMap()
                for (item in map){
                    Log.d("MAP", "${item.key}, ${item.value}")
                }

            }
        }
    }
}
