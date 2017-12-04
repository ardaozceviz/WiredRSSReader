package com.ardaozceviz.wired.controllers

import android.content.Context
import android.preference.PreferenceManager
import android.util.Log
import com.ardaozceviz.wired.models.RssFeed
import com.ardaozceviz.wired.models.TAG_C_LOCAL_DATA
import com.google.gson.Gson

/**
 * Created by arda on 04/12/2017.
 */
class LocalRssFeed(private val context: Context) {

    fun save(rssFeed: RssFeed) {
        Log.d(TAG_C_LOCAL_DATA, "save() is executed.")
        val gson = Gson()
        val rssFeedJson = gson.toJson(rssFeed)
        Log.d(TAG_C_LOCAL_DATA, "save() rssFeedJson: $rssFeedJson.")
        PreferenceManager.getDefaultSharedPreferences(context).edit()
                .putString("rssFeed", rssFeedJson.toString()).apply()
    }
}