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
    @GET("users")
    fun getAllUsers(): Call<List<UsersItem>>

    @POST("users")
    fun postUser(@Body request: DataUsers): Call<List<DataPostUser>>


    @PUT("users/{id}")
    fun putUserById(@Path("id") id:String ):Call<UsersItem>

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
     fun getUserCart(@Path("id") id: String): Call<List<CartItem>>

    @POST("users/{id}/cart")
    fun postCart(@Path("id") id:String,
                 @Body request: DataDetailProductItem) : Call <List<CartItem>>

    @FormUrlEncoded
    @POST("users/{id}/cart")
    fun addCart(
        @Path("id") id:String,
        @Field("name") name:String,
        @Field("product_image") productImage:String,
        @Field("price") price:Int,
        @Field("description") description:String,
    ):Call<List<CartItem>>

    @DELETE("users/{id}/cart/{id_cart}")
     fun deleteCart(
        @Path("id") id: Int,
        @Path("id_cart") id_cart: Int
    ): Call<List<CartItem>>

    //Favourite
    @GET("users/{id}/favourite")
    fun getFavourite(@Path("id") id: String): Call<List<FavouriteItem>>

    @POST("users/{id}/favourite")
   fun postFavourite(@Path("id") id: Int):Call<List<FavouriteItem>>

    @FormUrlEncoded
    @POST("users/{id}/favourite")
    fun addFavouriteProduct(
        @Path("id") id:String,
        @Field("name") name:String,
        @Field("product_image") productImage:String,
        @Field("price") price:Int,
        @Field("description") description:String,
    ):Call<FavouriteItem>

    @DELETE("users/{userId}/favourite/{id}")
    fun deleteFavouriteProduct(
        @Path("userId") userId:String,
        @Path("id") id:String
    ):Call<FavouriteItem>

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
    fun getProduct(): Call<List<ProductsItem>>

    @GET("category_product/2/products")
    fun getProductSecond(): Call<List<ProductsItem>>

    @GET("category_product/1/products/{id}?")
    fun getDetailProduct(@Path("id") id:String): Call<DataDetailProductItem>

    @GET("category_product/2/products/{id}?")
    fun getDetailProductSecond(@Path("id") id:Int): Call<DataDetailProductItem>


    @GET("users/{id}/transhistory")
    suspend fun getDataHistoryTransaction(
        @Path("id") id : Int,
    ) : List<TransHistoryItem>

    @POST("users/{id}/transhistory")
    suspend fun insertDataHistoryTransaction(
        @Path("id") id : Int,
        @Body data :TransHistoryItem
    ) : TransHistoryItem




}