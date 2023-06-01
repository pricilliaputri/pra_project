package com.binar.pra_project.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.binar.pra_project.model.Favourite
import com.binar.pra_project.model.FavouriteItem
import com.binar.pra_project.network.RestfulApi
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(private val api : RestfulApi) : ViewModel() {

    //set up data
    private val _listFavourite = MutableLiveData<List<FavouriteItem>>()
    val listFavourite: LiveData<List<FavouriteItem>> = _listFavourite

    // get item favourite
    private val _itemFavourite = MutableLiveData<FavouriteItem>()
    val itemFavourite: LiveData<FavouriteItem> = _itemFavourite



    //get favourite product
    fun getFavouriteProducts(id:String){
        api.getFavourite(id).enqueue(object : Callback<List<FavouriteItem>> {
            override fun onResponse(
                call: Call<List<FavouriteItem>>,
                response: Response<List<FavouriteItem>>
            ) {
                if (response.isSuccessful){
                    _listFavourite.value = response.body()

                } else{
                    Log.e(TAG, "Error ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<FavouriteItem>>, t: Throwable) {
                Log.e(TAG, t.message.toString())

            }

        })
    }

    fun postFavouriteProducts(id:String,name:String,productImage:String,price:Int,description:String){

        api.addFavouriteProduct(id,name,productImage,price,description).enqueue(object :Callback<FavouriteItem>{
            override fun onResponse(
                call: Call<FavouriteItem>,
                response: Response<FavouriteItem>
            ) {
                if (response.isSuccessful){
                    _itemFavourite.value = response.body()

                } else{
                    Log.e(TAG, "Error ${response.message()}")
                }
            }

            override fun onFailure(call: Call<FavouriteItem>, t: Throwable) {
                Log.e(TAG, t.message.toString())
            }
        })
    }

    fun deleteFavouriteProducts(userId:String,favId:String){
        api.deleteFavouriteProduct(userId,favId).enqueue(object :Callback<FavouriteItem>{
            override fun onResponse(
                call: Call<FavouriteItem>,
                response: Response<FavouriteItem>
            ) {
                if (response.isSuccessful){
                    _itemFavourite.value = response.body()

                } else{
                    Log.e(TAG, "Error ${response.message()}")
                }
            }

            override fun onFailure(call: Call<FavouriteItem>, t: Throwable) {
                Log.e(TAG, t.message.toString())
            }
        })
    }

    companion object{
        const val TAG = "FavouriteViewModel"
    }
}