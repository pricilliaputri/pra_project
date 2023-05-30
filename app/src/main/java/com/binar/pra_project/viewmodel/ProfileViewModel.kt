package com.binar.pra_project.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.binar.pra_project.model.UsersItem
import com.binar.pra_project.network.RestfulApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ProfileViewModel @Inject constructor(private var api : RestfulApi) : ViewModel() {

    private val livedataprofile : MutableLiveData<UsersItem?> = MutableLiveData()

    fun getlivedataprofile(): MutableLiveData<UsersItem?>{
        return livedataprofile
    }

    fun profile(id:String) {
        api.putUserById(id).enqueue(object : Callback<UsersItem>{
            override fun onResponse(call: Call<UsersItem>, response: Response<UsersItem>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        livedataprofile.postValue(responseBody)
                    }
                }
            }

            override fun onFailure(call: Call<UsersItem>, t: Throwable) {

            }

        })
    }




}
