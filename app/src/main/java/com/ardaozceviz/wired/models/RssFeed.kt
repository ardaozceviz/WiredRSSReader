package com.ardaozceviz.wired.models

import com.google.gson.annotations.SerializedName


data class RssFeed(
        @SerializedName("rss") val rss: Rss = Rss()
)

data class Rss(
        @SerializedName("channel") val channel: Channel = Channel()
)

data class Channel(
        @SerializedName("item") val item: List<Item> = listOf()
)

data class Item(
        @SerializedName("title") val title: String = "")

