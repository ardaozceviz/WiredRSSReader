package com.ardaozceviz.wired.controllers

import android.app.Activity
import android.content.Context
import android.util.Log
import com.ardaozceviz.wired.models.TAG_C_INTERFACE
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

    fun startSwipeRefresh() {
        Log.d(TAG_C_INTERFACE, "startSwipeRefresh() is executed.")
        if (!swipeRefreshLayout.isRefreshing) {
            swipeRefreshLayout.post {
                swipeRefreshLayout.isEnabled = false
                swipeRefreshLayout.isRefreshing = true
            }
        }
    }

    fun stopSwipeRefresh() {
        Log.d(TAG_C_INTERFACE, "stopSwipeRefresh() is executed.")
        if (swipeRefreshLayout.isRefreshing) {
            swipeRefreshLayout.post {
                swipeRefreshLayout.isEnabled = true
                swipeRefreshLayout.isRefreshing = false
            }
        }
    }
}