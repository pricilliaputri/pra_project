package com.binar.pra_project.model

import com.google.gson.annotations.SerializedName

data class DataDetailProductItem(

    @SerializedName("category_productId")
    val categoryProductId: String? = null,
    @SerializedName("createdAt")
    val createdAt: String? = null,
    @SerializedName("description")
    val description: String ? = null,
    @SerializedName("id_product")
    val idProduct: String ? = null,
    @SerializedName("name")
    val name: String ? = null,
    @SerializedName("price")
    val price: String ? = null,
    @SerializedName("product_image")
    val productImage: String ? = null
)
