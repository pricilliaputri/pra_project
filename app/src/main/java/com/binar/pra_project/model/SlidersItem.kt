package com.binar.pra_project.model

import com.google.gson.annotations.SerializedName

data class SlidersItem(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String
)
