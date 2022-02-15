package com.example.restcountries.network

import com.example.restcountries.model.CountryModel
import retrofit2.Call
import retrofit2.http.GET

interface RetroServiceInterface {

    @GET("v2/all")
    fun getCountryList(): Call<List<CountryModel>>
}