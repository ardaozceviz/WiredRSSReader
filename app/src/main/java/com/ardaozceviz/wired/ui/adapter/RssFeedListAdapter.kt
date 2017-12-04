package com.ardaozceviz.wired.ui.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
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
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.drawable.ProgressBarDrawable
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.common.ResizeOptions
import com.facebook.imagepipeline.request.ImageRequestBuilder
import java.net.URL

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
        return if (channel.item.count() > 5) {
            5
        } else {
            channel.item.count()
        }
    }

    inner class RssFeedHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }

        private val titleTextView = itemView.findViewById<TextView>(R.id.list_item_title)
        private val feedImageView = itemView.findViewById<SimpleDraweeView>(R.id.list_item_image_view)
        private var link = ""
        //private val repetitiveWordsTextView = itemView.findViewById<TextView>(R.id.list_item_repetitive_words)

        fun bindRssFeedItem(listItem: Item) {
            Log.d(TAG_AD_LIST, "bindRssFeedItem() is executed.")
            val progressBarDrawable = ProgressBarDrawable()

            val url = URL(listItem.thumbnail.url)
            val uri = Uri.parse(url.toURI().toString())
            val imageRequest = ImageRequestBuilder.newBuilderWithSource(uri)
                    .setResizeOptions(ResizeOptions(50, 50))
                    .build()

            titleTextView.text = listItem.title
            feedImageView.hierarchy.setProgressBarImage(progressBarDrawable)
            feedImageView.controller = Fresco.newDraweeControllerBuilder()
                    .setTapToRetryEnabled(true)
                    .setImageRequest(imageRequest)
                    .build()
            link = listItem.link
        }

        override fun onClick(p0: View?) {
            Log.d(TAG_AD_LIST, "onClick(): $link")
            val webpage = Uri.parse(link)
            val intent = Intent(Intent.ACTION_VIEW, webpage)
            if (intent.resolveActivity(context.packageManager) != null) {
                context.startActivity(intent)
            }
        }

    }
}