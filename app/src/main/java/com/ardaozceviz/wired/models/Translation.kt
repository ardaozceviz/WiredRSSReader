package com.ardaozceviz.wired.models

import com.google.gson.annotations.SerializedName


/**
 * Created by arda on 05/12/2017.
 */
data class Translation(
        @SerializedName("code") val code: Int = 0,
        @SerializedName("lang") val lang: String = "",
        @SerializedName("text") val text: List<String> = listOf()
)