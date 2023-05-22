package com.binar.pra_project.network

import android.telecom.Call
import retrofit2.http.GET
import com.binar.pra_project.model.UsersItem

interface RestfulApi {
    @GET("admin/user")
    fun getUser():Call<List<UsersItem>>
}