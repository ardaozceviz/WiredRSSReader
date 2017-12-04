package com.ardaozceviz.wired.controllers

import android.content.Context
import android.util.Log
import com.ardaozceviz.wired.models.RssFeed
import com.ardaozceviz.wired.models.TAG_C_SERVER
import com.ardaozceviz.wired.ui.controller.UserInterface
import com.google.gson.Gson
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.JsonHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject


/**
 * Created by arda on 03/12/2017.
 */
class Server(private val context: Context) {
    private val rssFeedUrl = "http://192.168.1.8:8080/?url=https://www.wired.com/feed/rss"
    private val userInterface = UserInterface(context)

    fun getRssFeed() {
        Log.d(TAG_C_SERVER, "getRssFeed() is executed.")
        userInterface.startSwipeRefresh()
        val client = AsyncHttpClient()
        client.get(rssFeedUrl, object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, response: JSONObject?) {
                Log.d(TAG_C_SERVER, "getRssFeed() onSuccess() is executed.")
                userInterface.stopSwipeRefresh()
                if (response != null) {
                    val rssFeed = Gson().fromJson(response.toString(), RssFeed::class.java)
                    LocalRssFeed(context).save(rssFeed)
                    userInterface.updateUI(rssFeed)
                    Log.d(TAG_C_SERVER, "getRssFeed() onSuccess() rssFeed: $rssFeed.")
                }
            }

            override fun onFailure(statusCode: Int, headers: Array<out Header>?, throwable: Throwable?, errorResponse: JSONObject?) {
                Log.e(TAG_C_SERVER, "getRssFeed() onFailure() ${throwable.toString()}.")
                Log.d(TAG_C_SERVER, "getRssFeed() statusCode: $statusCode.")
                userInterface.stopSwipeRefresh(true)
            }
        })
    }

}