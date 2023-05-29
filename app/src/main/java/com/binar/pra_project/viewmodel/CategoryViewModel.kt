package com.binar.pra_project.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binar.pra_project.model.ProductsItem
import com.binar.pra_project.network.RestfulApi
import com.binar.pra_project.wrap.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val api : RestfulApi): ViewModel(){

    private val listproduk: MutableLiveData<Resource<List<ProductsItem>>> = MutableLiveData()
    val listProduct: LiveData<Resource<List<ProductsItem>>> get() = listproduk


    fun setCategoryList(id: Int) = viewModelScope.launch {
        try {

            listproduk.postValue(Resource.Loading())

            val response = api.getProduct(id)
            listproduk.postValue(Resource.Success(response))

        } catch(e: Exception) {
            listproduk.postValue(Resource.Error(e.message!!))
            Log.e("error", e.message!!)
        }
    }


}