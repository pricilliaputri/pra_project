package com.binar.pra_project.model

import com.google.gson.annotations.SerializedName

data class CartItem(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id_cart")
    val idCart: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("product_image")
    val productImage: String,
    @SerializedName("userId")
    val userId: String
)
