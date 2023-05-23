package com.binar.pra_project.model

import com.google.gson.annotations.SerializedName

data class CategoryProductItem(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String
)
