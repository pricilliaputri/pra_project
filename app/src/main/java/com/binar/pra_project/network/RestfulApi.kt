package com.binar.pra_project.network


import com.binar.pra_project.model.*
import retrofit2.Call
import retrofit2.http.*

interface RestfulApi {
//    @GET("users")
//    fun getAllUsers(): Call<List<UsersItem>>
//
//    @GET("news")
//    fun getAllNews(): Call<List<NewsUpdateItem>>
//
    @GET("sliders/{id}")
    fun getSliderById(
        @Path("id") id : Int,
    ):Call<List<SlidersItem>>

    //Users
    @GET("users/{id}")
     fun getAllUser():Call<List<UsersItem>>

    @POST("users")
     fun postUser(@Body request: DataUser): Call<List<DataPostUser>>

    @PUT("users/{id}")
    fun putUserById(@Path("id") id: Int,@Body request: DataUser ):Call<List<DataPostUser>>

    @GET("user/{id}")
    fun getUserId(
        @Path("id") userId: String
    ) : Call<List<UsersItem>>


    //Transaction History
    @GET("users/{id}/transhistory")
     fun getUserTransactionHistory(@Path("id") id: Int): Call<List<TransHistoryItem>>

    @POST("users/{id}/transhistory")
     fun postTransactionHistory(@Path("id") id: Int): Call<List<TransHistoryItem>>
    @DELETE("users/{id}/transhistory/{id_trans}")
     fun deleteTransactionHistory(
        @Path("id") id: Int,
        @Path("id_trans") id_trans: Int
    ): Call<List<TransHistoryItem>>

    //Cart
    @GET("users/{id}/cart")
     fun getUserCart(@Path("id") id: Int): Call<List<CartItem>>

    @POST("users/{id}/cart")
    fun postCart(@Path("id") id: Int): Call<List<CartItem>>

    @DELETE("users/{id}/cart/{id_cart}")
     fun deleteCart(
        @Path("id") id: Int,
        @Path("id_cart") id_cart: Int
    ): Call<List<CartItem>>

    //Favourite
    @GET("users/{id}/favourite")
    fun getUserFavourite(@Path("id") id: Int): Call<List<FavouriteItem>>

    @POST("users/{id}/favourite")
   fun postFavourite(@Path("id") id: Int):Call<List<FavouriteItem>>

    @DELETE("users/{id}/cart/{id_fav}")
     fun deleteFavourite(
        @Path("id") id: Int,
        @Path("id_fav") id_fav: Int
    ): Call<List<FavouriteItem>>

    //News Update
    @GET("news_update")
    fun getNewsUpdate(): Call<List<NewsUpdateItem>>

    @GET("news_update/{id}?")
    fun getDetailNews(@Path("id") id:Int): Call<DataDetailNewsItem>

    //Category Product
    @GET("category_product")
    fun getCategory(): Call<List<CategoryProductItem>>

    @GET("category_product/{id}")
    fun getCategoryById(@Path("id") id: Int): Call<List<CategoryProductItem>>

    //Product
    @GET("category_product/1/products")
    fun getProduct(id: Int): Call<List<ProductsItem>>

    @GET("category_product/{id}/products/{id_product}")
     fun getProductById(
        @Path("id") id: Int,
        @Path("id_product") id_product: Int
    ): Call<List<ProductsItem>>




}