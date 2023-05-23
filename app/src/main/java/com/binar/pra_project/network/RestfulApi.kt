package com.binar.pra_project.network


import com.binar.pra_project.model.NewsUpdateItem
import com.binar.pra_project.model.SlidersItem
import retrofit2.http.GET
import com.binar.pra_project.model.UsersItem
import retrofit2.Call
import retrofit2.http.Path

interface RestfulApi {
    @GET("users")
    fun getAllUsers(): Call<List<UsersItem>>

    @GET("news")
    fun getAllNews(): Call<List<NewsUpdateItem>>

    @GET("sliders/{id}")
    fun getSliderById(
        @Path("id") id : Int,
    ):Call<List<SlidersItem>>


}