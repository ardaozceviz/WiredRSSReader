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
        @SerializedName("thumbnail") val thumbnail: Thumbnail = Thumbnail(),
        @SerializedName("link") val link: String = "",
        @SerializedName("title") val title: String = "",
        @SerializedName("keywords") val keywords: String = ""
)

data class Thumbnail(
        @SerializedName("-url") val url: String = "",
        @SerializedName("-width") val width: String = "",
        @SerializedName("-height") val height: String = ""
)


/*


data class asd(
		@SerializedName("rss") val rss: Rss = Rss()
)

data class Rss(
		@SerializedName("-atom") val atom: String = "",
		@SerializedName("-dc") val dc: String = "",
		@SerializedName("-media") val media: String = "",
		@SerializedName("-version") val version: String = "",
		@SerializedName("channel") val channel: Channel = Channel()
)

data class Channel(
		@SerializedName("language") val language: String = "",
		@SerializedName("lastBuildDate") val lastBuildDate: String = "",
		@SerializedName("item") val item: List<Item> = listOf(),
		@SerializedName("title") val title: String = "",
		@SerializedName("description") val description: String = "",
		@SerializedName("link") val link: List<String> = listOf(),
		@SerializedName("copyright") val copyright: String = ""
)

data class Item(
		@SerializedName("modified") val modified: String = "",
		@SerializedName("publisher") val publisher: String = "",
		@SerializedName("thumbnail") val thumbnail: Thumbnail = Thumbnail(),
		@SerializedName("description") val description: String = "",
		@SerializedName("link") val link: String = "",
		@SerializedName("guid") val guid: Guid = Guid(),
		@SerializedName("keywords") val keywords: String = "",
		@SerializedName("creator") val creator: String = "",
		@SerializedName("title") val title: String = "",
		@SerializedName("pubDate") val pubDate: String = "",
		@SerializedName("content") val content: String = "",
		@SerializedName("category") val category: String = ""
)

data class Thumbnail(
		@SerializedName("-url") val url: String = "",
		@SerializedName("-width") val width: String = "",
		@SerializedName("-height") val height: String = ""
)

data class Guid(
		@SerializedName("#content") val content: String = "",
		@SerializedName("-isPermaLink") val isPermaLink: String = ""
)*/
