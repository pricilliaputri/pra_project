package com.binar.pra_project.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binar.pra_project.model.NewsUpdateItem
import com.binar.pra_project.model.ProductsItem
import com.binar.pra_project.network.RestfulApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private var api : RestfulApi) : ViewModel() {

    var livedatanews : MutableLiveData<List<NewsUpdateItem>?> = MutableLiveData()

    fun getlivedatanews() : MutableLiveData<List<NewsUpdateItem>?> {

        return livedatanews
    }

    var livedataproduk : MutableLiveData<List<ProductsItem>?> = MutableLiveData()

    fun getlivedataproduk() : MutableLiveData<List<ProductsItem>?>{
        return livedataproduk
    }

    fun getUpdateNews(){
        api.getNewsUpdate().enqueue(object : Callback<List<NewsUpdateItem>> {
            override fun onResponse(
                call: Call<List<NewsUpdateItem>>,
                response: Response<List<NewsUpdateItem>>
            ) {
                if (response.isSuccessful) {
                    livedatanews.postValue(response.body())
                } else {
                    livedatanews.postValue(null)
                }
            }

            override fun onFailure(call: Call<List<NewsUpdateItem>>, t: Throwable) {
                livedatanews.postValue(null)
            }

        })
    }

    fun getAllDataProduk(){
        api.getProduct(1).enqueue(object : Callback<List<ProductsItem>>{
            override fun onResponse(
                call: Call<List<ProductsItem>>,
                response: Response<List<ProductsItem>>
            ) {

                if (response.isSuccessful) {
                    livedataproduk.postValue(response.body())
                } else {
                    livedataproduk.postValue(null)
                }

            }

            override fun onFailure(call: Call<List<ProductsItem>>, t: Throwable) {
                livedataproduk.postValue(null)

            }

        })
    }



}