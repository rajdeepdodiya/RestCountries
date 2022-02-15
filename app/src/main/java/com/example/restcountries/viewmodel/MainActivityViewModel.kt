package com.example.restcountries.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.restcountries.model.CountryModel
import com.example.restcountries.network.RetroInstance
import com.example.restcountries.network.RetroServiceInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel: ViewModel() {

    lateinit var liveDataList: MutableLiveData<List<CountryModel>>

    init {
        liveDataList = MutableLiveData()
    }

    fun getLiveDataObserver(): MutableLiveData<List<CountryModel>>{
        return liveDataList
    }

    fun makeApiCall(){
        val retroInstance = RetroInstance.getRetroInstance()
        val retroService = retroInstance.create(RetroServiceInterface::class.java)
        val call = retroService.getCountryList()

        call.enqueue(object : Callback<List<CountryModel>> {
            override fun onResponse(call: Call<List<CountryModel>>, response: Response<List<CountryModel>>) {

                Log.d("MY_TAG", "onResponse: response Code - ${response.code()}")
                if (response.code() == 200 && response.body() != null){
                    liveDataList.postValue(response.body())
                }
            }
            override fun onFailure(call: Call<List<CountryModel>>, t: Throwable) {
                liveDataList.postValue(null)
                Log.e("MY_TAG", "onFailure: ${t.localizedMessage}")
            }
        })
    }

}