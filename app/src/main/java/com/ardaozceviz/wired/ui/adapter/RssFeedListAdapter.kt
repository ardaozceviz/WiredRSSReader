package com.ardaozceviz.wired.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ardaozceviz.wired.R
import com.ardaozceviz.wired.models.Channel
import com.ardaozceviz.wired.models.Item
import com.ardaozceviz.wired.models.TAG_AD_LIST

/**
 * Created by arda on 04/12/2017.
 */
class RssFeedListAdapter(private val context: Context, private val channel: Channel) : RecyclerView.Adapter<RssFeedListAdapter.RssFeedHolder>() {

    override fun onBindViewHolder(holder: RssFeedHolder?, position: Int) {
        Log.d(TAG_AD_LIST, "onBindViewHolder() is executed.")
        holder?.bindRssFeedItem(channel.item[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RssFeedHolder {
        Log.d(TAG_AD_LIST, "onCreateViewHolder() is executed.")
        val view = LayoutInflater.from(context).inflate(R.layout.rss_feed_list_item, parent, false)
        return RssFeedHolder(view)
    }

    override fun getItemCount(): Int {
        Log.d(TAG_AD_LIST, "getItemCount() is executed.")
        return channel.item.count()
    }

    inner class RssFeedHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }

        fun bindRssFeedItem(listItem: Item) {
            Log.d(TAG_AD_LIST, "bindRssFeedItem() is executed.")
            val titleTextView = itemView.findViewById<TextView>(R.id.list_item_title)
            titleTextView.text = listItem.title
        }

        override fun onClick(p0: View?) {
            Log.d(TAG_AD_LIST, "onClick() is executed.")
        }

    }
}