package com.binar.pra_project.viewmodel

import androidx.lifecycle.*
import androidx.lifecycle.LiveData
import com.binar.pra_project.model.DataPostUser
import com.binar.pra_project.model.DataUsers
import com.binar.pra_project.model.UsersItem
import com.binar.pra_project.network.RestfulApi
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val Client : RestfulApi) : ViewModel(){

    private var livedatauser : MutableLiveData<List<DataPostUser>> = MutableLiveData()
    val dataPostUser: LiveData<List<DataPostUser>> get() = livedatauser


    fun getregister(email: String, name: String, password: String){
        //memakai callback yang retrofit
        Client.postUser(DataUsers(email, "",name,password)).enqueue(object : Callback<List<DataPostUser>> {
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

    private var livedataUserLogin : MutableLiveData<List<UsersItem>> = MutableLiveData()
    val dataLoginUser: LiveData<List<UsersItem>> get() = livedataUserLogin
    fun UserLogin(){
        //memakai callback yang retrofit
        Client.getAllUsers().enqueue(object : Callback<List<UsersItem>> {
            override fun onResponse(
                call: Call<List<UsersItem>>,
                response: Response<List<UsersItem>>

            ) {
                if (response.isSuccessful){
                    livedataUserLogin.postValue(response.body())
                }else{
                    livedataUserLogin.postValue(emptyList())
                }
            }

            override fun onFailure(call: Call<List<UsersItem>>, t: Throwable) {
                livedataUserLogin.postValue(emptyList())
            }
        })
    }

}