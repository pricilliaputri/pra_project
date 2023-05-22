package com.binar.pra_project.network


import retrofit2.http.GET
import com.binar.pra_project.model.UsersItem
import retrofit2.Call

interface RestfulApi {
    @GET("admin/user")
    fun getUser(): Call<List<UsersItem>>
}