package com.example.funcy_portfolio_android.model

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val URL = "http://192.168.3.7:8080/"

val gson = GsonBuilder()
    .serializeNulls()
    .create()

private val retrofit = Retrofit.Builder()
    .baseUrl(URL)
    .addConverterFactory(GsonConverterFactory.create(gson))
    .build()

object apiService{
    val service: ICreationApi by lazy{
        retrofit.create(ICreationApi::class.java)
    }
}