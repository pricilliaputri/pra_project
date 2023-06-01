package com.binar.pra_project.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.binar.pra_project.model.Cart
import com.binar.pra_project.model.CartItem
import com.binar.pra_project.model.DataDetailProductItem
import com.binar.pra_project.model.UsersItem
import com.binar.pra_project.network.RestfulApi
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private var api : RestfulApi) : ViewModel() {
    private val liveLoadData = MutableLiveData<Boolean>()
    val loadData: LiveData<Boolean> = liveLoadData

    private var livedatacart : MutableLiveData<List<CartItem>> = MutableLiveData()

    fun getlivedatacart(): MutableLiveData<List<CartItem>>{
        return livedatacart
    }

    private var cart : MutableLiveData<CartItem> = MutableLiveData()

    fun getcart() : MutableLiveData<CartItem>{
        return cart
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

    fun addCart(id: String, name: String, productImage: String, price: Int, desc: String){
        liveLoadData.value = true
        api.addCart(id, name, productImage, price, desc).enqueue(object :
            Callback<List<CartItem>> {
            override fun onResponse(
                call: Call<List<CartItem>>,
                response: Response<List<CartItem>>

            ) {
                if (response.isSuccessful) {
                    liveLoadData.value = false
                    livedatacart.postValue(response.body())
                } else {
                    liveLoadData.value = false
                }
            }

            override fun onFailure(call: Call<List<CartItem>>, t: Throwable) {
                liveLoadData.value = false
            }
        })
    }

    }





