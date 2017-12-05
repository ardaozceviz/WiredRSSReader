package com.ardaozceviz.wired.ui.controller

import android.app.Activity
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.ardaozceviz.wired.R
import com.ardaozceviz.wired.controllers.Server
import com.ardaozceviz.wired.controllers.WordCount
import com.ardaozceviz.wired.models.RssFeed
import com.ardaozceviz.wired.models.TAG_C_INTERFACE
import com.ardaozceviz.wired.models.Translation
import com.ardaozceviz.wired.ui.adapter.RssFeedListAdapter
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by arda on 03/12/2017.
 */
class UserInterface(private val context: Context) {
    private val activity = context as Activity
    private val swipeRefreshLayout = activity.main_swipe_refresh_layout

    fun initialize() {
        Server(context).getRssFeed()
        swipeRefreshLayout.setOnRefreshListener {
            Log.d(TAG_C_INTERFACE, "onRefresh called from swipeRefreshLayout")
            Server(context).getRssFeed()
        }
    }

    fun updateUI(rssFeed: RssFeed) {
        Log.d(TAG_C_INTERFACE, "updateUI() is executed.")
        val rssFeedRecyclerView = activity.rss_feed_recycler_view
        val adapter = RssFeedListAdapter(context, rssFeed.rss.channel)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rssFeedRecyclerView.adapter = adapter
        rssFeedRecyclerView.layoutManager = layoutManager
        rssFeedRecyclerView.setHasFixedSize(true)
    }

    fun updateTranslate(translation: Translation? = null, isFailed: Boolean) {
        val progressBarTr = activity.findViewById<ProgressBar>(R.id.feed_detail_progress_tr)
        val repetitiveWordsTextViewTr = activity.findViewById<TextView>(R.id.feed_detail_repetitive_words_tr)
        progressBarTr.visibility = View.GONE
        if (!isFailed) {
            val trWordsMap = WordCount.phrase(translation?.text.toString())
            val trWordsList = mutableListOf<String>()
            for (word in trWordsMap) {
                trWordsList.add(word.key)
            }
            repetitiveWordsTextViewTr.text = trWordsList.toString().removePrefix("[").removeSuffix("]")
        } else {
            repetitiveWordsTextViewTr.text = "Farklı yapıda sayfa hatası."
        }
    }

    fun startSwipeRefresh() {
        Log.d(TAG_C_INTERFACE, "startSwipeRefresh() is executed.")
        if (!swipeRefreshLayout.isRefreshing) {
            swipeRefreshLayout.post {
                swipeRefreshLayout.isEnabled = false
                swipeRefreshLayout.isRefreshing = true
            }
        }
    }

    fun stopSwipeRefresh(isWithError: Boolean) {
        Log.d(TAG_C_INTERFACE, "stopSwipeRefresh() is executed.")
        fun stop() {
            swipeRefreshLayout.post {
                swipeRefreshLayout.isEnabled = true
                swipeRefreshLayout.isRefreshing = false
            }
        }
        if (swipeRefreshLayout.isRefreshing) {
            if (!isWithError) {
                toast("Update is successful!")
                stop()
            } else {
                toast("Update is failed!")
                stop()
            }
        }
    }


    private fun toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(context, message, duration).show()
    }
}