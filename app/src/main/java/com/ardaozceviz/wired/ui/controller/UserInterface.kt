package com.ardaozceviz.wired.ui.controller

import android.app.Activity
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.ardaozceviz.wired.controllers.Server
import com.ardaozceviz.wired.models.RssFeed
import com.ardaozceviz.wired.models.TAG_C_INTERFACE
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

    fun startSwipeRefresh() {
        Log.d(TAG_C_INTERFACE, "startSwipeRefresh() is executed.")
        if (!swipeRefreshLayout.isRefreshing) {
            swipeRefreshLayout.post {
                swipeRefreshLayout.isEnabled = false
                swipeRefreshLayout.isRefreshing = true
            }
        }
    }

    fun stopSwipeRefresh(isWithError: Boolean? = null) {
        Log.d(TAG_C_INTERFACE, "stopSwipeRefresh() is executed.")
        if (swipeRefreshLayout.isRefreshing) {
            swipeRefreshLayout.post {
                swipeRefreshLayout.isEnabled = true
                swipeRefreshLayout.isRefreshing = false
            }
        }
        if (isWithError != null) {
            // Show error snackbar here!
        }
    }
}