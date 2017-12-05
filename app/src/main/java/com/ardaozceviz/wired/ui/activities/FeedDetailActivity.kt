package com.ardaozceviz.wired.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import android.widget.TextView
import com.ardaozceviz.wired.R
import com.ardaozceviz.wired.controllers.Translator
import com.ardaozceviz.wired.models.EXTRA_URL
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async

class FeedDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed_detail)

        val repetitiveWordsTextView = findViewById<TextView>(R.id.feed_detail_repetitive_words_en)
        val progressBarEn = findViewById<ProgressBar>(R.id.feed_detail_progress_en)

        val webView = findViewById<WebView>(R.id.feed_detail_web_view)
        val url = intent.getStringExtra(EXTRA_URL)

        val translate = Translator(this, url)

        webView.webViewClient = WebViewClient()
        webView.loadUrl(url)

        async(UI) {
            translate.start()
            if (translate.repetitiveWords != null) {
                repetitiveWordsTextView.text = translate.repetitiveWords
            }
            progressBarEn.visibility = View.GONE
        }
    }


}
