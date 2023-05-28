package com.binar.pra_project.model

import com.google.gson.annotations.SerializedName

data class DataDetailNewsItem(
    @SerializedName("content")
    val content: String? = null,
    @SerializedName("createdAt")
    val createdAt: String? = null,
    @SerializedName("id_news")
    val idNews: String? = null,
    @SerializedName("news_image")
    val newsImage: String? = null,
    @SerializedName("title")
    val title: String? = null
)
