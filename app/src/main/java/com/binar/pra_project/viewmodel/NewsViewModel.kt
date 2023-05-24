package com.binar.pra_project.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.binar.pra_project.model.NewsUpdateItem
import com.binar.pra_project.network.RestfulApi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@HiltViewModel
class NewsViewModel @Inject constructor(private var api : RestfulApi) : ViewModel() {
    var livedatanews : MutableLiveData<List<NewsUpdateItem>?> = MutableLiveData()

    fun getlivedatanews() : MutableLiveData<List<NewsUpdateItem>?>{

        return livedatanews
    }

    fun getUpdateNews(){
        api.getNewsUpdate().enqueue(object : Callback<List<NewsUpdateItem>>{
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


}