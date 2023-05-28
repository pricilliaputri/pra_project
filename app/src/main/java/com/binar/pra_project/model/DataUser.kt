package com.binar.pra_project.model

import com.google.gson.annotations.SerializedName

data class DataUser(
    val email: String?,
    val image: String? = "",
    val name: String?,
    val password: String?
)
