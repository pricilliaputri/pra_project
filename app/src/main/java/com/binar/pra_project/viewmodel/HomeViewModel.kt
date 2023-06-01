package com.binar.pra_project.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.binar.pra_project.model.DataDetailNewsItem
import com.binar.pra_project.model.DataDetailProductItem
import com.binar.pra_project.model.NewsUpdateItem
import com.binar.pra_project.model.ProductsItem
import com.binar.pra_project.network.RestfulApi
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private var api : RestfulApi) : ViewModel(){

    var livedatanews : MutableLiveData<List<NewsUpdateItem>> = MutableLiveData()
    var liveDetailNews: MutableLiveData<DataDetailNewsItem?> = MutableLiveData()
    var livedataproduk : MutableLiveData<List<ProductsItem>> = MutableLiveData()

    var livedataproduksecond : MutableLiveData<List<ProductsItem>> = MutableLiveData()

    var liveDetailProduk : MutableLiveData<DataDetailProductItem> = MutableLiveData()

    var liveDetailProdukSecond : MutableLiveData<DataDetailProductItem> = MutableLiveData()

    fun getUpdateNews(){
        api.getNewsUpdate().enqueue(object : Callback<List<NewsUpdateItem>> {
            override fun onResponse(
                call: Call<List<NewsUpdateItem>>,
                response: Response<List<NewsUpdateItem>>
            ) {
                if (response.isSuccessful) {
                    livedatanews.postValue(response.body())
                } else {
                    livedatanews.postValue(emptyList())
                }
            }

            override fun onFailure(call: Call<List<NewsUpdateItem>>, t: Throwable) {
                livedatanews.postValue(emptyList())
            }

        })
    }


    fun getDetailNews(id: Int) {
        api.getDetailNews(id).enqueue(object : Callback<DataDetailNewsItem> {
            override fun onResponse(
                call: Call<DataDetailNewsItem>,
                response: Response<DataDetailNewsItem>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        liveDetailNews.postValue(responseBody)
                    }
                }
            }
            override fun onFailure(call: Call<DataDetailNewsItem>, t: Throwable) {
                liveDetailNews.postValue(null)

            }

        })
    }

    fun getAllNewDataProduk(){
        api.getProduct().enqueue(object : Callback<List<ProductsItem>>{
            override fun onResponse(
                call: Call<List<ProductsItem>>,
                response: Response<List<ProductsItem>>
            ) {

                if (response.isSuccessful) {
                    livedataproduk.postValue(response.body())
                } else {
                    livedataproduk.postValue(emptyList())
                }

            }

            override fun onFailure(call: Call<List<ProductsItem>>, t: Throwable) {
                livedataproduk.postValue(emptyList())

            }

        })
    }

    fun getDetailProduk(id:String){
        api.getDetailProduct(id).enqueue(object : Callback<DataDetailProductItem>{
            override fun onResponse(
                call: Call<DataDetailProductItem>,
                response: Response<DataDetailProductItem>
            ) {
                if (response.isSuccessful) {
                        liveDetailProduk.postValue(response.body())

                }
            }

            override fun onFailure(call: Call<DataDetailProductItem>, t: Throwable) {

            }

        })

    }

    fun getAllNewProdukSecond(){
        api.getProductSecond().enqueue(object : Callback<List<ProductsItem>>{
            override fun onResponse(
                call: Call<List<ProductsItem>>,
                response: Response<List<ProductsItem>>
            ) {
                if (response.isSuccessful) {
                    livedataproduksecond.postValue(response.body())
                } else {
                    livedataproduksecond.postValue(emptyList())
                }
            }

            override fun onFailure(call: Call<List<ProductsItem>>, t: Throwable) {
                livedataproduksecond.postValue(emptyList())
            }

        })
    }

    fun getDetailProductSecond(id : Int){
        api.getDetailProductSecond(id).enqueue(object : Callback<DataDetailProductItem>{
            override fun onResponse(
                call: Call<DataDetailProductItem>,
                response: Response<DataDetailProductItem>
            ) {
                if (response.isSuccessful) {
                        liveDetailProdukSecond.postValue(response.body())

                }
            }

            override fun onFailure(call: Call<DataDetailProductItem>, t: Throwable) {


            }

        })
    }

}