package com.binar.pra_project.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.binar.pra_project.model.DataPostUser
import com.binar.pra_project.model.DataUser
import com.binar.pra_project.model.UsersItem
import com.binar.pra_project.network.RestfulApi
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private var api : RestfulApi) : ViewModel(){

    var livedatausers : MutableLiveData<List<DataPostUser>?> = MutableLiveData()

    var idUser : MutableLiveData<List<UsersItem>?> = MutableLiveData()

    var livealldatausers : MutableLiveData<List<UsersItem>?> = MutableLiveData()

    fun getlivedatausers() :MutableLiveData<List<DataPostUser>?>{
        return livedatausers
    }

    fun getidUser() :MutableLiveData<List<UsersItem>?>{
        return idUser
    }

    fun getAllUser() :MutableLiveData<List<UsersItem>?>{
        return livealldatausers
    }

    fun addDataUser(email : String, name: String, password : String){
        api.postUser(DataUser(email, "", name, password)).enqueue(object :
            Callback<List<DataPostUser>>{
            override fun onResponse(
                call: Call<List<DataPostUser>>,
                response: Response<List<DataPostUser>>
            ) {
                if (response.isSuccessful){

                    livedatausers.postValue(response.body())

                }else{
                    error(response.message())
                }
            }

            override fun onFailure(call: Call<List<DataPostUser>>, t: Throwable) {
                livedatausers.postValue(null)
            }

        })
    }


    fun postDataUser(email : String, id : Int, name : String, password : String){
        api.putUserById(id,DataUser(email,name, password, id.toString())).enqueue(object : Callback<List<DataPostUser>>{
            override fun onResponse(
                call: Call<List<DataPostUser>>,
                response: Response<List<DataPostUser>>
            ) {
                if (response.isSuccessful){
                    livedatausers.postValue(response.body())
                }else{
                    error(response.message())
                }
            }

            override fun onFailure(call: Call<List<DataPostUser>>, t: Throwable) {

                livedatausers.postValue(null)

            }

        })
    }


    fun getuserbyid(id : String){
        api.getUserId(id).enqueue(object : Callback<List<UsersItem>>{
            override fun onResponse(
                call: Call<List<UsersItem>>,
                response: Response<List<UsersItem>>
            ) {
                if (response.isSuccessful){
                    idUser.postValue(response.body())
                }else{
                    error(response.message())
                }
            }

            override fun onFailure(call: Call<List<UsersItem>>, t: Throwable) {
                idUser.postValue(null)
            }

        })

    }

    fun alldatausers(){
        api.getAllUser().enqueue(object : Callback<List<UsersItem>>{
            override fun onResponse(
                call: Call<List<UsersItem>>,
                response: Response<List<UsersItem>>
            ) {
                if (response.isSuccessful){
                   livealldatausers.postValue(response.body())
                }else{
                    error(response.message())
                }
            }

            override fun onFailure(call: Call<List<UsersItem>>, t: Throwable) {
                livealldatausers.postValue(null)
            }

        })
    }








}