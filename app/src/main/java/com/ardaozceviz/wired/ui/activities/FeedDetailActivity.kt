package com.ardaozceviz.wired.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebView
import android.webkit.WebViewClient
import com.ardaozceviz.wired.R
import com.ardaozceviz.wired.models.EXTRA_URL


class FeedDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed_detail)

        val webView = findViewById<WebView>(R.id.feed_detail_web_view)
        val url = intent.getStringExtra(EXTRA_URL)
        
        webView.webViewClient = WebViewClient()
        webView.loadUrl(url)

    }
}
