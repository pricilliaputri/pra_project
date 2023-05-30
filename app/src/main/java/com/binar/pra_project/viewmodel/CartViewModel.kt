package com.binar.pra_project.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.binar.pra_project.model.CartItem
import com.binar.pra_project.model.DataDetailProductItem
import com.binar.pra_project.model.UsersItem
import com.binar.pra_project.network.RestfulApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CartViewModel @Inject constructor(private var api : RestfulApi) : ViewModel() {
    private var livedatacart : MutableLiveData<List<CartItem>> = MutableLiveData()

    fun getlivedatacart(): MutableLiveData<List<CartItem>>{
        return livedatacart
    }

    fun postcart(id:String,cart: DataDetailProductItem){
        api.postCart(id,cart).enqueue(object : Callback<List<CartItem>>{
            override fun onResponse(
                call: Call<List<CartItem>>,
                response: Response<List<CartItem>>
            ) {
                if (response.isSuccessful){
                    livedatacart.postValue(response.body())
                }else{
                    livedatacart.postValue(emptyList())
                }
            }

            override fun onFailure(call: Call<List<CartItem>>, t: Throwable) {
                livedatacart.postValue(emptyList())
            }

        })
    }

    fun getCart(userId: String){
        api.getUserCart(userId).enqueue(object : Callback<List<CartItem>> {
            override fun onResponse(
                call: Call<List<CartItem>>,
                response: Response<List<CartItem>>
            ) {
                if (response.isSuccessful){
                    livedatacart.postValue(response.body())
                }else{
                    livedatacart.postValue(emptyList())
                }
            }

            override fun onFailure(call: Call<List<CartItem>>, t: Throwable) {
                livedatacart.postValue(emptyList())
            }

        })
    }



}