package com.binar.pra_project.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.*
import com.binar.pra_project.database.UserPreferences
import com.binar.pra_project.model.DataPostUser
import com.binar.pra_project.model.DataUser
import com.binar.pra_project.model.UsersItem
import com.binar.pra_project.network.RestfulApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private var api : RestfulApi) : ViewModel(){

    private var livedatauser : MutableLiveData<List<DataPostUser>> = MutableLiveData()

    private var livedatauserlogin : MutableLiveData<List<UsersItem>> = MutableLiveData()

    fun getlivedatauser(): MutableLiveData<List<DataPostUser>>{
        return livedatauser
    }

    fun getlivedatauserlogin() : MutableLiveData<List<UsersItem>>{
        return livedatauserlogin
    }

    fun getregister(User : UsersItem){
        api.postUser(User).enqueue(object : Callback<List<DataPostUser>>{
            override fun onResponse(
                call: Call<List<DataPostUser>>,
                response: Response<List<DataPostUser>>
            ) {
                if (response.isSuccessful){
                    livedatauser.postValue(response.body())
                }else{
                    livedatauser.postValue(emptyList())
                }
            }

            override fun onFailure(call: Call<List<DataPostUser>>, t: Throwable) {
                livedatauser.postValue(emptyList())
            }

        })

    }

    fun getlogin(){
        api.getAllUser().enqueue(object : Callback<List<UsersItem>>{
            override fun onResponse(
                call: Call<List<UsersItem>>,
                response: Response<List<UsersItem>>
            ) {
                if (response.isSuccessful){
                    livedatauserlogin.postValue(response.body())
                }else{
                    livedatauserlogin.postValue(emptyList())
                }
            }

            override fun onFailure(call: Call<List<UsersItem>>, t: Throwable) {
                livedatauserlogin.postValue(emptyList())
            }

        })
    }

}